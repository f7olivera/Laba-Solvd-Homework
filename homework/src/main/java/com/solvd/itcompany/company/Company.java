package com.solvd.itcompany.company;

public abstract class Company {
    private String name;

    public Company(String name) {
        this.name = name;
    }

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
