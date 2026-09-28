package dev.perfectbogus.sorting;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortingIntermediateChallenge2 {

    // Used by CHALLENGE 1 through CHALLENGE 4, CHALLENGE 14.
    public static class Person implements Comparable<Person> {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        // CHALLENGE 1
        // Orders Person naturally by age, ascending.
        @Override
        public int compareTo(Person other) {
            return Integer.compare(age, other.getAge());
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    // A plain value type with no natural ordering of its own — used by
    // CHALLENGE 5. Fully implemented — nothing to do here.
    public static class Player {
        private final String name;
        private final int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }

    // A plain value type with no natural ordering of its own — used by
    // CHALLENGE 10 and CHALLENGE 15. Fully implemented — nothing to do
    // here.
    public static class Product {
        private final String name;
        private final String category;
        private final int price;

        public Product(String name, String category, int price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public int getPrice() {
            return price;
        }
    }

    // A plain value type whose priority field is NOT naturally ordered
    // the way you'd want ("HIGH" < "LOW" < "MEDIUM" alphabetically, which
    // is not priority order) — used by CHALLENGE 13. Fully implemented —
    // nothing to do here.
    public static class Task {
        private final String name;
        private final String priority; // one of "LOW", "MEDIUM", "HIGH"

        public Task(String name, String priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public String getPriority() {
            return priority;
        }
    }

    // CHALLENGE 1 is Person.compareTo(), declared above.

    // CHALLENGE 2
    // Returns a new list containing people sorted by name, ascending.
    // Does not modify people.
    public static List<Person> sortByNameAscending(List<Person> people) {
        Comparator<Person> byAlpha = Comparator.comparing(Person::getName);
        return people.stream().sorted(byAlpha).toList();
    }

    // CHALLENGE 3
    // Returns a new list containing people sorted by age, descending.
    // Does not modify people.
    public static List<Person> sortByAgeDescending(List<Person> people) {
        Comparator<Person> byAgeDesc = Comparator.comparingInt(Person::getAge).reversed();
        return people.stream().sorted(byAgeDesc).collect(Collectors.toList());
    }

    // CHALLENGE 4
    // Returns a new list containing people sorted primarily by age
    // (youngest first), and by name (alphabetically) among people of
    // equal age. Does not modify people.
    public static List<Person> sortByAgeThenName(List<Person> people) {
        Comparator<Person> byAge = Comparator.comparingInt(Person::getAge);
        Comparator<Person> byAlpha = Comparator.comparing(Person::getName);
        return people.stream().sorted(byAge.thenComparing(byAlpha)).collect(Collectors.toList());
    }

    // CHALLENGE 5
    // Returns a new list containing players sorted primarily by score,
    // descending (highest first), and by name, ascending, among players
    // with equal scores. Does not modify players.
    public static List<Player> sortByScoreDescThenNameAsc(List<Player> players) {
        Comparator<Player> byScoreDesc = Comparator.comparingInt(Player::getScore).reversed();
        Comparator<Player> byName = Comparator.comparing(Player::getName);
        return players.stream().sorted(byScoreDesc.thenComparing(byName)).collect(Collectors.toList());
    }

    // CHALLENGE 6
    // Returns a new list containing words sorted naturally, ascending,
    // with every null entry moved to the end (after all non-null
    // entries). Does not modify words.
    public static List<String> sortWithNullsLast(List<String> words) {
        Comparator<String> byNullEnd = Comparator.nullsLast(Comparator.naturalOrder());
        return words.stream().sorted(byNullEnd).collect(Collectors.toList());
    }

    // CHALLENGE 7
    // Returns a new list containing words sorted alphabetically,
    // ignoring case. When two words are equal ignoring case (e.g. "Bob"
    // and "bob"), their relative order from the original list must be
    // preserved. Does not modify words.
    public static List<String> sortCaseInsensitiveStable(List<String> words) {
        Comparator<String> byAlphaIgnoringCase = String::compareToIgnoreCase;
        return words.stream().sorted(byAlphaIgnoringCase).collect(Collectors.toList());
    }

    // CHALLENGE 8
    // Returns a new list containing nums sorted by their distance from
    // target (ascending — closest first). Ties (equal distance) are
    // broken by the values themselves, ascending. Does not modify nums.
    public static List<Integer> sortByDistanceFromTarget(List<Integer> nums, int target) {
        Comparator<Integer> byDistanceFromTarget = Comparator.comparingInt(a -> Math.abs(target - a));
        Comparator<Integer> byItself = Comparator.comparingInt(a -> a);
        return nums.stream().sorted(byDistanceFromTarget.thenComparing(byItself)).collect(Collectors.toList());
    }

    // CHALLENGE 9
    // Returns a new list containing intervals (each a 2-element array of
    // [start, end]) sorted by end time (index 1), ascending. Ties are
    // broken by start time (index 0), ascending. Does not modify
    // intervals or its elements.
    public static List<int[]> sortIntervalsByEndThenStart(List<int[]> intervals) {
        Comparator<int[]> byEnd = Comparator.comparingInt(a -> a[1]);
        Comparator<int[]> byStart = Comparator.comparingInt(a -> a[0]);
        return intervals.stream().sorted(byEnd.thenComparing(byStart)).collect(Collectors.toList());
    }

    // CHALLENGE 10
    // Returns a new list containing products sorted primarily by
    // category (alphabetically), and by price, descending, among
    // products in the same category. Does not modify products.
    public static List<Product> sortByCategoryThenPriceDesc(List<Product> products) {
        Comparator<Product> byCategory = Comparator.comparing(Product::getCategory);
        Comparator<Product> byPriceDesc = Comparator.comparing(Product::getPrice, Comparator.reverseOrder());
        return products.stream().sorted(byCategory.thenComparing(byPriceDesc)).collect(Collectors.toList());
    }

    // CHALLENGE 11
    // Builds a max-heap (a PriorityQueue ordered so the largest element
    // comes out first) from nums, then drains it completely, returning
    // the values in the order they were polled off. The result is nums
    // in descending order, but it must be produced by inserting into and
    // polling from a PriorityQueue with an appropriate Comparator — not
    // by sorting a list directly.
    public static List<Integer> maxHeapDrainOrder(List<Integer> nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(nums);
        List<Integer> drain = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            drain.add(maxHeap.poll());
        }
        return drain;
    }

    // CHALLENGE 12
    // Builds and returns a TreeMap<String, Integer> whose ordering (and
    // key equality) is case-insensitive: words that differ only in case
    // (e.g. "Bob" and "bob") count as the same key. For each word in
    // words, increments that key's count in the map (inserting it with
    // count 1 the first time it's seen). The key stored for each group
    // keeps whichever casing was encountered first.
    public static Map<String, Integer> countWordsCaseInsensitive(List<String> words) {
        return words.stream().collect(Collectors.toMap(
                Function.identity(),
                (s -> 1),
                Integer::sum,
                () -> new TreeMap<>(String::compareToIgnoreCase)
        ));
    }

    // CHALLENGE 13
    // Returns a new list containing tasks sorted by priority, with
    // "HIGH" first, then "MEDIUM", then "LOW" (note: this is not
    // alphabetical order). Ties (equal priority) are broken by name,
    // ascending. Does not modify tasks.
    public static List<Task> sortByPriorityDescending(List<Task> tasks) {
        Map<String, Integer> priorityMap = Map.of("HIGH", 0 ,"MEDIUM", 1, "LOW", 2);
        Comparator<Task> byPriority = Comparator.comparing(t -> priorityMap.get(t.getPriority()));
        Comparator<Task> byName = Comparator.comparing(Task::getName);
        return tasks.stream().sorted(byPriority.thenComparing(byName)).collect(Collectors.toList());
    }

    // CHALLENGE 14
    // Returns the Person in people with the highest age, found using a
    // Comparator rather than by sorting the whole list.
    public static Person mostExperiencedPerson(List<Person> people) {
        Comparator<Person> byAge = Comparator.comparingInt(Person::getAge);
        return people.stream().max(byAge).orElseThrow();
    }

    // CHALLENGE 15
    // Returns a new list containing products sorted by category
    // (ascending), then by price (ascending) among products in the same
    // category, then by name (ascending) among products with the same
    // category and price. Does not modify products.
    public static List<Product> sortByCategoryThenPriceThenName(List<Product> products) {
        Comparator<Product> byCategory = Comparator.comparing(Product::getCategory);
        Comparator<Product> byPrice = Comparator.comparing(Product::getPrice);
        Comparator<Product> byName = Comparator.comparing(Product::getName);
        return products.stream()
                .sorted(byCategory.thenComparing(byPrice).thenComparing(byName))
                .collect(Collectors.toList());
    }
}