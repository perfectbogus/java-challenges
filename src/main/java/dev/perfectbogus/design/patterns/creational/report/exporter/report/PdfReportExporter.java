package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.writer.PdfWriter;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

public class PdfReportExporter extends ReportExporter {
    @Override
    protected ReportWriter createWriter() {
        return new PdfWriter();
    }
}
