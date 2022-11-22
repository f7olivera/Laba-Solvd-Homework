public class Worker extends Person {
    private int employeeId;
    private int salary;

    public Worker(int employeeId) {
        super();
        this.employeeId = employeeId;
    }

    public Worker(int employeeId, int personId) {
        super(personId);
        this.employeeId = employeeId;
    }

    /*
     * Getters and setters
     */
    public int getEmployeeId() {
        return this.employeeId;
    }

    void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSalary() {
        return this.salary;
    }

    void setSalary(int salary) {
        this.salary = salary;
    }
}
