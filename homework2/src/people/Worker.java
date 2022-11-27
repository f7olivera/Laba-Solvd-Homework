package people;

public class Worker extends Person {
    private int employeeId;
    private int salary;

    public Worker(int employeeId) {
        super();
        this.employeeId = employeeId;
    }

    public Worker(int employeeId, int salary) {
        super();
        this.employeeId = employeeId;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        if(this.hashCode() != obj.hashCode()) return false;

        return employeeId == ((Worker) obj).getEmployeeId();
    }

    @Override
    public int hashCode() {
        return employeeId;
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
