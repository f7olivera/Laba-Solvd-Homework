package people;

public class ScrumMaster extends Worker {
    private static final int BASE_SALARY = 2500;
    public ScrumMaster(int employeeId) {
        super(employeeId);
        this.setSalary(BASE_SALARY);
    }

    public ScrumMaster(int employeeId, int salary) {
        super(employeeId, salary);
    }

    public static int getBaseSalary() {
        return BASE_SALARY;
    }
}
