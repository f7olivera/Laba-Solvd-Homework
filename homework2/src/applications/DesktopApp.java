package applications;

public class DesktopApp extends Application {
    private String platforms = "linux,mac,windows";

    public DesktopApp(String name) {
        super(name);
    }

    public DesktopApp(String name, String platforms) {
        super(name);
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
}
