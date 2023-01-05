package com.solvd.connectionpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientsRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConnectionPool connectionPool = new ConnectionPool();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture<Client> completable1 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 1000), executorService);
        CompletableFuture<Client> completable2 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 500), executorService);
        CompletableFuture<Client> completable3 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 1500), executorService);
        CompletableFuture<Client> completable4 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 2000), executorService);
        CompletableFuture<Client> completable5 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 500), executorService);
        CompletableFuture<Client> completable6 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 750), executorService);
        CompletableFuture<Client> completable7 = CompletableFuture.supplyAsync(() -> runClient(connectionPool, 750), executorService);

        completable1.get();
        completable2.get();
        completable3.get();
        completable4.get();
        completable5.get();
        completable6.get();
        completable7.get();

        executorService.shutdown();
    }

    public static Client runClient(ConnectionPool connectionPool, int timeout) {
        Client client = new Client(connectionPool, timeout);
        client.run();
        return client;
    }
}
