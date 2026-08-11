package dev.perfectbogus.lists.sorting.challenges;

import java.util.Comparator;
import java.util.List;

public class SortCaseInsensitive {

    public static List<String> sort(List<String> words) {
        if (words == null) throw new IllegalArgumentException("words is null");
        // TODO
        // compareToIgnoreCase + thenComparingInt(length reversed)
        Comparator<String> comp = String::compareToIgnoreCase;
        Comparator<String> lengthComp = Comparator.comparing(String::length).reversed();

        words.sort(comp.thenComparing(lengthComp));

        return words;
    }

    public static List<String> sort2(List<String> words) {
        if (words == null) throw new IllegalArgumentException("words is null");

        words.sort(((Comparator<String>) String::compareToIgnoreCase)
                .thenComparing(Comparator.comparingInt(String::length).reversed()));

        return words;
    }
}
