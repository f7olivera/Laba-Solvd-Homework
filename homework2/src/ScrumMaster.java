public class ScrumMaster extends Worker {
    public ScrumMaster(int employeeId) {
        super(employeeId);
        final int BASE_SALARY = 2500;
        this.setSalary(BASE_SALARY);
    }

    public ScrumMaster(int employeeId, int salary) {
        super(employeeId, salary);
    }
}
