package dev.perfectbogus.characters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CharArraySortChallenge {

    // ==========================================================
    // CHALLENGE 1
    // ==========================================================
    // Returns a new array with the same characters ordered by their character code (the normal order of chars, so
    // uppercase letters come before lowercase ones). The original array is not modified.
    //
    // Examples:
    //   ['b', 'A', 'a']  ->  ['A', 'a', 'b']
    //   ['z', 'y']       ->  ['y', 'z']
    //   []               ->  []
    public static char[] sortChars(char[] chars) {
        char[] clone = chars.clone();
        Arrays.sort(clone);
        return clone;
    }

    // ==========================================================
    // CHALLENGE 2
    // ==========================================================
    // The array only has letters. Returns a new array ordered alphabetically ignoring the case, so 'a' and 'A' are the
    // same letter. Letters that are equal ignoring the case keep the order they had in the original array.
    //
    // Examples:
    //   ['b', 'A', 'a', 'B']  ->  ['A', 'a', 'b', 'B']
    //   ['a', 'A']            ->  ['a', 'A']
    //   ['A', 'a']            ->  ['A', 'a']
    //   ['c', 'B', 'a']       ->  ['a', 'B', 'c']
    public static char[] sortIgnoringCase(char[] letters) {
        return new String(letters).chars()
                .mapToObj(c -> (char)c)
                .sorted(Comparator.comparing(c -> Character.toLowerCase(c)))
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
    }

    public static char[] sortIgnoringCaseExtended(char[] letters) {
        Character[] boxed = new Character[letters.length];
        for (int i = 0; i < letters.length; i++) {
            boxed[i] = letters[i];
        }
        Arrays.sort(boxed, Comparator.comparing(c -> Character.toLowerCase(c)));
        char[] result = new char[boxed.length];
        for (int i = 0; i < boxed.length; i++) {
            result[i] = boxed[i];
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 3
    // ==========================================================
    // Returns a new array with the same characters ordered by their character code from the biggest to the smallest.
    // The original array is not modified.
    //
    // Examples:
    //   ['a', 'c', 'b']  ->  ['c', 'b', 'a']
    //   ['a', 'A']       ->  ['a', 'A']
    //   ['x', 'x']       ->  ['x', 'x']
    public static char[] sortDescending(char[] chars) {
        return new String(chars).chars()
                .mapToObj(c -> (char)c)
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
    }

    public static char[] sortDescendingSimplified(char[] chars) {
        char[] result = chars.clone();
        Arrays.sort(result);
        for (int i = 0, j = result.length - 1; i < j; i++, j--) {
            char temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 4
    // ==========================================================
    // The array only has lowercase letters from 'a' to 'z'. Returns a new array where the vowels (a, e, i, o, u) come
    // first and then the consonants. Inside each group the letters are in alphabetical order.
    //
    // Examples:
    //   ['b', 'a', 'c', 'e']  ->  ['a', 'e', 'b', 'c']
    //   ['z', 'u', 'a']       ->  ['a', 'u', 'z']
    //   ['d', 'c']            ->  ['c', 'd']
    //   []                    ->  []
    public static char[] vowelsFirst(char[] letters) {
        String custom = "aeioubcdfghjklmnpqrstvwxyz";
        return new String(letters).chars()
                .mapToObj(c -> (char)c)
                .sorted(Comparator.comparing(custom::indexOf))
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
    }

    // ==========================================================
    // CHALLENGE 5
    // ==========================================================
    // Returns a new array with the same letters ordered by how far their character code is from the code of target,
    // the closest first. If two letters are at the same distance, the one with the smaller code goes first.
    //
    // Examples:
    //   (['a', 'd', 'b'], 'c')  ->  ['b', 'd', 'a']
    //   (['z', 'x'], 'y')       ->  ['x', 'z']
    //   (['m', 'a'], 'm')       ->  ['m', 'a']
    //   ([], 'k')               ->  []
        public static char[] sortByDistance(char[] letters, char target) {
            return new String(letters).chars()
                    .mapToObj(c -> (char)c)
                    .sorted(Comparator.comparing((Character c) -> Math.abs(target - c))
                            .thenComparing(Comparator.naturalOrder()))
                    .map(String::valueOf)
                    .collect(Collectors.joining())
                    .toCharArray();
        }

    // ==========================================================
    // CHALLENGE 6
    // ==========================================================
    // The array only has letters. Returns a new array where the lowercase letters come first and then the uppercase
    // ones. Inside each group the letters are in alphabetical order (the normal order of chars).
    //
    // Examples:
    //   ['b', 'A', 'a', 'B']  ->  ['a', 'b', 'A', 'B']
    //   ['Z', 'y']            ->  ['y', 'Z']
    //   ['C', 'B']            ->  ['B', 'C']
    //   []                    ->  []
    public static char[] lowerBeforeUpper(char[] letters) {
        Comparator<Character> byLowerCase = ( a, b) -> {
            if (Character.isLowerCase(a) && Character.isLowerCase(b)) {
                return Character.compare(a, b);
            } else if (Character.isUpperCase(a) && Character.isUpperCase(b)) {
                return Character.compare(a, b);
            } else if (Character.isLowerCase(a) && !Character.isLowerCase(b)) {
                return -1;
            } else if (!Character.isLowerCase(a) && Character.isLowerCase(b)) {
                return 1;
            } else {
                return 0;
            }
        };
        return new String(letters).chars()
                .mapToObj(c -> (char)c)
                .sorted(byLowerCase)
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
    }

    public static char[] lowerBeforeUpperSimplified(char[] letters) {
        Comparator<Character> byLower = Comparator.comparing(Character::isUpperCase);
        Comparator<Character> byAlpha = Comparator.naturalOrder();
        return new String(letters).chars()
                .mapToObj(c -> (char)c)
                .sorted(byLower.thenComparing(byAlpha))
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
    }

    // ==========================================================
    // CHALLENGE 7
    // ==========================================================
    // Returns a new array with the k biggest characters (by character code) ordered from the biggest to the smallest.
    // Repeated characters count each time they appear. k is between 0 and the length of the array.
    //
    // Examples:
    //   (['a', 'c', 'b'], 2)  ->  ['c', 'b']
    //   (['a', 'a', 'b'], 2)  ->  ['b', 'a']
    //   (['a', 'b'], 0)       ->  []
    //   (['q'], 1)            ->  ['q']
    public static char[] topLargest(char[] chars, int k) {
        return new String(chars).chars()
                .mapToObj(c -> (char)c)
                .sorted(Comparator.reverseOrder())
                .limit(k)
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
    }

    // ==========================================================
    // CHALLENGE 8
    // ==========================================================
    // Every word has at least one character. Returns a new array ordered by the last character of each word. Words
    // that end with the same character are ordered alphabetically (the normal comparison of texts).
    //
    // Examples:
    //   ["ab", "ca", "bb"]  ->  ["ca", "ab", "bb"]
    //   ["bb", "ab"]        ->  ["ab", "bb"]
    //   ["Zb", "aa"]        ->  ["aa", "Zb"]
    //   []                  ->  []
    public static String[] sortWordsByLastChar(String[] words) {
        return Arrays.stream(words)
                .sorted(Comparator.comparing((String s) -> s.charAt(s.length() - 1))
                        .thenComparing(Comparator.naturalOrder()))
                .toArray(String[]::new);
    }

    // ==========================================================
    // CHALLENGE 9
    // ==========================================================
    // Every word has an odd number of characters, so it has one character exactly in the middle. Returns a new array
    // ordered by that middle character. Words with the same middle character keep the order they had in the original.
    //
    // Examples:
    //   ["abc", "xay", "zzb"]  ->  ["xay", "abc", "zzb"]
    //   ["xbx", "aba"]         ->  ["xbx", "aba"]
    //   ["z", "a"]             ->  ["a", "z"]
    //   []                     ->  []
    public static String[] sortByMiddleChar(String[] words) {
        return Arrays.stream(words)
                .sorted(Comparator.comparing((String s) -> s.charAt(s.length()/2)))
                .toArray(String[]::new);
    }

    // ==========================================================
    // CHALLENGE 10
    // ==========================================================
    // Every row has at least one character. Returns a new array with the same rows ordered by their first character.
    // Rows with the same first character are ordered by length, the shorter first. Rows that are still equal keep the
    // order they had. The rows themselves are not modified.
    //
    // Examples:
    //   [['b'], ['a', 'z'], ['a']]    ->  [['a'], ['a', 'z'], ['b']]
    //   [['a', 'y'], ['a', 'x']]      ->  [['a', 'y'], ['a', 'x']]
    //   [['c', 'a', 'a'], ['b']]      ->  [['b'], ['c', 'a', 'a']]
    //   []                            ->  []
    public static char[][] sortRows(char[][] rows) {
        String[] data = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            data[i] = new String(rows[i]);
        }
        Comparator<String> byFirst = Comparator.comparing(s -> s.charAt(0));
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        Arrays.sort(data, byFirst.thenComparing(byLength));
        char[][] result = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = data[i].toCharArray();
        }
        return result;
    }

    public static char[][] sortRowsSimplified(char[][] rows) {
        char[][] result = rows.clone();
        Arrays.sort(result, Comparator.comparing((char[] row) -> row[0]).thenComparingInt(row -> row.length));
        return result;
    }
}
