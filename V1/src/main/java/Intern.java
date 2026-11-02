public class Intern extends Employee {
    private int maxHours;
    private int hoursWorked;

    public Intern(String name, int id, double fixedWage, int maxHours, int hoursWorked) {
        super(name, id, fixedWage);
        setMaxHours(maxHours);
        setHoursWorked(hoursWorked);
    }

    public int getMaxHours() {
        return maxHours;
    }

    public void setMaxHours(int maxHours) {
        if (maxHours < 0) {
            throw new IllegalArgumentException("maxHours darf nicht negativ sein.");
        }
        if (hoursWorked > maxHours) {
            throw new IllegalArgumentException("maxHours darf nicht kleiner als die geleisteten Stunden sein.");
        }
        this.maxHours = maxHours;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > maxHours) {
            throw new IllegalArgumentException(
                    "Die geleisteten Stunden müssen zwischen 0 und maxHours liegen.");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        return getSalary();
    }
}
