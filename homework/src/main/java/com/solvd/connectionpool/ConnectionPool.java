package com.solvd.connectionpool;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private final ConcurrentLinkedQueue<Connection> connections = new ConcurrentLinkedQueue<>();
    private int availableConnections = 5;

    public Connection connect() throws NoConnectionsAvailableException {
        if (availableConnections == 0)
            throw new NoConnectionsAvailableException("No connections available.");

        System.out.println("Connecting client.");

        Connection connection;
        if (connections.isEmpty())
            connection = new Connection();
        else {
            connection = connections.iterator().next();
            connections.remove(connection);
        }

        availableConnections--;
        return connection;
    }

    public void disconnect(Connection connection) {
        System.out.println("Disconnecting client.");

        connections.add(connection);
        availableConnections++;
    }
}
