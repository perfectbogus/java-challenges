package dev.perfectbogus.design.patterns.structural.adapter.payments;

import java.util.Map;
import java.util.Set;

// Task 5 — Registry that stores named PaymentProcessor instances.
// Allows the client to look up any registered adapter by name at runtime.
public class PaymentProcessorRegistry {

    // TODO: private Map<String, PaymentProcessor> to store named processors

    // TODO: register a processor under a given name
    //       throw IllegalArgumentException("Name cannot be null or blank") if name is null/blank
    //       throw IllegalArgumentException("Processor cannot be null") if processor is null
    public void register(String name, PaymentProcessor processor) {
        // TODO: implement
    }

    // TODO: return the processor registered under the given name
    //       throw IllegalArgumentException("Unknown processor: " + name) if not found
    public PaymentProcessor getProcessor(String name) {
        // TODO: implement
        return null;
    }

    // TODO: return an unmodifiable Set<String> of all registered processor names
    public Set<String> getRegisteredNames() {
        // TODO: implement
        return null;
    }
}
