import java.util.ArrayList;

public class ITCompany {
    private String name;
    private ArrayList<Project> projects;
    private ArrayList<Developer> developers;
    private ArrayList<ScrumMaster> scrumMasters;
    private ArrayList<ProductOwner> productOwners;

    public ITCompany(String name) {
        this.name = name;
    }

    public ITCompany(String name,
                     ArrayList<Developer> developers,
                     ArrayList<ScrumMaster> scrumMasters,
                     ArrayList<ProductOwner> productOwners) {
        this.name = name;
        this.developers = developers;
        this.scrumMasters = scrumMasters;
        this.productOwners = productOwners;
    }

    public String getRequirements(Application application) {
        if (application.getClass() == Website.class) {
            return "";
        } else if (application.getClass() == MobileApp.class) {
            return "";
        } else if (application.getClass() == DesktopApp.class) {
            return "";
        }
        return "";
    }

    public Quotation getQuotation(Application application) {
        return new Quotation();
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

    public void addProject(Application application, Customer customer) {
        // Create team based on the application needs
        ArrayList<Developer> ds = new ArrayList<Developer>();
        for (int i = 0; i < application.getNumberOfDevelopers() && !developers.isEmpty(); i++)
            ds.add(developers.remove(0));

        ProductOwner po = !productOwners.isEmpty() ? productOwners.remove(0) : null;
        ScrumMaster sm = !scrumMasters.isEmpty() ? scrumMasters.remove(0) : null;

        Team team = new Team(ds, po, sm);

        Project project = new Project(application, customer, team);
        this.projects.add(project);
    }

    public void removeProject(Project project) {
        if (projects.contains(project)) {
            developers.addAll(project.getTeam().getDevelopers());
            productOwners.add(project.getTeam().getProductOwner());
            scrumMasters.add(project.getTeam().getScrumMaster());
            this.projects.remove(project);
        }
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }
}