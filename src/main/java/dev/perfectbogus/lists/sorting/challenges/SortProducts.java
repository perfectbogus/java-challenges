package dev.perfectbogus.lists.sorting.challenges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SortProducts {

    record Product(String name, double price) {}

    public static List<Product> sort(List<Product> products) {
        if (products == null)
            throw new IllegalArgumentException("Products cannot be null");

        Comparator<Product> cPrice = Comparator.comparingDouble(Product::price);
        Comparator<Product> cName = Comparator.comparing(Product::name);

        // Step 1 — Create PriorityQueue WITH Comparator
        PriorityQueue<Product> pq = new PriorityQueue<>(cPrice.thenComparing(cName));

        // Step 2 — Add all products to PQ
        pq.addAll(products);

        // Step 3 — Poll all products into result
        List<Product> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }
}
