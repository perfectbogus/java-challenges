package dev.perfectbogus.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingComparatorChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Sort by last char ASC, then length ASC, then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<String> result = SortingComparatorChallenges.challenge1(
                    new ArrayList<>(List.of("banana","apple","grape","fig","mango","date","lime")));

            assertEquals("banana",  result.get(0)); // last='a'
            assertEquals("date",    result.get(1)); // last='e', len=4, d<l
            assertEquals("lime",    result.get(2)); // last='e', len=4, l>d
            assertEquals("apple",   result.get(3)); // last='e', len=5, a<g
            assertEquals("grape",   result.get(4)); // last='e', len=5, g>a
            assertEquals("fig",     result.get(5)); // last='g'
            assertEquals("mango",   result.get(6)); // last='o'
        }

        @Test
        void allSameLastChar() {
            List<String> result = SortingComparatorChallenges.challenge1(
                    new ArrayList<>(List.of("bee","tree","ale")));

            // all last='e' → len ASC: ale(3),bee(3),tree(4) → alpha for len tie
            assertEquals("ale",  result.get(0)); // len=3, a<b
            assertEquals("bee",  result.get(1)); // len=3, b>a
            assertEquals("tree", result.get(2)); // len=4
        }

        @Test
        void allDifferentLastChars() {
            List<String> result = SortingComparatorChallenges.challenge1(
                    new ArrayList<>(List.of("zoo","cat","big")));

            // last: cat='t', big='g', zoo='o' → g,o,t
            assertEquals("big", result.get(0)); // last='g'
            assertEquals("zoo", result.get(1)); // last='o'
            assertEquals("cat", result.get(2)); // last='t'
        }

        @Test
        void singleWord() {
            assertEquals(List.of("hello"),
                    SortingComparatorChallenges.challenge1(new ArrayList<>(List.of("hello"))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingComparatorChallenges.challenge1(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Sort Map entries by combined score (key.length * value) ASC then key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat",      4,
                    "elephant", 1,
                    "dog",      3,
                    "ant",      5,
                    "bear",     2
            ));
            List<Map.Entry<String, Integer>> result = SortingComparatorChallenges.challenge2(map);

            // bear=8, elephant=8, dog=9, cat=12, ant=15
            assertEquals("bear",     result.get(0).getKey()); // score=8, b<e
            assertEquals("elephant", result.get(1).getKey()); // score=8, e>b
            assertEquals("dog",      result.get(2).getKey()); // score=9
            assertEquals("cat",      result.get(3).getKey()); // score=12
            assertEquals("ant",      result.get(4).getKey()); // score=15
        }

        @Test
        void allSameScore() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "bb",  3,   // score=6
                    "aaa", 2,   // score=6
                    "c",   6    // score=6
            ));
            List<Map.Entry<String, Integer>> result = SortingComparatorChallenges.challenge2(map);

            // all score=6 → key alpha ASC: aaa, bb, c
            assertEquals("aaa", result.get(0).getKey());
            assertEquals("bb",  result.get(1).getKey());
            assertEquals("c",   result.get(2).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("cat", 3));
            assertEquals(1, SortingComparatorChallenges.challenge2(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingComparatorChallenges.challenge2(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Sort 2D by product of first*last ASC, then first ASC, then last ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            int[][] result = SortingComparatorChallenges.challenge3(
                    new int[][]{{3,2},{1,6},{2,3},{4,1},{1,4}});

            // products: 6,6,6,4,4
            // product=4: [1,4] first=1, [4,1] first=4 → first ASC
            // product=6: [1,6] first=1, [2,3] first=2, [3,2] first=3
            assertArrayEquals(new int[]{1, 4}, result[0]);
            assertArrayEquals(new int[]{4, 1}, result[1]);
            assertArrayEquals(new int[]{1, 6}, result[2]);
            assertArrayEquals(new int[]{2, 3}, result[3]);
            assertArrayEquals(new int[]{3, 2}, result[4]);
        }

        @Test
        void allSameProduct() {
            int[][] result = SortingComparatorChallenges.challenge3(
                    new int[][]{{3,4},{2,6},{1,12}});

            // all product=12 → first ASC: 1,2,3
            assertArrayEquals(new int[]{1, 12}, result[0]);
            assertArrayEquals(new int[]{2,  6}, result[1]);
            assertArrayEquals(new int[]{3,  4}, result[2]);
        }

        @Test
        void sameProductSameFirst() {
            int[][] result = SortingComparatorChallenges.challenge3(
                    new int[][]{{2,6},{2,4},{2,3}});

            // product: 12,8,6 → ASC: 6,8,12
            assertArrayEquals(new int[]{2, 3}, result[0]); // product=6
            assertArrayEquals(new int[]{2, 4}, result[1]); // product=8
            assertArrayEquals(new int[]{2, 6}, result[2]); // product=12
        }

        @Test
        void singleRow() {
            int[][] result = SortingComparatorChallenges.challenge3(new int[][]{{2, 5}});
            assertArrayEquals(new int[]{2, 5}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Sort employees by name length DESC, name ASC, salary DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        private List<SortingComparatorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Employee("Alice",   "Engineering", 95000),
                    new SortingComparatorChallenges.Employee("Bob",     "Marketing",   60000),
                    new SortingComparatorChallenges.Employee("Charlie", "Engineering", 85000),
                    new SortingComparatorChallenges.Employee("Diana",   "HR",          70000),
                    new SortingComparatorChallenges.Employee("Eve",     "Marketing",   90000)
            ));
        }

        @Test
        void nameLengthOrder() {
            List<SortingComparatorChallenges.Employee> result =
                    SortingComparatorChallenges.challenge4(employees);

            assertEquals("Charlie", result.get(0).name()); // len=7
            // len=5: Alice, Diana → name alpha ASC
            assertEquals("Alice",   result.get(1).name());
            assertEquals("Diana",   result.get(2).name());
            // len=3: Bob, Eve → name alpha ASC
            assertEquals("Bob",     result.get(3).name());
            assertEquals("Eve",     result.get(4).name());
        }

        @Test
        void sameNameLength() {
            List<SortingComparatorChallenges.Employee> same = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Employee("Zara",  "HR",  50000),
                    new SortingComparatorChallenges.Employee("Alice", "Eng", 90000),
                    new SortingComparatorChallenges.Employee("Mia",   "HR",  70000)
            ));
            List<SortingComparatorChallenges.Employee> result =
                    SortingComparatorChallenges.challenge4(same);

            // len=4: Alice, Mia, Zara → name alpha ASC
            assertEquals("Alice", result.get(0).name());
            assertEquals("Mia",   result.get(1).name());
            assertEquals("Zara",  result.get(2).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingComparatorChallenges.challenge4(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Sort by digit count ASC then value ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<Integer> result = SortingComparatorChallenges.challenge5(
                    new ArrayList<>(List.of(350, 7, 42, 1000, 5, 98, 6543, 3)));

            assertEquals(List.of(3, 5, 7, 42, 98, 350, 1000, 6543), result);
        }

        @Test
        void allSameDigitCount() {
            List<Integer> result = SortingComparatorChallenges.challenge5(
                    new ArrayList<>(List.of(99, 11, 55, 33)));

            assertEquals(List.of(11, 33, 55, 99), result);
        }

        @Test
        void singleDigits() {
            List<Integer> result = SortingComparatorChallenges.challenge5(
                    new ArrayList<>(List.of(9, 1, 5)));

            assertEquals(List.of(1, 5, 9), result);
        }

        @Test
        void largeNumbers() {
            List<Integer> result = SortingComparatorChallenges.challenge5(
                    new ArrayList<>(List.of(100000, 1, 100)));

            // digits: 1,3,6 → ASC: 1,100,100000
            assertEquals(List.of(1, 100, 100000), result);
        }

        @Test
        void singleElement() {
            assertEquals(List.of(42),
                    SortingComparatorChallenges.challenge5(new ArrayList<>(List.of(42))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingComparatorChallenges.challenge5(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Sort products by profit margin DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        private List<SortingComparatorChallenges.Product> products;

        @BeforeEach
        void setUp() {
            products = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Product("Phone",  999.0,  600.0),
                    new SortingComparatorChallenges.Product("Shirt",   49.0,   10.0),
                    new SortingComparatorChallenges.Product("Laptop", 1299.0, 900.0),
                    new SortingComparatorChallenges.Product("Book",    29.0,    5.0),
                    new SortingComparatorChallenges.Product("Cable",    9.99,   2.0)
            ));
        }

        @Test
        void marginOrder() {
            List<SortingComparatorChallenges.Product> result =
                    SortingComparatorChallenges.challenge6(products);

            // Book=82.76%, Cable=79.98%, Shirt=79.59%, Phone=39.94%, Laptop=30.72%
            assertEquals("Book",   result.get(0).name());
            assertEquals("Cable",  result.get(1).name());
            assertEquals("Shirt",  result.get(2).name());
            assertEquals("Phone",  result.get(3).name());
            assertEquals("Laptop", result.get(4).name());
        }

        @Test
        void samePriceAndCostUsesName() {
            List<SortingComparatorChallenges.Product> same = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Product("Zebra", 100.0, 50.0),
                    new SortingComparatorChallenges.Product("Alpha", 100.0, 50.0)
            ));
            List<SortingComparatorChallenges.Product> result =
                    SortingComparatorChallenges.challenge6(same);

            // same margin → name ASC
            assertEquals("Alpha", result.get(0).name());
            assertEquals("Zebra", result.get(1).name());
        }

        @Test
        void singleProduct() {
            List<SortingComparatorChallenges.Product> single = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Product("Phone", 999.0, 600.0)));
            assertEquals(1, SortingComparatorChallenges.challenge6(single).size());
        }

        @Test
        void emptyList() {
            assertTrue(SortingComparatorChallenges.challenge6(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Sort Map<String, List<String>> by avg word length DESC then key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            Map<String, List<String>> map = new LinkedHashMap<>();
            map.put("team1", List.of("java","stream","collect"));
            map.put("team2", List.of("go","python"));
            map.put("team3", List.of("javascript","typescript"));
            map.put("team4", List.of("c","cpp","rust","java"));
            map.put("team5", List.of("kotlin","scala"));

            List<Map.Entry<String, List<String>>> result =
                    SortingComparatorChallenges.challenge7(map);

            // avg: team3=10.0,team1=5.67,team5=5.5,team2=4.0,team4=3.0
            assertEquals("team3", result.get(0).getKey());
            assertEquals("team1", result.get(1).getKey());
            assertEquals("team5", result.get(2).getKey());
            assertEquals("team2", result.get(3).getKey());
            assertEquals("team4", result.get(4).getKey());
        }

        @Test
        void tieOnAvgUsesListSize() {
            Map<String, List<String>> map = new LinkedHashMap<>();
            map.put("b", List.of("ab","cd"));      // avg=2.0, size=2
            map.put("a", List.of("ab","cd","ef"));  // avg=2.0, size=3

            List<Map.Entry<String, List<String>>> result =
                    SortingComparatorChallenges.challenge7(map);

            // same avg=2.0 → size ASC: b(2) before a(3)
            assertEquals("b", result.get(0).getKey());
            assertEquals("a", result.get(1).getKey());
        }

        @Test
        void tieOnAvgAndSizeUsesKey() {
            Map<String, List<String>> map = new LinkedHashMap<>();
            map.put("z", List.of("ab","cd")); // avg=2.0, size=2
            map.put("a", List.of("ef","gh")); // avg=2.0, size=2

            List<Map.Entry<String, List<String>>> result =
                    SortingComparatorChallenges.challenge7(map);

            // same avg + size → key alpha ASC: a before z
            assertEquals("a", result.get(0).getKey());
            assertEquals("z", result.get(1).getKey());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingComparatorChallenges.challenge7(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Sort 2D by prime count DESC, row sum ASC, first elem ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            int[][] result = SortingComparatorChallenges.challenge8(
                    new int[][]{{4,6,8},{2,3,5},{7,11,4},{1,9,15},{2,7,11}});

            // primes: 0,3,2,0,3
            assertArrayEquals(new int[]{2,  3,  5}, result[0]); // 3 primes, sum=10
            assertArrayEquals(new int[]{2,  7, 11}, result[1]); // 3 primes, sum=20
            assertArrayEquals(new int[]{7, 11,  4}, result[2]); // 2 primes
            assertArrayEquals(new int[]{4,  6,  8}, result[3]); // 0 primes, sum=18
            assertArrayEquals(new int[]{1,  9, 15}, result[4]); // 0 primes, sum=25
        }

        @Test
        void allNoPrimes() {
            int[][] result = SortingComparatorChallenges.challenge8(
                    new int[][]{{4,6,8},{1,9},{6,4,8}});

            // all 0 primes → sum ASC: {1,9}=10, {4,6,8}=18, {6,4,8}=18
            assertArrayEquals(new int[]{1, 9},    result[0]); // sum=10
            // tie sum=18 → first elem ASC: 4 < 6
            assertArrayEquals(new int[]{4, 6, 8}, result[1]); // sum=18, first=4
            assertArrayEquals(new int[]{6, 4, 8}, result[2]); // sum=18, first=6
        }

        @Test
        void allPrimes() {
            int[][] result = SortingComparatorChallenges.challenge8(
                    new int[][]{{2,3},{5,7,11},{2}});

            // primes: 2,3,1 → DESC: 3,2,1
            assertArrayEquals(new int[]{5, 7, 11}, result[0]); // 3 primes
            assertArrayEquals(new int[]{2,  3},    result[1]); // 2 primes
            assertArrayEquals(new int[]{2},         result[2]); // 1 prime
        }

        @Test
        void singleRow() {
            int[][] result = SortingComparatorChallenges.challenge8(new int[][]{{2, 3, 5}});
            assertArrayEquals(new int[]{2, 3, 5}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Sort employees by dept headcount DESC, salary DESC, name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        private List<SortingComparatorChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Employee("Alice",  "Engineering", 95000),
                    new SortingComparatorChallenges.Employee("Bob",    "Marketing",   60000),
                    new SortingComparatorChallenges.Employee("Carol",  "Engineering", 85000),
                    new SortingComparatorChallenges.Employee("Diana",  "Marketing",   70000),
                    new SortingComparatorChallenges.Employee("Eve",    "Engineering", 92000),
                    new SortingComparatorChallenges.Employee("Frank",  "HR",          75000)
            ));
        }

        @Test
        void headcountOrder() {
            List<SortingComparatorChallenges.Employee> result =
                    SortingComparatorChallenges.challenge9(employees);

            // Engineering(3) → Marketing(2) → HR(1)
            assertEquals("Engineering", result.get(0).department());
            assertEquals("Engineering", result.get(1).department());
            assertEquals("Engineering", result.get(2).department());
            assertEquals("Marketing",   result.get(3).department());
            assertEquals("Marketing",   result.get(4).department());
            assertEquals("HR",          result.get(5).department());
        }

        @Test
        void salaryWithinDept() {
            List<SortingComparatorChallenges.Employee> result =
                    SortingComparatorChallenges.challenge9(employees);

            assertEquals("Alice", result.get(0).name()); // Eng 95000
            assertEquals("Eve",   result.get(1).name()); // Eng 92000
            assertEquals("Carol", result.get(2).name()); // Eng 85000
            assertEquals("Diana", result.get(3).name()); // Mkt 70000
            assertEquals("Bob",   result.get(4).name()); // Mkt 60000
            assertEquals("Frank", result.get(5).name()); // HR  75000
        }

        @Test
        void twoEqualHeadcounts() {
            List<SortingComparatorChallenges.Employee> equal = new ArrayList<>(List.of(
                    new SortingComparatorChallenges.Employee("Alice", "Eng", 90000),
                    new SortingComparatorChallenges.Employee("Bob",   "Mkt", 80000),
                    new SortingComparatorChallenges.Employee("Carol", "Eng", 70000),
                    new SortingComparatorChallenges.Employee("Diana", "Mkt", 60000)
            ));
            List<SortingComparatorChallenges.Employee> result =
                    SortingComparatorChallenges.challenge9(equal);

            // Both depts have 2 → same headcount → within each: salary DESC
            // Eng: Alice(90000), Carol(70000)
            // Mkt: Bob(80000), Diana(60000)
            // dept order when headcount tied → salary of first person or dept name?
            // headcount=2 for both → salary DESC across: Alice(90000),Bob(80000),Carol(70000),Diana(60000)
            assertEquals(4, result.size());
        }

        @Test
        void emptyList() {
            assertTrue(SortingComparatorChallenges.challenge9(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Sort by vowel ratio DESC, length ASC, alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            List<String> result = SortingComparatorChallenges.challenge10(
                    new ArrayList<>(List.of("rhythm","queue","hello","aeiou","cat","beautiful","gym")));

            // aeiou=1.0, queue=0.8, beautiful=0.556, hello=0.4, cat=0.333, gym=0.0, rhythm=0.0
            assertEquals("aeiou",     result.get(0));
            assertEquals("queue",     result.get(1));
            assertEquals("beautiful", result.get(2));
            assertEquals("hello",     result.get(3));
            assertEquals("cat",       result.get(4));
            assertEquals("gym",       result.get(5)); // ratio=0, len=3
            assertEquals("rhythm",    result.get(6)); // ratio=0, len=6
        }

        @Test
        void allNoVowels() {
            List<String> result = SortingComparatorChallenges.challenge10(
                    new ArrayList<>(List.of("cry","gym","rhythm")));

            // all ratio=0 → len ASC: cry(3),gym(3),rhythm(6) → alpha for len tie
            assertEquals("cry",    result.get(0)); // len=3, c<g
            assertEquals("gym",    result.get(1)); // len=3, g>c
            assertEquals("rhythm", result.get(2)); // len=6
        }

        @Test
        void allVowels() {
            List<String> result = SortingComparatorChallenges.challenge10(
                    new ArrayList<>(List.of("ai","aeiou","oe")));

            // all ratio=1.0 → len ASC: ai(2),oe(2),aeiou(5) → alpha for len tie
            assertEquals("ai",    result.get(0)); // len=2, a<o
            assertEquals("oe",    result.get(1)); // len=2, o>a
            assertEquals("aeiou", result.get(2)); // len=5
        }

        @Test
        void sameRatioDifferentLength() {
            // "io"=1.0 len=2, "aeiou"=1.0 len=5 → len ASC: io,aeiou
            List<String> result = SortingComparatorChallenges.challenge10(
                    new ArrayList<>(List.of("aeiou","io")));

            assertEquals("io",    result.get(0));
            assertEquals("aeiou", result.get(1));
        }

        @Test
        void singleWord() {
            assertEquals(List.of("hello"),
                    SortingComparatorChallenges.challenge10(new ArrayList<>(List.of("hello"))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingComparatorChallenges.challenge10(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingComparatorChallenges.challenge10(null));
        }
    }
}