package people;
import java.util.HashSet;
import java.util.Objects;

public class Team {
    private HashSet<Developer> developers;
    private ProductOwner productOwner;
    private ScrumMaster scrumMaster;

    public Team(HashSet<Developer> developers) {
        this.developers = developers;
    }

    public Team(HashSet<Developer> developers, ProductOwner productOwner, ScrumMaster scrumMaster) {
        this.developers = developers;
        this.productOwner = productOwner;
        this.scrumMaster = scrumMaster;
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

    public HashSet<Developer> getDevelopers() {
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
