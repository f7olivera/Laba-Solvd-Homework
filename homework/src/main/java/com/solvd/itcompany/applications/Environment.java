package com.solvd.itcompany.applications;

import java.util.Set;

public enum Environment {
    // Desktop environments
    LINUX(DesktopApp.class),
    MACOS(DesktopApp.class),
    WINDOWS(DesktopApp.class),

    // Mobile environments
    IOS(MobileApp.class),
    ANDROID(MobileApp.class),

    // Browser environments
    CHROME(Website.class),
    EDGE(Website.class),
    SAFARI(Website.class),
    FIREFOX(Website.class),
    OPERA(Website.class),
    INTERNET_EXPLORER(Website.class);

    private Class<? extends Application> type;
    private String minimumSupportedVersion;

    Environment(Class<? extends Application> type) {
        this.type = type;
    }

    public static String toString(Set<Environment> environments) {
        return environments.toString().replaceAll("^.|.$", "");
    }


    public Class<? extends Application> getType() {
        return type;
    }

    public void setType(Class<? extends Application> type) {
        this.type = type;
    }

    public String getMinimumSupportedVersion() {
        return minimumSupportedVersion;
    }

    public void setMinimumSupportedVersion(String version) {
        this.minimumSupportedVersion = version;
    }
}
