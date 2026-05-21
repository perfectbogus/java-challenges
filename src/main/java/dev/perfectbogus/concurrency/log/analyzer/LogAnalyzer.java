package dev.perfectbogus.concurrency.log.analyzer;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private static final int THREADS = 4;

    private final ConcurrentHashMap<String, Long> levelCounts = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> ipCounts = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<String>> errorsByIp = new ConcurrentHashMap<>();

    public void analyze(List<LogEntry> logs) throws InterruptedException, ExecutionException {
        if (logs == null) throw new IllegalArgumentException("Logs cannot be null");
        if (logs.isEmpty()) return;

        List<Future<Map<String, Long>>> futures = new ArrayList<>();
        int chunkSize = Math.max(1, logs.size() / THREADS);

        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS)) {
            for (int i = 0; i < THREADS; i++) {
                int start = i * chunkSize;
                int end = (i == THREADS - 1) ? logs.size() : start + chunkSize;
                List<LogEntry> chunk = logs.subList(start, end);
                futures.add(executor.submit(() -> analyzeChunk(chunk)));
            }

            for (Future<Map<String, Long>> future : futures) {
                Map<String, Long> partial = future.get();
                partial.forEach((level, count) -> levelCounts.merge(level, count, Long::sum));
            }
        }
    }

    private Map<String, Long> analyzeChunk(List<LogEntry> chunk) {
        Map<String, Long> localCounts = new HashMap<>();
        for (LogEntry entry : chunk) {
            localCounts.merge(entry.getLevel(), 1L, Long::sum);
            ipCounts.merge(entry.getIp(), 1L, Long::sum);
            if ("ERROR".equals(entry.getLevel())) {
                errorsByIp.computeIfAbsent(
                        entry.getIp(),
                        k -> Collections.synchronizedList(new ArrayList<>())
                ).add(entry.getMessage());
            }
        }
        return localCounts;
    }

    public long getLevelCount(String level) {
        if (level == null) throw new IllegalArgumentException("Level cannot be null");
        return levelCounts.getOrDefault(level, 0L);
    }

    public List<String> getTopIPs(int n) {
        if (n < 1) throw new IllegalArgumentException("n cannot be negative");
        return ipCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> getErrorsForIp(String ip) {
        if (ip == null) throw new IllegalArgumentException("IP cannot be null");
        return errorsByIp.getOrDefault(ip, new ArrayList<>());
    }

    public Map<String, Long> getLevelSummary() {
        return Map.copyOf(levelCounts);
    }

    public String getMostFrequentLevel() {
        return levelCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("NONE");
    }

    public long getTotalEntries() {
        Long total = levelCounts.reduceValues(1, Long::sum);
        return total == null ? 0L : total;
    }
}
