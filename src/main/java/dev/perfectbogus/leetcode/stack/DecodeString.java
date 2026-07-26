package dev.perfectbogus.leetcode.stack;

import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(decodeString(s));

        System.out.println((int)'1' - '0' );

        System.out.println((int) '1' - '0');

        String s2 = "12345";

        int currNum = 0;
        for (char c : s2.toCharArray()) {
            currNum = (currNum * 10) + (c - '0');
        }
        System.out.println(currNum);
    }

    public static String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        int currNum = 0;
        Stack<Integer> intStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        for (char x : s.toCharArray()) {
            if (x >= '0' && x <= '9') {
                currNum = (currNum * 10) + (x - '0');
            } else {
                if (x == '[') {
                    intStack.push(currNum);
                    currNum = 0;
                    strStack.push(String.valueOf(x));
                } else if (x == ']') {
                    String temp = "";
                    while (!strStack.isEmpty() && !strStack.peek().equals("[")) {
                        temp = strStack.pop() + temp;
                    }
                    strStack.pop();

                    int num = intStack.pop();
                    StringBuilder tempAns = new StringBuilder();
                    for (int i = 0; i < num; ++i) {
                        tempAns.append(temp);
                    }
                    strStack.push(tempAns.toString());
                } else {
                    strStack.push(String.valueOf(x));
                }
            }
        }

        String ans = "";
        while (!strStack.isEmpty()) {
            ans = strStack.pop() + ans;
        }

        return ans;
    }
}
