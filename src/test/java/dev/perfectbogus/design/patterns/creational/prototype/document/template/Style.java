package dev.perfectbogus.design.patterns.creational.prototype.document.template;

public class Style {
    private String font;
    private int fontSize;

    public Style(String font, int fontSize) {
        this.font = font;
        this.fontSize = fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Style(Style other) {
        this.font = other.getFont();
        this.fontSize = other.getFontSize();
    }
}
