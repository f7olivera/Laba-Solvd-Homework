package applications;

import interfaces.IDeploy;

public class Website extends Application {
    public final static String ENVIRONMENTS = "chrome,edge,safari,firefox,opera,ie";
    private String domain;

    public Website(String name, int numberOfUsers, String domain) {
        super(name, ENVIRONMENTS, numberOfUsers);
        this.domain = domain;
    }

    public Website(String name, String supportedEnvironments, int numberOfUsers, String domain) {
        super(name, supportedEnvironments, numberOfUsers);
        this.domain = domain;
    }

    @Override
    public void deploy() {

    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Domain: " + this.getDomain() + "\n" +
                "- Supported browsers: " + this.getSupportedEnvironments();
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
