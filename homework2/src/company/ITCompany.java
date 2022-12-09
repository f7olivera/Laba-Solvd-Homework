package company;

import applications.*;
import exceptions.*;
import interfaces.IDevelop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.*;

import java.util.HashMap;
import java.util.HashSet;

public class ITCompany extends Company implements IDevelop {
    private final HashMap<Class<? extends Application>, Application> baseApps = new HashMap<>();
    private final HumanResources humanResources = new HumanResources();
    private final ProjectsManager projectsManager = new ProjectsManager();
    private final static Logger LOGGER = LogManager.getLogger(ITCompany.class);

    public ITCompany(String name) {
        super(name);
    }

    public String getRequirements(Application application) {
        return baseApps.get(application.getClass()).getAppDetails().getRequirements();
    }

    public Quotation getQuotation(Application application) {
        try {
            Team team = createTeam(application);

            int workersSalaries = humanResources.getSalary(team.getScrumMaster()) + humanResources.getSalary(team.getProductOwner());
            for (Developer developer : team.getDevelopers())
                workersSalaries += humanResources.getSalary(developer);

            disassembleTeam(team);

            return new Quotation(baseApps.get(application.getClass()).getAppDetails().getBasePrice(), workersSalaries);
        } catch (NoWorkersAvailableException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException {
        projectsManager.startProject(project);
    }

    public void finishProject(Project project) throws ProjectNotFoundException {
        try {
            disassembleTeam(project.getTeam());
            projectsManager.finishProject(project);
        } catch (ProjectNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new ProjectNotFoundException(e.getMessage());
        } catch (InvalidProjectStateException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Team createTeam(Application application) throws NoWorkersAvailableException {
        AppDetails appDetails = baseApps.get(application.getClass()).getAppDetails();
        HashSet<Developer> devs = new HashSet<>();
        for (int i = 0; i < appDetails.getNumberOfDevelopers(); i++)
            devs.add((Developer) humanResources.getWorker(Developer.class));

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
    @Override
    public void hireWorker(Worker worker) {
        humanResources.addWorker(worker);
    }

    @Override
    public void fireWorker(Worker worker) {
        humanResources.removeWorker(worker);
    }

    public void addApp(Application application) {
        baseApps.put(application.getClass(), application);
    }

    public void setBaseSalary(Class<? extends Worker> workerClass, int salary) {
        humanResources.setBaseSalary(workerClass, salary);
    }

    public Project addProject(Application application, Customer customer) throws InsufficientBudgetException {
        try {
            int cost = getQuotation(application).getTotal();
            if (customer.getBudget() < cost) {
                throw new InsufficientBudgetException("Insufficient budget for customer with id " + customer.getId());
            } else {
                Project project = new Project(application, customer, createTeam(application));
                customer.spend(cost);
                projectsManager.addProject(project);
                return project;
            }
        } catch (NoWorkersAvailableException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public void removeProject(Project project) {
        disassembleTeam(projectsManager.removeProject(project));
    }
}
