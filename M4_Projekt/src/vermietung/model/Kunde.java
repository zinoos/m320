package vermietung.model;

/**
 * Ein registrierter Kunde. Stammkunden erhalten je nach Preisstrategie Rabatt.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Kunde {

    private final String id;
    private final String name;
    private final String email;
    private final boolean stammkunde;

    public Kunde(String id, String name, String email, boolean stammkunde) {
        this.id = Validierung.pruefeText(id, "ID");
        this.name = Validierung.pruefeText(name, "Name");
        this.email = Validierung.pruefeEmail(email);
        this.stammkunde = stammkunde;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean istStammkunde() {
        return stammkunde;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-22s %-28s %s", id, name, email, stammkunde ? "Stammkunde" : "");
    }
}
