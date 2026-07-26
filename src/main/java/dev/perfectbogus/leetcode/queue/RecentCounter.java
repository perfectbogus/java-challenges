package dev.perfectbogus.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class RecentCounter {

    public static void main(String[] args) {
        Counter c = new Counter();

        System.out.println(c.ping(1));    // 1
        System.out.println(c.ping(100));  // 2
        System.out.println(c.ping(3001)); // 3
        System.out.println(c.ping(3002)); // 3

    }

    private static class Counter {
        private Queue<Integer> queue;

        public Counter() {
            this.queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            int range = t - 3000;

            queue.offer(t);

            while (!queue.isEmpty() && queue.peek() < range) {
                queue.poll();
            }

            return queue.size();
        }
    }
}

