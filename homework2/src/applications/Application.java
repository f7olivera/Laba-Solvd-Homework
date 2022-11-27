package applications;

abstract public class Application {
    private String name;

    public Application(String name) {
        this.name = name;
    }

    /*
     * Getters and setters
     */
    public String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }
}
