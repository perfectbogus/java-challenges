package dev.perfectbogus.design.patterns.behavioral.strategy.pricing;

// Task 3 — The Context class.
// Holds a reference to a PricingStrategy and delegates pricing work to it.
// The strategy can be swapped at any time via setStrategy().
public class PricingContext {

    // TODO: add a private field to hold the current PricingStrategy
    private PricingStrategy strategy;

    // TODO: constructor that accepts a PricingStrategy
    //       throw IllegalArgumentException("Strategy cannot be null") if null
    public PricingContext(PricingStrategy strategy) {
        // TODO: implement
        if (strategy == null) throw new IllegalArgumentException("Strategy cannot be null");
        this.strategy = strategy;
    }

    // TODO: swap the current strategy at runtime
    //       throw IllegalArgumentException("Strategy cannot be null") if null
    public void setStrategy(PricingStrategy strategy) {
        // TODO: implement
        if (strategy == null) throw new IllegalArgumentException("Strategy cannot be null");
        this.strategy = strategy;
    }

    // TODO: delegate the price calculation to the current strategy
    public double calculatePrice(double basePrice) {
        // TODO: implement
        return strategy.calculate(basePrice);
    }
}
