package dev.perfectbogus.design.patterns.behavioral.strategy.pricing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Task 5 — A registry that stores named PricingStrategy instances.
// Allows strategies to be looked up by name at runtime.
public class PricingStrategyRegistry {

    // TODO: add a private Map<String, PricingStrategy> to store named strategies
    private final Map<String, PricingStrategy> registry = new HashMap<>();

    // TODO: register a strategy under a given name
    //       throw IllegalArgumentException("Name cannot be null or blank") if name is null or blank
    //       throw IllegalArgumentException("Strategy cannot be null") if strategy is null
    public void register(String name, PricingStrategy strategy) {
        // TODO: implement
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (strategy == null) throw new IllegalArgumentException("Strategy cannot be null");
        registry.put(name, strategy);
    }

    // TODO: return the strategy registered under the given name
    //       throw IllegalArgumentException("Unknown strategy: " + name) if not found
    public PricingStrategy getStrategy(String name) {
        // TODO: implement
        var strategy = registry.get(name);
        if (strategy == null) throw new IllegalArgumentException("Unknown strategy: " + name);
        return strategy;
    }

    // TODO: return an unmodifiable Set<String> of all registered strategy names
    public Set<String> getAvailableStrategies() {
        // TODO: implement
        return Set.copyOf(registry.keySet());
    }
}
