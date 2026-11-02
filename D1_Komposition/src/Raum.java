/**
 * Ein Raum gehoert zu genau einem Haus und besitzt genau eine Heizung.
 * Komposition: Der Raum erzeugt seine Heizung selbst; ohne Raum gibt es keine Heizung.
 * Die Konstruktoren sind package-private, damit Raeume nur ueber Haus.createRaum(...) entstehen.
 */
public class Raum {

    private String name;
    private final Haus haus;
    private final Heizung heizung;

    Raum(Haus haus, String name, int temperature, int increment) {
        this.haus = haus;
        this.name = name;
        this.heizung = new Heizung(temperature, increment);
    }

    Raum(Haus haus, String name, int temperature, int increment, int min, int max) {
        this.haus = haus;
        this.name = name;
        this.heizung = new Heizung(temperature, increment, min, max);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Haus getHaus() { return haus; }
    public Heizung getHeizung() { return heizung; }

    @Override
    public String toString() {
        return "Raum '" + name + "' -> " + heizung;
    }
}
