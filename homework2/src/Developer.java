public class Developer extends Worker {
    private String role;
    private final int BASE_SALARY = 1000;

    public Developer(int employeeId) {
        super(employeeId);
        this.setSalary(BASE_SALARY);
    }

    public Developer(int employeeId, String role) {
        super(employeeId);
        this.setSalary(BASE_SALARY);
        this.role = role;
    }

    public Developer(int employeeId, int salary, String role) {
        super(employeeId, salary);
        this.role = role;
    }

    /*
     * Getters and setters
     */
    public String getRole() {
        return this.role;
    }

    void setRole(String role) {
        this.role = role;
    }
}
