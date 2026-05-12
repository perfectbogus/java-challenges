package dev.perfectbogus.threads.rate_limiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {

    private static final int TASK_PER_SECOND = 3;
    private static final int TOTAL_TASKS = 9;

    public static void main(String[] args) {
        Queue<Runnable> taskQueue = new LinkedList<>();
        AtomicInteger tokens = new AtomicInteger(TASK_PER_SECOND);

        for (int i = 1; i <= TOTAL_TASKS; i++) {
            final int id = i;
            taskQueue.add(() -> processTask(id));
        }

        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
            scheduler.scheduleAtFixedRate(
                    () -> tokens.set(TASK_PER_SECOND),
                    1,
                    1,
                    TimeUnit.SECONDS
            );

            scheduler.shutdown();
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static void processTask(int taskId) {

        System.out.println("Task " + taskId + " executed at " + System.currentTimeMillis()
         + " by " + Thread.currentThread().getName());
    }
}
