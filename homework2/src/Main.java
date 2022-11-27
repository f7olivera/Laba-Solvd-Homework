import applications.*;
import people.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        /*
         * Instantiate IT Company
         */

        // Define base salaries for every worker
        HashMap<Class<? extends Worker>, Integer> baseSalaries = new HashMap<>();
        baseSalaries.put(Developer.class, 1000);
        baseSalaries.put(ProductOwner.class, 2500);
        baseSalaries.put(ScrumMaster.class, 1000);

        // Define app details
        HashMap<Class<? extends Application>, AppDetails> appDetails = new HashMap<>();
        appDetails.put(Website.class, new AppDetails("name, domain and expected number of users", 10000));
        appDetails.put(MobileApp.class, new AppDetails("name, operating systems to be supported and expected number of users", 7500));
        appDetails.put(DesktopApp.class, new AppDetails("name and platforms to be supported", 5000));

        ITCompany itCompany = new ITCompany("Solvd", baseSalaries, appDetails);

        /*
         * Requirements and quotation for a Website
         */
        System.out.println("/*********** Website ***********/\n");
        String requirements = itCompany.getRequirements(Website.class);
        System.out.println("Requirements to build a website: " + requirements + "\n");

        Website website = new Website("Facebook", "www.facebook.com");

        Quotation quotation = itCompany.getQuotation(website);
        System.out.println("Quotation for this project:\n" + quotation);

        /*
         * Requirements and quotation for a MobileApp
         */
        System.out.println("/*********** MobileApp ***********/\n");
        String mobileAppRequirements = itCompany.getRequirements(MobileApp.class);
        System.out.println("Requirements to build a mobile app: " + mobileAppRequirements + "\n");

        MobileApp app = new MobileApp("WhatsApp", "android");

        Quotation appQuotation = itCompany.getQuotation(app);
        System.out.println("Quotation for this project:\n" + appQuotation);

        /*
         * Requirements and quotation for a DesktopApp
         */
        System.out.println("/*********** DesktopApp ***********/\n");
        String desktopAppRequirements = itCompany.getRequirements(DesktopApp.class);
        System.out.println("Requirements to build a mobile app: " + desktopAppRequirements + "\n");

        DesktopApp desktopApp = new DesktopApp("Firefox", "android");

        Quotation desktopAppQuotation = itCompany.getQuotation(desktopApp);
        System.out.println("Quotation for this project:\n" + desktopAppQuotation);
    }
}
