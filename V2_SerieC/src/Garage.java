import java.util.ArrayList;
import java.util.List;

// Verwaltet die Liste aller Fahrzeuge
public class Garage {

    // Liste vom Typ Fahrzeug: darf Autos, Motorraeder und Lastwagen enthalten
    private List<Fahrzeug> fahrzeuge = new ArrayList<>();

    public void hinzufuegen(Fahrzeug fahrzeug) {
        fahrzeuge.add(fahrzeug);
    }

    // Jedes Objekt ruft seine eigene print-Methode auf, egal ob Auto, Motorrad oder Lastwagen
    public void print() {
        for (Fahrzeug f : fahrzeuge) {
            f.print();
        }
    }

    // Sucht ein Fahrzeug nach Kundenname, gibt null zurueck wenn keins gefunden
    public Fahrzeug suchen(String kundenname) {
        for (Fahrzeug f : fahrzeuge) {
            if (f.getKundenname().equalsIgnoreCase(kundenname)) {
                return f;
            }
        }
        return null;
    }

    // Gibt nur die reparierten Fahrzeuge aus
    public void printReparierte() {
        for (Fahrzeug f : fahrzeuge) {
            if (f.isRepariert()) {
                f.print();
            }
        }
    }
}
