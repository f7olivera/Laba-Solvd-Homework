public class Website extends Application {
    private String domain;
    private static final int BASE_PRICE = 10000;
    private static final int NUMBER_OF_DEVELOPERS = 5;

    public Website() {
        super("", DEFAULT_NUMBER_OF_USERS, BASE_PRICE, NUMBER_OF_DEVELOPERS);
    }

    public Website(String name) {
        super(name, DEFAULT_NUMBER_OF_USERS, BASE_PRICE, NUMBER_OF_DEVELOPERS);
    }

    public Website(String name, int numberOfUsers) {
        super(name, numberOfUsers, BASE_PRICE, NUMBER_OF_DEVELOPERS);
    }

    public Website(String name, int numberOfUsers, String domain) {
        super(name, numberOfUsers, BASE_PRICE, NUMBER_OF_DEVELOPERS);
        this.domain = domain;
    }

    /*
     * Getters and setters
     */
    public String getPlatforms() {
        return this.domain;
    }

    void setPlatforms(String domain) {
        this.domain = domain;
    }
}
