package dev.perfectbogus.functional;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Usage {

    public static void main(String[] args) {
        // Function<T, R> - takes T, returns R
        Function<String, Integer> length = String::length;
        Integer lengthWord = length.apply("hello");
        System.out.println("hello length: " + lengthWord);

        Function<Integer, Double> toDouble = Integer::doubleValue;
        System.out.println("Transform a Integer to Double using a Function:" + toDouble.apply(10));


        // Predicate<String> - takes T, returns boolean
        Predicate<String> isEmptyP = String::isEmpty;
        System.out.println(isEmptyP.test(""));
        System.out.println(isEmptyP.test("not empty"));

        Predicate<Integer> bigger = (a) -> a > 10;
        System.out.println("Bigger from Predicate: 15 > 10: " + bigger.test(15));
        System.out.println("Bigger from Predicate: 5 > 10: " + bigger.test(5));

        // Consumer<T> - takes T, return nothing
        Consumer<String> print = System.out::println;
        print.accept("print this from the consumer");

        // Supplier<T> - takes nothing, returns T
        Supplier<List<String>> newList = ArrayList::new;
        List<String> newListFromSupplier = newList.get();

        // BiFunction<T, U, R> - takes T and U, returns R
        BiFunction<String, Integer, String> repeat = String::repeat;
        String repeatStr = repeat.apply("ab", 3);
        System.out.println("repeat from BiFunction: " + repeatStr);

        // Stream Pipeline
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9 ,10);
        int result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println("Stream Pipeline: " + result);

        // Key Stream Operations
        List<String> names = List.of("Alice", "Bob", "Charlie", "Diego", "Ernest");

        List<String> filteredNames = names.stream().filter(n -> n.startsWith("A")).toList();
        System.out.println("Filtered Names Starts With A: " + filteredNames);

        List<String> mappedNames = names.stream().map(String::toLowerCase).toList();
        System.out.println("Mapped Names: " + mappedNames);

        // Sorted - Natural
        List<String> sortedNames = names.stream().sorted().toList();
        System.out.println("Ordered Names: " + sortedNames);

        // Sorted - Custom
        List<String> sortedCustomNames = names.stream().sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println("Sorted By Length: " + sortedCustomNames);

        // Distinct
        List<Integer> disctinctList = Stream.of(1, 2, 2, 2, 3, 3, 3).distinct().toList();
        System.out.println("Distinct Numbers: " + disctinctList);

        // limit / skip
        List<String> skippedNames = names.stream().skip(2).limit(2).toList();
        System.out.println("Skipped and Limited Names: " + skippedNames);

        // flatmap - flatten nested lists
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));
        System.out.println("Nested Lists:" + nested);
        List<Integer> flatten = nested.stream().flatMap(Collection::stream).toList();
        System.out.println("Flatten :" + flatten);

        // Terminal Operations
        // collect
        List<String> list = names.stream().toList();// stream().collect(Collectors.toList());
        Set<String> set = names.stream().collect(Collectors.toSet());

        // count
        long countNames = names.stream().count();
        System.out.println("Count names: " + countNames );

        // reduce - combine all elements
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println("Summed numbers: " + sum);

        Integer sumIdentity = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Summed numbers with Identity: " + sumIdentity);

        // findFirst / FindAny
        boolean any = numbers.stream().anyMatch(n -> n > 5);
        boolean all = numbers.stream().allMatch(n -> n > 0);
        boolean none = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("find first / find any - any: " + any + " all: " + all + " none: " + none);

        // min / max
        Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
        Optional<Integer> min = numbers.stream().min(Comparator.naturalOrder());
        System.out.println("Optional Max: " + max + " Optional Min: " + min);

        // forEach
        numbers.forEach(System.out::print);
        System.out.println();

        // 6: Collectors
        Map<Integer, List<String>> byLength = names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Collector byLength: " + byLength);

        // Counting
        Map<Integer, Long> countByLength = names.stream().collect(Collectors.groupingBy(
                String::length, Collectors.counting()
        ));
        System.out.println("Counting countByLength: " + countByLength);

        // Joining - concatenate strings
        String joined = names.stream().collect(Collectors.joining(", ", "{", "}"));
        System.out.println("Joined Names by commas and '{' and '}': " + joined);

        // toMap
        Map<String, Integer> nameLengths = names.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("Mapped to name and its length: " + nameLengths );

        // partitioningBy - split into true/false
        Map<Boolean, List<String>> partitioned = names.stream().collect(Collectors.partitioningBy(n -> n.startsWith("A")));
        System.out.println("Partitioned Map: " + partitioned);

        // 7 Optional - No More NullPointerException
        Optional<String> name = Optional.of("Alice");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println("Name is present? : " + name.isPresent() + " name: " + name.get());
        System.out.println("Is empty or? " + empty.orElse("default"));
        System.out.println("Map: " + name.map(String::length));
        System.out.println("Filtered: " + name.filter(n -> n.startsWith("A")));
        name.ifPresent(System.out::println);

        // 8 Method Reference
        // Static method
        Function<String, Integer> parse = Integer::parseInt;
        // Instance method on type
        Function<String, String> upper = String::toUpperCase;
        // Instance method on specific object
        String prefix = "Hello ";
        Function<String, String> greet = prefix::concat;
        // Constructor
        Supplier<List<String>> create = ArrayList::new;
    }
}
