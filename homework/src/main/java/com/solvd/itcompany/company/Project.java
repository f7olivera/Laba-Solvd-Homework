package com.solvd.itcompany.company;

import com.solvd.itcompany.applications.Application;
import com.solvd.itcompany.enums.ProjectState;
import com.solvd.itcompany.people.Customer;
import com.solvd.itcompany.people.Team;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Project {
    private Application application;
    private Customer customer;
    private Quotation quotation;
    private Team team;
    private ProjectState projectState = ProjectState.NOT_STARTED;
    private long deadline;
    public static final int SECONDS_IN_DAY = 86400;

    public Project(Application application, Customer customer, Quotation quotation, int deadline) {
        this.application = application;
        this.customer = customer;
        this.quotation = quotation;
        this.deadline = Instant.now().getEpochSecond() + (long) deadline * SECONDS_IN_DAY;
    }

    public Project(Application application, Customer customer, Quotation quotation, int deadline, Team team) {
        this(application, customer, quotation, deadline);
        this.team = team;
    }

    @Override
    public String toString() {
        return "Project to build a " + application.getClass().getSimpleName() +
                " for " + customer.getFullName() + "\n" +
                "Deadline: " + timestamp2date(deadline) + "\n" +
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

    public static String timestamp2date(long deadline) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(deadline, 0, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        return dateTime.format(formatter);
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

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}
