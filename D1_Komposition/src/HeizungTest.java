import java.util.Scanner;

public class HeizungTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Haus haus = new Haus("Musterstrasse 1, Zürich");

        Raum wohnzimmer = haus.createRaum("Wohnzimmer", 20, 1);
        Raum kueche = haus.createRaum("Küche", 18, 2, 15, 25);
        Raum keller = haus.createRaum("Keller", 12, 1, 8, 18);

        zeigeHaus(haus);

        Raum aktiv = wohnzimmer;
        String eingabe = "";

        while (!eingabe.equals("q")) {
            System.out.println("\nAktiver Raum: " + aktiv);
            System.out.println("[+] wärmer  [-] kälter");
            System.out.println("[1] Wohnzimmer  [2] Küche  [3] Keller");
            System.out.println("[q] Beenden");
            System.out.print("Eingabe: ");
            eingabe = scanner.nextLine().trim();

            switch (eingabe) {
                case "+":
                    aktiv.getHeizung().warmer();
                    break;
                case "-":
                    aktiv.getHeizung().colder();
                    break;
                case "1":
                    aktiv = wohnzimmer;
                    break;
                case "2":
                    aktiv = kueche;
                    break;
                case "3":
                    aktiv = keller;
                    break;
                case "q":
                    System.out.println("Programm beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }

        scanner.close();
    }

    private static void zeigeHaus(Haus haus) {
        System.out.println("\n=== " + haus + " ===");
        for (Raum r : haus.getRaeume()) {
            System.out.println("  " + r);
        }
    }
}