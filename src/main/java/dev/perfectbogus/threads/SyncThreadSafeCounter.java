package dev.perfectbogus.threads;

public class SyncThreadSafeCounter {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized int getCount() {
        return counter;
    }
}
