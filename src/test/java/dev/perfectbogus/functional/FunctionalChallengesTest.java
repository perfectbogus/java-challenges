package dev.perfectbogus.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — 🟢 Function<String,String> capitalize first letter
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<String> result = FunctionalChallenges.challenge1(
                    new ArrayList<>(List.of("hELLO","wORLD","jAVA","fUNCTIONAL")));

            assertEquals(List.of("Hello","World","Java","Functional"), result);
        }

        @Test
        void alreadyCorrect() {
            List<String> result = FunctionalChallenges.challenge1(
                    new ArrayList<>(List.of("Hello","World")));
            assertEquals(List.of("Hello","World"), result);
        }

        @Test
        void allUppercase() {
            List<String> result = FunctionalChallenges.challenge1(
                    new ArrayList<>(List.of("HELLO","WORLD")));
            assertEquals(List.of("Hello","World"), result);
        }

        @Test
        void allLowercase() {
            List<String> result = FunctionalChallenges.challenge1(
                    new ArrayList<>(List.of("hello","world")));
            assertEquals(List.of("Hello","World"), result);
        }

        @Test
        void singleChar() {
            List<String> result = FunctionalChallenges.challenge1(
                    new ArrayList<>(List.of("a","B","c")));
            assertEquals(List.of("A","B","C"), result);
        }

        @Test
        void emptyList() {
            assertTrue(FunctionalChallenges.challenge1(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — 🟢 Predicate combinators and()/or() to filter employees
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        private List<FunctionalChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 95000),
                    new FunctionalChallenges.Employee("Bob",   "Marketing",   60000),
                    new FunctionalChallenges.Employee("Carol", "Engineering", 75000),
                    new FunctionalChallenges.Employee("Diana", "Marketing",   70000),
                    new FunctionalChallenges.Employee("Eve",   "HR",          90000)
            ));
        }

        @Test
        void basicCase() {
            List<FunctionalChallenges.Employee> result = FunctionalChallenges.challenge2(employees);

            assertEquals(2, result.size());
            assertTrue(result.stream().anyMatch(e -> e.name().equals("Alice"))); // Eng > 80000
            assertTrue(result.stream().anyMatch(e -> e.name().equals("Diana"))); // Mkt > 65000
        }

        @Test
        void engineeringOnlyQualifies() {
            List<FunctionalChallenges.Employee> single = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 95000)
            ));
            List<FunctionalChallenges.Employee> result = FunctionalChallenges.challenge2(single);
            assertEquals(1, result.size());
            assertEquals("Alice", result.get(0).name());
        }

        @Test
        void marketingOnlyQualifies() {
            List<FunctionalChallenges.Employee> single = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Diana", "Marketing", 70000)
            ));
            List<FunctionalChallenges.Employee> result = FunctionalChallenges.challenge2(single);
            assertEquals(1, result.size());
            assertEquals("Diana", result.get(0).name());
        }

        @Test
        void noneQualify() {
            List<FunctionalChallenges.Employee> none = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Eve",  "HR",          90000),
                    new FunctionalChallenges.Employee("Bob",  "Marketing",   60000),
                    new FunctionalChallenges.Employee("Carol","Engineering", 75000)
            ));
            assertTrue(FunctionalChallenges.challenge2(none).isEmpty());
        }

        @Test
        void boundaryEngineering() {
            // Exactly 80000 → NOT > 80000
            List<FunctionalChallenges.Employee> boundary = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Test", "Engineering", 80000)
            ));
            assertTrue(FunctionalChallenges.challenge2(boundary).isEmpty());
        }

        @Test
        void boundaryMarketing() {
            // Exactly 65000 → NOT > 65000
            List<FunctionalChallenges.Employee> boundary = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Test", "Marketing", 65000)
            ));
            assertTrue(FunctionalChallenges.challenge2(boundary).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — 🟢 Consumer andThen() — chain two consumers
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        private List<FunctionalChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 95000),
                    new FunctionalChallenges.Employee("Bob",   "Marketing",   60000),
                    new FunctionalChallenges.Employee("Carol", "Engineering", 85000)
            ));
        }

        @Test
        void allNamesLogged() {
            Map<String, Object> result = FunctionalChallenges.challenge3(employees);
            @SuppressWarnings("unchecked")
            List<String> logged = (List<String>) result.get("logged");

            assertEquals(3, logged.size());
            assertTrue(logged.contains("Alice"));
            assertTrue(logged.contains("Bob"));
            assertTrue(logged.contains("Carol"));
        }

        @Test
        void onlyHighEarnersTracked() {
            Map<String, Object> result = FunctionalChallenges.challenge3(employees);
            @SuppressWarnings("unchecked")
            List<FunctionalChallenges.Employee> highEarners =
                    (List<FunctionalChallenges.Employee>) result.get("highEarners");

            assertEquals(2, highEarners.size()); // Alice + Carol
            assertTrue(highEarners.stream().anyMatch(e -> e.name().equals("Alice")));
            assertTrue(highEarners.stream().anyMatch(e -> e.name().equals("Carol")));
            assertFalse(highEarners.stream().anyMatch(e -> e.name().equals("Bob")));
        }

        @Test
        void loggedOrderMatchesInput() {
            Map<String, Object> result = FunctionalChallenges.challenge3(employees);
            @SuppressWarnings("unchecked")
            List<String> logged = (List<String>) result.get("logged");

            // Consumer processes in forEach order → same as input order
            assertEquals("Alice", logged.get(0));
            assertEquals("Bob",   logged.get(1));
            assertEquals("Carol", logged.get(2));
        }

        @Test
        void emptyList() {
            Map<String, Object> result = FunctionalChallenges.challenge3(new ArrayList<>());
            @SuppressWarnings("unchecked")
            List<String> logged = (List<String>) result.get("logged");
            @SuppressWarnings("unchecked")
            List<FunctionalChallenges.Employee> high = (List<FunctionalChallenges.Employee>) result.get("highEarners");

            assertTrue(logged.isEmpty());
            assertTrue(high.isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — 🟢 Supplier<T> — default value when empty
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        private List<FunctionalChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 95000),
                    new FunctionalChallenges.Employee("Bob",   "Marketing",   60000)
            ));
        }

        @Test
        void departmentExists() {
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge4(employees, "Engineering");

            assertEquals(1, result.size());
            assertEquals("Alice", result.get(0).name());
        }

        @Test
        void departmentNotFound() {
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge4(employees, "HR");

            assertEquals(1, result.size());
            assertEquals("Unknown",  result.get(0).name());       // Supplier default
            assertEquals("HR",       result.get(0).department());  // correct dept
            assertEquals(0.0,        result.get(0).salary(), 0.01);
        }

        @Test
        void multiplePeopleInDept() {
            List<FunctionalChallenges.Employee> more = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 95000),
                    new FunctionalChallenges.Employee("Carol", "Engineering", 85000)
            ));
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge4(more, "Engineering");

            assertEquals(2, result.size());
        }

        @Test
        void emptyEmployeeList() {
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge4(new ArrayList<>(), "Engineering");

            // No employees → Supplier kicks in
            assertEquals(1, result.size());
            assertEquals("Unknown", result.get(0).name());
        }

        @Test
        void nullEmployees() {
            assertThrows(IllegalArgumentException.class,
                    () -> FunctionalChallenges.challenge4(null, "Engineering"));
        }

        @Test
        void nullDepartment() {
            assertThrows(IllegalArgumentException.class,
                    () -> FunctionalChallenges.challenge4(employees, null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — 🟢 UnaryOperator<String> with replaceAll()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<String> result = FunctionalChallenges.challenge5(
                    new ArrayList<>(List.of("  Hello   World  ","JAVA   IS   FUN","  functional  ")));

            assertEquals("hello world",    result.get(0));
            assertEquals("java is fun",    result.get(1));
            assertEquals("functional",     result.get(2));
        }

        @Test
        void noExtraSpaces() {
            List<String> result = FunctionalChallenges.challenge5(
                    new ArrayList<>(List.of("hello","world")));

            assertEquals("hello", result.get(0));
            assertEquals("world", result.get(1));
        }

        @Test
        void allUppercase() {
            List<String> result = FunctionalChallenges.challenge5(
                    new ArrayList<>(List.of("HELLO","WORLD")));

            assertEquals("hello", result.get(0));
            assertEquals("world", result.get(1));
        }

        @Test
        void onlySpaces() {
            List<String> result = FunctionalChallenges.challenge5(
                    new ArrayList<>(List.of("   ")));

            assertEquals("", result.get(0)); // trim → empty string
        }

        @Test
        void modifiesInPlace() {
            List<String> original = new ArrayList<>(List.of("  HELLO  "));
            List<String> result = FunctionalChallenges.challenge5(original);

            // replaceAll modifies in place → same list reference
            assertSame(original, result);
        }

        @Test
        void emptyList() {
            assertTrue(FunctionalChallenges.challenge5(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — 🟢 Function.andThen() composition pipeline
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            List<String> result = FunctionalChallenges.challenge6(
                    new ArrayList<>(List.of("  hello world  ","  java  is  fun  ","  test  ")));

            assertEquals("KEY_HELLO_WORLD_END",    result.get(0));
            assertEquals("KEY_JAVA__IS__FUN_END",  result.get(1));
            assertEquals("KEY_TEST_END",           result.get(2));
        }

        @Test
        void noLeadingTrailingSpaces() {
            List<String> result = FunctionalChallenges.challenge6(
                    new ArrayList<>(List.of("hello world")));

            assertEquals("KEY_HELLO_WORLD_END", result.get(0));
        }

        @Test
        void alreadyUppercase() {
            List<String> result = FunctionalChallenges.challenge6(
                    new ArrayList<>(List.of("  HELLO  ")));

            assertEquals("KEY_HELLO_END", result.get(0));
        }

        @Test
        void singleWord() {
            List<String> result = FunctionalChallenges.challenge6(
                    new ArrayList<>(List.of("  java  ")));

            assertEquals("KEY_JAVA_END", result.get(0));
        }

        @Test
        void emptyList() {
            assertTrue(FunctionalChallenges.challenge6(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — 🟡 Higher-Order Functions — salary adjuster factory
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        private List<FunctionalChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 100000),
                    new FunctionalChallenges.Employee("Bob",   "Marketing",    60000),
                    new FunctionalChallenges.Employee("Carol", "Engineering",  80000)
            ));
        }

        @Test
        void raiseOnlyEngineering() {
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge7(employees, "Engineering", 0.10);

            assertEquals(110000, result.get(0).salary(), 0.01); // Alice raised
            assertEquals(60000,  result.get(1).salary(), 0.01); // Bob unchanged
            assertEquals(88000,  result.get(2).salary(), 0.01); // Carol raised
        }

        @Test
        void raiseOnlyMarketing() {
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge7(employees, "Marketing", 0.20);

            assertEquals(100000, result.get(0).salary(), 0.01); // Alice unchanged
            assertEquals(72000,  result.get(1).salary(), 0.01); // Bob raised 20%
            assertEquals(80000,  result.get(2).salary(), 0.01); // Carol unchanged
        }

        @Test
        void createRaiseFunctionReturnsCorrectFunction() {
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> raiser =
                    FunctionalChallenges.createRaiseFunction(0.50); // 50% raise

            FunctionalChallenges.Employee alice = new FunctionalChallenges.Employee("Alice", "Eng", 100000);
            FunctionalChallenges.Employee raised = raiser.apply(alice);

            assertEquals(150000, raised.salary(), 0.01);
            assertEquals("Alice", raised.name()); // name unchanged
        }

        @Test
        void createDeptFilterLeavesOtherUnchanged() {
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> raiser =
                    FunctionalChallenges.createRaiseFunction(0.10);
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> engOnly =
                    FunctionalChallenges.createDeptFilter("Engineering", raiser);

            FunctionalChallenges.Employee bob = new FunctionalChallenges.Employee("Bob", "Marketing", 60000);
            assertEquals(60000, engOnly.apply(bob).salary(), 0.01); // unchanged!
        }

        @Test
        void zeroPctRaiseChangesNothing() {
            List<FunctionalChallenges.Employee> result =
                    FunctionalChallenges.challenge7(employees, "Engineering", 0.0);

            assertEquals(100000, result.get(0).salary(), 0.01);
            assertEquals(80000,  result.get(2).salary(), 0.01);
        }

        @Test
        void emptyList() {
            assertTrue(FunctionalChallenges.challenge7(new ArrayList<>(), "Engineering", 0.10).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> FunctionalChallenges.challenge7(null, "Engineering", 0.10));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — 🟡 BinaryOperator<Employee> in reduce()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        private List<FunctionalChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice",  "Engineering", 95000),
                    new FunctionalChallenges.Employee("Bob",    "Marketing",   60000),
                    new FunctionalChallenges.Employee("Carol",  "Engineering", 85000),
                    new FunctionalChallenges.Employee("Diana",  "HR",          70000),
                    new FunctionalChallenges.Employee("Eve",    "Engineering", 92000)
            ));
        }

        @Test
        void findHighestPaid() {
            FunctionalChallenges.Employee result = FunctionalChallenges.findHighestPaid(employees);
            assertEquals("Alice", result.name());
            assertEquals(95000,   result.salary(), 0.01);
        }

        @Test
        void findLowestPaid() {
            FunctionalChallenges.Employee result = FunctionalChallenges.findLowestPaid(employees);
            assertEquals("Bob", result.name());
            assertEquals(60000, result.salary(), 0.01);
        }

        @Test
        void findMostExperienced() {
            Map<String, Integer> years = new HashMap<>(Map.of(
                    "Alice", 5, "Bob", 10, "Carol", 3, "Diana", 8, "Eve", 6));

            FunctionalChallenges.Employee result =
                    FunctionalChallenges.findMostExperienced(employees, years);
            assertEquals("Bob", result.name()); // 10 years
        }

        @Test
        void singleEmployee() {
            List<FunctionalChallenges.Employee> single = List.of(
                    new FunctionalChallenges.Employee("Alice", "Eng", 95000));

            assertEquals("Alice", FunctionalChallenges.findHighestPaid(single).name());
            assertEquals("Alice", FunctionalChallenges.findLowestPaid(single).name());
        }

        @Test
        void nullInputHighest() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.findHighestPaid(null));
        }

        @Test
        void emptyInputHighest() {
            assertThrows(IllegalArgumentException.class,
                    () -> FunctionalChallenges.findHighestPaid(new ArrayList<>()));
        }

        @Test
        void nullInputLowest() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.findLowestPaid(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — 🟡 BiFunction to merge two department maps
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        private Map<String, List<FunctionalChallenges.Employee>> map1;
        private Map<String, List<FunctionalChallenges.Employee>> map2;

        @BeforeEach
        void setUp() {
            map1 = new HashMap<>(Map.of(
                    "Engineering", new ArrayList<>(List.of(
                            new FunctionalChallenges.Employee("Alice", "Engineering", 95000),
                            new FunctionalChallenges.Employee("Carol", "Engineering", 85000)
                    )),
                    "Marketing", new ArrayList<>(List.of(
                            new FunctionalChallenges.Employee("Bob", "Marketing", 60000)
                    ))
            ));
            map2 = new HashMap<>(Map.of(
                    "Engineering", new ArrayList<>(List.of(
                            new FunctionalChallenges.Employee("Eve", "Engineering", 92000)
                    )),
                    "HR", new ArrayList<>(List.of(
                            new FunctionalChallenges.Employee("Frank", "HR", 55000)
                    ))
            ));
        }

        @Test
        void basicMerge() {
            Map<String, List<FunctionalChallenges.Employee>> result =
                    FunctionalChallenges.challenge9(map1, map2);

            assertEquals(3, result.size()); // Engineering, Marketing, HR
        }

        @Test
        void engineeringCombined() {
            Map<String, List<FunctionalChallenges.Employee>> result =
                    FunctionalChallenges.challenge9(map1, map2);

            List<FunctionalChallenges.Employee> eng = result.get("Engineering");
            assertEquals(3, eng.size()); // Alice + Carol + Eve
            assertTrue(eng.stream().anyMatch(e -> e.name().equals("Alice")));
            assertTrue(eng.stream().anyMatch(e -> e.name().equals("Carol")));
            assertTrue(eng.stream().anyMatch(e -> e.name().equals("Eve")));
        }

        @Test
        void marketingOnlyInMap1() {
            Map<String, List<FunctionalChallenges.Employee>> result =
                    FunctionalChallenges.challenge9(map1, map2);

            List<FunctionalChallenges.Employee> mkt = result.get("Marketing");
            assertEquals(1, mkt.size());
            assertEquals("Bob", mkt.get(0).name());
        }

        @Test
        void hrOnlyInMap2() {
            Map<String, List<FunctionalChallenges.Employee>> result =
                    FunctionalChallenges.challenge9(map1, map2);

            List<FunctionalChallenges.Employee> hr = result.get("HR");
            assertEquals(1, hr.size());
            assertEquals("Frank", hr.get(0).name());
        }

        @Test
        void emptyMaps() {
            Map<String, List<FunctionalChallenges.Employee>> result =
                    FunctionalChallenges.challenge9(new HashMap<>(), new HashMap<>());
            assertTrue(result.isEmpty());
        }

        @Test
        void nullMap1() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge9(null, map2));
        }

        @Test
        void nullMap2() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge9(map1, null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — 🔴 Dynamic Function pipeline with transforms + filter + format
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<FunctionalChallenges.Employee> employees;

        @BeforeEach
        void setUp() {
            employees = new ArrayList<>(List.of(
                    new FunctionalChallenges.Employee("Alice", "Engineering", 100000),
                    new FunctionalChallenges.Employee("Bob",   "Marketing",    50000),
                    new FunctionalChallenges.Employee("Carol", "Engineering",  80000)
            ));
        }

        @Test
        void basicPipeline() {
            // Transform 1 — raise all by 10%
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> raise10 =
                    e -> new FunctionalChallenges.Employee(e.name(), e.department(), e.salary() * 1.10);

            // Transform 2 — extra 20% bonus for Engineering
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> engBonus =
                    e -> e.department().equals("Engineering")
                            ? new FunctionalChallenges.Employee(e.name(), e.department(), e.salary() * 1.20)
                            : e;

            // Filter — salary > 100000 after transforms
            Predicate<FunctionalChallenges.Employee> highSalary = e -> e.salary() > 100000;

            // Format — "Name: $salary"
            BiFunction<String, Double, String> format = (name, sal) -> name + ": $" + sal;

            List<String> result = FunctionalChallenges.challenge10(
                    employees, List.of(raise10, engBonus), highSalary, format);

            // Alice:  100000 * 1.10 = 110000 * 1.20 = 132000 → > 100000 ✓
            // Bob:    50000  * 1.10 = 55000  (no bonus) → NOT > 100000 ✗
            // Carol:  80000  * 1.10 = 88000  * 1.20 = 105600 → > 100000 ✓
            assertEquals(2, result.size());
            assertTrue(result.contains("Alice: $132000"));
            assertTrue(result.contains("Carol: $105600"));
        }

        @Test
        void noTransforms() {
            // Function.identity() chain → employees unchanged
            Predicate<FunctionalChallenges.Employee> all = e -> true;
            BiFunction<String, Double, String> format = (name, sal) -> name;

            List<String> result = FunctionalChallenges.challenge10(
                    employees, List.of(), all, format);

            assertEquals(3, result.size());
            assertTrue(result.contains("Alice"));
            assertTrue(result.contains("Bob"));
            assertTrue(result.contains("Carol"));
        }

        @Test
        void filterRemovesAll() {
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> identity = Function.identity();
            Predicate<FunctionalChallenges.Employee> none = e -> false; // filter everything!
            BiFunction<String, Double, String> format = (name, sal) -> name;

            List<String> result = FunctionalChallenges.challenge10(
                    employees, List.of(identity), none, format);

            assertTrue(result.isEmpty());
        }

        @Test
        void resultIsSorted() {
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> identity = Function.identity();
            Predicate<FunctionalChallenges.Employee> all = e -> true;
            BiFunction<String, Double, String> format = (name, sal) -> name;

            List<String> result = FunctionalChallenges.challenge10(
                    new ArrayList<>(List.of(
                            new FunctionalChallenges.Employee("Charlie", "Eng", 90000),
                            new FunctionalChallenges.Employee("Alice",   "Eng", 90000),
                            new FunctionalChallenges.Employee("Bob",     "Eng", 90000)
                    )),
                    List.of(identity), all, format);

            // Must be sorted!
            assertEquals("Alice",   result.get(0));
            assertEquals("Bob",     result.get(1));
            assertEquals("Charlie", result.get(2));
        }

        @Test
        void singleTransform() {
            Function<FunctionalChallenges.Employee, FunctionalChallenges.Employee> doubler =
                    e -> new FunctionalChallenges.Employee(e.name(), e.department(), e.salary() * 2);

            Predicate<FunctionalChallenges.Employee> highSalary = e -> e.salary() > 100000;
            BiFunction<String, Double, String> format = (name, sal) -> name + "=" + sal;

            List<String> result = FunctionalChallenges.challenge10(
                    employees, List.of(doubler), highSalary, format);

            // Alice: 200000, Bob: 100000 (not >), Carol: 160000
            assertEquals(2, result.size());
            assertTrue(result.contains("Alice=200000"));
            assertTrue(result.contains("Carol=160000"));
        }

        @Test
        void nullEmployees() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge10(
                    null, List.of(), e -> true, (n, s) -> n));
        }

        @Test
        void nullTransforms() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge10(
                    employees, null, e -> true, (n, s) -> n));
        }

        @Test
        void nullFilter() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge10(
                    employees, List.of(), null, (n, s) -> n));
        }

        @Test
        void nullFormatter() {
            assertThrows(IllegalArgumentException.class, () -> FunctionalChallenges.challenge10(
                    employees, List.of(), e -> true, null));
        }
    }
}