package dev.perfectbogus.lists.sorting.challenges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortMapEntries {

    public static List<Map.Entry<String, Integer>> sort(Map<String, Integer> map) {
        if (map == null) throw new IllegalArgumentException("map is null");
        // TODO
        // entrySet().stream()
        // sorted by value descending + key ascending
        // collect to list
        Comparator<Map.Entry<String, Integer>> cValue = Map.Entry.<String, Integer>comparingByValue().reversed();
        Comparator<Map.Entry<String, Integer>> cKey = Map.Entry.comparingByKey();
        return map.entrySet().stream().sorted(cValue.thenComparing(cKey)).collect(Collectors.toList());
    }

    public static List<Map.Entry<String, Integer>> sort2(Map<String, Integer> map) {
        // TODO
        // entrySet().stream()
        // sorted by value descending + key ascending
        // collect to list
        return map.entrySet().stream().sorted(
                Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey())
        ).collect(Collectors.toList());
    }

}