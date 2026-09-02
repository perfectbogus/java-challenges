package dev.perfectbogus.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Total compensation DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        private List<SortingChallenges.Employee> employees;
        private Map<String, Double> bonusMap;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingChallenges.Employee("Alice", "Eng", 80000),
                    new SortingChallenges.Employee("Bob",   "Mkt", 60000),
                    new SortingChallenges.Employee("Carol", "Eng", 75000),
                    new SortingChallenges.Employee("Diana", "HR",  90000)
            ));
            bonusMap = Map.of("Alice", 20000.0, "Carol", 10000.0, "Diana", 5000.0);
        }

        @Test
        void basicCase() {
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge1(employees, bonusMap);

            assertEquals("Alice", result.get(0).name()); // 80000+20000=100000
            assertEquals("Diana", result.get(1).name()); // 90000+5000=95000
            assertEquals("Carol", result.get(2).name()); // 75000+10000=85000
            assertEquals("Bob",   result.get(3).name()); // 60000+0=60000
        }

        @Test
        void noBonusForAnyone() {
            Map<String, Double> empty = new HashMap<>();
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge1(employees, empty);

            // salary only → Diana(90000),Alice(80000),Carol(75000),Bob(60000)
            assertEquals("Diana", result.get(0).name());
            assertEquals("Alice", result.get(1).name());
        }

        @Test
        void sameCompensationUsesName() {
            List<SortingChallenges.Employee> tied = new ArrayList<>(List.of(
                    new SortingChallenges.Employee("Zara",  "HR", 100000),
                    new SortingChallenges.Employee("Alice", "HR", 100000)
            ));
            Map<String, Double> noBonus = new HashMap<>();
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge1(tied, noBonus);

            assertEquals("Alice", result.get(0).name()); // alpha first
            assertEquals("Zara",  result.get(1).name());
        }

        @Test
        void emptyEmployees() {
            assertTrue(SortingChallenges.challenge1(new ArrayList<>(), bonusMap).isEmpty());
        }

        @Test
        void nullEmployees() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge1(null, bonusMap));
        }

        @Test
        void nullBonusMap() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge1(employees, null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Map by median DESC then sum ASC then key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("alice", List.of(3,1,4,1,5)); // median=3.0, sum=14
            map.put("bob",   List.of(2,8));         // median=5.0, sum=10
            map.put("carol", List.of(7,3,7));       // median=7.0, sum=17
            map.put("diana", List.of(4,4,4,4));     // median=4.0, sum=16
            map.put("eve",   List.of(1,9));         // median=5.0, sum=10

            List<Map.Entry<String, List<Integer>>> result =
                    SortingChallenges.challenge2(map);

            assertEquals("carol", result.get(0).getKey()); // median=7.0
            assertEquals("bob",   result.get(1).getKey()); // median=5.0,sum=10,b<e
            assertEquals("eve",   result.get(2).getKey()); // median=5.0,sum=10,e>b
            assertEquals("diana", result.get(3).getKey()); // median=4.0
            assertEquals("alice", result.get(4).getKey()); // median=3.0
        }

        @Test
        void basicCase2_2() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("alice", List.of(3,1,4,1,5)); // median=3.0, sum=14
            map.put("bob",   List.of(2,8));         // median=5.0, sum=10
            map.put("carol", List.of(7,3,7));       // median=7.0, sum=17
            map.put("diana", List.of(4,4,4,4));     // median=4.0, sum=16
            map.put("eve",   List.of(1,9));         // median=5.0, sum=10

            List<Map.Entry<String, List<Integer>>> result =
                    SortingChallenges.challenge2_2(map);

            assertEquals("carol", result.get(0).getKey()); // median=7.0
            assertEquals("bob",   result.get(1).getKey()); // median=5.0,sum=10,b<e
            assertEquals("eve",   result.get(2).getKey()); // median=5.0,sum=10,e>b
            assertEquals("diana", result.get(3).getKey()); // median=4.0
            assertEquals("alice", result.get(4).getKey()); // median=3.0
        }

        @Test
        void oddSizeMedian() {
            Map<String, List<Integer>> map = new HashMap<>(Map.of(
                    "a", List.of(1,2,3,4,5), // sorted=[1,2,3,4,5] median=3
                    "b", List.of(5,4,3,2,1)  // sorted=[1,2,3,4,5] median=3
            ));
            List<Map.Entry<String, List<Integer>>> result =
                    SortingChallenges.challenge2(map);

            // same median AND same sum → key ASC: a,b
            assertEquals("a", result.get(0).getKey());
            assertEquals("b", result.get(1).getKey());
        }

        @Test
        void evenSizeMedian() {
            Map<String, List<Integer>> map = new HashMap<>(Map.of(
                    "x", List.of(1,3), // median=(1+3)/2=2.0
                    "y", List.of(2,6)  // median=(2+6)/2=4.0
            ));
            List<Map.Entry<String, List<Integer>>> result =
                    SortingChallenges.challenge2(map);

            assertEquals("y", result.get(0).getKey()); // median=4.0
            assertEquals("x", result.get(1).getKey()); // median=2.0
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Scrabble score DESC then length ASC then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            List<String> result = SortingChallenges.challenge3(
                    new ArrayList<>(List.of("java","quiz","hello","box","cat")));

            assertEquals("quiz",  result.get(0)); // 22
            assertEquals("java",  result.get(1)); // 14
            assertEquals("box",   result.get(2)); // 12
            assertEquals("hello", result.get(3)); // 8
            assertEquals("cat",   result.get(4)); // 5
        }

        @Test
        void sameScoreLengthTiebreaker() {
            // "ax" = A(1)+X(8)=9, len=2
            // "za" = Z(10)+A(1)=11... different. Let me use:
            // "it" = I(1)+T(1)=2, len=2
            // "in" = I(1)+N(1)=2, len=2 → same score+len → alpha: in,it
            List<String> result = SortingChallenges.challenge3(
                    new ArrayList<>(List.of("it","in")));

            assertEquals("in", result.get(0)); // alpha first
            assertEquals("it", result.get(1));
        }

        @Test
        void caseInsensitive() {
            // "JAVA" same score as "java"
            List<String> result = SortingChallenges.challenge3(
                    new ArrayList<>(List.of("JAVA","java")));

            // same score(14) same length(4) → alpha: JAVA before java
            assertEquals("JAVA", result.get(0));
            assertEquals("java", result.get(1));
        }

        @Test
        void singleWord() {
            assertEquals(List.of("hello"),
                    SortingChallenges.challenge3(new ArrayList<>(List.of("hello"))));
        }

        @Test
        void emptyList() {
            assertTrue(SortingChallenges.challenge3(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — 2D by weighted score DESC then score DESC then weight ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            int[][] result = SortingChallenges.challenge4(
                    new int[][]{{5,3},{4,4},{6,2},{3,5},{4,4},{8,1}});

            // weighted: 15,16,12,15,16,8
            // weighted=16: [4,4],[4,4] (equal)
            // weighted=15: [5,3] score>3→first, [3,5]
            // weighted=12: [6,2]
            // weighted=8:  [8,1]
            assertArrayEquals(new int[]{4,4}, result[0]);
            assertArrayEquals(new int[]{4,4}, result[1]);
            assertArrayEquals(new int[]{5,3}, result[2]);
            assertArrayEquals(new int[]{3,5}, result[3]);
            assertArrayEquals(new int[]{6,2}, result[4]);
            assertArrayEquals(new int[]{8,1}, result[5]);
        }

        @Test
        void singleRow() {
            int[][] result = SortingChallenges.challenge4(new int[][]{{3,4}});
            assertArrayEquals(new int[]{3,4}, result[0]);
        }

        @Test
        void allSameWeighted() {
            // [2,6]=12, [3,4]=12, [4,3]=12, [6,2]=12
            // score DESC: [6,2],[4,3],[3,4],[2,6]
            int[][] result = SortingChallenges.challenge4(
                    new int[][]{{2,6},{3,4},{4,3},{6,2}});

            assertArrayEquals(new int[]{6,2}, result[0]);
            assertArrayEquals(new int[]{4,3}, result[1]);
            assertArrayEquals(new int[]{3,4}, result[2]);
            assertArrayEquals(new int[]{2,6}, result[3]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Performance tier order then salary DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<SortingChallenges.Employee> employees = new ArrayList<>(List.of(
                    new SortingChallenges.Employee("Alice", "Eng", 110000),
                    new SortingChallenges.Employee("Bob",   "Eng",  70000),
                    new SortingChallenges.Employee("Carol", "Eng",  90000),
                    new SortingChallenges.Employee("Diana", "Mkt",  60000),
                    new SortingChallenges.Employee("Eve",   "Mkt",  80000)
            ));
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge5(employees);

            // Eng avg=90000: HIGH>=108000, LOW<72000
            //   Alice=110000 >= 108000 → HIGH
            //   Bob=70000 < 72000     → LOW
            //   Carol=90000 → MID
            // Mkt avg=70000: HIGH>=84000, LOW<56000
            //   Diana=60000 >= 56000  → MID
            //   Eve=80000 < 84000     → MID

            assertEquals("Alice", result.get(0).name()); // HIGH
            // MID: Eve(80000), Carol(90000)... wait Carol=90000 > Eve=80000
            // MID salary DESC: Carol(90000), Eve(80000), Diana(60000)
            assertEquals("Carol", result.get(1).name()); // MID, salary=90000
            assertEquals("Eve",   result.get(2).name()); // MID, salary=80000
            assertEquals("Diana", result.get(3).name()); // MID, salary=60000
            assertEquals("Bob",   result.get(4).name()); // LOW
        }

        @Test
        void allSameTier() {
            List<SortingChallenges.Employee> employees = new ArrayList<>(List.of(
                    new SortingChallenges.Employee("Zara",  "HR", 80000),
                    new SortingChallenges.Employee("Alice", "HR", 80000)
            ));
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge5(employees);

            // both MID (avg=80000, 80000*1.2=96000, 80000*0.8=64000)
            // both 80000 → name ASC: Alice, Zara
            assertEquals("Alice", result.get(0).name());
            assertEquals("Zara",  result.get(1).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingChallenges.challenge5(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Map by longest word DESC then list size ASC then key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            Map<String, List<String>> map = new LinkedHashMap<>();
            map.put("team1", List.of("java","stream","collect"));       // longest=collect(7)
            map.put("team2", List.of("go","python","typescript"));      // longest=typescript(10)
            map.put("team3", List.of("spring","hibernate","jpa"));      // longest=hibernate(9)
            map.put("team4", List.of("c","cpp"));                       // longest=cpp(3)
            map.put("team5", List.of("kotlin","clojure","erlang"));     // longest=clojure(7)

            List<Map.Entry<String, List<String>>> result =
                    SortingChallenges.challenge6(map);

            assertEquals("team2", result.get(0).getKey()); // longest=10
            assertEquals("team3", result.get(1).getKey()); // longest=9
            assertEquals("team1", result.get(2).getKey()); // longest=7,size=3,t1<t5
            assertEquals("team5", result.get(3).getKey()); // longest=7,size=3,t5>t1
            assertEquals("team4", result.get(4).getKey()); // longest=3
        }

        @Test
        void singleWordLists() {
            Map<String, List<String>> map = new HashMap<>(Map.of(
                    "z", List.of("hello"),
                    "a", List.of("world")
            ));
            List<Map.Entry<String, List<String>>> result =
                    SortingChallenges.challenge6(map);

            // same longest=5, same size=1 → key ASC: a,z
            assertEquals("a", result.get(0).getKey());
            assertEquals("z", result.get(1).getKey());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingChallenges.challenge6(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Dept salary variance DESC then salary DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            List<SortingChallenges.Employee> employees = new ArrayList<>(List.of(
                    new SortingChallenges.Employee("Alice", "Eng", 90000),
                    new SortingChallenges.Employee("Bob",   "Eng", 30000),
                    new SortingChallenges.Employee("Carol", "Mkt", 70000),
                    new SortingChallenges.Employee("Diana", "Mkt", 80000)
            ));
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge7(employees);

            // Eng var=900000000 > Mkt var=25000000
            assertEquals("Alice", result.get(0).name()); // Eng, 90000
            assertEquals("Bob",   result.get(1).name()); // Eng, 30000
            assertEquals("Diana", result.get(2).name()); // Mkt, 80000
            assertEquals("Carol", result.get(3).name()); // Mkt, 70000
        }

        @Test
        void sameDeptVariance() {
            List<SortingChallenges.Employee> employees = new ArrayList<>(List.of(
                    new SortingChallenges.Employee("Alice", "HR", 80000),
                    new SortingChallenges.Employee("Bob",   "HR", 80000)
            ));
            List<SortingChallenges.Employee> result =
                    SortingChallenges.challenge7(employees);

            // variance=0, same salary → name ASC
            assertEquals("Alice", result.get(0).name());
            assertEquals("Bob",   result.get(1).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingChallenges.challenge7(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — 2D by longest increasing run DESC then sum ASC then first ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            int[][] result = SortingChallenges.challenge8(
                    new int[][]{{3,1,2,4},{5,4,3,2},{1,2,3,4},{2,3,1,4},{1,1,1,1}});

            assertArrayEquals(new int[]{1,2,3,4}, result[0]); // run=4
            assertArrayEquals(new int[]{3,1,2,4}, result[1]); // run=3
            assertArrayEquals(new int[]{2,3,1,4}, result[2]); // run=2
            // run=1: [1,1,1,1]=4, [5,4,3,2]=14 → sum ASC
            assertArrayEquals(new int[]{1,1,1,1}, result[3]);
            assertArrayEquals(new int[]{5,4,3,2}, result[4]);
        }

        @Test
        void allIncreasing() {
            int[][] result = SortingChallenges.challenge8(
                    new int[][]{{1,2,3},{4,5,6}});

            // both run=3 → sum ASC: [1,2,3]=6,[4,5,6]=15
            assertArrayEquals(new int[]{1,2,3}, result[0]);
            assertArrayEquals(new int[]{4,5,6}, result[1]);
        }

        @Test
        void singleRow() {
            int[][] result = SortingChallenges.challenge8(new int[][]{{3,1,2}});
            assertArrayEquals(new int[]{3,1,2}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Map by digit sum DESC then key length DESC then key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "alpha",   199,  // digitSum=19, keyLen=5
                    "beta",    88,   // digitSum=16, keyLen=4
                    "gamma",   100,  // digitSum=1,  keyLen=5
                    "delta",   73,   // digitSum=10, keyLen=5
                    "epsilon", 55    // digitSum=10, keyLen=7
            ));
            List<Map.Entry<String, Integer>> result =
                    SortingChallenges.challenge9(map);

            assertEquals("alpha",   result.get(0).getKey()); // digitSum=19
            assertEquals("beta",    result.get(1).getKey()); // digitSum=16
            assertEquals("epsilon", result.get(2).getKey()); // digitSum=10,keyLen=7
            assertEquals("delta",   result.get(3).getKey()); // digitSum=10,keyLen=5
            assertEquals("gamma",   result.get(4).getKey()); // digitSum=1
        }

        @Test
        void sameDigitSumSameKeyLen() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "zeta", 10,  // digitSum=1, keyLen=4
                    "beta", 10   // digitSum=1, keyLen=4
            ));
            List<Map.Entry<String, Integer>> result =
                    SortingChallenges.challenge9(map);

            // same digit sum, same key len → key ASC
            assertEquals("beta", result.get(0).getKey());
            assertEquals("zeta", result.get(1).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("abc", 123));
            assertEquals(1, SortingChallenges.challenge9(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingChallenges.challenge9(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Unique char ratio DESC then length ASC then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            List<String> result = SortingChallenges.challenge10(
                    new ArrayList<>(List.of("hello","abcd","aabb","programming","hi","noon")));

            // ratio: abcd=1.0,hi=1.0,hello=0.8,programming=0.727,aabb=0.5,noon=0.5
            // ratio=1.0: hi(2),abcd(4) → len ASC: hi,abcd
            assertEquals("hi",          result.get(0));
            assertEquals("abcd",        result.get(1));
            assertEquals("hello",       result.get(2));
            assertEquals("programming", result.get(3));
            // ratio=0.5: aabb(4),noon(4) → len tie → alpha: aabb,noon
            assertEquals("aabb",        result.get(4));
            assertEquals("noon",        result.get(5));
        }

        @Test
        void allUniqueChars() {
            List<String> result = SortingChallenges.challenge10(
                    new ArrayList<>(List.of("ab","cd","ef")));

            // all ratio=1.0 → len ASC tie → alpha: ab,cd,ef
            assertEquals("ab", result.get(0));
            assertEquals("cd", result.get(1));
            assertEquals("ef", result.get(2));
        }

        @Test
        void singleChar() {
            List<String> result = SortingChallenges.challenge10(
                    new ArrayList<>(List.of("a","b","c")));

            // all ratio=1.0, len=1 → alpha: a,b,c
            assertEquals("a", result.get(0));
            assertEquals("b", result.get(1));
            assertEquals("c", result.get(2));
        }

        @Test
        void emptyList() {
            assertTrue(SortingChallenges.challenge10(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> SortingChallenges.challenge10(null));
        }
    }
}