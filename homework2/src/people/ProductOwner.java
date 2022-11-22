package people;

public class ProductOwner extends Worker {
    private final static int BASE_SALARY = 2500;
    public ProductOwner(int employeeId) {
        super(employeeId);
        this.setSalary(BASE_SALARY);
    }

    public ProductOwner(int employeeId, int salary) {
        super(employeeId, salary);
    }

    public static int getBaseSalary() {
        return BASE_SALARY;
    }
}
