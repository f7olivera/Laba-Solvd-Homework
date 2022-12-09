package interfaces;

import company.Project;
import exceptions.InvalidProjectStateException;
import exceptions.NoDevelopersException;
import exceptions.ProjectNotFoundException;

public interface IDevelop {
    void startProject(Project project) throws ProjectNotFoundException, NoDevelopersException;

    void finishProject(Project project) throws ProjectNotFoundException, InvalidProjectStateException;
}
