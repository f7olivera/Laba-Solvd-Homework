abstract public class Application {
    protected static final int DEFAULT_NUMBER_OF_USERS = 1;
    private String name;
    private int numberOfUsers = DEFAULT_NUMBER_OF_USERS;
    private int basePrice = 5000;
    private int numberOfDevelopers = 1;

    public Application() {
        this.name = "TBD";
    }

    public Application(String name) {
        this.name = name;
    }

    public Application(String name, int numberOfUsers, int basePrice) {
        this.name = name;
        this.numberOfUsers = numberOfUsers;
        this.basePrice = basePrice;
    }

    public Application(String name, int numberOfUsers, int basePrice, int numberOfDevelopers) {
        this.name = name;
        this.numberOfUsers = numberOfUsers;
        this.basePrice = basePrice;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public int getPrice(float pricePerUser) {
        return (int) (getBasePrice() + getNumberOfUsers() * pricePerUser);
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

    public int getBasePrice() {
        return this.basePrice;
    }

    void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getNumberOfDevelopers() {
        return this.numberOfDevelopers;
    }

    void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }
}
