import applications.*;
import people.*;

import java.util.HashMap;
import java.util.HashSet;

public class ITCompany extends Company {
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
                     HashMap<Class<? extends Worker>, Integer> baseSalaries,
                     HashMap<Class<? extends Application>, AppDetails> appDetails) {
        super(name);
        this.baseSalaries = baseSalaries;
        this.appDetails = appDetails;
    }

    public ITCompany(String name,
                     HashMap<Class<? extends Application>, AppDetails> appDetails,
                     HashSet<Developer> developers,
                     HashSet<ScrumMaster> scrumMasters,
                     HashSet<ProductOwner> productOwners) {
        super(name);
        this.appDetails = appDetails;
        this.developers = developers;
        this.scrumMasters = scrumMasters;
        this.productOwners = productOwners;
    }

    public String getRequirements(Class<? extends Application> appClass) {
        return appDetails.get(appClass).getRequirements();
    }

    public Quotation getQuotation(Application application) {
        int workersSalaries = baseSalaries.get(ProductOwner.class) +
                baseSalaries.get(ScrumMaster.class) +
                baseSalaries.get(Developer.class) * appDetails.get(application.getClass()).getNumberOfDevelopers();

        return new Quotation(getAppPrice(application), workersSalaries);
    }

    public int getAppPrice(Application application) {
        AppDetails details = appDetails.get(application.getClass());
        return details.getBasePrice() + (int) (application.getNumberOfUsers() * details.getPricePerUser());
    }

    @Override
    public void hireWorker(Object worker) {
        if(worker.getClass() == Developer.class)
            developers.add((Developer) worker);
        else if(worker.getClass() == ProductOwner.class)
            productOwners.add((ProductOwner) worker);
        else if(worker.getClass() == ScrumMaster.class)
            scrumMasters.add((ScrumMaster) worker);
    }

    @Override
    public void fireWorker(Object worker) {
        if(worker.getClass() == Developer.class)
            developers.remove((Developer) worker);
        else if(worker.getClass() == ProductOwner.class)
            productOwners.remove((ProductOwner) worker);
        else if(worker.getClass() == ScrumMaster.class)
            scrumMasters.remove((ScrumMaster) worker);
    }

    @Override
    public HashSet<? extends Worker> getWorkers() {
        HashSet<Worker> workers = new HashSet<>();
        workers.addAll(developers);
        workers.addAll(productOwners);
        workers.addAll(scrumMasters);

        return workers;
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
            System.out.println("No workers available.");
            System.exit(1);
        } else {
            // Create team based on the application needs
            HashSet<Developer> devs = new HashSet<>();
            for (int i = 0; i < appDetails.get(application.getClass()).getNumberOfDevelopers(); i++) {
                devs.add(developers.iterator().next());
                developers.remove(developers.iterator().next());
            }

            ProductOwner po = productOwners.iterator().next();
            productOwners.remove(productOwners.iterator().next());

            ScrumMaster sm = scrumMasters.iterator().next();
            scrumMasters.remove(scrumMasters.iterator().next());

            Team team = new Team(devs, po, sm);

            Project project = new Project(application, customer, team);
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
