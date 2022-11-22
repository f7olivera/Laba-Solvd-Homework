public class DesktopApp extends Application {
    private String platforms = "linux,mac,windows";
    private static final int BASE_PRICE = 9000;

    public DesktopApp() {
        super("", DEFAULT_NUMBER_OF_USERS, BASE_PRICE);
    }

    public DesktopApp(String name) {
        super(name, DEFAULT_NUMBER_OF_USERS, BASE_PRICE);
    }

    public DesktopApp(String name, int numberOfUsers) {
        super(name, numberOfUsers, BASE_PRICE);
    }

    public DesktopApp(String name, int numberOfUsers, String platforms) {
        super(name, numberOfUsers, BASE_PRICE);
        this.platforms = platforms;
    }

    /*
     * Getters and setters
     */
    public String setPlatforms() {
        return this.platforms;
    }

    void setPlatforms(String platforms) {
        this.platforms = platforms;
    }
}
