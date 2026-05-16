package dev.perfectbogus.performance.escaping_reference;

public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }
    public Customer(Customer c) {
        this.name = c.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}
