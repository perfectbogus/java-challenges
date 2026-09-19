package dev.perfectbogus.virtual.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class VirtualThreadsIntermediateChallenge {

    // CHALLENGE 1
    // Runs one virtual thread per number in numbers, each adding that
    // number to a running total, and returns the total once every thread
    // has finished.
    public static int sumConcurrently(List<Integer> numbers) {
        LongAdder i = new LongAdder();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            numbers.forEach(n -> executor.submit(() -> i.add(n)));
        }
        return i.intValue();
    }

    // CHALLENGE 2
    // Starts numberOfTasks virtual threads, each recording its own thread
    // id, waits for all of them to finish, and returns the set of ids
    // that were recorded.
    public static Set<Long> collectDistinctThreadIds(int numberOfTasks) {
        Set<Long> set = new ConcurrentSkipListSet<>();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < numberOfTasks; i++) {
                executor.submit(() -> set.add(Thread.currentThread().threadId()));
            }
        }
        return set;
    }

    // CHALLENGE 3
    // Runs failingTask on a virtual thread. If it throws an exception,
    // returns the message of that underlying exception (not the wrapper
    // exception raised while waiting for the result).
    public static String getTaskExceptionMessage(Callable<?> failingTask) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?> f = executor.submit(failingTask);
            try {
                f.get();
                return "";
            } catch (ExecutionException  e) {
                return e.getCause().getMessage();
            } catch (InterruptedException e) {
                return e.getMessage();
            }
        }
    }

    // CHALLENGE 4
    // Runs two virtual threads concurrently. The first sets threadLocal
    // to firstValue and then records what threadLocal.get() returns; the
    // second does the same with secondValue. Waits for both to finish and
    // returns the two recorded values, in the order [firstValue's thread
    // result, secondValue's thread result], demonstrating that each
    // virtual thread sees its own independent value.
    public static List<Integer> runWithThreadLocalIsolated(ThreadLocal<Integer> threadLocal, int firstValue, int secondValue) {
        try {
            int[] a = new int[2];
            List<Integer> results = new ArrayList<>();
            Thread t1 = Thread.ofVirtual().unstarted(() -> {
                threadLocal.set(firstValue);
                a[0] = threadLocal.get();
            });
            Thread t2 = Thread.ofVirtual().unstarted(() -> {
                        threadLocal.set(secondValue);
                        a[1] = threadLocal.get();
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            for (int i : a) {
                results.add(i);
            }

            return results;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return List.of();
        }
    }

    // CHALLENGE 5
    // Returns whether a newly created virtual thread is a daemon thread
    // by default.
    public static boolean isVirtualThreadDaemonByDefault() {
        return Thread.ofVirtual().start(() -> {}).isDaemon();
    }

    // CHALLENGE 6
    // Runs every task in tasks, each on its own virtual thread, waits for
    // all of them to complete, and returns the sum of their results.
    public static int sumViaInvokeAll(List<Callable<Integer>> tasks) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Integer>> futures = executor.invokeAll(tasks);
            int sum = 0;
            for (Future<Integer> f : futures) {
                sum += f.get();
            }
            return sum;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    // CHALLENGE 7
    // Runs every task in tasks concurrently, each on its own virtual
    // thread, and returns the result of whichever one completes
    // successfully first.
    public static String firstSuccessfulResult(List<Callable<String>> tasks) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            return executor.invokeAny(tasks);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // CHALLENGE 8
    // Waits for every thread in threads to finish, then returns true if
    // none of them are still alive afterward, false otherwise.
    public static boolean joinAllAndConfirmFinished(List<Thread> threads) throws InterruptedException {
        for (Thread t : threads) t.join();
        for (Thread t : threads) {
            if (t.isAlive()) {
                return false;
            }
        }
        return true;
    }

    // CHALLENGE 9
    // Runs a task on a virtual-thread-per-task executor that checks
    // whether it is executing on a virtual thread, waits for the result,
    // and returns it.
    public static boolean confirmExecutorUsesVirtualThreads() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<Boolean> result = executor.submit(() -> Thread.currentThread().isVirtual());
            return result.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // CHALLENGE 10
    // Runs every task in namedTasks concurrently, each on its own virtual
    // thread, and returns a map from each task's name to its result,
    // once every task has completed.
    public static Map<String, Integer> collectNamedResults(Map<String, Callable<Integer>> namedTasks) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}