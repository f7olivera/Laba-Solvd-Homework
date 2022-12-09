package com.solvd.itcompany.exceptions;

public class NoDevelopersException extends RuntimeException {
    public NoDevelopersException(String errorMessage) {
        super(errorMessage);
    }

    public NoDevelopersException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
