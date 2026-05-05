package dev.perfectbogus.strings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ValidParenthesisSenior {

    private static final Map<Character, Character> MATCH_MAP = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );

    public static boolean isValid(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (s.isEmpty()) return true;

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (!MATCH_MAP.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.peek() != (char) MATCH_MAP.get(c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));      // true
        System.out.println(isValid("()[]{}"));  // true
        System.out.println(isValid("(]"));      // false
        System.out.println(isValid("([)]"));    // false
        System.out.println(isValid("{[]}"));    // true
        System.out.println(isValid("}"));       // false
    }
}
