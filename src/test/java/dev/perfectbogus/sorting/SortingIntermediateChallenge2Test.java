package dev.perfectbogus.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingIntermediateChallenge2Test {

    // ==========================================================
    // CHALLENGE 1: Person.compareTo
    // ==========================================================
    @Nested
    class PersonCompareToTests {

        @Test
        void testSortsByAgeAscending() {
            List<SortingIntermediateChallenge2.Person> people = new ArrayList<>(List.of(
                    new SortingIntermediateChallenge2.Person("Bob", 30),
                    new SortingIntermediateChallenge2.Person("Alice", 25),
                    new SortingIntermediateChallenge2.Person("Eve", 40)
            ));
            Collections.sort(people);
            assertEquals(List.of(25, 30, 40),
                    people.stream().map(SortingIntermediateChallenge2.Person::getAge).toList());
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortByNameAscending
    // ==========================================================
    @Nested
    class SortByNameAscendingTests {

        @Test
        void testAscendingByName() {
            List<SortingIntermediateChallenge2.Person> people = List.of(
                    new SortingIntermediateChallenge2.Person("Charlie", 22),
                    new SortingIntermediateChallenge2.Person("Alice", 25),
                    new SortingIntermediateChallenge2.Person("Bob", 19)
            );
            List<String> names = SortingIntermediateChallenge2.sortByNameAscending(people)
                    .stream().map(SortingIntermediateChallenge2.Person::getName).toList();
            assertEquals(List.of("Alice", "Bob", "Charlie"), names);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortByAgeDescending
    // ==========================================================
    @Nested
    class SortByAgeDescendingTests {

        @Test
        void testDescendingByAge() {
            List<SortingIntermediateChallenge2.Person> people = List.of(
                    new SortingIntermediateChallenge2.Person("A", 20),
                    new SortingIntermediateChallenge2.Person("B", 35),
                    new SortingIntermediateChallenge2.Person("C", 28)
            );
            List<Integer> ages = SortingIntermediateChallenge2.sortByAgeDescending(people)
                    .stream().map(SortingIntermediateChallenge2.Person::getAge).toList();
            assertEquals(List.of(35, 28, 20), ages);
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortByAgeThenName
    // ==========================================================
    @Nested
    class SortByAgeThenNameTests {

        @Test
        void testAgeThenName() {
            List<SortingIntermediateChallenge2.Person> people = List.of(
                    new SortingIntermediateChallenge2.Person("Bob", 30),
                    new SortingIntermediateChallenge2.Person("Alice", 25),
                    new SortingIntermediateChallenge2.Person("Carol", 25)
            );
            List<String> names = SortingIntermediateChallenge2.sortByAgeThenName(people)
                    .stream().map(SortingIntermediateChallenge2.Person::getName).toList();
            assertEquals(List.of("Alice", "Carol", "Bob"), names);
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortByScoreDescThenNameAsc
    // ==========================================================
    @Nested
    class SortByScoreDescThenNameAscTests {

        @Test
        void testScoreDescendingThenNameAscending() {
            List<SortingIntermediateChallenge2.Player> players = List.of(
                    new SortingIntermediateChallenge2.Player("Zoe", 50),
                    new SortingIntermediateChallenge2.Player("Amy", 80),
                    new SortingIntermediateChallenge2.Player("Bob", 80),
                    new SortingIntermediateChallenge2.Player("Cid", 50)
            );
            List<String> names = SortingIntermediateChallenge2.sortByScoreDescThenNameAsc(players)
                    .stream().map(SortingIntermediateChallenge2.Player::getName).toList();
            assertEquals(List.of("Amy", "Bob", "Cid", "Zoe"), names);
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortWithNullsLast
    // ==========================================================
    @Nested
    class SortWithNullsLastTests {

        @Test
        void testNullsPushedToEnd() {
            List<String> words = new ArrayList<>(Arrays.asList("banana", null, "apple", null, "cherry"));
            List<String> result = SortingIntermediateChallenge2.sortWithNullsLast(words);
            assertEquals(Arrays.asList("apple", "banana", "cherry", null, null), result);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortCaseInsensitiveStable
    // ==========================================================
    @Nested
    class SortCaseInsensitiveStableTests {

        @Test
        void testCaseInsensitiveOrderPreservesTiesOrder() {
            List<String> words = List.of("bob", "Alice", "BOB", "alice");
            assertEquals(
                    List.of("Alice", "alice", "bob", "BOB"),
                    SortingIntermediateChallenge2.sortCaseInsensitiveStable(words)
            );
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortByDistanceFromTarget
    // ==========================================================
    @Nested
    class SortByDistanceFromTargetTests {

        @Test
        void testClosestFirst() {
            List<Integer> nums = List.of(10, 1, 8, 3, 15);
            assertEquals(
                    List.of(8, 10, 3, 1, 15),
                    SortingIntermediateChallenge2.sortByDistanceFromTarget(nums, 7)
            );
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortIntervalsByEndThenStart
    // ==========================================================
    @Nested
    class SortIntervalsByEndThenStartTests {

        @Test
        void testEndThenStart() {
            List<int[]> intervals = List.of(
                    new int[] {1, 10}, new int[] {2, 5}, new int[] {6, 8}, new int[] {0, 5}
            );
            List<int[]> result = SortingIntermediateChallenge2.sortIntervalsByEndThenStart(intervals);
            int[][] expected = {{0, 5}, {2, 5}, {6, 8}, {1, 10}};
            assertEquals(expected.length, result.size());
            for (int i = 0; i < expected.length; i++) {
                assertArrayEquals(expected[i], result.get(i));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 10: sortByCategoryThenPriceDesc
    // ==========================================================
    @Nested
    class SortByCategoryThenPriceDescTests {

        @Test
        void testCategoryThenPriceDescending() {
            List<SortingIntermediateChallenge2.Product> products = List.of(
                    new SortingIntermediateChallenge2.Product("Mouse", "Electronics", 25),
                    new SortingIntermediateChallenge2.Product("Pen", "Office", 2),
                    new SortingIntermediateChallenge2.Product("Keyboard", "Electronics", 45),
                    new SortingIntermediateChallenge2.Product("Notebook", "Office", 5)
            );
            List<String> names = SortingIntermediateChallenge2.sortByCategoryThenPriceDesc(products)
                    .stream().map(SortingIntermediateChallenge2.Product::getName).toList();
            assertEquals(List.of("Keyboard", "Mouse", "Notebook", "Pen"), names);
        }
    }

    // ==========================================================
    // CHALLENGE 11: maxHeapDrainOrder
    // ==========================================================
    @Nested
    class MaxHeapDrainOrderTests {

        @Test
        void testDescendingDrainOrder() {
            assertEquals(
                    List.of(9, 7, 5, 3, 1),
                    SortingIntermediateChallenge2.maxHeapDrainOrder(List.of(5, 1, 9, 3, 7))
            );
        }
    }

    // ==========================================================
    // CHALLENGE 12: countWordsCaseInsensitive
    // ==========================================================
    @Nested
    class CountWordsCaseInsensitiveTests {

        @Test
        void testCaseInsensitiveCountingAndKeyOrder() {
            List<String> words = List.of("Bob", "alice", "bob", "Bob", "Alice");
            Map<String, Integer> result = SortingIntermediateChallenge2.countWordsCaseInsensitive(words);

            assertEquals(2, result.size());
            assertEquals(3, result.get("BOB"));
            assertEquals(2, result.get("aLiCe"));
            assertEquals(List.of("alice", "Bob"), new ArrayList<>(result.keySet()));
        }
    }

    // ==========================================================
    // CHALLENGE 13: sortByPriorityDescending
    // ==========================================================
    @Nested
    class SortByPriorityDescendingTests {

        @Test
        void testHighMediumLowOrder() {
            List<SortingIntermediateChallenge2.Task> tasks = List.of(
                    new SortingIntermediateChallenge2.Task("Fix bug", "LOW"),
                    new SortingIntermediateChallenge2.Task("Deploy", "HIGH"),
                    new SortingIntermediateChallenge2.Task("Review PR", "MEDIUM"),
                    new SortingIntermediateChallenge2.Task("Write docs", "HIGH")
            );
            List<String> names = SortingIntermediateChallenge2.sortByPriorityDescending(tasks)
                    .stream().map(SortingIntermediateChallenge2.Task::getName).toList();
            assertEquals(List.of("Deploy", "Write docs", "Review PR", "Fix bug"), names);
        }
    }

    // ==========================================================
    // CHALLENGE 14: mostExperiencedPerson
    // ==========================================================
    @Nested
    class MostExperiencedPersonTests {

        @Test
        void testReturnsOldest() {
            List<SortingIntermediateChallenge2.Person> people = List.of(
                    new SortingIntermediateChallenge2.Person("A", 20),
                    new SortingIntermediateChallenge2.Person("B", 45),
                    new SortingIntermediateChallenge2.Person("C", 33)
            );
            assertEquals("B", SortingIntermediateChallenge2.mostExperiencedPerson(people).getName());
        }
    }

    // ==========================================================
    // CHALLENGE 15: sortByCategoryThenPriceThenName
    // ==========================================================
    @Nested
    class SortByCategoryThenPriceThenNameTests {

        @Test
        void testThreeLevelSort() {
            List<SortingIntermediateChallenge2.Product> products = List.of(
                    new SortingIntermediateChallenge2.Product("Mouse", "Electronics", 25),
                    new SortingIntermediateChallenge2.Product("Pen", "Office", 2),
                    new SortingIntermediateChallenge2.Product("Keyboard", "Electronics", 25),
                    new SortingIntermediateChallenge2.Product("Notebook", "Office", 5)
            );
            List<String> names = SortingIntermediateChallenge2.sortByCategoryThenPriceThenName(products)
                    .stream().map(SortingIntermediateChallenge2.Product::getName).toList();
            assertEquals(List.of("Keyboard", "Mouse", "Pen", "Notebook"), names);
        }
    }
}