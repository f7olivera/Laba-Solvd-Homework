package com.solvd.connectionpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientsRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConnectionPool connectionPool = new ConnectionPool();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completable1 = CompletableFuture.runAsync(() -> new Client(connectionPool, 1000).run(), executorService);
        CompletableFuture<Void> completable2 = CompletableFuture.runAsync(() -> new Client(connectionPool, 500).run(), executorService);
        CompletableFuture<Void> completable3 = CompletableFuture.runAsync(() -> new Client(connectionPool, 1500).run(), executorService);
        CompletableFuture<Void> completable4 = CompletableFuture.runAsync(() -> new Client(connectionPool, 2000).run(), executorService);
        CompletableFuture<Void> completable5 = CompletableFuture.runAsync(() -> new Client(connectionPool, 500).run(), executorService);
        CompletableFuture<Void> completable6 = CompletableFuture.runAsync(() -> new Client(connectionPool, 750).run(), executorService);
        CompletableFuture<Void> completable7 = CompletableFuture.runAsync(() -> new Client(connectionPool, 750).run(), executorService);

        completable1.get();
        completable2.get();
        completable3.get();
        completable4.get();
        completable5.get();
        completable6.get();
        completable7.get();

        executorService.shutdown();
    }
}
