package dev.perfectbogus.queues;

import java.util.List;

/**
 * 15 easy challenges about queues: Queue, Deque, ArrayDeque and PriorityQueue.
 *
 * Every method returns a new result and must NOT modify the arguments.
 */
public class QueuePlayChallenge {

    // ==========================================================
    // CHALLENGE 1: serveInOrder
    // ==========================================================
    // Each command is "arrive NAME" or "serve".
    // "arrive NAME": the customer joins the end of the line (NAME has no spaces).
    // "serve": the customer at the front leaves and is served. If the line is empty, the command is ignored.
    // Return the names in the order they were served. Customers never served are not included.
    //
    // ["arrive ana", "arrive bob", "serve", "arrive cy", "serve", "serve", "serve"] -> [ana, bob, cy]
    // ["serve", "arrive x", "serve"]                                                -> [x]
    // []                                                                            -> []
    public static List<String> serveInOrder(String[] commands) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 2: reverseWords
    // ==========================================================
    // Words are separated by exactly one space (no spaces at the start or the end).
    // Return the same words in reverse order, separated by one space.
    //
    // "a b c"        -> "c b a"
    // "hello world"  -> "world hello"
    // "one"          -> "one"
    // ""             -> ""
    public static String reverseWords(String sentence) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 3: areBracketsBalanced
    // ==========================================================
    // The text can have the brackets ( ) [ ] { }. Every other character is ignored.
    // Return true if every bracket is closed by the same kind of bracket, in the right order.
    //
    // "([]{})"  -> true
    // "(]"      -> false
    // "(("      -> false
    // ")("      -> false
    // "a(b)c"   -> true
    // ""        -> true
    public static boolean areBracketsBalanced(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 4: kSmallest
    // ==========================================================
    // Return the k smallest values of nums in ascending order. Repeated values count each time.
    // 1 <= k <= nums.length. nums can have up to 200,000 values.
    //
    // ([5, 1, 4, 2, 3], 2)  -> [1, 2]
    // ([3, 3, 1], 2)        -> [1, 3]
    // ([9], 1)              -> [9]
    public static int[] kSmallest(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 5: kthLargest
    // ==========================================================
    // Return the k-th largest value of nums. Repeated values count each time.
    // 1 <= k <= nums.length. nums can have up to 200,000 values.
    //
    // ([3, 2, 1, 5, 6, 4], 2)           -> 5
    // ([3, 2, 3, 1, 2, 4, 5, 5, 6], 4)  -> 4
    // ([7], 1)                          -> 7
    public static int kthLargest(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 6: mergeSortedArrays
    // ==========================================================
    // Every array inside arrays is sorted in ascending order (some can be empty).
    // Return one array with all the values, in ascending order. Up to 300,000 values in total.
    //
    // [[1, 4, 7], [2, 5], [3, 6, 8]]  -> [1, 2, 3, 4, 5, 6, 7, 8]
    // [[], [1], []]                   -> [1]
    // []                              -> []
    public static int[] mergeSortedArrays(int[][] arrays) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 7: isPalindrome
    // ==========================================================
    // Return true if the text reads the same from left to right and from right to left.
    // Every character counts (spaces too) and the case matters.
    //
    // "racecar"  -> true
    // "abca"     -> false
    // "Aa"       -> false
    // ""         -> true
    public static boolean isPalindrome(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 8: lastSurvivor
    // ==========================================================
    // The names stand in a circle, in the given order. Start counting at the first name: 1, 2, ... k.
    // The k-th person leaves the circle. Keep counting from the next person, until only one is left.
    // Return that name. 1 <= names.length <= 1000 and 1 <= k <= 1000.
    //
    // ([a, b, c, d, e], 2)  -> c      (b, d, a and e leave)
    // ([a, b, c, d, e], 1)  -> e      (a, b, c and d leave)
    // ([x], 5)              -> x
    public static String lastSurvivor(String[] names, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 9: runTasks
    // ==========================================================
    // Each task is "name:priority" (the priority is between 0 and 1,000,000).
    // Tasks with a higher priority run first. With the same priority, the task that came first runs first.
    // Return the names in the order they run. Up to 100,000 tasks.
    //
    // ["a:1", "b:5", "c:3"]        -> [b, c, a]
    // ["a:2", "b:2", "c:2"]        -> [a, b, c]
    // ["a:1", "b:2", "c:1", "d:2"] -> [b, d, a, c]
    // []                           -> []
    public static List<String> runTasks(String[] tasks) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 10: movingSum
    // ==========================================================
    // result[i] is the sum of the last k values up to position i (nums[i - k + 1] .. nums[i]).
    // At the start, when there are fewer than k values, add the ones that exist.
    // k >= 1. nums can have up to 300,000 values and k can be up to 300,000.
    //
    // ([1, 2, 3, 4, 5], 3)  -> [1, 3, 6, 9, 12]
    // ([5, -1, 2], 1)       -> [5, -1, 2]
    // ([1, 1, 1], 10)       -> [1, 2, 3]
    public static int[] movingSum(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 11: minCostToConnect
    // ==========================================================
    // You have ropes with the given lengths. Joining two ropes costs the sum of their lengths.
    // Join all the ropes into one, always choosing the cheapest way. Return the total cost.
    // Every length is between 1 and 1000 and there can be up to 100,000 ropes.
    //
    // [4, 3, 2, 6]  -> 29     (2+3=5, 4+5=9, 6+9=15 -> 5+9+15)
    // [5]           -> 0
    // []            -> 0
    // [1, 1]        -> 2
    public static long minCostToConnect(int[] ropes) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 12: binaryNumbers
    // ==========================================================
    // Return the first n positive numbers written in binary, as texts, in order: 1, 10, 11, 100, ...
    // n >= 0 and n can be up to 100,000.
    //
    // 5  -> [1, 10, 11, 100, 101]
    // 1  -> [1]
    // 0  -> []
    public static List<String> binaryNumbers(int n) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 13: rotateRight
    // ==========================================================
    // Return a new array with the values moved k places to the right.
    // The values that leave at the end come back at the start. k >= 0 and it can be bigger than nums.length.
    // nums can have up to 100,000 values and k can be up to 1,000,000,000.
    //
    // ([1, 2, 3, 4, 5], 2)  -> [4, 5, 1, 2, 3]
    // ([1, 2, 3], 3)        -> [1, 2, 3]
    // ([1, 2, 3], 4)        -> [3, 1, 2]
    // ([], 5)               -> []
    public static int[] rotateRight(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 14: topKFrequent
    // ==========================================================
    // Return the k most frequent words, the most frequent first.
    // Words with the same count go in alphabetical order. The case matters.
    // 1 <= k <= the number of different words. Up to 100,000 words.
    //
    // ([a, b, a, c, b, a], 2)  -> [a, b]
    // ([b, a, c], 2)           -> [a, b]
    // ([z, y, y], 1)           -> [y]
    public static String[] topKFrequent(String[] words, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ==========================================================
    // CHALLENGE 15: applyEditor
    // ==========================================================
    // A text editor starts with an empty text. Each operation is "type TEXT" or "undo" (TEXT has no spaces).
    // "type TEXT": TEXT is added at the end of the text.
    // "undo": removes the last TEXT that was typed and not undone yet. If there is nothing to undo, it is ignored.
    // Return the final text.
    //
    // ["type ab", "type cd", "undo", "type e"]  -> "abe"
    // ["undo", "type x"]                        -> "x"
    // ["type a", "undo", "undo"]                -> ""
    // []                                        -> ""
    public static String applyEditor(String[] ops) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
