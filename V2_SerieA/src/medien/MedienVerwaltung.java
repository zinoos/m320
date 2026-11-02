package medien;

import java.util.ArrayList;
import java.util.List;

/** Verwaltet die Liste der Medien: hinzufuegen, loeschen, ausgeben, suchen. */
public class MedienVerwaltung {

    private final List<Medium> medien = new ArrayList<>();

    public void hinzufuegen(Medium medium) {
        medien.add(medium);
    }

    /**
     * Loescht ein Medium anhand des Titels.
     * Ausgeliehene Medien duerfen nicht geloescht werden.
     * @return true, wenn das Medium geloescht wurde
     */
    public boolean loeschen(String titel) {
        Medium m = suche(titel);
        if (m == null || m.isIstAusgeliehen()) {
            return false;
        }
        return medien.remove(m);
    }

    /** Gibt jedes Medium via seine eigene print-Methode aus. */
    public void print() {
        printListe(medien);
    }

    /** Sucht ein bestimmtes Medium anhand des Titels. */
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

    /** Liefert alle ausgeliehenen Medien. */
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
            m.print(); // dynamisches Binden: print() der Subklasse
        }
        System.out.println("----------------------------");
    }

    public List<Medium> getMedien() {
        return medien;
    }
}
