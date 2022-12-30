package com.solvd.itcompany.interfaces;

import com.solvd.itcompany.company.Project;

import java.util.Set;

public interface ProjectFilter {
    Set<Project> filter(Set<Project> projects, Class<?> criteria);
}
