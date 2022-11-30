import company.ITCompany;
import company.Quotation;
import applications.*;
import people.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ITCompany itCompany = initCompany();

        // Requirements and quotation for a Website
        printExample(itCompany, new Website("Facebook", "www.facebook.com", 50000));

        // Requirements and quotation for a MobileApp
        printExample(itCompany, new MobileApp("WhatsApp", "android", 1000));

        // Requirements and quotation for a DesktopApp
        printExample(itCompany, new DesktopApp("Firefox", "linux"));
    }

    /**
     * Print requirements and quotation for an Application
     */
    public static void printExample(ITCompany itCompany, Application app) {
        System.out.printf("/*********** %s ***********/\n%n", app.getClass().getSimpleName());
        String appRequirements = itCompany.getRequirements(app.getClass());
        System.out.println(String.format("Requirements to build a %s: ", app.getClass().getSimpleName()) + appRequirements + "\n");

        Quotation appQuotation = itCompany.getQuotation(app);
        System.out.println("Quotation for this project:\n" + appQuotation);
    }

    /**
     * Creates an instance of IT Company
     */
    public static ITCompany initCompany() {
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

        return new ITCompany("Solvd", baseSalaries, appDetails);
    }
}
