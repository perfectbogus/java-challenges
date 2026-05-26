package dev.perfectbogus.design.patterns.behavioral.pricing;

import java.util.List;

// Task 6 — A composite strategy that chains multiple strategies together.
// The output of each strategy becomes the input of the next.
// Example: [SeasonalDiscount(10%), VipDiscount(20%)] on 100.0
//          → 100.0 * 0.9 = 90.0 → 90.0 * 0.8 = 72.0
public class CompositePricingStrategy implements PricingStrategy {

    // TODO: add a private List<PricingStrategy> field

    // TODO: constructor that accepts List<PricingStrategy> strategies
    //       throw IllegalArgumentException("Strategies cannot be null or empty") if null or empty
    public CompositePricingStrategy(List<PricingStrategy> strategies) {
        // TODO: implement
    }

    // TODO: apply each strategy in sequence — output of one feeds into the next
    @Override
    public double calculate(double basePrice) {
        // TODO: implement
        return 0;
    }
}
