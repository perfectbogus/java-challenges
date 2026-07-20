package dev.perfectbogus.leetcode.two.pointers;

public class Subsequence {

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        boolean result = isSubsequence(s, t);
        System.out.println(result);
    }

    public static boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int i = 0;
        int j = 0;

        while (i < tChars.length && j < sChars.length) {
            if (sChars[j] == tChars[i]) {
                if (j == sChars.length - 1){
                    return true;
                }
                i++;
                j++;
            } else {
                i++;
            }
        }

        return false;
    }
}
