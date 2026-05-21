package dev.perfectbogus.concurrency.log.analyzer;

public class LogEntry {

    private final String timestamp;
    private final String level;
    private final String ip;
    private final String message;

    public LogEntry(String timestamp, String level, String ip, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.ip = ip;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getIp() {
        return ip;
    }

    public String getMessage() {
        return message;
    }
}
