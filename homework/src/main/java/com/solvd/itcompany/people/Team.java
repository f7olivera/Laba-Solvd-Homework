package com.solvd.itcompany.people;

import com.solvd.linkedlist.LinkedList;

import java.util.Objects;

public class Team {
    private LinkedList<Developer> developers;
    private ProductOwner productOwner;
    private ScrumMaster scrumMaster;

    public Team(LinkedList<Developer> developers) {
        this.developers = developers;
    }

    public Team(LinkedList<Developer> developers, ProductOwner productOwner, ScrumMaster scrumMaster) {
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
    void addWorker(Worker worker) {
        if (worker.getClass() == Developer.class) {
            developers.add((Developer) worker);
        } else if (worker.getClass() == ProductOwner.class) {
            productOwner = (ProductOwner) worker;
        } else if (worker.getClass() == ScrumMaster.class) {
            scrumMaster = (ScrumMaster) worker;
        }
    }

    public void removeWorker(Worker worker) {
        if (worker.getClass() == Developer.class) {
            developers.remove((Developer) worker);
        } else if (worker.getClass() == ProductOwner.class) {
            productOwner = worker == productOwner ? null : (ProductOwner) worker;
        } else if (worker.getClass() == ScrumMaster.class) {
            scrumMaster = worker == scrumMaster ? null : (ScrumMaster) worker;
        }
    }

    public LinkedList<Developer> getDevelopers() {
        return this.developers;
    }

    public ProductOwner getProductOwner() {
        return this.productOwner;
    }

    public ScrumMaster getScrumMaster() {
        return this.scrumMaster;
    }
}
