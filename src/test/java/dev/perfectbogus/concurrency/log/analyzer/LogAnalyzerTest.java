package dev.perfectbogus.concurrency.log.analyzer;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class LogAnalyzerTest {

    @Test
    void testBasicLevelCounting() throws InterruptedException, ExecutionException {
        LogEntry e1 = new LogEntry("2024-01-15 10:00:00", "ERROR", "192.168.1.1", "DB error");
        LogEntry e2 = new LogEntry("2024-01-15 10:00:01", "INFO",  "192.168.1.2", "OK");
        LogEntry e3 = new LogEntry("2024-01-15 10:00:02", "ERROR", "192.168.1.1", "Timeout");
        LogAnalyzer analyzer = new LogAnalyzer();

        analyzer.analyze(List.of(e1, e2, e3));

        assertEquals(2, analyzer.getLevelCount("ERROR"));
        assertEquals(1, analyzer.getLevelCount("INFO"));
        assertEquals(0, analyzer.getLevelCount("WARN"));
    }

    @Test
    void testGetTopIPs() throws ExecutionException, InterruptedException {
        List<LogEntry> errors = logEntryListFactory();
        LogAnalyzer analyzer = new LogAnalyzer();

        analyzer.analyze(errors);

        List<String> result = analyzer.getTopIPs(1);
        assertEquals(1, result.size());
        assertEquals("192.168.1.1", result.get(0));
    }

    @Test
    void testGetErrorsForIp() throws ExecutionException, InterruptedException{
        List<LogEntry> errors = logEntryListFactory();
        LogAnalyzer analyzer = new LogAnalyzer();

        analyzer.analyze(errors);

        List<String> results = analyzer.getErrorsForIp("192.168.1.1");
        assertEquals(2, results.size());
    }


    @Test
    void testGetLevelSummary() throws ExecutionException, InterruptedException {
        List<LogEntry> entries = logEntryListFactory();
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyze(entries);

        Map<String, Long> result = analyzer.getLevelSummary();
        assertEquals(2, result.size());
        assertEquals(2, result.get("ERROR"));
        assertEquals(1, result.get("INFO"));

        String most = analyzer.getMostFrequentLevel();
        assertEquals("ERROR", most);

        Long total = analyzer.getTotalEntries();
        assertEquals(3L, total);
    }





    private List<LogEntry> logEntryListFactory() {
        LogEntry e1 = new LogEntry("2024-01-15 10:00:00", "ERROR", "192.168.1.1", "DB error");
        LogEntry e2 = new LogEntry("2024-01-15 10:00:01", "INFO",  "192.168.1.2", "OK");
        LogEntry e3 = new LogEntry("2024-01-15 10:00:02", "ERROR", "192.168.1.1", "Timeout");
        return List.of(e1, e2, e3);
    }

}