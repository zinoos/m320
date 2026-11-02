package vermietung.ui;

import vermietung.fehler.DatenzugriffException;
import vermietung.fehler.VermietungException;
import vermietung.logik.IMietListener;
import vermietung.logik.IPreisStrategie;
import vermietung.logik.StammkundenPreisStrategie;
import vermietung.logik.StandardPreisStrategie;
import vermietung.logik.VermietungService;
import vermietung.logik.WochenendPreisStrategie;
import vermietung.model.Fahrzeug;
import vermietung.model.FahrzeugTyp;
import vermietung.model.Kunde;
import vermietung.model.Mietvertrag;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Konsolen-Benutzeroberflaeche. Zeigt das Menue, liest Eingaben und ruft den Service auf.
 * Enthaelt keine Geschaeftsregeln; Fehler aus der Logik werden hier abgefangen und angezeigt.
 * Ist zugleich ein Observer (IMietListener) und meldet Vermietungen auf der Konsole.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class KonsolenUI implements IMietListener {

    private static final String LINIE = "-".repeat(90);

    private final VermietungService service;
    private final Eingabe eingabe;

    public KonsolenUI(VermietungService service, Eingabe eingabe) {
        this.service = service;
        this.eingabe = eingabe;
    }

    public void starten() {
        System.out.println("=== Fahrzeugvermietung ===");
        boolean laeuft = true;
        while (laeuft) {
            zeigeMenue();
            try {
                int auswahl = eingabe.leseGanzzahl("Auswahl: ", 0, 9);
                laeuft = fuehreAus(auswahl);
            } catch (DatenzugriffException e) {
                eingabe.zeigeFehler("Speicherfehler: " + e.getMessage());
            } catch (VermietungException e) {
                // Clean Code: Fehlerbehandlung an einer Stelle statt in jedem Menuepunkt
                eingabe.zeigeFehler(e.getMessage());
            } catch (NoSuchElementException e) {
                laeuft = false;
            }
        }
        System.out.println("Auf Wiedersehen.");
    }

    private void zeigeMenue() {
        System.out.println();
        System.out.println(LINIE);
        System.out.println(" 1 Alle Fahrzeuge anzeigen        6 Fahrzeug vermieten");
        System.out.println(" 2 Verfuegbare Fahrzeuge          7 Fahrzeug zurueckgeben");
        System.out.println(" 3 Fahrzeug erfassen              8 Mietvertraege & Umsatz anzeigen");
        System.out.println(" 4 Kunden anzeigen                9 Preisstrategie waehlen");
        System.out.println(" 5 Kunde registrieren             0 Beenden");
        System.out.println(" Aktueller Tarif: " + service.getPreisStrategie().getBezeichnung());
        System.out.println(LINIE);
    }

    // Clean Code: jede Menue-Aktion ist eine eigene, kurze Methode mit sprechendem Namen
    private boolean fuehreAus(int auswahl) {
        switch (auswahl) {
            case 1 -> zeigeListe("Alle Fahrzeuge", service.getAlleFahrzeuge());
            case 2 -> zeigeListe("Verfuegbare Fahrzeuge", service.getVerfuegbareFahrzeuge());
            case 3 -> fahrzeugErfassen();
            case 4 -> zeigeListe("Kunden", service.getAlleKunden());
            case 5 -> kundeRegistrieren();
            case 6 -> fahrzeugVermieten();
            case 7 -> fahrzeugZurueckgeben();
            case 8 -> vertraegeAnzeigen();
            case 9 -> preisStrategieWaehlen();
            default -> {
                return false;
            }
        }
        return true;
    }

    private void zeigeListe(String titel, List<?> eintraege) {
        System.out.println(titel + " (" + eintraege.size() + "):");
        if (eintraege.isEmpty()) {
            System.out.println("  (keine Eintraege)");
        }
        eintraege.forEach(e -> System.out.println("  " + e));
    }

    private void fahrzeugErfassen() {
        FahrzeugTyp[] typen = FahrzeugTyp.values();
        for (int i = 0; i < typen.length; i++) {
            System.out.println("  " + (i + 1) + " " + typen[i].getBezeichnung());
        }
        FahrzeugTyp typ = typen[eingabe.leseGanzzahl("Typ: ", 1, typen.length) - 1];
        String marke = eingabe.leseText("Marke: ");
        String modell = eingabe.leseText("Modell: ");
        double preis = eingabe.leseBetrag("Grundpreis pro Tag (CHF): ");
        int km = eingabe.leseGanzzahl("Kilometerstand: ", 0, Integer.MAX_VALUE);
        int zusatz = eingabe.leseGanzzahl(typ.getZusatzBezeichnung() + ": ", 0, Integer.MAX_VALUE);

        Fahrzeug fahrzeug = service.fahrzeugErfassen(typ, marke, modell, preis, km, zusatz);
        System.out.println("Erfasst: " + fahrzeug);
    }

    private void kundeRegistrieren() {
        String name = eingabe.leseText("Name: ");
        String email = eingabe.leseText("E-Mail: ");
        boolean stammkunde = eingabe.leseJaNein("Stammkunde?");

        Kunde kunde = service.kundeRegistrieren(name, email, stammkunde);
        System.out.println("Registriert: " + kunde);
    }

    private void fahrzeugVermieten() {
        zeigeListe("Verfuegbare Fahrzeuge", service.getVerfuegbareFahrzeuge());
        String fahrzeugId = eingabe.leseText("Fahrzeug-ID: ");
        zeigeListe("Kunden", service.getAlleKunden());
        String kundeId = eingabe.leseText("Kunden-ID: ");
        LocalDate von = eingabe.leseDatum("Von", LocalDate.now());
        LocalDate bis = eingabe.leseDatum("Bis", von.plusDays(1));

        Mietvertrag vertrag = service.vermieten(fahrzeugId, kundeId, von, bis);
        System.out.printf("Mietvertrag %s erstellt, voraussichtlich %.2f CHF%n", vertrag.getId(), vertrag.getBetrag());
    }

    private void fahrzeugZurueckgeben() {
        zeigeListe("Offene Mietvertraege", service.getOffeneVertraege());
        if (service.getOffeneVertraege().isEmpty()) {
            return;
        }
        String vertragId = eingabe.leseText("Mietvertrag-ID: ");
        LocalDate rueckgabe = eingabe.leseDatum("Rueckgabedatum", LocalDate.now());
        int km = eingabe.leseGanzzahl("Neuer Kilometerstand: ", 0, Integer.MAX_VALUE);

        Mietvertrag vertrag = service.zurueckgeben(vertragId, rueckgabe, km);
        System.out.printf("Rueckgabe erfasst: %d km gefahren, Endbetrag %.2f CHF%n",
                vertrag.getGefahreneKm(), vertrag.getBetrag());
    }

    private void vertraegeAnzeigen() {
        zeigeListe("Mietvertraege", service.getAlleVertraege());
        System.out.printf("Umsatz abgeschlossener Vertraege: %.2f CHF%n", service.berechneUmsatz());
    }

    private void preisStrategieWaehlen() {
        System.out.println("  1 Standardtarif");
        System.out.println("  2 Wochenend-Aktion (Sa/So -25%)");
        System.out.println("  3 Standardtarif + Stammkunden -10%");
        System.out.println("  4 Wochenend-Aktion + Stammkunden -10%");
        IPreisStrategie strategie = switch (eingabe.leseGanzzahl("Tarif: ", 1, 4)) {
            case 1 -> new StandardPreisStrategie();
            case 2 -> new WochenendPreisStrategie();
            case 3 -> new StammkundenPreisStrategie(new StandardPreisStrategie());
            default -> new StammkundenPreisStrategie(new WochenendPreisStrategie());
        };
        service.setPreisStrategie(strategie);
        System.out.println("Tarif gesetzt: " + strategie.getBezeichnung());
    }

    @Override
    public void fahrzeugVermietet(Mietvertrag vertrag) {
        System.out.println("  [Info] Fahrzeug " + vertrag.getFahrzeugId() + " ist jetzt vermietet.");
    }

    @Override
    public void fahrzeugZurueckgegeben(Mietvertrag vertrag) {
        System.out.println("  [Info] Fahrzeug " + vertrag.getFahrzeugId() + " ist wieder verfuegbar.");
    }
}
