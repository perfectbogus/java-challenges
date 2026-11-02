package dev.perfectbogus.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexZeroWidthChallenge {

    // CHALLENGE 1
    // Receives a whole number written with digits only (not empty, and without leading zeros, except the number "0"
    // itself). Returns the same number with a comma between each group of three digits, counting from the right.
    // Example: addThousandsSeparators("1234567")  returns "1,234,567"
    //          addThousandsSeparators("1000")     returns "1,000"
    //          addThousandsSeparators("999")      returns "999"
    //          addThousandsSeparators("0")        returns "0"
    public static String addThousandsSeparators(String digits) {
        return digits.replaceAll("(?<=\\d)(?=(\\d{3})+$)", ",");
    }

    // CHALLENGE 2
    // Returns, in order of appearance, the sequences of digits that are immediately followed by the character '%'.
    // A sequence is a maximal group of consecutive digits. The '%' is not part of the result. If there are none, the
    // result is an empty list.
    // Example: numbersBeforePercent("10% of 20 and 5%")  returns [10, 5]
    //          numbersBeforePercent("3.5% and 100 %")    returns [5]
    //          numbersBeforePercent("no numbers")        returns []
    public static List<String> numbersBeforePercent(String text) {
        List<String> results = new ArrayList<>();
        Matcher m = Pattern.compile("\\d+(?=%)").matcher(text);
        while (m.find()) {
            results.add(m.group());
        }
        return results;
    }

    // CHALLENGE 3
    // Returns, in order of appearance, the sequences of digits that come immediately after the character '$'. A
    // sequence is a maximal group of consecutive digits. The '$' is not part of the result. Digits that do not come
    // right after a '$' are ignored. If there are none, the result is an empty list.
    // Example: amountsAfterDollar("$5 and $120")    returns [5, 120]
    //          amountsAfterDollar("$12.50 and 30")  returns [12]
    //          amountsAfterDollar("5$ and $ 7")     returns []
    public static List<String> amountsAfterDollar(String text) {
        List<String> results = new ArrayList<>();
        Matcher m = Pattern.compile("(?<=\\$)\\d+").matcher(text);
        while (m.find()) {
            results.add(m.group());
        }
        return results;
    }

    // CHALLENGE 4
    // The text has only letters (a-z and A-Z), spaces, commas and periods. A word is a maximal group of consecutive
    // letters. Returns, in order of appearance, the words that are NOT immediately followed by a comma. If there are
    // none, the result is an empty list.
    // Example: wordsWithoutComma("red, green blue, sun")  returns [green, sun]
    //          wordsWithoutComma("one two.")              returns [one, two]
    //          wordsWithoutComma("a, b, c,")              returns []
    public static List<String> wordsWithoutComma(String text) {
        List<String> res = new ArrayList<>();
        Matcher m = Pattern.compile("\\b[A-Za-z]+\\b(?!,)").matcher(text);
        while (m.find()) {
            res.add(m.group());
        }
        return res;
    }

    // CHALLENGE 5
    // Returns true if the password has at least 8 characters, at least one digit (0-9), at least one lowercase letter
    // (a-z) and at least one uppercase letter (A-Z). Any other characters are allowed. The password never contains
    // line breaks.
    // Example: isStrongPassword("Abcdefg1")   returns true
    //          isStrongPassword("abcdefg1")   returns false  (no uppercase letter)
    //          isStrongPassword("Ab1")        returns false  (too short)
    //          isStrongPassword("Ab1!Ab1!")   returns true
    public static boolean isStrongPassword(String password) {
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}");
    }

    // CHALLENGE 6
    // Inserts one space between a lowercase letter (a-z) and an uppercase letter (A-Z) that comes right after it. The
    // rest of the text does not change. An empty text gives an empty text.
    // Example: splitCamelCase("helloWorldFooBar")  returns "hello World Foo Bar"
    //          splitCamelCase("iPhone")            returns "i Phone"
    //          splitCamelCase("HTTPServer")        returns "HTTPServer"
    //          splitCamelCase("")                  returns ""
    public static String splitCamelCase(String text) {
        return text.replaceAll("(?<=[a-z])(?=[A-Z])", " ");
    }

    // CHALLENGE 7
    // Splits the text into pieces, and every piece keeps the ';' character where it ended. Only the last piece can
    // end without a ';'. If the text ends with ';', there is no extra empty piece at the end. The text is never empty.
    // Example: splitAfterSemicolon("a;b;c")  returns [a;, b;, c]
    //          splitAfterSemicolon("a;b;")   returns [a;, b;]
    //          splitAfterSemicolon(";a")     returns [;, a]
    //          splitAfterSemicolon("abc")    returns [abc]
    //          splitAfterSemicolon(";;")     returns [;, ;]
    public static List<String> splitAfterSemicolon(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // The text has only letters and spaces, and the words are separated by exactly one space. Changes the word "a"
    // (lowercase, as a complete word) into "an" when the next word starts with a lowercase vowel (a, e, i, o, u).
    // Everything else stays the same, including "A".
    // Example: fixArticles("a apple and a banana")  returns "an apple and a banana"
    //          fixArticles("a egg a orange")         returns "an egg an orange"
    //          fixArticles("a a a")                  returns "an an a"
    //          fixArticles("extra apple")            returns "extra apple"
    //          fixArticles("a")                      returns "a"
    public static String fixArticles(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns, in order of appearance, the sequences of digits that are NOT immediately preceded by a '-'. A sequence
    // is a maximal group of consecutive digits, so in "-34" the sequence is "34" and it is ignored as a whole. If
    // there are none, the result is an empty list.
    // Example: numbersWithoutMinus("5 -3 10")      returns [5, 10]
    //          numbersWithoutMinus("-34 and 12")   returns [12]
    //          numbersWithoutMinus("3-4")          returns [3]
    //          numbersWithoutMinus("-1 -2")        returns []
    public static List<String> numbersWithoutMinus(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // The email has exactly one '@', with at least one character before it. Replaces with '*' every character before
    // the '@' except the first one. The '@' and everything after it do not change.
    // Example: maskEmailUser("john@mail.com")  returns "j***@mail.com"
    //          maskEmailUser("ab@x.org")       returns "a*@x.org"
    //          maskEmailUser("a@x.org")        returns "a@x.org"
    public static String maskEmailUser(String email) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
