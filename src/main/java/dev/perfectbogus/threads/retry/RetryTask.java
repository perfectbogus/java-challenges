package dev.perfectbogus.threads.retry;

import java.util.Random;
import java.util.concurrent.*;

public class RetryTask {

    private static Random rand = new Random();

    public static String executeWithRetry(int maxRetries) {
        if (maxRetries <= 0) throw new IllegalArgumentException("maxRetries must be positive, got: " + maxRetries);

        try (ExecutorService executor = Executors.newSingleThreadExecutor()){
            for (int i = 0; i < maxRetries; i++) {
                Future<String> future = executor.submit(() -> {
                    if (rand.nextInt(100)  < 70) {
                        throw new RuntimeException("Task failed randomly");
                    }
                    return "success";
                });

                try {
                    return future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("thread interrupted");
                } catch (ExecutionException e) {
                    System.out.println("Attempt " + (i+1) + " failed, retrying...");
                    if (i < maxRetries) {
                        long delay = (long) Math.pow(2, i) * 100;
                        Thread.sleep(delay);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Retry loop interrupted", e);
        }
        throw new RuntimeException("Max retries reached (" + maxRetries + ")");
    }
}
