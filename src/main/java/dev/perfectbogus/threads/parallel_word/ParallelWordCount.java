package dev.perfectbogus.threads.parallel_word;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelWordCount {

    public static long count(List<String> sentences) {
        if (sentences == null) throw new IllegalArgumentException("Sentences cannot be null");
        if (sentences.isEmpty()) return 0L;

        final int nThreads = Math.min(sentences.size(), Runtime.getRuntime().availableProcessors());

        List<Callable<Long>> tasks = new ArrayList<>(sentences.size());
        for (String sentence : sentences) {
            if (sentence == null) { throw new IllegalArgumentException("Sentences list cannot contains null elements"); }
            tasks.add(() -> sentence.isBlank() ? 0L: (long) sentence.trim().split("\\s+").length);
        }


        try (ExecutorService es = Executors.newFixedThreadPool(nThreads)) {
            List<Future<Long>> futures = es.invokeAll(tasks);

            long total = 0L;
            for (Future<Long> future : futures) {
                try {
                    total += future.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException("Word count tasks failed", e.getCause());
                }
            }

            return total;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("word count interrupted", e);
        }
    }
}
