package vermietung.model;

import vermietung.fehler.ValidierungException;

/**
 * Die Fahrzeugarten, die die Vermietung anbietet.
 * Jeder Typ kennt die Bezeichnung seines typ-spezifischen Zusatzwertes (fuer die UI-Abfrage).
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public enum FahrzeugTyp {
    AUTO("Auto", "Sitze"),
    MOTORRAD("Motorrad", "Hubraum (ccm)"),
    LIEFERWAGEN("Lieferwagen", "Ladevolumen (m3)");

    private final String bezeichnung;
    private final String zusatzBezeichnung;

    FahrzeugTyp(String bezeichnung, String zusatzBezeichnung) {
        this.bezeichnung = bezeichnung;
        this.zusatzBezeichnung = zusatzBezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getZusatzBezeichnung() {
        return zusatzBezeichnung;
    }

    public static FahrzeugTyp ausText(String text) {
        for (FahrzeugTyp typ : values()) {
            if (typ.name().equalsIgnoreCase(text) || typ.bezeichnung.equalsIgnoreCase(text)) {
                return typ;
            }
        }
        throw new ValidierungException("Unbekannter Fahrzeugtyp: " + text);
    }
}
