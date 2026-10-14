package dev.perfectbogus.design.patterns.creational.factory.method.exporter;

import dev.perfectbogus.design.patterns.creational.factory.method.exporter.report.CsvReportExporter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.report.JsonReportExporter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.report.PdfReportExporter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.report.ReportExporter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer.CsvWriter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer.JsonWriter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer.PdfWriter;
import dev.perfectbogus.design.patterns.creational.factory.method.exporter.writer.ReportWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ReportMain {

    public static void main(String[] args) {
        List<Sale> sales = List.of(new Sale(100.0), new Sale(250.5), new Sale(75.25));

        List<ReportExporter> exporters = List.of(
                new PdfReportExporter(PdfWriter::new),
                new CsvReportExporter(CsvWriter::new),
                new JsonReportExporter(JsonWriter::new)
        );

        for (ReportExporter exporter : exporters) {
            exporter.export(sales, "sales-q3");
            System.out.println("---");
        }

        final String userChoice = "PDF";
        Map<String, Supplier<ReportWriter>> registry = new HashMap<>();
        registry.put("PDF", PdfWriter::new);
        registry.put("CSV", CsvWriter::new);
        registry.put("JSON", JsonWriter::new);

        Supplier<ReportWriter> factory = registry.get(userChoice);
        if (factory == null) throw new IllegalArgumentException("Unsupported format: " + userChoice);
        new ReportExporter(factory).export(sales, "sales-q3");
    }
}
