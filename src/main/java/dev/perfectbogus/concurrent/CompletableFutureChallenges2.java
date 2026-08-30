package dev.perfectbogus.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class CompletableFutureChallenges2 {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Use supplyAsync() + thenRun() to verify a side effect fires.
    // Step 1 (supplyAsync): compute the sum of a list of integers.
    // Step 2 (thenRun):     increment a shared AtomicInteger counter.
    //                       thenRun takes a Runnable → no access to result!
    // Wait for completion with .get() on the Void future.
    // Return the counter value (should always be 1).
    //
    // Input:  [1,2,3,4,5]
    // Output: counter=1  (thenRun fired exactly once!)
    //
    // Rules:
    // → MUST use thenRun() (NOT thenAccept!)
    // → thenRun Runnable must increment AtomicInteger
    // → call .get() on the Void CF to wait for completion
    // ─────────────────────────────────────────────────────────────
    public static int challenge1(List<Integer> numbers)
            throws ExecutionException, InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — AtomicInteger counter = new AtomicInteger(0)
        //        supplyAsync(sum).thenRun(counter::incrementAndGet).get()
        //        return counter.get()
        AtomicInteger counter = new AtomicInteger(0);

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() ->
                numbers.stream().mapToInt(Integer::intValue).sum());

        sumFuture.thenRun(counter::incrementAndGet).get();

        return counter.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Use whenComplete() to observe completion of an async task.
    // The async task computes n / d (integer division).
    // whenComplete sees BOTH success and failure:
    // → SUCCESS → store "SUCCESS:result" in AtomicReference
    // → FAILURE → store "FAILURE:exceptionMessage" in AtomicReference
    // Return the stored string.
    //
    // Input:  n=10, d=2 → "SUCCESS:5"
    // Input:  n=10, d=0 → "FAILURE:/ by zero"
    //
    // Rules:
    // → MUST use whenComplete((result, ex) -> ...)
    // → whenComplete does NOT transform the CF value!
    // → call .get() on the ORIGINAL CF to wait
    //   (catch ExecutionException for the error case!)
    // ─────────────────────────────────────────────────────────────
    public static String challenge2(int n, int d)
            throws InterruptedException {
        // TODO — AtomicReference<String> ref = new AtomicReference<>()
        //        CompletableFuture<Integer> cf = supplyAsync(n/d)
        //        cf.whenComplete((res,ex) -> ref.set(ex!=null ? "FAILURE:..." : "SUCCESS:..."))
        //        try { cf.get() } catch (ExecutionException e) { /* expected */ }
        //        return ref.get()
        AtomicReference<String> ref = new AtomicReference<>();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> n/d);
        cf.whenComplete((res, ex) ->
                ref.set(ex!=null
                        ? "FAILURE:" + ex.getCause().getMessage()
                        : "SUCCESS:" + res)
        );

        try {
            cf.get();
        } catch (ExecutionException e) {
        }

        return ref.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Use CompletableFuture.completedFuture() as the starting point.
    // Chain transforms to build a pipeline from a known value:
    // Step 1 (completedFuture): wrap the input integer
    // Step 2 (thenApply):       multiply by itself (square)
    // Step 3 (thenApply):       convert to String
    // Step 4 (thenApply):       wrap as "Result: X"
    //
    // Input:  7
    // → completedFuture(7) → 7*7=49 → "49" → "Result: 49"
    // Output: "Result: 49"
    //
    // Rules:
    // → MUST use CompletableFuture.completedFuture(n) as start!
    // → MUST chain THREE thenApply() calls
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static String challenge3(int n)
            throws ExecutionException, InterruptedException {
        // TODO — completedFuture(n).thenApply(square).thenApply(toString).thenApply(wrap).get()
        return CompletableFuture.completedFuture(n)
                .thenApply(i -> i * i)
                .thenApply(String::valueOf)
                .thenApply(x -> "Result: " + x).get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Use supplyAsync() with a CUSTOM ExecutorService.
    // Submit taskCount tasks to a fixed thread pool of poolSize.
    // Each task returns the name of its executing thread.
    // Return the SET of unique thread names used.
    //
    // Input:  taskCount=10, poolSize=3
    // Output: Set of at most 3 unique thread names
    //         (e.g. {"pool-1-thread-1","pool-1-thread-2","pool-1-thread-3"})
    //
    // Rules:
    // → MUST use Executors.newFixedThreadPool(poolSize)
    // → MUST pass executor as second arg to supplyAsync()
    // → shutdown executor after collecting results
    // → returned set size MUST be <= poolSize
    // ─────────────────────────────────────────────────────────────
    public static Set<String> challenge4(int taskCount, int poolSize)
            throws ExecutionException, InterruptedException {
        if (taskCount <= 0 || poolSize <= 0)
            throw new IllegalArgumentException("Must be positive");
        // TODO — ExecutorService executor = Executors.newFixedThreadPool(poolSize)
        //        List<CF<String>> futures = each supplyAsync(threadName, executor)
        //        collect results into Set<String>
        //        executor.shutdown()
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (int i = 0; i < taskCount; i++) {
            final int j = i;
            futures.add(CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), executor));
        }

        Set<String> set = new HashSet<>();

        try {
            for (CompletableFuture<String> cf : futures) {
                set.add(cf.get());
            }
        } finally {
            executor.shutdown();
        }

        return set;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Use thenAcceptBoth() to consume results of TWO parallel futures.
    // Task 1 (supplyAsync): find the longest word in list1
    // Task 2 (supplyAsync): find the shortest word in list2
    // thenAcceptBoth: format as "longest=X shortest=Y" in AtomicReference
    //
    // thenAcceptBoth(other, BiConsumer<T,U>) — consumes BOTH results
    //   when BOTH futures complete!
    //
    // Input:  list1=["apple","kiwi","strawberry","fig"]
    //         list2=["banana","mango","pear","blueberry"]
    // longest="strawberry"(10), shortest="pear"(4)
    // Output: "longest=strawberry shortest=pear"
    //
    // Rules:
    // → MUST use thenAcceptBoth()
    // → MUST use supplyAsync() for both tasks
    // → call .get() on the Void future to wait
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(List<String> list1, List<String> list2)
            throws ExecutionException, InterruptedException {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Lists cannot be null");
        // TODO — AtomicReference<String> ref = new AtomicReference<>()
        //        CF<String> longest  = supplyAsync(longest word in list1)
        //        CF<String> shortest = supplyAsync(shortest word in list2)
        //        longest.thenAcceptBoth(shortest, (l,s) -> ref.set(...)).get()
        //        return ref.get()
        AtomicReference<String> ref = new AtomicReference<>();
        CompletableFuture<String> longest = CompletableFuture.supplyAsync(() -> list1.stream().max(Comparator.comparingInt(String::length)).orElse(""));
        CompletableFuture<String> shortest = CompletableFuture.supplyAsync(() -> list2.stream().min(Comparator.comparingInt(String::length)).orElse(""));

        longest.thenAcceptBoth(shortest, (l, s) -> ref.set("longest=" + l + " shortest=" + s)).get();

        return ref.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Use CompletableFuture.runAsync() to execute a side effect
    // then chain thenApply() to supply a computed value.
    //
    // Step 1 (runAsync):  increment AtomicInteger counter (simulate logging)
    // Step 2 (thenApply): SUPPLY the sum of numbers (thenApply receives Void!)
    //   ← Wait! thenApply on Void CF gives thenApply(ignored -> value)
    // Step 3 (thenApply): multiply sum by multiplier
    //
    // Return record RunResult(int counterValue, int computedValue)
    //
    // Input:  numbers=[1,2,3,4,5], multiplier=2
    // → runAsync: counter becomes 1
    // → thenApply: sum=15
    // → thenApply: 15*2=30
    // Output: RunResult(counterValue=1, computedValue=30)
    //
    // Rules:
    // → MUST use runAsync() for step 1
    // → MUST chain two thenApply() calls after
    // ─────────────────────────────────────────────────────────────
    record RunResult(int counterValue, int computedValue) {}

    public static RunResult challenge6(List<Integer> numbers, int multiplier)
            throws ExecutionException, InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — AtomicInteger counter
        //        runAsync(counter::incrementAndGet)
        //        .thenApply(v -> sum of numbers)
        //        .thenApply(sum -> sum * multiplier)
        //        .get() → computedValue
        //        return new RunResult(counter.get(), computedValue)
        return new RunResult(0, 0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Use allOf() to run THREE async computations in parallel,
    // then combine their results into a single record.
    //
    // record EmployeeSummary(String name, String department, double salary)
    //
    // Given three separate async tasks:
    // Task 1: fetch name       → returns names.get(id)
    // Task 2: fetch department → returns depts.get(id)
    // Task 3: fetch salary     → returns salaries.get(id)
    //
    // Wait for ALL three using allOf(), then build EmployeeSummary.
    //
    // Input:  id=1,
    //         names={1→"Alice"}, depts={1→"Engineering"}, salaries={1→95000.0}
    // Output: EmployeeSummary("Alice","Engineering",95000.0)
    //
    // Rules:
    // → MUST use THREE supplyAsync() calls
    // → MUST use allOf() to wait for all
    // → use .join() to collect each result after allOf
    // ─────────────────────────────────────────────────────────────
    record EmployeeSummary(String name, String department, double salary) {}

    public static EmployeeSummary challenge7(int id,
                                             Map<Integer, String> names,
                                             Map<Integer, String> depts,
                                             Map<Integer, Double> salaries)
            throws ExecutionException, InterruptedException {
        if (names == null || depts == null || salaries == null)
            throw new IllegalArgumentException("Maps cannot be null");
        // TODO — CF<String> nameCF   = supplyAsync(names.get(id))
        //        CF<String> deptCF   = supplyAsync(depts.get(id))
        //        CF<Double> salCF    = supplyAsync(salaries.get(id))
        //        allOf(nameCF, deptCF, salCF).get()
        //        return new EmployeeSummary(nameCF.join(), deptCF.join(), salCF.join())
        return new EmployeeSummary("", "", 0.0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Use exceptionally() with a COMPUTED fallback (not just static value).
    // Async task: parse a string to integer using Integer.parseInt().
    // → valid string   → return parsed value
    // → invalid string → NumberFormatException → return string.length() as fallback!
    //
    // Input:  "123"   → parsed=123
    // Input:  "hello" → exception → fallback=5 (length of "hello")
    // Input:  "abc"   → exception → fallback=3 (length of "abc")
    //
    // Rules:
    // → MUST use supplyAsync() to parse
    // → MUST use exceptionally() for fallback
    // → fallback = input.length() (computed, not static!)
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge8(String input)
            throws ExecutionException, InterruptedException {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — supplyAsync(() -> Integer.parseInt(input))
        //        .exceptionally(ex -> input.length())
        //        .get()
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Use completeOnTimeout() to provide a DEFAULT VALUE if the
    // async task takes too long.
    //
    // completeOnTimeout(defaultValue, timeout, timeUnit)
    // → if CF completes before timeout → return actual result
    // → if timeout expires first       → complete with defaultValue!
    //
    // The async task sleeps for sleepMs milliseconds then returns value.
    // If sleepMs > timeoutMs → task times out → return defaultValue.
    // If sleepMs < timeoutMs → task completes → return value.
    //
    // Input:  value=42, sleepMs=50,  timeoutMs=200, defaultValue=0 → 42
    // Input:  value=42, sleepMs=500, timeoutMs=100, defaultValue=0 → 0
    //
    // Rules:
    // → MUST use supplyAsync() for the sleeping task
    // → MUST use completeOnTimeout(default, timeout, unit)
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge9(int value, long sleepMs,
                                 long timeoutMs, int defaultValue)
            throws ExecutionException, InterruptedException {
        // TODO — supplyAsync(() -> { sleep(sleepMs); return value; })
        //        .completeOnTimeout(defaultValue, timeoutMs, TimeUnit.MILLISECONDS)
        //        .get()
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Use a CHAIN of thenCompose() calls to simulate a 3-step
    // async lookup pipeline:
    // Step 1 (supplyAsync):   get username from userId map
    // Step 2 (thenCompose):   get department from username map
    // Step 3 (thenCompose):   get budget from department map
    // Return the final budget as a Double.
    //
    // Input:  userId=1,
    //         userMap={1→"alice"},
    //         deptMap={"alice"→"Engineering"},
    //         budgetMap={"Engineering"→500000.0}
    //
    // userId=1 → "alice" → "Engineering" → 500000.0
    // Output: 500000.0
    //
    // Rules:
    // → MUST use supplyAsync() for step 1
    // → MUST use TWO thenCompose() calls for steps 2 and 3
    // → each thenCompose() must use supplyAsync() internally
    // → retrieve with .get()
    // ─────────────────────────────────────────────────────────────
    public static double challenge10(int userId,
                                     Map<Integer, String> userMap,
                                     Map<String, String> deptMap,
                                     Map<String, Double> budgetMap)
            throws ExecutionException, InterruptedException {
        if (userMap == null || deptMap == null || budgetMap == null)
            throw new IllegalArgumentException("Maps cannot be null");
        // TODO — supplyAsync(userMap.get(userId))
        //        .thenCompose(username -> supplyAsync(deptMap.get(username)))
        //        .thenCompose(dept     -> supplyAsync(budgetMap.get(dept)))
        //        .get()
        return 0.0;
    }
}