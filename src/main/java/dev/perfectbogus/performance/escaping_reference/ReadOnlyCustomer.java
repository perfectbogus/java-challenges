package dev.perfectbogus.performance.escaping_reference;

public interface ReadOnlyCustomer {
    String getName();

    @Override
    String toString();
}
