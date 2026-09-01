package dev.perfectbogus.threading;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.stream.*;

public class ThreadingChallenges2 {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — volatile flag to stop a thread gracefully
    //
    // Start a thread that increments a counter in a loop.
    // After sleepMs milliseconds, set a volatile boolean flag to
    // signal the thread to STOP.
    // Join the thread and return the final counter value.
    //
    // Rules:
    // → MUST use volatile boolean running flag (not AtomicBoolean!)
    // → thread loops: while (running) counter++
    // → main thread sleeps sleepMs then sets running=false
    // → join thread, return counter.get()
    // ─────────────────────────────────────────────────────────────
    public static long challenge1(long sleepMs) throws InterruptedException {
        if (sleepMs <= 0) throw new IllegalArgumentException("sleepMs must be positive");
        // TODO — volatile boolean running = true
        //        AtomicLong counter = new AtomicLong()
        //        Thread t = new Thread(() -> { while(running) counter.incrementAndGet() })
        //        t.start(), Thread.sleep(sleepMs), running=false, t.join()
        //        return counter.get()
        AtomicBoolean running = new AtomicBoolean(true);
        AtomicLong counter = new AtomicLong();

        Thread t = new Thread(() -> {
            while (running.get()) {
                counter.incrementAndGet();
            }
        });

        t.start();
        Thread.sleep(sleepMs);
        running.set(false);
        t.join();

        return counter.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — ThreadLocal<Integer> per-thread counters
    //
    // Create N threads. Each thread uses a ThreadLocal<Integer>
    // to store its own counter (initialized to 0).
    // Each thread sets its ThreadLocal to its thread index (0..N-1).
    // Collect all ThreadLocal values into a list and return sorted.
    //
    // Input:  threadCount=5
    // Output: [0,1,2,3,4]  ← each thread stored its own index
    //
    // Rules:
    // → MUST use ThreadLocal<Integer>
    // → each thread sets threadLocal.set(index)
    // → collect values into shared CopyOnWriteArrayList
    // → join all, return sorted list
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge2(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — ThreadLocal<Integer> local = new ThreadLocal<>()
        //        CopyOnWriteArrayList<Integer> results = new CopyOnWriteArrayList<>()
        //        each thread: local.set(index), results.add(local.get())
        //        join all, return results sorted
        ThreadLocal<Integer> local = new ThreadLocal<>();
        List<Integer> list = new CopyOnWriteArrayList<>();
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int j = i;
            threads[i] = new Thread(() -> {
                local.set(j);
                list.add(local.get());
                local.remove();
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return list.stream().sorted().toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — AtomicReference CAS (compareAndSet)
    //
    // N threads each try to be the FIRST to set a shared
    // AtomicReference<String> using compareAndSet().
    // compareAndSet(expected, update):
    //   → if current value == expected → set to update, return true (winner!)
    //   → otherwise → do nothing, return false (lost the race!)
    //
    // Only ONE thread wins. Return the winning thread name.
    //
    // Input:  threadCount=10
    // Output: name of the thread that won (e.g. "Thread-0")
    //         (exactly one winner!)
    //
    // Rules:
    // → MUST use AtomicReference<String> initialized to null
    // → each thread: ref.compareAndSet(null, Thread.currentThread().getName())
    // → join all, return ref.get() (always exactly one winner!)
    // ─────────────────────────────────────────────────────────────
    public static String challenge3(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — AtomicReference<String> ref = new AtomicReference<>(null)
        //        threads: ref.compareAndSet(null, Thread.currentThread().getName())
        //        join all, return ref.get()
        AtomicReference<String> ref = new AtomicReference<>();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                ref.compareAndSet(null, Thread.currentThread().getName());
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return ref.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — synchronized block with explicit lock object
    //
    // N threads each increment a shared counter M times.
    // Use a synchronized block on an EXPLICIT lock object
    // (NOT a synchronized method or AtomicInteger!).
    //
    // Return final counter value (always N * M).
    //
    // Rules:
    // → MUST use synchronized(lockObject) { counter++ }
    // → lock object must be a separate Object (not 'this'!)
    // → NOT allowed to use AtomicInteger or synchronized methods
    // → join all before returning
    // ─────────────────────────────────────────────────────────────
    public static int challenge4(int threadCount, int incrementsPerThread)
            throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — final Object lock = new Object()
        //        int[] counter = {0}  (array trick for lambda capture!)
        //        threads: synchronized(lock) { counter[0]++ }
        //        join all, return counter[0]
        final Object lock = new Object();
        Thread[] threads = new Thread[threadCount];
        int[] counter = new int[]{0};

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    synchronized (lock) {
                        counter[0]++;
                    }
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return counter[0];
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Thread.join(timeoutMs) with timeout
    //
    // Start a thread that sleeps for sleepMs milliseconds.
    // Wait for it using join(timeoutMs).
    // Return true if thread finished within timeout, false if it timed out.
    //
    // Input:  sleepMs=50,  timeoutMs=200 → true  (finished in time!)
    // Input:  sleepMs=500, timeoutMs=100 → false (timed out!)
    //
    // Rules:
    // → MUST use thread.join(timeoutMs) (NOT Thread.sleep!)
    // → check thread.isAlive() after join to determine if timed out
    // → return true if NOT alive (finished), false if still alive (timeout)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge5(long sleepMs, long timeoutMs)
            throws InterruptedException {
        // TODO — Thread t = new Thread(() -> Thread.sleep(sleepMs))
        //        t.start()
        //        t.join(timeoutMs)
        //        return !t.isAlive()
        Thread t = new Thread(() -> {
            try { Thread.sleep(sleepMs); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); };
        });

        t.start();
        t.join(timeoutMs);

        return !t.isAlive();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — Executors.newCachedThreadPool()
    //
    // Use a CACHED thread pool to submit taskCount tasks.
    // Each task returns the name of its executing thread.
    // Return the set of unique thread names used.
    //
    // newCachedThreadPool():
    // → creates NEW threads as needed
    // → REUSES idle threads
    // → no fixed limit on thread count!
    //
    // Input:  taskCount=5
    // Output: set of thread names used (size >= 1)
    //
    // Rules:
    // → MUST use Executors.newCachedThreadPool()
    // → each task returns Thread.currentThread().getName()
    // → shutdown executor and await termination
    // ─────────────────────────────────────────────────────────────
    public static Set<String> challenge6(int taskCount)
            throws ExecutionException, InterruptedException {
        if (taskCount <= 0) throw new IllegalArgumentException("taskCount must be positive");
        // TODO — ExecutorService exec = Executors.newCachedThreadPool()
        //        submit taskCount Callables returning thread name
        //        collect results into Set<String>
        //        shutdown + awaitTermination
        Set<String> set = new HashSet<>();
        List<Future<String>> futures = new ArrayList<>();
        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < taskCount; i++) {
                Callable<String> task = () -> Thread.currentThread().getName();
                futures.add(executor.submit(task));
            }

            for (Future<String> f : futures) {
                set.add(f.get());
            }

            executor.shutdown();
        }
        return set;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — ExecutorService.invokeAny()
    //
    // Submit N Callables to an ExecutorService.
    // Each Callable sleeps for a random time then returns its index.
    // invokeAny() returns the FIRST successful result and cancels the rest.
    //
    // Input:  taskCount=5, sleepTimes=[300,50,200,400,100]
    // Output: 1 (index of task with sleepTime=50, finishes first!)
    //
    // Rules:
    // → MUST use executor.invokeAny(callables)
    // → each Callable: sleep(sleepTimes[i]), return i
    // → invokeAny() returns first result, cancels rest automatically!
    // ─────────────────────────────────────────────────────────────
    public static int challenge7(List<Long> sleepTimes)
            throws InterruptedException, ExecutionException {
        if (sleepTimes == null || sleepTimes.isEmpty())
            throw new IllegalArgumentException("sleepTimes cannot be null or empty");
        // TODO — ExecutorService exec = Executors.newFixedThreadPool(sleepTimes.size())
        //        List<Callable<Integer>> callables = each sleeps sleepTimes.get(i), returns i
        //        int result = exec.invokeAny(callables)
        //        exec.shutdown()
        //        return result
        ExecutorService executor = Executors.newFixedThreadPool(sleepTimes.size());
        List<Callable<Long>> callables = new ArrayList<>();

        for (int i = 0; i < sleepTimes.size(); i++) {
            final long l = sleepTimes.get(i);
            final long j = i;
            callables.add(() -> {
                try {
                    Thread.sleep(l);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return j;
            });
        }

        Long result = executor.invokeAny(callables);

        executor.shutdown();

        return result.intValue();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — AtomicInteger.compareAndSet() one-shot flag
    //
    // N threads each try to increment a shared AtomicInteger
    // using compareAndSet() in a loop until they succeed.
    //
    // This implements a SPIN LOCK pattern:
    //   while (!atomic.compareAndSet(current, current+1)) {
    //     current = atomic.get(); // re-read and try again!
    //   }
    //
    // Return final value (always exactly N after N threads each increment once).
    //
    // Rules:
    // → MUST use compareAndSet() in a loop (NOT incrementAndGet!)
    // → each thread increments exactly ONCE
    // → join all, return atomic.get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge8(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — AtomicInteger atomic = new AtomicInteger(0)
        //        each thread: int cur; do { cur=atomic.get() } while(!atomic.compareAndSet(cur,cur+1))
        //        join all, return atomic.get()
        AtomicInteger atomic = new AtomicInteger(0);
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                int current = atomic.get();
                while (!atomic.compareAndSet(current, current + 1)) {
                    current = atomic.get();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return atomic.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Thread.interrupt() + isInterrupted()
    //
    // Start a thread that runs a loop checking isInterrupted().
    // After sleepMs milliseconds, interrupt the thread from main.
    // The thread counts iterations before being interrupted.
    // Return the iteration count.
    //
    // Rules:
    // → thread loops: while(!Thread.currentThread().isInterrupted()) count++
    // → main: t.start(), sleep(sleepMs), t.interrupt(), t.join()
    // → return count.get() (count > 0 always!)
    // ─────────────────────────────────────────────────────────────
    public static long challenge9(long sleepMs) throws InterruptedException {
        if (sleepMs <= 0) throw new IllegalArgumentException("sleepMs must be positive");
        // TODO — AtomicLong count
        //        thread: while(!Thread.currentThread().isInterrupted()) count.incrementAndGet()
        //        t.start(), sleep(sleepMs), t.interrupt(), t.join()
        //        return count.get()
        AtomicLong count = new AtomicLong(0);

        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                count.incrementAndGet();
            }
        });

        t.start();
        Thread.sleep(sleepMs);
        t.interrupt();
        t.join();

        return count.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — CopyOnWriteArrayList thread-safe collection
    //
    // N threads each add their index to a shared CopyOnWriteArrayList.
    // Wait for all threads, then return the sorted list.
    //
    // CopyOnWriteArrayList:
    // → thread-safe for reads AND writes
    // → write operations copy the underlying array
    // → perfect for: few writes, many reads
    //
    // Input:  threadCount=5
    // Output: [0,1,2,3,4] (sorted!)
    //
    // Rules:
    // → MUST use CopyOnWriteArrayList<Integer>
    // → NO synchronized, NO AtomicInteger
    // → each thread: list.add(index)
    // → join all, return sorted list
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge10(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>()
        //        threads: list.add(index)
        //        join all, return sorted list
        List<Integer> list = new CopyOnWriteArrayList<>();
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int idx = i;
            threads[i] = new Thread(() -> {
                list.add(idx);
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return list.stream().sorted().toList();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 11–18)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 11 — ReadWriteLock: multiple readers, exclusive writer
    //
    // Implement a thread-safe cache using ReadWriteLock:
    // → Multiple READER threads can read simultaneously
    // → Only ONE WRITER thread can write at a time
    // → Writers BLOCK all readers while writing!
    //
    // Given N reader threads and M writer threads:
    // → Writers: write incrementing values (0,1,2...) to shared int[]
    // → Readers: read current value, add to AtomicLong total
    //
    // Return total reads performed (count of successful reads).
    //
    // Rules:
    // → MUST use ReentrantReadWriteLock
    // → readers use lock.readLock().lock()/unlock()
    // → writers use lock.writeLock().lock()/unlock()
    // → join all threads, return readCount.get()
    // ─────────────────────────────────────────────────────────────
    public static long challenge11(int readerCount, int writerCount, int operationsEach)
            throws InterruptedException {
        if (readerCount <= 0 || writerCount <= 0)
            throw new IllegalArgumentException("Counts must be positive");
        // TODO — ReentrantReadWriteLock lock = new ReentrantReadWriteLock()
        //        int[] sharedValue = {0}
        //        AtomicLong readCount = new AtomicLong()
        //        readers: lock.readLock().lock(), readCount.incrementAndGet(), lock.readLock().unlock()
        //        writers: lock.writeLock().lock(), sharedValue[0]++, lock.writeLock().unlock()
        //        join all, return readCount.get()
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        int[] sharedValue = {0};
        AtomicLong readCount = new AtomicLong();
        Thread[] readers = new Thread[readerCount];
        for (int i = 0; i < readerCount; i++) {
            readers[i] = new Thread(() -> {
                for (int j = 0; j < operationsEach; j++) {
                    lock.readLock().lock();
                    try { readCount.incrementAndGet(); }
                    finally { lock.readLock().unlock(); }
                }
            });
        }

        Thread[] writers = new Thread[writerCount];
        for (int i = 0; i < writerCount; i++) {
            writers[i] = new Thread(() -> {
                lock.writeLock().lock();
                sharedValue[0]++;
                lock.writeLock().unlock();
            });
        }

        for (Thread r : readers) r.start();
        for (Thread r : readers) r.join();
        for (Thread w : writers) w.start();
        for (Thread w : writers) w.join();

        return readCount.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 12 — ScheduledExecutorService periodic tasks
    //
    // Use ScheduledExecutorService to run a task at a fixed rate.
    // The task increments an AtomicInteger counter.
    // Schedule it to run every periodMs milliseconds.
    // After totalMs milliseconds, shutdown and return counter value.
    //
    // Input:  periodMs=100, totalMs=450
    //   runs at: 0ms, 100ms, 200ms, 300ms, 400ms → 5 times
    //
    // Rules:
    // → MUST use Executors.newScheduledThreadPool(1)
    // → MUST use scheduleAtFixedRate(task, 0, periodMs, MILLISECONDS)
    // → sleep totalMs then shutdownNow()
    // → return counter.get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge12(long periodMs, long totalMs)
            throws InterruptedException {
        if (periodMs <= 0 || totalMs <= 0)
            throw new IllegalArgumentException("Periods must be positive");
        // TODO — ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)
        //        AtomicInteger counter = new AtomicInteger()
        //        scheduler.scheduleAtFixedRate(counter::incrementAndGet, 0, periodMs, MILLISECONDS)
        //        Thread.sleep(totalMs)
        //        scheduler.shutdownNow()
        //        return counter.get()
        AtomicInteger counter = new AtomicInteger();
        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
            scheduler.scheduleAtFixedRate(counter::incrementAndGet, 0, periodMs, TimeUnit.MILLISECONDS);
            Thread.sleep(totalMs);
            scheduler.shutdownNow();
        }

        return counter.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 13 — Phaser for multi-phase computation
    //
    // Use a Phaser to coordinate threadCount threads across TWO phases.
    //
    // Phase 1: each thread computes its number squared → stores in results[]
    // Phaser.arriveAndAwaitAdvance() → all wait here before Phase 2!
    // Phase 2: each thread reads ALL results[] and adds to a shared sum
    //
    // Return the final sum (sum of all squared values computed twice = sum * threadCount)
    //
    // Input:  numbers=[1,2,3,4,5]
    // Phase1: results=[1,4,9,16,25]
    // Phase2: each thread sums ALL results=[1+4+9+16+25=55] → adds 55 to total
    //         5 threads × 55 = 275
    // Output: 275
    //
    // Rules:
    // → MUST use Phaser(threadCount)
    // → phase 1 before arriveAndAwaitAdvance()
    // → phase 2 after arriveAndAwaitAdvance()
    // ─────────────────────────────────────────────────────────────
    public static long challenge13(List<Integer> numbers) throws InterruptedException {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO — int[] results = new int[numbers.size()]
        //        Phaser phaser = new Phaser(numbers.size())
        //        AtomicLong total = new AtomicLong()
        //        each thread i:
        //          phase1: results[i] = numbers.get(i) * numbers.get(i)
        //          phaser.arriveAndAwaitAdvance()
        //          phase2: total.addAndGet(Arrays.stream(results).sum())
        //        join all, return total.get()
        int[] results = new int[numbers.size()];
        Phaser phaser = new Phaser(numbers.size());
        AtomicLong total = new AtomicLong();
        Thread[] threads = new Thread[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            final int j = i;
            threads[i] = new Thread(() -> {
                results[j] = numbers.get(j) * numbers.get(j);
                phaser.arriveAndAwaitAdvance();
                total.addAndGet(Arrays.stream(results).sum());
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return total.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 14 — Exchanger: two threads swap data
    //
    // Use Exchanger<String> to swap data between TWO threads.
    // Thread 1 sends "hello" and receives something from Thread 2.
    // Thread 2 sends "world" and receives something from Thread 1.
    //
    // exchanger.exchange(value) → blocks until BOTH threads call exchange!
    // Then each thread receives the OTHER's value.
    //
    // Return ExchangeResult(String fromThread1, String fromThread2)
    // where fromThread1 = what Thread1 received (= Thread2's value!)
    //       fromThread2 = what Thread2 received (= Thread1's value!)
    //
    // Output: ExchangeResult("world", "hello")
    //         fromThread1 received "world" (sent by Thread2)
    //         fromThread2 received "hello" (sent by Thread1)
    //
    // Rules:
    // → MUST use Exchanger<String>
    // → both threads call exchanger.exchange(value)
    // → join both threads, return result record
    // ─────────────────────────────────────────────────────────────
    record ExchangeResult(String fromThread1, String fromThread2) {}

    public static ExchangeResult challenge14(String value1, String value2)
            throws InterruptedException {
        if (value1 == null || value2 == null)
            throw new IllegalArgumentException("Values cannot be null");
        // TODO — Exchanger<String> exchanger = new Exchanger<>()
        //        AtomicReference<String> ref1 = new AtomicReference<>()
        //        AtomicReference<String> ref2 = new AtomicReference<>()
        //        thread1: ref1.set(exchanger.exchange(value1))
        //        thread2: ref2.set(exchanger.exchange(value2))
        //        join both
        //        return new ExchangeResult(ref1.get(), ref2.get())
        Exchanger<String> exchanger = new Exchanger<>();
        AtomicReference<String> ref1 = new AtomicReference<>();
        AtomicReference<String> ref2 = new AtomicReference<>();

        Thread t1 = new Thread(() -> {
            try {
                ref1.set(exchanger.exchange(value1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                ref2.set(exchanger.exchange(value2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return new ExchangeResult(ref1.get(), ref2.get());
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 15 — RecursiveAction for parallel array fill
    //
    // Use ForkJoinPool + RecursiveAction to fill an int[] array
    // with the SQUARE of each element's index in parallel.
    // result[i] = i * i
    //
    // RecursiveAction (no return value!):
    // → Base case (size <= threshold) → fill directly
    // → Recursive case → split in half, fork both halves!
    //
    // Input:  size=10, threshold=3
    // Output: [0,1,4,9,16,25,36,49,64,81]
    //
    // Rules:
    // → create inner class FillTask extends RecursiveAction
    // → use ForkJoinPool.invoke() to run it
    // → RecursiveAction has NO return type → just fills the array!
    // ─────────────────────────────────────────────────────────────
    public static int[] challenge15(int size, int threshold) {
        if (size <= 0)     throw new IllegalArgumentException("size must be positive");
        if (threshold <= 0) throw new IllegalArgumentException("threshold must be positive");
        // TODO — int[] result = new int[size]
        //        implement FillTask extends RecursiveAction:
        //          if end-start <= threshold: fill result[start..end] = i*i
        //          else: split, fork both halves!
        //        ForkJoinPool.commonPool().invoke(new FillTask(0, size))
        //        return result
        int[] result = new int[size];

        class FillTask extends RecursiveAction {
            private final int start;
            private final int end;

            public FillTask(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            protected void compute() {
                if (end - start <= threshold) {
                    for (int i = start; i < end; i++) {
                        result[i] = i * i;
                    }
                    return;
                } else {
                    int mid = (start + end) / 2;
                    FillTask left = new FillTask(start, mid);
                    FillTask right = new FillTask(mid, end);

                    left.fork();
                    right.compute();
                    left.join();
                }
            }
        }

        ForkJoinPool.commonPool().invoke(new FillTask(0, size));

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 16 — ReentrantLock with tryLock()
    //
    // N threads try to acquire a ReentrantLock using tryLock().
    // tryLock() returns IMMEDIATELY:
    //   → true if lock acquired
    //   → false if lock held by someone else (don't wait!)
    //
    // Each thread tries tryLock():
    //   → if acquired → increment successCount, hold lock briefly, unlock
    //   → if not acquired → increment failCount
    //
    // Return record TryLockResult(int successes, int failures)
    //
    // Rules:
    // → MUST use ReentrantLock + tryLock()
    // → MUST release lock in finally block if acquired!
    // → join all threads, return result
    // ─────────────────────────────────────────────────────────────
    record TryLockResult(int successes, int failures) {}

    public static TryLockResult challenge16(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        // TODO — ReentrantLock lock = new ReentrantLock()
        //        AtomicInteger successes = new AtomicInteger()
        //        AtomicInteger failures  = new AtomicInteger()
        //        each thread: if(lock.tryLock()) { try{successes++} finally{lock.unlock()} }
        //                     else failures++
        //        join all, return new TryLockResult(successes.get(), failures.get())
        ReentrantLock lock = new ReentrantLock();
        AtomicInteger successes = new AtomicInteger(0);
        AtomicInteger failures = new AtomicInteger(0);

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                if (lock.tryLock()) {
                    try {
                        successes.incrementAndGet();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    failures.incrementAndGet();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return new TryLockResult(successes.get(), failures.get());
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 17 — LinkedTransferQueue synchronous handoff
    //
    // Use LinkedTransferQueue for a SYNCHRONOUS producer-consumer.
    // transfer() blocks until a CONSUMER takes the item!
    // (unlike put() which just adds to queue immediately)
    //
    // 1 producer transfers N items one by one (blocks until each taken!)
    // 1 consumer takes each item and adds to a sum
    // Return the sum of all consumed items.
    //
    // Input:  items=[1,2,3,4,5]
    // Output: 15
    //
    // Rules:
    // → MUST use LinkedTransferQueue<Integer>
    // → producer: queue.transfer(item) ← blocks until consumer takes it!
    // → consumer: queue.take() in a loop N times
    // → join both, return sum.get()
    // ─────────────────────────────────────────────────────────────
    public static long challenge17(List<Integer> items) throws InterruptedException {
        if (items == null) throw new IllegalArgumentException("Items cannot be null");
        // TODO — LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>()
        //        AtomicLong sum = new AtomicLong()
        //        producer thread: items.forEach(i -> queue.transfer(i))
        //        consumer thread: for each item: sum.addAndGet(queue.take())
        //        join both, return sum.get()
        LinkedTransferQueue<Integer> q = new LinkedTransferQueue<>();
        AtomicLong sum = new AtomicLong();

        Thread producer = new Thread(() -> {
            for (Integer item : items) {
                try {
                    q.transfer(item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < items.size(); i++) {
                try {
                    sum.addAndGet(q.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        return sum.get();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 18 — ThreadPoolExecutor with custom settings
    //
    // Create a ThreadPoolExecutor with:
    // → corePoolSize   = 2
    // → maximumPoolSize = 4
    // → keepAliveTime  = 60 seconds
    // → workQueue      = ArrayBlockingQueue(10)
    //
    // Submit taskCount tasks, each returning its executing thread name.
    // Collect unique thread names and return as a Set.
    //
    // Input:  taskCount=8
    // Output: Set of thread names (size between 1 and 4!)
    //
    // Rules:
    // → MUST use new ThreadPoolExecutor(...) directly (not Executors factory!)
    // → shutdown and awaitTermination after collecting results
    // ─────────────────────────────────────────────────────────────
    public static Set<String> challenge18(int taskCount)
            throws ExecutionException, InterruptedException {
        if (taskCount <= 0) throw new IllegalArgumentException("taskCount must be positive");
        // TODO — ThreadPoolExecutor executor = new ThreadPoolExecutor(
        //            2, 4, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10))
        //        submit taskCount Callables returning Thread.currentThread().getName()
        //        collect results into Set<String>
        //        executor.shutdown() + awaitTermination
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
        List<Future<String>> futures = new ArrayList<>();
        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS, workQueue)) {
            for (int i = 0; i < taskCount; i++) {
                futures.add(executor.submit(() -> Thread.currentThread().getName()));
            }
        }

        Set<String> set = new HashSet<>();
        for (Future<String> f : futures) {
            set.add(f.get());
        }

        return set;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenges 19–20)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 19 — Parallel Merge Sort using ForkJoinPool + RecursiveAction
    //
    // Implement parallel merge sort using ForkJoinPool.
    // Sort an int[] array in place using divide-and-conquer.
    //
    // Hint:
    // Step 1 — Create inner class MergeSortTask extends RecursiveAction:
    //          fields: int[] array, int start, int end, int threshold
    //
    // Step 2 — override compute():
    //          if (end - start <= threshold):
    //            sort directly: Arrays.sort(array, start, end)  ← base case!
    //          else:
    //            int mid = (start + end) / 2
    //            MergeSortTask left  = new MergeSortTask(array, start, mid, threshold)
    //            MergeSortTask right = new MergeSortTask(array, mid,   end, threshold)
    //            left.fork()              ← sort left half in parallel!
    //            right.compute()          ← sort right half in current thread!
    //            left.join()              ← wait for left!
    //            merge(array, start, mid, end) ← merge both halves!
    //
    // Step 3 — merge(array, start, mid, end):
    //          copy array[start..end] to temp[]
    //          merge temp[start..mid] and temp[mid..end] back into array
    //
    // Step 4 — run with ForkJoinPool:
    //          new ForkJoinPool().invoke(new MergeSortTask(array, 0, array.length, threshold))
    //          return array  ← sorted in place!
    // ─────────────────────────────────────────────────────────────
    public static int[] challenge19(int[] array, int threshold) {
        if (array == null)     throw new IllegalArgumentException("Array cannot be null");
        if (threshold <= 0)    throw new IllegalArgumentException("threshold must be positive");
        // TODO — implement MergeSortTask extends RecursiveAction
        //        use ForkJoinPool to invoke
        return array;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 20 — Custom ThreadPool from scratch
    //
    // Implement a simple ThreadPool using:
    // → A fixed array of worker Thread objects
    // → A LinkedBlockingQueue<Runnable> as the task queue
    // → Workers poll from queue and execute tasks
    //
    // Submit taskCount tasks that each increment a shared AtomicInteger.
    // Shutdown the pool gracefully after all tasks complete.
    // Return final counter value (should always equal taskCount).
    //
    // Hint:
    // Step 1 — create worker threads:
    //          Thread[] workers = new Thread[poolSize]
    //          each worker: while(!Thread.currentThread().isInterrupted()) {
    //            Runnable task = queue.poll(100, MILLISECONDS)
    //            if (task != null) task.run()
    //          }
    //
    // Step 2 — submit tasks:
    //          queue.put(task) for each of taskCount tasks
    //          each task: counter.incrementAndGet()
    //
    // Step 3 — wait for queue to drain, then interrupt workers:
    //          while (!queue.isEmpty()) Thread.sleep(10)
    //          Thread.sleep(50)  ← allow last tasks to complete
    //          for (Thread w : workers) w.interrupt()
    //          for (Thread w : workers) w.join()
    //
    // Step 4 — return counter.get()
    // ─────────────────────────────────────────────────────────────
    public static int challenge20(int poolSize, int taskCount) throws InterruptedException {
        if (poolSize  <= 0) throw new IllegalArgumentException("poolSize must be positive");
        if (taskCount <= 0) throw new IllegalArgumentException("taskCount must be positive");
        // TODO — see hints above
        return 0;
    }
}