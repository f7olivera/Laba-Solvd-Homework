package com.solvd.connectionpool;

public class Client implements Runnable {
    private final ConnectionPool connectionPool;
    private final int timeout;

    public Client(ConnectionPool connectionPool, int timeout) {
        this.connectionPool = connectionPool;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionPool.connect();
            Thread.sleep(timeout);
            connectionPool.disconnect(connection);
        } catch (InterruptedException | NoConnectionsAvailableException e) {
            throw new RuntimeException(e);
        }
    }
}
