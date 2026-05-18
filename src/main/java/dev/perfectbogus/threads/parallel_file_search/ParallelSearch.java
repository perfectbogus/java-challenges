package dev.perfectbogus.threads.parallel_file_search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class ParallelSearch {

    public static List<String> search(List<String> files, String word) {
        if (files == null) throw new IllegalArgumentException("files cannot be null");
        if (files.isEmpty()) return new ArrayList<>();
        if (word == null) throw new IllegalArgumentException("word cannot be null");

        List<Callable<Optional<String>>> tasks = new ArrayList<>(files.size());
        for (String file : files) {
            if (file == null) throw new IllegalArgumentException("Files cannot contains null");
            tasks.add(() -> {
                String pattern = "\\b" + word + "\\b";
                boolean matches = file.matches(".*" + pattern + ".*");
                return matches ? Optional.of(file) : Optional.empty();
            });
        }

        int nThreads = Math.min(files.size(), Runtime.getRuntime().availableProcessors());
        try (ExecutorService executor = Executors.newFixedThreadPool(nThreads)) {
            List<Future<Optional<String>>> futures = executor.invokeAll(tasks);
            List<String> results = new ArrayList<>(files.size());
            for (Future<Optional<String>> future : futures) {
                try {
                    Optional<String> optional = future.get();
                    optional.ifPresent(results::add);
                } catch (ExecutionException e) {
                    throw new RuntimeException("Search task failed", e.getCause());
                }
            }
            return results;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread Interrupted");
        }
    }
}
