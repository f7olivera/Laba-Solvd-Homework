package com.solvd.itcompany.company;

import com.solvd.itcompany.applications.AppDetails;
import com.solvd.itcompany.applications.Application;
import com.solvd.itcompany.enums.CompanyType;
import com.solvd.itcompany.exceptions.*;
import com.solvd.itcompany.interfaces.IDevelop;
import com.solvd.itcompany.interfaces.IEmploy;
import com.solvd.itcompany.people.*;
import com.solvd.linkedlist.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.stream.IntStream;

public class ITCompany extends Company implements IDevelop, IEmploy {
    private final HashMap<Class<? extends Application>, Application> baseApps = new HashMap<>();
    private final HumanResources humanResources = new HumanResources();
    private final ProjectsManager projectsManager = new ProjectsManager();
    private final static Logger LOGGER = LogManager.getLogger(ITCompany.class);

    public ITCompany(String name, CompanyType companyType) {
        super(name, companyType);
        LOGGER.info("Creating IT Company " + name + " (" + companyType + ").");
    }

    public String getRequirements(Application application) {
        LOGGER.info("Getting requirements for " + application.getClass().getSimpleName() + ".");
        return baseApps.get(application.getClass()).getAppDetails().getRequirements();
    }

    public Quotation getQuotation(Application application) {
        LOGGER.info("Getting quotation for " + application.getClass().getSimpleName() + ".");
        Team team = createTeam(application);

        int workersSalaries = humanResources.getSalary(team.getScrumMaster()) + humanResources.getSalary(team.getProductOwner());
        for (Developer developer : team.getDevelopers())
            workersSalaries += humanResources.getSalary(developer);

        disassembleTeam(team);

        return new Quotation(baseApps.get(application.getClass()).getAppDetails().getBasePrice(), workersSalaries);
    }

    public void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException {
        LOGGER.info("Starting project " + project.getApplication().getName() + " for " + project.getCustomer().getFullName() + ".");
        try {
            projectsManager.startProject(project);
        } catch (InvalidProjectStateException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void finishProject(Project project) throws ProjectNotFoundException {
        LOGGER.info("Finishing project " + project.getApplication().getName() + " for " + project.getCustomer().getFullName() + ".");
        try {
            disassembleTeam(project.getTeam());
            projectsManager.finishProject(project);
        } catch (InvalidProjectStateException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Team createTeam(Application application) throws NoWorkersAvailableException {
        AppDetails appDetails = baseApps.get(application.getClass()).getAppDetails();
        LOGGER.info("Creating team with " + appDetails.getNumberOfDevelopers()
                + " developer" + (appDetails.getNumberOfDevelopers() == 1 ? "." : "s."));

        LinkedList<Developer> devs = new LinkedList<>();
        IntStream.range(0, appDetails.getNumberOfDevelopers())
                .forEach((i) -> devs.add((Developer) humanResources.getWorkerWithPredicate((worker -> worker.getClass() == Developer.class))));

        return new Team(devs,
                (ProductOwner) humanResources.getWorker(ProductOwner.class),
                (ScrumMaster) humanResources.getWorker(ScrumMaster.class));
    }

    public void disassembleTeam(Team team) {
        for (Developer dev : team.getDevelopers())
            humanResources.addWorker(dev);

        humanResources.addWorker(team.getScrumMaster());
        humanResources.addWorker(team.getProductOwner());
    }

    /*
     * Getters and setters
     */
    public void hireWorker(Worker worker) {
        LOGGER.info("Hiring worker with id " + worker.getEmployeeId() + ".");
        humanResources.addWorker(worker);
    }

    public void fireWorker(Worker worker) {
        LOGGER.info("Firing worker with id " + worker.getEmployeeId() + ".");
        humanResources.removeWorker(worker);
    }

    public void addApp(Application application) {
        baseApps.put(application.getClass(), application);
    }

    public void setBaseSalary(Class<? extends Worker> workerClass, int salary) {
        try {
            humanResources.setBaseSalary(workerClass, salary);
        } catch (NegativeAmountException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * @param deadline: amount of days left for the deadline date
     */
    public Project addProject(Application application, Customer customer, int deadline)
            throws InsufficientBudgetException, NoWorkersAvailableException, NegativeAmountException {
        LOGGER.info("Creating new " + application.getClass().getSimpleName() + " project for " + customer.getFullName() + ".");
        int cost = getQuotation(application).getTotal();
        if (customer.getBudget() < cost) {
            throw new InsufficientBudgetException("Insufficient budget for customer with id " + customer.getId());
        } else {
            Project project = new Project(application, customer, deadline, createTeam(application));
            customer.spend(cost);
            projectsManager.addProject(project);
            return project;
        }
    }

    public void removeProject(Project project) {
        disassembleTeam(projectsManager.removeProject(project));
    }
}
