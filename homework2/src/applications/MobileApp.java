package applications;

public class MobileApp extends Application {
    // Consider all operating systems by default
    private String operatingSystems = "iOS,android";

    public MobileApp(String name, String operatingSystems) {
        super(name);
        this.operatingSystems = operatingSystems;
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Operating Systems: " + this.getOperatingSystems() + "\n";
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
}
