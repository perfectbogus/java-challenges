package dev.perfectbogus.threads.rate_limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RateLimiter {

    private static final int TASK_PER_SECOND = 3;
    private static final int TOTAL_TASKS = 9;

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(TOTAL_TASKS);
    }

    static void processTask(int taskId) {

        System.out.println("Task " + taskId + " executed at " + System.currentTimeMillis()
         + " by " + Thread.currentThread().getName());
    }
}
