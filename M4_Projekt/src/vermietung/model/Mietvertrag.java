package vermietung.model;

import vermietung.fehler.ValidierungException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Verbindet Fahrzeug und Kunde fuer einen Zeitraum.
 * Haelt nur die IDs (nicht die Objekte), damit die CSV-Persistenz einfach bleibt.
 * Ein Vertrag ist "offen", bis das Fahrzeug zurueckgegeben wurde.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Mietvertrag {

    private static final DateTimeFormatter ANZEIGE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final String id;
    private final String fahrzeugId;
    private final String kundeId;
    private final LocalDate von;
    private final LocalDate bisGeplant;
    private LocalDate rueckgabe;
    private int gefahreneKm;
    private double betrag;

    public Mietvertrag(String id, String fahrzeugId, String kundeId, LocalDate von, LocalDate bisGeplant,
                       LocalDate rueckgabe, int gefahreneKm, double betrag) {
        pruefeZeitraum(von, bisGeplant);
        this.id = Validierung.pruefeText(id, "ID");
        this.fahrzeugId = Validierung.pruefeText(fahrzeugId, "Fahrzeug-ID");
        this.kundeId = Validierung.pruefeText(kundeId, "Kunden-ID");
        this.von = von;
        this.bisGeplant = bisGeplant;
        this.rueckgabe = rueckgabe;
        this.gefahreneKm = Validierung.pruefeNichtNegativ(gefahreneKm, "Gefahrene km");
        this.betrag = betrag;
    }

    /** Neuer, offener Vertrag mit dem voraussichtlichen Betrag. */
    public static Mietvertrag neu(String id, String fahrzeugId, String kundeId,
                                  LocalDate von, LocalDate bis, double voraussichtlicherBetrag) {
        return new Mietvertrag(id, fahrzeugId, kundeId, von, bis, null, 0, voraussichtlicherBetrag);
    }

    public static void pruefeZeitraum(LocalDate von, LocalDate bis) {
        if (von == null || bis == null) {
            throw new ValidierungException("Start- und Enddatum muessen angegeben werden.");
        }
        if (bis.isBefore(von)) {
            throw new ValidierungException("Das Enddatum darf nicht vor dem Startdatum liegen.");
        }
    }

    /** Abholung und Rueckgabe am selben Tag zaehlt als 1 Tag. */
    public static long berechneTage(LocalDate von, LocalDate bis) {
        return Math.max(1, ChronoUnit.DAYS.between(von, bis));
    }

    public void abschliessen(LocalDate rueckgabeDatum, int gefahreneKm, double endBetrag) {
        if (!istOffen()) {
            throw new ValidierungException("Mietvertrag " + id + " ist bereits abgeschlossen.");
        }
        if (rueckgabeDatum.isBefore(von)) {
            throw new ValidierungException("Die Rueckgabe kann nicht vor dem Mietbeginn liegen.");
        }
        this.rueckgabe = rueckgabeDatum;
        this.gefahreneKm = gefahreneKm;
        this.betrag = endBetrag;
    }

    public boolean istOffen() {
        return rueckgabe == null;
    }

    public String getId() {
        return id;
    }

    public String getFahrzeugId() {
        return fahrzeugId;
    }

    public String getKundeId() {
        return kundeId;
    }

    public LocalDate getVon() {
        return von;
    }

    public LocalDate getBisGeplant() {
        return bisGeplant;
    }

    public LocalDate getRueckgabe() {
        return rueckgabe;
    }

    public int getGefahreneKm() {
        return gefahreneKm;
    }

    public double getBetrag() {
        return betrag;
    }

    @Override
    public String toString() {
        String status = istOffen()
                ? "OFFEN bis " + bisGeplant.format(ANZEIGE_FORMAT)
                : "zurueck " + rueckgabe.format(ANZEIGE_FORMAT) + ", " + gefahreneKm + " km";
        return String.format("%-6s %-6s %-6s ab %s  %-32s %9.2f CHF",
                id, fahrzeugId, kundeId, von.format(ANZEIGE_FORMAT), status, betrag);
    }
}
