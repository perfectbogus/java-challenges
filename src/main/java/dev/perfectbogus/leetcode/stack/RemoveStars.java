package dev.perfectbogus.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveStars {

    public static void main(String[] args) {
        String data = "leet**cod*e";
        System.out.println(removeStars(data));
    }

    public static String removeStars(String s) {
        /**
         pseudo code
         */
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c != '*') {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        char[] result = new char[stack.size()];
        int i = result.length - 1;
        while (!stack.isEmpty()) {
            result[i--] = stack.pop();
        }

        return new String(result);
    }
}
