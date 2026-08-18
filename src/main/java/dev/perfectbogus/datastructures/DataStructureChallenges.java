package dev.perfectbogus.datastructures;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class DataStructureChallenges {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–5)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Valid Parentheses — use a Deque as a stack to check if a string
    // containing '(', ')', '{', '}', '[', ']' is valid.
    //
    // A string is valid if:
    // → every opening bracket has a matching closing bracket
    // → brackets are closed in the correct order
    //
    // Input:  "([]{})"  → true
    // Input:  "([)]"    → false
    // Input:  "{[]}"    → true
    // Input:  "((("     → false
    // Input:  ""        → true  (empty = valid)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge1(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — use Deque<Character> as a stack
        Deque<Character> q = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>(Map.of(
                 ')', '(',
                ']', '[',
                '}', '{'
        ));

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                q.push(c);
            } else {
                if (q.isEmpty()) return false;
                char p = q.peek();
                if (p != map.get(c)) return false;
                q.pop();
            }
        }

        return q.isEmpty();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Running Average — use an ArrayDeque as a sliding window to compute
    // the average of the last N numbers added.
    //
    // Implement the logic inside the method:
    // → maintain a queue of the last 'windowSize' numbers
    // → when a new number arrives add it; if queue exceeds windowSize, remove oldest
    // → after each addition compute the average
    //
    // Given a list of numbers and a windowSize, return a list of
    // averages after each number is added.
    //
    // Input:  numbers=[1,2,3,4,5], windowSize=3
    //   add 1 → window=[1]     avg=1.0
    //   add 2 → window=[1,2]   avg=1.5
    //   add 3 → window=[1,2,3] avg=2.0
    //   add 4 → window=[2,3,4] avg=3.0  (1 removed)
    //   add 5 → window=[3,4,5] avg=4.0  (2 removed)
    // Output: [1.0, 1.5, 2.0, 3.0, 4.0]
    // ─────────────────────────────────────────────────────────────
    public static List<Double> challenge2(List<Integer> numbers, int windowSize) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        if (windowSize <= 0) throw new IllegalArgumentException("Window size must be positive");
        // TODO — use ArrayDeque<Integer> as sliding window queue
        ArrayDeque<Integer> window = new ArrayDeque<>();
        List<Double> result = new ArrayList<>(numbers.size());

        double sum = 0;
        for (int i : numbers) {
            if (window.size() >= windowSize) {
                sum -= window.poll();
            }
            sum += i;
            window.offer(i);
            double avg = sum / window.size();
            result.add(avg);
        }

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // K Largest Elements — use a PriorityQueue (min heap of size K)
    // to find the K largest elements in an array.
    // Return them sorted in DESCENDING order.
    //
    // Input:  nums=[3,1,5,12,2,11,7], k=3
    // Output: [12, 11, 7]
    //
    // Input:  nums=[5,5,5,5], k=2
    // Output: [5, 5]
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge3(int[] nums, int k) {
        if (nums == null) throw new IllegalArgumentException("Array cannot be null");
        if (k <= 0 || k > nums.length) throw new IllegalArgumentException("Invalid k");
        // TODO — use PriorityQueue (min heap of size k)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int c : nums) {
            minHeap.offer(c);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Integer> results = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
            results.add(minHeap.poll());
        }

        results.sort(Comparator.reverseOrder());

        return results;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // First Non-Repeating Character — use a LinkedHashMap to find
    // the FIRST character in a string that appears exactly once.
    // Return the character, or '-' if no such character exists.
    //
    // LinkedHashMap preserves INSERTION ORDER — perfect for tracking
    // first occurrence!
    //
    // Input:  "leetcode"  → 'l'  (appears once, first)
    // Input:  "aabb"      → '-'  (all repeat)
    // Input:  "aabbc"     → 'c'  (c appears once)
    // Input:  "z"         → 'z'  (single char, always unique)
    // ─────────────────────────────────────────────────────────────
    public static char challenge4(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (s.isEmpty()) return '-';
        // TODO — use LinkedHashMap<Character, Integer> for frequency
        //        iterate in insertion order to find first with count=1
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }

//        Map<Character, Long> map1 = s.chars().mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(
//                        Function.identity(),
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ));
//
//        Map<Character, Integer> map2 = s.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.toMap(
//                        Function.identity(),
//                        c -> 1,           // ← each = 1
//                        Integer::sum,     // ← merge sums
//                        LinkedHashMap::new
//                ));


        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

//        return map1.entrySet().stream()
//                .filter(e -> e.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse('-');




        return '-';
    }

    public static char challenge4_2(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (s.isEmpty()) return '-';

        Map<Character, Long> map = s.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        return map.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse('-');
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // TreeMap Range Count — use a TreeMap to count how many keys
    // fall within a given range [lo, hi] (inclusive).
    // Also return the FLOOR (largest key <= query) and
    // CEILING (smallest key >= query) for a given query key.
    //
    // Return a record RangeResult(int count, Integer floor, Integer ceiling)
    // floor/ceiling are null if they don't exist.
    //
    // Input:  keys=[1,3,5,7,9,11,13], lo=4, hi=10, query=6
    //   count   = 3  (5, 7, 9 are in [4,10])
    //   floor   = 5  (largest key <= 6)
    //   ceiling = 7  (smallest key >= 6)
    //
    // Input:  keys=[1,3,5], query=4
    //   floor   = 3  (largest key <= 4)
    //   ceiling = 5  (smallest key >= 4)
    // ─────────────────────────────────────────────────────────────
    record RangeResult(int count, Integer floor, Integer ceiling) {}

    public static RangeResult challenge5(List<Integer> keys, int lo, int hi, int query) {
        if (keys == null) throw new IllegalArgumentException("Keys cannot be null");
        // TODO — use TreeMap<Integer, Integer>
        //        subMap(lo, true, hi, true).size() for count
        //        floorKey(query) and ceilingKey(query) for floor/ceiling
        TreeMap<Integer, Integer> map = keys.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        k -> 0,
                        (a, b) -> a,
                        TreeMap::new
                ));

        int count = map.subMap(lo, true, hi, true).size();
        Integer floor = map.floorKey(query);
        Integer ceiling = map.ceilingKey(query);

        return new RangeResult(count, floor, ceiling);
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 6–9)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Max Stack — implement a stack that supports push, pop, peek
    // and getMax in O(1) time using TWO ArrayDeques.
    //
    // The second deque tracks the RUNNING MAXIMUM at each state:
    // → on push: push to main stack AND push max(newVal, currentMax) to maxStack
    // → on pop:  pop from both stacks simultaneously
    // → getMax:  peek at top of maxStack
    //
    // Operations list contains strings like:
    // "push:5", "push:3", "push:7", "getMax", "pop", "getMax", "peek"
    //
    // Input:  ["push:5","push:3","push:7","getMax","pop","getMax","peek"]
    // Results after each op:
    //   push:5  → main=[5]     maxStack=[5]
    //   push:3  → main=[5,3]   maxStack=[5,5]
    //   push:7  → main=[5,3,7] maxStack=[5,5,7]
    //   getMax  → 7
    //   pop     → removes 7
    //   getMax  → 5  (max is now 5 again!)
    //   peek    → 3  (top of main stack)
    //
    // Output: [7, 5, 3]  ← results of getMax/peek operations only
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge6(List<String> operations) {
        if (operations == null) throw new IllegalArgumentException("Operations cannot be null");
        // TODO — use two ArrayDeque<Integer>: mainStack and maxStack
        List<Integer> results = new ArrayList<>();
        MaxStack stack = new MaxStack();
        for (String s : operations) {
            String[] split = s.split(":");
            if (split[0].equals("push")) {
                int val = Integer.parseInt(split[1]);
                stack.push(val);
            } else if (split[0].equals("getMax")) {
                int max = stack.getMax();
                results.add(max);
            } else if (split[0].equals("pop")) {
                stack.pop();
            } else {
                int val = stack.peek();
                results.add(val);
            }
        }
        return results;
    }

    private static class MaxStack {

        ArrayDeque<Integer> main;
        ArrayDeque<Integer> maxStack;

        public MaxStack() {
            main = new ArrayDeque<>();
            maxStack = new ArrayDeque<>();
        }

        public void pop() {
            main.pop();
            maxStack.pop();
        }

        public Integer peek() {
            return main.peek();
        }

        public void push(Integer i) {
            main.push(i);
            int val = maxStack.isEmpty() ? i : Math.max(maxStack.peek(), i);
            maxStack.push(val);
        }

        public Integer getMax() {
            return maxStack.peek();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Task Scheduler — use a PriorityQueue to process tasks by PRIORITY
    // (highest priority first). For same priority process by NAME alphabetically.
    //
    // record Task(String name, int priority, int duration)
    //
    // Process ALL tasks using the PriorityQueue and return them in the
    // order they were processed, as "name(duration)" strings.
    //
    // Input:
    //   ("Deploy",  3, 5)
    //   ("Test",    1, 2)
    //   ("Review",  3, 3)
    //   ("Meeting", 2, 1)
    //   ("Fix Bug", 3, 4)
    //
    // Priority DESC then name ASC:
    //   priority=3: Fix Bug(4), Deploy(5), Review(3) → alpha: Deploy,Fix Bug,Review
    //   priority=2: Meeting(1)
    //   priority=1: Test(2)
    //
    // Output: ["Deploy(5)", "Fix Bug(4)", "Review(3)", "Meeting(1)", "Test(2)"]
    // ─────────────────────────────────────────────────────────────
    record Task(String name, int priority, int duration) {}

    public static List<String> challenge7(List<Task> tasks) {
        if (tasks == null) throw new IllegalArgumentException("Tasks cannot be null");
        // TODO — use PriorityQueue<Task> with comparator: priority DESC then name ASC
        //        poll all tasks and format as "name(duration)"
        Comparator<Task> byHighestPriorityDesc = Comparator.comparingInt(Task::priority).reversed();
        Comparator<Task> byName = Comparator.comparing(Task::name);
        PriorityQueue<Task> q = new PriorityQueue<>(byHighestPriorityDesc.thenComparing(byName));

        for (Task t : tasks) {
            q.offer(t);
        }

        List<String> result = new ArrayList<>();
        while (!q.isEmpty()) {
            Task t = q.poll();
            String tmp = t.name() + "(" + t.duration() + ")";
            result.add(tmp);
        }

        return result;
    }

    public static List<String> challenge7_2(List<Task> tasks) {
        Comparator<Task> byHighestPriorityDesc = Comparator.comparingInt(Task::priority).reversed();
        Comparator<Task> byName = Comparator.comparing(Task::name);
        return tasks.stream().sorted(byHighestPriorityDesc.thenComparing(byName)).map(t -> t.name() + "(" + t.duration() + ")").toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Top K Frequent Words — given a list of words, use a HashMap
    // to count frequencies and a PriorityQueue to find the top K
    // most frequent words.
    // For same frequency → alphabetically ascending order.
    // Return them sorted by frequency DESC then alpha ASC.
    //
    // Input:  words=["apple","banana","apple","cherry","banana","apple","date","cherry"], k=3
    //   apple=3, banana=2, cherry=2, date=1
    //   Top 3: apple(3), banana(2), cherry(2) → banana before cherry (alpha)
    //
    // Output: ["apple", "banana", "cherry"]
    //
    // Input:  words=["the","day","is","sunny","the","the","sunny","is","is"], k=4
    //   the=3, is=3, sunny=2, day=1
    //   Top 4: is(3), the(3), sunny(2), day(1) → is before the (alpha)
    //
    // Output: ["is", "the", "sunny", "day"]
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge8(List<String> words, int k) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        if (k <= 0)        throw new IllegalArgumentException("k must be positive");
        // TODO — Step 1: HashMap<String,Integer> for frequencies
        //        Step 2: PriorityQueue with comparator freq DESC then alpha ASC
        //        Step 3: offer all entries, poll k times
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Evaluate Reverse Polish Notation (RPN) — use a Deque as a stack
    // to evaluate an arithmetic expression in Reverse Polish Notation.
    //
    // Tokens are: numbers (integers) or operators: "+", "-", "*", "/"
    // Rules:
    // → when you see a NUMBER → push to stack
    // → when you see an OPERATOR → pop two numbers, apply op, push result
    // → division truncates towards zero (like Java integer division)
    //
    // Input:  ["2","1","+","3","*"]     → ((2+1)*3) = 9
    // Input:  ["4","13","5","/","+"]    → (4+(13/5)) = 4+2 = 6
    // Input:  ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    //          → 22
    // Input:  ["3","4","+","2","*","7","/"] → ((3+4)*2)/7 = 2
    // ─────────────────────────────────────────────────────────────
    public static int challenge9(String[] tokens) {
        if (tokens == null) throw new IllegalArgumentException("Tokens cannot be null");
        // TODO — use ArrayDeque<Integer> as stack
        //        for each token: if number → push; if operator → pop two, compute, push
        //        Set<String> operators = Set.of("+","-","*","/")
        return 0;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenge 10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Sliding Window Maximum — given an integer array and a window size k,
    // return the maximum value in each sliding window of size k.
    //
    // Input:  nums=[1,3,-1,-3,5,3,6,7], k=3
    //
    // Windows:
    //   [1, 3,-1]        max=3
    //   [3,-1,-3]        max=3
    //   [-1,-3, 5]       max=5
    //   [-3, 5, 3]       max=5
    //   [5, 3, 6]        max=6
    //   [3, 6, 7]        max=7
    //
    // Output: [3,3,5,5,6,7]
    //
    // ⚠️ Brute force O(n*k) is too slow — use a MONOTONIC DEQUE for O(n)!
    //
    // Hint:
    // Use ArrayDeque<Integer> storing INDICES (not values)!
    // The deque maintains indices of POTENTIALLY USEFUL elements:
    // → front of deque = index of current window MAXIMUM
    // → deque is always in DECREASING order of values (monotonic!)
    //
    // Algorithm for each index i:
    // Step 1 — REMOVE from FRONT if out of window:
    //          while !deque.isEmpty() && deque.peekFirst() < i - k + 1
    //          → deque.pollFirst()
    //
    // Step 2 — REMOVE from BACK if smaller than current (they'll never be max):
    //          while !deque.isEmpty() && nums[deque.peekLast()] < nums[i]
    //          → deque.pollLast()
    //
    // Step 3 — ADD current index to back:
    //          deque.addLast(i)
    //
    // Step 4 — RECORD maximum (only when window is full: i >= k-1):
    //          result.add(nums[deque.peekFirst()])
    //
    // Why it works:
    // → Removing smaller elements from back means front is ALWAYS the max
    // → Removing out-of-window indices from front keeps window valid
    // → Each element is added/removed at most once → O(n) total!
    // ─────────────────────────────────────────────────────────────
    public static int[] challenge10(int[] nums, int k) {
        if (nums == null) throw new IllegalArgumentException("Array cannot be null");
        if (k <= 0 || k > nums.length) throw new IllegalArgumentException("Invalid k");
        // TODO — use ArrayDeque<Integer> storing INDICES (monotonic deque!)
        return new int[]{};
    }
}