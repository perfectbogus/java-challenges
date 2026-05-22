package dev.perfectbogus.functional.mini.challenges;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Challenges {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 15, 7, 22, 1, 18, 9);
        // Challenge 1
        final int toCompare = 10;
        long result = filterAndCount(numbers, toCompare);
        System.out.println(result);

        // Challenge 2
        List<String> unsortedNames = List.of("charlie", "alice", "bob");
        List<String> resultC2 = transformAList(unsortedNames);
        System.out.println(resultC2);

        // Challenge 3
        List<Integer> natural = List.of(1, 2, 3, 4, 5, 6);
        long resultC3 = sumWithReduce(natural);
        System.out.println(resultC3);

        // Challenge 4
        List<String> sentences = List.of(
                "Hello World",
                "Java is awesome",
                "FlatMap is useful",
                "Hello Java with FlatMap"
        );
        List<String> words = flatMap(sentences);
        System.out.println(words);

        // Challenge 5
        List<String> fruits = List.of("apple", "banana", "avocado", "blueberry", "cherry");
        Map<Character, List<String>> resultC5 = groupingByFirstLetter(fruits);
        System.out.println(resultC5);

        // Challenge 6
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob",   45);
        scores.put("Carol", 72);
        scores.put("David", 55);
        scores.put("Eve", 90);
        Map<String, Character> students = studentsWhoPassed(scores);
        System.out.println(students);

        // Challenge 7
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> partitioned = partitioning(list);
        System.out.println(partitioned);

        // Challenge 8
        String joined = joiningNames(unsortedNames);
        System.out.println(joined);

        // Challenge 9
        List<String> wordsChain = List.of("hi", "hello", "world!", "java");
        String chained = chain(wordsChain);
        System.out.println(chained);

        // Challenge 10
        List<String> frequentWords = List.of("apple", "banana", "apple", "cherry", "banana", "banana");
        Map<String, Long> resultC10 = frequencyMap(frequentWords);
        System.out.println(resultC10);

    }

    static Map<String, Long> frequencyMap(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    static String chain(List<String> words) {
        return words.stream()
                .filter(w -> w.length() > 5)
                .findFirst()
                .map(String::toUpperCase)
                .orElse("NONE");
    }

    static String joiningNames(List<String> names) {
        return names.stream().collect(Collectors.joining(", ", "Members: [", "]"));
    }

    static Map<Boolean, List<Integer>> partitioning(List<Integer> numbers) {
        return numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }


    static Map<String, Character> studentsWhoPassed(Map<String, Integer> scores) {
        return scores.entrySet().stream()
                .filter(e -> e.getValue() >= 60)
                .map(e -> Map.entry(e.getKey(), intToLetter(e.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static char intToLetter(int number) {
        if (number >= 90) return 'A';
        if (number >= 80) return 'B';
        if (number >= 70) return 'C';
        if (number >= 60) return 'D';
        return 'F';
    }


    static Map<Character, List<String>> groupingByFirstLetter(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
    }

    // Need more example to catch this
    static List<String> flatMap(List<String> sentences) {
        return sentences.stream()
                .flatMap(s -> Arrays.stream(s.trim().split("\\s+")))
                .map(String::toLowerCase)
                .distinct().toList();
    }


    static long sumWithReduce(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).reduce(0, Integer::sum);
    }

    static List<String> transformAList(List<String> names) {
        return names.stream().map(String::toUpperCase).sorted(Comparator.naturalOrder()).toList();
    }

    static long filterAndCount(List<Integer> numbers, int toCompare) {
        return numbers.stream().filter(n -> n > toCompare).count();
    }
}
