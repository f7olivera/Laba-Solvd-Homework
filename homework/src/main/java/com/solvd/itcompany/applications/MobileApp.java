package com.solvd.itcompany.applications;

import com.solvd.itcompany.enums.Environment;

import java.util.EnumSet;
import java.util.Set;

import static com.solvd.itcompany.enums.Environment.ANDROID;
import static com.solvd.itcompany.enums.Environment.IOS;

public final class MobileApp extends Application {
    public MobileApp(AppDetails appDetails) {
        super(appDetails);
    }

    public MobileApp(String name) {
        // Consider all operating systems by default
        super(name, EnumSet.of(IOS, ANDROID));
    }

    public MobileApp(String name, Set<Environment> supportedEnvironments) {
        super(name, supportedEnvironments);
    }

    public MobileApp(String name, int numberOfUsers, Set<Environment> supportedEnvironments) {
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
                "- Operating Systems: " + Environment.toString(this.getSupportedEnvironments());
    }
}
