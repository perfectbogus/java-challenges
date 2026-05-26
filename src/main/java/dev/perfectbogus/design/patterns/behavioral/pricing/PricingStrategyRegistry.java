package dev.perfectbogus.design.patterns.behavioral.pricing;

import java.util.Set;

// Task 5 — A registry that stores named PricingStrategy instances.
// Allows strategies to be looked up by name at runtime.
public class PricingStrategyRegistry {

    // TODO: add a private Map<String, PricingStrategy> to store named strategies

    // TODO: register a strategy under a given name
    //       throw IllegalArgumentException("Name cannot be null or blank") if name is null or blank
    //       throw IllegalArgumentException("Strategy cannot be null") if strategy is null
    public void register(String name, PricingStrategy strategy) {
        // TODO: implement
    }

    // TODO: return the strategy registered under the given name
    //       throw IllegalArgumentException("Unknown strategy: " + name) if not found
    public PricingStrategy getStrategy(String name) {
        // TODO: implement
        return null;
    }

    // TODO: return an unmodifiable Set<String> of all registered strategy names
    public Set<String> getAvailableStrategies() {
        // TODO: implement
        return null;
    }
}
