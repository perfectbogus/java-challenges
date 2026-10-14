package dev.perfectbogus.design.patterns.creational.report.exporter.report;

import dev.perfectbogus.design.patterns.creational.report.exporter.Sale;
import dev.perfectbogus.design.patterns.creational.report.exporter.writer.ReportWriter;

import java.util.List;

public abstract class ReportExporter {

    public final void export(List<Sale> sales, String reportName) {
        if (sales == null || sales.isEmpty()) throw new IllegalArgumentException("No data to export");

        try (ReportWriter writer = createWriter()) {
            writer.open(reportName);
            for (Sale s : sales) writer.writeLine(s.toString());
            System.out.println("Report exported as " + writer.format());
        }
    }

    protected abstract ReportWriter createWriter();
}
