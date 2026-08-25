package dev.perfectbogus.threading;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadingChallenges {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Create and start a Thread using a Runnable lambda.
    // The thread computes the SUM of all integers from 1 to n
    // and stores it in an AtomicLong.
    // Use join() to wait for the thread to finish before returning.
    //
    // Input:  n=100
    // Output: 5050  (1+2+...+100)
    //
    // Rules:
    // → MUST create a new Thread and call start()
    // → MUST use AtomicLong to store the result
    // → MUST call join() before returning
    // ─────────────────────────────────────────────────────────────
    public static long challenge1(int n) throws InterruptedException {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        // TODO — AtomicLong result, create thread with lambda, start(), join(), return result.get()
        AtomicLong l = new AtomicLong(0L);
        Thread task = new Thread(() -> {
            for (int i = 1; i <= n ; i++) {
                l.getAndAdd(i);
            }
        });

        task.start();
        task.join();

        return l.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Start N threads simultaneously, each adds its index (0..N-1)
    // to a shared AtomicInteger. Wait for ALL threads with join()
    // then return the final counter value.
    //
    // Input:  threadCount=5
    // → thread 0 adds 0, thread 1 adds 1, ..., thread 4 adds 4
    // Output: 10  (0+1+2+3+4)
    //
    // Rules:
    // → MUST create exactly threadCount threads
    // → MUST start ALL threads before joining ANY of them
    // → MUST join ALL threads before returning
    // ─────────────────────────────────────────────────────────────
    public static int challenge2(int threadCount) throws InterruptedException {
        if (threadCount < 0) throw new IllegalArgumentException("threadCount must be non-negative");
        // TODO — create threads[], AtomicInteger sum
        //        loop: start all threads
        //        loop: join all threads
        //        return sum.get()
        AtomicInteger sum = new AtomicInteger(0);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            final int j = i;
            threads.add(new Thread(() -> sum.getAndAdd(j)));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return sum.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Use Thread.sleep() to simulate a delayed task.
    // Start a thread that sleeps for delayMs milliseconds
    // then sets a result string "Done after Xms".
    // Record the actual elapsed time and return it.
    // Main thread waits using join().
    //
    // Input:  delayMs=100
    // Output: actual elapsed time >= 100ms (return elapsed ms as long)
    //
    // Rules:
    // → thread MUST call Thread.sleep(delayMs)
    // → measure time with System.currentTimeMillis()
    // → main thread MUST use join() to wait
    // ─────────────────────────────────────────────────────────────
    public static long challenge3(long delayMs) throws InterruptedException {
        if (delayMs < 0) throw new IllegalArgumentException("delayMs must be non-negative");
        // TODO — record start time, start thread with sleep, join, return elapsed

        AtomicLong endTime = new AtomicLong();

        Thread task = new Thread(() -> {
            try {
                long startTime = Instant.now().toEpochMilli();
                Thread.sleep(delayMs);
                endTime.set(Instant.now().toEpochMilli() - startTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        task.start();
        task.join();

        return endTime.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Use synchronized to make a counter thread-safe.
    // N threads each call increment() M times on a shared counter.
    // N threads each call decrement() M times on the SAME counter.
    // Return the final counter value (should always be 0!)
    //
    // Input:  threadCount=10, operationsPerThread=1000
    // Output: 0  (always! because increments = decrements)
    //
    // Rules:
    // → MUST use synchronized keyword on the counter methods
    // → NOT allowed to use AtomicInteger here!
    // → MUST join all threads before returning
    // ─────────────────────────────────────────────────────────────
    public static int challenge4(int threadCount, int operationsPerThread)
            throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — create inner class or array with synchronized counter
        //        threadCount threads increment, threadCount threads decrement
        //        join all, return final value (should be 0)
        SyncClass counter = new SyncClass();

        Thread[] threads = new Thread[threadCount*2];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    counter.increment();
                }
            });
        }

        for (int i = threadCount; i < threadCount * 2; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    counter.decrement();
                }
            });
        }

        for (int i = 0; i < threadCount * 2; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadCount * 2; i++) {
            threads[i].join();
        }

        return counter.get();
    }

    static class SyncClass {
        private int counter = 0;
        private final Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                this.counter++;
            }
        }

        public void decrement() {
            synchronized (lock) {
                this.counter--;
            }
        }

        public Integer get() {
            synchronized (lock) {
                return this.counter;
            }
        }
    }

    static class SyncCounter {
        private int counter = 0;

        public synchronized void increment() { counter++; }
        public synchronized void decrement() { counter--; }
        public synchronized int get() { return counter; }
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Use AtomicInteger for a thread-safe counter.
    // N threads each increment a shared AtomicInteger M times.
    // Return the final value.
    //
    // Input:  threadCount=10, incrementsPerThread=1000
    // Output: 10000  (always exactly! thread-safe!)
    //
    // Rules:
    // → MUST use AtomicInteger (not synchronized)
    // → MUST start all threads before joining any
    // → MUST join all before returning
    // ─────────────────────────────────────────────────────────────
    public static int challenge5(int threadCount, int incrementsPerThread)
            throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — AtomicInteger counter, create + start threads, join all, return counter.get()
        AtomicInteger counter = new AtomicInteger(0);

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.getAndIncrement();
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return counter.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Use ExecutorService (fixed thread pool) to submit N tasks.
    // Each task returns the name of the thread that ran it.
    // Collect all thread names and return them as a Set.
    //
    // Input:  taskCount=20, poolSize=4
    // Output: Set of thread names (at most 4 unique names!)
    //         e.g. {"pool-1-thread-1","pool-1-thread-2","pool-1-thread-3","pool-1-thread-4"}
    //
    // Rules:
    // → MUST use Executors.newFixedThreadPool(poolSize)
    // → MUST use executor.submit(Callable) to get Future<String>
    // → MUST shutdown executor and await termination
    // → returned set size MUST be <= poolSize
    // ─────────────────────────────────────────────────────────────
    public static Set<String> challenge6(int taskCount, int poolSize)
            throws InterruptedException, ExecutionException {
        if (taskCount <= 0 || poolSize <= 0) throw new IllegalArgumentException("Must be positive");
        // TODO — newFixedThreadPool, submit taskCount Callables returning thread name
        //        collect Future results into Set<String>
        //        shutdown + awaitTermination
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Future<String> future = executor.submit(
                    () -> Thread.currentThread().getName()
            );
            futures.add(future);
        }

        Set<String> threadNames = new HashSet<>();
        for (Future<String> future : futures) {
            threadNames.add(future.get());
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        return threadNames;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Use ExecutorService + Future<Integer> to compute the SUM of
    // each sublist in PARALLEL, then return the list of sums
    // in the ORIGINAL ORDER.
    //
    // Input:  sublists=[[1,2,3],[4,5],[6,7,8,9]]
    // Output: [6, 9, 30]
    //
    // Rules:
    // → submit ALL tasks before calling ANY future.get()
    // → preserve original sublist order in result!
    // → MUST use Callable<Integer> returning the sum
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge7(List<List<Integer>> sublists)
            throws InterruptedException, ExecutionException {
        if (sublists == null) throw new IllegalArgumentException("Sublists cannot be null");
        // TODO — newFixedThreadPool, submit Callable per sublist
        //        collect all futures FIRST, then get() in order
        int nProcessors = Runtime.getRuntime().availableProcessors();
        int nThreads = Math.min(sublists.size(), nProcessors);

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        List<Future<Integer>> holder = new ArrayList<>();

        for (List<Integer> list : sublists) {
            Future<Integer> future = executor.submit(() -> {
                int count = 0;
                for (int i : list) {
                    count += i;
                }
                return count;
            });
            holder.add(future);
        }

        List<Integer> results = new ArrayList<>();

        try {
            for (Future<Integer> future : holder) {
                results.add(future.get());
            }
        } finally {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }

        return results;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Split an int[] array into chunkCount equal chunks.
    // Each chunk is processed by a separate thread that computes
    // the sum of its chunk. Collect partial sums and return the total.
    //
    // Input:  array=[1,2,3,4,5,6,7,8,9,10], chunkCount=2
    //   chunk1=[1,2,3,4,5]  → sum=15
    //   chunk2=[6,7,8,9,10] → sum=40
    // Output: 55
    //
    // Rules:
    // → create exactly chunkCount threads (one per chunk)
    // → use AtomicLong to accumulate total safely
    // → join all threads before returning
    // ─────────────────────────────────────────────────────────────
    public static long challenge8(int[] array, int chunkCount) throws InterruptedException, ExecutionException {
        if (array == null)   throw new IllegalArgumentException("Array cannot be null");
        if (chunkCount <= 0) throw new IllegalArgumentException("chunkCount must be positive");

        int n = array.length / chunkCount;       // ← base chunk size
        List<Future<Long>> futures = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(
                Math.min(chunkCount, Runtime.getRuntime().availableProcessors())
        );

        for (int i = 0; i < chunkCount; i++) {
            final int start = i * n;
            final int end   = (i == chunkCount - 1)  // ← Bug 2 fix: last chunk!
                    ? array.length
                    : start + n;                          // ← Bug 1 fix: no (n-1)!

            futures.add(executor.submit(() -> {
                long sum = 0L;
                for (int j = start; j < end; j++) {  // ← for loop is cleaner!
                    sum += array[j];
                }
                return sum;
            }));
        }

        long total = 0L;
        try {
            for (Future<Long> f : futures) total += f.get();
        } finally {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        return total;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Use ExecutorService.invokeAll() to run multiple Callables
    // and collect ALL their results at once.
    //
    // Given a list of strings, submit one Callable per string.
    // Each Callable returns the string reversed.
    // Collect all reversed strings in ORIGINAL ORDER.
    //
    // Input:  ["hello","world","java","threads"]
    // Output: ["olleh","dlrow","avaj","sdaerht"]
    //
    // Rules:
    // → MUST use invokeAll() (not submit one by one!)
    // → preserve original order in result list
    // → invokeAll() BLOCKS until all futures are done
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge9(List<String> words)
            throws InterruptedException, ExecutionException {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — create List<Callable<String>>, use invokeAll()
        //        collect results from futures in order

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Callable<String>> callables = new ArrayList<>();
        for (String word : words) {
            callables.add(() -> new StringBuilder(word).reverse().toString());
        }

        List<Future<String>> futures = executor.invokeAll(callables);

        List<String> results = new ArrayList<>();
        for (Future<String> future : futures) {
            results.add(future.get());
        }

        return results;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Use ConcurrentHashMap to safely count word frequencies
    // from multiple threads processing different parts of the list.
    //
    // Split the words list into threadCount chunks.
    // Each thread processes its chunk and updates a shared
    // ConcurrentHashMap using merge() for counting.
    //
    // Input:  words=["apple","banana","apple","cherry","banana","apple"], threadCount=2
    // Output: {apple=3, banana=2, cherry=1}
    //
    // Rules:
    // → MUST use ConcurrentHashMap (not HashMap!)
    // → MUST use merge() inside threads for thread-safe counting
    // → join all threads before returning
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge10(List<String> words, int threadCount)
            throws InterruptedException {
        if (words == null)   throw new IllegalArgumentException("Words cannot be null");
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — ConcurrentHashMap<String,Integer> freq
        //        split words into chunks, each thread: chunk.forEach(w -> freq.merge(w,1,Integer::sum))
        //        join all, return freq

        Map<String, Integer> freq = new ConcurrentHashMap<>();
        Thread[] threads = new Thread[threadCount];
        int offset = words.size() / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * offset;
            int end = (i == threadCount - 1) ? words.size() : start + offset;


            List<String> chunk = words.subList(start, end);

            threads[i] = new Thread(() -> {
                System.out.println(chunk);
                for (String w : chunk) {
                    freq.merge(w, 1, Integer::sum);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return freq;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 11–18)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 11
    // Producer-Consumer pattern using BlockingQueue.
    // producerCount threads produce numbers 1..N into a shared queue.
    // consumerCount threads consume and sum all numbers.
    // Use a "poison pill" (-1) to signal consumers to stop.
    // Return the total sum.
    //
    // Input:  numbers=[1,2,3,4,5,6,7,8,9,10], producers=2, consumers=3
    // Output: 55  (sum of 1..10)
    //
    // Rules:
    // → MUST use LinkedBlockingQueue
    // → producers divide numbers among themselves
    // → after producing, each producer puts ONE poison pill per consumer!
    // → consumers stop when they receive poison pill (-1)
    // → join all threads before returning
    // ─────────────────────────────────────────────────────────────
    public static long challenge11(List<Integer> numbers, int producerCount, int consumerCount)
            throws InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — LinkedBlockingQueue<Integer> queue
        //        producers: take slice, put each number, put poison pills
        //        consumers: poll until -1, add to AtomicLong sum
        //        join all, return sum.get()
        BlockingQueue<Integer> q = new LinkedBlockingQueue<>();
        AtomicLong sum = new AtomicLong(0);
        CountDownLatch producerLatch = new CountDownLatch(producerCount);
        final int POISON = -1;


        Thread[] producers = new Thread[producerCount];
        int offsetProd = numbers.size() / producerCount;
        for (int i = 0; i < producerCount; i++) {
            int start = i * offsetProd;
            int end = (i == producerCount - 1) ? numbers.size() : start + offsetProd;
            List<Integer> chunk = numbers.subList(start, end);
            producers[i] = new Thread(() -> {
                chunk.forEach(value -> {
                    try {
                        q.put(value);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                producerLatch.countDown();
            });
        }

        Thread[] consumers = new Thread[consumerCount];
        for (int i = 0; i < consumerCount; i++) {
            consumers[i] = new Thread(() -> {
                while (true) {
                    int val = 0;
                    try {
                        val = q.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (val == POISON) break;
                    sum.addAndGet(val);
                }
            });
        }

        Thread coordinator = new Thread(() -> {
            try {
                producerLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < consumerCount; i++) {
                q.offer(POISON);
            }
        });

        coordinator.start();
        for (Thread t : producers) {
            t.start();
        }

        for (Thread t : consumers) {
            t.start();
        }

        for (Thread t : producers) t.join();
        coordinator.join();
        for (Thread t : consumers) t.join();



        return sum.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 12
    // Use CompletableFuture.supplyAsync() to run a task asynchronously.
    // Chain transformations using thenApply() and thenApply() again.
    //
    // Pipeline:
    // supplyAsync → compute sum of numbers
    // thenApply   → multiply result by multiplier
    // thenApply   → convert to String "Result: X"
    //
    // Input:  numbers=[1,2,3,4,5], multiplier=3
    // → sum=15 → 15*3=45 → "Result: 45"
    // Output: "Result: 45"
    //
    // Rules:
    // → MUST use supplyAsync() for the initial computation
    // → MUST chain TWO thenApply() calls
    // → use .get() to retrieve the final result
    // ─────────────────────────────────────────────────────────────
    public static String challenge12(List<Integer> numbers, int multiplier)
            throws InterruptedException, ExecutionException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — CompletableFuture.supplyAsync(sum).thenApply(*mult).thenApply("Result: "+x).get()
        return CompletableFuture.supplyAsync(() -> numbers.stream().mapToInt(Integer::intValue).sum())
                .thenApply(sum -> sum * multiplier)
                .thenApply(result -> "Result: " + result)
                .get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 13
    // Use CompletableFuture.allOf() to run N async computations
    // in parallel and wait for ALL to complete.
    //
    // Given a list of numbers, square each number asynchronously.
    // Use allOf() to wait for all futures, then collect results
    // in original order.
    //
    // Input:  numbers=[1,2,3,4,5]
    // Output: [1,4,9,16,25]
    //
    // Rules:
    // → MUST use supplyAsync() for each number
    // → MUST use CompletableFuture.allOf() to wait
    // → collect results in ORIGINAL ORDER after allOf
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge13(List<Integer> numbers)
            throws InterruptedException, ExecutionException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — List<CompletableFuture<Integer>> futures = each supplyAsync(n*n)
        //        CompletableFuture.allOf(futures.toArray(new CF[0])).get()
        //        collect results: futures.stream().map(CompletableFuture::join).toList()
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int n : numbers) {
            futures.add(CompletableFuture.supplyAsync(() -> n * n));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();

        List<Integer> result = futures.stream().map(CompletableFuture::join).toList();
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 14
    // Use CountDownLatch to ensure all threads START at the same time.
    // Create a "starting gun" latch initialized to 1.
    // All threads wait on the latch, then main thread counts it down.
    // Each thread records its start time, does work, records end time.
    // Return the count of threads that successfully ran.
    //
    // Input:  threadCount=10
    // Output: 10  (all threads ran)
    //
    // Rules:
    // → MUST use CountDownLatch(1) as starting gun
    // → ALL threads must be created and waiting BEFORE latch.countDown()
    // → use another CountDownLatch(threadCount) to wait for all to finish
    // ─────────────────────────────────────────────────────────────
    public static int challenge14(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — CountDownLatch startGun = new CountDownLatch(1)
        //        CountDownLatch doneLatch = new CountDownLatch(threadCount)
        //        threads: startGun.await(), do work, counter.incrementAndGet(), doneLatch.countDown()
        //        after starting all: startGun.countDown()
        //        doneLatch.await()
        //        return counter.get()
        CountDownLatch startGun = new CountDownLatch(1);
        CountDownLatch finishLine = new CountDownLatch(threadCount);
        AtomicInteger counter = new AtomicInteger();

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    startGun.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                counter.incrementAndGet();
                finishLine.countDown();
            });
        }

        for (Thread t : threads) t.start();

        startGun.countDown();

        finishLine.await();

        return counter.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 15
    // Use Semaphore to limit concurrent access to a resource.
    // N threads try to "access" a resource simultaneously.
    // Only maxConcurrent threads can access it at once.
    // Track the MAXIMUM number of concurrent accesses observed.
    // Return that maximum.
    //
    // Input:  threadCount=20, maxConcurrent=3
    // Output: 3  (never more than 3 concurrent!)
    //
    // Rules:
    // → MUST use Semaphore(maxConcurrent)
    // → each thread: acquire() → do work → release()
    // → track concurrent count with AtomicInteger
    // → return the max concurrent access observed
    // ─────────────────────────────────────────────────────────────
    public static int challenge15(int threadCount, int maxConcurrent)
            throws InterruptedException {
        if (threadCount <= 0 || maxConcurrent <= 0)
            throw new IllegalArgumentException("Must be positive");
        // TODO — Semaphore sem = new Semaphore(maxConcurrent)
        //        AtomicInteger current = 0, maxSeen = 0
        //        thread: sem.acquire(), current++, maxSeen=max(maxSeen,current), current--, sem.release()
        //        join all, return maxSeen.get()
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 16
    // Use CompletableFuture.thenCombine() to combine results
    // of TWO independent async computations.
    //
    // Task 1: compute sum of list1 (async)
    // Task 2: compute product of list2 (async) (multiply all elements)
    // Combine: sum + product → return as "sum=X product=Y total=Z"
    //
    // Input:  list1=[1,2,3,4], list2=[1,2,3,4]
    //   sum=10, product=24
    // Output: "sum=10 product=24 total=34"
    //
    // Rules:
    // → MUST use TWO supplyAsync() calls
    // → MUST combine with thenCombine()
    // → get() to retrieve result
    // ─────────────────────────────────────────────────────────────
    public static String challenge16(List<Integer> list1, List<Integer> list2)
            throws InterruptedException, ExecutionException {
        if (list1 == null || list2 == null) throw new IllegalArgumentException("Lists cannot be null");
        // TODO — CompletableFuture<Integer> sumFuture    = supplyAsync(sum of list1)
        //        CompletableFuture<Integer> productFuture = supplyAsync(product of list2)
        //        thenCombine((s,p) -> "sum="+s+" product="+p+" total="+(s+p))
        //        .get()
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 17
    // Use ExecutorService.invokeAll() with Callable<int[]> to find
    // the MIN and MAX of each array CHUNK in parallel.
    // Return the global [min, max] across all chunks.
    //
    // Input:  array=[5,3,8,1,9,2,7,4,6], chunkCount=3
    //   chunk1=[5,3,8]  min=3, max=8
    //   chunk2=[1,9,2]  min=1, max=9
    //   chunk3=[7,4,6]  min=4, max=7
    //   global: min=1, max=9
    // Output: [1, 9]
    //
    // Rules:
    // → MUST use invokeAll() with List<Callable<int[]>>
    // → each Callable returns int[]{chunkMin, chunkMax}
    // → combine chunk results to find global min and max
    // ─────────────────────────────────────────────────────────────
    public static int[] challenge17(int[] array, int chunkCount)
            throws InterruptedException, ExecutionException {
        if (array == null)   throw new IllegalArgumentException("Array cannot be null");
        if (chunkCount <= 0) throw new IllegalArgumentException("chunkCount must be positive");
        // TODO — split into chunks, create Callable<int[]> per chunk
        //        invokeAll(callables), combine: globalMin=min of all chunkMins, globalMax=max of all chunkMaxs
        return new int[]{0, 0};
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 18
    // Use CyclicBarrier for a TWO-PHASE parallel computation.
    //
    // Phase 1: Each thread finds the MAX value in its chunk
    //          → stores in a shared array sharedMax[threadIdx]
    // CyclicBarrier → all threads wait here!
    // Phase 2: After barrier, each thread reads the GLOBAL max
    //          (max of all sharedMax values) and counts how many
    //          elements in its chunk equal the global max.
    //
    // Return the total count of elements equal to the global max.
    //
    // Input:  array=[3,9,2,9,1,5,9,4], threadCount=2
    //   chunk1=[3,9,2,9]  phase1 max=9
    //   chunk2=[1,5,9,4]  phase1 max=9
    //   globalMax=9
    //   chunk1 count of 9: 2
    //   chunk2 count of 9: 1
    // Output: 3
    //
    // Rules:
    // → MUST use CyclicBarrier(threadCount)
    // → phase 1 happens BEFORE barrier
    // → phase 2 happens AFTER barrier
    // ─────────────────────────────────────────────────────────────
    public static int challenge18(int[] array, int threadCount)
            throws InterruptedException, BrokenBarrierException {
        if (array == null)    throw new IllegalArgumentException("Array cannot be null");
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — int[] chunkMaxes = new int[threadCount]
        //        CyclicBarrier barrier = new CyclicBarrier(threadCount)
        //        each thread:
        //          phase1: find max in chunk → chunkMaxes[idx] = max
        //          barrier.await() ← all wait here!
        //          phase2: globalMax = Arrays.stream(chunkMaxes).max()
        //                  count elements == globalMax in chunk
        //        join all, return total count
        return 0;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenges 19–20)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 19
    // Parallel Array Sum using ForkJoinPool and RecursiveTask.
    //
    // Implement a divide-and-conquer parallel sum:
    // → If chunk size <= threshold → compute sum directly (base case)
    // → Otherwise → split in half, fork both halves, join and combine
    //
    // Input:  array=[1,2,3,4,5,6,7,8,9,10], threshold=3
    //   split: [1,2,3,4,5] and [6,7,8,9,10]
    //   split: [1,2,3] + [4,5] and [6,7,8] + [9,10]
    //   base cases: sum directly
    //   combine: 6+9=15 and 21+19=40 → 15+40=55
    // Output: 55
    //
    // Hint:
    // Step 1 — create inner class SumTask extends RecursiveTask<Long>
    //          fields: int[] array, int start, int end, int threshold
    //
    // Step 2 — override compute():
    //          if (end - start <= threshold):
    //            sum array[start..end] directly → return sum
    //          else:
    //            int mid = (start + end) / 2
    //            SumTask left  = new SumTask(array, start, mid, threshold)
    //            SumTask right = new SumTask(array, mid, end, threshold)
    //            left.fork()           ← run left in parallel!
    //            long rightResult = right.compute() ← run right in current thread!
    //            long leftResult  = left.join()     ← wait for left!
    //            return leftResult + rightResult
    //
    // Step 3 — run with ForkJoinPool:
    //          ForkJoinPool pool = new ForkJoinPool()
    //          return pool.invoke(new SumTask(array, 0, array.length, threshold))
    // ─────────────────────────────────────────────────────────────
    public static long challenge19(int[] array, int threshold) {
        if (array == null)    throw new IllegalArgumentException("Array cannot be null");
        if (threshold <= 0)   throw new IllegalArgumentException("threshold must be positive");
        // TODO — implement RecursiveTask<Long> SumTask inside this method or as inner class
        //        use ForkJoinPool to invoke it
        return 0L;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 20
    // Multi-Producer Multi-Consumer with BlockingQueue.
    // Multiple producers push items, multiple consumers process them
    // and build a frequency map of processed items.
    //
    // Each item format: "CATEGORY:VALUE" (e.g. "FOOD:apple")
    // Consumers count items per CATEGORY.
    //
    // Use POISON PILL to signal consumers to stop:
    // → After ALL producers finish, send ONE poison pill per consumer
    //
    // Return Map<String, Long> (category → total count of items)
    //
    // Input:  items=["FOOD:apple","TECH:phone","FOOD:banana","TECH:laptop","FOOD:cherry"]
    //         producerCount=2, consumerCount=3
    // Output: {"FOOD"=3, "TECH"=2}
    //
    // Hint:
    // Step 1 — LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>()
    //          String POISON = "POISON"
    //          ConcurrentHashMap<String, Long> counts = new ConcurrentHashMap<>()
    //
    // Step 2 — producers: split items into producerCount chunks
    //          each producer: for each item in chunk → queue.put(item)
    //          after all producers done: put POISON pill for EACH consumer
    //          → use CountDownLatch(producerCount) to know when all producers done
    //          → producer signals latch, a separate coordinator thread puts N poison pills
    //
    // Step 3 — consumers: poll from queue until POISON received
    //          String item = queue.take()
    //          if POISON → break
    //          String category = item.split(":")[0]
    //          counts.merge(category, 1L, Long::sum)
    //
    // Step 4 — join all, return counts
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Long> challenge20(List<String> items,
                                                int producerCount, int consumerCount) throws InterruptedException {
        if (items == null)       throw new IllegalArgumentException("Items cannot be null");
        if (producerCount <= 0)  throw new IllegalArgumentException("producerCount must be positive");
        if (consumerCount <= 0)  throw new IllegalArgumentException("consumerCount must be positive");
        // TODO — see hints above
        return new ConcurrentHashMap<>();
    }
}