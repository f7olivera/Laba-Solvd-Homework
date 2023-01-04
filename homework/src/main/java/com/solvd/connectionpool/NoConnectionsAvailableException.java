package com.solvd.connectionpool;

public class NoConnectionsAvailableException extends Exception {
    public NoConnectionsAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
