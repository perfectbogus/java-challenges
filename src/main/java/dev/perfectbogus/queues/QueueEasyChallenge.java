package dev.perfectbogus.queues;

import java.util.*;

public class QueueEasyChallenge {

    // CHALLENGE 1
    // Removes the first person of line and returns that name. If the line is empty, returns "nobody" and the line
    // stays empty.
    // Example: line = [Ana, Bob, Cy]  serveNext(line) returns "Ana" and line becomes [Bob, Cy]
    //          line = []              serveNext(line) returns "nobody"
    public static String serveNext(Queue<String> line) {
        if (line.isEmpty()) return "nobody";
        return line.poll();
    }

    // CHALLENGE 2
    // Takes the first person of line and sends that person to the back of the same queue. An empty line stays empty
    // and a line with only one person stays the same.
    // Example: line = [Ana, Bob, Cy]  after moveFrontToBack(line), line is [Bob, Cy, Ana]
    //          line = [Ana]           after moveFrontToBack(line), line is [Ana]
    //          line = []              after moveFrontToBack(line), line is []
    public static void moveFrontToBack(Queue<String> line) {
        if (!line.isEmpty()) line.offer(line.poll());
    }

    // CHALLENGE 3
    // Returns the greatest number in queue without changing the queue: it keeps the same numbers in the same order.
    // An empty queue gives null.
    // Example: queue = [4, 9, 2]  largestWaiting(queue) returns 9 and queue is still [4, 9, 2]
    //          queue = [-5, -2]   largestWaiting(queue) returns -2
    //          queue = []         largestWaiting(queue) returns null
    public static Integer largestWaiting(Queue<Integer> queue) {
        return queue.stream().max(Comparator.naturalOrder()).orElse(null);
    }

    // CHALLENGE 4
    // Removes from queue, in place, every number equal to value. The other numbers keep their order. Returns how
    // many numbers were removed.
    // Example: queue = [3, 7, 3, 5, 3], removeValue(queue, 3) returns 3 and queue becomes [7, 5]
    //          queue = [1, 2],          removeValue(queue, 9) returns 0 and queue stays [1, 2]
    //          queue = [],              removeValue(queue, 4) returns 0
    public static int removeValue(Queue<Integer> queue, int value) {
        int count = 0;
        int size = queue.size();
        int i = 0;
        while (i < size) {
            Integer v = queue.poll();
            Objects.requireNonNull(v);
            if (v == value) {
                count++;
            } else {
                queue.offer(v);
            }
            i++;
        }
        return count;
    }

    public static int removeValueImproved(Queue<Integer> queue, int value) {
        int removed = 0;
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int n = queue.poll();
            if (n == value) {
                removed++;
            } else {
                queue.offer(n);
            }
        }
        return removed;
    }

    public static int removeValueImprovedV2(Queue<Integer> queue, int value) {
        int before = queue.size();
        queue.removeIf(n -> n == value);
        return before - queue.size();
    }

    // CHALLENGE 5
    // Returns a new queue with the numbers of queue in reverse order: the last number of queue is the first one to
    // come out of the result. queue is not modified.
    // Example: queue = [1, 2, 3]  reversed(queue) returns [3, 2, 1] and queue is still [1, 2, 3]
    //          queue = [7]        reversed(queue) returns [7]
    //          queue = []         reversed(queue) returns []
    public static Queue<Integer> reversed(Queue<Integer> queue) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (Integer i : queue) {
            stack.push(i);
        }

        return new LinkedList<>(stack);
    }

    // CHALLENGE 6
    // Moves the first k numbers of queue to the back, in place, keeping their order. k is never negative, and it can
    // be greater than the number of elements in the queue. An empty queue stays empty.
    // Example: queue = [1, 2, 3, 4, 5], rotate(queue, 2) leaves queue as [3, 4, 5, 1, 2]
    //          queue = [1, 2, 3, 4, 5], rotate(queue, 7) leaves queue as [3, 4, 5, 1, 2]
    //          queue = [1, 2, 3],       rotate(queue, 3) leaves queue as [1, 2, 3]
    //          queue = [],              rotate(queue, 3) leaves queue as []
    public static void rotate(Queue<Integer> queue, int k) {
        int i = 0;
        while (!queue.isEmpty() && i < k) {
            queue.offer(queue.poll());
            i++;
        }
    }

    public static void rotateImproved(Queue<Integer> queue, int k) {
        if (queue.isEmpty()) return;
        int times = k % queue.size();
        for (int i = 0; i < times; i++) queue.offer(queue.poll());
    }

    // CHALLENGE 7
    // The numbers arrive one by one, in the order of the array. Returns a queue with only the last n numbers that
    // arrived, the oldest one first. If fewer than n numbers arrived, the queue has all of them. If n is 0, the queue
    // is empty.
    // Example: lastN([1, 2, 3, 4, 5, 6], 3) returns [4, 5, 6]
    //          lastN([8, 9], 5)             returns [8, 9]
    //          lastN([8, 9], 0)             returns []
    public static Queue<Integer> lastN(int[] numbers, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        int min = Math.min(n, numbers.length);
        for (int i = numbers.length - min; i < numbers.length; i++) {
            q.offer(numbers[i]);
        }
        return q;
    }

    public static Queue<Integer> lastNImproved(int[] numbers, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int number : numbers) {
            q.offer(number);
            if (q.size() > n) q.poll();
        }
        return q;
    }

    // CHALLENGE 8
    // Returns a new queue that takes one number from first, then one from second, and so on, starting with first.
    // When one of the queues runs out, the numbers that are left in the other one follow in their order. first and
    // second are not modified.
    // Example: first = [1, 2, 3], second = [10, 20]  interleave(first, second) returns [1, 10, 2, 20, 3]
    //          first = [],        second = [5, 6]    interleave(first, second) returns [5, 6]
    public static Queue<Integer> interleave(Queue<Integer> first, Queue<Integer> second) {
        Queue<Integer> firstCopy = new LinkedList<>(first);
        Queue<Integer> secondCopy = new LinkedList<>(second);
        Queue<Integer> q = new LinkedList<>();

        while (!firstCopy.isEmpty() && !secondCopy.isEmpty()) {
            q.offer(firstCopy.poll());
            q.offer(secondCopy.poll());
        }

        while (!firstCopy.isEmpty() && secondCopy.isEmpty()) {
            q.offer(firstCopy.poll());
        }

        while (firstCopy.isEmpty() && !secondCopy.isEmpty()) {
            q.offer(secondCopy.poll());
        }

        return q;
    }

    public static Queue<Integer> interleaveImproved(Queue<Integer> first, Queue<Integer> second) {
        Iterator<Integer> a = first.iterator();
        Iterator<Integer> b = second.iterator();
        Queue<Integer> res = new LinkedList<>();
        while (a.hasNext() || b.hasNext()) {
            if (a.hasNext()) res.offer(a.next());
            if (b.hasNext()) res.offer(b.next());
        }
        return res;
    }

    // CHALLENGE 9
    // The customers are served one at a time, in the order of the array. serviceTimes[i] is how many minutes the
    // customer i takes to be served. Each customer waits until all the customers before have been served, so the
    // first one waits 0 minutes. Returns the sum of the waiting times of all the customers.
    // Example: totalWaitingTime([3, 2, 4]) returns 8   (waits: 0, 3 and 3 + 2 = 5)
    //          totalWaitingTime([5])       returns 0
    //          totalWaitingTime([])        returns 0
    public static int totalWaitingTime(int[] serviceTimes) {
        int total = 0;
        int elapsed = 0;
        for (int time : serviceTimes) {
            total += elapsed;
            elapsed += time;
        }
        return total;
    }

    // CHALLENGE 10
    // Returns the first n positive whole numbers (1, 2, 3, ...) written in binary, as Strings, in order. If n is 0,
    // the result is empty.
    // Example: binaryNumbers(5) returns [1, 10, 11, 100, 101]
    //          binaryNumbers(1) returns [1]
    //          binaryNumbers(0) returns []
    public static List<String> binaryNumbers(int n) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        if (n > 0) queue.offer("1");
        for (int i = 0; i < n; i++) {
            String s = queue.poll();
            res.add(s);
            queue.offer(s + "0");
            queue.offer(s + "1");
        }
        return res;
    }

    // CHALLENGE 11
    // Hot potato. The players are in a circle, in the order of the list. The potato starts with the first player and
    // is passed to the next player, steps times. The player that holds the potato after the last pass is out, and the
    // next round starts with the player that comes after the one who left. This repeats until only one player is
    // left, and that player is returned. steps is at least 1. With only one player, that player wins. players is not
    // modified.
    // Example: lastPlayer([Ana, Bob, Cy], 1)           returns "Cy"
    //          (Ana passes to Bob, who is out. Cy starts and passes to Ana, who is out.)
    //          lastPlayer([Ana, Bob, Cy, Di, Eve], 2)  returns "Di"
    //          lastPlayer([Ana, Bob], 2)               returns "Bob"
    public static String lastPlayer(List<String> players, int steps) {
        Deque<String> q = new ArrayDeque<>(players);
        int i = 0;
        while (q.size() >= 2) {
            if (i == steps) {
                q.poll();
                i = 0;
            } else {
                i++;
                q.offer(q.poll());
            }
        }
        return q.poll();
    }

    public static String lastPlayerImproved(List<String> players, int steps) {
        Queue<String> circle = new LinkedList<>(players);
        while (circle.size() > 1) {
            for (int i = 0; i < steps; i++) {
                circle.offer(circle.poll());
            }
            circle.poll();
        }
        return circle.poll();
    }

    // CHALLENGE 12
    // The customers wait in line in the order of names, and units[i] is how many units of work customer i needs (at
    // least 1). The cashier works in turns: the customer at the front of the line gets up to quantum units of work
    // (quantum is at least 1). If that customer still needs more work, they go to the back of the line, and if not,
    // they leave. Returns the names in the order the customers leave.
    // Example: finishingOrder([Ana, Bob, Cy], [3, 1, 2], 1) returns [Bob, Cy, Ana]
    //          (Ana goes back, Bob leaves, Cy goes back, Ana goes back again, Cy leaves and Ana leaves)
    //          finishingOrder([Ana, Bob], [6, 5], 3)         returns [Ana, Bob]
    //          (Ana goes back with 3 left, Bob goes back with 2 left, then Ana leaves and Bob leaves)
    //          finishingOrder([], [], 2)                     returns []
    public static List<String> finishingOrder(String[] names, int[] units, int quantum) {
        int min = Math.min(names.length, units.length);
        Queue<Pair> data = new LinkedList<>();
        for (int i = 0; i < min; i++) {
            data.offer(new Pair(names[i], units[i]));
        }

        List<String> leaves = new ArrayList<>();
        while (!data.isEmpty()) {
            Pair current = data.poll();
            int result = current.decrease(quantum);
            if (result > 0) {
                data.offer(current);
            } else {
                leaves.add(current.getName());
            }
        }
        return leaves;
    }

    private static class Pair {
        private final String name;
        private int unit;

        public Pair(String name, int unit) {
            this.name = name;
            this.unit = unit;
        }

        public int decrease(int units) {
            unit = unit - units;
            return unit;
        }

        public String getName() {
            return name;
        }
    }

    public static List<String> finishingOrderImproved(String[] names, int[] units, int quantum) {
        Queue<Integer> line = new LinkedList<>();
        int[] left = units.clone();
        for (int i = 0; i < names.length; i++) {
            line.offer(i);
        }
        List<String> order = new ArrayList<>();
        while (!line.isEmpty()) {
            int i = line.poll();
            left[i] -= quantum;
            if (left[i] > 0) {
                line.offer(i);
            } else {
                order.add(names[i]);
            }
        }
        return order;
    }
}
