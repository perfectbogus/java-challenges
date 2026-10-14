package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.writer.JsonWriter;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

public class JsonReportExporter extends ReportExporter {
    @Override
    protected ReportWriter createWriter() {
        return new JsonWriter();
    }
}
