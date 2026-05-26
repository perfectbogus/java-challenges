package dev.perfectbogus.design.patterns.behavioral.pricing;

// Task 2c — Applies a flat 20% discount to the base price
public class VipDiscountStrategy implements PricingStrategy {
    @Override
    public double calculate(double basePrice) {
        return basePrice - (basePrice * .20);
    }
}
