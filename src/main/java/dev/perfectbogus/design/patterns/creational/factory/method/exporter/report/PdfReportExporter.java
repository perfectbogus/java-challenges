package dev.perfectbogus.design.patterns.creational.factory.method.exporter.report;

import dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer.ReportWriter;

import java.util.function.Supplier;

public class PdfReportExporter extends ReportExporter {

    public PdfReportExporter(Supplier<? extends ReportWriter> writeFactory) {
        super(writeFactory);
    }

}
