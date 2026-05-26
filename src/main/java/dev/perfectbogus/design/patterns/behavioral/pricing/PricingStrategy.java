package dev.perfectbogus.design.patterns.behavioral.pricing;

// Task 1 — The Strategy interface.
// Defines the contract that every pricing algorithm must fulfill.
// Since it has a single abstract method it is also a functional interface
// and can be implemented as a lambda (see Task 4).
@FunctionalInterface
public interface PricingStrategy {
    // TODO: declare calculate(double basePrice) returning double
    double calculate(double basePrice);
}
