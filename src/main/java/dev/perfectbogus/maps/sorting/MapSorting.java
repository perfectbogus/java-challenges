package dev.perfectbogus.maps.sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapSorting {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(Map.of(
                "banana", 2,
                "apple", 1,
                "cherry", 3,
                "date", 4
        ));

        System.out.println("Sorting By Key");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));

        Comparator<Map.Entry<String, Integer>> entryComparator = Map.Entry.comparingByKey();
        Comparator<Map.Entry<String, Integer>> reversed = Map.Entry.<String, Integer>comparingByKey().reversed();

        System.out.println("Sorting By Key Descending");
        map.entrySet().stream()
                .sorted(reversed)
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));

        System.out.println("Sorting By Length Key Ascending:");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(
                        Comparator.comparingInt(String::length)
                ))
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));

        System.out.println("Sorting By Length Key Descending:");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(
                        Comparator.comparingInt(String::length).reversed()
                ))
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));

        System.out.println("Sorting By Length then By Alphabetically");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(
                        Comparator.comparingInt(String::length)
                                .thenComparing(Comparator.naturalOrder())
                ))
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));

        System.out.println("Sorting key insensitive");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(
                        String.CASE_INSENSITIVE_ORDER
                ))
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));


        System.out.println("---Comparing By Values---");
        Map<String, Integer> scores = new HashMap<>(Map.of(
                "Alice", 85,
                "Bob", 92,
                "Charlie", 78,
                "Diana", 92
        ));

        System.out.println("Sorting By Value ascending");
        scores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));

        System.out.println("Sorting by Value Descending");
        scores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.printf("%-10s %d%n", e.getKey(), e.getValue()));
    }
}
