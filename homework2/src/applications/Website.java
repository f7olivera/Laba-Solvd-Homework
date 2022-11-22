package applications;

public class Website extends Application {
    private String domain;
    private static final int BASE_PRICE = 10000;
    private static final int NUMBER_OF_DEVELOPERS = 5;
    private static final float PRICE_PER_USER = 1.25F;
    private static final String REQUIREMENTS = "name, domain and expected number of users";

    public Website() {
        super("", BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
    }

    public Website(String name) {
        super(name, BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
    }

    public Website(String name, int numberOfUsers) {
        super(name, numberOfUsers, BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
    }

    public Website(String name, int numberOfUsers, String domain, int numberOfDevelopers) {
        super(name, numberOfUsers, BASE_PRICE, numberOfDevelopers, PRICE_PER_USER);
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Domain: " + this.getDomain() + "\n" +
                "- Number of users: " + this.getNumberOfUsers();
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

    public static String getRequirements() {
        return REQUIREMENTS;
    }
}
