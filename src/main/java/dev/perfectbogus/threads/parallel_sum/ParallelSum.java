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

    public static void miniMaxSum(List<Integer> arr) {
        if (arr == null) throw new IllegalArgumentException("arr cannot be null");
        if (arr.isEmpty()) throw new IllegalArgumentException("arr cannot be empty");

        // Write your code here
        long all = arr.stream().mapToLong(Integer::intValue).sum();
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (long current : arr) {
            long diff = all - current;
            if (diff < min) {
                min = diff;
            }
            if (diff > max) {
                max = diff;
            }
        }
        System.out.println(min + " " + max);
    }

    public static int count(List<Integer> arr) {
        if (arr == null || arr.size() <= 1) return 0;

        int count = 0;
        double runningSum = arr.get(0);

        for (int i = 1; i < arr.size(); i++) {
            double avg = runningSum / i;
            if (arr.get(i) > avg) count++;
            runningSum += arr.get(i);
        }
        return count;
    }

}
