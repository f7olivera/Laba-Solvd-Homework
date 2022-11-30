package people;

public class Person {
    private String firstName;
    private String lastName;
    private short age;
    private int id;

    public Person() {
    }

    public Person(String firstName, String lastName, short age, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    public Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Full name: %s %s\n", firstName, lastName) +
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
        return this.firstName + " " + this.lastName;
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

    public short getAge() {
        return this.age;
    }

    void setAge(short age) {
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }
}
