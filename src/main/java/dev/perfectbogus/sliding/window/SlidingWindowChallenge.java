package dev.perfectbogus.sliding.window;

public class SlidingWindowChallenge {

    // CHALLENGE 1
    // Returns the greatest sum that can be made by adding k consecutive numbers of the array. The array has at least
    // one number, k is between 1 and the length of the array, and the numbers can be negative. The numbers are
    // between -1000000000 and 1000000000, so a sum can be bigger than an int. The array is not modified.
    // Example: maxSumOfSize([1, 3, -2, 5, 4], 2)  returns 9
    //          maxSumOfSize([-3, -1, -2], 2)      returns -3
    //          maxSumOfSize([5], 1)               returns 5
    //          maxSumOfSize([1, 2, 3], 3)         returns 6
    public static long maxSumOfSize(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        long greatest = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i -k];
            if (sum > greatest) {
                greatest = sum;
            }
        }
        return greatest;
    }

    // CHALLENGE 2
    // Returns an array with the sum of every group of k consecutive numbers, from the first group to the last one. The
    // result has (length - k + 1) numbers. The array has at least one number, k is between 1 and the length of the
    // array, and every sum fits in an int. The array is not modified.
    // Example: windowSums([1, 2, 3, 4], 2)  returns [3, 5, 7]
    //          windowSums([1, 2, 3], 3)     returns [6]
    //          windowSums([4, 5], 1)        returns [4, 5]
    //          windowSums([-1, 1, -1], 2)   returns [0, 0]
    public static int[] windowSums(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        res[0] = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            res[i - k + 1] = sum;
        }
        return res;
    }

    // CHALLENGE 3
    // Returns how many groups of k consecutive numbers have a sum of "target" or more. The array has at least one
    // number, k is between 1 and the length of the array, and the numbers are between -1000 and 1000. The array is
    // not modified.
    // Example: countWindowsWithSumAtLeast([1, 2, 3, 4], 2, 5)  returns 2     (the groups 2+3 and 3+4)
    //          countWindowsWithSumAtLeast([1, 1, 1], 2, 3)     returns 0
    //          countWindowsWithSumAtLeast([5], 1, 5)           returns 1
    //          countWindowsWithSumAtLeast([-1, -2], 1, -1)     returns 1
    public static int countWindowsWithSumAtLeast(int[] nums, int k, int target) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        if (sum >= target) count++;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            if (sum >= target) count++;
        }

        return count;
    }

    // CHALLENGE 4
    // The text has only lowercase letters (a-z) and k is between 1 and the length of the text. Returns the greatest
    // number of vowels (a, e, i, o, u) that a group of k consecutive characters of the text can have.
    // Example: maxVowelsInWindow("abciiidef", 3)  returns 3     (the group "iii")
    //          maxVowelsInWindow("aeiou", 2)      returns 2
    //          maxVowelsInWindow("rhythm", 4)     returns 0
    //          maxVowelsInWindow("leetcode", 3)   returns 2
    public static int maxVowelsInWindow(String text, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns how many groups of k consecutive numbers are strictly increasing, which means that each number of the
    // group is greater than the one before it. A group with one number is always increasing. The array has at least
    // one number and k is between 1 and the length of the array. The array is not modified.
    // Example: countIncreasingWindows([1, 2, 3, 1, 2, 3], 3)  returns 2
    //          countIncreasingWindows([1, 2, 2, 3], 2)        returns 2
    //          countIncreasingWindows([3, 2, 1], 2)           returns 0
    //          countIncreasingWindows([5, 5], 1)              returns 2
    public static int countIncreasingWindows(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns true if there are two different positions i and j with the same number in them (nums[i] == nums[j]) and
    // with a distance between them of k or less (the difference between i and j is at most k). k is zero or greater.
    // The array can be empty. The array is not modified.
    // Example: hasNearbyDuplicate([1, 2, 3, 1], 3)  returns true
    //          hasNearbyDuplicate([1, 2, 3, 1], 2)  returns false
    //          hasNearbyDuplicate([1, 0, 1, 1], 1)  returns true
    //          hasNearbyDuplicate([1, 1], 0)        returns false
    public static boolean hasNearbyDuplicate(int[] nums, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Both texts have only lowercase letters (a-z) and the pattern is never empty. Returns true if the text has a
    // group of consecutive characters that is an anagram of the pattern: it has the same length as the pattern, and
    // the same letters the same number of times, in any order.
    // Example: containsAnagram("cbaebabacd", "abc")  returns true     (the group "cba")
    //          containsAnagram("abcd", "dc")         returns true     (the group "cd")
    //          containsAnagram("abcd", "ca")         returns false
    //          containsAnagram("ab", "abc")          returns false
    public static boolean containsAnagram(String text, String pattern) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // All the numbers of the array are 1 or greater and the target is 1 or greater. Returns the length of the
    // shortest group of consecutive numbers whose sum is "target" or more. If there is no such group, returns 0. The
    // sum of the whole array can be bigger than an int. The array can be empty. The array is not modified.
    // Example: minLengthWithSumAtLeast([2, 3, 1, 2, 4, 3], 7)  returns 2     (the group 4+3)
    //          minLengthWithSumAtLeast([1, 1, 1], 5)           returns 0
    //          minLengthWithSumAtLeast([10], 5)                returns 1
    //          minLengthWithSumAtLeast([1, 4, 4], 4)           returns 1
    public static int minLengthWithSumAtLeast(int[] nums, int target) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // All the numbers of the array are zero or greater and the limit is zero or greater. Returns the length of the
    // longest group of consecutive numbers whose sum is "limit" or less. If there is no such group, returns 0. The
    // sum of the whole array can be bigger than an int. The array can be empty. The array is not modified.
    // Example: longestSubarrayWithSumAtMost([1, 2, 3, 4], 5)  returns 2
    //          longestSubarrayWithSumAtMost([5, 5], 4)        returns 0
    //          longestSubarrayWithSumAtMost([0, 0, 0], 0)     returns 3
    //          longestSubarrayWithSumAtMost([2, 1, 1, 2], 4)  returns 3
    public static int longestSubarrayWithSumAtMost(int[] nums, int limit) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // The array has only 0 and 1. Returns the length of the longest group of consecutive numbers that has at most k
    // zeros (you could change those zeros into ones to get a group of only ones). k is zero or greater. The array can
    // be empty. The array is not modified.
    // Example: longestOnes([1, 1, 0, 1], 0)     returns 2
    //          longestOnes([1, 0, 0, 1, 1], 2)  returns 5
    //          longestOnes([0, 0], 1)           returns 1
    //          longestOnes([], 3)               returns 0
    public static int longestOnes(int[] bits, int k) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns the length of the longest group of consecutive characters of the text in which no character appears
    // twice. The case matters ('a' and 'A' are different characters). An empty text gives 0.
    // Example: longestUniqueSubstring("abcabcbb")  returns 3     (the group "abc")
    //          longestUniqueSubstring("bbbb")      returns 1
    //          longestUniqueSubstring("pwwkew")    returns 3     (the group "wke")
    //          longestUniqueSubstring("aAa")       returns 2     (the group "aA")
    public static int longestUniqueSubstring(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
