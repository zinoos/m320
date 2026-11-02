public class FullTimeEmployee extends Employee {
    private static final double BONUS_RATE = 0.10;

    private boolean hasBonus;

    public FullTimeEmployee(String name, int id, double salary, boolean hasBonus) {
        super(name, id, salary);
        this.hasBonus = hasBonus;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    public void setHasBonus(boolean hasBonus) {
        this.hasBonus = hasBonus;
    }

    @Override
    public double calculatePay() {
        return hasBonus() ? getSalary() * (1 + BONUS_RATE) : getSalary();
    }
}
