package dev.perfectbogus.design.patterns.behavioral.pricing;

// Task 2b — Applies a flat 10% discount to the base price
public class SeasonalDiscountStrategy implements PricingStrategy {
    @Override
    public double calculate(double basePrice) {
        return basePrice - (basePrice * .10);
    }
}
