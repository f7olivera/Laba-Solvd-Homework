package com.solvd.itcompany;

import com.solvd.itcompany.applications.*;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.company.Project;
import com.solvd.itcompany.company.Quotation;
import com.solvd.itcompany.enums.CompanyType;
import com.solvd.itcompany.enums.Environment;
import com.solvd.itcompany.exceptions.*;
import com.solvd.itcompany.interfaces.ProjectFilter;
import com.solvd.itcompany.interfaces.TriPredicate;
import com.solvd.itcompany.people.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.solvd.itcompany.company.Project.SECONDS_IN_DAY;

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
        Environment android = Environment.ANDROID;
        android.setMinimumSupportedVersion("9.0");
        logExample(itCompany, new MobileApp("WhatsApp", 1000, EnumSet.of(android)));

        // Requirements and quotation for a DesktopApp
        DesktopApp firefox = new DesktopApp("Firefox", EnumSet.of(Environment.LINUX));
        logExample(itCompany, firefox);

        Customer customer;

        // Read customer data using try-with-resources
        try (Scanner scanner = new Scanner(new File("src/main/resources/customer-data.txt"))) {
            String name = scanner.nextLine();
            int id = scanner.nextInt();
            int budget = scanner.nextInt();

            customer = new Customer(name, id, budget);
        }

        // Add sample projects
        Project firefoxProject = itCompany.addProject(firefox, customer, 30);
        itCompany.finishProject(firefoxProject);
        itCompany.startProject(firefoxProject);
        LOGGER.info("Firefox project example:\n" + firefoxProject);

        Website twitter = new Website("Twitter", EnumSet.of(Environment.CHROME), 50000, "www.twitter.com");
        Project twitterProject = itCompany.addProject(twitter, customer, 10);
        itCompany.startProject(twitterProject);
        LOGGER.info("Twitter project example:\n" + twitterProject);

        Website facebook = new Website("Facebook", 50000, "www.facebook.com");
        Project facebookProject = itCompany.addProject(facebook, customer, 2);
        itCompany.startProject(facebookProject);
        LOGGER.info("Facebook project example:\n" + facebookProject);

        LOGGER.info("Delaying all website projects by 1 week.");
        itCompany.getProjectsManager().processProjects(
                (project) -> project.getApplication().getClass() == Website.class,
                (project -> project.setDeadline(project.getDeadline() + SECONDS_IN_DAY * 7))
        );

        LOGGER.info("Twitter project example:\n" + twitterProject);

        LOGGER.info("Finding all website projects.");
        ProjectFilter projectFilter =
                (projects, appType) ->
                        projects.stream()
                                .filter(project -> project.getApplication().getClass() == appType)
                                .collect(Collectors.toSet());
        HashSet<Project> websiteProjects = (HashSet<Project>) projectFilter.filter(itCompany.getProjectsManager().getProjects(), Website.class);
        LOGGER.info(websiteProjects);

        LOGGER.info("Finding all website projects with a deadline within the next 10 days and a cost of at least $10.000.");
        long timestampTenDays = Instant.now().getEpochSecond() + 10 * SECONDS_IN_DAY;
        TriPredicate<Class<? extends Application>, Quotation, Long> projectPredicate =
                (appClass, quotation, deadline) ->
                        appClass == Website.class && quotation.getTotal() > 10000 && deadline < timestampTenDays;

        HashSet<Project> projects = (HashSet<Project>) itCompany.getProjectsManager().getProjects()
                .stream()
                .filter(project ->
                        projectPredicate.test(project.getApplication().getClass(),
                                itCompany.getQuotation(project.getApplication()),
                                project.getDeadline()))
                .collect(Collectors.toSet());
        LOGGER.info(projects);
    }

    /**
     * Create an instance of ITCompany
     */
    public static ITCompany initITCompany() {
        ITCompany itCompany = new ITCompany("Solvd", CompanyType.CORPORATION);

        // Define base salaries for every worker
        itCompany.setBaseSalary(Developer.class, 1000);
        itCompany.setBaseSalary(ProductOwner.class, 2500);
        itCompany.setBaseSalary(ScrumMaster.class, 1000);

        // Define app details
        itCompany.addApp(new Website(new AppDetails("name, domain and expected number of users", 10000, 0.25F, 4)));
        itCompany.addApp(new MobileApp(new AppDetails("name, operating systems to be supported and expected number of users", 7500, 0.5F, 2)));
        itCompany.addApp(new DesktopApp(new AppDetails("name and platforms to be supported", 5000)));

        // Hire workers
        itCompany.hireWorker(new Developer(1, 5000, "frontend"));
        itCompany.hireWorker(new Developer(2, 5000, "frontend"));
        itCompany.hireWorker(new Developer(3, "frontend"));
        itCompany.hireWorker(new Developer(4, "frontend"));
        itCompany.hireWorker(new Developer(5, "frontend"));
        itCompany.hireWorker(new Developer(6, 5000, "backend"));
        itCompany.hireWorker(new Developer(7, 5000, "backend"));
        itCompany.hireWorker(new Developer(8, "backend"));
        itCompany.hireWorker(new Developer(9, "backend"));
        itCompany.hireWorker(new Developer(10, 5000, "backend"));
        itCompany.hireWorker(new Developer(11, 5000, "full-stack"));
        itCompany.hireWorker(new Developer(12, "full-stack"));
        itCompany.hireWorker(new Developer(13, "full-stack"));
        itCompany.hireWorker(new Developer(14, "full-stack"));

        LOGGER.info("Reducing developers salaries by $500.");
        LOGGER.info(itCompany.getHumanResources().getWorkerWithPredicate((worker) -> worker.getClass() == Developer.class));
        Consumer<Worker> consumer = (worker) -> worker.setSalary(worker.getSalary() - 500);
        itCompany.getHumanResources().processWorkers(Developer.class, consumer);
        LOGGER.info(itCompany.getHumanResources().getWorkerWithPredicate((worker) -> worker.getClass() == Developer.class));

        itCompany.hireWorker(new ProductOwner(15));
        itCompany.hireWorker(new ProductOwner(16));
        itCompany.hireWorker(new ProductOwner(17));
        itCompany.hireWorker(new ScrumMaster(18));
        itCompany.hireWorker(new ScrumMaster(19));
        itCompany.hireWorker(new ScrumMaster(20));

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
