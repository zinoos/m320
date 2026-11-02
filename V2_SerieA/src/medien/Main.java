package medien;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MedienVerwaltung verwaltung = new MedienVerwaltung();
        verwaltung.hinzufuegen(new Buch("Clean Code", 2008, "Robert C. Martin", 464));
        verwaltung.hinzufuegen(new DVD("Inception", 2010, "Christopher Nolan", 148));
        verwaltung.hinzufuegen(new CD("Abbey Road", 1969, "The Beatles", 17));
        verwaltung.ausleihen("Inception");

        boolean weiter = true;
        while (weiter) {
            System.out.println();
            System.out.println("===== MEDIEN-VERWALTUNG =====");
            System.out.println("1) Alle Medien anzeigen");
            System.out.println("2) Medium hinzufuegen");
            System.out.println("3) Medium suchen (Titel)");
            System.out.println("4) Ausgeliehene Medien anzeigen");
            System.out.println("5) Medium ausleihen");
            System.out.println("6) Medium zurueckgeben");
            System.out.println("7) Medium loeschen (nur nicht ausgeliehene)");
            System.out.println("0) Beenden");
            System.out.print("Auswahl: ");

            if (!scanner.hasNextLine()) {
                break;
            }
            switch (scanner.nextLine().trim()) {
                case "1" -> verwaltung.print();
                case "2" -> {
                    Medium neu = mediumErfassen();
                    if (neu != null) {
                        verwaltung.hinzufuegen(neu);
                        System.out.println("Medium hinzugefuegt.");
                    }
                }
                case "3" -> verwaltung.sucheUndPrint(eingabe("Titel: "));
                case "4" -> verwaltung.printAusgeliehene();
                case "5" -> System.out.println(verwaltung.ausleihen(eingabe("Titel: "))
                        ? "Medium ausgeliehen." : "Nicht gefunden oder bereits ausgeliehen.");
                case "6" -> System.out.println(verwaltung.zurueckgeben(eingabe("Titel: "))
                        ? "Medium zurueckgegeben." : "Nicht gefunden oder nicht ausgeliehen.");
                case "7" -> System.out.println(verwaltung.loeschen(eingabe("Titel: "))
                        ? "Medium geloescht." : "Nicht gefunden oder noch ausgeliehen - nicht geloescht.");
                case "0" -> weiter = false;
                default -> System.out.println("Ungueltige Auswahl.");
            }
        }
        System.out.println("Auf Wiedersehen!");
    }

    private static Medium mediumErfassen() {
        String typ = eingabe("Typ (1=Buch, 2=DVD, 3=CD): ");
        String titel = eingabe("Titel: ");
        int jahr = zahl("Erscheinungsjahr: ");
        switch (typ) {
            case "1":
                return new Buch(titel, jahr, eingabe("Autor: "), zahl("Seitenzahl: "));
            case "2":
                return new DVD(titel, jahr, eingabe("Regisseur: "), zahl("Dauer (min): "));
            case "3":
                return new CD(titel, jahr, eingabe("Interpret: "), zahl("Anzahl Tracks: "));
            default:
                System.out.println("Unbekannter Typ.");
                return null;
        }
    }

    private static String eingabe(String text) {
        System.out.print(text);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

    private static int zahl(String text) {
        while (true) {
            String s = eingabe(text);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                if (!scanner.hasNextLine()) {
                    return 0;
                }
                System.out.println("Bitte eine Zahl eingeben.");
            }
        }
    }
}
