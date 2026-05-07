package dev.perfectbogus.threads.producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int poisonPill;

    public Producer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(i);
                System.out.println("Producer: " + i + " produced");
            }
            queue.put(poisonPill);
            System.out.println("Producer sent poison pill");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}