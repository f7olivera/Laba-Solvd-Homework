package com.solvd.itcompany.applications;

import com.solvd.itcompany.interfaces.IDeploy;
import com.solvd.itcompany.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Application implements IDeploy {
    private String name;
    private AppDetails appDetails;
    private String supportedEnvironments;
    private int numberOfUsers = 1;
    protected final static Logger LOGGER = LogManager.getLogger(Application.class);

    public Application(AppDetails appDetails) {
        this.appDetails = appDetails;
    }

    public Application(String name, String supportedEnvironments) {
        this.name = name;
        this.supportedEnvironments = supportedEnvironments;
    }

    public Application(String name, String supportedEnvironments, int numberOfUsers) {
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

    public String getSupportedEnvironments() {
        return this.supportedEnvironments;
    }

    void setSupportedEnvironments(String supportedEnvironments) {
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
