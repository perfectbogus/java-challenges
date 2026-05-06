package dev.perfectbogus.threads;

public class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Running Thread as Implementation on: " + Thread.currentThread().getName());
    }
}
