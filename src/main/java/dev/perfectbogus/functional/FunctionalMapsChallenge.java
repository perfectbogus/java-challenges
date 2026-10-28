package dev.perfectbogus.functional;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalMapsChallenge {

    // CHALLENGE 1
    // Groups the items by the key that keyFn gives for each item. Every key has the list of its items, in the same
    // order they have in items. keyFn never gives null. An empty list gives an empty map.
    // Example: groupBy([a, bb, c, dd, eee], String::length)  returns {1=[a, c], 2=[bb, dd], 3=[eee]}
    //          groupBy([1, 2, 3, 4, 5], n -> n % 2)          returns {0=[2, 4], 1=[1, 3, 5]}
    public static <T, K> Map<K, List<T>> groupBy(List<T> items, Function<T, K> keyFn) {
        return items.stream().collect(Collectors.groupingBy(keyFn));
    }

    // CHALLENGE 2
    // Counts how many items have each key that keyFn gives. keyFn never gives null. An empty list gives an empty map.
    // Example: countBy([a, bb, c, dd, eee], String::length)  returns {1=2, 2=2, 3=1}
    //          countBy([x, y, x], Function.identity())        returns {x=2, y=1}
    public static <T, K> Map<K, Long> countBy(List<T> items, Function<T, K> keyFn) {
        return items.stream().collect(Collectors.groupingBy(
                keyFn,
                Collectors.counting()
        ));
    }

    // CHALLENGE 3
    // Builds a map with keyFn(item) as the key and valueFn(item) as the value for each item. If two items give the
    // same key, the value of the first of them (in the order of the list) is kept. keyFn and valueFn never give null.
    // Example: toMapFirstWins([apple, avocado, banana], w -> w.charAt(0), String::length)  returns {a=5, b=6}
    //          toMapFirstWins([avocado, apple], w -> w.charAt(0), String::length)          returns {a=7}
    public static <T, K, V> Map<K, V> toMapFirstWins(List<T> items, Function<T, K> keyFn, Function<T, V> valueFn) {
        return items.stream().collect(Collectors.toMap(
                keyFn,
                valueFn,
                (existing, incoming) -> existing
        ));
    }

    // CHALLENGE 4
    // Returns a new map with the same keys as map, where every value is replaced by fn applied to that value. fn is
    // called exactly one time for each value. map is not modified. Values are never null.
    // Example: mapValues({a=1, b=2}, v -> v * 10)  returns {a=10, b=20}
    //          mapValues({a=1}, v -> "n" + v)      returns {a=n1}
    //          mapValues({}, v -> v)               returns {}
    public static <K, V, R> Map<K, R> mapValues(Map<K, V> map, Function<V, R> fn) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> fn.apply(e.getValue())
        ));
    }

    // CHALLENGE 5
    // Returns a new map with only the entries of map whose value passes test. test is called exactly one time for
    // each value. map is not modified. Values are never null.
    // Example: filterByValue({a=1, b=5, c=8}, v -> v > 3)  returns {b=5, c=8}
    //          filterByValue({a=1}, v -> v > 3)            returns {}
    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> test) {
        return map.entrySet().stream().filter(e -> test.test(e.getValue())).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

    // CHALLENGE 6
    // Splits the words in two lists with the test. The key true has the words that pass the test and the key false has
    // the words that do not pass it, both in the same order they have in words. Both keys are always in the map, even
    // when one of the lists is empty. test is called exactly one time for each word.
    // Example: splitByTest([a, bb, c, dd], w -> w.length() > 1)  returns {false=[a, c], true=[bb, dd]}
    //          splitByTest([a], w -> w.length() > 1)              returns {false=[a], true=[]}
    //          splitByTest([], w -> true)                         returns {false=[], true=[]}
    public static Map<Boolean, List<String>> splitByTest(List<String> words, Predicate<String> test) {
        return words.stream().collect(Collectors.partitioningBy(
                test
        ));
    }

    // CHALLENGE 7
    // Each entry is a name with a number. Returns, for each name, the sum of all its numbers. An empty list gives an
    // empty map.
    // Example: totalByKey([a=1, b=5, a=2])  returns {a=3, b=5}
    //          totalByKey([x=4])            returns {x=4}
    //          totalByKey([])               returns {}
    public static Map<String, Integer> totalByKey(List<Map.Entry<String, Integer>> entries) {
        return entries.stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                Integer::sum
        ));
    }

    // CHALLENGE 8
    // Each entry is a name with a number. Returns, for each name, the average of all its numbers, as a double (the
    // decimals are kept). An empty list gives an empty map.
    // Example: averageByKey([a=1, b=5, a=2])  returns {a=1.5, b=5.0}
    //          averageByKey([x=4])            returns {x=4.0}
    //          averageByKey([])               returns {}
    public static Map<String, Double> averageByKey(List<Map.Entry<String, Integer>> entries) {
        return entries.stream().collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.averagingDouble(Map.Entry::getValue)
        ));
    }

    // CHALLENGE 9
    // Returns a new map with all the keys of a and of b. A key that is only in one of the maps keeps its value. For a
    // key that is in both maps, the value is combine.apply(value in a, value in b), in that order. combine is only
    // called for the keys that are in both maps, one time for each of them. a and b are not modified.
    // Example: mergeWith({x=1, y=2}, {y=10, z=3}, (p, q) -> p + q)  returns {x=1, y=12, z=3}
    //          mergeWith({y=2}, {y=10}, (p, q) -> p - q)            returns {y=-8}
    //          mergeWith({}, {z=3}, (p, q) -> p + q)                returns {z=3}
    public static <K, V> Map<K, V> mergeWith(Map<K, V> a, Map<K, V> b, BinaryOperator<V> combine) {
        Map<K, V> mergeMap = new HashMap<>(a);
        b.forEach((k, v) -> mergeMap.merge(k, v, combine));
        return mergeMap;
    }

    public static <K, V> Map<K, V> mergeWithImproved(Map<K, V> a, Map<K, V> b, BinaryOperator<V> combine) {
        return Stream.concat(a.entrySet().stream(), b.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        combine
                ));
    }

    // CHALLENGE 10
    // Groups the items by keyFn and, for each key, keeps only the greatest item according to comparator. If two items
    // of a group are equally great, the first of them in the list is kept. keyFn never gives null.
    // Example: maxPerGroup([apple, avocado, banana, fig], w -> w.charAt(0), Comparator.comparingInt(String::length))
    //          returns {a=avocado, b=banana, f=fig}
    //          maxPerGroup([ab, cd, ax], w -> w.charAt(0), Comparator.comparingInt(String::length))
    //          returns {a=ab, c=cd}  (ab and ax have the same length, and ab comes first)
    public static <T, K> Map<K, T> maxPerGroup(List<T> items, Function<T, K> keyFn, Comparator<T> comparator) {
        return items.stream().collect(Collectors.groupingBy(
                keyFn,
                Collectors.collectingAndThen(
                        Collectors.maxBy(comparator),
                        Optional::get
                )
        ));
    }

    // CHALLENGE 11
    // Groups the words by their first character. For each character, the words that start with it are written in one
    // text, separated by a comma, in the same order they have in words. The case matters ('A' and 'a' are different
    // keys). No word is empty.
    // Example: wordsByInitial([apple, avocado, banana])  returns {a=apple,avocado, b=banana}
    //          wordsByInitial([Ant, ant])                returns {A=Ant, a=ant}
    //          wordsByInitial([])                        returns {}
    public static Map<Character, String> wordsByInitial(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                s -> s.charAt(0),
                Collectors.joining(",")
        ));
    }

    // CHALLENGE 12
    // Counts how many times each word appears. The keys of the returned map are in the alphabetical order of the
    // normal comparison of texts (so "B" comes before "a"). The result is a TreeMap.
    // Example: countSorted([pear, apple, pear, fig])  returns {apple=1, fig=1, pear=2}
    //          countSorted([b, B, a])                 returns {B=1, a=1, b=1}
    //          countSorted([])                        returns {}
    public static TreeMap<String, Long> countSorted(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                Function.identity(),
                () -> new TreeMap<>(),
                Collectors.counting()
        ));
    }

    // CHALLENGE 13
    // Returns the keys of the map, from the key with the greatest value to the key with the smallest value. If two
    // keys have the same value, the one that comes first alphabetically goes first. map is not modified.
    // Example: keysByValueDescending({a=1, b=3, c=2})  returns [b, c, a]
    //          keysByValueDescending({b=2, a=2, c=5})  returns [c, a, b]
    //          keysByValueDescending({})               returns []
    public static List<String> keysByValueDescending(Map<String, Integer> map) {
        Comparator<Map.Entry<String, Integer>> byValueDesc = Map.Entry.<String, Integer>comparingByValue().reversed();
        Comparator<Map.Entry<String, Integer>> byKey = Map.Entry.comparingByKey();
        return map.entrySet().stream()
                .sorted(byValueDesc.thenComparing(byKey))
                .map(Map.Entry::getKey)
                .toList();
    }

    // CHALLENGE 14
    // Returns a collector that counts how many texts of a stream have each length. The result is a map from a length
    // to the number of texts with that length. An empty stream gives an empty map.
    // Example: Stream.of("a", "bb", "c", "dd", "eee").collect(countByLength())  returns {1=2, 2=2, 3=1}
    //          Stream.<String>empty().collect(countByLength())                   returns {}
    public static Collector<String, ?, Map<Integer, Long>> countByLength() {
        return Collectors.groupingBy(String::length, Collectors.counting());
    }

    // CHALLENGE 15
    // Returns a collector that splits the numbers of a stream in even and odd ones. The key true has the even numbers
    // and the key false has the odd numbers (negative numbers can be even or odd too), both in the same order they
    // have in the stream. Both keys are always in the map, even when one of the lists is empty.
    // Example: Stream.of(1, 2, 3, 4, 5).collect(evenOdd())  returns {false=[1, 3, 5], true=[2, 4]}
    //          Stream.of(-3, -2).collect(evenOdd())         returns {false=[-3], true=[-2]}
    //          Stream.<Integer>empty().collect(evenOdd())   returns {false=[], true=[]}
    public static Collector<Integer, ?, Map<Boolean, List<Integer>>> evenOdd() {
        return Collectors.partitioningBy(n -> n % 2 == 0);
    }
}
