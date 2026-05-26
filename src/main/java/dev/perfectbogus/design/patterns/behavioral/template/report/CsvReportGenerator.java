package dev.perfectbogus.design.patterns.behavioral.template.report;

// Task 2 — CSV concrete implementation of ReportGenerator.
// Returns raw CSV data unchanged in formatData.
public class CsvReportGenerator extends ReportGenerator {

    // TODO: private field for title
    private final String title;

    // TODO: constructor accepting String title
    public CsvReportGenerator(String title) {
        this.title = title;
    }

    // TODO: fetchData() → "id,name,amount\n1,Alice,200\n2,Bob,150"
    @Override
    public String fetchData() {
        return "id,name,amount\n1,Alice,200\n2,Bob,150";
    }

    // TODO: formatData(String data) → returns data unchanged
    @Override
    public String formatData(String data) {
        return data;
    }

    // TODO: buildHeader() → "=== " + title + " ==="
    @Override
    public String buildHeader() {
        return "=== " + title + " ===";
    }

    // TODO: buildFooter() → "--- end of report ---"
    @Override
    public String buildFooter() {
        return "--- end of report ---";
    }
}
