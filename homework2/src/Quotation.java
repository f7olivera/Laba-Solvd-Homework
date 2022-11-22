public class Quotation {
    private int appPrice;
    private int workersSalaries;
    private int total;

    public Quotation(int appPrice, int workersSalaries) {
        this.appPrice = appPrice;
        this.workersSalaries = workersSalaries;
        this.total = appPrice + workersSalaries;
    }

    @Override
    public String toString() {
        return "Total: $" + total + "\n" +
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

    public int getTotal() {
        return this.total;
    }

    void setTotal(int total) {
        this.total = total;
    }
}
