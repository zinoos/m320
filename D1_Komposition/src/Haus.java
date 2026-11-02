import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Ein Haus besteht aus einem oder mehreren Raeumen.
 * Komposition: Das Haus erzeugt seine Raeume selbst (createRaum); ein Raum existiert nicht ohne Haus.
 */
public class Haus {

    private String adresse;
    private final List<Raum> raeume = new ArrayList<>();

    public Haus(String adresse) {
        this.adresse = adresse;
    }

    /** Neuer Raum mit Heizung im Standardbereich 10-30 Grad. */
    public Raum createRaum(String name, int temperature, int increment) {
        Raum r = new Raum(this, name, temperature, increment);
        raeume.add(r);
        return r;
    }

    /** Neuer Raum mit Heizung in einem eigenen Temperaturbereich min-max. */
    public Raum createRaum(String name, int temperature, int increment, int min, int max) {
        Raum r = new Raum(this, name, temperature, increment, min, max);
        raeume.add(r);
        return r;
    }

    public void removeRaum(Raum raum) {
        raeume.remove(raum);
    }

    public List<Raum> getRaeume() {
        return Collections.unmodifiableList(raeume);
    }

    public int getAnzahlRaeume() { return raeume.size(); }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    @Override
    public String toString() {
        return "Haus an " + adresse + " mit " + raeume.size() + " Raum/Räumen";
    }
}