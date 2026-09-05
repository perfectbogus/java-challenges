package dev.perfectbogus.threading.producer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class BoundedBuffer<T> {

    private final Deque<Optional<T>> buffer;
    private final int capacity;

    public BoundedBuffer(int capacity) {
        this.buffer = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public synchronized void put(Optional<T> item) {
        while (buffer.size() == capacity && item.isPresent()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        buffer.add(item);
        notifyAll();
    }

    public synchronized Optional<T> take() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return Optional.empty();
            }
        }
        Optional<T> item = buffer.poll();
        notifyAll();
        return item;
    }

}
