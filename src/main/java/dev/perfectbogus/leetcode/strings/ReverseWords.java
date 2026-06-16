package dev.perfectbogus.leetcode.strings;

public class ReverseWords {
    public static void main(String[] args) {
        String s = "the sky is blue";
//        String result = reverseWords(s);
//        System.out.println(result);

        System.out.println(reverseWordsArray(s));
    }

    public static String reverseWordsArray(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;

        int index = chars.length - 1;

        char[] result = new char[length];
        int resultIndex = 0;

        while (index >= 0) {
            // Trim Start
            while (index >= 0 && chars[index] == ' ') {
                index--;
            }

            if (index < 0) {
                break;
            }

            int wordEnd = index;

            while (index >= 0 && chars[index] != ' ') {
                index--;
            }

            int wordStart = index + 1;

            if (resultIndex != 0) {
                result[resultIndex++] = ' ';
            }

            for (int i = wordStart; i <= wordEnd; i++) {
                result[resultIndex++] = chars[i];
            }

        }
        return new String(result, 0, resultIndex);
    }

    public static String reverseWords(String s) {
        final String prepared = s.replaceAll("\\s+", " ").trim();
        final String[] split = prepared.split(" ");
        String[] result = new String[split.length];


        int j = split.length - 1;
        for (String string : split) {
            result[j--] = string;
        }

        return String.join(" ", result);
    }
}
