package dev.perfectbogus.design.patterns.creational.builder.pizzeria;

public class Pizza {

    private final String size;
    private final boolean cheese;
    private final int extraSauce;

    private Pizza(Builder b) {
        this.size = b.size;
        this.cheese = b.cheese;
        this.extraSauce = b.extraSauce;
    }

    public static Builder builder(String size) {
        return new Builder(size);
    }

    public static final class Builder {
        private final String size;
        private boolean cheese = false;
        private int extraSauce = 0;

        private Builder(String size) {
            this.size = size;
        }

        public Builder cheese(boolean value) { this.cheese = value; return this; }
        public Builder extraSauce(int value) { this.extraSauce = value; return this; }

        public Pizza build() {
            if (extraSauce > 3) throw new IllegalStateException("Max 3 extra sauces");
            return new Pizza(this);
        }
    }
}
