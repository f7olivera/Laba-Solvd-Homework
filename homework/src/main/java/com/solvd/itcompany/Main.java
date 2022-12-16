package com.solvd.itcompany;

import com.solvd.itcompany.applications.*;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.company.Project;
import com.solvd.itcompany.company.Quotation;
import com.solvd.itcompany.exceptions.*;
import com.solvd.itcompany.people.Customer;
import com.solvd.itcompany.people.Developer;
import com.solvd.itcompany.people.ProductOwner;
import com.solvd.itcompany.people.ScrumMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args)
            throws NoWorkersAvailableException, InsufficientBudgetException, ProjectNotFoundException,
            NoDevelopersException, NegativeAmountException, FileNotFoundException {
        LOGGER.info("Starting program");
        ITCompany itCompany = initITCompany();

        // Requirements and quotation for a Website
        logExample(itCompany, new Website("Facebook", 50000, "www.facebook.com"));

        // Requirements and quotation for a MobileApp
        logExample(itCompany, new MobileApp("WhatsApp", 1000, "android"));

        // Requirements and quotation for a DesktopApp
        DesktopApp firefox = new DesktopApp("Firefox", "linux");
        logExample(itCompany, firefox);

        Customer customer;

        // Read customer data using try-with-resources
        try (Scanner scanner = new Scanner(new File("src/main/resources/customer-data.txt"))) {
            String name = scanner.nextLine();
            int id = scanner.nextInt();
            int budget = scanner.nextInt();

            customer = new Customer(name, id, budget);
        }

        Project firefoxProject = itCompany.addProject(firefox, customer);
        itCompany.finishProject(firefoxProject);
        itCompany.startProject(firefoxProject);

        LOGGER.info("Firefox project example:\n" + firefoxProject);
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
    public static void logExample(ITCompany itCompany, Application app) throws NoWorkersAvailableException {
        LOGGER.info(String.format("Logging %s example.", app.getClass().getSimpleName()));
        String appRequirements = itCompany.getRequirements(app);
        LOGGER.info(String.format("Requirements to build a %s: ", app.getClass().getSimpleName()) + appRequirements + ".");

        Quotation appQuotation = itCompany.getQuotation(app);
        LOGGER.info("Quotation for this project:\n" + appQuotation);
    }
}
