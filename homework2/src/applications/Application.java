package applications;

import interfaces.IDeploy;

public abstract class Application implements IDeploy {
    private String name;
    private String supportedEnvironments;
    private int numberOfUsers = 1;

    public Application(String name, String supportedEnvironments) {
        this.name = name;
        this.supportedEnvironments = supportedEnvironments;
    }

    public Application(String name, String supportedEnvironments, int numberOfUsers) {
        this.name = name;
        this.supportedEnvironments = supportedEnvironments;
        this.numberOfUsers = numberOfUsers;
    }

    public abstract void deploy();

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
}
