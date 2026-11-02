package vermietung.fehler;

/**
 * Fehler beim Lesen oder Schreiben der CSV-Dateien.
 * Kapselt die IOException, damit die Logik-Schicht nichts von Dateien wissen muss.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class DatenzugriffException extends VermietungException {

    public DatenzugriffException(String meldung, Throwable ursache) {
        super(meldung, ursache);
    }
}
