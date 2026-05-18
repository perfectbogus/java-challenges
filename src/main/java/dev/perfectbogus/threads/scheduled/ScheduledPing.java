package dev.perfectbogus.threads.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledPing {
    private static final int MAX_PINGS = 5;
    private static final int INTERVAL_SECS = 2;
    private static final int INITIAL_DELAY = 0;
    private static final long START_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);

        try (ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor()) {
            scheduler.scheduleAtFixedRate(
                    () -> {
                        long elapsed = System.currentTimeMillis() - START_TIME;
                        int pingNumber = count.incrementAndGet();
                        System.out.printf("t=%4dms Ping #%d -> Pinging server... OK%n", elapsed, pingNumber);
                        if (count.get() >= MAX_PINGS) scheduler.shutdown();
                    },
                    INITIAL_DELAY,
                    INTERVAL_SECS,
                    TimeUnit.SECONDS
            );


            scheduler.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e.getCause());
        }
        System.out.println("All pings completed!");
    }
}
