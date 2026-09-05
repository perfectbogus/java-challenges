package dev.perfectbogus.threading.counter;

public class CounterChallenge {

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];
        Counter counter = new Counter();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                int incrementsPerThread = 10_000;
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println(counter.getCount());

    }

}


