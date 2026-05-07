package dev.perfectbogus.threads.producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int poisonPill;

    public Consumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int i = queue.take();
                System.out.println("Consumed: " + i);
                if (i == poisonPill) {
                    System.out.println("Consumer received poison pill - stopping");
                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        };
        System.out.println("Consumer exit!");
    }
}
