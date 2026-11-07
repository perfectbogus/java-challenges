package dev.perfectbogus.sets;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SetsPracticeChallenge {

    // ==========================================================
    // HASHSET
    // ==========================================================

    // CHALLENGE 1 (HashSet)
    // Returns a set with the different words of the list. The case matters ("a" and "A" are different words). An empty
    // list gives an empty set.
    // Example: uniqueWords([a, b, a])  returns {a, b}
    //          uniqueWords([a, A])     returns {a, A}
    //          uniqueWords([x, x, x])  returns {x}
    //          uniqueWords([])         returns {}
    public static HashSet<String> uniqueWords(List<String> words) {
        return words.stream().collect(Collectors.toCollection(() -> new HashSet<>()));
    }

    public static HashSet<String> uniqueWordsSimplifiedV2(List<String> words) {
        return words.stream().collect(Collectors.toCollection(HashSet::new));
    }

    public static HashSet<String> uniqueWordsSimplified(List<String> words) {
        return new HashSet<>(words);
    }

    // CHALLENGE 2 (HashSet)
    // Returns a new set with the elements that are in the first set, in the second set, or in both. Neither of the
    // sets received is modified.
    // Example: union({1, 2, 3}, {3, 4})  returns {1, 2, 3, 4}
    //          union({1}, {})            returns {1}
    //          union({}, {})             returns {}
    public static HashSet<Integer> union(Set<Integer> first, Set<Integer> second) {
        HashSet<Integer> set = new HashSet<>(first);
        set.addAll(second);
        return set;
    }

    // CHALLENGE 3 (HashSet)
    // Returns a new set with the elements that are in both sets. Neither of the sets received is modified.
    // Example: intersection({1, 2, 3}, {2, 3, 4})  returns {2, 3}
    //          intersection({1, 2}, {3, 4})        returns {}
    //          intersection({1, 2}, {})            returns {}
    public static HashSet<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        HashSet<Integer> merge = new HashSet<>(first);
        HashSet<Integer> match = new HashSet<>();
        merge.addAll(second);
        for (Integer i : merge) {
            if (first.contains(i) && second.contains(i)) match.add(i);
        }
        return match;
    }

    public static HashSet<Integer> intersectionSimplified(Set<Integer> first, Set<Integer> second) {
        HashSet<Integer> match = new HashSet<>();
        for (Integer i : first) {
            if (second.contains(i)) match.add(i);
        }
        return match;
    }

    public static HashSet<Integer> intersectionSimplifiedV2(Set<Integer> first, Set<Integer> second) {
        HashSet<Integer> result = new HashSet<>(first);
        result.retainAll(second);
        return result;
    }

    // CHALLENGE 4 (HashSet)
    // Returns a new set with the elements that are in the first set and are not in the second set. Neither of the
    // sets received is modified.
    // Example: difference({1, 2, 3}, {2, 5})  returns {1, 3}
    //          difference({1, 2}, {1, 2, 3})  returns {}
    //          difference({1, 2}, {})         returns {1, 2}
    public static HashSet<Integer> difference(Set<Integer> first, Set<Integer> second) {
        HashSet<Integer> diff = new HashSet<>();
        HashSet<Integer> set = new HashSet<>(first);
        for (Integer i : set) {
            if (!second.contains(i)) diff.add(i);
        }
        return diff;
    }

    public static HashSet<Integer> differenceSimplified(Set<Integer> first, Set<Integer> second) {
        HashSet<Integer> result = new HashSet<>(first);
        result.removeAll(second);
        return result;
    }

    // CHALLENGE 5 (HashSet)
    // Returns the first word of the list that has already appeared before in the list, that is, the word whose second
    // appearance comes first. If no word is repeated, returns null. The case matters.
    // Example: firstRepeatedWord([a, b, c, b, a])  returns "b"
    //          firstRepeatedWord([a, b, c])        returns null
    //          firstRepeatedWord([x, x, y, y])     returns "x"
    //          firstRepeatedWord([a, A])           returns null
    public static String firstRepeatedWord(List<String> words) {
        Set<String> set = new HashSet<>();
        for (String s : words) {
            if (!set.add(s)) return s;
        }
        return null;
    }

    // ==========================================================
    // LINKEDHASHSET
    // ==========================================================

    // CHALLENGE 6 (LinkedHashSet)
    // Returns a set with the different words of the list, in the order in which each word appears for the first time.
    // Example: distinctInOrder([b, a, b, c, a])  returns {b, a, c}
    //          distinctInOrder([x, x])           returns {x}
    //          distinctInOrder([])               returns {}
    public static LinkedHashSet<String> distinctInOrder(List<String> words) {
        return new LinkedHashSet<>(words);
    }

    // CHALLENGE 7 (LinkedHashSet)
    // Returns a set with the numbers that appear in both lists, without repetitions, in the order in which they appear
    // for the first time in the first list. Neither of the lists received is modified.
    // Example: commonInOrder([3, 1, 2, 3], [2, 3])  returns {3, 2}
    //          commonInOrder([5, 6], [7])           returns {}
    //          commonInOrder([1, 2, 3], [3, 2, 1])  returns {1, 2, 3}
    public static LinkedHashSet<Integer> commonInOrder(List<Integer> first, List<Integer> second) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>(first);
        set.retainAll(second);
        return set;
    }

    // CHALLENGE 8 (LinkedHashSet)
    // Returns a set with the different characters of the text, in the order in which each character appears for the
    // first time. The case matters and spaces count like any other character.
    // Example: uniqueChars("banana")  returns {b, a, n}
    //          uniqueChars("aAa")     returns {a, A}
    //          uniqueChars("a b a")   returns {a, ' ', b}
    //          uniqueChars("")        returns {}
    public static LinkedHashSet<Character> uniqueChars(String text) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : text.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static LinkedHashSet<Character> uniqueCharsSimplified(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    // CHALLENGE 9 (LinkedHashSet)
    // Returns a set with the words that appear exactly once in the list, in the same order they have in the list.
    // The case matters.
    // Example: appearOnce([a, b, a, c])     returns {b, c}
    //          appearOnce([x, x, x])        returns {}
    //          appearOnce([a, b, c])        returns {a, b, c}
    //          appearOnce([a, a, b, a, c])  returns {b, c}
    public static LinkedHashSet<String> appearOnce(List<String> words) {
        LinkedHashMap<String, Long> map = words.stream().collect(Collectors.groupingBy(
                Function.identity(),
                LinkedHashMap::new,
                Collectors.counting()
        ));

        LinkedHashSet<String> res = new LinkedHashSet<>();
        for (Map.Entry<String, Long> e : map.entrySet()) {
            if (e.getValue() == 1) res.add(e.getKey());
        }

        return res;
    }

    public static LinkedHashSet<String> appearOnceSimplified(List<String> words) {
        LinkedHashSet<String> once = new LinkedHashSet<>();
        Set<String> repeated = new HashSet<>();
        for (String w : words) {
            if (repeated.contains(w)) {
                continue;
            }
            if (!once.add(w)) {
                once.remove(w);
                repeated.add(w);
            }
        }
        return once;
    }

    // CHALLENGE 10 (LinkedHashSet)
    // Returns a new set with all the elements of the first set, followed by the elements of the second set that are
    // not in the first one. Each group keeps the order in which its set gives its elements. Neither of the sets
    // received is modified.
    // Example: mergeKeepingOrder({c, a}, {b, a, d})  returns {c, a, b, d}
    //          mergeKeepingOrder({x}, {})            returns {x}
    //          mergeKeepingOrder({}, {y, x})         returns {y, x}
    public static LinkedHashSet<String> mergeKeepingOrder(Set<String> first, Set<String> second) {
        LinkedHashSet<String> res = new LinkedHashSet<>(first);
        res.addAll(second);
        return res;
    }

    // ==========================================================
    // TREESET
    // ==========================================================

    // CHALLENGE 11 (TreeSet)
    // Returns a set with the different numbers of the list, ordered from the smallest to the greatest.
    // Example: sortedDistinct([3, 1, 3, 2])  returns {1, 2, 3}
    //          sortedDistinct([-1, 0, -1])   returns {-1, 0}
    //          sortedDistinct([])            returns {}
    public static TreeSet<Integer> sortedDistinct(List<Integer> numbers) {
        return new TreeSet<>(numbers);
    }

    // CHALLENGE 12 (TreeSet)
    // Returns the smallest element of the set that is greater than n (strictly greater). If there is none, returns -1.
    // All the elements of the set are zero or greater. n can be any int value. The set is not modified.
    // Example: with {2, 5, 9}
    //          nextBigger(set, 4)   returns 5
    //          nextBigger(set, 5)   returns 9
    //          nextBigger(set, 9)   returns -1
    //          nextBigger(set, -3)  returns 2
    public static int nextBigger(TreeSet<Integer> set, int n) {
        Integer i = set.higher(n);
        if (i == null) {
            return -1;
        } else {
            return i;
        }
    }

    // CHALLENGE 13 (TreeSet)
    // Returns how many elements of the set are between "from" and "to", both included. If "from" is greater than
    // "to", the result is 0. The set is not modified.
    // Example: with {1, 5, 9, 12}
    //          countBetween(set, 5, 12)  returns 3
    //          countBetween(set, 2, 4)   returns 0
    //          countBetween(set, 9, 9)   returns 1
    //          countBetween(set, 12, 5)  returns 0
    public static int countBetween(TreeSet<Integer> set, int from, int to) {
        if (from > to) return 0;
        return set.subSet(from, true, to, true).size();
    }

    // CHALLENGE 14 (TreeSet)
    // Returns a set with the different words of the list, ordered by length (the shortest first). Words with the same
    // length are ordered alphabetically (the normal comparison of texts, so any uppercase letter comes before any
    // lowercase letter).
    // Example: byLengthThenAlphabetical([bb, a, ccc, aa, a])  returns {a, aa, bb, ccc}
    //          byLengthThenAlphabetical([b, A])               returns {A, b}
    //          byLengthThenAlphabetical([])                   returns {}
    public static TreeSet<String> byLengthThenAlphabetical(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15 (TreeSet)
    // Removes from the set the "count" greatest elements, and returns them from the greatest to the smallest. If the
    // set has fewer than "count" elements, all of them are removed. A count of 0 changes nothing. The count is never
    // negative.
    // Example: each one starts with the set {3, 1, 2}
    //          removeLargest(set, 2)  returns [3, 2]     and the set is now {1}
    //          removeLargest(set, 0)  returns []         and the set does not change
    //          removeLargest(set, 5)  returns [3, 2, 1]  and the set is now {}
    public static List<Integer> removeLargest(TreeSet<Integer> set, int count) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}