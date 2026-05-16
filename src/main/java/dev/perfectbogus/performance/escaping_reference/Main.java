package dev.perfectbogus.performance.escaping_reference;

import java.util.Iterator;

public class Main {

    static void main(String[] args) {
        CustomerRecords records = new CustomerRecords();

        records.addCustomer(new Customer("John"));
        records.addCustomer(new Customer("Simon"));

        System.out.println("All records");
        for (Customer next : records) {
            System.out.println(next);
        }

        // Even with iterator is possible to modify the reference
        Iterator<Customer> it = records.iterator();
        it.next();
        it.remove();

        System.out.println("modified records:");
        for (Customer next : records) {
            System.out.println(next);
        }

//        System.out.println("Cannot modified");
//        records.getCustomerImmutableCopy().clear();

//        System.out.println(records.find("John"));

        ReadOnlyCustomer rc = records.find("John");
        Customer newCustomer = (Customer) rc;
        newCustomer.setName("Jane");

        System.out.println(newCustomer);

    }
}
