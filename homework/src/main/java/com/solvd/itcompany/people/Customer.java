package com.solvd.itcompany.people;

import com.solvd.itcompany.exceptions.NegativeAmountException;
import com.solvd.itcompany.interfaces.IFinance;

public final class Customer extends Person implements IFinance {
    private int budget;

    public Customer() {
        super();
    }

    public Customer(int id, int budget) {
        super(id);
        this.budget = budget;
    }

    public Customer(String firstName, int id, int budget) {
        super(firstName, id);
        this.budget = budget;
    }

    public Customer(String firstName, String lastName, int age, int id, int budget) {
        super(firstName, lastName, age, id);
        this.budget = budget;
    }

    @Override
    public void earn(int amount) throws NegativeAmountException {
        if (amount < 0)
            throw new NegativeAmountException("Amount must be a positive number.");
        budget -= amount;
    }

    @Override
    public void spend(int amount) throws NegativeAmountException {
        if (amount < 0)
            throw new NegativeAmountException("Amount must be a positive number.");
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
