package dev.perfectbogus.design.patterns.creational.prototype.document.template;

import java.util.*;
import java.util.stream.Collectors;

public class Document {
    private String title;
    private final List<Section> sections;
    private final Map<String, String> metadata;
    private final Logo logo;

    public Document(String title, List<Section> sections, Map<String,String> metadata, Logo logo) {
        this.title = title;
        this.sections = new ArrayList<>();
        for (Section s : sections) {
            this.sections.add(new Section(s));
        }
        this.metadata = new HashMap<>(metadata);
        this.logo = logo;
    }

    public Document(Document other) {
        this.title = other.getTitle();
        this.sections = new ArrayList<>();
        for (Section s : other.getSections()) {
            this.sections.add(new Section(s));
        }
        this.metadata = new HashMap<>(other.metadata);
        this.logo = other.getLogo();
    }

    public Logo getLogo() {
        return this.logo;
    }

    public Map<String, String> getMetadata() {
        return metadata.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public List<Section> getSections() {
        return sections.stream().toList();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
