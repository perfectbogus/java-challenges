package dev.perfectbogus.design.patterns.creational.factory.method.exporter.report;

import dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer.ReportWriter;

import java.util.function.Supplier;

public class CsvReportExporter extends ReportExporter {

    public CsvReportExporter(Supplier<? extends ReportWriter> writerFactory) {
        super(writerFactory);
    }
}
