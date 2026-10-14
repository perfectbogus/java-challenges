package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.writer.CsvWriter;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

import java.util.function.Supplier;

public class CsvReportExporter extends ReportExporter {

    public CsvReportExporter(Supplier<? extends ReportWriter> writerFactory) {
        super(writerFactory);
    }
}
