package dev.perfectbogus.virtual.threads;

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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Starts task on a new virtual thread using the most direct API
    // available for this, and returns the thread.
    public static Thread startVirtualThreadQuick(Runnable task) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns whether thread is a virtual thread.
    public static boolean isThreadVirtual(Thread thread) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Creates and starts a new virtual thread running task, with its name
    // set to name, then returns it.
    public static Thread createNamedVirtualThread(String name, Runnable task) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Blocks the calling thread until thread has finished running.
    public static void waitForCompletion(Thread thread) throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Runs every task in tasks, each on its own virtual thread, and
    // returns only once all of them have finished.
    public static void runAllTasks(List<Runnable> tasks) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Runs task on a virtual thread and returns its result.
    public static <T> T submitAndGetResult(Callable<T> task) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Runs every task in tasks, each on its own virtual thread, and
    // returns their results as a list, in the same order as tasks.
    public static List<String> submitAllAndCollectResults(List<Callable<String>> tasks) throws Exception {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns the unique identifier of thread.
    public static long getThreadId(Thread thread) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}