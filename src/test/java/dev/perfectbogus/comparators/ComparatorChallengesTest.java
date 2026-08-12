package dev.perfectbogus.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Sort 2D Matrix by Second Column ASC
    //               then First Column DESC for ties
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            int[][] result = ComparatorChallenges.challenge1(
                    new int[][]{{3,2},{1,4},{2,2},{5,1},{4,3}});

            assertArrayEquals(new int[]{5, 1}, result[0]); // col1=1 smallest
            assertArrayEquals(new int[]{3, 2}, result[1]); // col1=2, col0=3>2
            assertArrayEquals(new int[]{2, 2}, result[2]); // col1=2, col0=2
            assertArrayEquals(new int[]{4, 3}, result[3]); // col1=3
            assertArrayEquals(new int[]{1, 4}, result[4]); // col1=4 largest
        }

        @Test
        void noTies() {
            int[][] result = ComparatorChallenges.challenge1(
                    new int[][]{{1,5},{2,3},{3,1},{4,4}});

            assertArrayEquals(new int[]{3, 1}, result[0]);
            assertArrayEquals(new int[]{2, 3}, result[1]);
            assertArrayEquals(new int[]{4, 4}, result[2]);
            assertArrayEquals(new int[]{1, 5}, result[3]);
        }

        @Test
        void allSameSecondColumn() {
            int[][] result = ComparatorChallenges.challenge1(
                    new int[][]{{1,5},{3,5},{2,5}});

            // Same col[1]=5 → sort col[0] DESC: 3,2,1
            assertArrayEquals(new int[]{3, 5}, result[0]);
            assertArrayEquals(new int[]{2, 5}, result[1]);
            assertArrayEquals(new int[]{1, 5}, result[2]);
        }

        @Test
        void singleRow() {
            int[][] result = ComparatorChallenges.challenge1(
                    new int[][]{{1, 2}});
            assertArrayEquals(new int[]{1, 2}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Sort Map by Key Length ASC then Value DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat",      3,
                    "elephant", 8,
                    "dog",      5,
                    "ant",      1,
                    "bear",     4
            ));
            List<Map.Entry<String, Integer>> result =
                    ComparatorChallenges.challenge2(map);

            // Length 3: cat(3), ant(1), dog(5) → value DESC: dog(5),cat(3),ant(1)
            assertEquals("dog",      result.get(0).getKey()); // len=3, val=5
            assertEquals("cat",      result.get(1).getKey()); // len=3, val=3
            assertEquals("ant",      result.get(2).getKey()); // len=3, val=1
            assertEquals("bear",     result.get(3).getKey()); // len=4
            assertEquals("elephant", result.get(4).getKey()); // len=8
        }

        @Test
        void allSameLength() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat", 1,
                    "dog", 3,
                    "ant", 2
            ));
            List<Map.Entry<String, Integer>> result =
                    ComparatorChallenges.challenge2(map);

            // All length 3 → value DESC: dog(3), ant(2), cat(1)
            assertEquals("dog", result.get(0).getKey());
            assertEquals("ant", result.get(1).getKey());
            assertEquals("cat", result.get(2).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 5));
            assertEquals(1, ComparatorChallenges.challenge2(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(ComparatorChallenges.challenge2(
                    new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Sort Students by Grade Priority Map
    //               then Name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        private List<ComparatorChallenges.Student> students;

        @BeforeEach
        void setUp() {
            students = new ArrayList<>(List.of(
                    new ComparatorChallenges.Student("Alice",  'B'),
                    new ComparatorChallenges.Student("Bob",    'A'),
                    new ComparatorChallenges.Student("Carol",  'B'),
                    new ComparatorChallenges.Student("Diana",  'C'),
                    new ComparatorChallenges.Student("Eve",    'A')
            ));
        }

        @Test
        void gradeOrder() {
            List<ComparatorChallenges.Student> result =
                    ComparatorChallenges.challenge3(students);

            assertEquals('A', result.get(0).grade());
            assertEquals('A', result.get(1).grade());
            assertEquals('B', result.get(2).grade());
            assertEquals('B', result.get(3).grade());
            assertEquals('C', result.get(4).grade());
        }

        @Test
        void gradeOrder2() {
            List<ComparatorChallenges.Student> result =
                    ComparatorChallenges.challenge3_2(students);

            assertEquals('A', result.get(0).grade());
            assertEquals('A', result.get(1).grade());
            assertEquals('B', result.get(2).grade());
            assertEquals('B', result.get(3).grade());
            assertEquals('C', result.get(4).grade());
        }


        @Test
        void nameOrderWithinGrade() {
            List<ComparatorChallenges.Student> result =
                    ComparatorChallenges.challenge3(students);

            assertEquals("Bob",   result.get(0).name()); // Grade A
            assertEquals("Eve",   result.get(1).name()); // Grade A
            assertEquals("Alice", result.get(2).name()); // Grade B
            assertEquals("Carol", result.get(3).name()); // Grade B
            assertEquals("Diana", result.get(4).name()); // Grade C
        }

        @Test
        void allSameGrade() {
            List<ComparatorChallenges.Student> same = new ArrayList<>(List.of(
                    new ComparatorChallenges.Student("Charlie", 'B'),
                    new ComparatorChallenges.Student("Alice",   'B'),
                    new ComparatorChallenges.Student("Bob",     'B')
            ));
            List<ComparatorChallenges.Student> result =
                    ComparatorChallenges.challenge3(same);

            assertEquals("Alice",   result.get(0).name());
            assertEquals("Bob",     result.get(1).name());
            assertEquals("Charlie", result.get(2).name());
        }

        @Test
        void allGrades() {
            List<ComparatorChallenges.Student> all = new ArrayList<>(List.of(
                    new ComparatorChallenges.Student("E", 'F'),
                    new ComparatorChallenges.Student("D", 'D'),
                    new ComparatorChallenges.Student("C", 'C'),
                    new ComparatorChallenges.Student("B", 'B'),
                    new ComparatorChallenges.Student("A", 'A')
            ));
            List<ComparatorChallenges.Student> result =
                    ComparatorChallenges.challenge3(all);

            assertEquals('A', result.get(0).grade());
            assertEquals('B', result.get(1).grade());
            assertEquals('C', result.get(2).grade());
            assertEquals('D', result.get(3).grade());
            assertEquals('F', result.get(4).grade());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge3(null));
        }

        @Test
        void emptyList() {
            assertTrue(ComparatorChallenges.challenge3(
                    new ArrayList<>()).isEmpty());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Sort 2D Intervals by Duration ASC
    //               then Start ASC for ties
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            int[][] result = ComparatorChallenges.challenge4(
                    new int[][]{{1,5},{2,4},{3,6},{1,3},{4,6}});

            // Durations: [4,2,3,2,2]
            // dur=2: [1,3],[2,4],[4,6] → start ASC
            // dur=3: [3,6]
            // dur=4: [1,5]
            assertArrayEquals(new int[]{1, 3}, result[0]); // dur=2, start=1
            assertArrayEquals(new int[]{2, 4}, result[1]); // dur=2, start=2
            assertArrayEquals(new int[]{4, 6}, result[2]); // dur=2, start=4
            assertArrayEquals(new int[]{3, 6}, result[3]); // dur=3
            assertArrayEquals(new int[]{1, 5}, result[4]); // dur=4
        }

        @Test
        void allSameDuration() {
            int[][] result = ComparatorChallenges.challenge4(
                    new int[][]{{3,5},{1,3},{2,4}});

            assertArrayEquals(new int[]{1, 3}, result[0]);
            assertArrayEquals(new int[]{2, 4}, result[1]);
            assertArrayEquals(new int[]{3, 5}, result[2]);
        }

        @Test
        void allSameDuration2() {
            int[][] result = ComparatorChallenges.challenge4_2(
                    new int[][]{{3,5},{1,3},{2,4}});

            assertArrayEquals(new int[]{1, 3}, result[0]);
            assertArrayEquals(new int[]{2, 4}, result[1]);
            assertArrayEquals(new int[]{3, 5}, result[2]);
        }

        @Test
        void allSameDuration3() {
            int[][] result = ComparatorChallenges.challenge4_3(
                    new int[][]{{3,5},{1,3},{2,4}});

            assertArrayEquals(new int[]{1, 3}, result[0]);
            assertArrayEquals(new int[]{2, 4}, result[1]);
            assertArrayEquals(new int[]{3, 5}, result[2]);
        }

        @Test
        void singleInterval() {
            int[][] result = ComparatorChallenges.challenge4(
                    new int[][]{{1, 5}});
            assertArrayEquals(new int[]{1, 5}, result[0]);
        }

        @Test
        void zeroDuration() {
            int[][] result = ComparatorChallenges.challenge4(
                    new int[][]{{3,3},{1,1},{2,2}});

            assertArrayEquals(new int[]{1, 1}, result[0]);
            assertArrayEquals(new int[]{2, 2}, result[1]);
            assertArrayEquals(new int[]{3, 3}, result[2]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Sort Products by Category Priority Map
    //               then Price ASC then Name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        private List<ComparatorChallenges.Product> products;

        @BeforeEach
        void setUp() {
            products = new ArrayList<>(List.of(
                    new ComparatorChallenges.Product("Phone",  "Electronics", 999.0),
                    new ComparatorChallenges.Product("Shirt",  "Clothing",     29.9),
                    new ComparatorChallenges.Product("Laptop", "Electronics", 1299.0),
                    new ComparatorChallenges.Product("Apple",  "Food",          0.5),
                    new ComparatorChallenges.Product("Jeans",  "Clothing",     59.9),
                    new ComparatorChallenges.Product("Tablet", "Electronics",  499.0)
            ));
        }

        @Test
        void categoryOrder() {
            List<ComparatorChallenges.Product> result =
                    ComparatorChallenges.challenge5(products);

            assertEquals("Electronics", result.get(0).category());
            assertEquals("Electronics", result.get(1).category());
            assertEquals("Electronics", result.get(2).category());
            assertEquals("Clothing",    result.get(3).category());
            assertEquals("Clothing",    result.get(4).category());
            assertEquals("Food",        result.get(5).category());
        }

        @Test
        void categoryOrder5_2() {
            List<ComparatorChallenges.Product> result =
                    ComparatorChallenges.challenge5_2(products);

            assertEquals("Electronics", result.get(0).category());
            assertEquals("Electronics", result.get(1).category());
            assertEquals("Electronics", result.get(2).category());
            assertEquals("Clothing",    result.get(3).category());
            assertEquals("Clothing",    result.get(4).category());
            assertEquals("Food",        result.get(5).category());
        }

        @Test
        void priceWithinCategory() {
            List<ComparatorChallenges.Product> result =
                    ComparatorChallenges.challenge5(products);

            assertEquals("Tablet", result.get(0).name());  // 499.0
            assertEquals("Phone",  result.get(1).name());  // 999.0
            assertEquals("Laptop", result.get(2).name());  // 1299.0
            assertEquals("Shirt",  result.get(3).name());  // 29.9
            assertEquals("Jeans",  result.get(4).name());  // 59.9
            assertEquals("Apple",  result.get(5).name());  // 0.5
        }

        @Test
        void samePriceSameCategory() {
            List<ComparatorChallenges.Product> same = new ArrayList<>(List.of(
                    new ComparatorChallenges.Product("Zebra", "Clothing", 29.9),
                    new ComparatorChallenges.Product("Alpha", "Clothing", 29.9),
                    new ComparatorChallenges.Product("Mango", "Clothing", 29.9)
            ));
            List<ComparatorChallenges.Product> result =
                    ComparatorChallenges.challenge5(same);

            assertEquals("Alpha", result.get(0).name());
            assertEquals("Mango", result.get(1).name());
            assertEquals("Zebra", result.get(2).name());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge5(null));
        }

        @Test
        void emptyList() {
            assertTrue(ComparatorChallenges.challenge5(
                    new ArrayList<>()).isEmpty());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Sort Map Entries by Value ASC
    //               then Key Length ASC then Key Alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "banana", 2,
                    "fig",    2,
                    "apple",  5,
                    "cat",    2,
                    "kiwi",   5
            ));
            List<Map.Entry<String, Integer>> result =
                    ComparatorChallenges.challenge6(map);

            // val=2: cat(3),fig(3),banana(6) → len then alpha
            assertEquals("cat",    result.get(0).getKey()); // val=2, len=3
            assertEquals("fig",    result.get(1).getKey()); // val=2, len=3
            assertEquals("banana", result.get(2).getKey()); // val=2, len=6
            // val=5: kiwi(4) before apple(5) by length
            assertEquals("kiwi",   result.get(3).getKey()); // val=5, len=4
            assertEquals("apple",  result.get(4).getKey()); // val=5, len=5
        }

        @Test
        void basicCase6_2() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "banana", 2,
                    "fig",    2,
                    "apple",  5,
                    "cat",    2,
                    "kiwi",   5
            ));
            List<Map.Entry<String, Integer>> result =
                    ComparatorChallenges.challenge6_2(map);

            // val=2: cat(3),fig(3),banana(6) → len then alpha
            assertEquals("cat",    result.get(0).getKey()); // val=2, len=3
            assertEquals("fig",    result.get(1).getKey()); // val=2, len=3
            assertEquals("banana", result.get(2).getKey()); // val=2, len=6
            // val=5: kiwi(4) before apple(5) by length
            assertEquals("kiwi",   result.get(3).getKey()); // val=5, len=4
            assertEquals("apple",  result.get(4).getKey()); // val=5, len=5
        }

        @Test
        void allSameValue() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "bb",  1,
                    "aaa", 1,
                    "c",   1
            ));
            List<Map.Entry<String, Integer>> result =
                    ComparatorChallenges.challenge6(map);

            // Same value → key length ASC: c(1), bb(2), aaa(3)
            assertEquals("c",   result.get(0).getKey());
            assertEquals("bb",  result.get(1).getKey());
            assertEquals("aaa", result.get(2).getKey());
        }

        @Test
        void sameValueSameLength() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "dog", 3,
                    "cat", 3,
                    "ant", 3
            ));
            List<Map.Entry<String, Integer>> result =
                    ComparatorChallenges.challenge6(map);

            assertEquals("ant", result.get(0).getKey());
            assertEquals("cat", result.get(1).getKey());
            assertEquals("dog", result.get(2).getKey());
        }

        @Test
        void emptyMap() {
            assertTrue(ComparatorChallenges.challenge6(
                    new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Sort 2D Matrix by Even Count DESC
    //               then Row Sum ASC for ties
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            int[][] result = ComparatorChallenges.challenge7(
                    new int[][]{{1,2,4},{3,5,7},{2,4,6},{1,3,5},{1,2,3}});

            // Even counts: 2,0,3,0,1
            assertArrayEquals(new int[]{2, 4, 6}, result[0]); // 3 evens
            assertArrayEquals(new int[]{1, 2, 4}, result[1]); // 2 evens
            assertArrayEquals(new int[]{1, 2, 3}, result[2]); // 1 even
            assertArrayEquals(new int[]{1, 3, 5}, result[3]); // 0 evens sum=9
            assertArrayEquals(new int[]{3, 5, 7}, result[4]); // 0 evens sum=15
        }

        @Test
        void basicCase7_2() {
            int[][] result = ComparatorChallenges.challenge7_2(
                    new int[][]{{1,2,4},{3,5,7},{2,4,6},{1,3,5},{1,2,3}});

            // Even counts: 2,0,3,0,1
            assertArrayEquals(new int[]{2, 4, 6}, result[0]); // 3 evens
            assertArrayEquals(new int[]{1, 2, 4}, result[1]); // 2 evens
            assertArrayEquals(new int[]{1, 2, 3}, result[2]); // 1 even
            assertArrayEquals(new int[]{1, 3, 5}, result[3]); // 0 evens sum=9
            assertArrayEquals(new int[]{3, 5, 7}, result[4]); // 0 evens sum=15
        }

        @Test
        void basicCase7_3() {
            int[][] result = ComparatorChallenges.challenge7_3(
                    new int[][]{{1,2,4},{3,5,7},{2,4,6},{1,3,5},{1,2,3}});

            // Even counts: 2,0,3,0,1
            assertArrayEquals(new int[]{2, 4, 6}, result[0]); // 3 evens
            assertArrayEquals(new int[]{1, 2, 4}, result[1]); // 2 evens
            assertArrayEquals(new int[]{1, 2, 3}, result[2]); // 1 even
            assertArrayEquals(new int[]{1, 3, 5}, result[3]); // 0 evens sum=9
            assertArrayEquals(new int[]{3, 5, 7}, result[4]); // 0 evens sum=15
        }

        @Test
        void allOdd() {
            int[][] result = ComparatorChallenges.challenge7(
                    new int[][]{{3,5,7},{1,3,5},{9,11,13}});

            assertArrayEquals(new int[]{1,  3,  5}, result[0]); // sum=9
            assertArrayEquals(new int[]{3,  5,  7}, result[1]); // sum=15
            assertArrayEquals(new int[]{9, 11, 13}, result[2]); // sum=33
        }

        @Test
        void allEven() {
            int[][] result = ComparatorChallenges.challenge7(
                    new int[][]{{2,4},{6,8},{10,12}});

            // All same even count → sum ASC
            assertArrayEquals(new int[]{ 2,  4}, result[0]); // sum=6
            assertArrayEquals(new int[]{ 6,  8}, result[1]); // sum=14
            assertArrayEquals(new int[]{10, 12}, result[2]); // sum=22
        }

        @Test
        void singleRow() {
            int[][] result = ComparatorChallenges.challenge7(
                    new int[][]{{1, 2, 3}});
            assertArrayEquals(new int[]{1, 2, 3}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Sort Tasks by Priority Map
    //               then Deadline ASC then Name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        private List<ComparatorChallenges.Task> tasks;

        @BeforeEach
        void setUp() {
            tasks = new ArrayList<>(List.of(
                    new ComparatorChallenges.Task("Deploy",  "HIGH",   3),
                    new ComparatorChallenges.Task("Test",    "MEDIUM", 1),
                    new ComparatorChallenges.Task("Review",  "HIGH",   1),
                    new ComparatorChallenges.Task("Meeting", "LOW",    2),
                    new ComparatorChallenges.Task("Fix Bug", "HIGH",   1),
                    new ComparatorChallenges.Task("Docs",    "MEDIUM", 3)
            ));
        }

        @Test
        void priorityOrder() {
            List<ComparatorChallenges.Task> result =
                    ComparatorChallenges.challenge8(tasks);

            assertEquals("HIGH",   result.get(0).priority());
            assertEquals("HIGH",   result.get(1).priority());
            assertEquals("HIGH",   result.get(2).priority());
            assertEquals("MEDIUM", result.get(3).priority());
            assertEquals("MEDIUM", result.get(4).priority());
            assertEquals("LOW",    result.get(5).priority());
        }

        @Test
        void priorityOrder8_2() {
            List<ComparatorChallenges.Task> result =
                    ComparatorChallenges.challenge8_2(tasks);

            assertEquals("HIGH",   result.get(0).priority());
            assertEquals("HIGH",   result.get(1).priority());
            assertEquals("HIGH",   result.get(2).priority());
            assertEquals("MEDIUM", result.get(3).priority());
            assertEquals("MEDIUM", result.get(4).priority());
            assertEquals("LOW",    result.get(5).priority());
        }

        @Test
        void deadlineWithinPriority() {
            List<ComparatorChallenges.Task> result =
                    ComparatorChallenges.challenge8(tasks);

            assertEquals("Fix Bug", result.get(0).name()); // HIGH dl=1 F<R
            assertEquals("Review",  result.get(1).name()); // HIGH dl=1 R>F
            assertEquals("Deploy",  result.get(2).name()); // HIGH dl=3
            assertEquals("Test",    result.get(3).name()); // MEDIUM dl=1
            assertEquals("Docs",    result.get(4).name()); // MEDIUM dl=3
            assertEquals("Meeting", result.get(5).name()); // LOW dl=2
        }

        @Test
        void allSamePriority() {
            List<ComparatorChallenges.Task> same = new ArrayList<>(List.of(
                    new ComparatorChallenges.Task("Zebra", "HIGH", 2),
                    new ComparatorChallenges.Task("Alpha", "HIGH", 1),
                    new ComparatorChallenges.Task("Mango", "HIGH", 1)
            ));
            List<ComparatorChallenges.Task> result =
                    ComparatorChallenges.challenge8(same);

            assertEquals("Alpha", result.get(0).name()); // dl=1, A<M
            assertEquals("Mango", result.get(1).name()); // dl=1, M>A
            assertEquals("Zebra", result.get(2).name()); // dl=2
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge8(null));
        }

        @Test
        void emptyList() {
            assertTrue(ComparatorChallenges.challenge8(
                    new ArrayList<>()).isEmpty());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Sort 2D Matrix Lexicographically
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            int[][] result = ComparatorChallenges.challenge9(
                    new int[][]{{3,1,4},{1,5,9},{1,5,2},{2,6,5},{1,5,9}});

            assertArrayEquals(new int[]{1, 5, 2}, result[0]);
            assertArrayEquals(new int[]{1, 5, 9}, result[1]);
            assertArrayEquals(new int[]{1, 5, 9}, result[2]);
            assertArrayEquals(new int[]{2, 6, 5}, result[3]);
            assertArrayEquals(new int[]{3, 1, 4}, result[4]);
        }

        @Test
        void firstElementDiffers() {
            int[][] result = ComparatorChallenges.challenge9(
                    new int[][]{{3,1},{1,9},{2,5}});

            assertArrayEquals(new int[]{1, 9}, result[0]);
            assertArrayEquals(new int[]{2, 5}, result[1]);
            assertArrayEquals(new int[]{3, 1}, result[2]);
        }

        @Test
        void allSame() {
            int[][] result = ComparatorChallenges.challenge9(
                    new int[][]{{1,2,3},{1,2,3},{1,2,3}});

            assertArrayEquals(new int[]{1, 2, 3}, result[0]);
            assertArrayEquals(new int[]{1, 2, 3}, result[1]);
            assertArrayEquals(new int[]{1, 2, 3}, result[2]);
        }

        @Test
        void singleRow() {
            int[][] result = ComparatorChallenges.challenge9(
                    new int[][]{{5, 3, 1}});
            assertArrayEquals(new int[]{5, 3, 1}, result[0]);
        }

        @Test
        void twoRows() {
            int[][] result = ComparatorChallenges.challenge9(
                    new int[][]{{2,1},{1,2}});

            assertArrayEquals(new int[]{1, 2}, result[0]);
            assertArrayEquals(new int[]{2, 1}, result[1]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Sort Cities by Continent Priority Map
    //                then Population DESC then Name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<ComparatorChallenges.City> cities;

        @BeforeEach
        void setUp() {
            cities = new ArrayList<>(List.of(
                    new ComparatorChallenges.City("Paris",     "Europe",    2_161_000),
                    new ComparatorChallenges.City("Tokyo",     "Asia",     13_960_000),
                    new ComparatorChallenges.City("Lagos",     "Africa",   14_862_000),
                    new ComparatorChallenges.City("New York",  "Americas",  8_336_000),
                    new ComparatorChallenges.City("London",    "Europe",    8_982_000),
                    new ComparatorChallenges.City("Shanghai",  "Asia",     24_870_000),
                    new ComparatorChallenges.City("Sao Paulo", "Americas", 12_325_000),
                    new ComparatorChallenges.City("Cairo",     "Africa",   20_076_000)
            ));
        }

        @Test
        void continentOrder() {
            List<ComparatorChallenges.City> result =
                    ComparatorChallenges.challenge10(cities);

            assertEquals("Europe",   result.get(0).continent());
            assertEquals("Europe",   result.get(1).continent());
            assertEquals("Asia",     result.get(2).continent());
            assertEquals("Asia",     result.get(3).continent());
            assertEquals("Americas", result.get(4).continent());
            assertEquals("Americas", result.get(5).continent());
            assertEquals("Africa",   result.get(6).continent());
            assertEquals("Africa",   result.get(7).continent());
        }

        @Test
        void populationWithinContinent() {
            List<ComparatorChallenges.City> result =
                    ComparatorChallenges.challenge10(cities);

            assertEquals("London",    result.get(0).name()); // Europe  8.9M
            assertEquals("Paris",     result.get(1).name()); // Europe  2.1M
            assertEquals("Shanghai",  result.get(2).name()); // Asia   24.8M
            assertEquals("Tokyo",     result.get(3).name()); // Asia   13.9M
            assertEquals("Sao Paulo", result.get(4).name()); // Americas 12.3M
            assertEquals("New York",  result.get(5).name()); // Americas  8.3M
            assertEquals("Cairo",     result.get(6).name()); // Africa  20M
            assertEquals("Lagos",     result.get(7).name()); // Africa  14.8M
        }

        @Test
        void sameContinentSamePopulation() {
            List<ComparatorChallenges.City> same = new ArrayList<>(List.of(
                    new ComparatorChallenges.City("Zurich", "Europe", 400_000),
                    new ComparatorChallenges.City("Athens", "Europe", 400_000),
                    new ComparatorChallenges.City("Madrid", "Europe", 400_000)
            ));
            List<ComparatorChallenges.City> result =
                    ComparatorChallenges.challenge10(same);

            assertEquals("Athens", result.get(0).name());
            assertEquals("Madrid", result.get(1).name());
            assertEquals("Zurich", result.get(2).name());
        }

        @Test
        void singleCity() {
            List<ComparatorChallenges.City> single = new ArrayList<>(List.of(
                    new ComparatorChallenges.City("Paris", "Europe", 2_161_000)));
            assertEquals(1, ComparatorChallenges.challenge10(single).size());
        }

        @Test
        void emptyList() {
            assertTrue(ComparatorChallenges.challenge10(
                    new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> ComparatorChallenges.challenge10(null));
        }
    }
}