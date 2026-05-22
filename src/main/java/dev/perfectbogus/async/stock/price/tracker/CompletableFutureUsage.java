package dev.perfectbogus.async.stock.price.tracker;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class CompletableFutureUsage {

    public static void main(String[] args) {
        // Run async task that returns a value
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> 1d + 1d);

        // 2: Transform result (like stream.map)
        cf.thenApply(price -> String.format("$%.2f", price));

        // 3: Consume result (no return value)
        cf.thenAccept(price -> System.out.println("Current price: " + price));

        // 4: Chain another asyn operation
        CompletableFuture<Double> withTax = cf.thenCompose(
                price ->
                CompletableFuture.supplyAsync(() -> price * 1.1));

        // 5: Combine two futures
        CompletableFuture<Double> appl = CompletableFuture.supplyAsync(() -> 150.0);
        CompletableFuture<Double> googl = CompletableFuture.supplyAsync(() -> 2800.0);

        CompletableFuture<Double> total = appl.thenCombine(googl, Double::sum);

        // 6 wait for all futures
        CompletableFuture.allOf(cf, withTax, total).join();

        // 7: Return FIRST completed future
        CompletableFuture.anyOf(cf, withTax, total).thenAccept(System.out::println);

        // 8: Handle Errors
        cf.exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return 0.0;
        });

        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();

        Integer result = data.computeIfAbsent("APPL", k -> 0);
        System.out.println("result 1: " + result);
        Integer appl1 = data.computeIfAbsent("APPL", k -> 10);
        System.out.println("result 2: " + appl1);
        Integer appl2 = data.computeIfPresent("APPL", (key, current) -> 10);
        System.out.println("result 3: " + appl2);

        data.put("GOOGL", 0);
        data.put("GOOGL", 1);
        data.put("GOOGL", 2);

        System.out.println(data.get("GOOGL"));

    }
}
