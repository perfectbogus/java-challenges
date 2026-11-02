package dev.perfectbogus.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPeekAheadChallenge {

    // CHALLENGE 1
    // Returns, in order of appearance, the words that are immediately followed by the character ':'. A word is a
    // maximal group of consecutive letters (a-z and A-Z). The ':' is not part of the result. If there are none, the
    // result is an empty list.
    // Example: wordsBeforeColon("name:John age:30")  returns [name, age]
    //          wordsBeforeColon("a:b:c")             returns [a, b]
    //          wordsBeforeColon("key : value")       returns []
    //          wordsBeforeColon("x1:")               returns []
    public static List<String> wordsBeforeColon(String text) {
        List<String> res = new ArrayList<>();
        Matcher m = Pattern.compile("[a-zA-Z]+(?=:)").matcher(text);
        while (m.find()) {
            res.add(m.group());
        }
        return res;
    }

    // CHALLENGE 2
    // Receives a number written with digits only (not empty). Removes its leading zeros, but always keeps at least
    // one digit.
    // Example: removeLeadingZeros("00120")  returns "120"
    //          removeLeadingZeros("1000")   returns "1000"
    //          removeLeadingZeros("000")    returns "0"
    //          removeLeadingZeros("0")      returns "0"
    public static String removeLeadingZeros(String digits) {
        return digits.replaceAll("^0+(?=\\d)", "");
    }

    // CHALLENGE 3
    // Adds one space after every comma that is immediately followed by a character that is not a whitespace. Commas
    // followed by a whitespace (a space, a tab or a line break), and a comma at the end of the text, do not change.
    // Example: addSpaceAfterComma("a,b, c,d")  returns "a, b, c, d"
    //          addSpaceAfterComma("a,,b")      returns "a, , b"
    //          addSpaceAfterComma("x,")        returns "x,"
    //          addSpaceAfterComma("a,\nb")     returns "a,\nb"
    public static String addSpaceAfterComma(String text) {
        return text.replaceAll(",(?=\\S)", ", ");
    }

    // CHALLENGE 4
    // Returns, in order of appearance, the sequences of digits that are followed by exactly one space and then the
    // word "kg". A sequence is a maximal group of consecutive digits. "kg" must be a complete word: if it is followed
    // by a letter, a digit or an underscore, it does not count. If there are none, the result is an empty list.
    // Example: kilogramAmounts("5 kg and 10 kg, 3 g")  returns [5, 10]
    //          kilogramAmounts("5 kgs and 7 kg.")      returns [7]
    //          kilogramAmounts("5kg and 6  kg")        returns []
    public static List<String> kilogramAmounts(String text) {
        List<String> res = new ArrayList<>();
        Matcher m = Pattern.compile("\\d+(?= kg\\b)").matcher(text);
        while (m.find()) {
            res.add(m.group());
        }
        return res;
    }

    // CHALLENGE 5
    // Receives a text of digits only, not empty, whose length is a multiple of 4. Returns it divided in groups of four
    // digits, separated by exactly one space (no space at the beginning or at the end).
    // Example: groupInFours("1234567812345678")  returns "1234 5678 1234 5678"
    //          groupInFours("12345678")          returns "1234 5678"
    //          groupInFours("1234")              returns "1234"
    public static String groupInFours(String digits) {
        return digits.replaceAll("(\\d{4})(?=\\d)", "$1 ");
    }

    // CHALLENGE 6
    // The text is not empty and has only letters (a-z and A-Z). Splits it into pieces so that every uppercase letter
    // starts a new piece. The first piece starts at the beginning of the text, even if it starts with a lowercase
    // letter. The pieces keep all their letters, and there are no empty pieces.
    // Example: splitBeforeCapitals("HelloWorldFoo")  returns [Hello, World, Foo]
    //          splitBeforeCapitals("helloWorld")     returns [hello, World]
    //          splitBeforeCapitals("ABC")            returns [A, B, C]
    //          splitBeforeCapitals("hello")          returns [hello]
    public static List<String> splitBeforeCapitals(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns true if the username is valid. A valid username:
    //   - has between 3 and 12 characters, both included,
    //   - has only letters (a-z and A-Z), digits (0-9) and the underscore '_',
    //   - does not start with a digit,
    //   - does not contain two underscores in a row ("__").
    // The username never contains line breaks.
    // Example: isValidUsername("john_doe")  returns true
    //          isValidUsername("_ab")       returns true
    //          isValidUsername("1john")     returns false
    //          isValidUsername("a__b")      returns false
    //          isValidUsername("jo")        returns false
    //          isValidUsername("john doe")  returns false
    public static boolean isValidUsername(String username) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // The text has several lines separated by the character '\n' (there is no '\r'). Returns, in order, the lines
    // that contain both the text "error" and the text "db" (in lowercase, in any order, and inside other words too).
    // The lines are returned exactly as they are. If there are none, the result is an empty list.
    // Example: errorDatabaseLines("db error\nok\nerror in db\nERROR db")  returns [db error, error in db]
    //          errorDatabaseLines("mydbserror")                           returns [mydbserror]
    //          errorDatabaseLines("only error\nonly db")                  returns []
    public static List<String> errorDatabaseLines(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns true if no character appears more than once in the text. The case matters ('a' and 'A' are different)
    // and spaces count like any other character. An empty text gives true. The text never contains line breaks.
    // Example: allDifferentCharacters("abc")  returns true
    //          allDifferentCharacters("aba")  returns false
    //          allDifferentCharacters("aA")   returns true
    //          allDifferentCharacters("a a")  returns false
    //          allDifferentCharacters("")     returns true
    public static boolean allDifferentCharacters(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns how many times "part" appears in "text", counting also the occurrences that overlap each other. part is
    // never empty. The case matters, and every character of part is a normal character (a dot or a '*' in part
    // means the dot or the star itself).
    // Example: countOverlapping("aaa", "aa")      returns 2
    //          countOverlapping("banana", "ana")  returns 2
    //          countOverlapping("aaaa", "aa")     returns 3
    //          countOverlapping("abc", "d")       returns 0
    //          countOverlapping("a.a.a", "a.a")   returns 2
    public static int countOverlapping(String text, String part) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
