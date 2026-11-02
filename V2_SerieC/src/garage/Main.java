package garage;

import java.util.Scanner;

/** Einfaches User-Menu fuer die Garagen-App. */
public class Main {

    public static void main(String[] args) {
        Garage garage = new Garage();
        garage.hinzufuegen(new Auto("VW", "Meier", false, 5, "Benzin"));
        garage.hinzufuegen(new Auto("Tesla", "Huber", true, 4, "Elektro"));
        garage.hinzufuegen(new Motorrad("Ducati", "Keller", true, 1100, false));
        garage.hinzufuegen(new Motorrad("BMW", "Frei", false, 800, true));
        garage.hinzufuegen(new Lastwagen("Scania", "Transport AG", false, 18.5, 3));

        Scanner scanner = new Scanner(System.in);
        boolean weiter = true;

        while (weiter) {
            System.out.println();
            System.out.println("===== GARAGEN-APP =====");
            System.out.println("1) Alle Fahrzeuge anzeigen");
            System.out.println("2) Fahrzeug suchen (Kundenname)");
            System.out.println("3) Reparierte Fahrzeuge anzeigen");
            System.out.println("4) Fahrzeug als repariert markieren");
            System.out.println("0) Beenden");
            System.out.print("Auswahl: ");

            if (!scanner.hasNextLine()) {
                break;
            }
            String auswahl = scanner.nextLine().trim();

            switch (auswahl) {
                case "1" -> garage.print();
                case "2" -> {
                    System.out.print("Kundenname: ");
                    garage.sucheUndPrint(scanner.nextLine().trim());
                }
                case "3" -> garage.printReparierte();
                case "4" -> {
                    System.out.print("Kundenname: ");
                    String name = scanner.nextLine().trim();
                    if (garage.reparieren(name)) {
                        System.out.println("Fahrzeug von " + name + " ist jetzt repariert.");
                    } else {
                        System.out.println("Kein Fahrzeug von '" + name + "' gefunden.");
                    }
                }
                case "0" -> weiter = false;
                default -> System.out.println("Ungueltige Auswahl.");
            }
        }
        System.out.println("Auf Wiedersehen!");
    }
}
