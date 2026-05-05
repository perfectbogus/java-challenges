package dev.perfectbogus.deques;

import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {

    public static boolean isPalindrome(String str) {
        if (str == null) throw new IllegalArgumentException("Input cannot be null");
        if (str.isEmpty()) return true;

        Deque<Character> deque = new ArrayDeque<>(str.length());

        for (int i = 0; i < str.length(); i++) {
            deque.push(str.charAt(i));
        }

        while (!deque.isEmpty()) {
            Character head = deque.getFirst();
            Character tail = deque.getLast();

            if (!head.equals(tail)) {
                return false;
            } else {
                deque.pollFirst();
                deque.pollLast();
            }
        }
        return true;
    }
}
