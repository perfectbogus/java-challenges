package dev.perfectbogus.threads;

import java.util.concurrent.*;

public class ManageThread {

    /**
     * Types of thread pools:
     *      Fixed: newFixedThreadPool - good for CPU-bound Tasks
     *      Cached: newCachedThreadPool - grows as needed, good for short tasks
     *      Single: newSingleThreadExecutor - one thread, tasks run sequentially
     *      Scheduled: newScheduledThreadPool - run tasks after delay or periodically
     */
    public static void main(String[] args) {
        // 6: Executor Service - The Right Way to Manage Threads
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {

            executor.submit(() -> System.out.println("Task 1"));
            executor.submit(() -> System.out.println("Task 2"));
            executor.submit(() -> System.out.println("Task 3"));

            executor.shutdown();
            executor.shutdownNow();
        }

        // 7: Future - Getting Results from Threads
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            Future<Integer> future = executor.submit(() -> {
                Thread.sleep(1000);
                return 42;
            });

            System.out.println("Doing other work...");
            Integer resulta = future.get();
            Integer resultb = future.get(2, TimeUnit.SECONDS);

            System.out.println(resulta);
            System.out.println(resultb);

            executor.shutdown();

        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    // 8: Volatile - Visibility between threads
    public static class Flag {
        // X Thread B may never see Thread A's update
        private boolean runningNoVolatile = true;
        // volatile ensures all threads see the latest value
        private volatile boolean runningVolatile = true;

        public void stop() { runningVolatile = false; }
        public void run() { while (runningVolatile) { /* work */} }
    }

    // 9: Common Pitfalls
    //      Deadlocks - always acquire locks in the same Order
    //      call run() instead of start()
    //      Not Shutting down ExecutorService - always shut down
}
