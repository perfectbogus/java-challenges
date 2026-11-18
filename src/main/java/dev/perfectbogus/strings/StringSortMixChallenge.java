package dev.perfectbogus.strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringSortMixChallenge {

    // CHALLENGE 1
    // The sentence has words separated by exactly one space, with no space at the start or at the end. It can be an
    // empty text (no words). Returns the same words, in a sentence written the same way (one space between words),
    // with the words ordered by length from the shortest to the longest. Words with the same length are ordered
    // alphabetically (the normal comparison of texts, where the case matters). A repeated word appears as many times as
    // it was written.
    // Example: sortWordsOfSentence("the quick brown fox")  returns "fox the brown quick"
    //          sortWordsOfSentence("b a")                  returns "a b"
    //          sortWordsOfSentence("b B a")                returns "B a b"
    //          sortWordsOfSentence("")                     returns ""
    public static String sortWordsOfSentence(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.joining(" "));
    }

    // CHALLENGE 2
    // The words have only lowercase letters (a-z). Returns a new array with the same words, ordered by the number of
    // vowels (a, e, i, o, u) they have, from the word with more vowels to the word with fewer vowels. Words with the
    // same number of vowels are ordered alphabetically. The original array is not modified.
    // Example: sortedByVowelCount(["sky", "banana", "tree", "audio"])  returns [audio, banana, tree, sky]
    //          sortedByVowelCount(["ba", "ab"])                         returns [ab, ba]
    //          sortedByVowelCount(["aab", "ea"])                        returns [aab, ea]
    //          sortedByVowelCount([])                                   returns []
    public static String[] sortedByVowelCount(String[] words) {
        String vowels = "aeiou";
        return Arrays.stream(words)
                .sorted(Comparator.comparing(
                        (String a) -> a.chars().filter(c -> vowels.indexOf(c) != -1).count(),
                        Comparator.reverseOrder()).thenComparing(String::compareTo)
                ).toArray(String[]::new);
    }

    public static String[] sortedByVowelsCountExtended(String[] words) {
        String vowels = "aeiou";
        Comparator<String> byVowelsDesc = Comparator.comparing(
                a -> a.chars().filter(c -> vowels.indexOf(c) != -1).count(),
                Comparator.reverseOrder());
        Comparator<String> byAlpha = Comparator.naturalOrder();
        return Arrays.stream(words)
                .sorted(byVowelsDesc.thenComparing(byAlpha))
                .toArray(String[]::new);
    }

    public static String[] sortedByVowelsCountPerformance(String[] words) {
        String vowels = "aeiou";
        Map<String, Long> map = Arrays.stream(words).collect(Collectors.toMap(
                Function.identity(),
                s -> s.chars().filter(a -> vowels.indexOf(a) != -1).count()
        ));

        Comparator<String> byVowelsDesc = Comparator.comparing(map::get, Comparator.reverseOrder());
        Comparator<String> byAlpha = Comparator.naturalOrder();

        return Arrays.stream(words)
                .sorted(byVowelsDesc.thenComparing(byAlpha))
                .toArray(String[]::new);
    }

    // CHALLENGE 3
    // The text has only lowercase letters (a-z). Returns a text with the same characters, where the letters that appear
    // more times in the text come first. Letters that appear the same number of times are in alphabetical order. All
    // the copies of a letter are together.
    // Example: sortCharactersByFrequency("tree")    returns "eert"
    //          sortCharactersByFrequency("banana")  returns "aaannb"
    //          sortCharactersByFrequency("zzzaaa")  returns "aaazzz"
    //          sortCharactersByFrequency("")        returns ""
    public static String sortCharactersByFrequency(String text) {
        int[] counts = new int[26];
        Character[] chars = new Character[text.length()];
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i) - 'a']++;
            chars[i] = text.charAt(i);
        }
        Arrays.sort(chars, Comparator.comparing((Character c) -> counts[c - 'a'], Comparator.reverseOrder())
                .thenComparing(Comparator.naturalOrder()));
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }
        return result.toString();
    }

    public static String sortCharactersByFrequencyStream(String text) {
        Map<Character, Long> counts = text.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));

        return text.chars().mapToObj(c -> (char) c)
                .sorted(Comparator.comparing((Character c) -> counts.get(c)).reversed()
                        .thenComparing(Comparator.naturalOrder()))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    // CHALLENGE 4
    // The "order" text has different characters, and it says the position of each character in a made-up alphabet (the
    // first character of "order" is the first letter of that alphabet). The text has only characters that appear in
    // "order". Returns a text with the same characters as "text", sorted according to that alphabet. The case matters
    // ('a' and 'A' are different characters).
    // Example: sortByCustomOrder("hello", "lohe")  returns "llohe"
    //          sortByCustomOrder("abc", "cba")     returns "cba"
    //          sortByCustomOrder("aab", "ba")      returns "baa"
    //          sortByCustomOrder("", "x")          returns ""
    public static String sortByCustomOrder(String text, String order) {
        return text.chars().mapToObj(c -> (char)c)
                .sorted(Comparator.comparing(c -> order.indexOf(c)))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static String sortByCustomOrderSimplified(String text, String order) {
        return text.chars().mapToObj(c -> (char)c)
                .sorted(Comparator.comparing(order::indexOf))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    // CHALLENGE 5
    // Returns a new array with the "n" longest words, ordered by length from the longest to the shortest. Words with
    // the same length are ordered alphabetically (the normal comparison of texts), and that order also decides which
    // words are chosen when not all of the words with the same length fit. n is between 0 and the length of the array.
    // The original array is not modified.
    // Example: topLongestWords(["a", "ccc", "bb", "aaa"], 2)  returns [aaa, ccc]
    //          topLongestWords(["a", "bb"], 2)                returns [bb, a]
    //          topLongestWords(["ccc", "aaa", "bbb"], 2)      returns [aaa, bbb]
    //          topLongestWords(["a", "bb"], 0)                returns []
    public static String[] topLongestWords(String[] words, int n) {
        Comparator<String> byLengthDesc = Comparator.comparing(String::length, Comparator.reverseOrder());
        Comparator<String> byAlpha = Comparator.naturalOrder();
        return Arrays.stream(words)
                .sorted(byLengthDesc.thenComparing(byAlpha))
                .limit(n)
                .toArray(String[]::new);
    }

    public static String[] topLongestWordsSimplified(String[] words, int n) {
        return Arrays.stream(words)
                .sorted(Comparator.comparing(String::length, Comparator.reverseOrder())
                        .thenComparing(Comparator.naturalOrder()))
                .limit(n)
                .toArray(String[]::new);
    }

    // CHALLENGE 6
    // Each file name has exactly one dot, with at least one character before it and at least one after it, like
    // "report.txt". The part after the dot is the extension. Returns a new array with the same names, ordered by
    // extension alphabetically (the normal comparison of texts). Names with the same extension are ordered by the part
    // before the dot, also alphabetically. The original array is not modified.
    // Example: sortFilesByExtension(["b.txt", "a.zip", "c.doc", "a.txt"])  returns [c.doc, a.txt, b.txt, a.zip]
    //          sortFilesByExtension(["a-b.x", "a.x"])                      returns [a.x, a-b.x]
    //          sortFilesByExtension(["a.TXT", "a.txt"])                    returns [a.TXT, a.txt]
    //          sortFilesByExtension([])                                    returns []
    public static String[] sortFilesByExtension(String[] fileNames) {
        return Arrays.stream(fileNames)
                .sorted(Comparator.comparing(
                        (String s) -> s.split("\\.")[1])
                        .thenComparing(s -> s.split("\\.")[0])
                ).toArray(String[]::new);
    }

    public static String[] sortFilesByExtensionExtended(String[] fileNames) {
        Comparator<String> byExtension = Comparator.comparing(s -> s.split("\\.")[1]);
        Comparator<String> byName = Comparator.comparing(s -> s.split("\\.")[0]);
        return Arrays.stream(fileNames)
                .sorted(byExtension.thenComparing(byName))
                .toArray(String[]::new);
    }

    // CHALLENGE 7
    // Each version has 3 numbers separated by dots, like "1.10.2". Each number is between 0 and 999999 and has no
    // leading zeros. Returns a new array with the same versions, ordered from the oldest to the newest: first by the
    // first number, then by the second number, and then by the third number (compared as numbers, not as texts). The
    // original array is not modified.
    // Example: sortVersions(["1.10.0", "1.2.0", "0.9.9"])  returns [0.9.9, 1.2.0, 1.10.0]
    //          sortVersions(["1.0.10", "1.0.9"])           returns [1.0.9, 1.0.10]
    //          sortVersions(["9.0.0", "10.0.0"])           returns [9.0.0, 10.0.0]
    //          sortVersions([])                            returns []
    public static String[] sortVersions(String[] versions) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Each sentence has one or more words separated by exactly one space, with no space at the start or at the end.
    // Returns a new array with the same sentences, ordered by their last word alphabetically, ignoring the case. If two
    // sentences have the same last word (ignoring the case), they keep the order they have in the original array. The
    // original array is not modified.
    // Example: sortByLastWord(["go home", "say Apple", "a b Zoo"])  returns [say Apple, go home, a b Zoo]
    //          sortByLastWord(["x b", "y B"])                       returns [x b, y B]
    //          sortByLastWord(["x ab", "y a"])                      returns [y a, x ab]
    //          sortByLastWord([])                                   returns []
    public static String[] sortByLastWord(String[] sentences) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Each text is a whole number written with digits only, without a sign and without leading zeros (the number 0 is
    // written "0"). A number can have up to 50 digits, so it does not fit in an int or in a long. Returns a new array
    // with the same texts, ordered by the value of the number, from the smallest to the greatest. The original array is
    // not modified.
    // Example: sortNumbersAsText(["10", "9", "100", "1"])  returns [1, 9, 10, 100]
    //          sortNumbersAsText(["30", "12", "21"])       returns [12, 21, 30]
    //          sortNumbersAsText(["10", "0", "5"])         returns [0, 5, 10]
    //          sortNumbersAsText([])                       returns []
    public static String[] sortNumbersAsText(String[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Each entry is written as "name:score", where the name has no colon and the score is a whole number between 0 and
    // 1000. Returns a new array with only the names, ordered by score from the highest to the lowest. Entries with the
    // same score are ordered by name alphabetically (the normal comparison of texts). The original array is not
    // modified.
    // Example: rankByScore(["ana:90", "bob:100", "cy:90"])  returns [bob, ana, cy]
    //          rankByScore(["a:9", "b:10"])                 returns [b, a]
    //          rankByScore(["b:1", "B:1"])                  returns [B, b]
    //          rankByScore([])                              returns []
    public static String[] rankByScore(String[] entries) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
