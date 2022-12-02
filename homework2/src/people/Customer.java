package people;

public class Customer extends Person {
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

    /*
     * Getters and setters
     */
    public int getCustomerId() {
        return this.budget;
    }

    public void setCustomerId(int budget) {
        this.budget = budget;
    }
}
