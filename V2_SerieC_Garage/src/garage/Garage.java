package garage;

import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltet die Liste der Fahrzeuge.
 * Die Liste ist vom Typ Fahrzeug (Superklasse) – beim Aufruf von print()
 * wird zur Laufzeit die ueberschriebene Methode der jeweiligen Subklasse
 * ausgefuehrt (Polymorphismus).
 */
public class Garage {

    private final List<Fahrzeug> fahrzeuge = new ArrayList<>();

    public void hinzufuegen(Fahrzeug fahrzeug) {
        fahrzeuge.add(fahrzeug);
    }

    /** Gibt jedes Fahrzeug via seine eigene print-Methode aus. */
    public void print() {
        if (fahrzeuge.isEmpty()) {
            System.out.println("Keine Fahrzeuge in der Garage.");
            return;
        }
        for (Fahrzeug f : fahrzeuge) {
            System.out.println("----------------------------");
            f.print(); // dynamisches Binden
        }
        System.out.println("----------------------------");
    }

    /** Sucht ein bestimmtes Fahrzeug anhand des Kundennamens. */
    public Fahrzeug sucheNachKunde(String kundenname) {
        for (Fahrzeug f : fahrzeuge) {
            if (f.getKundenname().equalsIgnoreCase(kundenname)) {
                return f;
            }
        }
        return null;
    }

    /** Sucht ein bestimmtes Fahrzeug und gibt es aus. */
    public void sucheUndPrint(String kundenname) {
        Fahrzeug f = sucheNachKunde(kundenname);
        if (f == null) {
            System.out.println("Kein Fahrzeug von '" + kundenname + "' gefunden.");
        } else {
            f.print();
        }
    }

    /** Liefert alle reparierten Fahrzeuge. */
    public List<Fahrzeug> sucheReparierte() {
        List<Fahrzeug> resultat = new ArrayList<>();
        for (Fahrzeug f : fahrzeuge) {
            if (f.isIstRepariert()) {
                resultat.add(f);
            }
        }
        return resultat;
    }

    /** Gibt alle reparierten Fahrzeuge aus. */
    public void printReparierte() {
        List<Fahrzeug> reparierte = sucheReparierte();
        if (reparierte.isEmpty()) {
            System.out.println("Keine reparierten Fahrzeuge vorhanden.");
            return;
        }
        for (Fahrzeug f : reparierte) {
            System.out.println("----------------------------");
            f.print();
        }
        System.out.println("----------------------------");
    }

    /** Markiert das Fahrzeug eines Kunden als repariert. */
    public boolean reparieren(String kundenname) {
        Fahrzeug f = sucheNachKunde(kundenname);
        if (f == null) {
            return false;
        }
        f.setIstRepariert(true);
        return true;
    }

    public List<Fahrzeug> getFahrzeuge() {
        return fahrzeuge;
    }
}
