package com.solvd.itcompany.enums;

public enum CompanyType {
    SOLE_PROPRIETORSHIP("SP"),
    CORPORATION("Corp"),
    LIMITED_LIABILITY_COMPANY("LLC"),
    COOPERATIVE("co-op");

    private String abbreviation;

    CompanyType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
