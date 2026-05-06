package dev.perfectbogus.threads;

public class SyncCounterMain {

    private static final int THREAD_COUNT = 10;
    private static final int INCREMENTS_PER_THREAD = 1000;

    public static void main(String[] args) {
        SyncThreadSafeCounter counter = new SyncThreadSafeCounter();
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    counter.increment();
                }
            });
        }

        for (Thread thread : threads ) thread.start();

        for (Thread thread : threads ) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Counter: " + counter.getCount());
    }
}
