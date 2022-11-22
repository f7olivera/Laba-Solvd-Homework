package applications;

public class DesktopApp extends Application {
    private String platforms = "linux,mac,windows";
    private static final int BASE_PRICE = 9000;
    private static final String REQUIREMENTS = "name and platforms to be supported";

    public DesktopApp() {
        super("", BASE_PRICE);
    }

    public DesktopApp(String name) {
        super(name, BASE_PRICE);
    }

    public DesktopApp(String name, int numberOfUsers) {
        super(name, numberOfUsers, BASE_PRICE);
    }

    public DesktopApp(String name, int numberOfUsers, String platforms) {
        super(name, numberOfUsers, BASE_PRICE);
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Platforms: " + this.getPlatforms();
    }

    /*
     * Getters and setters
     */
    public String getPlatforms() {
        return this.platforms;
    }

    void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public static String getRequirements() {
        return REQUIREMENTS;
    }
}
