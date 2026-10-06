package dev.perfectbogus.completable.future.usage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;

public class Lesson1 {
    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static CompletableFuture<Integer> findUserId(String username) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return username.length() * 100;
        });
    }

    static CompletableFuture<String> fetchProfile(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Profile#" + userId;
        });
    }

    static CompletableFuture<Double> getPrice(String product) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            sleep(1000);
            if (product.equals("monitor")) throw new IllegalArgumentException("Unknown product: " + product);
            return product.length() * 100.0;
        });
    }

    static CompletableFuture<Double> applyDiscount(double price) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            sleep(500);
            return price * 0.90;
        });
    }

    // Exercise 1: getStock
    static CompletableFuture<Integer> getStock(String product) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(800);
            return product.length();
        });
    }

    // Exercise 2: getShippingCost
    static CompletableFuture<Double> getShippingCost(String product) {
        return CompletableFuture.supplyAsync(() -> {
           sleep(600);
           return product.length() * 2.5;
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> result =
                findUserId("alice")
                        .thenCompose(Lesson1::fetchProfile)
                        .thenApply(String::toUpperCase);

        final String product = "laptop";
        CompletableFuture<String> message =
                getPrice(product)
                        .thenCompose(Lesson1::applyDiscount)
                        .thenApply(price -> String.format("Final price for %s: %.2f", product, price));

        System.out.println(result.join());
        System.out.println(message.join());

        // Exercise 2
        long startEx2 = System.currentTimeMillis();
        String productEx2 = "laptop";
        CompletableFuture<Double> discountCf = getPrice(productEx2).thenCompose(Lesson1::applyDiscount);
        CompletableFuture<Integer> stock = getStock(productEx2);
        CompletableFuture<Double> shippingCostCf = getShippingCost(productEx2);

        CompletableFuture<String> summaryCf = discountCf
                .thenCombine(
                        shippingCostCf,
                        Double::sum
                ).thenCombine(
                        stock,
                        (total, stockValue) ->
                                String.format("%s -> total: %.2f, stock: %d", productEx2, total, stockValue)
                );

        System.out.println(summaryCf.join());
        System.out.println("Took " + (System.currentTimeMillis() - startEx2) + " ms");

        //Exercise 2 Part B:
        long startPartB = System.currentTimeMillis();
        List<String> products = List.of("laptop", "mouse", "monitor");
        Map<String, CompletableFuture<Double>> futuresByProduct = new LinkedHashMap<>();
        for (String p : products) {
            futuresByProduct.put(p, getPrice(p).thenCompose(Lesson1::applyDiscount));
        }

        CompletableFuture<Void> allCf = CompletableFuture.allOf(futuresByProduct.values().toArray(new CompletableFuture[0]));

        CompletableFuture<Map<String, Double>> pricesCf = allCf.thenApply(v -> {
            Map<String, Double> prices = new LinkedHashMap<>();
            futuresByProduct.forEach((p, future) -> prices.put(p, future.join()));
            return prices;
        });

        try {
            System.out.println(pricesCf.join());
        } catch (CompletionException e) {
            System.out.println("Outer: " + e.getClass());
            System.out.println("Cause: " + e.getCause());
        }
        System.out.println("Exercise 2 Part B: Took " + (System.currentTimeMillis() - startPartB) + " ms");

        // Lesson 3


    }
}
