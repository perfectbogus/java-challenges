package dev.perfectbogus.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeCounterTest {

    @Test
    void testThreadSafeCounter() {
        ThreadSafeCounter counter = new ThreadSafeCounter();
        final int size = 10;
        final int times = 1000;
        Thread[] threads = new Thread[size];

        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    counter.increment();
                }
            });
        }

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        final int expected = 10_000;
        assertEquals(expected, counter.getCount());

    }

}