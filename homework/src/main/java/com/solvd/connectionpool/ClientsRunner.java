package com.solvd.connectionpool;

import java.util.concurrent.*;

public class ClientsRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConnectionPool connectionPool = new ConnectionPool(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Void> completable1 = CompletableFuture.runAsync(() -> new Client(connectionPool, 1500).run(), executorService);
        Future<Void> completable2 = CompletableFuture.runAsync(() -> new Client(connectionPool, 1000).run(), executorService);
        Future<Void> completable3 = CompletableFuture.runAsync(() -> new Client(connectionPool, 2500).run(), executorService);
        Future<Void> completable4 = CompletableFuture.runAsync(() -> new Client(connectionPool, 2500).run(), executorService);
        Future<Void> completable5 = CompletableFuture.runAsync(() -> new Client(connectionPool, 2500).run(), executorService);
        Future<Void> completable6 = CompletableFuture.runAsync(() -> new Client(connectionPool, 1000).run(), executorService);
        Future<Void> completable7 = CompletableFuture.runAsync(() -> new Client(connectionPool, 1000).run(), executorService);

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
