package applications;

public class AppDetails {
    private String requirements;
    private int basePrice;
    private float pricePerUser = 0;
    private int numberOfDevelopers = 1;

    public AppDetails(String requirements, int basePrice) {
        this.requirements = requirements;
        this.basePrice = basePrice;
    }

    public AppDetails(String requirements, int basePrice, float pricePerUser, int numberOfDevelopers) {
        this.requirements = requirements;
        this.basePrice = basePrice;
        this.pricePerUser = pricePerUser;
        this.numberOfDevelopers = numberOfDevelopers;
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
