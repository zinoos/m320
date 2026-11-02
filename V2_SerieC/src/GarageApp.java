import java.util.Scanner;

// Startklasse mit einfachem User-Menu
public class GarageApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Testdaten: verschiedene Subklassen in derselben Liste
        Garage garage = new Garage();
        garage.hinzufuegen(new Auto("VW", "Meier", true, 5, "Benzin"));
        garage.hinzufuegen(new Motorrad("Ducati", "Keller", false, 950, false));
        garage.hinzufuegen(new Lastwagen("Scania", "Huber", true, 18.5, 3));
        garage.hinzufuegen(new Auto("Tesla", "Weber", false, 4, "Elektro"));

        // Menu laeuft, bis der User q eingibt
        String eingabe = "";
        while (!eingabe.equals("q")) {
            System.out.println("\n--- Garage ---");
            System.out.println("1 = Alle Fahrzeuge anzeigen");
            System.out.println("2 = Fahrzeug suchen (Kundenname)");
            System.out.println("3 = Reparierte Fahrzeuge anzeigen");
            System.out.println("q = Beenden");
            System.out.print("Auswahl: ");
            eingabe = scanner.nextLine().trim();

            // Auswahl auswerten
            switch (eingabe) {
                case "1":
                    garage.print();
                    break;
                case "2":
                    System.out.print("Kundenname: ");
                    Fahrzeug gefunden = garage.suchen(scanner.nextLine().trim());
                    if (gefunden != null) {
                        gefunden.print();
                    } else {
                        System.out.println("Kein Fahrzeug gefunden.");
                    }
                    break;
                case "3":
                    garage.printReparierte();
                    break;
                case "q":
                    System.out.println("Tschuess!");
                    break;
                default:
                    System.out.println("Ungueltige Eingabe.");
            }
        }
    }
}
