package dev.perfectbogus.deques;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeUsage {

    public static void main(String[] args) {
        System.out.println("Usage as Stack:");
        StackUsage();
        System.out.println("Usage as Queue");
        QueueUsage();
    }

    public static void QueueUsage() {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println("Offer queue: ");
        printDeque(queue);

        queue.poll();
        System.out.println("Poll queue: ");
        printDeque(queue);
        System.out.println("FIFO: First Input First Output");

    }

    public static void StackUsage() {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Pushed Elements:");
        printDeque(stack);

        stack.pop();
        stack.pop();

        System.out.println("After Popped:");
        printDeque(stack);
        System.out.println("LIFO: Last Input First Output");
    }

    public static void printDeque(Deque<Integer> stack) {
        for (int val: stack) {
            System.out.println(val);
        }
    }
}
