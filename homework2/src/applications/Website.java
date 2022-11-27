package applications;

public class Website extends Application {
    private String domain;

    public Website(String name, String domain) {
        super(name);
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Domain: " + this.getDomain() + "\n";
    }

    /*
     * Getters and setters
     */
    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
