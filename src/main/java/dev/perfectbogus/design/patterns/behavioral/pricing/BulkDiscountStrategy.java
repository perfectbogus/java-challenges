package dev.perfectbogus.design.patterns.behavioral.pricing;

// Task 2d — Applies a quantity-based tiered discount:
//   quantity < 10  → no discount
//   quantity < 50  → 15% discount
//   quantity >= 50 → 25% discount
public class BulkDiscountStrategy implements PricingStrategy {

    // TODO: add a private field for quantity
    private int quantity;

    // TODO: constructor that accepts int quantity
    public BulkDiscountStrategy(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        // TODO: store quantity
        this.quantity = quantity;
    }

    // TODO: implement calculate(double basePrice) applying the tiered discount
    @Override
    public double calculate(double basePrice) {
        return basePrice - (basePrice * tier(quantity));
    }

    private double tier(int quantity) {
        if (quantity < 10) return 0;
        if (quantity < 50) return .15;
        return .25;
    }

}
