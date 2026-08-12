package dev.perfectbogus.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TypeWitnessChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Sort Strings by Length DESC then Alpha ASC
    // ⚠️ Type witness needed: comparingInt(length).reversed()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<String> result = TypeWitnessChallenges.challenge1(
                    new ArrayList<>(List.of(
                            "fig","banana","kiwi","apple","plum","date")));

            assertEquals("banana", result.get(0)); // len=6
            assertEquals("apple",  result.get(1)); // len=5
            assertEquals("date",   result.get(2)); // len=4, d<k<p
            assertEquals("kiwi",   result.get(3)); // len=4
            assertEquals("plum",   result.get(4)); // len=4
            assertEquals("fig",    result.get(5)); // len=3
        }

        @Test
        void basicCase1_2() {
            List<String> result = TypeWitnessChallenges.challenge1_2(
                    new ArrayList<>(List.of(
                            "fig","banana","kiwi","apple","plum","date")));

            assertEquals("banana", result.get(0)); // len=6
            assertEquals("apple",  result.get(1)); // len=5
            assertEquals("date",   result.get(2)); // len=4, d<k<p
            assertEquals("kiwi",   result.get(3)); // len=4
            assertEquals("plum",   result.get(4)); // len=4
            assertEquals("fig",    result.get(5)); // len=3
        }
        @Test
        void allSameLength() {
            List<String> result = TypeWitnessChallenges.challenge1(
                    new ArrayList<>(List.of("dog","cat","ant")));

            // Same length → alpha ASC
            assertEquals("ant", result.get(0));
            assertEquals("cat", result.get(1));
            assertEquals("dog", result.get(2));
        }

        @Test
        void allDifferentLength() {
            List<String> result = TypeWitnessChallenges.challenge1(
                    new ArrayList<>(List.of("a","bbb","cc")));

            // Length DESC: 3,2,1
            assertEquals("bbb", result.get(0));
            assertEquals("cc",  result.get(1));
            assertEquals("a",   result.get(2));
        }

        @Test
        void singleWord() {
            List<String> result = TypeWitnessChallenges.challenge1(
                    new ArrayList<>(List.of("hello")));
            assertEquals(1, result.size());
            assertEquals("hello", result.get(0));
        }

        @Test
        void emptyList() {
            assertTrue(TypeWitnessChallenges.challenge1(
                    new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Sort Map Entries by Value DESC then Key DESC
    // ⚠️ Type witness needed on BOTH comparingByValue and comparingByKey
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "apple",      5,
                    "banana",     3,
                    "cherry",     5,
                    "date",       1,
                    "elderberry", 3
            ));
            List<Map.Entry<String, Integer>> result =
                    TypeWitnessChallenges.challenge2(map);

            // val=5: cherry>apple DESC alpha
            assertEquals("cherry",     result.get(0).getKey());
            assertEquals("apple",      result.get(1).getKey());
            // val=3: elderberry>banana DESC alpha
            assertEquals("elderberry", result.get(2).getKey());
            assertEquals("banana",     result.get(3).getKey());
            // val=1
            assertEquals("date",       result.get(4).getKey());
        }

        @Test
        void basicCase2_2() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "apple",      5,
                    "banana",     3,
                    "cherry",     5,
                    "date",       1,
                    "elderberry", 3
            ));
            List<Map.Entry<String, Integer>> result =
                    TypeWitnessChallenges.challenge2_2(map);

            // val=5: cherry>apple DESC alpha
            assertEquals("cherry",     result.get(0).getKey());
            assertEquals("apple",      result.get(1).getKey());
            // val=3: elderberry>banana DESC alpha
            assertEquals("elderberry", result.get(2).getKey());
            assertEquals("banana",     result.get(3).getKey());
            // val=1
            assertEquals("date",       result.get(4).getKey());
        }

        @Test
        void allSameValue() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat", 2,
                    "ant", 2,
                    "bat", 2
            ));
            List<Map.Entry<String, Integer>> result =
                    TypeWitnessChallenges.challenge2(map);

            // Same value → key DESC: cat>bat>ant
            assertEquals("cat", result.get(0).getKey());
            assertEquals("bat", result.get(1).getKey());
            assertEquals("ant", result.get(2).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 5));
            assertEquals(1, TypeWitnessChallenges.challenge2(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(TypeWitnessChallenges.challenge2(
                    new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Sort Employees by Salary DESC then Experience DESC
    // ⚠️ Type witness needed on BOTH reversed() calls
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        private List<TypeWitnessChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Employee("Alice",  95000, 5),
                    new TypeWitnessChallenges.Employee("Bob",    85000, 8),
                    new TypeWitnessChallenges.Employee("Carol",  95000, 3),
                    new TypeWitnessChallenges.Employee("Diana",  85000, 8),
                    new TypeWitnessChallenges.Employee("Eve",    72000, 6)
            ));
        }

        @Test
        void salaryOrder() {
            List<TypeWitnessChallenges.Employee> result =
                    TypeWitnessChallenges.challenge3(employees);

            assertEquals(95000, result.get(0).salary(), 0.01);
            assertEquals(95000, result.get(1).salary(), 0.01);
            assertEquals(85000, result.get(2).salary(), 0.01);
            assertEquals(85000, result.get(3).salary(), 0.01);
            assertEquals(72000, result.get(4).salary(), 0.01);
        }

        @Test
        void experienceWithinSalary() {
            List<TypeWitnessChallenges.Employee> result =
                    TypeWitnessChallenges.challenge3(employees);

            // salary=95000: exp DESC → Alice(5) before Carol(3)
            assertEquals("Alice", result.get(0).name());
            assertEquals("Carol", result.get(1).name());

            // salary=85000: exp=8=8 → name ASC → Bob before Diana
            assertEquals("Bob",   result.get(2).name());
            assertEquals("Diana", result.get(3).name());

            // salary=72000
            assertEquals("Eve",   result.get(4).name());
        }

        @Test
        void allSameSalary() {
            List<TypeWitnessChallenges.Employee> same = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Employee("Charlie", 80000, 2),
                    new TypeWitnessChallenges.Employee("Alice",   80000, 5),
                    new TypeWitnessChallenges.Employee("Bob",     80000, 5)
            ));
            List<TypeWitnessChallenges.Employee> result =
                    TypeWitnessChallenges.challenge3(same);

            // Same salary → exp DESC: 5,5,2
            // Same exp → name ASC: Alice before Bob
            assertEquals("Alice",   result.get(0).name()); // exp=5, A<B
            assertEquals("Bob",     result.get(1).name()); // exp=5, B>A
            assertEquals("Charlie", result.get(2).name()); // exp=2
        }

        @Test
        void emptyList() {
            assertTrue(TypeWitnessChallenges.challenge3(
                    new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Sort 2D Array by Row Max DESC then Row Min ASC
    // ⚠️ Type witness needed: comparingInt((int[] row) -> ...).reversed()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            int[][] result = TypeWitnessChallenges.challenge4(
                    new int[][]{{3,1,4},{5,2,1},{3,1,2},{5,0,3},{2,6,1}});

            // Max values: [4,5,3,5,6]
            // max=6: [2,6,1]
            // max=5: [5,0,3] min=0, [5,2,1] min=1 → min ASC
            // max=4: [3,1,4]
            // max=3: [3,1,2]
            assertArrayEquals(new int[]{2, 6, 1}, result[0]); // max=6
            assertArrayEquals(new int[]{5, 0, 3}, result[1]); // max=5, min=0
            assertArrayEquals(new int[]{5, 2, 1}, result[2]); // max=5, min=1
            assertArrayEquals(new int[]{3, 1, 4}, result[3]); // max=4
            assertArrayEquals(new int[]{3, 1, 2}, result[4]); // max=3
        }

        @Test
        void allSameMax() {
            int[][] result = TypeWitnessChallenges.challenge4(
                    new int[][]{{5,1,3},{5,0,2},{5,2,4}});

            // Same max=5 → min ASC: 0,1,2
            assertArrayEquals(new int[]{5, 0, 2}, result[0]); // min=0
            assertArrayEquals(new int[]{5, 1, 3}, result[1]); // min=1
            assertArrayEquals(new int[]{5, 2, 4}, result[2]); // min=2
        }

        @Test
        void singleRow() {
            int[][] result = TypeWitnessChallenges.challenge4(
                    new int[][]{{3, 1, 4}});
            assertArrayEquals(new int[]{3, 1, 4}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Sort Map Entries by Key Length DESC
    //               then Value ASC then Key Alpha ASC
    // ⚠️ Type witness needed: comparingByKey(customComp).reversed()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat",      3,
                    "elephant", 1,
                    "dog",      5,
                    "ant",      2,
                    "bear",     4,
                    "eel",      5
            ));
            List<Map.Entry<String, Integer>> result =
                    TypeWitnessChallenges.challenge5(map);

            // len=8: elephant(1)
            assertEquals("elephant", result.get(0).getKey());
            // len=4: bear(4)
            assertEquals("bear",     result.get(1).getKey());
            // len=3: ant(2),cat(3),dog(5),eel(5)
            // val ASC: ant(2),cat(3),dog(5),eel(5) → dog=eel tie → alpha
            assertEquals("ant",      result.get(2).getKey()); // val=2
            assertEquals("cat",      result.get(3).getKey()); // val=3
            assertEquals("dog",      result.get(4).getKey()); // val=5, d<e
            assertEquals("eel",      result.get(5).getKey()); // val=5, e>d
        }

        @Test
        void allSameLength() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat", 3,
                    "dog", 1,
                    "ant", 2
            ));
            List<Map.Entry<String, Integer>> result =
                    TypeWitnessChallenges.challenge5(map);

            // Same length → val ASC: dog(1),ant(2),cat(3)
            assertEquals("dog", result.get(0).getKey());
            assertEquals("ant", result.get(1).getKey());
            assertEquals("cat", result.get(2).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 5));
            assertEquals(1, TypeWitnessChallenges.challenge5(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(TypeWitnessChallenges.challenge5(
                    new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — PriorityQueue by Score DESC then Name ASC
    // ⚠️ Type witness needed inside PriorityQueue constructor
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        private List<TypeWitnessChallenges.Player> players;

        @BeforeEach
        void setUp() {
            players = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Player("Alice", 850),
                    new TypeWitnessChallenges.Player("Bob",   920),
                    new TypeWitnessChallenges.Player("Carol", 850),
                    new TypeWitnessChallenges.Player("Diana", 920),
                    new TypeWitnessChallenges.Player("Eve",   750)
            ));
        }

        @Test
        void scoreOrder() {
            List<TypeWitnessChallenges.Player> result =
                    TypeWitnessChallenges.challenge6(players);

            assertEquals(920, result.get(0).score());
            assertEquals(920, result.get(1).score());
            assertEquals(850, result.get(2).score());
            assertEquals(850, result.get(3).score());
            assertEquals(750, result.get(4).score());
        }

        @Test
        void nameWithinScore() {
            List<TypeWitnessChallenges.Player> result =
                    TypeWitnessChallenges.challenge6(players);

            // score=920: Bob<Diana alpha ASC
            assertEquals("Bob",   result.get(0).name());
            assertEquals("Diana", result.get(1).name());

            // score=850: Alice<Carol alpha ASC
            assertEquals("Alice", result.get(2).name());
            assertEquals("Carol", result.get(3).name());

            // score=750
            assertEquals("Eve",   result.get(4).name());
        }

        @Test
        void allSameScore() {
            List<TypeWitnessChallenges.Player> same = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Player("Charlie", 500),
                    new TypeWitnessChallenges.Player("Alice",   500),
                    new TypeWitnessChallenges.Player("Bob",     500)
            ));
            List<TypeWitnessChallenges.Player> result =
                    TypeWitnessChallenges.challenge6(same);

            // Same score → name ASC
            assertEquals("Alice",   result.get(0).name());
            assertEquals("Bob",     result.get(1).name());
            assertEquals("Charlie", result.get(2).name());
        }

        @Test
        void singlePlayer() {
            List<TypeWitnessChallenges.Player> single = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Player("Alice", 100)));
            assertEquals(1, TypeWitnessChallenges.challenge6(single).size());
        }

        @Test
        void emptyList() {
            assertTrue(TypeWitnessChallenges.challenge6(
                    new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Sort Products by Stock DESC then Price DESC
    //               then Name ASC
    // ⚠️ Type witness needed on stock.reversed() AND price.reversed()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        private List<TypeWitnessChallenges.Product> products;

        @BeforeEach
        void setUp() {
            products = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Product("Phone",  999.0,  50),
                    new TypeWitnessChallenges.Product("Tablet", 499.0,  50),
                    new TypeWitnessChallenges.Product("Laptop", 1299.0, 30),
                    new TypeWitnessChallenges.Product("Watch",  299.0,  50),
                    new TypeWitnessChallenges.Product("Cable",  9.99,  100)
            ));
        }

        @Test
        void stockOrder() {
            List<TypeWitnessChallenges.Product> result =
                    TypeWitnessChallenges.challenge7(products);

            assertEquals(100, result.get(0).stock()); // Cable
            assertEquals(50,  result.get(1).stock()); // Phone
            assertEquals(50,  result.get(2).stock()); // Tablet
            assertEquals(50,  result.get(3).stock()); // Watch
            assertEquals(30,  result.get(4).stock()); // Laptop
        }

        @Test
        void priceWithinStock() {
            List<TypeWitnessChallenges.Product> result =
                    TypeWitnessChallenges.challenge7(products);

            assertEquals("Cable",  result.get(0).name()); // stock=100
            // stock=50 → price DESC: 999,499,299
            assertEquals("Phone",  result.get(1).name()); // price=999
            assertEquals("Tablet", result.get(2).name()); // price=499
            assertEquals("Watch",  result.get(3).name()); // price=299
            assertEquals("Laptop", result.get(4).name()); // stock=30
        }

        @Test
        void sameStockSamePrice() {
            List<TypeWitnessChallenges.Product> same = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Product("Zebra", 100.0, 10),
                    new TypeWitnessChallenges.Product("Alpha", 100.0, 10),
                    new TypeWitnessChallenges.Product("Mango", 100.0, 10)
            ));
            List<TypeWitnessChallenges.Product> result =
                    TypeWitnessChallenges.challenge7(same);

            // Same stock + price → name ASC
            assertEquals("Alpha", result.get(0).name());
            assertEquals("Mango", result.get(1).name());
            assertEquals("Zebra", result.get(2).name());
        }

        @Test
        void emptyList() {
            assertTrue(TypeWitnessChallenges.challenge7(
                    new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Collect Map into TreeMap Sorted by Value DESC
    // ⚠️ Type witness needed in TreeMap constructor
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "apple",      3,
                    "banana",     1,
                    "cherry",     5,
                    "date",       3,
                    "elderberry", 2
            ));
            Map<String, Integer> result =
                    TypeWitnessChallenges.challenge8(map);

            List<String> keys = new ArrayList<>(result.keySet());
            // Value DESC then key ASC:
            assertEquals("cherry",     keys.get(0)); // val=5
            assertEquals("apple",      keys.get(1)); // val=3, a<d
            assertEquals("date",       keys.get(2)); // val=3, d>a
            assertEquals("elderberry", keys.get(3)); // val=2
            assertEquals("banana",     keys.get(4)); // val=1
        }

        @Test
        void allSameValue() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat", 1,
                    "ant", 1,
                    "bat", 1
            ));
            Map<String, Integer> result =
                    TypeWitnessChallenges.challenge8(map);

            List<String> keys = new ArrayList<>(result.keySet());
            // Same value → key ASC: ant,bat,cat
            assertEquals("ant", keys.get(0));
            assertEquals("bat", keys.get(1));
            assertEquals("cat", keys.get(2));
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 5));
            assertEquals(1, TypeWitnessChallenges.challenge8(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(TypeWitnessChallenges.challenge8(
                    new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Sort 2D Array by Row Sum DESC
    //               then Row Length DESC then First Element ASC
    // ⚠️ Type witness needed on BOTH reversed() calls
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            int[][] result = TypeWitnessChallenges.challenge9(
                    new int[][]{{1,2,3},{4,1},{2,3,1},{6},{1,2,3,0}});

            // Sums: [6,5,6,6,6]
            // sum=6: [1,2,3,0] len=4, [1,2,3] len=3, [2,3,1] len=3, [6] len=1
            //   len DESC: 4,3,3,1
            //   len=3 tie: first elem ASC: [1,2,3](1) before [2,3,1](2)
            // sum=5: [4,1] len=2
            assertArrayEquals(new int[]{1, 2, 3, 0}, result[0]); // sum=6 len=4
            assertArrayEquals(new int[]{1, 2, 3},    result[1]); // sum=6 len=3 first=1
            assertArrayEquals(new int[]{2, 3, 1},    result[2]); // sum=6 len=3 first=2
            assertArrayEquals(new int[]{6},           result[3]); // sum=6 len=1
            assertArrayEquals(new int[]{4, 1},        result[4]); // sum=5
        }

        @Test
        void allSameSum() {
            int[][] result = TypeWitnessChallenges.challenge9(
                    new int[][]{{3,0},{1,2},{2,1}});

            // All sum=3 → len DESC: all len=2 → first elem ASC
            assertArrayEquals(new int[]{1, 2}, result[0]); // first=1
            assertArrayEquals(new int[]{2, 1}, result[1]); // first=2
            assertArrayEquals(new int[]{3, 0}, result[2]); // first=3
        }

        @Test
        void differentLengths() {
            int[][] result = TypeWitnessChallenges.challenge9(
                    new int[][]{{5},{2,3},{1,1,3}});

            // All sum=5 → len DESC: 3,2,1
            assertArrayEquals(new int[]{1, 1, 3}, result[0]); // len=3
            assertArrayEquals(new int[]{2, 3},    result[1]); // len=2
            assertArrayEquals(new int[]{5},        result[2]); // len=1
        }

        @Test
        void singleRow() {
            int[][] result = TypeWitnessChallenges.challenge9(
                    new int[][]{{1, 2, 3}});
            assertArrayEquals(new int[]{1, 2, 3}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Sort Books by Rating DESC then Pages DESC
    //                then Title ASC then Author ASC
    // ⚠️ Type witness needed on BOTH rating.reversed() and pages.reversed()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<TypeWitnessChallenges.Book> books;

        @BeforeEach
        void setUp() {
            books = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Book(
                            "Clean Code",     "Martin",  4.5, 431),
                    new TypeWitnessChallenges.Book(
                            "Refactoring",    "Fowler",  4.5, 448),
                    new TypeWitnessChallenges.Book(
                            "Design Patterns","GoF",     4.5, 395),
                    new TypeWitnessChallenges.Book(
                            "The Pragmatic",  "Thomas",  4.8, 352),
                    new TypeWitnessChallenges.Book(
                            "SICP",           "Abelson", 4.7, 657),
                    new TypeWitnessChallenges.Book(
                            "Clean Code 2",   "Martin",  4.5, 431)
            ));
        }

        @Test
        void ratingOrder() {
            List<TypeWitnessChallenges.Book> result =
                    TypeWitnessChallenges.challenge10(books);

            assertEquals(4.8, result.get(0).rating(), 0.001);
            assertEquals(4.7, result.get(1).rating(), 0.001);
            assertEquals(4.5, result.get(2).rating(), 0.001);
            assertEquals(4.5, result.get(3).rating(), 0.001);
            assertEquals(4.5, result.get(4).rating(), 0.001);
            assertEquals(4.5, result.get(5).rating(), 0.001);
        }

        @Test
        void pagesWithinRating() {
            List<TypeWitnessChallenges.Book> result =
                    TypeWitnessChallenges.challenge10(books);

            // rating=4.8
            assertEquals("The Pragmatic",   result.get(0).title());
            // rating=4.7
            assertEquals("SICP",            result.get(1).title());
            // rating=4.5 → pages DESC: 448,431,431,395
            assertEquals("Refactoring",     result.get(2).title()); // 448
            // pages=431 tie → title ASC: Clean Code < Clean Code 2
            assertEquals("Clean Code",      result.get(3).title()); // 431
            assertEquals("Clean Code 2",    result.get(4).title()); // 431
            assertEquals("Design Patterns", result.get(5).title()); // 395
        }

        @Test
        void allSameRatingAndPages() {
            List<TypeWitnessChallenges.Book> same = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Book("Zebra", "Z", 4.5, 300),
                    new TypeWitnessChallenges.Book("Alpha", "A", 4.5, 300),
                    new TypeWitnessChallenges.Book("Mango", "M", 4.5, 300)
            ));
            List<TypeWitnessChallenges.Book> result =
                    TypeWitnessChallenges.challenge10(same);

            // Same rating + pages → title ASC
            assertEquals("Alpha", result.get(0).title());
            assertEquals("Mango", result.get(1).title());
            assertEquals("Zebra", result.get(2).title());
        }

        @Test
        void sameTitleDifferentAuthor() {
            List<TypeWitnessChallenges.Book> same = new ArrayList<>(List.of(
                    new TypeWitnessChallenges.Book("Java", "Smith", 4.5, 300),
                    new TypeWitnessChallenges.Book("Java", "Jones", 4.5, 300),
                    new TypeWitnessChallenges.Book("Java", "Adams", 4.5, 300)
            ));
            List<TypeWitnessChallenges.Book> result =
                    TypeWitnessChallenges.challenge10(same);

            // Same everything → author ASC
            assertEquals("Adams", result.get(0).author());
            assertEquals("Jones", result.get(1).author());
            assertEquals("Smith", result.get(2).author());
        }

        @Test
        void emptyList() {
            assertTrue(TypeWitnessChallenges.challenge10(
                    new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> TypeWitnessChallenges.challenge10(null));
        }
    }
}