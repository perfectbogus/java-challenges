package dev.perfectbogus.sorting;

import java.util.*;

public class SortingIntermediateChallenge {

    // Simple data class used by several challenges below.
    public static class Person {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    // CHALLENGE 1
    // Returns a new list containing the given words sorted first by
    // length (shortest first), and words of equal length sorted
    // alphabetically.
    public static List<String> sortByLengthThenAlphabetically(List<String> words) {
        return words.stream()
                .sorted(
                        Comparator.comparingInt(String::length)
                                .thenComparing(Comparator.naturalOrder())
                )
                .toList();
    }

    // CHALLENGE 2
    // Returns a new list containing the given people sorted by age,
    // oldest first.
    public static List<Person> sortByAgeDescending(List<Person> people) {
        return people.stream().sorted(Comparator.comparingInt(Person::getAge).reversed()).toList();
    }

    // CHALLENGE 3
    // Returns a new list containing the given words sorted alphabetically,
    // treating uppercase and lowercase letters as equivalent.
    public static List<String> sortIgnoringCase(List<String> words) {
        return words.stream().sorted(String::compareToIgnoreCase).toList();
    }

    // CHALLENGE 4
    // Returns a new list containing the given words sorted alphabetically,
    // with any null elements placed at the beginning.
    public static List<String> sortWithNullsFirst(List<String> words) {
        return words.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).toList();
    }

    // CHALLENGE 5
    // Sorts numbers in place, in ascending order.
    public static void sortInPlaceAscending(int[] numbers) {
        Arrays.sort(numbers);
    }

    // CHALLENGE 6
    // Sorts numbers in place, in descending order.
    public static void sortDescendingInPlace(Integer[] numbers) {
        Arrays.sort(numbers, Comparator.reverseOrder());
    }

    // CHALLENGE 7
    // Returns the entries of map as a list, sorted by value in ascending
    // order.
    public static List<Map.Entry<String, Integer>> sortMapEntriesByValue(Map<String, Integer> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
    }

    // CHALLENGE 8
    // sortedList is sorted in ascending order. Returns the index of
    // target within it, or a negative value if target is not present
    // (following the same contract as Collections.binarySearch).
    public static int findIndexBinarySearch(List<Integer> sortedList, int target) {
        int left = 0;
        int right = sortedList.size() - 1;
        int mid = sortedList.size() / 2;
        while (left <= right) {
            if (sortedList.get(mid) == target) {
                return mid;
            }

            if (target > sortedList.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = left + (right - left)/ 2;
        }
        return -1;
    }

    // CHALLENGE 9
    // Returns a new list containing the given people sorted by name
    // alphabetically; people with the same name are sorted by age,
    // oldest first.
    public static List<Person> sortByNameThenAgeDescending(List<Person> people) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a new list containing the given people sorted by age,
    // youngest first. Among people who share the same age, their
    // relative order from the original list must be preserved.
    public static List<Person> sortPreservingInsertionOrderForTies(List<Person> people) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}