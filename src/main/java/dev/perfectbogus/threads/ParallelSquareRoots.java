package dev.perfectbogus.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSquareRoots {

    public static List<Double> compute(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("numbers cannot be null");
        if (numbers.isEmpty()) return new ArrayList<>();
        for (Integer n : numbers) {
            if (n < 0) throw new IllegalArgumentException("Cannot compute square root fo negative number:" + n);
        }

        List<Callable<Double>> tasks = new ArrayList<>(numbers.size());
        List<Double> results = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            tasks.add(() -> Math.sqrt(number));
        }

        final int nCpus = Math.min(numbers.size(), Runtime.getRuntime().availableProcessors());

        try (ExecutorService executor = Executors.newFixedThreadPool(nCpus)) {
            try {
                List<Future<Double>> futures = executor.invokeAll(tasks);

                for (Future<Double> f : futures) {
                    results.add(f.get());
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return results;
    }
}
