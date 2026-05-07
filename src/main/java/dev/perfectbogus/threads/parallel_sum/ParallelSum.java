package dev.perfectbogus.threads.parallel_sum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelSum {

    private static final int THREADS = 4;

    public static long sum(int[] array) {
        if (array == null) throw new IllegalArgumentException("array cannot be null");
        if (array.length == 0) return 0L;

        // Avoid creating more threads than elements
        int actualThreads = Math.min(THREADS, array.length);
        int chunkSize = array.length / actualThreads;

        try (ExecutorService executor = Executors.newFixedThreadPool(actualThreads)) {

            List<Future<Long>> futures = new ArrayList<>();
            for (int i = 0; i < actualThreads; i++) {
                final int start = i * chunkSize;
                final int end = (i == actualThreads - 1) ? array.length : start + chunkSize;
                futures.add(executor.submit(() -> sumRange(array, start, end)));
            }

            long total = 0L;
            for (Future<Long> future : futures) {
                total += future.get();
            }

            return total;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        } catch (ExecutionException e) {
            throw new RuntimeException("Thread execution failed", e.getCause());
        }
    }

    static long sumRange(int[] array, int start, int end) {
        long sum = 0L;
        for (int j = start; j < end; j++) {
            sum += array[j];
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] array = IntStream.rangeClosed(1, 1000).toArray();
        Long result = sum(array);
        System.out.println(result);
    }
}
