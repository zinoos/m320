package vermietung.fehler;

/**
 * Ungueltige Benutzereingabe oder ungueltige Daten (z.B. negativer Preis, leerer Name).
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class ValidierungException extends VermietungException {

    public ValidierungException(String meldung) {
        super(meldung);
    }
}
