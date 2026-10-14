package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.writer.PdfWriter;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

import java.util.function.Supplier;

public class PdfReportExporter extends ReportExporter {

    public PdfReportExporter(Supplier<? extends ReportWriter> writeFactory) {
        super(writeFactory);
    }

}
