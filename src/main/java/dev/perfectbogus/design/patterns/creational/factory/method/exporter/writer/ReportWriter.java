package dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer;

public interface ReportWriter extends AutoCloseable {
    void open(String reportName);
    void writeLine(String line);
    String format();

    @Override
    void close();
}
