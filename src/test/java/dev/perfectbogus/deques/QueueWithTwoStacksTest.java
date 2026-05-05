package dev.perfectbogus.deques;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueWithTwoStacksTest {

    @Test
    void enqueue() {
        QueueWithTwoStacks<Integer> queue = queueFactory();
        assertEquals(3, queue.size());
    }

    @Test
    void dequeue() {
        QueueWithTwoStacks<Integer> queue = queueFactory();
        assertEquals(1, queue.dequeue());
    }

    @Test
    void testDequeueAll() {
        QueueWithTwoStacks<Integer> queue = queueFactory();
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void peek() {
        QueueWithTwoStacks<Integer> queue = queueFactory();
        assertEquals(1, queue.peek());
    }

    @Test
    void isEmpty() {
        QueueWithTwoStacks<Integer> queue = queueFactory();
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void size() {
        QueueWithTwoStacks<Integer> queue = queueFactory();
        assertEquals(3, queue.size());
    }

    @Test
    void testDequeueEmptyQueue() {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void testNullEnqueue() {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }

    @Test
    void testInterleavedEnqueueDequeue() {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        queue.enqueue(3);         // enqueue after a dequeue
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    QueueWithTwoStacks<Integer> queueFactory() {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        return queue;
    }
}