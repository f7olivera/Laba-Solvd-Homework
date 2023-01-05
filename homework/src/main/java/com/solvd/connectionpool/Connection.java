package com.solvd.connectionpool;

public class Connection {
    private final int id;

    public Connection(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "connection #" + id;
    }
}
