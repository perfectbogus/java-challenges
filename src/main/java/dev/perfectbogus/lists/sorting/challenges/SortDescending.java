package dev.perfectbogus.lists.sorting.challenges;

import java.util.List;

public class SortDescending {

    public static List<Integer> sort(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("numbers is null");

        // TODO — one line with Comparator!
        numbers.sort((a, b) -> Integer.compare(b, a));

        return List.copyOf(numbers);
    }

}
