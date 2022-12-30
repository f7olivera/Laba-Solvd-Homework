package com.solvd.itcompany;

import com.solvd.itcompany.applications.DesktopApp;
import com.solvd.itcompany.applications.MobileApp;
import com.solvd.itcompany.applications.Website;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.company.Project;
import com.solvd.itcompany.enums.Environment;
import com.solvd.itcompany.exceptions.*;
import com.solvd.itcompany.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;

import static com.solvd.itcompany.Initializer.initITCompany;
import static com.solvd.itcompany.Initializer.logExample;
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
        HashSet<Project> websiteProjects = itCompany.getProjectsManager().getProjects(Website.class);

        websiteProjects
                .stream()
                .forEach(project -> LOGGER.info(project.getApplication().getName() + " project deadline: " + timestamp2date(project.getDeadline())));

        // Delay website projects deadline by 1 week
        itCompany.getProjectsManager().delayProjects(Website.class, 7);

        websiteProjects
                .stream()
                .forEach(project -> LOGGER.info(project.getApplication().getName() + " project deadline: " + timestamp2date(project.getDeadline())));

        // Get upcoming projects
        HashSet<Project> upcomingProjects = itCompany.getProjectsManager().getUpcomingProjects();
        LOGGER.info(upcomingProjects);
    }
}
