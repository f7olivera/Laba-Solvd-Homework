import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John", "Doe", (short) 45, 41412);
        System.out.println(customer);

        // Create developer team
        ArrayList<Developer> developers = new ArrayList<>();
        developers.add(new Developer(124, 3000, "backend"));
        developers.add(new Developer(234, 3000, "frontend"));
        developers.add(new Developer(12, 3000, "full stack"));

        // Create scrum master
        ArrayList<ScrumMaster> scrumMasters = new ArrayList<>();
        scrumMasters.add(new ScrumMaster(211,  3000));

        // Create product owners
        ArrayList<ProductOwner> productOwners = new ArrayList<>();
        productOwners.add(new ProductOwner(51, 5000));
        productOwners.add(new ProductOwner(100, 5000));

        // Instantiate IT Company
        ITCompany itCompany = new ITCompany("Solvd", developers, scrumMasters, productOwners);

        // Ask for requirements and quotation to build a website
        MobileApp m = new MobileApp("asd");
        String requirements = itCompany.getRequirements(new Website());
        String requirements2 = itCompany.getRequirements(new MobileApp());
        String requirements3 = itCompany.getRequirements(new DesktopApp());
        Quotation quotation = itCompany.getQuotation(new Website());
    }
}
