package applications;

public class MobileApp extends Application {
    // Consider all operating systems by default
    private String operatingSystems = "iOS,android";
    private static final int BASE_PRICE = 7000;
    private static final int NUMBER_OF_DEVELOPERS = 3;
    private static final float PRICE_PER_USER = 1.25F;
    private static final String REQUIREMENTS = "name, operating systems to be supported and expected number of users";

    public MobileApp() {
        super("", BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
    }

    public MobileApp(String name) {
        super(name, BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
    }

    public MobileApp(String name, String operatingSystems) {
        super(name, BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
        this.operatingSystems = operatingSystems;
    }

    public MobileApp(String name, int numberOfUsers, String operatingSystems) {
        super(name, numberOfUsers, BASE_PRICE, NUMBER_OF_DEVELOPERS, PRICE_PER_USER);
        this.operatingSystems = operatingSystems;
    }

    public MobileApp(String name, int numberOfUsers, String operatingSystems, int numberOfDevelopers) {
        super(name, numberOfUsers, BASE_PRICE, numberOfDevelopers, PRICE_PER_USER);
        this.operatingSystems = operatingSystems;
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Operating Systems: " + this.getOperatingSystems() + "\n" +
                "- Number of users: " + this.getNumberOfUsers();
    }

    /*
     * Getters and setters
     */
    public String getOperatingSystems() {
        return this.operatingSystems;
    }

    void setOperatingSystems(String operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public static String getRequirements() {
        return REQUIREMENTS;
    }
}
