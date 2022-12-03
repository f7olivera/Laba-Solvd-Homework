package company;

import applications.*;
import interfaces.IDevelop;
import interfaces.IEmploy;
import people.*;

import java.util.HashMap;
import java.util.HashSet;

public class ITCompany extends Company implements IEmploy, IDevelop {
    private HashMap<Class<? extends Worker>, Integer> baseSalaries;
    private HashMap<Class<? extends Application>, AppDetails> appDetails;
    private HashSet<Developer> developers;
    private HashSet<ScrumMaster> scrumMasters;
    private HashSet<ProductOwner> productOwners;
    private HashSet<Project> projects = new HashSet<>();

    public ITCompany(String name) {
        super(name);
    }

    public ITCompany(String name,
                     HashMap<Class<? extends Worker>,
                             Integer> baseSalaries,
                     HashMap<Class<? extends Application>, AppDetails> appDetails) {
        super(name);
        this.baseSalaries = baseSalaries;
        this.appDetails = appDetails;
    }

    public ITCompany(String name,
                     HashMap<Class<? extends Worker>,
                             Integer> baseSalaries,
                     HashMap<Class<? extends Application>, AppDetails> appDetails,
                     HashSet<Developer> developers,
                     HashSet<ScrumMaster> scrumMasters,
                     HashSet<ProductOwner> productOwners) {
        super(name);
        this.baseSalaries = baseSalaries;
        this.appDetails = appDetails;
        this.developers = developers;
        this.scrumMasters = scrumMasters;
        this.productOwners = productOwners;
    }

    public String getRequirements(Class<? extends Application> appClass) {
        return appDetails.get(appClass).getRequirements();
    }

    public Quotation getQuotation(Application application) {
        Team team = createTeam(application);
        int workersSalaries = getSalary(team.getScrumMaster()) + getSalary(team.getProductOwner());

        for (Developer developer : team.getDevelopers())
            workersSalaries += getSalary(developer);

        disassembleTeam(team);

        return new Quotation(getAppPrice(application), workersSalaries);
    }

    public int getAppPrice(Application application) {
        AppDetails details = appDetails.get(application.getClass());
        return details.getBasePrice() + (int) (application.getNumberOfUsers() * details.getPricePerUser());
    }

    @Override
    public void hireWorker(Worker worker) {
        if (worker.getClass() == Developer.class)
            developers.add((Developer) worker);
        else if (worker.getClass() == ProductOwner.class)
            productOwners.add((ProductOwner) worker);
        else if (worker.getClass() == ScrumMaster.class)
            scrumMasters.add((ScrumMaster) worker);
    }

    @Override
    public void fireWorker(Worker worker) {
        if (Developer.class.equals(worker.getClass()))
            developers.remove(worker);
        else if (ProductOwner.class.equals(worker.getClass()))
            productOwners.remove((ProductOwner) worker);
        else if (ScrumMaster.class.equals(worker.getClass()))
            scrumMasters.remove((ScrumMaster) worker);
    }

    @Override
    public HashSet<Worker> getWorkers() {
        HashSet<Worker> workers = new HashSet<>();
        workers.addAll(developers);
        workers.addAll(productOwners);
        workers.addAll(scrumMasters);

        return workers;
    }

    public void startProject(Project project) {
        if (!projects.contains(project)) {
            // TODO: Throw exception
            System.out.println("This project does not belong to this company.");
            System.exit(1);
        } else if (project.getTeam().getDevelopers().isEmpty()) {
            // TODO: Throw exception
            System.out.println("Project can't start with no developers.");
            System.exit(1);
        } else {
            projects.remove(project);
            project.setState(ProjectState.STARTED);
            projects.add(project);
        }
    }

    public void finishProject(Project project) {
        if (!projects.contains(project)) {
            // TODO: Throw exception
            System.out.println("This project does not belong to this company.");
            System.exit(1);
        } else if (project.getState() != ProjectState.STARTED) {
            System.out.println(project.getState() == ProjectState.UNBEGUN ? "This project has not started yet." : "This project is already finished.");
            System.exit(1);
        } else {
            disassembleTeam(project.getTeam());
            projects.remove(project);
            project.setState(ProjectState.FINISHED);
            projects.add(project);
        }
    }

    public Team createTeam(Application application) {
        HashSet<Developer> devs = new HashSet<>();
        for (int i = 0; i < appDetails.get(application.getClass()).getNumberOfDevelopers(); i++) {
            devs.add(developers.iterator().next());
            developers.remove(developers.iterator().next());
        }

        ProductOwner po = productOwners.iterator().next();
        productOwners.remove(productOwners.iterator().next());

        ScrumMaster sm = scrumMasters.iterator().next();
        scrumMasters.remove(scrumMasters.iterator().next());

        return new Team(devs, po, sm);
    }

    public void disassembleTeam(Team team) {
        // TODO: Add validation
        developers.addAll(team.getDevelopers());
        scrumMasters.add(team.getScrumMaster());
        productOwners.add(team.getProductOwner());
    }

    public int getSalary(Worker worker) {
        if (worker.getSalary() == 0)
            return baseSalaries.get(worker.getClass());
        else
            return worker.getSalary();
    }

    /*
     * Getters and setters
     */
    public HashSet<Project> getProjects() {
        return this.projects;
    }

    public Project addProject(Application application, Customer customer) {
        if (developers.size() < appDetails.get(application.getClass()).getNumberOfDevelopers()
                || productOwners.isEmpty()
                || scrumMasters.isEmpty()) {
            // TODO: Throw exception
            System.out.println("No workers available.");
            System.exit(1);
        } else {
            // Create team based on the application needs
            Project project = new Project(application, customer, createTeam(application));
            this.projects.add(project);

            return project;
        }
        return null;
    }

    public void removeProject(Project project) {
        if (projects.contains(project)) {
            developers.addAll(project.getTeam().getDevelopers());
            productOwners.add(project.getTeam().getProductOwner());
            scrumMasters.add(project.getTeam().getScrumMaster());
            this.projects.remove(project);
        }
    }

    public HashMap<Class<? extends Application>, AppDetails> getAppDetails() {
        return this.appDetails;
    }

    public void setAppDetails(HashMap<Class<? extends Application>, AppDetails> appDetails) {
        this.appDetails = appDetails;
    }
}
