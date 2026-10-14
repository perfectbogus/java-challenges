package dev.perfectbogus.design.patterns.creational.report.exporter.writer;

public class CsvWriter implements ReportWriter {

    private final String delimiter = ",";

    @Override
    public void open(String reportName) {
        System.out.println("[CSV] Opening " + reportName + ".csv (delimiter '" + delimiter + "')");
    }

    @Override
    public void writeLine(String line) {
        System.out.println("[CSV] " + line);
    }

    @Override
    public String format() {
        return "CSV";
    }

    @Override
    public void close() {
        System.out.println("[CSV] Closed");
    }
}
