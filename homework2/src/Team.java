import java.util.ArrayList;

public class Team {
    private ProductOwner productOwner;
    private ScrumMaster scrumMaster;
    private ArrayList<Developer> developers;

    public Team(ArrayList<Developer> developers) {
        this.developers = developers;
    }

    public Team(ArrayList<Developer> developers, ProductOwner productOwner, ScrumMaster scrumMaster) {
        this.productOwner = productOwner;
    }

    /*
     * Getters and setters
     */
    public void addDeveloper(Developer developer) {
        this.developers.add(developer);
    }

    public void removeDeveloper(Developer developer) {
        this.developers.remove(developer);
    }

    public ArrayList<Developer> getDevelopers() {
        return this.developers;
    }

    public ProductOwner getProductOwner() {
        return this.productOwner;
    }

    void setProductOwner(ProductOwner productOwner) {
        this.productOwner = productOwner;
    }

    public ScrumMaster getScrumMaster() {
        return this.scrumMaster;
    }

    void setScrumMaster(ScrumMaster scrumMaster) {
        this.scrumMaster = scrumMaster;
    }
}
