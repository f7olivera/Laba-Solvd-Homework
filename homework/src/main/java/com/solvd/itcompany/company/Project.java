package com.solvd.itcompany.company;

import com.solvd.itcompany.enums.ProjectState;
import com.solvd.itcompany.applications.Application;
import com.solvd.itcompany.people.Customer;
import com.solvd.itcompany.people.Team;

import java.util.Objects;

public class Project {
    private Application application;
    private Customer customer;
    private Team team;
    private ProjectState projectState = ProjectState.NOT_STARTED;

    public Project(Application application, Customer customer) {
        this.application = application;
        this.customer = customer;
    }

    public Project(Application application, Customer customer, Team team) {
        this.application = application;
        this.customer = customer;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Project to build a " + application.getClass().getSimpleName() +
                " for " + customer.getFullName() + "\n" +
                "Details:\n" + application.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        if (this.hashCode() != obj.hashCode()) return false;

        return application == ((Project) obj).getApplication() && customer == ((Project) obj).getCustomer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, customer);
    }

    /*
     * Getters and setters
     */
    public Application getApplication() {
        return this.application;
    }

    void setApplication(Application application) {
        this.application = application;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Team getTeam() {
        return this.team;
    }

    void setTeam(Team team) {
        this.team = team;
    }

    public ProjectState getState() {
        return this.projectState;
    }

    void setState(ProjectState projectState) {
        this.projectState = projectState;
    }
}
