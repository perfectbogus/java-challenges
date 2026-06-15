package dev.perfectbogus.leetcode.strings;

public class GreatestCommonDivisorStrings {

    public static void main(String[] args) {
        String str1 = "ABCABC";
        String str2 = "ABC";

        System.out.println(gcdOfStrings(str1, str2));
    }

    public static String gcdOfStrings(String str1, String str2) {
        for (int i = 1; i <= str2.length(); i++) {
            String prefix = str2.substring(0, i);

            int common = str1.length()/prefix.length();

            String total = prefix.repeat(common);
            if (total.equals(str1)) {
                return prefix;
            }
        }
        return "";
    }

}
