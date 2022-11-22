import applications.*;

public class Main {
    public static void main(String[] args) {
        // Instantiate IT Company
        ITCompany itCompany = new ITCompany("Solvd");

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
