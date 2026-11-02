public abstract class Employee {
    private String name;
    private int id;
    private double salary;

    protected Employee(String name, int id, double salary) {
        setName(name);
        setId(id);
        setSalary(salary);
    }

    public abstract double calculatePay();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Der Name darf nicht leer sein.");
        }
        this.name = name.trim();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Die ID muss positiv sein.");
        }
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Der Lohn darf nicht negativ sein.");
        }
        this.salary = salary;
    }

    public String getTypeName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "%s{id=%d, name='%s', pay=%.2f}"
                .formatted(getTypeName(), id, name, calculatePay());
    }
}
