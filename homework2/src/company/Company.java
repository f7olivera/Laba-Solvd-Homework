package company;

import people.Worker;

import java.util.HashSet;

public abstract class Company {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public abstract void hireWorker(Worker worker);

    public abstract void fireWorker(Worker worker);

    public abstract HashSet<Worker> getWorkers();

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
