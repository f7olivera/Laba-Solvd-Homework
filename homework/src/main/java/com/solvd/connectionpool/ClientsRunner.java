package com.solvd.connectionpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientsRunner {
    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Client client1 = new Client(connectionPool, 1000);
        Client client2 = new Client(connectionPool, 500);
        Client client3 = new Client(connectionPool, 1500);
        Client client4 = new Client(connectionPool, 2000);
        Client client5 = new Client(connectionPool, 500);
        executorService.execute(client1);
        executorService.execute(client2);
        executorService.execute(client3);
        executorService.execute(client4);
        executorService.execute(client5);

        executorService.shutdown();
    }
}
