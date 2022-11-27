import applications.*;
import people.*;

import java.util.HashSet;

public class ITCompany {
    private String name;
    private HashSet<Project> projects = new HashSet<>();
    private HashSet<Developer> developers;
    private HashSet<ScrumMaster> scrumMasters;
    private HashSet<ProductOwner> productOwners;

    public ITCompany(String name) {
        this.name = name;
    }

    public ITCompany(String name,
                     HashSet<Developer> developers,
                     HashSet<ScrumMaster> scrumMasters,
                     HashSet<ProductOwner> productOwners) {
        this.name = name;
        this.developers = developers;
        this.scrumMasters = scrumMasters;
        this.productOwners = productOwners;
    }

    public String getRequirements(Application application) {
        if (application.getClass() == Website.class) {
            return Website.getRequirements();
        } else if (application.getClass() == MobileApp.class) {
            return MobileApp.getRequirements();
        } else if (application.getClass() == DesktopApp.class) {
            return DesktopApp.getRequirements();
        }
        return "";
    }

    public Quotation getQuotation(Application application) {
        Project project = new Project(application, new Customer());

        int appPrice = project.getApplication().getPrice();
        int workersSalaries = ProductOwner.getBaseSalary() + ScrumMaster.getBaseSalary() +
                              Developer.getBaseSalary() * application.getNumberOfDevelopers();

        return new Quotation(appPrice, workersSalaries);
    }

    /*
     * Getters and setters
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project addProject(Application application, Customer customer) {
        if (developers.size() < application.getNumberOfDevelopers() || productOwners.isEmpty() || scrumMasters.isEmpty()) {
            System.out.println("No workers available.");
            System.exit(1);
        } else {
            // Create team based on the application needs
            HashSet<Developer> devs = new HashSet<>();
            for (int i = 0; i < application.getNumberOfDevelopers(); i++) {
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

    public HashSet<Project> getProjects() {
        return this.projects;
    }
}
