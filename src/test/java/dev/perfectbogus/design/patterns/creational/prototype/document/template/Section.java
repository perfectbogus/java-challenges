package dev.perfectbogus.design.patterns.creational.prototype.document.template;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String heading;
    private List<String> paragraphs;
    private Style style;

    public Section(String heading, List<String> paragraphs, Style style) {
        this.heading = heading;
        this.paragraphs = new ArrayList<>(paragraphs);
        this.style = new Style(style);
    }

    public Section(Section other) {
        heading = other.getHeading();
        paragraphs = new ArrayList<>(other.getParagraphs());
        style = new Style(other.getStyle());
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }


}
