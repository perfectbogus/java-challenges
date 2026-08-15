package dev.perfectbogus.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingNewChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Sort by digit count ASC then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<String> result = SortingNewChallenges.challenge1(
                    new ArrayList<>(List.of("abc123","hello","a1b2c3","xyz","test99","world1")));

            assertEquals("hello",   result.get(0)); // 0 digits, h < x
            assertEquals("xyz",     result.get(1)); // 0 digits
            assertEquals("world1",  result.get(2)); // 1 digit
            assertEquals("test99",  result.get(3)); // 2 digits
            assertEquals("a1b2c3",  result.get(4)); // 3 digits, a < a... a1 < ab alpha
            assertEquals("abc123",  result.get(5)); // 3 digits
        }

        @Test
        void noDigits() {
            List<String> result = SortingNewChallenges.challenge1(
                    new ArrayList<>(List.of("zebra","apple","mango")));

            assertEquals(List.of("apple","mango","zebra"), result);
        }

        @Test
        void allDigits() {
            List<String> result = SortingNewChallenges.challenge1(
                    new ArrayList<>(List.of("999","1","12")));

            // digits=3,1,2 → ASC: 1,12,999
            assertEquals("1",   result.get(0));
            assertEquals("12",  result.get(1));
            assertEquals("999", result.get(2));
        }

        @Test
        void sameDigitCount() {
            List<String> result = SortingNewChallenges.challenge1(
                    new ArrayList<>(List.of("b1","c1","a1")));

            // same digits=1 → alpha ASC: a1,b1,c1
            assertEquals("a1", result.get(0));
            assertEquals("b1", result.get(1));
            assertEquals("c1", result.get(2));
        }

        @Test
        void emptyList() {
            assertTrue(SortingNewChallenges.challenge1(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Sort Map entries by key's first char ASC then value DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "banana", 3, "cherry", 5, "blueberry", 1,
                    "avocado", 4, "apricot", 7, "coconut", 2));

            List<Map.Entry<String, Integer>> result = SortingNewChallenges.challenge2(map);

            // first='a': apricot(7), avocado(4) → value DESC
            assertEquals("apricot",   result.get(0).getKey());
            assertEquals("avocado",   result.get(1).getKey());
            // first='b': banana(3), blueberry(1) → value DESC
            assertEquals("banana",    result.get(2).getKey());
            assertEquals("blueberry", result.get(3).getKey());
            // first='c': cherry(5), coconut(2) → value DESC
            assertEquals("cherry",    result.get(4).getKey());
            assertEquals("coconut",   result.get(5).getKey());
        }

        @Test
        void allSameFirstChar() {
            Map<String, Integer> map = new HashMap<>(Map.of(
                    "cat", 1, "cow", 5, "crab", 3));

            List<Map.Entry<String, Integer>> result = SortingNewChallenges.challenge2(map);

            // All 'c' → value DESC: cow(5),crab(3),cat(1)
            assertEquals("cow",  result.get(0).getKey());
            assertEquals("crab", result.get(1).getKey());
            assertEquals("cat",  result.get(2).getKey());
        }

        @Test
        void singleEntry() {
            Map<String, Integer> map = new HashMap<>(Map.of("apple", 5));
            assertEquals(1, SortingNewChallenges.challenge2(map).size());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingNewChallenges.challenge2(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Sort 2D by row RANGE ASC then first element ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            int[][] result = SortingNewChallenges.challenge3(
                    new int[][]{{3,7,1},{5,5,5},{2,9,4},{1,3,2},{8,2,6}});

            assertArrayEquals(new int[]{5, 5, 5}, result[0]); // range=0
            assertArrayEquals(new int[]{1, 3, 2}, result[1]); // range=2
            assertArrayEquals(new int[]{3, 7, 1}, result[2]); // range=6, first=3
            assertArrayEquals(new int[]{8, 2, 6}, result[3]); // range=6, first=8
            assertArrayEquals(new int[]{2, 9, 4}, result[4]); // range=7
        }

        @Test
        void allSameRange() {
            int[][] result = SortingNewChallenges.challenge3(
                    new int[][]{{5,1},{3,7},{2,6}});

            // All range=4 → first elem ASC: 2,3,5
            assertArrayEquals(new int[]{2, 6}, result[0]); // first=2
            assertArrayEquals(new int[]{3, 7}, result[1]); // first=3
            assertArrayEquals(new int[]{5, 1}, result[2]); // first=5
        }

        @Test
        void zeroRangeAllSame() {
            int[][] result = SortingNewChallenges.challenge3(
                    new int[][]{{3,3,3},{1,1,1},{2,2,2}});

            // All range=0 → first elem ASC: 1,2,3
            assertArrayEquals(new int[]{1, 1, 1}, result[0]);
            assertArrayEquals(new int[]{2, 2, 2}, result[1]);
            assertArrayEquals(new int[]{3, 3, 3}, result[2]);
        }

        @Test
        void singleRow() {
            int[][] result = SortingNewChallenges.challenge3(new int[][]{{1, 5, 3}});
            assertArrayEquals(new int[]{1, 5, 3}, result[0]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Sort by NET salary after tax DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        private List<SortingNewChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingNewChallenges.Employee("Alice", "Engineering", 95000),
                    new SortingNewChallenges.Employee("Bob",   "Marketing",   60000),
                    new SortingNewChallenges.Employee("Carol", "Engineering", 85000),
                    new SortingNewChallenges.Employee("Diana", "HR",          80000),
                    new SortingNewChallenges.Employee("Eve",   "Marketing",   75000)
            ));
        }

        @Test
        void netSalaryOrder() {
            List<SortingNewChallenges.Employee> result = SortingNewChallenges.challenge4(employees);

            // Alice:  95000*0.70=66500
            // Diana:  80000*0.80=64000  (80000 is NOT > 80000!)
            // Eve:    75000*0.80=60000
            // Carol:  85000*0.70=59500
            // Bob:    60000*0.80=48000
            assertEquals("Alice", result.get(0).name());
            assertEquals("Diana", result.get(1).name());
            assertEquals("Eve",   result.get(2).name());
            assertEquals("Carol", result.get(3).name());
            assertEquals("Bob",   result.get(4).name());
        }

        @Test
        void exactBoundary80000TaxRate() {
            List<SortingNewChallenges.Employee> boundary = new ArrayList<>(List.of(
                    new SortingNewChallenges.Employee("A", "Dept", 80001),  // net = 80001*0.70
                    new SortingNewChallenges.Employee("B", "Dept", 80000)   // net = 80000*0.80
            ));
            List<SortingNewChallenges.Employee> result = SortingNewChallenges.challenge4(boundary);

            // A: 80001*0.70=56000.7, B: 80000*0.80=64000 → B has higher net!
            assertEquals("B", result.get(0).name());
            assertEquals("A", result.get(1).name());
        }

        @Test
        void sameNetSalaryUsesName() {
            List<SortingNewChallenges.Employee> tied = new ArrayList<>(List.of(
                    new SortingNewChallenges.Employee("Zara",  "Dept", 50000),
                    new SortingNewChallenges.Employee("Alice", "Dept", 50000)
            ));
            List<SortingNewChallenges.Employee> result = SortingNewChallenges.challenge4(tied);

            assertEquals("Alice", result.get(0).name());
            assertEquals("Zara",  result.get(1).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingNewChallenges.challenge4(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Sort by distinct char count DESC then length ASC then alpha ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<String> result = SortingNewChallenges.challenge5(
                    new ArrayList<>(List.of("hello","world","java","stream","collect","map")));

            assertEquals("stream",  result.get(0)); // 6 distinct
            assertEquals("world",   result.get(1)); // 5 distinct, len=5
            assertEquals("collect", result.get(2)); // 5 distinct, len=7
            assertEquals("hello",   result.get(3)); // 4 distinct
            assertEquals("map",     result.get(4)); // 3 distinct, len=3
            assertEquals("java",    result.get(5)); // 3 distinct, len=4
        }

        @Test
        void allSameDistinctCount() {
            List<String> result = SortingNewChallenges.challenge5(
                    new ArrayList<>(List.of("abc","xyz","def")));

            // All 3 distinct, same len=3 → alpha: abc,def,xyz
            assertEquals("abc", result.get(0));
            assertEquals("def", result.get(1));
            assertEquals("xyz", result.get(2));
        }

        @Test
        void sameDistinctDifferentLength() {
            List<String> result = SortingNewChallenges.challenge5(
                    new ArrayList<>(List.of("abcde","abc")));

            // Both 3 distinct ('a','b','c' + more in abcde has 5)
            // abc=3 distinct, abcde=5 distinct → abcde first
            assertEquals("abcde", result.get(0));
            assertEquals("abc",   result.get(1));
        }

        @Test
        void repeatedChars() {
            // "aaa" has 1 distinct, "ab" has 2 distinct
            List<String> result = SortingNewChallenges.challenge5(
                    new ArrayList<>(List.of("aaa","ab","abc")));

            assertEquals("abc", result.get(0)); // 3 distinct
            assertEquals("ab",  result.get(1)); // 2 distinct
            assertEquals("aaa", result.get(2)); // 1 distinct
        }

        @Test
        void emptyList() {
            assertTrue(SortingNewChallenges.challenge5(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Sort Map<String,List<Integer>> by max DESC, size ASC, key ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("alice",  List.of(3, 9, 2));     // max=9, size=3
            map.put("bob",    List.of(5, 7));         // max=7, size=2
            map.put("carol",  List.of(1, 9, 4, 6));  // max=9, size=4
            map.put("diana",  List.of(8, 2));         // max=8, size=2
            map.put("eve",    List.of(7, 3, 5));      // max=7, size=3

            List<Map.Entry<String, List<Integer>>> result = SortingNewChallenges.challenge6(map);

            // max=9: alice(size=3),carol(size=4) → size ASC: alice,carol
            assertEquals("alice",  result.get(0).getKey());
            assertEquals("carol",  result.get(1).getKey());
            // max=8: diana(size=2)
            assertEquals("diana",  result.get(2).getKey());
            // max=7: bob(size=2),eve(size=3) → size ASC: bob,eve
            assertEquals("bob",    result.get(3).getKey());
            assertEquals("eve",    result.get(4).getKey());
        }

        @Test
        void allSameMax() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("cat",  List.of(5, 1));    // max=5, size=2
            map.put("ant",  List.of(5, 2, 3)); // max=5, size=3
            map.put("bat",  List.of(5));        // max=5, size=1

            List<Map.Entry<String, List<Integer>>> result = SortingNewChallenges.challenge6(map);

            // Same max → size ASC: bat(1),cat(2),ant(3)
            assertEquals("bat", result.get(0).getKey());
            assertEquals("cat", result.get(1).getKey());
            assertEquals("ant", result.get(2).getKey());
        }

        @Test
        void sameSizeSameMax() {
            Map<String, List<Integer>> map = new LinkedHashMap<>();
            map.put("zebra", List.of(9, 1)); // max=9, size=2
            map.put("alpha", List.of(9, 2)); // max=9, size=2

            List<Map.Entry<String, List<Integer>>> result = SortingNewChallenges.challenge6(map);

            // Same max + size → key ASC: alpha, zebra
            assertEquals("alpha", result.get(0).getKey());
            assertEquals("zebra", result.get(1).getKey());
        }

        @Test
        void emptyMap() {
            assertTrue(SortingNewChallenges.challenge6(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Sort products by category priority then rating tier then price ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        private List<SortingNewChallenges.Product> products;

        @BeforeEach
        void setUp() {
            products = new ArrayList<>(List.of(
                    new SortingNewChallenges.Product("Phone",  "Electronics", 999.0,  4.8),
                    new SortingNewChallenges.Product("Shirt",  "Clothing",     49.0,  2.5),
                    new SortingNewChallenges.Product("Laptop", "Electronics", 1299.0, 3.2),
                    new SortingNewChallenges.Product("Apple",  "Food",          1.5,  4.7),
                    new SortingNewChallenges.Product("Tablet", "Electronics",  499.0, 4.6),
                    new SortingNewChallenges.Product("Jeans",  "Clothing",      89.0, 4.5)
            ));
        }

        @Test
        void categoryOrder() {
            List<SortingNewChallenges.Product> result = SortingNewChallenges.challenge7(products);

            // Electronics first (3), Clothing second (2), Food last (1)
            assertEquals("Electronics", result.get(0).category());
            assertEquals("Electronics", result.get(1).category());
            assertEquals("Electronics", result.get(2).category());
            assertEquals("Clothing",    result.get(3).category());
            assertEquals("Clothing",    result.get(4).category());
            assertEquals("Food",        result.get(5).category());
        }

        @Test
        void tierAndPriceOrder() {
            List<SortingNewChallenges.Product> result = SortingNewChallenges.challenge7(products);

            // Electronics: PREMIUM(Tablet=499, Phone=999), STANDARD(Laptop=1299)
            assertEquals("Tablet", result.get(0).name()); // Elec+PREMIUM price=499
            assertEquals("Phone",  result.get(1).name()); // Elec+PREMIUM price=999
            assertEquals("Laptop", result.get(2).name()); // Elec+STANDARD

            // Clothing: PREMIUM(Jeans=89), BUDGET(Shirt=49)
            assertEquals("Jeans",  result.get(3).name()); // Cloth+PREMIUM
            assertEquals("Shirt",  result.get(4).name()); // Cloth+BUDGET

            // Food: PREMIUM(Apple=1.5)
            assertEquals("Apple",  result.get(5).name());
        }

        @Test
        void ratingTierBoundary() {
            // Exactly 4.5 → PREMIUM, exactly 3.0 → STANDARD
            List<SortingNewChallenges.Product> boundary = new ArrayList<>(List.of(
                    new SortingNewChallenges.Product("A", "Electronics", 100.0, 4.5),  // PREMIUM
                    new SortingNewChallenges.Product("B", "Electronics", 100.0, 3.0),  // STANDARD
                    new SortingNewChallenges.Product("C", "Electronics", 100.0, 2.9)   // BUDGET
            ));
            List<SortingNewChallenges.Product> result = SortingNewChallenges.challenge7(boundary);

            assertEquals("A", result.get(0).name()); // PREMIUM
            assertEquals("B", result.get(1).name()); // STANDARD
            assertEquals("C", result.get(2).name()); // BUDGET
        }

        @Test
        void emptyList() {
            assertTrue(SortingNewChallenges.challenge7(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Sort 2D by above-avg count DESC, sum ASC, first elem ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            int[][] result = SortingNewChallenges.challenge8(
                    new int[][]{{1,2,9},{4,4,4},{2,3,7},{1,1,1},{5,8,2}});

            // [1,2,9]=count1,sum12  [4,4,4]=count0,sum12
            // [2,3,7]=count1,sum12  [1,1,1]=count0,sum3
            // [5,8,2]=count1,sum15
            // count=1: sum12([1,2,9],[2,3,7]) then 15 → first: [1,2,9],[2,3,7],[5,8,2]
            // count=0: [1,1,1]=3,[4,4,4]=12 → sum ASC
            assertArrayEquals(new int[]{1, 2, 9}, result[0]); // count=1,sum=12,first=1
            assertArrayEquals(new int[]{2, 3, 7}, result[1]); // count=1,sum=12,first=2
            assertArrayEquals(new int[]{5, 8, 2}, result[2]); // count=1,sum=15
            assertArrayEquals(new int[]{1, 1, 1}, result[3]); // count=0,sum=3
            assertArrayEquals(new int[]{4, 4, 4}, result[4]); // count=0,sum=12
        }

        @Test
        void allSameElements() {
            int[][] result = SortingNewChallenges.challenge8(
                    new int[][]{{5,5,5},{1,1,1},{3,3,3}});

            // All count=0 → sum ASC: 3,9,15
            assertArrayEquals(new int[]{1, 1, 1}, result[0]);
            assertArrayEquals(new int[]{3, 3, 3}, result[1]);
            assertArrayEquals(new int[]{5, 5, 5}, result[2]);
        }

        @Test
        void singleRow() {
            int[][] result = SortingNewChallenges.challenge8(new int[][]{{1, 5, 2}});
            assertArrayEquals(new int[]{1, 5, 2}, result[0]);
        }

        @Test
        void singleElementRows() {
            int[][] result = SortingNewChallenges.challenge8(
                    new int[][]{{5},{1},{3}});

            // All count=0 (can't be above own avg) → sum ASC
            assertArrayEquals(new int[]{1}, result[0]);
            assertArrayEquals(new int[]{3}, result[1]);
            assertArrayEquals(new int[]{5}, result[2]);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Sort by dept name length DESC then salary DESC then name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        private List<SortingNewChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new SortingNewChallenges.Employee("Alice", "Engineering", 95000),
                    new SortingNewChallenges.Employee("Bob",   "Marketing",   60000),
                    new SortingNewChallenges.Employee("Carol", "Engineering", 85000),
                    new SortingNewChallenges.Employee("Diana", "HR",          70000),
                    new SortingNewChallenges.Employee("Eve",   "Marketing",   75000),
                    new SortingNewChallenges.Employee("Frank", "Operations",  80000)
            ));
        }

        @Test
        void deptLengthOrder() {
            List<SortingNewChallenges.Employee> result = SortingNewChallenges.challenge9(employees);

            // Engineering(11): Alice,Carol; Operations(10): Frank
            // Marketing(9): Bob,Eve; HR(2): Diana
            assertEquals("Alice", result.get(0).name()); // Eng 95000
            assertEquals("Carol", result.get(1).name()); // Eng 85000
            assertEquals("Frank", result.get(2).name()); // Ops 80000
            assertEquals("Eve",   result.get(3).name()); // Mkt 75000
            assertEquals("Bob",   result.get(4).name()); // Mkt 60000
            assertEquals("Diana", result.get(5).name()); // HR  70000
        }

        @Test
        void sameDeptLengthSameSalaryUsesName() {
            List<SortingNewChallenges.Employee> tied = new ArrayList<>(List.of(
                    new SortingNewChallenges.Employee("Zara",  "HR", 50000),
                    new SortingNewChallenges.Employee("Alice", "HR", 50000)
            ));
            List<SortingNewChallenges.Employee> result = SortingNewChallenges.challenge9(tied);

            assertEquals("Alice", result.get(0).name());
            assertEquals("Zara",  result.get(1).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingNewChallenges.challenge9(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Sort students by weighted GPA DESC, class rank ASC, name ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<SortingNewChallenges.Student> students;

        @BeforeEach
        void setUp() {
            students = new ArrayList<>(List.of(
                    new SortingNewChallenges.Student("Alice", "SchoolA",
                            new HashMap<>(Map.of("Math",90,"Science",80,"English",70,"History",60))),
                    new SortingNewChallenges.Student("Bob", "SchoolA",
                            new HashMap<>(Map.of("Math",70,"Science",90,"English",80,"History",100))),
                    new SortingNewChallenges.Student("Carol", "SchoolB",
                            new HashMap<>(Map.of("Math",95,"Science",85,"English",90,"History",80))),
                    new SortingNewChallenges.Student("Diana", "SchoolB",
                            new HashMap<>(Map.of("Math",95,"Science",85,"English",90,"History",80))),
                    new SortingNewChallenges.Student("Eve", "SchoolA",
                            new HashMap<>(Map.of("Math",85,"Science",75,"English",95,"History",70)))
            ));
        }

        @Test
        void correctOrder() {
            List<SortingNewChallenges.Student> result = SortingNewChallenges.challenge10(students);

            // GPAs: Alice=80.0, Bob=81.0, Carol=89.5, Diana=89.5, Eve=82.5
            // GPA DESC: Carol=89.5, Diana=89.5, Eve=82.5, Bob=81.0, Alice=80.0
            // Carol and Diana same GPA, same rank (rank1 in SchoolB) → name ASC: Carol,Diana
            assertEquals("Carol", result.get(0).name());
            assertEquals("Diana", result.get(1).name());
            assertEquals("Eve",   result.get(2).name()); // 82.5 rank1 in SchoolA
            assertEquals("Bob",   result.get(3).name()); // 81.0 rank2 in SchoolA
            assertEquals("Alice", result.get(4).name()); // 80.0 rank3 in SchoolA
        }

        @Test
        void gpaCalculation() {
            // Verify GPA ordering: Bob(81.0) > Alice(80.0)
            List<SortingNewChallenges.Student> twoStudents = new ArrayList<>(List.of(
                    new SortingNewChallenges.Student("Alice", "SchoolA",
                            new HashMap<>(Map.of("Math",90,"Science",80,"English",70,"History",60))),
                    new SortingNewChallenges.Student("Bob", "SchoolA",
                            new HashMap<>(Map.of("Math",70,"Science",90,"English",80,"History",100)))
            ));
            List<SortingNewChallenges.Student> result = SortingNewChallenges.challenge10(twoStudents);

            // Bob GPA=81.0 > Alice GPA=80.0
            assertEquals("Bob",   result.get(0).name());
            assertEquals("Alice", result.get(1).name());
        }

        @Test
        void sameGpaSameRankUsesName() {
            // Carol and Diana have same GPA=89.5 in SchoolB → same rank → name ASC
            List<SortingNewChallenges.Student> twoStudents = new ArrayList<>(List.of(
                    new SortingNewChallenges.Student("Diana", "SchoolB",
                            new HashMap<>(Map.of("Math",95,"Science",85,"English",90,"History",80))),
                    new SortingNewChallenges.Student("Carol", "SchoolB",
                            new HashMap<>(Map.of("Math",95,"Science",85,"English",90,"History",80)))
            ));
            List<SortingNewChallenges.Student> result = SortingNewChallenges.challenge10(twoStudents);

            assertEquals("Carol", result.get(0).name());
            assertEquals("Diana", result.get(1).name());
        }

        @Test
        void sameGpaDifferentRank() {
            // Two students with same GPA but different schools → different class ranks
            List<SortingNewChallenges.Student> students = new ArrayList<>(List.of(
                    new SortingNewChallenges.Student("TopOfClass",    "SchoolA",
                            new HashMap<>(Map.of("Math",90,"Science",80,"English",70,"History",60))), // GPA=80, rank1 in A
                    new SortingNewChallenges.Student("BottomOfClass", "SchoolB",
                            new HashMap<>(Map.of("Math",90,"Science",80,"English",70,"History",60))), // GPA=80, rank2 in B
                    new SortingNewChallenges.Student("AheadInSchoolB", "SchoolB",
                            new HashMap<>(Map.of("Math",95,"Science",85,"English",90,"History",80)))  // GPA=89.5, rank1 in B
            ));
            List<SortingNewChallenges.Student> result = SortingNewChallenges.challenge10(students);

            assertEquals("AheadInSchoolB", result.get(0).name()); // GPA=89.5
            // Both 80.0 GPA: TopOfClass rank1(SchoolA) vs BottomOfClass rank2(SchoolB) → rank ASC: Top first
            assertEquals("TopOfClass",    result.get(1).name()); // rank=1
            assertEquals("BottomOfClass", result.get(2).name()); // rank=2
        }

        @Test
        void singleStudent() {
            List<SortingNewChallenges.Student> single = new ArrayList<>(List.of(
                    new SortingNewChallenges.Student("Alice", "SchoolA",
                            new HashMap<>(Map.of("Math",90,"Science",80,"English",70,"History",60)))
            ));
            List<SortingNewChallenges.Student> result = SortingNewChallenges.challenge10(single);

            assertEquals(1, result.size());
            assertEquals("Alice", result.get(0).name());
        }

        @Test
        void emptyList() {
            assertTrue(SortingNewChallenges.challenge10(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> SortingNewChallenges.challenge10(null));
        }
    }
}