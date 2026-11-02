package vermietung.fehler;

/**
 * Das Fahrzeug ist bereits vermietet und kann nicht erneut vermietet werden.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class FahrzeugNichtVerfuegbarException extends VermietungException {

    public FahrzeugNichtVerfuegbarException(String fahrzeugId) {
        super("Fahrzeug " + fahrzeugId + " ist bereits vermietet.");
    }
}
