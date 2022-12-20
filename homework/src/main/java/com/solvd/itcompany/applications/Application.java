package com.solvd.itcompany.applications;

import com.solvd.enums.Environment;
import com.solvd.itcompany.interfaces.IDeploy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public abstract class Application implements IDeploy {
    private String name;
    private AppDetails appDetails;
    private Set<Environment> supportedEnvironments;
    private int numberOfUsers = 1;
    protected final static Logger LOGGER = LogManager.getLogger(Application.class);

    public Application(AppDetails appDetails) {
        this.appDetails = appDetails;
    }

    public Application(String name, Set<Environment> supportedEnvironments) {
        this.name = name;
        this.supportedEnvironments = supportedEnvironments;
    }

    public Application(String name, Set<Environment> supportedEnvironments, int numberOfUsers) {
        this.name = name;
        this.supportedEnvironments = supportedEnvironments;
        this.numberOfUsers = numberOfUsers;
    }

    public final int getAppPrice() {
        return appDetails.getBasePrice() + (int) (numberOfUsers * appDetails.getPricePerUser());
    }

    /*
     * Getters and setters
     */
    public String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Set<Environment> getSupportedEnvironments() {
        return this.supportedEnvironments;
    }

    void setSupportedEnvironments(Set<Environment> supportedEnvironments) {
        this.supportedEnvironments = supportedEnvironments;
    }

    public int getNumberOfUsers() {
        return this.numberOfUsers;
    }

    void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public AppDetails getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }
}
