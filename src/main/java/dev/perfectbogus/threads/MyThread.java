package dev.perfectbogus.threads;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Running Thread as Extends on: " + Thread.currentThread().getName());
    }
}
