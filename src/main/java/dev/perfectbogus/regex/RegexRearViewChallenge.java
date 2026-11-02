package dev.perfectbogus.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexRearViewChallenge {

    // CHALLENGE 1
    // Returns, in order of appearance, the words that start immediately after the character '@'. A word is a
    // maximal group of consecutive letters (a-z and A-Z). The '@' is not part of the result. If there are none, the
    // result is an empty list.
    // Example: wordsAfterAt("hi @ana and @bob")  returns [ana, bob]
    //          wordsAfterAt("a@b1")               returns [b]
    //          wordsAfterAt("@1ab")               returns []
    //          wordsAfterAt("ana bob")            returns []
    public static List<String> wordsAfterAt(String text) {
        List<String> res = new ArrayList<>();
        Matcher m = Pattern.compile("(?<=@)[A-Za-z]+").matcher(text);
        while (m.find()) {
            res.add(m.group());
        }
        return res;
    }

    // CHALLENGE 2
    // Replaces with a semicolon ';' every comma that is immediately after a digit. The other commas do not change.
    // Example: replaceCommasAfterDigits("1,2, a,3")  returns "1;2; a,3"
    //          replaceCommasAfterDigits("1,,2")      returns "1;,2"
    //          replaceCommasAfterDigits(",1")        returns ",1"
    //          replaceCommasAfterDigits("a,b")       returns "a,b"
    public static String replaceCommasAfterDigits(String text) {
        return text.replaceAll("(?<=\\d),", ";");
    }

    // CHALLENGE 3
    // Receives a text of digits only (it can be empty). Keeps the first three digits and replaces every other digit
    // with '*'. If the text has three digits or fewer, it does not change.
    // Example: maskAfterFirstThree("1234567")  returns "123****"
    //          maskAfterFirstThree("1234")     returns "123*"
    //          maskAfterFirstThree("123")      returns "123"
    //          maskAfterFirstThree("12")       returns "12"
    public static String maskAfterFirstThree(String digits) {
        return digits.replaceAll("(?<=\\d{3})\\d", "*");
    }

    // CHALLENGE 4
    // Returns, in order of appearance, the sequences of digits that are not immediately after a '-'. A sequence is a
    // maximal group of consecutive digits, so a sequence that is after a '-' is left out completely (not only its
    // first digit). If there are none, the result is an empty list.
    // Example: numbersNotAfterMinus("5 -3 10-2")  returns [5, 10]
    //          numbersNotAfterMinus("-35")        returns []
    //          numbersNotAfterMinus("a12 b-7")    returns [12]
    public static List<String> numbersNotAfterMinus(String text) {
        List<String> res = new ArrayList<>();
        Matcher m = Pattern.compile("(?<![-\\d])\\d+").matcher(text);
        while (m.find()) {
            res.add(m.group());
        }
        return res;
    }

    // CHALLENGE 5
    // The text has only letters (a-z and A-Z). Inserts an underscore '_' between a lowercase letter and an
    // uppercase letter that comes right after it. Nothing else changes.
    // Example: insertUnderscores("helloWorldFoo")  returns "hello_World_Foo"
    //          insertUnderscores("ABC")            returns "ABC"
    //          insertUnderscores("getHTTP")        returns "get_HTTP"
    //          insertUnderscores("Hello")          returns "Hello"
    public static String insertUnderscores(String text) {
        return text.replaceAll("(?<=[a-z])(?=[A-Z])", "_");
    }

    // CHALLENGE 6
    // Replaces with '*' every letter (a-z and A-Z) that is immediately after another letter. So the first letter of
    // every group of letters stays, and the rest of that group become '*'. Everything else does not change.
    // Example: censorAfterFirstLetter("hello big")  returns "h**** b**"
    //          censorAfterFirstLetter("don't")      returns "d**'t"
    //          censorAfterFirstLetter("a1bc")       returns "a1b*"
    //          censorAfterFirstLetter("")           returns ""
    public static String censorAfterFirstLetter(String text) {
        return text.replaceAll("(?<=[a-zA-Z])[a-zA-Z]", "*");
    }

    // CHALLENGE 7
    // Returns, in order of appearance, the words that come right after the word "the" followed by exactly one
    // space. A word is a maximal group of consecutive lowercase letters (a-z). "the" must be a complete word written
    // in lowercase (so "bathe cat" and "The cat" do not count). If there are none, the result is an empty list.
    // Example: wordsAfterThe("the cat saw the dog")  returns [cat, dog]
    //          wordsAfterThe("bathe cat")            returns []
    //          wordsAfterThe("the  cat")             returns []
    //          wordsAfterThe("The cat")              returns []
    public static List<String> wordsAfterThe(String text) {
        List<String> res = new ArrayList<>();
        Matcher m = Pattern.compile("(?<=\\bthe )[a-z]+").matcher(text);
        while (m.find()) {
            res.add(m.group());
        }
        return res;
    }
}
