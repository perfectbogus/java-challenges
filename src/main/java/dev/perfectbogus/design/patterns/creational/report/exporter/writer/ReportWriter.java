package dev.perfectbogus.design.patterns.creational.report.exporter.writer;

public interface ReportWriter extends AutoCloseable {
    void open(String reportName);
    void writeLine(String line);
    String format();

    @Override
    void close();
}
