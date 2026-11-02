package vermietung.model;

import vermietung.fehler.FahrzeugNichtVerfuegbarException;
import vermietung.fehler.ValidierungException;

/**
 * Abstrakte Superklasse aller Fahrzeuge (Vererbungshierarchie).
 * Gemeinsame Daten und Verhalten (Verfuegbarkeit, Kilometerstand) liegen hier,
 * der typ-spezifische Preiszuschlag wird von den Subklassen polymorph geliefert.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public abstract class Fahrzeug {

    private final String id;
    private final String marke;
    private final String modell;
    private final double grundpreisProTag;
    private int kilometerstand;
    private boolean verfuegbar;

    protected Fahrzeug(String id, String marke, String modell, double grundpreisProTag,
                       int kilometerstand, boolean verfuegbar) {
        this.id = Validierung.pruefeText(id, "ID");
        this.marke = Validierung.pruefeText(marke, "Marke");
        this.modell = Validierung.pruefeText(modell, "Modell");
        this.grundpreisProTag = Validierung.pruefePositiv(grundpreisProTag, "Grundpreis pro Tag");
        this.kilometerstand = Validierung.pruefeNichtNegativ(kilometerstand, "Kilometerstand");
        this.verfuegbar = verfuegbar;
    }

    /**
     * Template Method: Der Ablauf ist fix (Grundpreis + Zuschlag),
     * nur der Zuschlag wird von der Subklasse bestimmt (Polymorphismus).
     */
    public final double getTagespreis() {
        return grundpreisProTag + berechneZuschlagProTag();
    }

    protected abstract double berechneZuschlagProTag();

    public abstract FahrzeugTyp getTyp();

    /** Typ-spezifischer Wert: Sitze (Auto), Hubraum (Motorrad), Ladevolumen (Lieferwagen). */
    public abstract int getZusatzwert();

    public void vermieten() {
        if (!verfuegbar) {
            throw new FahrzeugNichtVerfuegbarException(id);
        }
        verfuegbar = false;
    }

    public void zuruecknehmen(int neuerKilometerstand) {
        if (verfuegbar) {
            throw new ValidierungException("Fahrzeug " + id + " ist gar nicht vermietet.");
        }
        if (neuerKilometerstand < kilometerstand) {
            throw new ValidierungException("Neuer Kilometerstand (" + neuerKilometerstand
                    + ") ist kleiner als der bisherige (" + kilometerstand + ").");
        }
        kilometerstand = neuerKilometerstand;
        verfuegbar = true;
    }

    public String getId() {
        return id;
    }

    public String getMarke() {
        return marke;
    }

    public String getModell() {
        return modell;
    }

    public double getGrundpreisProTag() {
        return grundpreisProTag;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public boolean istVerfuegbar() {
        return verfuegbar;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-11s %-10s %-10s %7d km %17s: %-4d %7.2f CHF/Tag  %s",
                id, getTyp().getBezeichnung(), marke, modell, kilometerstand,
                getTyp().getZusatzBezeichnung(), getZusatzwert(),
                getTagespreis(), verfuegbar ? "verfuegbar" : "VERMIETET");
    }
}
