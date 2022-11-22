import applications.DesktopApp;
import applications.MobileApp;
import applications.Website;
import people.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John", "Doe", (short) 45, 41412);

        // Create developer team
        ArrayList<Developer> developers = new ArrayList<>();
        developers.add(new Developer(124, 3000, "backend"));
        developers.add(new Developer(234, 3000, "frontend"));
        developers.add(new Developer(12, 3000, "full stack"));

        // Create scrum master
        ArrayList<ScrumMaster> scrumMasters = new ArrayList<>();
        scrumMasters.add(new ScrumMaster(211,  3000));
        scrumMasters.add(new ScrumMaster(210,  3000));

        // Create product owners
        ArrayList<ProductOwner> productOwners = new ArrayList<>();
        productOwners.add(new ProductOwner(51, 5000));
        productOwners.add(new ProductOwner(100, 5000));

        // Instantiate IT Company
        ITCompany itCompany = new ITCompany("Solvd", developers, scrumMasters, productOwners);

        /*
         * Requirements and quotation for a Website
         */
        System.out.println("/*********** Website ***********/\n");
        String requirements = itCompany.getRequirements(new Website());
        System.out.println("Requirements to build a website: " + requirements + "\n");

        Website website = new Website("Facebook", 5000);

        Quotation quotation = itCompany.getQuotation(website);
        System.out.println("Quotation for this project:\n" + quotation);

        /*
         * Requirements and quotation for a MobileApp
         */
        System.out.println("/*********** MobileApp ***********/\n");
        String mobileAppRequirements = itCompany.getRequirements(new MobileApp());
        System.out.println("Requirements to build a mobile app: " + mobileAppRequirements + "\n");

        MobileApp app = new MobileApp("WhatsApp", "android");

        Quotation appQuotation = itCompany.getQuotation(app);
        System.out.println("Quotation for this project:\n" + appQuotation);
    }
}
