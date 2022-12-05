package company;

import interfaces.IEmploy;
import people.Worker;

public abstract class Company implements IEmploy {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public abstract void hireWorker(Worker worker);

    public abstract void fireWorker(Worker worker);

    /*
     * Getters and setters
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
