package dev.perfectbogus.threads.rate_limiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {

    private static final int TASK_PER_SECOND = 3;
    private static final int TOTAL_TASKS = 9;

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();

        Queue<Runnable> taskQueue = new LinkedList<>();
        for (int i = 1; i <= TOTAL_TASKS; i++) {
            final int id = i;
            taskQueue.add(() -> processTask(id, startTime));
        }

        AtomicInteger tokens = new AtomicInteger();

        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
             ExecutorService executor = Executors.newFixedThreadPool(TASK_PER_SECOND)) {

            scheduler.scheduleAtFixedRate(
                    () -> {
                        tokens.set(TASK_PER_SECOND);
                        System.out.println("Tokens refilled!");
                    },
                    1,
                    1,
                    TimeUnit.SECONDS
            );

            while (!taskQueue.isEmpty()) {
                boolean acquired = tokens.getAndUpdate(
                        t -> t > 0 ? t - 1 : 0
                ) > 0;

                if (acquired) {
                    Runnable task = taskQueue.poll();
                    executor.submit(task);
                } else {
                    Thread.sleep(100);
                }
            }

            scheduler.shutdown();
            executor.shutdown();

            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("Executor did not terminate in time!");
                executor.shutdown();
            }

            System.out.println("All tasks completed!");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static void processTask(int taskId, long startTime) {
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.printf("t=%4dms Task-%d executed by %s%n", elapsed, taskId, Thread.currentThread().getName());
    }
}
