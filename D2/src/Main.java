import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Flug flug = new Flug();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Flugverwaltung ---");
            System.out.println("1) Passagier buchen");
            System.out.println("2) Passagierliste anzeigen");
            System.out.println("3) Passagier entfernen");
            System.out.println("4) Beenden");
            System.out.print("Auswahl: ");

            String auswahl = scanner.nextLine().trim();

            switch (auswahl) {
                case "1":
                    System.out.print("Name: ");
                    String neuerName = scanner.nextLine();
                    flug.passagierHinzufuegen(new Passagier(neuerName));
                    System.out.println(neuerName + " wurde gebucht.");
                    break;
                case "2":
                    flug.passagierListeAusgeben();
                    break;
                case "3":
                    System.out.print("Name des zu entfernenden Passagiers: ");
                    String zuEntfernen = scanner.nextLine();
                    if (flug.passagierEntfernen(zuEntfernen)) {
                        System.out.println(zuEntfernen + " wurde entfernt.");
                    } else {
                        System.out.println("Kein Passagier mit dem Namen \"" + zuEntfernen + "\" gefunden.");
                    }
                    break;
                case "4":
                    System.out.println("Programm beendet.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ungültige Auswahl.");
            }
        }
    }
}
