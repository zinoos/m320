package vermietung.fehler;

/**
 * Ein Fahrzeug, Kunde oder Mietvertrag mit der angegebenen ID existiert nicht.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class EintragNichtGefundenException extends VermietungException {

    public EintragNichtGefundenException(String art, String id) {
        super(art + " mit ID '" + id + "' wurde nicht gefunden.");
    }
}
