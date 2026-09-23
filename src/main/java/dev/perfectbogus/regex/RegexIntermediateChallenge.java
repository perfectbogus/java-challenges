package dev.perfectbogus.regex;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexIntermediateChallenge {

    // CHALLENGE 1
    // Returns whether s is a syntactically valid email address: one or more
    // letters, digits, dots, underscores or hyphens, then '@', then one or
    // more letters, digits or hyphens, then a dot, then 2 to 6 letters.
    // The entire string must match (no leading or trailing characters).
    public static boolean isValidEmail(String s) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    // CHALLENGE 2
    // Returns every maximal run of digits in s, in the order they appear.
    // A leading '-' or a decimal point is not part of the number.
    public static List<String> extractNumbers(String s) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    // CHALLENGE 3
    // Returns s with every digit character replaced by '*'.
    public static String maskDigits(String s) {
        return s.replaceAll("\\d", "*");
    }

    // CHALLENGE 4
    // Returns how many maximal sequences of letters (A-Z, a-z only) appear
    // in s. Digits and punctuation are never part of a word and do not
    // count on their own.
    public static int countWords(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(s);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    // CHALLENGE 5
    // Returns whether s is a US phone number in exactly one of these two
    // formats: "123-456-7890" or "(123) 456-7890". Any other formatting
    // (missing space, missing dash, extra characters, etc.) is invalid.
    public static boolean isValidUSPhoneNumber(String s) {
        Pattern p = Pattern.compile("(?:\\d{3}-|\\(\\d{3}\\) )\\d{3}-\\d{4}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    // CHALLENGE 6
    // Returns every hashtag in s (a '#' immediately followed by one or more
    // letters, digits or underscores), as the tag text without the leading
    // '#', in the order they appear.
    public static List<String> extractHashtags(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Splits s on any run of one or more commas, semicolons and/or
    // whitespace characters, returning the non-empty tokens in order.
    public static List<String> splitOnMultipleDelimiters(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns whether s is a valid hex color code: a '#' followed by
    // exactly 3 or exactly 6 hexadecimal digits (0-9, a-f, A-F) and
    // nothing else.
    public static boolean isValidHexColor(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns s with every run of one or more whitespace characters
    // collapsed to a single space, and any leading or trailing whitespace
    // removed entirely.
    public static String collapseWhitespace(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns every date in s written as YYYY-MM-DD (four digits, hyphen,
    // two digits, hyphen, two digits), in the order they appear. The
    // digits are not checked for forming a real calendar date.
    public static List<String> extractDates(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // If digits consists of exactly 10 digit characters, returns it
    // reformatted as "XXX-XXX-XXXX". Otherwise, returns digits unchanged.
    public static String formatPhoneNumber(String digits) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns every substring of s enclosed in double quotes, with the
    // quotes themselves excluded, in the order they appear. A quote pair
    // with nothing between them ("") contributes an empty string.
    public static List<String> extractQuotedStrings(String s) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}