package dev.perfectbogus.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

public class CompletableFutureChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Use CompletableFuture.supplyAsync() to compute the SUM of a
    // list of integers in a background thread.
    // Retrieve the result using .get().
    //
    // Input:  [1,2,3,4,5]
    // Output: 15
    //
    // Rules:
    // → MUST use supplyAsync()
    // → MUST return result via .get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge1(List<Integer> numbers)
            throws ExecutionException, InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — CompletableFuture.supplyAsync(() -> sum).get()
        Supplier<Integer> sumSupplier = () -> numbers.stream().mapToInt(Integer::intValue).sum();
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(sumSupplier);
        return cf.get();
    }

    public static int challenge1_2(List<Integer> numbers) throws ExecutionException, InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");

        return CompletableFuture.supplyAsync(() ->
                numbers.stream().mapToInt(Integer::intValue).sum())
                .get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Use supplyAsync() + thenApply() + thenApply() to build a
    // string processing pipeline:
    // Step 1 (supplyAsync) → trim whitespace
    // Step 2 (thenApply)   → convert to UPPERCASE
    // Step 3 (thenApply)   → wrap with "<<" and ">>"
    //
    // Input:  "  hello world  "
    // Output: "<<HELLO WORLD>>"
    //
    // Rules:
    // → MUST use supplyAsync() for step 1
    // → MUST chain TWO thenApply() calls
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static String challenge2(String input)
            throws ExecutionException, InterruptedException {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — supplyAsync(trim).thenApply(toUpperCase).thenApply(wrap).get()
        return CompletableFuture.supplyAsync(input::trim)
                .thenApply(String::toUpperCase)
                .thenApply(s -> "<<" + s + ">>")
                .get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Use supplyAsync() + thenAccept() to process a list asynchronously.
    // The async task computes the count of numbers ABOVE a threshold.
    // Store the result in an AtomicInteger via thenAccept().
    // Return the stored value.
    //
    // Input:  numbers=[1,5,8,3,9,2,7], threshold=5
    // Numbers above 5: [8,9,7] → count=3
    // Output: 3
    //
    // Rules:
    // → MUST use supplyAsync() to compute the count
    // → MUST use thenAccept() to store in AtomicInteger
    // → call .get() on the Void future to wait for completion
    // ─────────────────────────────────────────────────────────────
    public static int challenge3(List<Integer> numbers, int threshold)
            throws ExecutionException, InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — AtomicInteger result = new AtomicInteger()
        //        supplyAsync(count > threshold).thenAccept(result::set).get()
        //        return result.get()
        AtomicInteger result = new AtomicInteger(0);
        CompletableFuture.supplyAsync(() -> (int) numbers.stream().filter(i -> i > threshold).count())
                .thenAccept(result::set)
                .get();
        return result.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Use supplyAsync() + thenApply() to compute employee stats.
    // Step 1 (supplyAsync) → find the MAX salary from the list
    // Step 2 (thenApply)   → format as "Max salary: X.00"
    //
    // record Employee(String name, double salary)
    //
    // Input:  [Alice/95000, Bob/60000, Carol/85000]
    // Output: "Max salary: 95000.00"
    //
    // Rules:
    // → MUST use supplyAsync() to find max salary
    // → MUST use thenApply() to format the result
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    record Employee(String name, double salary) {}

    public static String challenge4(List<Employee> employees)
            throws ExecutionException, InterruptedException {
        if (employees == null || employees.isEmpty())
            throw new IllegalArgumentException("Employees cannot be null or empty");
        // TODO — supplyAsync(maxSalary).thenApply(format).get()
        return CompletableFuture.supplyAsync(() ->
                employees.stream()
                        .mapToDouble(Employee::salary)
                        .max().orElse(0.0))
                .thenApply(max -> String.format("Max salary: %.2f", max))
                .get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Use thenCompose() to chain TWO async operations.
    // Step 1: async lookup of a department name from a Map by ID
    // Step 2: async lookup of the employee count for that department
    //         from a second Map
    //
    // Input:  deptId=1,
    //         deptNames={1→"Engineering", 2→"Marketing"}
    //         deptCounts={"Engineering"→15, "Marketing"→8}
    // Output: 15
    //
    // Rules:
    // → MUST use supplyAsync() for Step 1
    // → MUST use thenCompose() for Step 2 (Step 2 also uses supplyAsync!)
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge5(int deptId,
                                 Map<Integer, String> deptNames, Map<String, Integer> deptCounts)
            throws ExecutionException, InterruptedException {
        if (deptNames == null || deptCounts == null)
            throw new IllegalArgumentException("Maps cannot be null");
        // TODO — supplyAsync(deptNames.get(deptId))
        //        .thenCompose(name -> supplyAsync(deptCounts.get(name)))
        //        .get()
        CompletableFuture<String> supplyName = CompletableFuture.supplyAsync(() -> deptNames.get(deptId));
        Function<String, CompletableFuture<Integer>> supplyCount = (name) -> CompletableFuture.supplyAsync(() -> deptCounts.get(name));

        return supplyName.thenCompose(supplyCount).get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Use thenCombine() to run TWO independent async tasks in
    // parallel and combine their results.
    // Task 1: compute the SUM of list1
    // Task 2: compute the PRODUCT of list2 (multiply all elements)
    // Combine: return record CombineResult(int sum, int product)
    //
    // Input:  list1=[1,2,3,4], list2=[1,2,3,4]
    // Output: CombineResult(sum=10, product=24)
    //
    // Rules:
    // → MUST use TWO supplyAsync() calls (run in parallel!)
    // → MUST use thenCombine() to merge results
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    record CombineResult(int sum, int product) {}

    public static CombineResult challenge6(List<Integer> list1, List<Integer> list2)
            throws ExecutionException, InterruptedException {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Lists cannot be null");
        // TODO — supplyAsync(sum).thenCombine(supplyAsync(product), CombineResult::new).get()
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> list1.stream().mapToInt(Integer::intValue).sum());
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> list2.stream().reduce((a, b) -> a * b).orElse(0));

        CompletableFuture<CombineResult> resultFuture = sumFuture.thenCombine(productFuture, CombineResult::new);

        return resultFuture.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Use CompletableFuture.allOf() to square each number in the
    // list asynchronously (one CF per number) then collect all
    // results in ORIGINAL ORDER.
    //
    // Input:  [1,2,3,4,5]
    // Output: [1,4,9,16,25]
    //
    // Rules:
    // → create one supplyAsync(n*n) per number
    // → use allOf() to wait for ALL futures
    // → collect results with .join() after allOf completes
    // → preserve original order!
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge7(List<Integer> numbers)
            throws ExecutionException, InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — List<CF<Integer>> futures = each supplyAsync(n*n)
        //        allOf(futures.toArray(new CF[0])).get()
        //        futures.stream().map(CF::join).toList()
        List<CompletableFuture<Integer>> listCF = new ArrayList<>();
        for (int n : numbers) {
            listCF.add(CompletableFuture.supplyAsync(() -> n*n));
        }

        CompletableFuture<Void> allCF = CompletableFuture.allOf(listCF.toArray(new CompletableFuture[0]));

        allCF.get();

        List<Integer> results = listCF.stream()
                .map(CompletableFuture::join)
                .toList();

        return results;
    }

    public static List<Integer> challenge7_2(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");

        List<CompletableFuture<Integer>> futures = numbers.stream()
                .map(n -> CompletableFuture.supplyAsync(() -> n * n))
                .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Use exceptionally() to handle errors gracefully.
    // Attempt to divide numerator by denominator asynchronously.
    // If denominator is 0 → exception thrown → return -1 as default.
    //
    // Input:  numerator=10, denominator=2  → 5
    // Input:  numerator=10, denominator=0  → -1 (division by zero!)
    //
    // Rules:
    // → MUST use supplyAsync() to perform division
    // → MUST use exceptionally() to catch ArithmeticException
    // → return -1 as fallback value
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge8(int numerator, int denominator)
            throws ExecutionException, InterruptedException {
        // TODO — supplyAsync(numerator/denominator)
        //        .exceptionally(ex -> -1)
        //        .get()
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    if (denominator == 0) throw new IllegalArgumentException("denominator cannot be zero");
                    return numerator/denominator;
                })
                .exceptionally(ex -> -1);

        return future.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Use handle() to process a string safely — handling BOTH
    // success and failure in one place.
    //
    // Async task: reverse the given string
    // → If string is null or empty → task throws IllegalArgumentException
    // → handle() intercepts:
    //   SUCCESS → return reversed string
    //   FAILURE → return "ERROR: " + exception message
    //
    // Input:  "hello"  → "olleh"
    // Input:  ""       → "ERROR: String cannot be empty"
    //
    // Rules:
    // → MUST use supplyAsync() for the reversal task
    //   (throw IllegalArgumentException("String cannot be empty") if empty!)
    // → MUST use handle() to intercept both cases
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static String challenge9(String s)
            throws ExecutionException, InterruptedException {
        // TODO — supplyAsync(() -> { if empty throw; return reversed; })
        //        .handle((result, ex) -> ex != null ? "ERROR: "+ex.getMessage() : result)
        //        .get()
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
                () -> {
                    if (s.isEmpty()) throw new IllegalArgumentException("String cannot be null");
                    return new StringBuilder(s).reverse().toString();
                })
                .handle((result, ex) -> {
                    if (ex != null) {
                        return "ERROR: String cannot be empty";
                    }
                    return result;
                });

        return future.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Use CompletableFuture.anyOf() to run multiple async tasks and
    // return the result of whichever finishes FIRST.
    // Each task sleeps for a given duration then returns its name.
    //
    // Input:  tasks=[("slow",300),("fast",50),("medium",150)]
    //         (name, sleepMs)
    // Output: "fast"  ← finishes after only 50ms!
    //
    // record Task(String name, long sleepMs)
    //
    // Rules:
    // → create one supplyAsync per task (each sleeps sleepMs then returns name)
    // → use anyOf() to get whichever finishes first
    // → cast result to String and return
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    record Task(String name, long sleepMs) {}

    public static String challenge10(List<Task> tasks)
            throws ExecutionException, InterruptedException {
        if (tasks == null || tasks.isEmpty())
            throw new IllegalArgumentException("Tasks cannot be null or empty");
        // TODO — List<CF<String>> futures = each supplyAsync(sleep then return name)
        //        anyOf(futures.toArray(new CF[0])).get() → cast to String
        return "";
    }
}