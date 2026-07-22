package dev.perfectbogus.leetcode.sliding.window;

import java.util.HashSet;
import java.util.Set;

public class VowelsInSubset {

    public static void main(String[] args) {
        String s = "abciiidef";

        System.out.println(maxVowels(s, 3));
        System.out.println(maxVowelsMap(s, 3));
    }

    public static int maxVowelsMap(String s, int k) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        char[] letters = s.toCharArray();

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (vowels.contains(letters[i])) count++;
        }

        int maxVowels = count;

        for (int i = k; i < letters.length; i++) {
            if (vowels.contains(letters[i])) count++;
            if (vowels.contains(letters[i-k])) count--;
            maxVowels = Math.max(maxVowels, count);
        }

        return maxVowels;
    }

    public static int maxVowels(String s, int k) {
        boolean[] vowels = new boolean[128];
        vowels['a'] = true;
        vowels['e'] = true;
        vowels['i'] = true;
        vowels['o'] = true;
        vowels['u'] = true;

        char[] letters = s.toCharArray();
        int count = 0;

        for (int i = 0; i < k; i++) {
            if (vowels[letters[i]]) {
                count++;
            }
        }

        int maxVowels = count;


        for (int i = k; i < letters.length; i++) {
            if (vowels[letters[i]]) {
                count++;
            }
            if (vowels[letters[i-k]]) {
                count--;
            }
            maxVowels = Math.max(maxVowels, count);
        }

        return maxVowels;
    }
}
