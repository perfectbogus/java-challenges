package dev.perfectbogus.lists.sorting.challenges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortNullsLast {

    public static List<String> sort(List<String> words) {
        if (words == null) throw new IllegalArgumentException("words is null");
        // TODO
        // Comparator.nullsLast(naturalOrder())
        words.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        return words;
    }
}