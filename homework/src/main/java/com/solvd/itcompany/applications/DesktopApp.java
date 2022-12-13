package com.solvd.itcompany.applications;

public final class DesktopApp extends Application {
    public final static String ENVIRONMENTS = "linux,mac,windows";

    public DesktopApp(AppDetails appDetails) {
        super(appDetails);
    }

    public DesktopApp(String name) {
        super(name, ENVIRONMENTS);
    }

    public DesktopApp(String name, String supportedEnvironments) {
        super(name, supportedEnvironments);
    }

    @Override
    public void deploy() {
        LOGGER.info("Deploying DesktopApp\n" + this);
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Platforms: " + this.getSupportedEnvironments();
    }
}