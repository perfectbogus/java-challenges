package dev.perfectbogus.design.patterns.behavioral.template.report;

// Task 6b — Validates that data is not blank before generating the report.
// Reuses the same CSV data/format/header/footer logic as CsvReportGenerator.
public class NonEmptyReportGenerator extends ValidatedReportGenerator {

    // TODO: private fields for title and the raw data string to return from fetchData
    private final String title;
    private final String rawData;

    // TODO: constructor accepting String title and String data
    public NonEmptyReportGenerator(String title, String data) {
        this.title = title;
        this.rawData = data;
    }

    // TODO: validate(String data) → return true if data is not blank, false otherwise
    @Override
    public boolean validate(String data) {
        return !data.isBlank();
    }

    @Override
    protected String fetchData() {
        return rawData;
    }

    @Override
    protected String formatData(String data) {
        return data;
    }

    @Override
    protected String buildHeader() {
        return "=== " + title + " ===";
    }

    @Override
    protected String buildFooter() {
        return "--- end of report ---";
    }

    // TODO: same fetchData, formatData, buildHeader, buildFooter as CsvReportGenerator
    //       but fetchData() returns the data passed in the constructor, not a hardcoded string

}
