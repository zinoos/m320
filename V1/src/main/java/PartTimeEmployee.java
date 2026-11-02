public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String name, int id, double hourlyRate, double hoursWorked) {
        super(name, id, 0);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Der Stundenlohn darf nicht negativ sein.");
        }
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Die Arbeitszeit darf nicht negativ sein.");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}
