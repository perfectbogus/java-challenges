package dev.perfectbogus.threads.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerMain {

    private static final int POISON_PILL = -1;
    private static final int CAPACITY = 5;

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("Producer: " + i + " produced");
                }
                queue.put(POISON_PILL);
                System.out.println("Producer sent poison pill");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int i = queue.take();
                    System.out.println("Consumed: " + i);
                    if (i == POISON_PILL) {
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

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(CAPACITY);
        Thread pThread = new Thread(new Producer(queue));
        Thread cThread = new Thread(new Consumer(queue));

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
