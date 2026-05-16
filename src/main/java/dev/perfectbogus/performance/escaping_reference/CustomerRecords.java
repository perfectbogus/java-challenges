package dev.perfectbogus.performance.escaping_reference;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomerRecords implements Iterable<Customer> {

    private Map<String, Customer> records;

    public CustomerRecords() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer c) {
        this.records.put(c.getName(), c);
    }

    // Return a copy
    public Map<String, Customer> getCustomers() {
        return new HashMap<>(this.records);
    }

    // Return immutable
    public Map<String, Customer> getCustomersImmutable() {
        return Collections.unmodifiableMap(this.records);
    }

    // Return Immutable Copy
    public Map<String, Customer> getCustomerImmutableCopy() {
        return Map.copyOf(records);
    }

    public ReadOnlyCustomer find(String name) {
        return new Customer(records.get(name));
    }

    @Override
    public Iterator<Customer> iterator() {
        return records.values().iterator();
    }
}
