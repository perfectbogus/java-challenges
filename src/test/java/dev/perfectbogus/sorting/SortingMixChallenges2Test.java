package dev.perfectbogus.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingMixChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Sort by tax-adjusted net salary DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        private List<SortingMixChallenges2.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Alice",  "Engineering", 120000),
                    new SortingMixChallenges2.Employee("Bob",    "Marketing",    45000),
                    new SortingMixChallenges2.Employee("Carol",  "Engineering",  75000),
                    new SortingMixChallenges2.Employee("Diana",  "HR",           55000),
                    new SortingMixChallenges2.Employee("Eve",    "Marketing",   100000),
                    new SortingMixChallenges2.Employee("Frank",  "Engineering",  70000)
            ));
        }

        @Test
        void basicCase() {
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge1(employees);

            // net: Alice=72000, Eve=70000, Frank=56000, Carol=52500, Diana=44000, Bob=40500
            assertEquals("Alice", result.get(0).name());
            assertEquals("Eve",   result.get(1).name());
            assertEquals("Frank", result.get(2).name());
            assertEquals("Carol", result.get(3).name());
            assertEquals("Diana", result.get(4).name());
            assertEquals("Bob",   result.get(5).name());
        }

        @Test
        void exactBoundary100000TaxBracket() {
            // salary = 100000 → NOT > 100000 → 30% tax → net = 70000
            // salary = 100001 → > 100000 → 40% tax → net = 60000.6
            List<SortingMixChallenges2.Employee> boundary = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("A", "Dept", 100001),
                    new SortingMixChallenges2.Employee("B", "Dept", 100000)
            ));
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge1(boundary);

            // B net=70000 > A net=60000.6 → B first!
            assertEquals("B", result.get(0).name());
            assertEquals("A", result.get(1).name());
        }

        @Test
        void sameNetSalaryUsesName() {
            List<SortingMixChallenges2.Employee> tied = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Zara",  "Dept", 50000),
                    new SortingMixChallenges2.Employee("Alice", "Dept", 50000)
            ));
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge1(tied);

            assertEquals("Alice", result.get(0).name());
            assertEquals("Zara",  result.get(1).name());
        }

        @Test
        void singleEmployee() {
            List<SortingMixChallenges2.Employee> single = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Alice", "Eng", 80000)));
            assertEquals(1, SortingMixChallenges2.challenge1(single).size());
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges2.challenge1(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingMixChallenges2.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Sort Map<String,List<Integer>> by range DESC, sum ASC, key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("alice", List.of(3, 9, 2));     // range=7, sum=14
            map.put("bob",   List.of(5, 5, 5));      // range=0, sum=15
            map.put("carol", List.of(1, 8, 4, 2));  // range=7, sum=15
            map.put("diana", List.of(10, 1));        // range=9, sum=11
            map.put("eve",   List.of(3, 3));         // range=0, sum=6

            List<Map.Entry<String, List<Integer>>> result =
                    SortingMixChallenges2.challenge2(map);

            assertEquals("diana", result.get(0).getKey()); // range=9
            assertEquals("alice", result.get(1).getKey()); // range=7, sum=14
            assertEquals("carol", result.get(2).getKey()); // range=7, sum=15
            assertEquals("eve",   result.get(3).getKey()); // range=0, sum=6
            assertEquals("bob",   result.get(4).getKey()); // range=0, sum=15
        }

        @Test
        void allSameRange() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("z", List.of(1, 5)); // range=4, sum=6
            map.put("a", List.of(2, 6)); // range=4, sum=8

            List<Map.Entry<String, List<Integer>>> result =
                    SortingMixChallenges2.challenge2(map);

            // same range → sum ASC: z(6),a(8)
            assertEquals("z", result.get(0).getKey());
            assertEquals("a", result.get(1).getKey());
        }

        @Test
        void sameRangeSameSum() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("z", List.of(1, 3)); // range=2, sum=4
            map.put("a", List.of(2, 4)); // range=2, sum=6

            List<Map.Entry<String, List<Integer>>> result =
                    SortingMixChallenges2.challenge2(map);

            // same range → sum ASC: z(4) before a(6)
            assertEquals("z", result.get(0).getKey());
            assertEquals("a", result.get(1).getKey());
        }

        @Test
        void singleElement() {
            Map<String, List<Integer>> map = new HashMap<>(Map.of("a", List.of(1, 2)));
            assertEquals(1, SortingMixChallenges2.challenge2(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingMixChallenges2.challenge2(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingMixChallenges2.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Sort 2D by even count DESC, sum ASC, first elem ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            int[][] result = SortingMixChallenges2.challenge3(
                    new int[][]{{3,7,5},{2,4,6},{1,2,3},{8,2,4},{7,1,1}});

            assertArrayEquals(new int[]{2, 4, 6}, result[0]); // evens=3, sum=12
            assertArrayEquals(new int[]{8, 2, 4}, result[1]); // evens=3, sum=14
            assertArrayEquals(new int[]{1, 2, 3}, result[2]); // evens=1
            assertArrayEquals(new int[]{7, 1, 1}, result[3]); // evens=0, sum=9
            assertArrayEquals(new int[]{3, 7, 5}, result[4]); // evens=0, sum=15
        }

        @Test
        void allEven() {
            int[][] result = SortingMixChallenges2.challenge3(
                    new int[][]{{4,2},{6,8},{2,4,6}});

            // all even → sum ASC: [4,2]=6,[6,8]=14,[2,4,6]=12
            assertArrayEquals(new int[]{4, 2}, result[0]);    // sum=6
            assertArrayEquals(new int[]{2,4,6}, result[1]);   // sum=12
            assertArrayEquals(new int[]{6, 8}, result[2]);    // sum=14
        }

        @Test
        void allOdd() {
            int[][] result = SortingMixChallenges2.challenge3(
                    new int[][]{{3,5},{1,3},{7,1}});

            // all even=0 → sum ASC: [1,3]=4,[3,5]=8,[7,1]=8 → first elem ASC for tie
            assertArrayEquals(new int[]{1, 3}, result[0]); // sum=4
            assertArrayEquals(new int[]{3, 5}, result[1]); // sum=8, first=3
            assertArrayEquals(new int[]{7, 1}, result[2]); // sum=8, first=7
        }

        @Test
        void singleRow() {
            int[][] result = SortingMixChallenges2.challenge3(new int[][]{{2, 4, 6}});
            assertArrayEquals(new int[]{2, 4, 6}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingMixChallenges2.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Sort by longest consecutive run DESC, length ASC, alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<String> result = SortingMixChallenges2.challenge4(
                    new ArrayList<>(List.of("aabbbcc","abcde","aaaa","aab","bbccdd","ab")));

            assertEquals("aaaa",    result.get(0)); // run=4
            assertEquals("aabbbcc", result.get(1)); // run=3
            assertEquals("aab",     result.get(2)); // run=2, len=3
            assertEquals("bbccdd",  result.get(3)); // run=2, len=6
            assertEquals("ab",      result.get(4)); // run=1, len=2
            assertEquals("abcde",   result.get(5)); // run=1, len=5
        }

        @Test
        void allRunLengthOne() {
            List<String> result = SortingMixChallenges2.challenge4(
                    new ArrayList<>(List.of("abc","de","fghi")));

            // all run=1 → len ASC: de(2),abc(3),fghi(4)
            assertEquals("de",   result.get(0));
            assertEquals("abc",  result.get(1));
            assertEquals("fghi", result.get(2));
        }

        @Test
        void sameRunSameLength() {
            List<String> result = SortingMixChallenges2.challenge4(
                    new ArrayList<>(List.of("bba","aab")));

            // both run=2, len=3 → alpha: aab,bba
            assertEquals("aab", result.get(0));
            assertEquals("bba", result.get(1));
        }

        @Test
        void singleChar() {
            List<String> result = SortingMixChallenges2.challenge4(
                    new ArrayList<>(List.of("a","bbb","cc")));

            // bbb=3, cc=2, a=1
            assertEquals("bbb", result.get(0));
            assertEquals("cc",  result.get(1));
            assertEquals("a",   result.get(2));
        }

        @Test
        void singleWord() {
            assertEquals(List.of("hello"),
                    SortingMixChallenges2.challenge4(new ArrayList<>(List.of("hello"))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges2.challenge4(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingMixChallenges2.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Sort by salary rank within dept ASC, salary DESC, name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        private List<SortingMixChallenges2.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Alice", "Engineering", 95000),
                    new SortingMixChallenges2.Employee("Bob",   "Marketing",   60000),
                    new SortingMixChallenges2.Employee("Carol", "Engineering", 85000),
                    new SortingMixChallenges2.Employee("Diana", "Marketing",   70000),
                    new SortingMixChallenges2.Employee("Eve",   "Engineering", 95000),
                    new SortingMixChallenges2.Employee("Frank", "HR",          75000)
            ));
        }

        @Test
        void correctRankOrder() {
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge5(employees);

            // rank=1: Alice(95000),Eve(95000),Frank(75000),Diana(70000)
            //         salary DESC: Alice,Eve → name ASC tie; then Frank,Diana
            assertEquals("Alice", result.get(0).name()); // rank=1, salary=95000
            assertEquals("Eve",   result.get(1).name()); // rank=1, salary=95000
            assertEquals("Frank", result.get(2).name()); // rank=1, salary=75000
            assertEquals("Diana", result.get(3).name()); // rank=1, salary=70000
            // rank=2: Bob(60000)
            assertEquals("Bob",   result.get(4).name()); // rank=2
            // rank=3: Carol(85000)
            assertEquals("Carol", result.get(5).name()); // rank=3
        }

        @Test
        void singleDepartment() {
            List<SortingMixChallenges2.Employee> single = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Alice", "Eng", 90000),
                    new SortingMixChallenges2.Employee("Bob",   "Eng", 70000),
                    new SortingMixChallenges2.Employee("Carol", "Eng", 80000)
            ));
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge5(single);

            // rank1=Alice(90000), rank2=Carol(80000), rank3=Bob(70000)
            assertEquals("Alice", result.get(0).name());
            assertEquals("Carol", result.get(1).name());
            assertEquals("Bob",   result.get(2).name());
        }

        @Test
        void allSameSalaryInDept() {
            List<SortingMixChallenges2.Employee> tied = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Zara",  "HR", 80000),
                    new SortingMixChallenges2.Employee("Alice", "HR", 80000),
                    new SortingMixChallenges2.Employee("Mia",   "HR", 80000)
            ));
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge5(tied);

            // all rank=1, same salary → name ASC
            assertEquals("Alice", result.get(0).name());
            assertEquals("Mia",   result.get(1).name());
            assertEquals("Zara",  result.get(2).name());
        }

        @Test
        void sameSalaryAcrossDepartments() {
            List<SortingMixChallenges2.Employee> cross = new ArrayList<>(List.of(
                    new SortingMixChallenges2.Employee("Alice", "Eng", 80000), // rank1 in Eng
                    new SortingMixChallenges2.Employee("Bob",   "Mkt", 80000)  // rank1 in Mkt
            ));
            List<SortingMixChallenges2.Employee> result =
                    SortingMixChallenges2.challenge5(cross);

            // both rank=1, same salary → name ASC
            assertEquals("Alice", result.get(0).name());
            assertEquals("Bob",   result.get(1).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingMixChallenges2.challenge5(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingMixChallenges2.challenge5(null));
        }
    }
}