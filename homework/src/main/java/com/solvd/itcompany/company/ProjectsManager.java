package com.solvd.itcompany.company;

import com.solvd.enums.ProjectState;
import com.solvd.itcompany.exceptions.InvalidProjectStateException;
import com.solvd.itcompany.exceptions.NoDevelopersException;
import com.solvd.itcompany.exceptions.ProjectNotFoundException;
import com.solvd.itcompany.interfaces.IDevelop;
import com.solvd.itcompany.people.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public final class ProjectsManager implements IDevelop {
    private final HashSet<Project> projects = new HashSet<>();
    private final String UNRECOGNIZED_PROJECT_MESSAGE = "This project does not belong to this company.";
    private final static Logger LOGGER = LogManager.getLogger(ProjectsManager.class);

    public void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException, InvalidProjectStateException {
        if (!projects.contains(project)) {
            throw new ProjectNotFoundException(UNRECOGNIZED_PROJECT_MESSAGE);
        } else if (project.getTeam().getDevelopers().isEmpty()) {
            throw new NoDevelopersException("Project can't start with no developers.");
        } else if (project.getState() != ProjectState.NOT_STARTED) {
            throw new InvalidProjectStateException("This project has already started.");
        } else {
            projects.remove(project);
            project.setState(ProjectState.STARTED);
            projects.add(project);
            LOGGER.info(project.getState());
        }
    }

    public void finishProject(Project project) throws ProjectNotFoundException, InvalidProjectStateException {
        if (!projects.contains(project)) {
            throw new ProjectNotFoundException(UNRECOGNIZED_PROJECT_MESSAGE);
        } else if (project.getState() != ProjectState.STARTED) {
            String errorMessage = "";
            switch (project.getState()) {
                case NOT_STARTED:
                    errorMessage = "This project has not started yet.";
                    break;
                case FINISHED:
                    errorMessage = "This project is already finished.";
            }

            throw new InvalidProjectStateException(errorMessage);
        } else {
            projects.remove(project);
            project.setState(ProjectState.FINISHED);
            project.getApplication().deploy();
            projects.add(project);
            LOGGER.info(project.getState());
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
            throw new ProjectNotFoundException(UNRECOGNIZED_PROJECT_MESSAGE);
        } else {
            this.projects.remove(project);
            return project.getTeam();
        }
    }
}
