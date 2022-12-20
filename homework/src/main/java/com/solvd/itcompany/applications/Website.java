package com.solvd.itcompany.applications;

import java.util.EnumSet;
import java.util.Set;

import static com.solvd.itcompany.applications.Environment.*;

public final class Website extends Application {
    private String domain;

    public Website(AppDetails appDetails) {
        super(appDetails);
    }

    public Website(String name, int numberOfUsers, String domain) {
        super(name, EnumSet.of(CHROME, EDGE, SAFARI, FIREFOX, OPERA, INTERNET_EXPLORER), numberOfUsers);
        this.domain = domain;
    }

    public Website(String name, Set<Environment> supportedEnvironments, int numberOfUsers, String domain) {
        super(name, supportedEnvironments, numberOfUsers);
        this.domain = domain;
    }

    @Override
    public void deploy() {
        LOGGER.info("Deploying Website\n" + this);
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Domain: " + this.getDomain() + "\n" +
                "- Supported browsers: " + Environment.toString(this.getSupportedEnvironments());
    }

    /*
     * Getters and setters
     */
    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
