package com.solvd.connectionpool;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private final ConcurrentLinkedQueue<Connection> connections = new ConcurrentLinkedQueue<>();
    private final int CONNECTIONS_LIMIT = 5;
    private int availableConnections = CONNECTIONS_LIMIT;

    public synchronized Connection connect() throws NoConnectionsAvailableException {
        if (availableConnections == 0)
            throw new NoConnectionsAvailableException("No connections available.");

        availableConnections--;

        Connection connection;
        if (connections.isEmpty())
            connection = new Connection(CONNECTIONS_LIMIT - availableConnections);
        else {
            connection = connections.iterator().next();
            connections.remove(connection);
        }

        System.out.println(String.format("Connecting client (%s)", connection));

        return connection;
    }

    public synchronized void disconnect(Connection connection) {
        System.out.println(String.format("Disconnecting client (%s)", connection));

        availableConnections++;
        connections.add(connection);
    }
}
