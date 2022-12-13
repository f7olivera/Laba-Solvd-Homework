package com.solvd.itcompany.people;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private int id;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(String firstName, int id) {
        this.firstName = firstName;
        this.id = id;
    }

    public Person(String firstName, String lastName, int age, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Full name: \n", getFullName()) +
                String.format("Age: %d\n", age) +
                String.format("ID: %d\n", id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        if (this.hashCode() != obj.hashCode()) return false;

        return id == ((Person) obj).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    /*
     * Getters and setters
     */
    public String getFirstName() {
        return this.firstName;
    }

    public String getFullName() {
        return firstName != null ? firstName : "" + " " + (lastName != null ? lastName : "");
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }
}
