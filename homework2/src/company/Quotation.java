package company;

public class Quotation {
    private int appPrice;
    private int workersSalaries;

    public Quotation(int appPrice, int workersSalaries) {
        this.appPrice = appPrice;
        this.workersSalaries = workersSalaries;
    }

    public int getTotal() {
        return appPrice + workersSalaries;
    }

    @Override
    public String toString() {
        return "Total: $" + getTotal() + "\n" +
                "- Application price: $" + appPrice + "\n" +
                "- Workers Salaries: $" + workersSalaries + "\n";
    }

    /*
     * Getters and setters
     */
    public int getAppPrice() {
        return this.appPrice;
    }

    void setAppPrice(int appPrice) {
        this.appPrice = appPrice;
    }

    public int getWorkersSalaries() {
        return this.workersSalaries;
    }

    void setWorkersSalaries(int workersSalaries) {
        this.workersSalaries = workersSalaries;
    }
}
