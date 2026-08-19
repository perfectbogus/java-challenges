package dev.perfectbogus.datastructures;

import java.util.*;

public class QueueStackChallenges {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–6)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Reverse Words in a Sentence using a Deque.
    // Split the sentence into words, push each word onto a Deque,
    // then pop them off to build the reversed sentence.
    // Preserve single spaces between words.
    //
    // Input:  "Hello World Java is fun"
    // Output: "fun is Java World Hello"
    //
    // Input:  "one"
    // Output: "one"
    //
    // Input:  "a b c d"
    // Output: "d c b a"
    // ─────────────────────────────────────────────────────────────
    public static String challenge1(String sentence) {
        if (sentence == null) throw new IllegalArgumentException("Sentence cannot be null");
        if (sentence.isEmpty()) return "";
        // TODO — push each word onto Deque, pop all into result
        Deque<String> stack = new ArrayDeque<>();
        for (String s : sentence.split("\\s+")) {
            stack.push(s);
        }

        List<String> reversed = new ArrayList<>();
        while (!stack.isEmpty()) {
            reversed.add(stack.pop());
        }

        return String.join(" ", reversed);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Valid Stack Sequence — given a pushed sequence and a popped sequence,
    // return true if the popped sequence could result from pushing all
    // pushed elements onto a Stack in order.
    //
    // Rules:
    // → push elements from pushed array one at a time onto the stack
    // → after each push, pop from stack while top matches next in popped
    // → at the end the stack must be empty
    //
    // Input:  pushed=[1,2,3,4,5], popped=[4,5,3,2,1] → true
    // Input:  pushed=[1,2,3,4,5], popped=[4,3,5,1,2] → false
    // Input:  pushed=[1,2,3],     popped=[3,2,1]     → true
    // Input:  pushed=[1],         popped=[1]          → true
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge2(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) throw new IllegalArgumentException("Arrays cannot be null");
        if (pushed.length != popped.length)   throw new IllegalArgumentException("Arrays must be same length");
        // TODO — simulate push/pop with an ArrayDeque as stack
        //        push elements one by one, popping while stack top matches popped[j]
        Deque<Integer> stack = new ArrayDeque<>();



        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Palindrome Check using a Deque.
    // A string is a palindrome if it reads the same forwards and backwards.
    // Use a Deque to compare characters from BOTH ends simultaneously.
    // Ignore case and non-alphanumeric characters.
    //
    // Input:  "racecar"          → true
    // Input:  "A man a plan a canal Panama"  → true  (ignore spaces/case)
    // Input:  "hello"            → false
    // Input:  "Was it a car or a cat I saw" → true
    // Input:  ""                 → true  (empty = palindrome)
    // ─────────────────────────────────────────────────────────────
    public static boolean challenge3(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — filter alphanumeric, push to Deque
        //        compare peekFirst() and peekLast(), pollFirst() and pollLast()
        //        until deque has 0 or 1 element
        String normalizedS = s.replaceAll("\\s+", "").trim().toLowerCase();

        Deque<Character> queue = new ArrayDeque<>();
        for (char c : normalizedS.toCharArray()) {
            queue.push(c);
        }

        while (!queue.isEmpty()) {
            char front = queue.peekFirst();
            char tail = queue.peekLast();
            if (front != tail) {
                return false;
            }
            queue.pollLast();
            queue.pollFirst();
        }

        return true;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Ticket Counter Simulation using a Queue.
    // Process a list of customers at a ticket counter.
    // Regular customers join the BACK of the queue.
    // VIP customers jump to the FRONT of the queue.
    //
    // Each customer is represented as "VIP:Name" or "REG:Name".
    // Return the list of names in the ORDER they were served.
    //
    // Input:  ["REG:Alice", "REG:Bob", "VIP:Carol", "REG:Diana", "VIP:Eve"]
    // Queue after all join:
    //   Eve → Carol → Alice → Bob → Diana  (VIPs at front in arrival order)
    // Output: ["Eve", "Carol", "Alice", "Bob", "Diana"]
    //
    // Note: VIPs are added in arrival order at the front,
    //       so the LAST VIP to arrive is at the VERY FRONT.
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge4(List<String> customers) {
        if (customers == null) throw new IllegalArgumentException("Customers cannot be null");
        // TODO — use ArrayDeque as Deque
        //        REG → addLast (join back)
        //        VIP → addFirst (jump to front)
        //        poll all into result list
        Deque<String> customerQueue = new ArrayDeque<>();
        for (String s : customers) {
            String[] split = s.split(":");
            String type = split[0];
            String name = split[1];
            if (type.equals("REG")) {
                customerQueue.offerLast(name);
            } else if (type.equals("VIP")) {
                customerQueue.offerFirst(name);
            }
        }

        return new ArrayList<>(customerQueue);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Sort a Stack using ONE additional Stack.
    // Given a list of integers representing a stack (last element = top),
    // sort it so the SMALLEST element is at the TOP.
    //
    // Rules:
    // → you may only use two ArrayDeques as stacks
    // → no arrays, no lists for sorting — only stack operations!
    // → return the sorted stack as a List (index 0 = bottom, last = top)
    //
    // Input:  [3,1,4,1,5,9,2,6]   (6 is top)
    // Output: [9,5,4,3,2,1,1,6]... wait
    //
    // sorted so SMALLEST at TOP:
    // Output: [9,6,5,4,3,2,1,1]  (1 is top = smallest)
    //
    // Algorithm:
    // → pop from main stack into temp stack in SORTED order
    // → when inserting to temp, if top of temp > current → move back to main
    // → result: temp stack has largest at bottom, smallest at top
    //
    // Input:  [3,1,4]  → sorted top=1: return as list [4,3,1]
    // Input:  [5,2,8]  → sorted top=2: return as list [8,5,2]
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge5(List<Integer> stack) {
        if (stack == null) throw new IllegalArgumentException("Stack cannot be null");
        // TODO — push all elements to mainStack (ArrayDeque)
        //        use tempStack to sort:
        //          while mainStack not empty:
        //            pop current from main
        //            while tempStack not empty AND temp.peek() < current:
        //              move temp.pop() back to main
        //            push current to temp
        //        drain tempStack to result list (index 0=bottom, last=top)
        Deque<Integer> mainStack = new ArrayDeque<>();
        for (Integer i : stack) {
            mainStack.push(i);
        }

        Deque<Integer> tmpStack = new ArrayDeque<>();
        while (!mainStack.isEmpty()) {
            int current = mainStack.pop();
            while (!tmpStack.isEmpty() && tmpStack.peek() > current) {
                mainStack.push(tmpStack.pop());
            }
            tmpStack.push(current);
        }

        List<Integer> result = new ArrayList<>();
        while (!tmpStack.isEmpty()) {
            result.add(tmpStack.pop());
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Score of Balanced Brackets using a Stack.
    // Compute the score of a balanced brackets string:
    // → "()" scores 1
    // → "AB" scores A + B  (concatenation)
    // → "(A)" scores 2 * A  (wrapping doubles the score)
    //
    // Algorithm (stack-based):
    // → push 0 onto stack as base
    // → for '(': push 0 (start new scope)
    // → for ')': pop v (inner score)
    //            pop w (outer score)
    //            push w + max(2*v, 1)
    //
    // Input:  "()"       → 1
    // Input:  "(())"     → 2
    // Input:  "()()"     → 2
    // Input:  "(()(()))" → 6
    //          outer wraps ()(()) → 2*(1+2) = 6
    // ─────────────────────────────────────────────────────────────
    public static int challenge6(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — use ArrayDeque<Integer> as stack
        //        push 0 as base, then process each char
        return 0;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 7–10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Browser History using Two Stacks.
    // Implement browser navigation with back/forward/visit operations.
    // Use TWO ArrayDeques as stacks: backStack and forwardStack.
    //
    // Operations (as strings):
    // "visit:url"   → visit new page, push current to back, CLEAR forward!
    // "back"        → go back one page (push current to forward, pop from back)
    //                 if back is empty → stay on current page
    // "forward"     → go forward one page (push current to back, pop from forward)
    //                 if forward is empty → stay on current page
    // "current"     → return current page
    //
    // Starting page: "home"
    //
    // Input:  ["visit:google", "visit:facebook", "back", "current",
    //          "forward", "current", "visit:twitter", "forward", "current"]
    //
    // Trace:
    //   start:           current=home
    //   visit:google   → current=google,   back=[home],      forward=[]
    //   visit:facebook → current=facebook, back=[home,google],forward=[]
    //   back           → current=google,   back=[home],      forward=[facebook]
    //   current        → "google"
    //   forward        → current=facebook, back=[home,google],forward=[]
    //   current        → "facebook"
    //   visit:twitter  → current=twitter,  back=[home,google,facebook],forward=[]
    //   forward        → (empty) stay at twitter
    //   current        → "twitter"
    //
    // Return list of results for "current" and "back"/"forward" operations
    // (only operations that produce output: "current" → current page)
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge7(List<String> operations) {
        if (operations == null) throw new IllegalArgumentException("Operations cannot be null");
        // TODO — use two ArrayDeque<String> as stacks: backStack and forwardStack
        //        current page starts as "home"
        //        only "current" operations add to result list
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Decode String using a Stack.
    // Given an encoded string in format k[encoded_string],
    // decode it by repeating encoded_string exactly k times.
    // Brackets can be NESTED!
    //
    // Rules:
    // → k is always a positive integer
    // → encoded_string contains only lowercase letters or nested patterns
    //
    // Algorithm:
    // → use TWO stacks: countStack (integers) and stringStack (strings)
    // → for digit → build the full number (could be multi-digit!)
    // → for '[' → push current string and current count to their stacks, reset both
    // → for ']' → repeat current string count times,
    //             then prepend the string from stringStack
    // → for letter → append to current string
    //
    // Input:  "3[a]"          → "aaa"
    // Input:  "3[a2[b]]"     → "abbabbabb"
    //          inner: 2[b]=bb → outer: 3[a+bb]=abbabbabb
    // Input:  "2[abc]3[cd]ef" → "abcabccdcdcdef"
    // Input:  "2[3[a]b]"     → "aaabaaab"
    //          inner: 3[a]=aaa → outer: 2[aaa+b]=aaabaaab
    // ─────────────────────────────────────────────────────────────
    public static String challenge8(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — use ArrayDeque<Integer> countStack and ArrayDeque<String> stringStack
        //        current string starts as ""
        //        current count starts as 0
        return "";
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Sliding Window MINIMUM using a Monotonic Deque.
    // Given an integer array and window size k, return the MINIMUM
    // value in each sliding window of size k.
    //
    // (Mirror of the sliding window maximum — but track MIN instead!)
    //
    // Deque stores INDICES of POTENTIALLY MINIMUM elements.
    // Deque is in INCREASING order of values (monotonically increasing).
    // → front = index of current window MINIMUM
    // → remove from front if out of window (index < i - k + 1)
    // → remove from back if value >= current (they'll never be min)
    //
    // Input:  nums=[3,1,2,4,0,5,3,2], k=3
    // Windows:
    //   [3,1,2]  → min=1
    //   [1,2,4]  → min=1
    //   [2,4,0]  → min=0
    //   [4,0,5]  → min=0
    //   [0,5,3]  → min=0
    //   [5,3,2]  → min=2
    //
    // Output: [1,1,0,0,0,2]
    // ─────────────────────────────────────────────────────────────
    public static int[] challenge9(int[] nums, int k) {
        if (nums == null) throw new IllegalArgumentException("Array cannot be null");
        if (k <= 0 || k > nums.length) throw new IllegalArgumentException("Invalid k");
        // TODO — use ArrayDeque<Integer> storing INDICES
        //        monotonic INCREASING (remove from back if nums[back] >= nums[i])
        //        front = minimum of current window
        return new int[]{};
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Hot Potato Game using a Deque.
    // N players sit in a circle. Starting from the first player,
    // pass the potato K times. The player holding the potato after
    // K passes is eliminated. Repeat until one player remains.
    // Return the list of eliminated players IN ORDER, then the winner.
    //
    // Simulation using Deque:
    // → add all players to deque (front = current holder)
    // → for each round: rotate K-1 times (move front to back)
    //                   then pollFirst() = eliminated player
    // → repeat until 1 player remains
    //
    // Input:  players=["Alice","Bob","Carol","Diana","Eve"], k=3
    //
    // Round 1: start=Alice, pass 3: Alice→Bob→Carol (Carol holds) → Carol OUT
    //   [Alice,Bob,Carol,Diana,Eve] → rotate 2 → [Carol,Diana,Eve,Alice,Bob]
    //   → pollFirst = Carol
    //   remaining = [Diana,Eve,Alice,Bob]
    //
    // Round 2: start=Diana, pass 3: Diana→Eve→Alice (Alice holds) → Alice OUT
    //   [Diana,Eve,Alice,Bob] → rotate 2 → [Alice,Bob,Diana,Eve]
    //   → pollFirst = Alice
    //   remaining = [Bob,Diana,Eve]
    //
    // Round 3: start=Bob, pass 3: Bob→Diana→Eve (Eve holds) → Eve OUT
    //   [Bob,Diana,Eve] → rotate 2 → [Eve,Bob,Diana]
    //   → pollFirst = Eve
    //   remaining = [Bob,Diana]
    //
    // Round 4: start=Bob, pass 3: Bob→Diana→Bob (Bob holds) → Bob OUT
    //   [Bob,Diana] → rotate 2 → [Bob,Diana]
    //   → pollFirst = Bob
    //   remaining = [Diana]
    //
    // Winner: Diana
    //
    // Output: eliminated=["Carol","Alice","Eve","Bob"], winner="Diana"
    // Return record HotPotatoResult(List<String> eliminated, String winner)
    // ─────────────────────────────────────────────────────────────
    record HotPotatoResult(List<String> eliminated, String winner) {}

    public static HotPotatoResult challenge10(List<String> players, int k) {
        if (players == null || players.isEmpty()) throw new IllegalArgumentException("Players cannot be null or empty");
        if (k <= 0) throw new IllegalArgumentException("k must be positive");
        // TODO — add all players to ArrayDeque
        //        each round: rotate (k-1) times (addLast(pollFirst()))
        //        then pollFirst() = eliminated player
        //        repeat until 1 remains → that is the winner
        return new HotPotatoResult(new ArrayList<>(), "");
    }
}