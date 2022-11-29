package applications;

abstract public class Application {
    private String name;
    private int numberOfUsers = 1;

    public Application(String name) {
        this.name = name;
    }

    public Application(String name, int numberOfUsers) {
        this.name = name;
        this.numberOfUsers = numberOfUsers;
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

    public int getNumberOfUsers() {
        return this.numberOfUsers;
    }

    void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }
}
