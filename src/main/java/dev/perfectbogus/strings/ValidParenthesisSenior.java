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

    public static boolean solve(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (MATCH_MAP.containsValue(c)) {
                stack.push(c);
            } else if (MATCH_MAP.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != MATCH_MAP.get(c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(solve("()"));      // true
        System.out.println(solve("()[]{}"));  // true
        System.out.println(solve("(]"));      // false
        System.out.println(solve("([)]"));    // false
        System.out.println(solve("{[]}"));    // true
        System.out.println(solve("}"));       // false
    }
}
