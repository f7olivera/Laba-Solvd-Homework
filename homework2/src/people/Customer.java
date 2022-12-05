package people;

import interfaces.IFinance;

public class Customer extends Person implements IFinance {
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
    public void earn(int ammount) {
        budget -= ammount;
    }

    @Override
    public void spend(int ammount) {
        budget += ammount;
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
