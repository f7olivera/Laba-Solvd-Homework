package people;

public class Developer extends Worker {
    private String role;

    public Developer(int employeeId) {
        super(employeeId);
    }

    public Developer(int employeeId, int salary) {
        super(employeeId, salary);
    }

    public Developer(int employeeId, String role) {
        super(employeeId);
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
