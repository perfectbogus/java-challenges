package dev.perfectbogus.design.patterns.creational.prototype.document.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
    private String title;
    private final List<Section> sections;
    private final Map<String, String> metadata;
    private final byte[] logo;

    public Document(String title, List<Section> sections, Map<String,String> metadata, byte[] logo) {
        this.title = title;
        this.sections = new ArrayList<>();
        for (Section s : sections) {
            sections.add(new Section(s));
        }
        this.metadata = new HashMap<>(metadata);
        this.logo = logo.clone();
    }

    public Document(Document other) {
        this.title = other.getTitle();
        this.sections = other.getSections();
        this.metadata = other.getMetadata();
        this.logo = other.getLogo();
    }

    public byte[] getLogo() {
        return this.logo.clone();
    }

    public Map<String, String> getMetadata() {
        return new HashMap<>(metadata);
    }

    public List<Section> getSections() {
        return new ArrayList<>(sections);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
