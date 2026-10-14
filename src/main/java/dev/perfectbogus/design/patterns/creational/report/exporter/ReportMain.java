package dev.perfectbogus.design.patterns.creational.report.exporter;

import dev.perfectbogus.design.patterns.creational.report.exporter.report.CsvReportExporter;
import dev.perfectbogus.design.patterns.creational.report.exporter.report.JsonReportExporter;
import dev.perfectbogus.design.patterns.creational.report.exporter.report.PdfReportExporter;
import dev.perfectbogus.design.patterns.creational.report.exporter.report.ReportExporter;

import java.util.List;

public class ReportMain {

    public static void main(String[] args) {
        List<Sale> sales = List.of(new Sale(100.0), new Sale(250.5), new Sale(75.25));

        List<ReportExporter> exporters = List.of(
                new PdfReportExporter(),
                new CsvReportExporter(),
                new JsonReportExporter()
        );

        for (ReportExporter exporter : exporters) {
            exporter.export(sales, "sales-q3");
            System.out.println("---");
        }
    }
}
