package com.solvd.itcompany.applications;

public final class MobileApp extends Application {
    public final static String ENVIRONMENTS = "iOS,android";

    public MobileApp(AppDetails appDetails) {
        super(appDetails);
    }

    public MobileApp(String name) {
        // Consider all operating systems by default
        super(name, ENVIRONMENTS);
    }

    public MobileApp(String name, String supportedEnvironments) {
        super(name, supportedEnvironments);
    }

    public MobileApp(String name, int numberOfUsers, String supportedEnvironments) {
        super(name, supportedEnvironments, numberOfUsers);
    }

    @Override
    public void deploy() {
        LOGGER.info("Deploying MobileApp\n" + this);
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Operating Systems: " + this.getSupportedEnvironments() + "\n";
    }
}
