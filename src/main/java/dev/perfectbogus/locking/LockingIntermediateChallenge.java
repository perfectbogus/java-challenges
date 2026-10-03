package dev.perfectbogus.locking;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockingIntermediateChallenge {

    // Used by CHALLENGE 1, 2. getCount() is fully implemented — nothing
    // to do there.
    public static class SyncCounter {
        private int count;
        private final Object lock = new Object();

        // CHALLENGE 1
        // Increments count by 1. Must be safe to call concurrently from
        // many threads with no lost updates, using the synchronized
        // keyword on the method itself.
        public synchronized void incrementSynchronized() {
            count++;
        }

        // CHALLENGE 2
        // Increments count by 1. Must be safe to call concurrently from
        // many threads with no lost updates, using a synchronized
        // statement (block) rather than a synchronized method.
        public void incrementWithBlock() {
            synchronized (lock) {
                count++;
            }
        }

        public synchronized int getCount() {
            return count;
        }
    }

    // Used by CHALLENGE 3, 4, 5, 6. getCount() and holdLockFor() are
    // fully implemented — nothing to do there. holdLockFor() exists only
    // so tests can simulate another thread holding the lock.
    public static class LockCounter {
        private final ReentrantLock lock = new ReentrantLock();
        private int count;

        // CHALLENGE 3
        // Increments count by 1, using lock to guard the update. Must
        // release the lock in all cases, even if an exception were to
        // occur while it is held.
        public void incrementWithLock() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        // CHALLENGE 4
        // Returns the sum 1 + 2 + ... + n (or 0 if n <= 0), computed by
        // acquiring lock and then, for n > 0, recursively calling itself
        // with n - 1 while still holding lock, adding n to the result.
        // Demonstrates that a thread already holding the lock can safely
        // reacquire it.
        public int recursiveSum(int n) {
            if (n <= 0) return 0;
            lock.lock();
            try {
                return n + recursiveSum(n - 1);
            } finally {
                lock.unlock();
            }
        }

        // CHALLENGE 5
        // Attempts to increment count by 1 without blocking: if the lock
        // is immediately available, acquires it, increments count,
        // releases the lock, and returns true. If the lock is currently
        // held by another thread, returns false immediately, without
        // waiting and without modifying count.
        public boolean tryIncrementIfAvailable() {
            if (lock.tryLock()) {
                try{
                    count++;
                    return true;
                } finally {
                    lock.unlock();
                }
            }
            return false;
        }

        // CHALLENGE 6
        // Attempts to increment count by 1, waiting up to timeoutMillis
        // milliseconds to acquire the lock. Returns true and increments
        // count if the lock was acquired within that time; returns false
        // if the timeout elapsed before the lock became available.
        public boolean incrementWithTimeout(long timeoutMillis) throws InterruptedException {
            if (lock.tryLock(timeoutMillis, TimeUnit.MILLISECONDS)) {
                try {
                    count++;
                    return true;
                } finally {
                    lock.unlock();
                }
            }
            return false;
        }

        public int getCount() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }

        public void holdLockFor(long millis) throws InterruptedException {
            lock.lock();
            try {
                Thread.sleep(millis);
            } finally {
                lock.unlock();
            }
        }
    }

    // Used by CHALLENGE 7, 8. The constructor and size() are fully
    // implemented — nothing to do there.
    public static class BoundedBuffer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        public BoundedBuffer(int capacity) {
            this.capacity = capacity;
        }

        // CHALLENGE 7
        // Adds value to the buffer. If the buffer is already at
        // capacity, blocks until another thread removes an item (making
        // room), then adds value.
        public void put(int value) throws InterruptedException {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        // CHALLENGE 8
        // Removes and returns the oldest value in the buffer. If the
        // buffer is empty, blocks until another thread adds an item.
        public int take() throws InterruptedException {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        public int size() {
            lock.lock();
            try {
                return queue.size();
            } finally {
                lock.unlock();
            }
        }
    }

    // Used by CHALLENGE 9. Fully implemented — nothing to do here.
    public static class Account {
        private final int id;
        private int balance;
        private final ReentrantLock lock = new ReentrantLock();

        public Account(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        public int getId() { return id; }

        public int getBalance() {
            lock.lock();
            try {
                return balance;
            } finally {
                lock.unlock();
            }
        }

        ReentrantLock getLock() { return lock; }

        void deposit(int amount) { balance += amount; }
        void withdraw(int amount) { balance -= amount; }
    }

    // CHALLENGE 9
    // Transfers amount from from's balance to to's balance. Must acquire
    // both accounts' locks before moving the money, and must do so in a
    // way that is safe from deadlock no matter which order concurrent
    // calls pass the same two accounts in (e.g. one thread calling
    // transfer(a, b, amount) while another thread simultaneously calls
    // transfer(b, a, amount)).
    public static void transfer(Account from, Account to, int amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Releases lock if, and only if, it is currently held by the calling
    // thread. Does nothing (and must not throw) if the calling thread
    // does not currently hold lock, whether because nobody holds it or
    // because a different thread holds it.
    public static void unlockIfHeld(ReentrantLock lock) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
