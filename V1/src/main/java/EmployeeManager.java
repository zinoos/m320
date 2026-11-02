import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class EmployeeManager {
    private final List<FullTimeEmployee> fullTimeEmployees = new ArrayList<>();
    private final List<PartTimeEmployee> partTimeEmployees = new ArrayList<>();
    private final List<Intern> interns = new ArrayList<>();

    public void addEmployee(FullTimeEmployee employee) {
        fullTimeEmployees.add(requireEmployee(employee));
    }

    public void addEmployee(PartTimeEmployee employee) {
        partTimeEmployees.add(requireEmployee(employee));
    }

    public void addEmployee(Intern employee) {
        interns.add(requireEmployee(employee));
    }

    public List<FullTimeEmployee> getFullTimeEmployees() {
        return Collections.unmodifiableList(fullTimeEmployees);
    }

    public List<PartTimeEmployee> getPartTimeEmployees() {
        return Collections.unmodifiableList(partTimeEmployees);
    }

    public List<Intern> getInterns() {
        return Collections.unmodifiableList(interns);
    }

    public List<Employee> getAllEmployees() {
        return Stream.of(fullTimeEmployees.stream(), partTimeEmployees.stream(), interns.stream())
                .flatMap(stream -> stream)
                .map(employee -> (Employee) employee)
                .toList();
    }

    public List<Employee> findByName(String searchText) {
        if (searchText == null || searchText.isBlank()) {
            return List.of();
        }
        String normalizedSearch = searchText.trim().toLowerCase(Locale.ROOT);
        return getAllEmployees().stream()
                .filter(employee -> employee.getName().toLowerCase(Locale.ROOT).contains(normalizedSearch))
                .toList();
    }

    private static <T extends Employee> T requireEmployee(T employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Ein Mitarbeiter darf nicht null sein.");
        }
        return employee;
    }
}
