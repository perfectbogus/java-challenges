package dev.perfectbogus.threads;

/**
 * Not atomic!
 */
public class Counter {

    private int count = 0;

    public void increment() {
        count++;
    }
}
