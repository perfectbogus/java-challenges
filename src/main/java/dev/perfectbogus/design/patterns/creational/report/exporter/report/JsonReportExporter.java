package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.writer.JsonWriter;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

import java.util.function.Supplier;

public class JsonReportExporter extends ReportExporter {

    public JsonReportExporter(Supplier<? extends ReportWriter> writerFactory) {
        super(writerFactory);
    }
}
