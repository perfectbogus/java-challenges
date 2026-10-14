package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.writer.CsvWriter;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

public class CsvReportExporter extends ReportExporter {
    @Override
    protected ReportWriter createWriter() {
        return new CsvWriter();
    }
}
