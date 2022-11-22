public class ProductOwner extends Worker {
    public ProductOwner(int employeeId) {
        super(employeeId);
        final int BASE_SALARY = 2500;
        this.setSalary(BASE_SALARY);
    }

    public ProductOwner(int employeeId, int salary) {
        super(employeeId, salary);
    }
}
