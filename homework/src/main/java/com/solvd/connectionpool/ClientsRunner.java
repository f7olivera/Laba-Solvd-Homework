package com.solvd.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class ClientsRunner {
    private final static Logger LOGGER = LogManager.getLogger(ClientsRunner.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LOGGER.info("Testing connection pool.");
        run();

        LOGGER.info("Testing connection pool on a separate thread.");
        runUsingThread();
    }

    public static void run() throws ExecutionException, InterruptedException {
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

    public static void runUsingThread() {
        ThreadRunner threadRunner = new ThreadRunner();
        threadRunner.start();
    }

    private static class ThreadRunner extends Thread {
        @Override
        public synchronized void start() {
            try {
                ClientsRunner.run();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
