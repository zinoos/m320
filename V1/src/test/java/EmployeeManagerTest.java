public class EmployeeManagerTest {
    public static void main(String[] args) {
        testPayCalculations();
        testInternHourLimit();
        testManagerListsAndSearch();
        System.out.println("Alle Tests erfolgreich.");
    }

    private static void testPayCalculations() {
        Employee fullTime = new FullTimeEmployee("Anna", 1, 5000, true);
        assertEquals(5500, fullTime.calculatePay(), "Bonusberechnung");

        Employee partTime = new PartTimeEmployee("Ben", 2, 25, 12);
        assertEquals(300, partTime.calculatePay(), "Teilzeitberechnung");

        Employee intern = new Intern("Clara", 3, 1000, 80, 40);
        assertEquals(1000, intern.calculatePay(), "Fixer Praktikantenlohn");
    }

    private static void testInternHourLimit() {
        Intern intern = new Intern("Clara", 3, 1000, 80, 40);
        boolean rejected = false;
        try {
            intern.setHoursWorked(81);
        } catch (IllegalArgumentException exception) {
            rejected = true;
        }
        assertTrue(rejected, "Überschreitung von maxHours muss abgelehnt werden");
    }

    private static void testManagerListsAndSearch() {
        EmployeeManager manager = new EmployeeManager();
        manager.addEmployee(new FullTimeEmployee("Anna Keller", 1, 5000, false));
        manager.addEmployee(new PartTimeEmployee("Ben Meier", 2, 25, 12));
        manager.addEmployee(new Intern("Clara Weber", 3, 1000, 80, 40));

        assertTrue(manager.getFullTimeEmployees().size() == 1, "Vollzeitliste");
        assertTrue(manager.getPartTimeEmployees().size() == 1, "Teilzeitliste");
        assertTrue(manager.getInterns().size() == 1, "Praktikantenliste");
        assertTrue(manager.findByName("keller").size() == 1, "Namenssuche ohne Beachtung der Gross-/Kleinschreibung");
        assertTrue(manager.getAllEmployees().size() == 3, "Gesamtliste");
    }

    private static void assertEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.000001) {
            throw new AssertionError(message + ": erwartet " + expected + ", erhalten " + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
