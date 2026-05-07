package dev.perfectbogus.threads.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerMain {

    private static final int POISON_PILL = -1;
    private static final int CAPACITY = 5;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(CAPACITY);
        Thread pThread = new Thread(new Producer(queue, POISON_PILL));
        Thread cThread = new Thread(new Consumer(queue, POISON_PILL));

        pThread.start();
        cThread.start();

        for (Thread t : new Thread[]{pThread, cThread}) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Done!");
    }
}
