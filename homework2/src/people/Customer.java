package people;

public class Customer extends Person {
    public Customer(String firstName, String lastName, short age, int id) {
        super(firstName, lastName, age, id);
    }

    public Customer() {
        super();
    }

    public Customer(int id) {
        super(id);
    }
}
