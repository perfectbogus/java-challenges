package dev.perfectbogus.design.patterns.creational.prototype.document.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainDocument {

    public static void main(String[] args) {
        Section s = new Section(
                "Payment",
                new ArrayList<>(List.of("Pay in 30 days")),
                new Style("Arial", 12)
        );

        Document template = new Document(
                "Contract",
                List.of(s),
                Map.of("dept", "Legal"),
                new Logo(new byte[2 * 1024 * 1024])
        );

        Document copy = new Document(template);
        copy.getSections().get(0).getParagraphs().set(0, "My text");
        copy.getSections().get(0).getStyle().setFontSize(18);

        System.out.println(template.getSections().get(0).getParagraphs().get(0));
        System.out.println(template.getSections().get(0).getStyle().getFontSize());
        System.out.println(copy.getLogo() == template.getLogo());
    }
}
