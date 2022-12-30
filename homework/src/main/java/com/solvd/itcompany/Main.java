package com.solvd.itcompany;

import com.solvd.itcompany.applications.Application;
import com.solvd.itcompany.applications.DesktopApp;
import com.solvd.itcompany.applications.MobileApp;
import com.solvd.itcompany.applications.Website;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.company.Project;
import com.solvd.itcompany.company.Quotation;
import com.solvd.itcompany.enums.Environment;
import com.solvd.itcompany.exceptions.*;
import com.solvd.itcompany.interfaces.ProjectFilter;
import com.solvd.itcompany.interfaces.TriPredicate;
import com.solvd.itcompany.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.solvd.itcompany.Initializer.initITCompany;
import static com.solvd.itcompany.Initializer.logExample;
import static com.solvd.itcompany.company.Project.SECONDS_IN_DAY;
import static com.solvd.itcompany.company.Project.timestamp2date;

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
        // Firefox project
        Project firefoxProject = itCompany.addProject(firefox, customer, 30);
        itCompany.finishProject(firefoxProject);
        itCompany.startProject(firefoxProject);
        LOGGER.info("Firefox project example:\n" + firefoxProject);

        // Twitter project
        Website twitter = new Website("Twitter", EnumSet.of(Environment.CHROME), 50000, "www.twitter.com");
        Project twitterProject = itCompany.addProject(twitter, customer, 10);
        itCompany.startProject(twitterProject);
        LOGGER.info("Twitter project example:\n" + twitterProject);

        // Facebook project
        Website facebook = new Website("Facebook", 50000, "www.facebook.com");
        Project facebookProject = itCompany.addProject(facebook, customer, 2);
        itCompany.startProject(facebookProject);
        LOGGER.info("Facebook project example:\n" + facebookProject);

        // Use lambdas
        LOGGER.info("Finding all website projects.");
        ProjectFilter projectFilter =
                (projects, appType) ->
                        projects.stream()
                                .filter(project -> project.getApplication().getClass() == appType)
                                .collect(Collectors.toSet());
        HashSet<Project> websiteProjects = (HashSet<Project>) projectFilter.filter(itCompany.getProjectsManager().getProjects(), Website.class);
        LOGGER.info(websiteProjects);

        websiteProjects
                .stream()
                .forEach(project -> LOGGER.info(project.getApplication().getName() + " project deadline: " + timestamp2date(project.getDeadline())));
        LOGGER.info("Delaying all website projects by 1 week.");
        itCompany.getProjectsManager().processProjects(
                (project) -> project.getApplication().getClass() == Website.class,
                (project -> {
                    project.setDeadline(project.getDeadline() + SECONDS_IN_DAY * 7);
                    return project;
                })
        );
        websiteProjects
                .stream()
                .forEach(project -> LOGGER.info(project.getApplication().getName() + " project deadline: " + timestamp2date(project.getDeadline())));

        LOGGER.info("Finding all website projects with a deadline within the next 10 days and a cost of at least $10.000.");
        long timestampTenDays = Instant.now().getEpochSecond() + 10 * SECONDS_IN_DAY;
        TriPredicate<Class<? extends Application>, Quotation, Long> projectPredicate =
                (appClass, quotation, deadline) ->
                        appClass == Website.class && quotation.getTotal() > 10000 && deadline < timestampTenDays;

        HashSet<Project> projects = (HashSet<Project>) itCompany.getProjectsManager().getProjects()
                .stream()
                .filter(project ->
                        projectPredicate.test(project.getApplication().getClass(),
                                project.getQuotation(),
                                project.getDeadline()))
                .collect(Collectors.toSet());
        LOGGER.info(projects);
    }
}
