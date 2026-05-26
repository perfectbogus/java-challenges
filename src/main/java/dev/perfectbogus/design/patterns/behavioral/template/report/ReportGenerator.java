package dev.perfectbogus.design.patterns.behavioral.template.report;


// Task 1 — The abstract base class that defines the algorithm skeleton.
// generate() is the template method — it is final so no subclass can reorder the steps.
// Each abstract step is protected so only subclasses (not external callers) implement them.
public abstract class ReportGenerator {

    // TODO: declare protected abstract String fetchData()
    // TODO: declare protected abstract String formatData(String data)
    // TODO: declare protected abstract String buildHeader()
    // TODO: declare protected abstract String buildFooter()
    protected abstract String fetchData();
    protected abstract String formatData(String data);
    protected abstract String buildHeader();
    protected abstract String buildFooter();

    // Task 4 — Hook method: returns empty String by default.
    // Subclasses may override to inject content before the report.
    // TODO: declare protected String onBeforeGenerate() — returns "" by default
    protected String onBeforeGenerate() {
        return "";
    }

    // Task 1 — Template method: defines the fixed algorithm structure.
    // Order: onBeforeGenerate (if non-blank) → buildHeader → formatData(fetchData) → buildFooter
    // Each part is separated by "\n".
    // TODO: declare public final String generate()
    public String generate() {
        String on = onBeforeGenerate();
        StringBuilder sb = new StringBuilder();
        if (!on.isBlank()) {
            sb.append(on).append("\n");
        }
        sb.append(buildHeader()).append("\n");
        sb.append(formatData(fetchData())).append("\n");
        sb.append(buildFooter());
        return sb.toString();
    }
}
