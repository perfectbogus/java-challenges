package dev.perfectbogus.virtual.threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

class VirtualThreadsIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: sumConcurrently
    // ==========================================================
    @Nested
    class SumConcurrentlyTests {

        @Test
        void testSumsAllNumbers() {
            assertEquals(15, VirtualThreadsIntermediateChallenge.sumConcurrently(List.of(1, 2, 3, 4, 5)));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, VirtualThreadsIntermediateChallenge.sumConcurrently(List.of()));
        }

        @Test
        void testLargeNumberOfTasks() {
            List<Integer> numbers = Collections.nCopies(1000, 1);
            assertEquals(1000, VirtualThreadsIntermediateChallenge.sumConcurrently(numbers));
        }
    }

    // ==========================================================
    // CHALLENGE 2: collectDistinctThreadIds
    // ==========================================================
    @Nested
    class CollectDistinctThreadIdsTests {

        @Test
        void testCorrectCountOfIds() {
            assertEquals(50, VirtualThreadsIntermediateChallenge.collectDistinctThreadIds(50).size());
        }

        @Test
        void testAllIdsArePositive() {
            Set<Long> ids = VirtualThreadsIntermediateChallenge.collectDistinctThreadIds(20);
            assertTrue(ids.stream().allMatch(id -> id > 0));
        }

        @Test
        void testZeroTasksReturnsEmptySet() {
            assertTrue(VirtualThreadsIntermediateChallenge.collectDistinctThreadIds(0).isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 3: getTaskExceptionMessage
    // ==========================================================
    @Nested
    class GetTaskExceptionMessageTests {

        @Test
        void testReturnsUnderlyingMessage() {
            Callable<Object> failingTask = () -> {
                throw new RuntimeException("boom");
            };
            assertEquals("boom", VirtualThreadsIntermediateChallenge.getTaskExceptionMessage(failingTask));
        }

        @Test
        void testReturnsMessageForDifferentExceptionType() {
            Callable<Object> failingTask = () -> {
                throw new IllegalStateException("bad state");
            };
            assertEquals("bad state", VirtualThreadsIntermediateChallenge.getTaskExceptionMessage(failingTask));
        }
    }

    // ==========================================================
    // CHALLENGE 4: runWithThreadLocalIsolated
    // ==========================================================
    @Nested
    class RunWithThreadLocalIsolatedTests {

        @Test
        void testEachThreadSeesItsOwnValue() {
            ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
            List<Integer> results = VirtualThreadsIntermediateChallenge.runWithThreadLocalIsolated(threadLocal, 10, 20);
            assertEquals(List.of(10, 20), results);
        }

        @Test
        void testWorksWithNegativeValues() {
            ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
            List<Integer> results = VirtualThreadsIntermediateChallenge.runWithThreadLocalIsolated(threadLocal, -5, -15);
            assertEquals(List.of(-5, -15), results);
        }
    }

    // ==========================================================
    // CHALLENGE 5: isVirtualThreadDaemonByDefault
    // ==========================================================
    @Nested
    class IsVirtualThreadDaemonByDefaultTests {

        @Test
        void testIsDaemon() {
            assertTrue(VirtualThreadsIntermediateChallenge.isVirtualThreadDaemonByDefault());
        }
    }

    // ==========================================================
    // CHALLENGE 6: sumViaInvokeAll
    // ==========================================================
    @Nested
    class SumViaInvokeAllTests {

        @Test
        void testSumsAllTaskResults() {
            List<Callable<Integer>> tasks = List.of(() -> 1, () -> 2, () -> 3);
            assertEquals(6, VirtualThreadsIntermediateChallenge.sumViaInvokeAll(tasks));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, VirtualThreadsIntermediateChallenge.sumViaInvokeAll(List.of()));
        }

        @Test
        void testSingleTask() {
            List<Callable<Integer>> tasks = List.of(() -> 99);
            assertEquals(99, VirtualThreadsIntermediateChallenge.sumViaInvokeAll(tasks));
        }
    }

    // ==========================================================
    // CHALLENGE 7: firstSuccessfulResult
    // ==========================================================
    @Nested
    class FirstSuccessfulResultTests {

        @Test
        void testReturnsAResultFromTheList() {
            List<Callable<String>> tasks = List.of(() -> "only-option");
            assertEquals("only-option", VirtualThreadsIntermediateChallenge.firstSuccessfulResult(tasks));
        }

        @Test
        void testIgnoresFailingTasks() {
            List<Callable<String>> tasks = List.of(
                    () -> { throw new RuntimeException("fails"); },
                    () -> "succeeds"
            );
            assertEquals("succeeds", VirtualThreadsIntermediateChallenge.firstSuccessfulResult(tasks));
        }
    }

    // ==========================================================
    // CHALLENGE 8: joinAllAndConfirmFinished
    // ==========================================================
    @Nested
    class JoinAllAndConfirmFinishedTests {

        @Test
        void testReturnsTrueWhenAllFinish() throws InterruptedException {
            List<Thread> threads = List.of(
                    Thread.ofVirtual().start(() -> {}),
                    Thread.ofVirtual().start(() -> {})
            );
            assertTrue(VirtualThreadsIntermediateChallenge.joinAllAndConfirmFinished(threads));
        }

        @Test
        void testEmptyListReturnsTrue() throws InterruptedException {
            assertTrue(VirtualThreadsIntermediateChallenge.joinAllAndConfirmFinished(List.of()));
        }

        @Test
        void testNoneAreAliveAfterwards() throws InterruptedException {
            Thread t1 = Thread.ofVirtual().start(() -> {});
            Thread t2 = Thread.ofVirtual().start(() -> {});
            VirtualThreadsIntermediateChallenge.joinAllAndConfirmFinished(List.of(t1, t2));
            assertFalse(t1.isAlive());
            assertFalse(t2.isAlive());
        }
    }

    // ==========================================================
    // CHALLENGE 9: confirmExecutorUsesVirtualThreads
    // ==========================================================
    @Nested
    class ConfirmExecutorUsesVirtualThreadsTests {

        @Test
        void testReturnsTrue() {
            assertTrue(VirtualThreadsIntermediateChallenge.confirmExecutorUsesVirtualThreads());
        }
    }

    // ==========================================================
    // CHALLENGE 10: collectNamedResults
    // ==========================================================
    @Nested
    class CollectNamedResultsTests {

        @Test
        void testResultsKeyedByName() {
            Map<String, Callable<Integer>> namedTasks = Map.of(
                    "one", () -> 1,
                    "two", () -> 2
            );
            Map<String, Integer> result = VirtualThreadsIntermediateChallenge.collectNamedResults(namedTasks);
            assertEquals(1, result.get("one"));
            assertEquals(2, result.get("two"));
        }

        @Test
        void testEmptyMapReturnsEmptyMap() {
            assertTrue(VirtualThreadsIntermediateChallenge.collectNamedResults(Map.of()).isEmpty());
        }

        @Test
        void testAllTasksRepresented() {
            Map<String, Callable<Integer>> namedTasks = Map.of(
                    "a", () -> 10,
                    "b", () -> 20,
                    "c", () -> 30
            );
            Map<String, Integer> result = VirtualThreadsIntermediateChallenge.collectNamedResults(namedTasks);
            assertEquals(3, result.size());
        }
    }
}