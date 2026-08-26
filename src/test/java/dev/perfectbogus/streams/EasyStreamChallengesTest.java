package dev.perfectbogus.streams;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EasyStreamChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Count strings containing at least one vowel
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            assertEquals(3L, EasyStreamChallenges.challenge1(
                    List.of("apple","gym","rhythm","hello","cry","fly","orange")));
        }

        @Test
        void basicCase1_2() {
            assertEquals(3L, EasyStreamChallenges.challenge1_2(
                    List.of("apple","gym","rhythm","hello","cry","fly","orange")));
        }

        @Test
        void allHaveVowels() {
            assertEquals(3L, EasyStreamChallenges.challenge1(
                    List.of("apple","orange","idea")));
        }

        @Test
        void noneHaveVowels() {
            assertEquals(0L, EasyStreamChallenges.challenge1(
                    List.of("gym","cry","rhythm","fly")));
        }

        @Test
        void singleVowelWord() {
            assertEquals(1L, EasyStreamChallenges.challenge1(List.of("a")));
        }

        @Test
        void emptyList() {
            assertEquals(0L, EasyStreamChallenges.challenge1(List.of()));
        }

        @Test
        void mixedCase() {
            // only lowercase vowels count
            assertEquals(1L, EasyStreamChallenges.challenge1(
                    List.of("apple","GYM","HELLO")));
            // "apple" has lowercase 'a','e' ✓
            // "GYM" → no lowercase vowels ✗
            // "HELLO" → no lowercase vowels ✗
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Filter odd numbers and multiply by 3
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            assertEquals(List.of(3,9,15,21,27),
                    EasyStreamChallenges.challenge2(List.of(1,2,3,4,5,6,7,8,9,10)));
        }

        @Test
        void allOdd() {
            assertEquals(List.of(3,9,15),
                    EasyStreamChallenges.challenge2(List.of(1,3,5)));
        }

        @Test
        void allEven() {
            assertTrue(EasyStreamChallenges.challenge2(List.of(2,4,6,8)).isEmpty());
        }

        @Test
        void withNegativeOdd() {
            assertEquals(List.of(-3,3),
                    EasyStreamChallenges.challenge2(List.of(-1,2,1)));
        }

        @Test
        void singleOddElement() {
            assertEquals(List.of(21),
                    EasyStreamChallenges.challenge2(List.of(7)));
        }

        @Test
        void emptyList() {
            assertTrue(EasyStreamChallenges.challenge2(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Sum of squares of even numbers
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            // 2²+4²+6² = 4+16+36 = 56
            assertEquals(56, EasyStreamChallenges.challenge3(List.of(1,2,3,4,5,6)));
        }

        @Test
        void allOdd() {
            assertEquals(0, EasyStreamChallenges.challenge3(List.of(1,3,5,7)));
        }

        @Test
        void allEven() {
            // 2²+4² = 4+16 = 20
            assertEquals(20, EasyStreamChallenges.challenge3(List.of(2,4)));
        }

        @Test
        void singleEven() {
            assertEquals(16, EasyStreamChallenges.challenge3(List.of(4)));
        }

        @Test
        void withZero() {
            // 0²+2² = 0+4 = 4
            assertEquals(4, EasyStreamChallenges.challenge3(List.of(0,1,2)));
        }

        @Test
        void emptyList() {
            assertEquals(0, EasyStreamChallenges.challenge3(List.of()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Distinct sorted list of strings
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            assertEquals(List.of("apple","banana","cherry","date"),
                    EasyStreamChallenges.challenge4(
                            List.of("banana","apple","cherry","apple","banana","date")));
        }

        @Test
        void noDuplicates() {
            assertEquals(List.of("apple","banana","cherry"),
                    EasyStreamChallenges.challenge4(List.of("cherry","apple","banana")));
        }

        @Test
        void allDuplicates() {
            assertEquals(List.of("apple"),
                    EasyStreamChallenges.challenge4(List.of("apple","apple","apple")));
        }

        @Test
        void singleElement() {
            assertEquals(List.of("hello"),
                    EasyStreamChallenges.challenge4(List.of("hello")));
        }

        @Test
        void emptyList() {
            assertTrue(EasyStreamChallenges.challenge4(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Name of highest paid employee
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            assertEquals("Alice", EasyStreamChallenges.challenge5(List.of(
                    new EasyStreamChallenges.Employee("Alice",  "Eng", 95000),
                    new EasyStreamChallenges.Employee("Bob",    "Mkt", 60000),
                    new EasyStreamChallenges.Employee("Carol",  "Eng", 85000),
                    new EasyStreamChallenges.Employee("Diana",  "HR",  70000)
            )));
        }

        @Test
        void singleEmployee() {
            assertEquals("Alice", EasyStreamChallenges.challenge5(List.of(
                    new EasyStreamChallenges.Employee("Alice", "Eng", 80000))));
        }

        @Test
        void emptyList() {
            assertEquals("NONE", EasyStreamChallenges.challenge5(List.of()));
        }

        @Test
        void highestAtEnd() {
            assertEquals("Eve", EasyStreamChallenges.challenge5(List.of(
                    new EasyStreamChallenges.Employee("Alice", "Eng", 60000),
                    new EasyStreamChallenges.Employee("Bob",   "Mkt", 70000),
                    new EasyStreamChallenges.Employee("Eve",   "HR",  99000)
            )));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — allMatch + anyMatch + noneMatch
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void mixedNumbers() {
            EasyStreamChallenges.MatchResult result =
                    EasyStreamChallenges.challenge6(List.of(1,-2,3,0,5));

            assertFalse(result.allPositive());  // -2 is not positive
            assertTrue(result.anyNegative());   // -2 is negative
            assertFalse(result.noneZero());     // 0 exists
        }

        @Test
        void allPositive() {
            EasyStreamChallenges.MatchResult result =
                    EasyStreamChallenges.challenge6(List.of(1,2,3,4,5));

            assertTrue(result.allPositive());
            assertFalse(result.anyNegative());
            assertTrue(result.noneZero());
        }

        @Test
        void allNegative() {
            EasyStreamChallenges.MatchResult result =
                    EasyStreamChallenges.challenge6(List.of(-1,-2,-3));

            assertFalse(result.allPositive());
            assertTrue(result.anyNegative());
            assertTrue(result.noneZero());
        }

        @Test
        void containsZero() {
            EasyStreamChallenges.MatchResult result =
                    EasyStreamChallenges.challenge6(List.of(1,2,0));

            assertFalse(result.noneZero());    // 0 exists
            assertFalse(result.allPositive()); // 0 is not > 0
        }

        @Test
        void singlePositive() {
            EasyStreamChallenges.MatchResult result =
                    EasyStreamChallenges.challenge6(List.of(42));

            assertTrue(result.allPositive());
            assertFalse(result.anyNegative());
            assertTrue(result.noneZero());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — findFirst string starting with letter
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            Optional<String> result = EasyStreamChallenges.challenge7(
                    List.of("apple","banana","apricot","cherry","avocado"), 'a');

            assertTrue(result.isPresent());
            assertEquals("apple", result.get()); // first match!
        }

        @Test
        void noMatch() {
            Optional<String> result = EasyStreamChallenges.challenge7(
                    List.of("banana","cherry"), 'z');
            assertTrue(result.isEmpty());
        }

        @Test
        void onlyOneMatch() {
            Optional<String> result = EasyStreamChallenges.challenge7(
                    List.of("apple","banana","cherry"), 'b');

            assertTrue(result.isPresent());
            assertEquals("banana", result.get());
        }

        @Test
        void firstElementMatches() {
            Optional<String> result = EasyStreamChallenges.challenge7(
                    List.of("cherry","apple","banana"), 'c');

            assertEquals("cherry", result.get());
        }

        @Test
        void emptyList() {
            assertTrue(EasyStreamChallenges.challenge7(List.of(), 'a').isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge7(null, 'a'));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Average salary per department
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            List<EasyStreamChallenges.Employee> employees = List.of(
                    new EasyStreamChallenges.Employee("Alice", "Engineering", 95000),
                    new EasyStreamChallenges.Employee("Bob",   "Marketing",   60000),
                    new EasyStreamChallenges.Employee("Carol", "Engineering", 85000)
            );
            assertEquals(90000.0,
                    EasyStreamChallenges.challenge8(employees, "Engineering"), 0.01);
        }

        @Test
        void departmentNotFound() {
            List<EasyStreamChallenges.Employee> employees = List.of(
                    new EasyStreamChallenges.Employee("Alice", "Engineering", 95000));

            assertEquals(0.0,
                    EasyStreamChallenges.challenge8(employees, "HR"), 0.01);
        }

        @Test
        void singleEmployee() {
            List<EasyStreamChallenges.Employee> employees = List.of(
                    new EasyStreamChallenges.Employee("Alice", "Engineering", 80000));

            assertEquals(80000.0,
                    EasyStreamChallenges.challenge8(employees, "Engineering"), 0.01);
        }

        @Test
        void emptyList() {
            assertEquals(0.0,
                    EasyStreamChallenges.challenge8(List.of(), "Engineering"), 0.01);
        }

        @Test
        void nullEmployees() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge8(null, "Engineering"));
        }

        @Test
        void nullDepartment() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge8(List.of(), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Filter, sort, join with prefix/suffix
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            assertEquals("[Alice, Carol]",
                    EasyStreamChallenges.challenge9(
                            List.of("Alice","Bob","Carol","Di","Eve"),
                            3, ", ", "[", "]"));
            // length > 3: Alice(5)✓ Bob(3)✗ Carol(5)✓ Di(2)✗ Eve(3)✗
        }

        @Test
        void noneQualify() {
            assertEquals("[]",
                    EasyStreamChallenges.challenge9(
                            List.of("hi","no","ok"),
                            5, ", ", "[", "]"));
        }

        @Test
        void allQualify() {
            assertEquals("[apple, cherry, mango]",
                    EasyStreamChallenges.challenge9(
                            List.of("mango","apple","cherry"),
                            3, ", ", "[", "]"));
        }

        @Test
        void differentSeparator() {
            assertEquals("<Alice|Carol>",
                    EasyStreamChallenges.challenge9(
                            List.of("Alice","Bob","Carol"),
                            3, "|", "<", ">"));
        }

        @Test
        void emptyList() {
            assertEquals("[]",
                    EasyStreamChallenges.challenge9(
                            List.of(), 3, ", ", "[", "]"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge9(null, 3, ", ", "[", "]"));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Paginated sublist using skip + limit
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void firstPage() {
            assertEquals(List.of(1,2,3),
                    EasyStreamChallenges.challenge10(
                            List.of(1,2,3,4,5,6,7,8,9,10), 3, 0));
        }

        @Test
        void secondPage() {
            assertEquals(List.of(4,5,6),
                    EasyStreamChallenges.challenge10(
                            List.of(1,2,3,4,5,6,7,8,9,10), 3, 1));
        }

        @Test
        void lastPartialPage() {
            // Page 3 with size 3 → only element 10 remains
            assertEquals(List.of(10),
                    EasyStreamChallenges.challenge10(
                            List.of(1,2,3,4,5,6,7,8,9,10), 3, 3));
        }

        @Test
        void beyondData() {
            assertTrue(EasyStreamChallenges.challenge10(
                    List.of(1,2,3,4,5), 3, 5).isEmpty());
        }

        @Test
        void pageSizeEqualsListSize() {
            assertEquals(List.of(1,2,3,4,5),
                    EasyStreamChallenges.challenge10(
                            List.of(1,2,3,4,5), 5, 0));
        }

        @Test
        void pageSizeOne() {
            assertEquals(List.of(3),
                    EasyStreamChallenges.challenge10(
                            List.of(1,2,3,4,5), 1, 2));
        }

        @Test
        void emptyList() {
            assertTrue(EasyStreamChallenges.challenge10(
                    List.of(), 3, 0).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge10(null, 3, 0));
        }

        @Test
        void invalidPageSize() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge10(List.of(1,2), 0, 0));
        }

        @Test
        void invalidPageNumber() {
            assertThrows(IllegalArgumentException.class,
                    () -> EasyStreamChallenges.challenge10(List.of(1,2), 3, -1));
        }
    }
}