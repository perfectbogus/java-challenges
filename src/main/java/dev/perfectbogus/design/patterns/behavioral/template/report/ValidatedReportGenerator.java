package dev.perfectbogus.design.patterns.behavioral.template.report;


// Task 6a — Extends ReportGenerator with a validation step.
// Overrides generate() to validate data before producing the report.
public abstract class ValidatedReportGenerator extends ReportGenerator {

    // TODO: declare protected abstract boolean validate(String data)
    protected abstract boolean validate(String data);

    // TODO: override generate():
    //       if validate(fetchData()) returns true  → return super.generate()
    //       if validate(fetchData()) returns false → return "[ERROR] Report data failed validation"
    //       Do NOT call buildHeader, formatData, or buildFooter when validation fails.
    @Override
    public String generate() {
        if (validate(fetchData())) {
            return super.generate();
        } else {
            return "[ERROR] Report data failed validation";
        }
    }
}
