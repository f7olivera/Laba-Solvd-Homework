package com.solvd.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private final ConcurrentLinkedQueue<Connection> connections = new ConcurrentLinkedQueue<>();
    private final int maxConnections;
    private int availableConnections;
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    public ConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
        this.availableConnections = maxConnections;
    }

    public synchronized Connection connect() throws NoConnectionsAvailableException {
        if (availableConnections == 0)
            throw new NoConnectionsAvailableException("No connections available.");

        availableConnections--;

        Connection connection;
        if (connections.isEmpty())
            connection = new Connection(maxConnections - availableConnections);
        else {
            connection = connections.iterator().next();
            connections.remove(connection);
        }

        LOGGER.info(String.format("Connecting client (%s)", connection));

        return connection;
    }

    public synchronized void disconnect(Connection connection) {
        LOGGER.info(String.format("Disconnecting client (%s)", connection));

        availableConnections++;
        connections.add(connection);
    }
}
