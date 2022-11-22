public class MobileApp extends Application {
    // Consider all operating systems by default
    private String operatingSystems = "iOS,android";
    private static final int BASE_PRICE = 7000;
    private static final int NUMBER_OF_DEVELOPERS = 3;

    public MobileApp() {
        super("", DEFAULT_NUMBER_OF_USERS, BASE_PRICE, NUMBER_OF_DEVELOPERS);
    }

    public MobileApp(String name) {
        super(name, DEFAULT_NUMBER_OF_USERS, BASE_PRICE, NUMBER_OF_DEVELOPERS);
    }

    public MobileApp(String name, int numberOfUsers, String operatingSystems) {
        super(name, numberOfUsers, BASE_PRICE, NUMBER_OF_DEVELOPERS);
        this.operatingSystems = operatingSystems;
    }

    /*
     * Getters and setters
     */
    public String setOperatingSystems() {
        return this.operatingSystems;
    }

    void setOperatingSystems(String operatingSystems) {
        this.operatingSystems = operatingSystems;
    }
}
