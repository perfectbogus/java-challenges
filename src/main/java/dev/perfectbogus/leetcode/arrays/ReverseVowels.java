package dev.perfectbogus.leetcode.arrays;

import java.util.Arrays;

public class ReverseVowels {

    public static void main(String[] args) {
        String s = "IceCream";

        String result = reverseVowels(s);
        System.out.println(result);
    }

    public static String reverseVowels(String s) {
        boolean[] isVowel = new boolean[128];
        isVowel['a'] = true; isVowel['e'] = true; isVowel['i'] = true; isVowel['o'] = true; isVowel['u'] = true;
        isVowel['A'] = true; isVowel['E'] = true; isVowel['I'] = true; isVowel['O'] = true; isVowel['U'] = true;

        int right = s.length() - 1;
        int left = 0;

        char[] data = s.toCharArray();

        while (left < right) {
            char lv = data[left];
            char rv = data[right];

            if (isVowel[lv] && isVowel[rv]) {
                char tmp = data[left];
                data[left] = data[right];
                data[right] = tmp;
                left++;
                right--;
            } else if (isVowel[lv] && !isVowel[rv]) {
                right--;
            } else if (!isVowel[lv] && isVowel[rv]) {
                left++;
            } else {
                right--;
                left++;
            }
        }

        return new String(data);
    }

}
