package dev.perfectbogus.design.patterns.behavioral.pricing;

// Task 3 — The Context class.
// Holds a reference to a PricingStrategy and delegates pricing work to it.
// The strategy can be swapped at any time via setStrategy().
public class PricingContext {

    // TODO: add a private field to hold the current PricingStrategy

    // TODO: constructor that accepts a PricingStrategy
    //       throw IllegalArgumentException("Strategy cannot be null") if null
    public PricingContext(PricingStrategy strategy) {
        // TODO: implement
    }

    // TODO: swap the current strategy at runtime
    //       throw IllegalArgumentException("Strategy cannot be null") if null
    public void setStrategy(PricingStrategy strategy) {
        // TODO: implement
    }

    // TODO: delegate the price calculation to the current strategy
    public double calculatePrice(double basePrice) {
        // TODO: implement
        return 0;
    }
}
