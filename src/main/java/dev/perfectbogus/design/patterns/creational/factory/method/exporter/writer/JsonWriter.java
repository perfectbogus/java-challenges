package dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer;

public class JsonWriter implements ReportWriter {

    @Override
    public void open(String reportName) {
        System.out.println("[JSON] Opening " + reportName + ".json");
    }

    @Override
    public void writeLine(String line) {
        System.out.println("[JSON] " + line);
    }

    @Override
    public String format() {
        return "JSON";
    }

    @Override
    public void close() {
        System.out.println("[JSON] Closed");
    }

}
