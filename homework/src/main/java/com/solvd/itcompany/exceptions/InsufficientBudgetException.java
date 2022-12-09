package com.solvd.itcompany.exceptions;

public class InsufficientBudgetException extends Exception {
    public InsufficientBudgetException(String errorMessage) {
        super(errorMessage);
    }

    public InsufficientBudgetException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
