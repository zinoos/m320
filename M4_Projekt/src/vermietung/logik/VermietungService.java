package vermietung.logik;

import vermietung.fehler.EintragNichtGefundenException;
import vermietung.fehler.ValidierungException;
import vermietung.model.Fahrzeug;
import vermietung.model.FahrzeugFactory;
import vermietung.model.FahrzeugTyp;
import vermietung.model.Kunde;
import vermietung.model.Mietvertrag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fachlogik der Vermietung. Kennt weder Konsole noch Dateien:
 * - Speichern wird an die Repositories delegiert (IRepository),
 * - die Preisberechnung an die aktuelle Preisstrategie (IPreisStrategie),
 * - Benachrichtigungen an die registrierten Listener (IMietListener).
 *
 * Clean Code (Single Responsibility): diese Klasse enthaelt nur Geschaeftsregeln.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class VermietungService {

    private final IRepository<Fahrzeug> fahrzeugRepository;
    private final IRepository<Kunde> kundeRepository;
    private final IRepository<Mietvertrag> vertragRepository;
    private final List<IMietListener> listener = new ArrayList<>();
    private IPreisStrategie preisStrategie;

    // Clean Code (Dependency Injection): Abhaengigkeiten werden von aussen uebergeben, nicht selbst erzeugt
    public VermietungService(IRepository<Fahrzeug> fahrzeugRepository, IRepository<Kunde> kundeRepository,
                             IRepository<Mietvertrag> vertragRepository, IPreisStrategie preisStrategie) {
        this.fahrzeugRepository = Objects.requireNonNull(fahrzeugRepository);
        this.kundeRepository = Objects.requireNonNull(kundeRepository);
        this.vertragRepository = Objects.requireNonNull(vertragRepository);
        setPreisStrategie(preisStrategie);
    }

    // ---------- Fahrzeuge ----------

    public Fahrzeug fahrzeugErfassen(FahrzeugTyp typ, String marke, String modell,
                                     double grundpreisProTag, int kilometerstand, int zusatzwert) {
        Fahrzeug fahrzeug = FahrzeugFactory.erstelle(typ, fahrzeugRepository.naechsteId(), marke, modell,
                grundpreisProTag, kilometerstand, true, zusatzwert);
        fahrzeugRepository.speichern(fahrzeug);
        return fahrzeug;
    }

    public List<Fahrzeug> getAlleFahrzeuge() {
        return fahrzeugRepository.findeAlle();
    }

    public List<Fahrzeug> getVerfuegbareFahrzeuge() {
        return fahrzeugRepository.findeAlle().stream().filter(Fahrzeug::istVerfuegbar).toList();
    }

    public Fahrzeug getFahrzeug(String id) {
        return fahrzeugRepository.findeNachId(id)
                .orElseThrow(() -> new EintragNichtGefundenException("Fahrzeug", id));
    }

    // ---------- Kunden ----------

    public Kunde kundeRegistrieren(String name, String email, boolean stammkunde) {
        Kunde kunde = new Kunde(kundeRepository.naechsteId(), name, email, stammkunde);
        boolean emailVergeben = kundeRepository.findeAlle().stream()
                .anyMatch(k -> k.getEmail().equals(kunde.getEmail()));
        if (emailVergeben) {
            throw new ValidierungException("Die E-Mail " + kunde.getEmail() + " ist bereits registriert.");
        }
        kundeRepository.speichern(kunde);
        return kunde;
    }

    public List<Kunde> getAlleKunden() {
        return kundeRepository.findeAlle();
    }

    public Kunde getKunde(String id) {
        return kundeRepository.findeNachId(id)
                .orElseThrow(() -> new EintragNichtGefundenException("Kunde", id));
    }

    // ---------- Vermieten / Zurueckgeben ----------

    /** Use Case "Fahrzeug vermieten" (siehe Sequenzdiagramm). */
    public Mietvertrag vermieten(String fahrzeugId, String kundeId, LocalDate von, LocalDate bis) {
        Mietvertrag.pruefeZeitraum(von, bis);
        Fahrzeug fahrzeug = getFahrzeug(fahrzeugId);
        Kunde kunde = getKunde(kundeId);

        double voraussichtlicherBetrag = preisStrategie.berechnePreis(fahrzeug, kunde, von, bis);
        fahrzeug.vermieten();
        Mietvertrag vertrag = Mietvertrag.neu(vertragRepository.naechsteId(), fahrzeug.getId(), kunde.getId(),
                von, bis, voraussichtlicherBetrag);

        fahrzeugRepository.speichern(fahrzeug);
        vertragRepository.speichern(vertrag);
        benachrichtigeVermietet(vertrag);
        return vertrag;
    }

    /** Use Case "Fahrzeug zurueckgeben": Endbetrag wird mit dem effektiven Rueckgabedatum berechnet. */
    public Mietvertrag zurueckgeben(String vertragId, LocalDate rueckgabeDatum, int neuerKilometerstand) {
        Mietvertrag vertrag = vertragRepository.findeNachId(vertragId)
                .orElseThrow(() -> new EintragNichtGefundenException("Mietvertrag", vertragId));
        if (!vertrag.istOffen()) {
            throw new ValidierungException("Mietvertrag " + vertragId + " ist bereits abgeschlossen.");
        }
        Mietvertrag.pruefeZeitraum(vertrag.getVon(), rueckgabeDatum);
        Fahrzeug fahrzeug = getFahrzeug(vertrag.getFahrzeugId());
        Kunde kunde = getKunde(vertrag.getKundeId());

        int gefahreneKm = neuerKilometerstand - fahrzeug.getKilometerstand();
        double endBetrag = preisStrategie.berechnePreis(fahrzeug, kunde, vertrag.getVon(), rueckgabeDatum);
        fahrzeug.zuruecknehmen(neuerKilometerstand);
        vertrag.abschliessen(rueckgabeDatum, gefahreneKm, endBetrag);

        fahrzeugRepository.speichern(fahrzeug);
        vertragRepository.speichern(vertrag);
        benachrichtigeZurueckgegeben(vertrag);
        return vertrag;
    }

    public List<Mietvertrag> getAlleVertraege() {
        return vertragRepository.findeAlle();
    }

    public List<Mietvertrag> getOffeneVertraege() {
        return vertragRepository.findeAlle().stream().filter(Mietvertrag::istOffen).toList();
    }

    public double berechneUmsatz() {
        return vertragRepository.findeAlle().stream()
                .filter(v -> !v.istOffen())
                .mapToDouble(Mietvertrag::getBetrag)
                .sum();
    }

    // ---------- Strategy ----------

    public void setPreisStrategie(IPreisStrategie preisStrategie) {
        this.preisStrategie = Objects.requireNonNull(preisStrategie, "Preisstrategie fehlt");
    }

    public IPreisStrategie getPreisStrategie() {
        return preisStrategie;
    }

    // ---------- Observer ----------

    public void registriereListener(IMietListener neuerListener) {
        listener.add(Objects.requireNonNull(neuerListener));
    }

    private void benachrichtigeVermietet(Mietvertrag vertrag) {
        for (IMietListener l : listener) {
            l.fahrzeugVermietet(vertrag);
        }
    }

    private void benachrichtigeZurueckgegeben(Mietvertrag vertrag) {
        for (IMietListener l : listener) {
            l.fahrzeugZurueckgegeben(vertrag);
        }
    }
}
