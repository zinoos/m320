package garage;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private final List<Fahrzeug> fahrzeuge = new ArrayList<>();

    public void hinzufuegen(Fahrzeug fahrzeug) {
        fahrzeuge.add(fahrzeug);
    }

    public void print() {
        if (fahrzeuge.isEmpty()) {
            System.out.println("Keine Fahrzeuge in der Garage.");
            return;
        }
        for (Fahrzeug f : fahrzeuge) {
            System.out.println("----------------------------");
            f.print();
        }
        System.out.println("----------------------------");
    }

    public Fahrzeug sucheNachKunde(String kundenname) {
        for (Fahrzeug f : fahrzeuge) {
            if (f.getKundenname().equalsIgnoreCase(kundenname)) {
                return f;
            }
        }
        return null;
    }

    public void sucheUndPrint(String kundenname) {
        Fahrzeug f = sucheNachKunde(kundenname);
        if (f == null) {
            System.out.println("Kein Fahrzeug von '" + kundenname + "' gefunden.");
        } else {
            f.print();
        }
    }

    public List<Fahrzeug> sucheReparierte() {
        List<Fahrzeug> resultat = new ArrayList<>();
        for (Fahrzeug f : fahrzeuge) {
            if (f.isIstRepariert()) {
                resultat.add(f);
            }
        }
        return resultat;
    }

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
