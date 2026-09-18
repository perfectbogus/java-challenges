package dev.perfectbogus.virtual.threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class VirtualThreadsBeginnerChallengeTest {

    // ==========================================================
    // CHALLENGE 1: createStartedVirtualThread
    // ==========================================================
    @Nested
    class CreateStartedVirtualThreadTests {

        @Test
        void testThreadIsVirtual() {
            Thread thread = VirtualThreadsBeginnerChallenge.createStartedVirtualThread(() -> {});
            assertTrue(thread.isVirtual());
        }

        @Test
        void testTaskActuallyRuns() throws InterruptedException {
            AtomicBoolean ran = new AtomicBoolean(false);
            Thread thread = VirtualThreadsBeginnerChallenge.createStartedVirtualThread(() -> ran.set(true));
            thread.join();
            assertTrue(ran.get());
        }

        @Test
        void testThreadIsAlreadyStarted() throws InterruptedException {
            AtomicInteger counter = new AtomicInteger(0);
            Thread thread = VirtualThreadsBeginnerChallenge.createStartedVirtualThread(counter::incrementAndGet);
            thread.join();
            assertEquals(1, counter.get());
        }
    }

    // ==========================================================
    // CHALLENGE 2: createUnstartedVirtualThread
    // ==========================================================
    @Nested
    class CreateUnstartedVirtualThreadTests {

        @Test
        void testThreadIsVirtual() {
            Thread thread = VirtualThreadsBeginnerChallenge.createUnstartedVirtualThread(() -> {});
            assertTrue(thread.isVirtual());
        }

        @Test
        void testTaskDoesNotRunUntilStarted() {
            AtomicBoolean ran = new AtomicBoolean(false);
            VirtualThreadsBeginnerChallenge.createUnstartedVirtualThread(() -> ran.set(true));
            assertFalse(ran.get());
        }

        @Test
        void testTaskRunsAfterManualStart() throws InterruptedException {
            AtomicBoolean ran = new AtomicBoolean(false);
            Thread thread = VirtualThreadsBeginnerChallenge.createUnstartedVirtualThread(() -> ran.set(true));
            thread.start();
            thread.join();
            assertTrue(ran.get());
        }
    }

    // ==========================================================
    // CHALLENGE 3: startVirtualThreadQuick
    // ==========================================================
    @Nested
    class StartVirtualThreadQuickTests {

        @Test
        void testThreadIsVirtual() {
            Thread thread = VirtualThreadsBeginnerChallenge.startVirtualThreadQuick(() -> {});
            assertTrue(thread.isVirtual());
        }

        @Test
        void testTaskActuallyRuns() throws InterruptedException {
            AtomicBoolean ran = new AtomicBoolean(false);
            Thread thread = VirtualThreadsBeginnerChallenge.startVirtualThreadQuick(() -> ran.set(true));
            thread.join();
            assertTrue(ran.get());
        }
    }

    // ==========================================================
    // CHALLENGE 4: isThreadVirtual
    // ==========================================================
    @Nested
    class IsThreadVirtualTests {

        @Test
        void testVirtualThreadReturnsTrue() {
            Thread thread = Thread.ofVirtual().unstarted(() -> {});
            assertTrue(VirtualThreadsBeginnerChallenge.isThreadVirtual(thread));
        }

        @Test
        void testPlatformThreadReturnsFalse() {
            Thread thread = new Thread(() -> {});
            assertFalse(VirtualThreadsBeginnerChallenge.isThreadVirtual(thread));
        }

        @Test
        void testCurrentTestThreadIsPlatform() {
            assertFalse(VirtualThreadsBeginnerChallenge.isThreadVirtual(Thread.currentThread()));
        }
    }

    // ==========================================================
    // CHALLENGE 5: createNamedVirtualThread
    // ==========================================================
    @Nested
    class CreateNamedVirtualThreadTests {

        @Test
        void testNameIsSet() {
            Thread thread = VirtualThreadsBeginnerChallenge.createNamedVirtualThread("worker-1", () -> {});
            assertEquals("worker-1", thread.getName());
        }

        @Test
        void testThreadIsVirtual() {
            Thread thread = VirtualThreadsBeginnerChallenge.createNamedVirtualThread("worker-2", () -> {});
            assertTrue(thread.isVirtual());
        }

        @Test
        void testTaskActuallyRuns() throws InterruptedException {
            AtomicBoolean ran = new AtomicBoolean(false);
            Thread thread = VirtualThreadsBeginnerChallenge.createNamedVirtualThread("worker-3", () -> ran.set(true));
            thread.join();
            assertTrue(ran.get());
        }
    }

    // ==========================================================
    // CHALLENGE 6: waitForCompletion
    // ==========================================================
    @Nested
    class WaitForCompletionTests {

        @Test
        void testBlocksUntilThreadFinishes() throws InterruptedException {
            AtomicBoolean ran = new AtomicBoolean(false);
            Thread thread = Thread.ofVirtual().start(() -> ran.set(true));
            VirtualThreadsBeginnerChallenge.waitForCompletion(thread);
            assertTrue(ran.get());
        }

        @Test
        void testThreadIsNoLongerAliveAfterwards() throws InterruptedException {
            Thread thread = Thread.ofVirtual().start(() -> {});
            VirtualThreadsBeginnerChallenge.waitForCompletion(thread);
            assertFalse(thread.isAlive());
        }
    }

    // ==========================================================
    // CHALLENGE 7: runAllTasks
    // ==========================================================
    @Nested
    class RunAllTasksTests {

        @Test
        void testAllTasksRun() {
            AtomicInteger counter = new AtomicInteger(0);
            List<Runnable> tasks = List.of(counter::incrementAndGet, counter::incrementAndGet, counter::incrementAndGet);
            VirtualThreadsBeginnerChallenge.runAllTasks(tasks);
            assertEquals(3, counter.get());
        }

        @Test
        void testReturnsOnlyAfterAllTasksFinish() {
            List<Integer> results = Collections.synchronizedList(new ArrayList<>());
            List<Runnable> tasks = List.of(
                    () -> results.add(1),
                    () -> results.add(2),
                    () -> results.add(3)
            );
            VirtualThreadsBeginnerChallenge.runAllTasks(tasks);
            assertEquals(3, results.size());
        }

        @Test
        void testEmptyTaskListDoesNothing() {
            assertDoesNotThrow(() -> VirtualThreadsBeginnerChallenge.runAllTasks(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: submitAndGetResult
    // ==========================================================
    @Nested
    class SubmitAndGetResultTests {

        @Test
        void testReturnsTaskResult() throws Exception {
            assertEquals("hello", VirtualThreadsBeginnerChallenge.submitAndGetResult(() -> "hello"));
        }

        @Test
        void testWorksWithComputedResult() throws Exception {
            assertEquals(42, VirtualThreadsBeginnerChallenge.submitAndGetResult(() -> 6 * 7));
        }

        @Test
        void testRunsOnAVirtualThread() throws Exception {
            boolean result = VirtualThreadsBeginnerChallenge.submitAndGetResult(() -> Thread.currentThread().isVirtual());
            assertTrue(result);
        }
    }

    // ==========================================================
    // CHALLENGE 9: submitAllAndCollectResults
    // ==========================================================
    @Nested
    class SubmitAllAndCollectResultsTests {

        @Test
        void testResultsInOrder() throws Exception {
            List<Callable<String>> tasks = List.of(() -> "a", () -> "b", () -> "c");
            assertEquals(List.of("a", "b", "c"), VirtualThreadsBeginnerChallenge.submitAllAndCollectResults(tasks));
        }

        @Test
        void testEmptyListReturnsEmptyList() throws Exception {
            assertEquals(List.of(), VirtualThreadsBeginnerChallenge.submitAllAndCollectResults(List.of()));
        }

        @Test
        void testSingleTask() throws Exception {
            List<Callable<String>> tasks = List.of(() -> "solo");
            assertEquals(List.of("solo"), VirtualThreadsBeginnerChallenge.submitAllAndCollectResults(tasks));
        }
    }

    // ==========================================================
    // CHALLENGE 10: getThreadId
    // ==========================================================
    @Nested
    class GetThreadIdTests {

        @Test
        void testReturnsPositiveId() {
            Thread thread = Thread.ofVirtual().unstarted(() -> {});
            assertTrue(VirtualThreadsBeginnerChallenge.getThreadId(thread) > 0);
        }

        @Test
        void testDifferentThreadsHaveDifferentIds() {
            Thread t1 = Thread.ofVirtual().unstarted(() -> {});
            Thread t2 = Thread.ofVirtual().unstarted(() -> {});
            assertNotEquals(VirtualThreadsBeginnerChallenge.getThreadId(t1), VirtualThreadsBeginnerChallenge.getThreadId(t2));
        }

        @Test
        void testMatchesThreadIdMethod() {
            Thread thread = Thread.ofVirtual().unstarted(() -> {});
            assertEquals(thread.threadId(), VirtualThreadsBeginnerChallenge.getThreadId(thread));
        }
    }
}