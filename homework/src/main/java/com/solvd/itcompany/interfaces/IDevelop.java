package com.solvd.itcompany.interfaces;


import com.solvd.itcompany.company.Project;
import com.solvd.itcompany.exceptions.InvalidProjectStateException;
import com.solvd.itcompany.exceptions.NoDevelopersException;
import com.solvd.itcompany.exceptions.ProjectNotFoundException;

public interface IDevelop {
    void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException, InvalidProjectStateException;

    void finishProject(Project project) throws ProjectNotFoundException, InvalidProjectStateException;
}
