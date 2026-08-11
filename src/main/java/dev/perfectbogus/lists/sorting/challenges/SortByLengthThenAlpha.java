package dev.perfectbogus.lists.sorting.challenges;

import java.util.Comparator;
import java.util.List;

public class SortByLengthThenAlpha {

    public static List<String> sort(List<String> words) {
        if (words == null) throw new IllegalArgumentException("words is null");
        // TODO
        // comparingInt(length) + thenComparing(natural)
        words.sort(
                Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder())
        );

        return words;
    }

    public static List<String> sort2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("word is null");

        words.sort((a, b) -> {
            if (a.length() != b.length()) return Integer.compare(a.length(), b.length());
            return a.compareTo(b);
        });

        return words;
    }
}
