package dev.perfectbogus.deques;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class QueueWithTwoStacks<T> {

    private Deque<T> inboxStack = new ArrayDeque<>();
    private Deque<T> outboxStack = new ArrayDeque<>();

    public void enqueue(T item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        inboxStack.push(item);
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        if (outboxStack.isEmpty()) {
            while (!inboxStack.isEmpty()) {
                outboxStack.push(inboxStack.pop());
            }
        }
        return outboxStack.pop();
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        while (!inboxStack.isEmpty()) {
            outboxStack.push(inboxStack.pop());
        }
        T item = outboxStack.peekFirst();
        while (!outboxStack.isEmpty()) {
            inboxStack.push(outboxStack.pop());
        }
        return item;
    }

    public boolean isEmpty() {
        return inboxStack.isEmpty() && outboxStack.isEmpty();
    }

    public int size() {
        return inboxStack.size() + outboxStack.size();
    }
}
