package dev.perfectbogus.threads;

public class ParallelPrinter {

    public static void main(String[] args) {
        final int nThreads = 3;
        Thread[] threads = new Thread[nThreads];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(ParallelPrinter::printName);
            threads[i].setName("Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + thread.getName());
            }
        }
        System.out.println("All threads finished!");
    }

    public static void printName() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
