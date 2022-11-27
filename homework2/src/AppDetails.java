public class AppDetails {
    private String requirements;
    private int basePrice;
    private float pricePerUser = 0;
    private int numberOfUsers = 1;
    private int numberOfDevelopers = 1;

    public AppDetails(String requirements, int basePrice) {
        this.requirements = requirements;
        this.basePrice = basePrice;
    }

    public AppDetails(String requirements, int basePrice, float pricePerUser, int numberOfUsers, int numberOfDevelopers) {
        this.requirements = requirements;
        this.basePrice = basePrice;
        this.pricePerUser = pricePerUser;
        this.numberOfUsers = numberOfUsers;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public int getPrice() {
        return (int) (basePrice + numberOfUsers * pricePerUser);
    }

    /*
     * Getters and setters
     */
    public String getRequirements() {
        return this.requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
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
