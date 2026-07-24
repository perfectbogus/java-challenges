package dev.perfectbogus.leetcode.hashset;

import java.util.Arrays;

public class CloseStrings {

    public static void main(String[] args) {
        String word1 = "abc", word2 = "bca";
        System.out.println(closeStrings(word1, word2));
    }

    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int nLetters = 26;
        int[] freq1 = new int[nLetters];
        int[] freq2 = new int[nLetters];

        for (int i = 0; i < word1.length(); i++) {
            char a = word1.charAt(i);
            char b = word2.charAt(i);
            freq1[a - 'a']++;
            freq2[b - 'a']++;
        }

        for (int i = 0; i < nLetters; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(freq1);
        Arrays.sort(freq2);

        for (int i = 0; i < nLetters; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        return true;
    }
}
