package applications;

abstract public class Application {
    private String name;
    private int numberOfUsers = 0;
    private int basePrice = 5000;
    private float pricePerUser = 0;
    private int numberOfDevelopers = 1;

    public Application() {
        this.name = "TBD";
    }

    public Application(String name) {
        this.name = name;
    }

    public Application(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public Application(String name, int numberOfUsers, int basePrice) {
        this.name = name;
        this.numberOfUsers = numberOfUsers;
        this.basePrice = basePrice;
    }

    public Application(String name, int basePrice, int numberOfDevelopers, float pricePerUser) {
        this.name = name;
        this.basePrice = basePrice;
        this.pricePerUser = pricePerUser;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public Application(String name, int numberOfUsers, int basePrice, int numberOfDevelopers, float pricePerUser) {
        this.name = name;
        this.numberOfUsers = numberOfUsers;
        this.basePrice = basePrice;
        this.pricePerUser = pricePerUser;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public int getPrice() {
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

    public float getPricePerUser() {
        return this.pricePerUser;
    }

    void setPricePerUser(float pricePerUser) {
        this.pricePerUser = pricePerUser;
    }

    public int getNumberOfDevelopers() {
        return this.numberOfDevelopers;
    }

    void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }
}
