package vermietung.fehler;

/**
 * Basisklasse aller fachlichen Fehler der Applikation.
 * Unchecked, damit die Logik nicht mit throws-Klauseln ueberladen wird;
 * die UI faengt sie zentral ab und zeigt die Meldung an.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic), Prompts siehe docs/KI-Prompts.md.
 */
public class VermietungException extends RuntimeException {

    public VermietungException(String meldung) {
        super(meldung);
    }

    public VermietungException(String meldung, Throwable ursache) {
        super(meldung, ursache);
    }
}
