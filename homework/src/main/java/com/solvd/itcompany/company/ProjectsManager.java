package com.solvd.itcompany.company;

import com.solvd.itcompany.exceptions.InvalidProjectStateException;
import com.solvd.itcompany.exceptions.NoDevelopersException;
import com.solvd.itcompany.exceptions.ProjectNotFoundException;
import com.solvd.itcompany.interfaces.IDevelop;
import com.solvd.itcompany.people.*;

import java.util.HashSet;

public final class ProjectsManager implements IDevelop {
    private final HashSet<Project> projects = new HashSet<>();

    public void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException, InvalidProjectStateException {
        if (!projects.contains(project)) {
            throw new ProjectNotFoundException("This project does not belong to this company.");
        } else if (project.getTeam().getDevelopers().isEmpty()) {
            throw new NoDevelopersException("Project can't start with no developers.");
        } else if (project.getState() != ProjectState.NOT_STARTED) {
            throw new InvalidProjectStateException("This project has already started.");
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
        // Create team based on the application needs
        this.projects.add(project);
    }

    public Team removeProject(Project project) {
        if (!projects.contains(project)) {
            throw new ProjectNotFoundException("This project does not belong to this company.");
        } else {
            this.projects.remove(project);
            return project.getTeam();
        }
    }
}
