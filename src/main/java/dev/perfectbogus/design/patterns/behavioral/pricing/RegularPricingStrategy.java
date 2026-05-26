package dev.perfectbogus.design.patterns.behavioral.pricing;

// Task 2a — Returns the base price unchanged (no discount applied)
public class RegularPricingStrategy implements PricingStrategy {
    // TODO: implement calculate(double basePrice)
    @Override
    public double calculate(double basePrice) {
        return basePrice;
    }
}
