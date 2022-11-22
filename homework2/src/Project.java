public class Project {
    private Application application;
    private Team team;
    private Customer customer;

    public Project(Application application, Customer customer) {
        this.application = application;
        this.customer = customer;
    }

    public Project(Application application, Customer customer, Team team) {
        this.application = application;
        this.customer = customer;
    }

    /*
     * Getters and setters
     */
    public Application getApplication() {
        return this.application;
    }

    void setApplication(Application application) {
        this.application = application;
    }

    public Team getTeam() {
        return this.team;
    }

    void setTeam(Team team) {
        this.team = team;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
