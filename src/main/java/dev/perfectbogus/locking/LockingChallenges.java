package dev.perfectbogus.locking;

import java.util.*;
import java.util.concurrent.locks.*;

public class LockingChallenges {

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — synchronized method
    //
    // Implement a thread-safe counter using a SYNCHRONIZED METHOD.
    // N threads each call increment() M times on the same counter.
    // Return the final count (always N × M).
    //
    // Create inner class SynchronizedCounter:
    //   private int count = 0
    //   public synchronized void increment()
    //   public synchronized int getCount()
    //
    // Input:  threadCount=10, incrementsPerThread=1000
    // Output: 10000
    // ─────────────────────────────────────────────────────────────
    static class SynchronizedCounter {
        private int count = 0;

        public synchronized void increment() { count++; }
        public synchronized int getCount()   { return count; }
    }

    public static int challenge1(int threadCount, int incrementsPerThread)
            throws InterruptedException {
        if (threadCount <= 0 || incrementsPerThread <= 0)
            throw new IllegalArgumentException("Values must be positive");
        SynchronizedCounter counter = new SynchronizedCounter();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return counter.getCount();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — synchronized block with explicit lock object
    //
    // Implement a thread-safe bank account using a SYNCHRONIZED BLOCK
    // on an EXPLICIT lock object (NOT synchronized method!).
    // N threads each perform a deposit and a withdrawal concurrently.
    //
    // Create inner class BankAccount:
    //   private final Object lock = new Object()
    //   private double balance
    //   constructor: BankAccount(double initialBalance)
    //   void deposit(double amount)  → synchronized(lock) { balance += amount }
    //   void withdraw(double amount) → synchronized(lock) { balance -= amount }
    //   double getBalance()          → synchronized(lock) { return balance }
    //
    // Test: start with 1000.0
    //   N threads each: deposit(100.0) then withdraw(100.0)
    //   → net change = 0 → final balance = 1000.0
    //
    // Return final balance.
    // ─────────────────────────────────────────────────────────────
    static class BankAccount {
        private final Object lock = new Object();
        private double balance;

        BankAccount(double initialBalance) { this.balance = initialBalance; }

        void deposit(double amount)  { synchronized (lock) { balance += amount; } }
        void withdraw(double amount) { synchronized (lock) { balance -= amount; } }
        double getBalance()          { synchronized (lock) { return balance; } }
    }

    public static double challenge2(int threadCount, double initialBalance)
            throws InterruptedException {
        if (threadCount <= 0)  throw new IllegalArgumentException("threadCount must be positive");
        if (initialBalance < 0) throw new IllegalArgumentException("Balance must be non-negative");
        BankAccount ba = new BankAccount(initialBalance);
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                ba.deposit(100.0);
                ba.withdraw(100.0);
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return ba.getBalance();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — ReentrantLock basic lock/unlock
    //
    // Implement a thread-safe counter using ReentrantLock.
    // N threads each increment a shared counter M times.
    // Use lock() in a try-finally block.
    // Return final counter value (always N × M).
    //
    // Rules:
    // → MUST use ReentrantLock (not synchronized!)
    // → MUST use try-finally to unlock!
    // → int[] counter = {0} for lambda capture
    // ─────────────────────────────────────────────────────────────
    public static int challenge3(int threadCount, int incrementsPerThread)
            throws InterruptedException {
        if (threadCount <= 0 || incrementsPerThread <= 0)
            throw new IllegalArgumentException("Values must be positive");
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — tryLock() non-blocking
    //
    // N threads all try to acquire a ReentrantLock using tryLock().
    // Track how many threads SUCCEEDED vs FAILED to get the lock.
    // Each thread that gets the lock sleeps briefly (10ms) before unlocking.
    //
    // Return record TryLockStats(int acquired, int missed, int total)
    //   acquired + missed = total = threadCount
    //
    // Rules:
    // → MUST use tryLock() (no blocking wait!)
    // → thread that gets lock: sleep(10ms), then unlock
    // → thread that misses: just increments missed count
    // ─────────────────────────────────────────────────────────────
    record TryLockStats(int acquired, int missed, int total) {}

    public static TryLockStats challenge4(int threadCount) throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        return new TryLockStats(0, 0, 0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — Condition await() and signal()
    //
    // Implement a bounded buffer (capacity=1) using ReentrantLock
    // and TWO Conditions:
    //   Condition notFull  = lock.newCondition()
    //   Condition notEmpty = lock.newCondition()
    //
    // Producer: adds items to buffer one at a time
    //   if buffer full  → notFull.await()
    //   add item        → notEmpty.signal()
    //
    // Consumer: takes items from buffer one at a time
    //   if buffer empty → notEmpty.await()
    //   take item       → notFull.signal()
    //
    // Produce [1,2,3,4,5], consume all, return sum.
    //
    // Input:  items=[1,2,3,4,5]
    // Output: 15
    // ─────────────────────────────────────────────────────────────
    public static long challenge5(List<Integer> items) throws InterruptedException {
        if (items == null) throw new IllegalArgumentException("Items cannot be null");
        return 0L;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — ReadWriteLock for a thread-safe cache
    //
    // Implement a thread-safe key-value cache using ReadWriteLock:
    // → reads  use readLock()  (multiple simultaneous readers OK!)
    // → writes use writeLock() (exclusive access)
    //
    // N reader threads each read ALL keys and count non-null values.
    // M writer threads each write a new value for one key.
    //
    // Start with cache: {"a"=1, "b"=2, "c"=3}
    // Writers update: key "a" → threadIndex value
    // Readers count: how many non-null values exist.
    //
    // Return final cache size (always 3, values may have changed).
    //
    // Rules:
    // → MUST use ReentrantReadWriteLock
    // → readers: readLock for ALL reads
    // → writers: writeLock for ALL writes
    // ─────────────────────────────────────────────────────────────
    public static int challenge6(int readerCount, int writerCount)
            throws InterruptedException {
        if (readerCount <= 0 || writerCount <= 0)
            throw new IllegalArgumentException("Counts must be positive");
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — StampedLock optimistic read
    //
    // Use StampedLock to implement a thread-safe 2D point.
    // One writer thread updates x and y continuously.
    // One reader thread uses OPTIMISTIC READ to get a consistent snapshot.
    //
    // StampedLock optimistic read pattern:
    //   long stamp = lock.tryOptimisticRead()
    //   read values
    //   if (!lock.validate(stamp)) → fallback to readLock!
    //
    // Create inner class Point:
    //   StampedLock lock
    //   double x, y
    //   void write(double x, double y)  → writeLock
    //   double[] read()                 → optimistic read with fallback
    //     returns double[]{x, y}
    //
    // Verify: after writer sets (3.0, 4.0) → reader always gets
    //         a CONSISTENT snapshot (both from same write!)
    //
    // Input:  x=3.0, y=4.0
    // Output: [3.0, 4.0] (consistent! not mixed from different writes)
    // ─────────────────────────────────────────────────────────────
    static class Point {
        private final StampedLock lock = new StampedLock();
        private double x, y;

        void write(double newX, double newY) {
            long stamp = lock.writeLock();
            try { x = newX; y = newY; }
            finally { lock.unlockWrite(stamp); }
        }

        double[] read() {
            long stamp = lock.tryOptimisticRead();
            double curX = x, curY = y;
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try { curX = x; curY = y; }
                finally { lock.unlockRead(stamp); }
            }
            return new double[]{curX, curY};
        }
    }

    public static double[] challenge7(double x, double y)
            throws InterruptedException {
        return new double[]{0.0, 0.0};
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — Lock ordering to prevent deadlock
    //
    // Transfer money between two accounts WITHOUT deadlock.
    // Use consistent lock ordering: always lock lower-id account first!
    //
    // Without ordering:
    //   Thread1: lock(account1) then lock(account2) → transfers A→B
    //   Thread2: lock(account2) then lock(account1) → transfers B→A
    //   → DEADLOCK! each holds one lock and waits for the other!
    //
    // With ordering (always lock lower id first):
    //   Thread1: lock(min-id), lock(max-id) → safe!
    //   Thread2: lock(min-id), lock(max-id) → safe! (same order!)
    //
    // record Account(int id, ReentrantLock lock, AtomicDouble balance)
    //
    // Return record TransferResult(double balance1, double balance2)
    // where balance1 is final balance of account with id=1
    //       balance2 is final balance of account with id=2
    //
    // Input:  balance1=1000, balance2=1000, transferAmount=100, threadCount=10
    //   5 threads transfer 100 from account1 → account2
    //   5 threads transfer 100 from account2 → account1
    //   net: 0 change → both still 1000.0
    // ─────────────────────────────────────────────────────────────
    record TransferResult(double balance1, double balance2) {}

    public static TransferResult challenge8(double initialBalance1,
                                            double initialBalance2, double amount, int threadCount)
            throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        return new TransferResult(0.0, 0.0);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — Fair ReentrantLock (FIFO ordering)
    //
    // Use a FAIR ReentrantLock to ensure threads acquire the lock
    // in the order they requested it.
    //
    // Fair lock: new ReentrantLock(true) ← true = fair!
    //
    // N threads each: wait for lock → record their acquisition order
    //   → release lock
    //
    // Verify that ALL threads eventually acquired the lock
    // (fair lock prevents starvation!)
    //
    // Return the ORDER list of thread indices that acquired the lock.
    // With fair lock → order should be close to arrival order!
    //
    // Input:  threadCount=5
    // Output: list of size 5 (all threads got the lock!)
    //         e.g. [0,1,2,3,4] or close to arrival order
    //
    // Rules:
    // → MUST use new ReentrantLock(true) (fair mode!)
    // → collect acquisition order in a CopyOnWriteArrayList
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge9(int threadCount)
            throws InterruptedException {
        if (threadCount <= 0) throw new IllegalArgumentException("threadCount must be positive");
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — LockSupport park() and unpark()
    //
    // Use LockSupport to coordinate TWO threads:
    //
    // Worker thread:
    //   1. adds "worker: started" to log
    //   2. LockSupport.park() ← waits for permission!
    //   3. adds "worker: resumed" to log
    //   4. adds "worker: done" to log
    //
    // Main flow:
    //   1. start worker thread
    //   2. add "main: started" to log
    //   3. Thread.sleep(50ms) ← wait for worker to park
    //   4. add "main: unparking" to log
    //   5. LockSupport.unpark(worker) ← give permission!
    //   6. worker.join()
    //   7. add "main: done" to log
    //
    // Return the log list in order.
    //
    // Expected output:
    //   ["worker: started", "main: started", "main: unparking",
    //    "worker: resumed", "worker: done", "main: done"]
    //
    // Rules:
    // → MUST use LockSupport.park() in worker
    // → MUST use LockSupport.unpark(thread) in main
    // → use CopyOnWriteArrayList for thread-safe logging
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge10() throws InterruptedException {
        return new ArrayList<>();
    }
}