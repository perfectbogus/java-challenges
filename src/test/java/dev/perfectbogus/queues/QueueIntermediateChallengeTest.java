package dev.perfectbogus.queues;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class QueueIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: createQueueFromList
    // ==========================================================
    @Nested
    class CreateQueueFromListTests {

        @Test
        void testContainsAllItems() {
            Queue<Integer> queue = QueueIntermediateChallenge.createQueueFromList(List.of(1, 2, 3));
            assertEquals(3, queue.size());
        }

        @Test
        void testPollsInOriginalOrder() {
            Queue<Integer> queue = QueueIntermediateChallenge.createQueueFromList(List.of(10, 20, 30));
            assertEquals(10, queue.poll());
            assertEquals(20, queue.poll());
            assertEquals(30, queue.poll());
        }

        @Test
        void testEmptyList() {
            Queue<Integer> queue = QueueIntermediateChallenge.createQueueFromList(List.of());
            assertTrue(queue.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 2: peekWithoutRemoving
    // ==========================================================
    @Nested
    class PeekWithoutRemovingTests {

        @Test
        void testReturnsHeadElement() {
            Queue<Integer> queue = new LinkedList<>(List.of(1, 2, 3));
            assertEquals(1, QueueIntermediateChallenge.peekWithoutRemoving(queue));
        }

        @Test
        void testDoesNotRemoveElement() {
            Queue<Integer> queue = new LinkedList<>(List.of(1, 2, 3));
            QueueIntermediateChallenge.peekWithoutRemoving(queue);
            assertEquals(3, queue.size());
        }

        @Test
        void testEmptyQueueReturnsNull() {
            Queue<Integer> queue = new LinkedList<>();
            assertNull(QueueIntermediateChallenge.peekWithoutRemoving(queue));
        }
    }

    // ==========================================================
    // CHALLENGE 3: pollOrDefault
    // ==========================================================
    @Nested
    class PollOrDefaultTests {

        @Test
        void testReturnsHeadWhenPresent() {
            Queue<Integer> queue = new LinkedList<>(List.of(5, 6));
            assertEquals(5, QueueIntermediateChallenge.pollOrDefault(queue, -1));
        }

        @Test
        void testRemovesHeadWhenPresent() {
            Queue<Integer> queue = new LinkedList<>(List.of(5, 6));
            QueueIntermediateChallenge.pollOrDefault(queue, -1);
            assertEquals(1, queue.size());
        }

        @Test
        void testReturnsDefaultWhenEmpty() {
            Queue<Integer> queue = new LinkedList<>();
            assertEquals(-1, QueueIntermediateChallenge.pollOrDefault(queue, -1));
        }
    }

    // ==========================================================
    // CHALLENGE 4: addSafely
    // ==========================================================
    @Nested
    class AddSafelyTests {

        @Test
        void testSucceedsWhenSpaceAvailable() {
            Queue<Integer> queue = new ArrayBlockingQueue<>(3);
            assertTrue(QueueIntermediateChallenge.addSafely(queue, 1));
        }

        @Test
        void testValueIsInserted() {
            Queue<Integer> queue = new ArrayBlockingQueue<>(3);
            QueueIntermediateChallenge.addSafely(queue, 42);
            assertTrue(queue.contains(42));
        }

        @Test
        void testFalseWhenQueueIsFull() {
            Queue<Integer> queue = new ArrayBlockingQueue<>(1);
            queue.add(1);
            assertFalse(QueueIntermediateChallenge.addSafely(queue, 2));
        }
    }

    // ==========================================================
    // CHALLENGE 5: offerReturnsFalseWhenFull
    // ==========================================================
    @Nested
    class OfferReturnsFalseWhenFullTests {

        @Test
        void testTrueWhenSpaceAvailable() {
            Queue<Integer> queue = new ArrayBlockingQueue<>(3);
            assertTrue(QueueIntermediateChallenge.offerReturnsFalseWhenFull(queue, 1));
        }

        @Test
        void testFalseWhenQueueIsFull() {
            Queue<Integer> queue = new ArrayBlockingQueue<>(1);
            queue.offer(1);
            assertFalse(QueueIntermediateChallenge.offerReturnsFalseWhenFull(queue, 2));
        }

        @Test
        void testValueIsInsertedWhenSuccessful() {
            Queue<Integer> queue = new ArrayBlockingQueue<>(2);
            QueueIntermediateChallenge.offerReturnsFalseWhenFull(queue, 99);
            assertTrue(queue.contains(99));
        }
    }

    // ==========================================================
    // CHALLENGE 6: drainQueue
    // ==========================================================
    @Nested
    class DrainQueueTests {

        @Test
        void testReturnsElementsInOrder() {
            Queue<Integer> queue = new LinkedList<>(List.of(1, 2, 3));
            assertEquals(List.of(1, 2, 3), QueueIntermediateChallenge.drainQueue(queue));
        }

        @Test
        void testQueueIsEmptyAfterward() {
            Queue<Integer> queue = new LinkedList<>(List.of(1, 2, 3));
            QueueIntermediateChallenge.drainQueue(queue);
            assertTrue(queue.isEmpty());
        }

        @Test
        void testEmptyQueueReturnsEmptyList() {
            Queue<Integer> queue = new LinkedList<>();
            assertEquals(List.of(), QueueIntermediateChallenge.drainQueue(queue));
        }
    }

    // ==========================================================
    // CHALLENGE 7: createStackUsingDeque
    // ==========================================================
    @Nested
    class CreateStackUsingDequeTests {

        @Test
        void testPopsInReverseOrder() {
            Deque<Integer> stack = QueueIntermediateChallenge.createStackUsingDeque(List.of(1, 2, 3));
            assertEquals(3, stack.pop());
            assertEquals(2, stack.pop());
            assertEquals(1, stack.pop());
        }

        @Test
        void testContainsAllItems() {
            Deque<Integer> stack = QueueIntermediateChallenge.createStackUsingDeque(List.of(1, 2, 3));
            assertEquals(3, stack.size());
        }

        @Test
        void testEmptyList() {
            Deque<Integer> stack = QueueIntermediateChallenge.createStackUsingDeque(List.of());
            assertTrue(stack.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 8: peekStackTop
    // ==========================================================
    @Nested
    class PeekStackTopTests {

        @Test
        void testReturnsTopElement() {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(1);
            stack.push(2);
            assertEquals(2, QueueIntermediateChallenge.peekStackTop(stack));
        }

        @Test
        void testDoesNotRemoveElement() {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(1);
            stack.push(2);
            QueueIntermediateChallenge.peekStackTop(stack);
            assertEquals(2, stack.size());
        }

        @Test
        void testEmptyStackReturnsNull() {
            Deque<Integer> stack = new ArrayDeque<>();
            assertNull(QueueIntermediateChallenge.peekStackTop(stack));
        }
    }

    // ==========================================================
    // CHALLENGE 9: createFifoQueueUsingDeque
    // ==========================================================
    @Nested
    class CreateFifoQueueUsingDequeTests {

        @Test
        void testPollsInOriginalOrder() {
            Deque<Integer> queue = QueueIntermediateChallenge.createFifoQueueUsingDeque(List.of(1, 2, 3));
            assertEquals(1, queue.poll());
            assertEquals(2, queue.poll());
            assertEquals(3, queue.poll());
        }

        @Test
        void testContainsAllItems() {
            Deque<Integer> queue = QueueIntermediateChallenge.createFifoQueueUsingDeque(List.of(1, 2, 3));
            assertEquals(3, queue.size());
        }

        @Test
        void testEmptyList() {
            Deque<Integer> queue = QueueIntermediateChallenge.createFifoQueueUsingDeque(List.of());
            assertTrue(queue.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 10: slidingWindowMax
    // ==========================================================
    @Nested
    class SlidingWindowMaxTests {

        @Test
        void testBasicWindow() {
            int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
            assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, QueueIntermediateChallenge.slidingWindowMax(nums, 3));
        }

        @Test
        void testWindowSizeOne() {
            int[] nums = {4, 2, 9};
            assertArrayEquals(new int[]{4, 2, 9}, QueueIntermediateChallenge.slidingWindowMax(nums, 1));
        }

        @Test
        void testWindowSizeEqualsArrayLength() {
            int[] nums = {5, 1, 8, 2};
            assertArrayEquals(new int[]{8}, QueueIntermediateChallenge.slidingWindowMax(nums, 4));
        }
    }

    // ==========================================================
    // CHALLENGE 11: createMinHeap
    // ==========================================================
    @Nested
    class CreateMinHeapTests {

        @Test
        void testPollsSmallestFirst() {
            PriorityQueue<Integer> heap = QueueIntermediateChallenge.createMinHeap(List.of(5, 1, 3));
            assertEquals(1, heap.poll());
            assertEquals(3, heap.poll());
            assertEquals(5, heap.poll());
        }

        @Test
        void testContainsAllItems() {
            PriorityQueue<Integer> heap = QueueIntermediateChallenge.createMinHeap(List.of(5, 1, 3));
            assertEquals(3, heap.size());
        }

        @Test
        void testEmptyList() {
            PriorityQueue<Integer> heap = QueueIntermediateChallenge.createMinHeap(List.of());
            assertTrue(heap.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 12: createMaxHeap
    // ==========================================================
    @Nested
    class CreateMaxHeapTests {

        @Test
        void testPollsLargestFirst() {
            PriorityQueue<Integer> heap = QueueIntermediateChallenge.createMaxHeap(List.of(5, 1, 3));
            assertEquals(5, heap.poll());
            assertEquals(3, heap.poll());
            assertEquals(1, heap.poll());
        }

        @Test
        void testContainsAllItems() {
            PriorityQueue<Integer> heap = QueueIntermediateChallenge.createMaxHeap(List.of(5, 1, 3));
            assertEquals(3, heap.size());
        }

        @Test
        void testEmptyList() {
            PriorityQueue<Integer> heap = QueueIntermediateChallenge.createMaxHeap(List.of());
            assertTrue(heap.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 13: pollTopN
    // ==========================================================
    @Nested
    class PollTopNTests {

        @Test
        void testPollsRequestedCount() {
            PriorityQueue<String> queue = new PriorityQueue<>(List.of("banana", "apple", "cherry"));
            assertEquals(List.of("apple", "banana"), QueueIntermediateChallenge.pollTopN(queue, 2));
        }

        @Test
        void testFewerThanNAvailable() {
            PriorityQueue<String> queue = new PriorityQueue<>(List.of("apple"));
            assertEquals(List.of("apple"), QueueIntermediateChallenge.pollTopN(queue, 5));
        }

        @Test
        void testRemainingQueueShrinks() {
            PriorityQueue<String> queue = new PriorityQueue<>(List.of("banana", "apple", "cherry"));
            QueueIntermediateChallenge.pollTopN(queue, 2);
            assertEquals(1, queue.size());
        }

        @Test
        void testZeroRequested() {
            PriorityQueue<String> queue = new PriorityQueue<>(List.of("apple", "banana"));
            assertEquals(List.of(), QueueIntermediateChallenge.pollTopN(queue, 0));
        }
    }

    // ==========================================================
    // CHALLENGE 14: createPriorityQueueByPriority
    // ==========================================================
    @Nested
    class CreatePriorityQueueByPriorityTests {

        @Test
        void testPollsLowestPriorityNumberFirst() {
            List<QueueIntermediateChallenge.Task> tasks = List.of(
                    new QueueIntermediateChallenge.Task("low", 5),
                    new QueueIntermediateChallenge.Task("high", 1),
                    new QueueIntermediateChallenge.Task("mid", 3)
            );
            PriorityQueue<QueueIntermediateChallenge.Task> queue = QueueIntermediateChallenge.createPriorityQueueByPriority(tasks);
            assertEquals("high", queue.poll().name());
            assertEquals("mid", queue.poll().name());
            assertEquals("low", queue.poll().name());
        }

        @Test
        void testContainsAllTasks() {
            List<QueueIntermediateChallenge.Task> tasks = List.of(
                    new QueueIntermediateChallenge.Task("a", 2),
                    new QueueIntermediateChallenge.Task("b", 1)
            );
            PriorityQueue<QueueIntermediateChallenge.Task> queue = QueueIntermediateChallenge.createPriorityQueueByPriority(tasks);
            assertEquals(2, queue.size());
        }

        @Test
        void testEmptyList() {
            PriorityQueue<QueueIntermediateChallenge.Task> queue = QueueIntermediateChallenge.createPriorityQueueByPriority(List.of());
            assertTrue(queue.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 15: putAndTake
    // ==========================================================
    @Nested
    class PutAndTakeTests {

        @Test
        void testReturnsSomeValue() throws InterruptedException {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
            queue.put(77);
            int result = QueueIntermediateChallenge.putAndTake(queue);
            assertTrue(result == 77 || queue.isEmpty());
        }

        @Test
        void testQueueEndsEmptyWhenStartedEmpty() throws InterruptedException {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
            QueueIntermediateChallenge.putAndTake(queue);
            assertTrue(queue.isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 16: offerWithTimeout
    // ==========================================================
    @Nested
    class OfferWithTimeoutTests {

        @Test
        void testSucceedsWhenSpaceAvailable() throws InterruptedException {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
            assertTrue(QueueIntermediateChallenge.offerWithTimeout(queue, 1, 100));
        }

        @Test
        void testFailsWhenQueueStaysFull() throws InterruptedException {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
            queue.put(1);
            assertFalse(QueueIntermediateChallenge.offerWithTimeout(queue, 2, 50));
        }
    }

    // ==========================================================
    // CHALLENGE 17: pollWithTimeout
    // ==========================================================
    @Nested
    class PollWithTimeoutTests {

        @Test
        void testReturnsElementWhenAvailable() throws InterruptedException {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
            queue.put(5);
            assertEquals(5, QueueIntermediateChallenge.pollWithTimeout(queue, 100));
        }

        @Test
        void testReturnsNullWhenTimeoutElapses() throws InterruptedException {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
            assertNull(QueueIntermediateChallenge.pollWithTimeout(queue, 50));
        }
    }

    // ==========================================================
    // CHALLENGE 18: drainToList
    // ==========================================================
    @Nested
    class DrainToListTests {

        @Test
        void testTransfersAllElements() {
            BlockingQueue<Integer> source = new LinkedBlockingQueue<>(List.of(1, 2, 3));
            List<Integer> destination = new ArrayList<>();
            int count = QueueIntermediateChallenge.drainToList(source, destination);
            assertEquals(3, count);
            assertEquals(List.of(1, 2, 3), destination);
        }

        @Test
        void testSourceIsEmptyAfterward() {
            BlockingQueue<Integer> source = new LinkedBlockingQueue<>(List.of(1, 2, 3));
            List<Integer> destination = new ArrayList<>();
            QueueIntermediateChallenge.drainToList(source, destination);
            assertTrue(source.isEmpty());
        }

        @Test
        void testEmptySourceReturnsZero() {
            BlockingQueue<Integer> source = new LinkedBlockingQueue<>();
            List<Integer> destination = new ArrayList<>();
            assertEquals(0, QueueIntermediateChallenge.drainToList(source, destination));
        }
    }

    // ==========================================================
    // CHALLENGE 19: concatenateQueues
    // ==========================================================
    @Nested
    class ConcatenateQueuesTests {

        @Test
        void testConcatenatesInOrder() {
            Queue<Integer> first = new LinkedList<>(List.of(1, 2));
            Queue<Integer> second = new LinkedList<>(List.of(3, 4));
            Queue<Integer> result = QueueIntermediateChallenge.concatenateQueues(first, second);
            assertEquals(List.of(1, 2, 3, 4), new ArrayList<>(result));
        }

        @Test
        void testOriginalQueuesUnmodified() {
            Queue<Integer> first = new LinkedList<>(List.of(1, 2));
            Queue<Integer> second = new LinkedList<>(List.of(3, 4));
            QueueIntermediateChallenge.concatenateQueues(first, second);
            assertEquals(2, first.size());
            assertEquals(2, second.size());
        }

        @Test
        void testEmptyFirstQueue() {
            Queue<Integer> first = new LinkedList<>();
            Queue<Integer> second = new LinkedList<>(List.of(1, 2));
            Queue<Integer> result = QueueIntermediateChallenge.concatenateQueues(first, second);
            assertEquals(List.of(1, 2), new ArrayList<>(result));
        }
    }

    // ==========================================================
    // CHALLENGE 20: sumConcurrentlyUsingQueue
    // ==========================================================
    @Nested
    class SumConcurrentlyUsingQueueTests {

        @Test
        void testSumsAllNumbers() {
            assertEquals(15, QueueIntermediateChallenge.sumConcurrentlyUsingQueue(List.of(1, 2, 3, 4, 5)));
        }

        @Test
        void testEmptyListReturnsZero() {
            assertEquals(0, QueueIntermediateChallenge.sumConcurrentlyUsingQueue(List.of()));
        }

        @Test
        void testLargeNumberOfTasks() {
            List<Integer> numbers = Collections.nCopies(500, 2);
            assertEquals(1000, QueueIntermediateChallenge.sumConcurrentlyUsingQueue(numbers));
        }
    }
}