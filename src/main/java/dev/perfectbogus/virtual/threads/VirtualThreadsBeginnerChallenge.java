package dev.perfectbogus.virtual.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadsBeginnerChallenge {

    // CHALLENGE 1
    // Creates and starts a new virtual thread running task, then returns it.
    public static Thread createStartedVirtualThread(Runnable task) {
        return Thread.ofVirtual().name("Virtual-Thread-Worker").start(task);
    }

    // CHALLENGE 2
    // Creates a new virtual thread running task, without starting it.
    public static Thread createUnstartedVirtualThread(Runnable task) {
        return Thread.ofVirtual().name("Virtual-Thread-Worker").unstarted(task);
    }

    // CHALLENGE 3
    // Starts task on a new virtual thread using the most direct API
    // available for this, and returns the thread.
    public static Thread startVirtualThreadQuick(Runnable task) {
        return Thread.startVirtualThread(task);
    }

    // CHALLENGE 4
    // Returns whether thread is a virtual thread.
    public static boolean isThreadVirtual(Thread thread) {
        return thread.isVirtual();
    }

    // CHALLENGE 5
    // Creates and starts a new virtual thread running task, with its name
    // set to name, then returns it.
    public static Thread createNamedVirtualThread(String name, Runnable task) {
        return Thread.ofVirtual().name(name).start(task);
    }

    // CHALLENGE 6
    // Blocks the calling thread until thread has finished running.
    public static void waitForCompletion(Thread thread) throws InterruptedException {
        thread.join();
    }

    // CHALLENGE 7
    // Runs every task in tasks, each on its own virtual thread, and
    // returns only once all of them have finished.
    public static void runAllTasks(List<Runnable> tasks) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            tasks.forEach(executor::submit);
        }
    }

    // CHALLENGE 8
    // Runs task on a virtual thread and returns its result.
    public static <T> T submitAndGetResult(Callable<T> task) throws Exception {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<T> f = executor.submit(task);
            return f.get();
        }
    }

    // CHALLENGE 9
    // Runs every task in tasks, each on its own virtual thread, and
    // returns their results as a list, in the same order as tasks.
    public static List<String> submitAllAndCollectResults(List<Callable<String>> tasks) throws Exception {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<String> results = new ArrayList<>();
            for (Future<String> f : executor.invokeAll(tasks)) {
                results.add(f.get());
            }
            return results;
        }
    }

    // CHALLENGE 10
    // Returns the unique identifier of thread.
    public static long getThreadId(Thread thread) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}