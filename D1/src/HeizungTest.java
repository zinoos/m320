import java.util.Scanner;

public class HeizungTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Heizung wohnzimmer = new Heizung(20, 1);
        Heizung buero = new Heizung(18, 2, 15, 25);

        Heizung aktiv = wohnzimmer;

        String eingabe = "";

        System.out.println(aktiv.getTemperature());
        while (!eingabe.equals("q")) {
            System.out.println("\nAktiv: " + aktiv);
            System.out.println("[+] wärmer  [-] kälter  [1] Wohnzimmer wählen  [2] Büro wählen  [q] Beenden");
            System.out.print("Eingabe: ");
            eingabe = scanner.nextLine().trim();

            switch (eingabe) {
                case "+":
                    aktiv.warmer();
                    break;
                case "-":
                    aktiv.colder();
                    break;
                case "1":
                    aktiv = wohnzimmer;
                    break;
                case "2":
                    aktiv = buero;
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
}
