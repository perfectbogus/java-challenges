package dev.perfectbogus.threading.counter;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterTest {

    // Test 2: increased contention
    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS)
    void highContention_shouldReachExactExpectedTotal() throws InterruptedException {
        int threadCount = 50;
        int incrementsPerThread = 100_000;
        long expected = (long) threadCount * incrementsPerThread;

        Counter counter = new Counter();
        runThreads(threadCount, () -> {
            for (int j = 0; j < incrementsPerThread; j++) {
                counter.increment();
            }
        });

        assertEquals(expected, counter.getCount());
    }

    // Test 3: uneven load per thread, expected sum computed independently
    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void unevenLoad_shouldMatchComputedExpectedSum() throws InterruptedException {
        int threadCount = 10;
        int[] incrementsPerThread = new int[threadCount];
        long expected = 0;

        for (int i = 0; i < threadCount; i++) {
            incrementsPerThread[i] = (i % 2 == 0) ? 5_000 : 20_000;
            expected += incrementsPerThread[i];
        }

        Counter counter = new Counter();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            final int times = incrementsPerThread[i];
            threads[i] = new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    counter.increment();
                }
            });
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        assertEquals(expected, counter.getCount());
    }

    // Test 1 & 5: repeated runs within/across JVM invocations to rule out timing flukes.
    // JUnit's @RepeatedTest re-runs this N times; run the whole test class multiple
    // times (e.g. via your build tool) to also cover separate-JVM runs.
    @RepeatedTest(50)
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void baselineScenario_repeatedly_shouldAlwaysBeExact() throws InterruptedException {
        int threadCount = 10;
        int incrementsPerThread = 10_000;
        long expected = (long) threadCount * incrementsPerThread;

        Counter counter = new Counter();
        runThreads(threadCount, () -> {
            for (int j = 0; j < incrementsPerThread; j++) {
                counter.increment();
            }
        });

        assertEquals(expected, counter.getCount());
    }

    private static void runThreads(int threadCount, Runnable task) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(task);
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
    }
}