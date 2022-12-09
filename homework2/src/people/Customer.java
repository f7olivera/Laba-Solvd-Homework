package people;

import interfaces.IFinance;

public final class Customer extends Person implements IFinance {
    private int budget;

    public Customer() {
        super();
    }

    public Customer(int id, int budget) {
        super(id);
        this.budget = budget;
    }

    public Customer(String firstName, String lastName, short age, int id, int budget) {
        super(firstName, lastName, age, id);
        this.budget = budget;
    }

    @Override
    public void earn(int amount) {
        budget -= amount;
    }

    @Override
    public void spend(int amount) {
        budget += amount;
    }

    /*
     * Getters and setters
     */
    public int getBudget() {
        return this.budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
