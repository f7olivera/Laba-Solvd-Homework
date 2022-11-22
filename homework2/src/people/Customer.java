package people;

public class Customer extends Person {
    private int customerId;

    public Customer() {
        super();
    }

    public Customer(int id, int customerId) {
        super(id);
        this.customerId = customerId;
    }

    public Customer(String firstName, String lastName, short age, int id, int customerId) {
        super(firstName, lastName, age, id);
        this.customerId = customerId;
    }


    /*
     * Getters and setters
     */
    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
