package com.solvd.itcompany.company;

import com.solvd.itcompany.enums.CompanyType;

public abstract class Company {
    private String name;
    private CompanyType companyType;

    public Company(String name, CompanyType companyType) {
        this.name = name;
        this.companyType = companyType;
    }

    /*
     * Getters and setters
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public CompanyType getCompanyType() {
        return this.companyType;
    }
}
