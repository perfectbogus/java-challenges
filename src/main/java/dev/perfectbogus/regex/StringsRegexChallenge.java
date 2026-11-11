package dev.perfectbogus.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringsRegexChallenge {

    // CHALLENGE 1
    // Returns true if the whole text is a hexadecimal color: a '#' followed by exactly 6 characters, each one a digit
    // (0-9) or a letter from a to f, in lowercase or uppercase.
    // Example: isHexColor("#FF00aa")   returns true
    //          isHexColor("#FFF")      returns false
    //          isHexColor("FF00aa")    returns false
    //          isHexColor("#12345g")   returns false
    public static boolean isHexColor(String text) {
        return text.matches("(?i)#[a-f0-9]{6}");
    }

    // CHALLENGE 2
    // Returns, in order of appearance, the numbers written in the text. A number is a maximal group of consecutive
    // digits (0 to 9), so "12" is one number and not two. A minus sign is ignored (the numbers are never negative).
    // No group has more than 9 digits. Leading zeros do not matter. If there are none, the result is an empty list.
    // Example: extractNumbers("a12b3")      returns [12, 3]
    //          extractNumbers("-5 and 007")  returns [5, 7]
    //          extractNumbers("no numbers")  returns []
    //          extractNumbers("")            returns []
    public static List<Integer> extractNumbers(String text) {
        Matcher m = Pattern.compile("[0-9]+").matcher(text);
        List<Integer> res = new ArrayList<>();
        while (m.find()) {
            res.add(Integer.parseInt(m.group()));
        }
        return res;
    }

    // CHALLENGE 3
    // Rewrites every date of the text from the form yyyy-mm-dd to the form dd/mm/yyyy. A date is 4 digits, a hyphen,
    // 2 digits, a hyphen and 2 digits (the month and the day are not checked). The dates are never next to other
    // digits. The rest of the text does not change.
    // Example: reformatDates("Born 2001-02-03.")             returns "Born 03/02/2001."
    //          reformatDates("2026-11-11 and 2027-01-02")    returns "11/11/2026 and 02/01/2027"
    //          reformatDates("2026-1-1")                     returns "2026-1-1"
    //          reformatDates("no date")                      returns "no date"
    public static String reformatDates(String text) {
        return text.replaceAll("([0-9]{4})-([0-9]{2})-([0-9]{2})", "$3/$2/$1");
    }

    public static String reformateDatesSimplified(String text) {
        return text.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})","$3/$2/$1");
    }

    // CHALLENGE 4
    // Returns the words of the text, in order. The words are separated by one or more separators, and a separator is
    // a comma, a semicolon or a space. The text can start or end with separators. The result never has empty texts.
    // Any other character (like a tab) is part of a word. If there are no words, the result is an empty list.
    // Example: splitWords("a, b;c  d")    returns [a, b, c, d]
    //          splitWords(",,x,")         returns [x]
    //          splitWords(" ; , ")        returns []
    //          splitWords("")             returns []
    public static List<String> splitWords(String text) {
        String[] split = text.split("[,; ]+");
        List<String> res = new ArrayList<>();
        for (String s : split) {
            if (!s.isEmpty()) res.add(s);
        }
        return res;
    }

    public static List<String> splitWordsSimplified(String text) {
        return Arrays.stream(text.split("[,; ]+")).filter(s -> !s.isEmpty()).toList();
    }

    // CHALLENGE 5
    // Returns true if the whole text is a valid time in the 24 hour format "HH:mm": two digits for the hour, a colon,
    // and two digits for the minutes. The hour goes from 00 to 23 and the minutes go from 00 to 59.
    // Example: isValidTime("09:30")   returns true
    //          isValidTime("23:59")   returns true
    //          isValidTime("24:00")   returns false
    //          isValidTime("9:30")    returns false
    public static boolean isValidTime(String text) {
        if (text.length() != 5) return false;
        return text.matches("(([0-1])([0-9])|(2)([0-3])):([0-5])([0-9])");
    }

    public static boolean isValidTimeSimplified(String text) {
        return text.matches("([01]\\d|2[0-3]):[0-5]\\d");
    }

    // CHALLENGE 6
    // Rewrites every name written as "Last, First" as "First Last". A name of this kind is a group of letters (a-z and
    // A-Z), a comma, exactly one space, and another group of letters, where each group of letters is taken as long as
    // possible. The names are found from left to right without overlapping. The rest of the text does not change.
    // Example: swapNames("Smith, John")                    returns "John Smith"
    //          swapNames("Smith, John and Doe, Jane")      returns "John Smith and Jane Doe"
    //          swapNames("Smith,John")                     returns "Smith,John"
    //          swapNames("ab, cd, ef")                     returns "cd ab, ef"
    public static String swapNames(String text) {
        return text.replaceAll("([a-zA-Z]+), ([a-zA-Z]+)", "$2 $1");
    }

    // CHALLENGE 7
    // Returns how many times the word appears in the text as a complete word. The word is made only of letters (a-z
    // and A-Z) and is never empty. It is complete when the characters next to it are not letters, digits or
    // underscores (or when there is no character next to it). The case matters.
    // Example: countWholeWord("the cat and the hat", "the")  returns 2
    //          countWholeWord("other the", "the")            returns 1
    //          countWholeWord("The the", "the")              returns 1
    //          countWholeWord("the-end, the2", "the")        returns 1
    public static int countWholeWord(String text, String word) {
        int count = 0;
        Matcher m = Pattern.compile("\\b" + Pattern.quote(word) + "\\b").matcher(text);
        while (m.find()) {
            count++;
        }
        return count;
    }

    // CHALLENGE 8
    // The text has only letters and spaces. Returns true if the same word appears twice in a row, with exactly one
    // space between the two. A word is a maximal group of letters, and the case matters.
    // Example: hasRepeatedWord("the the cat")   returns true
    //          hasRepeatedWord("the theory")    returns false
    //          hasRepeatedWord("The the")       returns false
    //          hasRepeatedWord("go  go")        returns false
    public static boolean hasRepeatedWord(String text) {
        Matcher m = Pattern.compile("\\b([a-zA-Z]+) \\1\\b").matcher(text);
        return m.find();
    }

    // CHALLENGE 9
    // Returns true if the whole text is a valid e-mail address: one or more letters, digits or dots; then an '@'; then
    // one or more letters or digits; then a dot; and finally 2 or 3 letters. No other characters are allowed.
    // Example: isValidEmail("ana.lopez@mail.com")   returns true
    //          isValidEmail("a@b.co")               returns true
    //          isValidEmail("a@b.info")             returns false
    //          isValidEmail("a@b.com.mx")           returns false
    public static boolean isValidEmail(String text) {
        Matcher m = Pattern.compile("[a-zA-Z\\d.]+@[a-zA-Z\\d]+\\.[a-zA-Z]{2,3}").matcher(text);
        return m.matches();
    }

    // CHALLENGE 10
    // The text has only letters and, if it is not empty, it starts with a lowercase letter. Returns the text in snake
    // case: every uppercase letter is changed to lowercase and has an underscore '_' put before it.
    // Example: camelToSnake("helloWorldFoo")  returns "hello_world_foo"
    //          camelToSnake("hello")          returns "hello"
    //          camelToSnake("aB")             returns "a_b"
    //          camelToSnake("")               returns ""
    public static String camelToSnake(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns, in order of appearance, the hashtags of the text, without the '#'. A hashtag is a '#' followed by one
    // or more letters (a-z and A-Z), digits or underscores, taken as long as possible. A '#' can be anywhere, also in
    // the middle of a word. A '#' that is not followed by any of those characters does not make a hashtag. If there
    // are none, the result is an empty list.
    // Example: extractHashtags("#java is #fun")   returns [java, fun]
    //          extractHashtags("# alone")         returns []
    //          extractHashtags("a#b_1 ##c")       returns [b_1, c]
    //          extractHashtags("no tags")         returns []
    public static List<String> extractHashtags(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}