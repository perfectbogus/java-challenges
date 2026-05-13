package dev.perfectbogus.threads;

import java.util.concurrent.*;

public class TimeoutFuture {

    private static final int TIMEOUT_SECONDS = 2;

    public static String fetchWithTimeout(int delaySeconds) {
        if (delaySeconds < 0) throw new IllegalArgumentException("Delay cannot be negative");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Future<String> task = executor.submit(() -> {
                Thread.sleep(delaySeconds * 1000L);
                return "result";
            });

            try {
                return task.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                task.cancel(true); // stop the background task
                return "default";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Task was interrupted", e);
            } catch (ExecutionException e) {
                throw new RuntimeException("Task failed", e.getCause());
            }
        }
    }

    public static void main(String[] args) {
        String s = fetchWithTimeout(1);
        System.out.println(s);
        String def = fetchWithTimeout(5);
        System.out.println(def);
    }
}
