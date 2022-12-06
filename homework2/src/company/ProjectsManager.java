package company;

import exceptions.InvalidProjectStateException;
import exceptions.NoDevelopersException;
import exceptions.ProjectNotFoundException;
import interfaces.IDevelop;
import people.*;

import java.util.HashSet;

public final class ProjectsManager implements IDevelop {
    private final HashSet<Project> projects = new HashSet<>();

    public void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException {
        if (!projects.contains(project)) {
            throw new ProjectNotFoundException("This project does not belong to this company.");
        } else if (project.getTeam().getDevelopers().isEmpty()) {
            throw new NoDevelopersException("Project can't start with no developers.");
        } else {
            projects.remove(project);
            project.setState(ProjectState.STARTED);
            projects.add(project);
        }
    }

    public void finishProject(Project project) throws ProjectNotFoundException, InvalidProjectStateException {
        if (!projects.contains(project)) {
            throw new ProjectNotFoundException("This project does not belong to this company.");
        } else if (project.getState() != ProjectState.STARTED) {
            String errorMessage =
                    project.getState() == ProjectState.NOT_STARTED ?
                            "This project has not started yet." :
                            "This project is already finished.";
            throw new InvalidProjectStateException(errorMessage);
        } else {
            projects.remove(project);
            project.setState(ProjectState.FINISHED);
            project.getApplication().deploy();
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
