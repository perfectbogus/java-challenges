package dev.perfectbogus.performance.escaping_reference;

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

    @Override
    public Iterator<Customer> iterator() {
        return records.values().iterator();
    }
}
