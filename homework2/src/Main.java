import company.ITCompany;
import company.Project;
import company.ProjectState;
import company.Quotation;
import applications.*;
import people.*;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        ITCompany itCompany = initITCompany();

        // Requirements and quotation for a Website
        printExample(itCompany, new Website("Facebook", 50000, "www.facebook.com"));

        // Requirements and quotation for a MobileApp
        printExample(itCompany, new MobileApp("WhatsApp", 1000, "android"));

        // Requirements and quotation for a DesktopApp
        DesktopApp firefox = new DesktopApp("Firefox", "linux");
        printExample(itCompany, firefox);

        // Create customer and start project for a desktop app
        Customer customer = new Customer("Tim", "Berners-Lee", (short) 67, 12303, 500000);

        Project firefoxProject = itCompany.addProject(firefox, customer);
        itCompany.startProject(firefoxProject);

        System.out.println("/*********** Project example ***********/\n");
        System.out.println(firefoxProject);
    }

    /**
     * Creates an instance of IT Company
     */
    public static ITCompany initITCompany() {
        // Define base salaries for every worker
        HashMap<Class<? extends Worker>, Integer> baseSalaries = new HashMap<>();
        baseSalaries.put(Developer.class, 1000);
        baseSalaries.put(ProductOwner.class, 2500);
        baseSalaries.put(ScrumMaster.class, 1000);

        // Define app details
        HashMap<Class<? extends Application>, AppDetails> appDetails = new HashMap<>();
        appDetails.put(Website.class, new AppDetails("name, domain and expected number of users", 10000, 0.25F, 4));
        appDetails.put(MobileApp.class, new AppDetails("name, operating systems to be supported and expected number of users", 7500));
        appDetails.put(DesktopApp.class, new AppDetails("name and platforms to be supported", 5000));

        HashSet<Developer> developers = new HashSet<>();
        developers.add(new Developer(1, "frontend"));
        developers.add(new Developer(2, "frontend"));
        developers.add(new Developer(3, "backend"));
        developers.add(new Developer(4, "full-stack"));

        HashSet<ProductOwner> productOwners = new HashSet<>();
        productOwners.add(new ProductOwner(6));

        HashSet<ScrumMaster> scrumMasters = new HashSet<>();
        scrumMasters.add(new ScrumMaster(5));

        return new ITCompany("Solvd", baseSalaries, appDetails, developers, scrumMasters, productOwners);
    }

    /**
     * Print requirements and quotation for an Application
     */
    public static void printExample(ITCompany itCompany, Application app) {
        System.out.printf("/*********** %s ***********/\n%n", app.getClass().getSimpleName());
        String appRequirements = itCompany.getRequirements(app.getClass());
        System.out.println(String.format("Requirements to build a %s: ", app.getClass().getSimpleName()) + appRequirements + ".\n");

        Quotation appQuotation = itCompany.getQuotation(app);
        System.out.println("Quotation for this project:\n" + appQuotation);
    }
}
