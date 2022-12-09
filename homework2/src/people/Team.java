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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        if (this.hashCode() != obj.hashCode()) return false;

        final Team other = (Team) obj;

        return developers.equals(other.developers) &&
                productOwner.equals(other.productOwner) &&
                scrumMaster.equals(other.scrumMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developers, productOwner, scrumMaster);
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
