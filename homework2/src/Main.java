import company.ITCompany;
import company.Project;
import company.Quotation;
import applications.*;
import exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.*;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws NoWorkersAvailableException,
            InsufficientBudgetException,
            ProjectNotFoundException,
            NoDevelopersException {
        LOGGER.info("Starting program");
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
        itCompany.finishProject(firefoxProject);
        itCompany.startProject(firefoxProject);
//        itCompany.addProject(new MobileApp("WhatsApp", 1000, "android"), customer);

        System.out.println("/*********** Project example ***********/\n");
        System.out.println(firefoxProject);
    }

    /**
     * Create an instance of ITCompany
     */
    public static ITCompany initITCompany() {
        ITCompany itCompany = new ITCompany("Solvd");

        // Define base salaries for every worker
        itCompany.setBaseSalary(Developer.class, 1000);
        itCompany.setBaseSalary(ProductOwner.class, 2500);
        itCompany.setBaseSalary(ScrumMaster.class, 1000);

        // Define app details
        itCompany.addApp(new Website(new AppDetails("name, domain and expected number of users", 10000, 0.25F, 4)));
        itCompany.addApp(new MobileApp(new AppDetails("name, operating systems to be supported and expected number of users", 7500, 0.5F, 2)));
        itCompany.addApp(new DesktopApp(new AppDetails("name and platforms to be supported", 5000)));

        // Hire workers
        itCompany.hireWorker(new Developer(1, "frontend"));
        itCompany.hireWorker(new Developer(2, "frontend"));
        itCompany.hireWorker(new Developer(3, "backend"));
        itCompany.hireWorker(new Developer(4, "full-stack"));

        itCompany.hireWorker(new ProductOwner(6));
        itCompany.hireWorker(new ScrumMaster(5));

        return itCompany;
    }

    /**
     * Print requirements and quotation for an Application
     */
    public static void printExample(ITCompany itCompany, Application app) throws NoWorkersAvailableException {
        System.out.printf("/*********** %s ***********/\n%n", app.getClass().getSimpleName());
        String appRequirements = itCompany.getRequirements(app);
        System.out.println(String.format("Requirements to build a %s: ", app.getClass().getSimpleName()) + appRequirements + ".\n");

        Quotation appQuotation = itCompany.getQuotation(app);
        System.out.println("Quotation for this project:\n" + appQuotation);
    }
}
