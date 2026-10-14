package dev.perfectbogus.design.patterns.creational.report.exporter.writer;

public class PdfWriter implements ReportWriter {

    private final String pageSize = "A4";

    @Override
    public void open(String reportName) {
        System.out.println("[PDF] Opening " + reportName + ".pdf (page size " + pageSize + ")");
    }

    @Override
    public void writeLine(String line) {
        System.out.println("[PDF] " + line);
    }

    @Override
    public String format() {
        return "PDF";
    }

    @Override
    public void close() {
        System.out.println("[PDF] Closed");
    }
}
