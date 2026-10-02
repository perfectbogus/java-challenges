package dev.perfectbogus.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericsIntermediateChallenge {

    // Used by CHALLENGE 9, 10. Fully implemented — nothing to do here.
    public static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() { return first; }
        public B getSecond() { return second; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> other = (Pair<?, ?>) o;
            return Objects.equals(first, other.first) && Objects.equals(second, other.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    // CHALLENGE 1
    // Returns the first element of list, or null if list is empty.
    public static <T> T firstElement(List<T> list) {
        if (list.isEmpty()) return null;
        return list.getFirst();
    }

    // CHALLENGE 2
    // Returns the largest element of list according to its natural
    // ordering. list is guaranteed to be non-empty.
    public static <T extends Comparable<T>> T max(List<T> list) {
        return list.stream().max(Comparator.naturalOrder()).orElseThrow();
    }

    // CHALLENGE 3
    // Returns the number of elements in list that are strictly greater
    // than threshold, according to natural ordering.
    public static <T extends Comparable<T>> long countGreaterThan(List<T> list, T threshold) {
        return list.stream().filter(element -> element.compareTo(threshold) > 0).count();
    }

    // CHALLENGE 4
    // Returns the sum of the values in numbers as a double. numbers may
    // contain any mix of Number subtypes (Integer, Double, Long, etc.).
    public static double sumNumbers(List<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }

    // CHALLENGE 5
    // Appends the integers 1 through count (inclusive) to list, in
    // ascending order.
    public static void addIntegers(List<? super Integer> list, int count) {
        for (int i = 1; i <= count; i++) {
            list.add(i);
        }
    }

    // CHALLENGE 6
    // Appends every element of source to dest, in order.
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        dest.addAll(source);
    }

    // CHALLENGE 7
    // Returns a single string listing every element of list via its own
    // toString(), in order, separated by ", " and wrapped in a leading "["
    // and a trailing "]" (e.g. "[1, 2, 3]"). list may contain elements of
    // any type.
    public static String describeAll(List<?> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i)).append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String describeAllStream(List<?> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"));
    }

    // CHALLENGE 8
    // Swaps the first and last elements of list in place. list is
    // guaranteed to contain at least two elements. list may contain
    // elements of any type.
    public static void swapFirstAndLast(List<?> list) {
        swapHelper(list, 0, list.size() - 1);
    }

    private static <T> void swapHelper(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // CHALLENGE 9
    // Returns a new Pair with the components of pair swapped, i.e. the
    // first component of the result is pair's second component and vice
    // versa.
    public static <A, B> Pair<B, A> swap(Pair<A, B> pair) {
        return new Pair<>(pair.getSecond(), pair.getFirst());
    }

    // CHALLENGE 10
    // Returns a list containing, for each pair in pairs (in order), the
    // result of applying combiner to that pair's first and second
    // components.
    public static <A, B, C> List<C> combinePairs(List<Pair<A, B>> pairs, BiFunction<A, B, C> combiner) {
        List<C> results = new ArrayList<>();
        for (Pair<A, B> p : pairs) {
            results.add(combiner.apply(p.getFirst(), p.getSecond()));
        }
        return results;
    }

    public static <A, B, C> List<C> combinePairsStream(List<Pair<A, B>> pairs, BiFunction<A, B, C> combiner) {
        return pairs.stream().map(p -> combiner.apply(p.getFirst(), p.getSecond())).toList();
    }

    // CHALLENGE 11
    // Returns value, clamped to the inclusive range [min, max]: if value is
    // less than min, returns min; if value is greater than max, returns
    // max; otherwise returns value. min is guaranteed to be <= max.
    public static <T extends Number & Comparable<T>> T clamp(T value, T min, T max) {
        if (value.compareTo(min) < 0) return min;
        if (value.compareTo(max) > 0) return max;
        return value;
    }

    // CHALLENGE 12
    // Returns a new list containing, in order, every element of list that
    // is an instance of type, cast to T.
    public static <T> List<T> filterByType(List<?> list, Class<T> type) {
        return list.stream().filter(type::isInstance).map(type::cast).toList();
    }

    // CHALLENGE 13
    // Returns true if list is sorted in non-decreasing order according to
    // natural ordering (an empty list or a single-element list counts as
    // sorted).
    public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Merges a and b, which are each already sorted in ascending order
    // according to natural ordering, into a single new list sorted in
    // ascending order containing all elements of both (duplicates kept).
    public static <T extends Comparable<T>> List<T> mergeSorted(List<T> a, List<T> b) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Returns the element of list whose key (as produced by keyExtractor)
    // is the largest according to natural ordering. list is guaranteed to
    // be non-empty. If multiple elements tie for the largest key, returns
    // the first one encountered.
    public static <T, K extends Comparable<K>> T maxByKey(List<T> list, Function<T, K> keyExtractor) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
