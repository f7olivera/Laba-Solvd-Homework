package com.solvd.itcompany.company;

import com.solvd.itcompany.applications.Application;
import com.solvd.itcompany.applications.Website;
import com.solvd.itcompany.enums.ProjectState;
import com.solvd.itcompany.exceptions.InvalidProjectStateException;
import com.solvd.itcompany.exceptions.NoDevelopersException;
import com.solvd.itcompany.exceptions.ProjectNotFoundException;
import com.solvd.itcompany.interfaces.IDevelop;
import com.solvd.itcompany.interfaces.ProjectFilter;
import com.solvd.itcompany.interfaces.TriPredicate;
import com.solvd.itcompany.people.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.solvd.itcompany.company.Project.SECONDS_IN_DAY;
import static com.solvd.itcompany.company.Project.timestamp2date;

public final class ProjectsManager implements IDevelop {
    private HashSet<Project> projects = new HashSet<>();
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

    public HashSet<Project> getUpcomingProjects() {
        LOGGER.info("Finding all website projects with a deadline within the next 10 days and a cost of at least $10.000.");
        long timestampTenDays = Instant.now().getEpochSecond() + 10 * SECONDS_IN_DAY;
        TriPredicate<Class<? extends Application>, Quotation, Long> projectPredicate =
                (appClass, quotation, deadline) ->
                        appClass == Website.class && quotation.getTotal() > 10000 && deadline < timestampTenDays;

        return (HashSet<Project>) projects
                .stream()
                .filter(project ->
                        projectPredicate.test(project.getApplication().getClass(),
                                project.getQuotation(),
                                project.getDeadline()))
                .collect(Collectors.toSet());
    }

    public void delayProjects(Class<? extends Application> appClass, int days) {
        getProjects(appClass)
                .stream()
                .forEach(project -> LOGGER.info(project.getApplication().getName() + " project deadline: " + timestamp2date(project.getDeadline())));

        LOGGER.info(String.format("Delaying all %s projects by %d day%s.", appClass.getSimpleName(), days, days > 1 ? "s" : ""));
        processProjects(
                (project) -> project.getApplication().getClass() == appClass,
                (project -> {
                    project.setDeadline(project.getDeadline() + (long) SECONDS_IN_DAY * days);
                    return project;
                })
        );
    }

    public void processProjects(Predicate<Project> tester, Function<Project, Project> function) {
        setProjects(
                (HashSet<Project>) projects.stream()
                        .map((project -> tester.test(project) ? function.apply(project) : project))
                        .collect(Collectors.toSet())
        );
    }

    /*
     * Getters and setters
     */
    public HashSet<Project> getProjects(Class<? extends Application> appClass) {
        LOGGER.info(String.format("Getting all %s projects.", appClass.getSimpleName()));
        ProjectFilter projectFilter =
                (projects, appType) ->
                        projects.stream()
                                .filter(project -> project.getApplication().getClass() == appType)
                                .collect(Collectors.toSet());
        return (HashSet<Project>) projectFilter.filter(projects, appClass);
    }

    public HashSet<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(HashSet<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
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
