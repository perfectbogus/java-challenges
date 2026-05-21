package dev.perfectbogus.concurrency.log.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogGenerator {

    private static final String[] LEVELS = {"INFO", "WARN", "ERROR", "DEBUG"};
    private static final String[] IPS = {
            "192.168.1.1", "192.168.1.2", "10.0.0.1", "10.0.0.2", "172.16.0.1"
    };
    private static final String[] MESSAGES = {
            "Request processed",
            "Connection timeout",
            "Database error",
            "Cache miss",
            "Authentication failed"
    };

    public static List<LogEntry> generate(int count) {
        Random rand = new Random();

        List<LogEntry> logs = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            logs.add(new LogEntry(
                    "2024-01-15 " + String.format("%02d:%02d:%02d", rand.nextInt(24),
                            rand.nextInt(60),
                            rand.nextInt(60)),
                    LEVELS[rand.nextInt(LEVELS.length)],
                    IPS[rand.nextInt(IPS.length)],
                    MESSAGES[rand.nextInt(MESSAGES.length)]
            ));
        }
        return logs;
    }

}
