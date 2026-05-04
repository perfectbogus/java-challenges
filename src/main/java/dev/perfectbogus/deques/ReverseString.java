package dev.perfectbogus.deques;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseString {

    public static String reverse(String str) {
        if (str == null) throw new IllegalArgumentException();
        if (str.isEmpty()) return str;

        Deque<Character> deque = new ArrayDeque<>(str.length());
        StringBuilder sb = new StringBuilder(str.length());

        for (int i = 0; i < str.length(); i++) {
            deque.push(str.charAt(i));
        }

        while (!deque.isEmpty()) {
            sb.append(deque.pop());
        }

        return sb.toString();
    }

    public static String solve(String str) {
        Deque<Character> deque = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            deque.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "hello";
        String reverse = solve(str);
        System.out.println(reverse);
    }

}
