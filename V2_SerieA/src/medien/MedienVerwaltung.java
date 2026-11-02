package medien;

import java.util.ArrayList;
import java.util.List;

public class MedienVerwaltung {

    private final List<Medium> medien = new ArrayList<>();

    public void hinzufuegen(Medium medium) {
        medien.add(medium);
    }

    public boolean loeschen(String titel) {
        Medium m = suche(titel);
        if (m == null || m.isIstAusgeliehen()) {
            return false;
        }
        return medien.remove(m);
    }

    public void print() {
        printListe(medien);
    }

    public Medium suche(String titel) {
        for (Medium m : medien) {
            if (m.getTitel().equalsIgnoreCase(titel)) {
                return m;
            }
        }
        return null;
    }

    public void sucheUndPrint(String titel) {
        Medium m = suche(titel);
        if (m == null) {
            System.out.println("Kein Medium mit Titel '" + titel + "' gefunden.");
        } else {
            m.print();
        }
    }

    public List<Medium> sucheAusgeliehene() {
        List<Medium> resultat = new ArrayList<>();
        for (Medium m : medien) {
            if (m.isIstAusgeliehen()) {
                resultat.add(m);
            }
        }
        return resultat;
    }

    public void printAusgeliehene() {
        printListe(sucheAusgeliehene());
    }

    public boolean ausleihen(String titel) {
        Medium m = suche(titel);
        if (m == null || m.isIstAusgeliehen()) {
            return false;
        }
        m.setIstAusgeliehen(true);
        return true;
    }

    public boolean zurueckgeben(String titel) {
        Medium m = suche(titel);
        if (m == null || !m.isIstAusgeliehen()) {
            return false;
        }
        m.setIstAusgeliehen(false);
        return true;
    }

    private void printListe(List<Medium> liste) {
        if (liste.isEmpty()) {
            System.out.println("Keine Medien gefunden.");
            return;
        }
        for (Medium m : liste) {
            System.out.println("----------------------------");
            m.print();
        }
        System.out.println("----------------------------");
    }

    public List<Medium> getMedien() {
        return medien;
    }
}
