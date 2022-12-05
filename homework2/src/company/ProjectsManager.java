package company;

import interfaces.IDevelop;
import people.*;

import java.util.HashSet;

public class ProjectsManager implements IDevelop {
    private final HashSet<Project> projects = new HashSet<>();

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
            project.getApplication().deploy();
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
            projects.remove(project);
            project.setState(ProjectState.FINISHED);
            projects.add(project);
        }
    }

    /*
     * Getters and setters
     */
    public HashSet<Project> getProjects() {
        return this.projects;
    }

    public void addProject(Project project) {
        //        if (developers.size() < appDetails.get(application.getClass()).getNumberOfDevelopers()
        //                || productOwners.isEmpty()
        //                || scrumMasters.isEmpty()) {
        //            // TODO: Throw exception
        //            System.out.println("No workers available.");
        //            System.exit(1);
        //        } else {
        // Create team based on the application needs
        this.projects.add(project);
        //        }
        //        return null;
    }

    public Team removeProject(Project project) {
        // TODO: Add validation
        if (projects.contains(project)) {
            this.projects.remove(project);
            return project.getTeam();
        }
        return null;
    }
}
