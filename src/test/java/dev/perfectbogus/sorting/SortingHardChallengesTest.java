package dev.perfectbogus.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingHardChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — 🟡 Sort employees by department total DESC then salary DESC then name ASC
    // Key: precompute dept totals → use in comparator
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        private List<SortingHardChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingHardChallenges.Employee("Alice",  "Engineering", 95000),
                    new SortingHardChallenges.Employee("Bob",    "Marketing",   60000),
                    new SortingHardChallenges.Employee("Carol",  "Engineering", 85000),
                    new SortingHardChallenges.Employee("Diana",  "Marketing",   70000),
                    new SortingHardChallenges.Employee("Eve",    "Engineering", 92000)
            ));
        }

        @Test
        void deptTotalOrder() {
            List<SortingHardChallenges.Employee> result = SortingHardChallenges.challenge1(employees);

            // First 3 → Engineering (total=272000)
            assertEquals("Engineering", result.get(0).department());
            assertEquals("Engineering", result.get(1).department());
            assertEquals("Engineering", result.get(2).department());
            // Last 2 → Marketing (total=130000)
            assertEquals("Marketing",   result.get(3).department());
            assertEquals("Marketing",   result.get(4).department());
        }

        @Test
        void salaryWithinDept() {
            List<SortingHardChallenges.Employee> result = SortingHardChallenges.challenge1(employees);

            assertEquals("Alice", result.get(0).name()); // 95000
            assertEquals("Eve",   result.get(1).name()); // 92000
            assertEquals("Carol", result.get(2).name()); // 85000
            assertEquals("Diana", result.get(3).name()); // 70000
            assertEquals("Bob",   result.get(4).name()); // 60000
        }

        @Test
        void singleDepartment() {
            List<SortingHardChallenges.Employee> single = new ArrayList<>(List.of(
                    new SortingHardChallenges.Employee("Bob",   "HR", 60000),
                    new SortingHardChallenges.Employee("Alice", "HR", 80000)
            ));
            List<SortingHardChallenges.Employee> result = SortingHardChallenges.challenge1(single);

            assertEquals("Alice", result.get(0).name()); // higher salary
            assertEquals("Bob",   result.get(1).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingHardChallenges.challenge1(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — 🟡 Sort words by Scrabble score DESC then length DESC then alpha ASC
    // Key: letter value map + computed score
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            List<String> result = SortingHardChallenges.challenge2(
                    new ArrayList<>(List.of("jazz","hello","quick","fox","cat")));

            // jazz=29, quick=20, fox=13, hello=8, cat=5
            assertEquals("jazz",  result.get(0));
            assertEquals("quick", result.get(1));
            assertEquals("fox",   result.get(2));
            assertEquals("hello", result.get(3));
            assertEquals("cat",   result.get(4));
        }

        @Test
        void sameScoredifferentLength() {
            // if two words have same score → length DESC
            List<String> result = SortingHardChallenges.challenge2(
                    new ArrayList<>(List.of("cat","act","cats")));

            // cat=5, act=5, cats=6
            assertEquals("cats", result.get(0)); // score=6
            // cat and act both score=5, length=3 → alpha ASC: act, cat
            assertEquals("act",  result.get(1));
            assertEquals("cat",  result.get(2));
        }

        @Test
        void allSameScore() {
            // all single vowels score=1
            List<String> result = SortingHardChallenges.challenge2(
                    new ArrayList<>(List.of("u","o","a","e","i")));

            // same score=1, same length=1 → alpha ASC
            assertEquals("a", result.get(0));
            assertEquals("e", result.get(1));
            assertEquals("i", result.get(2));
            assertEquals("o", result.get(3));
            assertEquals("u", result.get(4));
        }

        @Test
        void highValueLetters() {
            List<String> result = SortingHardChallenges.challenge2(
                    new ArrayList<>(List.of("quiz","box","jam")));

            // quiz = q(10)+u(1)+i(1)+z(10) = 22
            // box  = b(3)+o(1)+x(8) = 12
            // jam  = j(8)+a(1)+m(3) = 12
            // score=22: quiz
            // score=12: box,jam → same length → alpha: box,jam
            assertEquals("quiz", result.get(0));
            assertEquals("box",  result.get(1));
            assertEquals("jam",  result.get(2));
        }

        @Test
        void singleWord() {
            List<String> result = SortingHardChallenges.challenge2(new ArrayList<>(List.of("hello")));
            assertEquals(List.of("hello"), result);
        }

        @Test
        void emptyList() {
            assertTrue(SortingHardChallenges.challenge2(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — 🟡 Sort Map<String, List<Integer>> by size DESC, sum ASC, key ASC
    // Key: getValue().size() and getValue().stream().sum() inside comparator
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("alice",   List.of(3, 1, 4));   // size=3, sum=8
            map.put("bob",     List.of(1, 5));       // size=2, sum=6
            map.put("charlie", List.of(9, 2, 6));   // size=3, sum=17
            map.put("diana",   List.of(2, 7, 1));   // size=3, sum=10
            map.put("eve",     List.of(5, 5));       // size=2, sum=10

            List<Map.Entry<String, List<Integer>>> result = SortingHardChallenges.challenge3(map);

            // size=3: alice(8),diana(10),charlie(17) → sum ASC
            assertEquals("alice",   result.get(0).getKey());
            assertEquals("diana",   result.get(1).getKey());
            assertEquals("charlie", result.get(2).getKey());
            // size=2: bob(6),eve(10) → sum ASC
            assertEquals("bob",     result.get(3).getKey());
            assertEquals("eve",     result.get(4).getKey());
        }

        @Test
        void allSameSize() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("cat", List.of(1, 2));   // sum=3
            map.put("ant", List.of(3, 4));   // sum=7
            map.put("bat", List.of(2, 3));   // sum=5

            List<Map.Entry<String, List<Integer>>> result = SortingHardChallenges.challenge3(map);

            // Same size=2 → sum ASC: cat(3),bat(5),ant(7)
            assertEquals("cat", result.get(0).getKey());
            assertEquals("bat", result.get(1).getKey());
            assertEquals("ant", result.get(2).getKey());
        }

        @Test
        void sameSizeSameSum() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("zebra", List.of(1, 2)); // size=2, sum=3
            map.put("alpha", List.of(2, 1)); // size=2, sum=3
            map.put("mango", List.of(3, 0)); // size=2, sum=3

            List<Map.Entry<String, List<Integer>>> result = SortingHardChallenges.challenge3(map);

            // Same size + sum → key ASC: alpha, mango, zebra
            assertEquals("alpha", result.get(0).getKey());
            assertEquals("mango", result.get(1).getKey());
            assertEquals("zebra", result.get(2).getKey());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingHardChallenges.challenge3(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — 🟡 Sort words by frequency DESC, length ASC, alpha ASC
    // Key: precompute frequency map from the list itself
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<String> result = SortingHardChallenges.challenge4(
                    List.of("apple","banana","apple","cherry","banana","apple","date","cherry"));

            // apple=3, banana=2, cherry=2, date=1
            // banana and cherry both freq=2, same length=6 → alpha: banana, cherry
            assertEquals("apple",  result.get(0)); // freq=3
            assertEquals("banana", result.get(1)); // freq=2, len=6, b<c
            assertEquals("cherry", result.get(2)); // freq=2, len=6, c>b
            assertEquals("date",   result.get(3)); // freq=1
        }

        @Test
        void allSameFrequency() {
            List<String> result = SortingHardChallenges.challenge4(
                    List.of("cat","dog","ant"));

            // All freq=1 → length ASC (all 3) → alpha: ant,cat,dog
            assertEquals("ant", result.get(0));
            assertEquals("cat", result.get(1));
            assertEquals("dog", result.get(2));
        }

        @Test
        void sameFreqDifferentLength() {
            List<String> result = SortingHardChallenges.challenge4(
                    List.of("hi","hi","hello","hello"));

            // hi=2, hello=2, same freq → length ASC: hi(2), hello(5)
            assertEquals("hi",    result.get(0));
            assertEquals("hello", result.get(1));
        }

        @Test
        void singleWord() {
            List<String> result = SortingHardChallenges.challenge4(List.of("apple","apple","apple"));
            assertEquals(List.of("apple"), result);
        }

        @Test
        void emptyList() {
            assertTrue(SortingHardChallenges.challenge4(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — 🔴 Sort intervals by overlap count DESC then start ASC then end ASC
    // Key: precompute overlap count per interval using IdentityHashMap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            int[][] result = SortingHardChallenges.challenge5(
                    new int[][]{{1,5},{2,6},{8,10},{3,4},{7,9}});

            // [1,5]=2, [2,6]=2, [3,4]=2, [7,9]=1, [8,10]=1
            // count=2: [1,5],[2,6],[3,4] → start ASC
            assertArrayEquals(new int[]{1, 5},  result[0]);
            assertArrayEquals(new int[]{2, 6},  result[1]);
            assertArrayEquals(new int[]{3, 4},  result[2]);
            // count=1: [7,9],[8,10] → start ASC
            assertArrayEquals(new int[]{7, 9},  result[3]);
            assertArrayEquals(new int[]{8, 10}, result[4]);
        }

        @Test
        void noOverlaps() {
            int[][] result = SortingHardChallenges.challenge5(
                    new int[][]{{5,6},{1,2},{3,4}});

            // All count=0 → start ASC
            assertArrayEquals(new int[]{1, 2}, result[0]);
            assertArrayEquals(new int[]{3, 4}, result[1]);
            assertArrayEquals(new int[]{5, 6}, result[2]);
        }

        @Test
        void allOverlapEachOther() {
            int[][] result = SortingHardChallenges.challenge5(
                    new int[][]{{1,10},{2,9},{3,8}});

            // All overlap with 2 others → count=2 for all → start ASC
            assertArrayEquals(new int[]{1, 10}, result[0]);
            assertArrayEquals(new int[]{2,  9}, result[1]);
            assertArrayEquals(new int[]{3,  8}, result[2]);
        }

        @Test
        void singleInterval() {
            int[][] result = SortingHardChallenges.challenge5(new int[][]{{1, 5}});
            assertArrayEquals(new int[]{1, 5}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — 🔴 Sort by salary relative to dept avg (above avg first, by diff DESC)
    // Key: precompute dept averages → compute diffs → sort with above/below partition
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        private List<SortingHardChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingHardChallenges.Employee("Alice",  "Engineering", 95000),  // deptAvg=90667, diff=+4333
                    new SortingHardChallenges.Employee("Bob",    "Marketing",   60000),  // deptAvg=65000,  diff=-5000
                    new SortingHardChallenges.Employee("Carol",  "Engineering", 85000),  // diff=-5667
                    new SortingHardChallenges.Employee("Diana",  "Marketing",   70000),  // diff=+5000
                    new SortingHardChallenges.Employee("Eve",    "Engineering", 92000)   // diff=+1333
            ));
        }

        @Test
        void aboveAverageFirst() {
            List<SortingHardChallenges.Employee> result = SortingHardChallenges.challenge6(employees);

            // Above avg: Diana(+5000), Alice(+4333), Eve(+1333) → diff DESC
            // Below avg: Bob(-5000), Carol(-5667) → diff DESC (least negative first)
            assertEquals("Diana", result.get(0).name()); // +5000 highest diff
            assertEquals("Alice", result.get(1).name()); // +4333
            assertEquals("Eve",   result.get(2).name()); // +1333
            assertEquals("Bob",   result.get(3).name()); // -5000 (less negative)
            assertEquals("Carol", result.get(4).name()); // -5667 (more negative)
        }

        @Test
        void allAboveAverage() {
            List<SortingHardChallenges.Employee> all = new ArrayList<>(List.of(
                    new SortingHardChallenges.Employee("Alice", "Eng", 100000), // avg=85000, diff=+15000
                    new SortingHardChallenges.Employee("Bob",   "Eng",  80000), // diff=-5000
                    new SortingHardChallenges.Employee("Carol", "Eng",  75000)  // diff=-10000
            ));
            List<SortingHardChallenges.Employee> result = SortingHardChallenges.challenge6(all);

            // Above: Alice; Below: Bob, Carol → Bob before Carol (less negative)
            assertEquals("Alice", result.get(0).name());
            assertEquals("Bob",   result.get(1).name());
            assertEquals("Carol", result.get(2).name());
        }

        @Test
        void tieOnDifferenceUsesName() {
            List<SortingHardChallenges.Employee> tied = new ArrayList<>(List.of(
                    new SortingHardChallenges.Employee("Zara",  "HR", 80000),
                    new SortingHardChallenges.Employee("Alice", "HR", 80000),
                    new SortingHardChallenges.Employee("Mia",   "HR", 80000)
            ));
            List<SortingHardChallenges.Employee> result = SortingHardChallenges.challenge6(tied);

            // All at avg (diff=0) → name ASC
            assertEquals("Alice", result.get(0).name());
            assertEquals("Mia",   result.get(1).name());
            assertEquals("Zara",  result.get(2).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingHardChallenges.challenge6(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — 🟡 Sort 2D by variance DESC then sum ASC then first element ASC
    // Key: compute variance per row with IdentityHashMap precomputation
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            int[][] result = SortingHardChallenges.challenge7(
                    new int[][]{{1,2,3},{4,4,4},{1,5,9},{2,3,4},{7,1,1}});

            // variances: 0.667, 0.0, 10.667, 0.667, 8.0
            assertArrayEquals(new int[]{1, 5, 9}, result[0]); // var=10.667
            assertArrayEquals(new int[]{7, 1, 1}, result[1]); // var=8.0
            // var=0.667 tie: [1,2,3]=sum6, [2,3,4]=sum9 → sum ASC: [1,2,3],[2,3,4]
            assertArrayEquals(new int[]{1, 2, 3}, result[2]); // var=0.667, sum=6
            assertArrayEquals(new int[]{2, 3, 4}, result[3]); // var=0.667, sum=9
            assertArrayEquals(new int[]{4, 4, 4}, result[4]); // var=0.0
        }

        @Test
        void allZeroVariance() {
            int[][] result = SortingHardChallenges.challenge7(
                    new int[][]{{5,5,5},{2,2,2},{8,8,8}});

            // All var=0 → sum ASC: 6,15,24
            assertArrayEquals(new int[]{2, 2, 2}, result[0]); // sum=6
            assertArrayEquals(new int[]{5, 5, 5}, result[1]); // sum=15
            assertArrayEquals(new int[]{8, 8, 8}, result[2]); // sum=24
        }

        @Test
        void singleRow() {
            int[][] result = SortingHardChallenges.challenge7(new int[][]{{1, 2, 3}});
            assertArrayEquals(new int[]{1, 2, 3}, result[0]);
        }

        @Test
        void twoRowsTieOnVarianceAndSum() {
            int[][] result = SortingHardChallenges.challenge7(
                    new int[][]{{3,1,2},{1,2,3}});

            // Both have same variance and same sum → sort by first element ASC
            assertArrayEquals(new int[]{1, 2, 3}, result[0]); // first=1
            assertArrayEquals(new int[]{3, 1, 2}, result[1]); // first=3
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — 🔴 Sort words by custom alphabet order then length ASC
    // Key: build charOrder map from alphabet string, compare char by char
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void reversedAlphabet() {
            String alphabet = "zyxwvutsrqponmlkjihgfedcba";
            List<String> result = SortingHardChallenges.challenge8(
                    new ArrayList<>(List.of("apple","zoo","ant","zebra","ax")), alphabet);

            // z<y<...<a in custom order
            // zoo   starts z(0)
            // zebra starts z(0), then e(21) vs o(11): o<e → zoo before zebra
            // ax    starts a(25) → last group
            // ant   starts a(25), then n(12) vs x(2): x<n → ax before ant
            // apple starts a(25), then p(10) vs x(2): x<p → ax before apple, but ant vs apple: n(12)>p(10)? p comes before n
            assertEquals("zoo",   result.get(0));
            assertEquals("zebra", result.get(1));
            assertEquals("ax",    result.get(2));
            assertEquals("apple", result.get(3));
            assertEquals("ant",   result.get(4));
        }

        @Test
        void naturalAlphabet() {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            List<String> result = SortingHardChallenges.challenge8(
                    new ArrayList<>(List.of("banana","apple","cherry")), alphabet);

            assertEquals("apple",  result.get(0));
            assertEquals("banana", result.get(1));
            assertEquals("cherry", result.get(2));
        }

        @Test
        void prefixWordFirst() {
            // "cat" is prefix of "cats" → shorter first
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            List<String> result = SortingHardChallenges.challenge8(
                    new ArrayList<>(List.of("cats","cat")), alphabet);

            assertEquals("cat",  result.get(0));
            assertEquals("cats", result.get(1));
        }

        @Test
        void singleWord() {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            List<String> result = SortingHardChallenges.challenge8(
                    new ArrayList<>(List.of("hello")), alphabet);
            assertEquals(List.of("hello"), result);
        }

        @Test
        void emptyList() {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            assertTrue(SortingHardChallenges.challenge8(new ArrayList<>(), alphabet).isEmpty());
        }

        @Test
        void nullWords() {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            assertThrows(IllegalArgumentException.class,
                    () -> SortingHardChallenges.challenge8(null, alphabet));
        }

        @Test
        void nullAlphabet() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingHardChallenges.challenge8(new ArrayList<>(List.of("hello")), null));
        }

        @Test
        void invalidAlphabetLength() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingHardChallenges.challenge8(new ArrayList<>(List.of("hello")), "abc"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — 🔴 Dynamic comparator built from list of criteria strings
    // Key: parse "field:DIRECTION" → build comparator chain dynamically
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        private List<SortingHardChallenges.DynEmployee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingHardChallenges.DynEmployee("Alice",  "Engineering", 95000, 5),
                    new SortingHardChallenges.DynEmployee("Bob",    "Marketing",   60000, 8),
                    new SortingHardChallenges.DynEmployee("Carol",  "Engineering", 85000, 3),
                    new SortingHardChallenges.DynEmployee("Diana",  "HR",          70000, 8),
                    new SortingHardChallenges.DynEmployee("Eve",    "Engineering", 92000, 5)
            ));
        }

        @Test
        void sortByDeptAscSalaryDescNameAsc() {
            List<SortingHardChallenges.DynEmployee> result = SortingHardChallenges.challenge9(
                    employees, List.of("department:ASC", "salary:DESC", "name:ASC"));

            // dept ASC: Engineering(3), HR(1), Marketing(1)
            assertEquals("Alice",  result.get(0).name()); // Eng 95000
            assertEquals("Eve",    result.get(1).name()); // Eng 92000
            assertEquals("Carol",  result.get(2).name()); // Eng 85000
            assertEquals("Diana",  result.get(3).name()); // HR  70000
            assertEquals("Bob",    result.get(4).name()); // Mkt 60000
        }

        @Test
        void sortBySalaryDesc() {
            List<SortingHardChallenges.DynEmployee> result = SortingHardChallenges.challenge9(
                    employees, List.of("salary:DESC"));

            assertEquals("Alice", result.get(0).name()); // 95000
            assertEquals("Eve",   result.get(1).name()); // 92000
            assertEquals("Carol", result.get(2).name()); // 85000
            assertEquals("Diana", result.get(3).name()); // 70000
            assertEquals("Bob",   result.get(4).name()); // 60000
        }

        @Test
        void sortByYearsDescNameAsc() {
            List<SortingHardChallenges.DynEmployee> result = SortingHardChallenges.challenge9(
                    employees, List.of("yearsOfExperience:DESC", "name:ASC"));

            // years DESC: Bob=8,Diana=8 → name ASC: Bob,Diana; Alice=5,Eve=5 → Alice,Eve; Carol=3
            assertEquals("Bob",   result.get(0).name());
            assertEquals("Diana", result.get(1).name());
            assertEquals("Alice", result.get(2).name());
            assertEquals("Eve",   result.get(3).name());
            assertEquals("Carol", result.get(4).name());
        }

        @Test
        void sortByNameAsc() {
            List<SortingHardChallenges.DynEmployee> result = SortingHardChallenges.challenge9(
                    employees, List.of("name:ASC"));

            assertEquals("Alice", result.get(0).name());
            assertEquals("Bob",   result.get(1).name());
            assertEquals("Carol", result.get(2).name());
            assertEquals("Diana", result.get(3).name());
            assertEquals("Eve",   result.get(4).name());
        }

        @Test
        void emptyCriteria() {
            List<SortingHardChallenges.DynEmployee> result = SortingHardChallenges.challenge9(
                    employees, List.of());
            assertEquals(5, result.size()); // no sort, just return as is
        }

        @Test
        void emptyList() {
            assertTrue(SortingHardChallenges.challenge9(new ArrayList<>(),
                    List.of("salary:DESC")).isEmpty());
        }

        @Test
        void nullEmployees() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingHardChallenges.challenge9(null, List.of("salary:DESC")));
        }

        @Test
        void nullCriteria() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingHardChallenges.challenge9(employees, null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — 🔴 Sort students by percentile rank within class DESC then name ASC
    // Key: compute percentile per student using class scores
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<SortingHardChallenges.Student> students;

        @BeforeEach
        void setUp() {
            students = new ArrayList<>(List.of(
                    new SortingHardChallenges.Student("Alice",  "ClassA", 90),
                    new SortingHardChallenges.Student("Bob",    "ClassA", 75),
                    new SortingHardChallenges.Student("Carol",  "ClassA", 90),
                    new SortingHardChallenges.Student("Diana",  "ClassB", 80),
                    new SortingHardChallenges.Student("Eve",    "ClassB", 95),
                    new SortingHardChallenges.Student("Frank",  "ClassB", 80)
            ));
        }

        @Test
        void correctOrder() {
            List<SortingHardChallenges.Student> result = SortingHardChallenges.challenge10(students);

            // Eve=66.67%, Alice=33.33%, Carol=33.33%, Bob=0%, Diana=0%, Frank=0%
            assertEquals("Eve",   result.get(0).name()); // 66.67%
            // 33.33% tie: Alice, Carol → name ASC
            assertEquals("Alice", result.get(1).name());
            assertEquals("Carol", result.get(2).name());
            // 0% tie: Bob, Diana, Frank → name ASC
            assertEquals("Bob",   result.get(3).name());
            assertEquals("Diana", result.get(4).name());
            assertEquals("Frank", result.get(5).name());
        }

        @Test
        void allSameScore() {
            List<SortingHardChallenges.Student> same = new ArrayList<>(List.of(
                    new SortingHardChallenges.Student("Zara",  "ClassA", 80),
                    new SortingHardChallenges.Student("Alice", "ClassA", 80),
                    new SortingHardChallenges.Student("Mia",   "ClassA", 80)
            ));
            List<SortingHardChallenges.Student> result = SortingHardChallenges.challenge10(same);

            // All percentile=0% → name ASC
            assertEquals("Alice", result.get(0).name());
            assertEquals("Mia",   result.get(1).name());
            assertEquals("Zara",  result.get(2).name());
        }

        @Test
        void allDifferentScores() {
            List<SortingHardChallenges.Student> diff = new ArrayList<>(List.of(
                    new SortingHardChallenges.Student("Alice", "ClassA", 70),
                    new SortingHardChallenges.Student("Bob",   "ClassA", 80),
                    new SortingHardChallenges.Student("Carol", "ClassA", 90)
            ));
            List<SortingHardChallenges.Student> result = SortingHardChallenges.challenge10(diff);

            // Carol=66.67%, Bob=33.33%, Alice=0%
            assertEquals("Carol", result.get(0).name());
            assertEquals("Bob",   result.get(1).name());
            assertEquals("Alice", result.get(2).name());
        }

        @Test
        void singleStudent() {
            List<SortingHardChallenges.Student> single = new ArrayList<>(List.of(
                    new SortingHardChallenges.Student("Alice", "ClassA", 90)));
            List<SortingHardChallenges.Student> result = SortingHardChallenges.challenge10(single);

            assertEquals(1, result.size());
            assertEquals("Alice", result.get(0).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingHardChallenges.challenge10(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingHardChallenges.challenge10(null));
        }
    }
}