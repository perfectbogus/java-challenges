package dev.perfectbogus.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingMixChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — 🟢 Sort by absolute value ASC then original value ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<Integer> result = SortingMixChallenges.challenge1(
                    new ArrayList<>(List.of(-5, 3, -3, 1, -1, 4, -4, 2)));

            assertEquals(List.of(-1, 1, 2, -3, 3, -4, 4, -5), result);
        }

        @Test
        void allPositive() {
            List<Integer> result = SortingMixChallenges.challenge1(
                    new ArrayList<>(List.of(5, 2, 8, 1, 3)));

            assertEquals(List.of(1, 2, 3, 5, 8), result);
        }

        @Test
        void allNegative() {
            List<Integer> result = SortingMixChallenges.challenge1(
                    new ArrayList<>(List.of(-5, -2, -8, -1, -3)));

            assertEquals(List.of(-1, -2, -3, -5, -8), result);
        }

        @Test
        void withZero() {
            List<Integer> result = SortingMixChallenges.challenge1(
                    new ArrayList<>(List.of(-3, 0, 3, -1, 1)));

            assertEquals(List.of(0, -1, 1, -3, 3), result);
        }

        @Test
        void singleElement() {
            assertEquals(List.of(-5), SortingMixChallenges.challenge1(new ArrayList<>(List.of(-5))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges.challenge1(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — 🟢 Sort by vowel count ASC then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            List<String> result = SortingMixChallenges.challenge2(
                    new ArrayList<>(List.of("hello","fig","apple","cat","rhythm","bee","cry")));

            // vowels=0: cry, rhythm → alpha
            assertEquals("cry",    result.get(0));
            assertEquals("rhythm", result.get(1));
            // vowels=1: cat(a), fig(i) → alpha
            assertEquals("cat",    result.get(2));
            assertEquals("fig",    result.get(3));
            // vowels=2: apple(ae), bee(ee), hello(eo) → alpha
            assertEquals("apple",  result.get(4));
            assertEquals("bee",    result.get(5));
            assertEquals("hello",  result.get(6));
        }

        @Test
        void basicCase2_2() {
            List<String> result = SortingMixChallenges.challenge2_2(
                    new ArrayList<>(List.of("hello","fig","apple","cat","rhythm","bee","cry")));

            // vowels=0: cry, rhythm → alpha
            assertEquals("cry",    result.get(0));
            assertEquals("rhythm", result.get(1));
            // vowels=1: cat(a), fig(i) → alpha
            assertEquals("cat",    result.get(2));
            assertEquals("fig",    result.get(3));
            // vowels=2: apple(ae), bee(ee), hello(eo) → alpha
            assertEquals("apple",  result.get(4));
            assertEquals("bee",    result.get(5));
            assertEquals("hello",  result.get(6));
        }

        @Test
        void allNoVowels() {
            List<String> result = SortingMixChallenges.challenge2(
                    new ArrayList<>(List.of("cry","gym","myth","why")));

            assertEquals(List.of("cry","gym","myth","why"), result);
        }

        @Test
        void allSameVowelCount() {
            List<String> result = SortingMixChallenges.challenge2(
                    new ArrayList<>(List.of("cat","bat","hat")));

            assertEquals(List.of("bat","cat","hat"), result);
        }

        @Test
        void singleWord() {
            assertEquals(List.of("hello"), SortingMixChallenges.challenge2(new ArrayList<>(List.of("hello"))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges.challenge2(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — 🟢 Sort Map entries by last char of key ASC then value DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "banana", 3, "apple", 5, "grape", 2, "orange", 4, "mango", 1));

            List<Map.Entry<String, Integer>> result = SortingMixChallenges.challenge3(map);

            // last='a': banana(3)
            assertEquals("banana", result.get(0).getKey());
            // last='e': apple(5),orange(4),grape(2) → value DESC
            assertEquals("apple",  result.get(1).getKey());
            assertEquals("orange", result.get(2).getKey());
            assertEquals("grape",  result.get(3).getKey());
            // last='o': mango(1)
            assertEquals("mango",  result.get(4).getKey());
        }

        @Test
        void allSameLastChar() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 3, "table", 5, "cable", 1));

            List<Map.Entry<String, Integer>> result = SortingMixChallenges.challenge3(map);

            // All end in 'e' → value DESC: table(5),apple(3),cable(1)
            assertEquals("table", result.get(0).getKey());
            assertEquals("apple", result.get(1).getKey());
            assertEquals("cable", result.get(2).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 5));
            assertEquals(1, SortingMixChallenges.challenge3(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingMixChallenges.challenge3(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — 🟡 Sort orders by status priority then total DESC then ID ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        private List<SortingMixChallenges.Order> orders;

        @BeforeEach
        void setUp() {
            orders = new ArrayList<>(List.of(
                    new SortingMixChallenges.Order("O1", "PENDING",   10.0, 3),  // total=30
                    new SortingMixChallenges.Order("O2", "DELIVERED", 50.0, 2),  // total=100
                    new SortingMixChallenges.Order("O3", "CANCELLED", 20.0, 1),  // total=20
                    new SortingMixChallenges.Order("O4", "DELIVERED", 30.0, 3),  // total=90
                    new SortingMixChallenges.Order("O5", "PENDING",   15.0, 2),  // total=30
                    new SortingMixChallenges.Order("O6", "DELIVERED", 50.0, 2)   // total=100
            ));
        }

        @Test
        void statusOrder() {
            List<SortingMixChallenges.Order> result = SortingMixChallenges.challenge4(orders);

            assertEquals("DELIVERED", result.get(0).status());
            assertEquals("DELIVERED", result.get(1).status());
            assertEquals("DELIVERED", result.get(2).status());
            assertEquals("PENDING",   result.get(3).status());
            assertEquals("PENDING",   result.get(4).status());
            assertEquals("CANCELLED", result.get(5).status());
        }

        @Test
        void totalWithinStatus() {
            List<SortingMixChallenges.Order> result = SortingMixChallenges.challenge4(orders);

            // DELIVERED: total DESC then ID ASC
            assertEquals("O2", result.get(0).id()); // total=100, O2<O6
            assertEquals("O6", result.get(1).id()); // total=100, O6>O2
            assertEquals("O4", result.get(2).id()); // total=90

            // PENDING: total=30=30 → ID ASC
            assertEquals("O1", result.get(3).id()); // O1<O5
            assertEquals("O5", result.get(4).id());

            // CANCELLED
            assertEquals("O3", result.get(5).id());
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges.challenge4(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — 🟢 Sort 2D by column count ASC then row sum DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            int[][] result = SortingMixChallenges.challenge5(
                    new int[][]{{1,2,3,4},{5,6},{7,8,9},{1,2},{3,4,5}});

            // cols=2: [5,6]=11, [1,2]=3 → sum DESC
            assertArrayEquals(new int[]{5, 6},    result[0]); // cols=2, sum=11
            assertArrayEquals(new int[]{1, 2},    result[1]); // cols=2, sum=3
            // cols=3: [7,8,9]=24, [3,4,5]=12 → sum DESC
            assertArrayEquals(new int[]{7, 8, 9}, result[2]); // cols=3, sum=24
            assertArrayEquals(new int[]{3, 4, 5}, result[3]); // cols=3, sum=12
            // cols=4
            assertArrayEquals(new int[]{1, 2, 3, 4}, result[4]); // cols=4
        }

        @Test
        void allSameColumnCount() {
            int[][] result = SortingMixChallenges.challenge5(
                    new int[][]{{1,2},{5,6},{3,4}});

            // Same cols=2 → sum DESC: 11,7,3
            assertArrayEquals(new int[]{5, 6}, result[0]); // sum=11
            assertArrayEquals(new int[]{3, 4}, result[1]); // sum=7
            assertArrayEquals(new int[]{1, 2}, result[2]); // sum=3
        }

        @Test
        void singleRow() {
            int[][] result = SortingMixChallenges.challenge5(new int[][]{{1, 2, 3}});
            assertArrayEquals(new int[]{1, 2, 3}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — 🟡 Sort employees by seniority level then salary DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        private List<SortingMixChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingMixChallenges.Employee("Alice",  95000, 10),  // SENIOR
                    new SortingMixChallenges.Employee("Bob",    60000, 2),   // JUNIOR
                    new SortingMixChallenges.Employee("Carol",  85000, 8),   // SENIOR
                    new SortingMixChallenges.Employee("Diana",  70000, 5),   // MID
                    new SortingMixChallenges.Employee("Eve",    95000, 9),   // SENIOR
                    new SortingMixChallenges.Employee("Frank",  65000, 4)    // MID
            ));
        }

        @Test
        void seniorityOrder() {
            List<SortingMixChallenges.Employee> result = SortingMixChallenges.challenge6(employees);

            // First 3 are SENIOR (years >= 8)
            assertTrue(result.get(0).yearsOfExperience() >= 8);
            assertTrue(result.get(1).yearsOfExperience() >= 8);
            assertTrue(result.get(2).yearsOfExperience() >= 8);
            // Next 2 are MID (years >= 4)
            assertTrue(result.get(3).yearsOfExperience() >= 4 && result.get(3).yearsOfExperience() < 8);
            assertTrue(result.get(4).yearsOfExperience() >= 4 && result.get(4).yearsOfExperience() < 8);
            // Last is JUNIOR (years < 4)
            assertTrue(result.get(5).yearsOfExperience() < 4);
        }

        @Test
        void salaryWithinSeniority() {
            List<SortingMixChallenges.Employee> result = SortingMixChallenges.challenge6(employees);

            // SENIOR: Alice=95000, Eve=95000 → name ASC: Alice, Eve; Carol=85000
            assertEquals("Alice", result.get(0).name());
            assertEquals("Eve",   result.get(1).name());
            assertEquals("Carol", result.get(2).name());

            // MID: Diana=70000, Frank=65000
            assertEquals("Diana", result.get(3).name());
            assertEquals("Frank", result.get(4).name());

            // JUNIOR
            assertEquals("Bob",   result.get(5).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges.challenge6(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — 🟢 Sort by uppercase count DESC then length ASC then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            List<String> result = SortingMixChallenges.challenge7(
                    new ArrayList<>(List.of("Hello","WORLD","java","Hi","JAVA","cat")));

            assertEquals("WORLD", result.get(0)); // upper=5
            assertEquals("JAVA",  result.get(1)); // upper=4
            assertEquals("Hi",    result.get(2)); // upper=1, len=2
            assertEquals("Hello", result.get(3)); // upper=1, len=5
            assertEquals("cat",   result.get(4)); // upper=0, len=3
            assertEquals("java",  result.get(5)); // upper=0, len=4
        }

        @Test
        void basicCase7_2() {
            List<String> result = SortingMixChallenges.challenge7_2(
                    new ArrayList<>(List.of("Hello","WORLD","java","Hi","JAVA","cat")));

            assertEquals("WORLD", result.get(0)); // upper=5
            assertEquals("JAVA",  result.get(1)); // upper=4
            assertEquals("Hi",    result.get(2)); // upper=1, len=2
            assertEquals("Hello", result.get(3)); // upper=1, len=5
            assertEquals("cat",   result.get(4)); // upper=0, len=3
            assertEquals("java",  result.get(5)); // upper=0, len=4
        }

        @Test
        void allLowercase() {
            List<String> result = SortingMixChallenges.challenge7(
                    new ArrayList<>(List.of("zebra","apple","mango")));

            // All upper=0 → length ASC then alpha ASC
            assertEquals("apple", result.get(0)); // len=5, a<m<z... wait all len=5
            // All same length=5 → alpha ASC
            assertEquals("apple", result.get(0));
            assertEquals("mango", result.get(1));
            assertEquals("zebra", result.get(2));
        }

        @Test
        void allUppercase() {
            List<String> result = SortingMixChallenges.challenge7(
                    new ArrayList<>(List.of("CAT","APPLE","DOG")));

            // upper=3,5,3 → upper DESC: APPLE(5), then CAT=DOG upper=3 → len ASC: CAT(3),DOG(3) → alpha
            assertEquals("APPLE", result.get(0)); // upper=5
            assertEquals("CAT",   result.get(1)); // upper=3, len=3, C<D
            assertEquals("DOG",   result.get(2)); // upper=3, len=3, D>C
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges.challenge7(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — 🟡 Sort by salary/years ratio DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            List<SortingMixChallenges.Employee> employees = new ArrayList<>(List.of(
                    new SortingMixChallenges.Employee("Alice",  95000, 5),   // ratio=19000
                    new SortingMixChallenges.Employee("Bob",    60000, 2),   // ratio=30000
                    new SortingMixChallenges.Employee("Carol",  85000, 10),  // ratio=8500
                    new SortingMixChallenges.Employee("Diana",  70000, 5),   // ratio=14000
                    new SortingMixChallenges.Employee("Eve",    60000, 2)    // ratio=30000
            ));
            List<SortingMixChallenges.Employee> result = SortingMixChallenges.challenge8(employees);

            assertEquals("Bob",   result.get(0).name()); // ratio=30000, B<E
            assertEquals("Eve",   result.get(1).name()); // ratio=30000, E>B
            assertEquals("Alice", result.get(2).name()); // ratio=19000
            assertEquals("Diana", result.get(3).name()); // ratio=14000
            assertEquals("Carol", result.get(4).name()); // ratio=8500
        }

        @Test
        void allSameRatio() {
            List<SortingMixChallenges.Employee> employees = new ArrayList<>(List.of(
                    new SortingMixChallenges.Employee("Charlie", 60000, 2),  // ratio=30000
                    new SortingMixChallenges.Employee("Alice",   30000, 1),  // ratio=30000
                    new SortingMixChallenges.Employee("Bob",     90000, 3)   // ratio=30000
            ));
            List<SortingMixChallenges.Employee> result = SortingMixChallenges.challenge8(employees);

            // Same ratio → name ASC
            assertEquals("Alice",   result.get(0).name());
            assertEquals("Bob",     result.get(1).name());
            assertEquals("Charlie", result.get(2).name());
        }

        @Test
        void singleEmployee() {
            List<SortingMixChallenges.Employee> single = new ArrayList<>(List.of(
                    new SortingMixChallenges.Employee("Alice", 95000, 5)));
            assertEquals(1, SortingMixChallenges.challenge8(single).size());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — 🟢 Sort Map entries by (value mod 3) ASC then key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "apple",      7,   // 7%3=1
                    "banana",     3,   // 3%3=0
                    "cherry",     5,   // 5%3=2
                    "date",       9,   // 9%3=0
                    "elderberry", 4    // 4%3=1
            ));
            List<Map.Entry<String, Integer>> result = SortingMixChallenges.challenge9(map);

            // mod=0: banana, date → alpha
            assertEquals("banana",     result.get(0).getKey());
            assertEquals("date",       result.get(1).getKey());
            // mod=1: apple, elderberry → alpha
            assertEquals("apple",      result.get(2).getKey());
            assertEquals("elderberry", result.get(3).getKey());
            // mod=2: cherry
            assertEquals("cherry",     result.get(4).getKey());
        }

        @Test
        void allSameMod() {
            Map<String, Integer> map = new HashMap<>(Map.of("cat", 3, "ant", 6, "bat", 9));

            List<Map.Entry<String, Integer>> result = SortingMixChallenges.challenge9(map);

            // All mod=0 → alpha ASC
            assertEquals("ant", result.get(0).getKey());
            assertEquals("bat", result.get(1).getKey());
            assertEquals("cat", result.get(2).getKey());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingMixChallenges.challenge9(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — 🟡 Sort products by category priority then discount DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<SortingMixChallenges.Product> products;

        @BeforeEach
        void setUp() {
            products = new ArrayList<>(List.of(
                    new SortingMixChallenges.Product("Phone",  "Electronics", 1000, 800),  // 20%
                    new SortingMixChallenges.Product("Shirt",  "Clothing",    100,   70),  // 30%
                    new SortingMixChallenges.Product("Laptop", "Electronics", 2000, 1500), // 25%
                    new SortingMixChallenges.Product("Apple",  "Food",        2,     1.5), // 25%
                    new SortingMixChallenges.Product("Jeans",  "Clothing",    150,  150),  //  0%
                    new SortingMixChallenges.Product("Tablet", "Electronics", 500,  500),  //  0%
                    new SortingMixChallenges.Product("Banana", "Food",        1,    0.75)  // 25%
            ));
        }

        @Test
        void categoryOrder() {
            List<SortingMixChallenges.Product> result = SortingMixChallenges.challenge10(products);

            assertEquals("Electronics", result.get(0).category());
            assertEquals("Electronics", result.get(1).category());
            assertEquals("Electronics", result.get(2).category());
            assertEquals("Clothing",    result.get(3).category());
            assertEquals("Clothing",    result.get(4).category());
            assertEquals("Food",        result.get(5).category());
            assertEquals("Food",        result.get(6).category());
        }

        @Test
        void discountWithinCategory() {
            List<SortingMixChallenges.Product> result = SortingMixChallenges.challenge10(products);

            // Electronics: Laptop=25%, Phone=20%, Tablet=0%
            assertEquals("Laptop", result.get(0).name());
            assertEquals("Phone",  result.get(1).name());
            assertEquals("Tablet", result.get(2).name());

            // Clothing: Shirt=30%, Jeans=0%
            assertEquals("Shirt",  result.get(3).name());
            assertEquals("Jeans",  result.get(4).name());

            // Food: Apple=25%, Banana=25% → name ASC: Apple, Banana
            assertEquals("Apple",  result.get(5).name());
            assertEquals("Banana", result.get(6).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges.challenge10(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingMixChallenges.challenge10(null));
        }
    }
}