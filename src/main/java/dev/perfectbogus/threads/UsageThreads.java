package dev.perfectbogus.threads;

public class UsageThreads {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        MyThread myThread = new MyThread();
        myThread.start();

        MyTask myTask = new MyTask();
        Thread t = new Thread(myTask);
        t.start();
        t.join();

        Thread t1 = new Thread(() -> {
            System.out.println("Running thread as Lambda on: " + Thread.currentThread().getName());
        });
        t1.start();
        t1.join();

        System.out.println("Thread lifecycle:");
        System.out.println("New -> Runnable -> Running -> Terminated");
        System.out.println("Running -> Blocked/Waiting/Timed_Waiting -> Runnable");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } );

        System.out.println("T2 State: " + t2.getState());
        t2.start();
        System.out.println("T2 State: " + t2.getState());
        t2.join();
        System.out.println("T2 State: " + t2.getState());

        Thread a = new Thread(() -> {});
        a.start();
        a.join();
        a.join(2000);
        a.isAlive();
        a.getName();
        a.setName("worker");
        a.getPriority();
        a.setPriority(Thread.NORM_PRIORITY);

        // Static methods
        Thread.sleep(1000);
        Thread.currentThread().getName();
        Thread.yield();
    }
}
