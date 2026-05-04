package dev.perfectbogus.strings;

import java.util.Stack;

public class ValidParentheses {

    public static boolean solve(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{' ) {
                stack.push(c);
            } else if (c == ')' ) {
                Character peek = stack.peek();
                if (peek == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c == ']') {
                Character peek = stack.peek();
                if (peek == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c == '}') {
                Character peek = stack.peek();
                if (peek == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        String s = "()";
        System.out.println(solve(s));
        // Bugs 1
        solve("]");   //EmptyStackException
        // Bug 2
    }
}
