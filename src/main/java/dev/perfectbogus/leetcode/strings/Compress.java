package dev.perfectbogus.leetcode.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Compress {

    public static void main(String[] args) {
        char[] data = {'a','a','b','b','c','c','c'};
        char[] data2 = {'a','a','a','b','b','a','a'};
        //compress(data);
        System.out.println(compress2(data2));
        System.out.println(Arrays.toString(data2));
    }

    public static int compress(char[] chars) {
        if (chars.length == 1) return 1;

        int n = chars.length;

        int i = 0;
        int j = i;
        int count = 0;
        while (i < n && j < n) {
            if (chars[i] == chars[j]) {
                count++;
                j++;
            } else {
                if (count > 1) {
                    char[] nChars = String.valueOf(count).toCharArray();
                    for (char nChar : nChars) {
                        chars[++i] = nChar;
                    }
                }
                i = j;
                count = 0;
            }
        }

        char[] nChars = String.valueOf(count).toCharArray();
        for (char nChar : nChars) {
            System.out.println(i);
            chars[++i] = nChar;
            System.out.println(i);
        }

        return i;
    }

    public static int compress2(char[] chars) {
        int ans = 0; // keep track of current position in compressed array

        // iterate through input array using i pointer
        for (int i = 0; i < chars.length;) {
            final char letter = chars[i]; // current character being compressed
            int count = 0; // count of consecutive occurrences of letter

            // count consecutive occurrences of letter in input array
            while (i < chars.length && chars[i] == letter) {
                ++count;
                ++i;
            }

            // write letter to compressed array
            chars[ans++] = letter;

            // if count is greater than 1, write count as string to compressed array
            if (count > 1) {
                // convert count to string and iterate over each character in string
                for (final char c : String.valueOf(count).toCharArray()) {
                    chars[ans++] = c;
                }
            }
        }

        // return length of compressed array
        return ans;
    }
}
