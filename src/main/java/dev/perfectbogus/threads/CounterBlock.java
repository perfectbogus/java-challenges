package dev.perfectbogus.threads;

public class CounterBlock {

    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}
