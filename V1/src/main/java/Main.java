import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = createDemoManager();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                switch (scanner.nextLine().trim()) {
                    case "1" -> printEmployees(manager);
                    case "2" -> searchEmployees(scanner, manager);
                    case "3" -> addIntern(scanner, manager);
                    case "4" -> addFullTimeEmployee(scanner, manager);
                    case "5" -> addPartTimeEmployee(scanner, manager);
                    case "0" -> running = false;
                    default -> System.out.println("Ungültige Auswahl.");
                }
            }
        }

        System.out.println("Programm beendet.");
    }

    private static EmployeeManager createDemoManager() {
        EmployeeManager manager = new EmployeeManager();
        manager.addEmployee(new FullTimeEmployee("Anna Keller", 1, 6500, true));
        manager.addEmployee(new PartTimeEmployee("Ben Meier", 2, 28.50, 42));
        manager.addEmployee(new Intern("Clara Weber", 3, 1200, 80, 36));
        return manager;
    }

    private static void printMenu() {
        System.out.println("\n--- Employee Manager ---");
        System.out.println("1 - Alle Mitarbeiter anzeigen");
        System.out.println("2 - Mitarbeiter nach Name suchen");
        System.out.println("3 - Praktikant hinzufügen");
        System.out.println("4 - Vollzeitmitarbeiter hinzufügen");
        System.out.println("5 - Teilzeitmitarbeiter hinzufügen");
        System.out.println("0 - Beenden");
        System.out.print("Auswahl: ");
    }

    private static void printEmployees(EmployeeManager manager) {
        if (manager.getAllEmployees().isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }
        manager.getAllEmployees().forEach(employee ->
                System.out.printf("%s | ID: %d | Lohn: %.2f%n",
                        employee.getTypeName(), employee.getId(), employee.calculatePay()));
    }

    private static void searchEmployees(Scanner scanner, EmployeeManager manager) {
        System.out.print("Name oder Teil des Namens: ");
        String name = scanner.nextLine();
        var results = manager.findByName(name);
        if (results.isEmpty()) {
            System.out.println("Kein Mitarbeiter gefunden.");
            return;
        }
        results.forEach(System.out::println);
    }

    private static void addIntern(Scanner scanner, EmployeeManager manager) {
        System.out.println("\nNeuer Praktikant");
        String name = readText(scanner, "Name: ");
        int id = readInt(scanner, "ID: ");
        double wage = readDouble(scanner, "Fixer Lohn: ");
        int maxHours = readInt(scanner, "Maximale Stunden: ");
        int hoursWorked = readInt(scanner, "Geleistete Stunden: ");
        try {
            manager.addEmployee(new Intern(name, id, wage, maxHours, hoursWorked));
            System.out.println("Praktikant hinzugefügt.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Fehler: " + exception.getMessage());
        }
    }

    private static void addFullTimeEmployee(Scanner scanner, EmployeeManager manager) {
        System.out.println("\nNeuer Vollzeitmitarbeiter");
        String name = readText(scanner, "Name: ");
        int id = readInt(scanner, "ID: ");
        double salary = readDouble(scanner, "Monatslohn: ");
        boolean hasBonus = readText(scanner, "Bonus? (j/n): ").equalsIgnoreCase("j");
        try {
            manager.addEmployee(new FullTimeEmployee(name, id, salary, hasBonus));
            System.out.println("Vollzeitmitarbeiter hinzugefügt.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Fehler: " + exception.getMessage());
        }
    }

    private static void addPartTimeEmployee(Scanner scanner, EmployeeManager manager) {
        System.out.println("\nNeuer Teilzeitmitarbeiter");
        String name = readText(scanner, "Name: ");
        int id = readInt(scanner, "ID: ");
        double hourlyRate = readDouble(scanner, "Stundenlohn: ");
        double hoursWorked = readDouble(scanner, "Geleistete Stunden: ");
        try {
            manager.addEmployee(new PartTimeEmployee(name, id, hourlyRate, hoursWorked));
            System.out.println("Teilzeitmitarbeiter hinzugefügt.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Fehler: " + exception.getMessage());
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readText(scanner, prompt));
            } catch (NumberFormatException exception) {
                System.out.println("Bitte eine ganze Zahl eingeben.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readText(scanner, prompt).replace(',', '.'));
            } catch (NumberFormatException exception) {
                System.out.println("Bitte eine Zahl eingeben.");
            }
        }
    }
}
