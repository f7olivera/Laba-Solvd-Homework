package com.solvd.itcompany.applications;

import java.util.EnumSet;
import java.util.Set;

import static com.solvd.itcompany.applications.Environment.*;

public final class DesktopApp extends Application {
    public DesktopApp(AppDetails appDetails) {
        super(appDetails);
    }

    public DesktopApp(String name) {
        super(name, EnumSet.of(LINUX, MACOS, WINDOWS));
    }

    public DesktopApp(String name, Set<Environment> supportedEnvironments) {
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
                "- Platforms: " + Environment.toString(this.getSupportedEnvironments());
    }
}
