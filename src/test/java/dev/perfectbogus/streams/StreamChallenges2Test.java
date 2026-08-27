package dev.perfectbogus.streams;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — summaryStatistics for salary stats
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<StreamChallenges2.Employee> employees = List.of(
                    new StreamChallenges2.Employee("Alice",  "Eng", 95000, 5),
                    new StreamChallenges2.Employee("Bob",    "Mkt", 60000, 2),
                    new StreamChallenges2.Employee("Carol",  "Eng", 85000, 3),
                    new StreamChallenges2.Employee("Diana",  "HR",  70000, 7)
            );
            StreamChallenges2.SalaryStats result = StreamChallenges2.challenge1(employees);

            assertEquals(60000.0,  result.min(),   0.01);
            assertEquals(95000.0,  result.max(),   0.01);
            assertEquals(310000.0, result.sum(),   0.01);
            assertEquals(4L,       result.count());
        }

        @Test
        void singleEmployee() {
            List<StreamChallenges2.Employee> single = List.of(
                    new StreamChallenges2.Employee("Alice", "Eng", 80000, 5));

            StreamChallenges2.SalaryStats result = StreamChallenges2.challenge1(single);

            assertEquals(80000.0, result.min(),   0.01);
            assertEquals(80000.0, result.max(),   0.01);
            assertEquals(80000.0, result.sum(),   0.01);
            assertEquals(1L,      result.count());
        }

        @Test
        void allSameSalary() {
            List<StreamChallenges2.Employee> same = List.of(
                    new StreamChallenges2.Employee("A", "Eng", 70000, 5),
                    new StreamChallenges2.Employee("B", "Eng", 70000, 3)
            );
            StreamChallenges2.SalaryStats result = StreamChallenges2.challenge1(same);

            assertEquals(70000.0,  result.min(), 0.01);
            assertEquals(70000.0,  result.max(), 0.01);
            assertEquals(140000.0, result.sum(), 0.01);
            assertEquals(2L,       result.count());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Top N largest numbers sorted DESC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            assertEquals(List.of(12,11,9,8),
                    StreamChallenges2.challenge2(
                            List.of(3,1,5,12,2,11,7,4,9,8), 4));
        }

        @Test
        void nGreaterThanSize() {
            assertEquals(List.of(5,3,1),
                    StreamChallenges2.challenge2(List.of(3,1,5), 10));
        }

        @Test
        void nEqualsOne() {
            assertEquals(List.of(12),
                    StreamChallenges2.challenge2(List.of(3,12,5,1), 1));
        }

        @Test
        void nEqualsSize() {
            assertEquals(List.of(5,4,3,2,1),
                    StreamChallenges2.challenge2(List.of(1,2,3,4,5), 5));
        }

        @Test
        void withDuplicates() {
            assertEquals(List.of(9,9,5),
                    StreamChallenges2.challenge2(List.of(9,9,5,3,1), 3));
        }

        @Test
        void emptyList() {
            assertTrue(StreamChallenges2.challenge2(List.of(), 3).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge2(null, 3));
        }

        @Test
        void invalidN() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge2(List.of(1,2,3), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — collect(toMap) word → length
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            Map<String, Integer> result = StreamChallenges2.challenge3(
                    List.of("apple","banana","cherry","fig"));

            assertEquals(5, result.get("apple"));
            assertEquals(6, result.get("banana"));
            assertEquals(6, result.get("cherry"));
            assertEquals(3, result.get("fig"));
            assertEquals(4, result.size());
        }

        @Test
        void singleWord() {
            Map<String, Integer> result = StreamChallenges2.challenge3(List.of("hello"));
            assertEquals(5, result.get("hello"));
            assertEquals(1, result.size());
        }

        @Test
        void emptyList() {
            assertTrue(StreamChallenges2.challenge3(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — flatMap(Optional::stream) to extract present values
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<Optional<String>> optionals = List.of(
                    Optional.of("apple"),
                    Optional.empty(),
                    Optional.of("banana"),
                    Optional.empty(),
                    Optional.of("cherry")
            );
            assertEquals(List.of("apple","banana","cherry"),
                    StreamChallenges2.challenge4(optionals));
        }

        @Test
        void allPresent() {
            List<Optional<String>> optionals = List.of(
                    Optional.of("a"), Optional.of("b"), Optional.of("c"));

            assertEquals(List.of("a","b","c"),
                    StreamChallenges2.challenge4(optionals));
        }

        @Test
        void allEmpty() {
            List<Optional<String>> optionals = List.of(
                    Optional.empty(), Optional.empty());

            assertTrue(StreamChallenges2.challenge4(optionals).isEmpty());
        }

        @Test
        void singlePresent() {
            assertEquals(List.of("hello"),
                    StreamChallenges2.challenge4(List.of(Optional.of("hello"))));
        }

        @Test
        void emptyList() {
            assertTrue(StreamChallenges2.challenge4(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — partitioningBy salary threshold → names sorted
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<StreamChallenges2.Employee> employees = List.of(
                    new StreamChallenges2.Employee("Alice", "Eng", 95000, 5),
                    new StreamChallenges2.Employee("Bob",   "Mkt", 60000, 2),
                    new StreamChallenges2.Employee("Carol", "Eng", 85000, 3),
                    new StreamChallenges2.Employee("Diana", "HR",  70000, 7)
            );
            Map<Boolean, List<String>> result =
                    StreamChallenges2.challenge5(employees, 75000);

            assertEquals(List.of("Alice","Carol"), result.get(true));  // sorted!
            assertEquals(List.of("Bob","Diana"),   result.get(false)); // sorted!
        }

        @Test
        void exactBoundaryIsBelow() {
            List<StreamChallenges2.Employee> employees = List.of(
                    new StreamChallenges2.Employee("A", "Eng", 75000, 5), // NOT > 75000!
                    new StreamChallenges2.Employee("B", "Eng", 75001, 5)  // > 75000 ✓
            );
            Map<Boolean, List<String>> result =
                    StreamChallenges2.challenge5(employees, 75000);

            assertEquals(List.of("B"), result.get(true));
            assertEquals(List.of("A"), result.get(false));
        }

        @Test
        void allAboveThreshold() {
            List<StreamChallenges2.Employee> employees = List.of(
                    new StreamChallenges2.Employee("Alice", "Eng", 90000, 5),
                    new StreamChallenges2.Employee("Bob",   "Eng", 80000, 3)
            );
            Map<Boolean, List<String>> result =
                    StreamChallenges2.challenge5(employees, 50000);

            assertEquals(List.of("Alice","Bob"), result.get(true));
            assertTrue(result.get(false).isEmpty());
        }

        @Test
        void emptyList() {
            Map<Boolean, List<String>> result =
                    StreamChallenges2.challenge5(List.of(), 75000);

            assertTrue(result.get(true).isEmpty());
            assertTrue(result.get(false).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge5(null, 75000));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — IntStream.range() to pair index with value
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            assertEquals(List.of("0:apple","1:banana","2:cherry"),
                    StreamChallenges2.challenge6(List.of("apple","banana","cherry")));
        }

        @Test
        void singleElement() {
            assertEquals(List.of("0:hello"),
                    StreamChallenges2.challenge6(List.of("hello")));
        }

        @Test
        void numbersAsStrings() {
            assertEquals(List.of("0:a","1:b","2:c","3:d"),
                    StreamChallenges2.challenge6(List.of("a","b","c","d")));
        }

        @Test
        void emptyList() {
            assertTrue(StreamChallenges2.challenge6(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Stream.concat + distinct + sorted → unmodifiable
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            List<String> result = StreamChallenges2.challenge7(
                    List.of("banana","apple","cherry","date"),
                    List.of("cherry","elderberry","apple","fig"));

            assertEquals(List.of("apple","banana","cherry","date","elderberry","fig"),
                    result);
        }

        @Test
        void noOverlap() {
            List<String> result = StreamChallenges2.challenge7(
                    List.of("apple","banana"),
                    List.of("cherry","date"));

            assertEquals(List.of("apple","banana","cherry","date"), result);
        }

        @Test
        void completeOverlap() {
            List<String> result = StreamChallenges2.challenge7(
                    List.of("apple","banana"),
                    List.of("apple","banana"));

            assertEquals(List.of("apple","banana"), result);
        }

        @Test
        void resultIsUnmodifiable() {
            List<String> result = StreamChallenges2.challenge7(
                    List.of("apple"), List.of("banana"));

            assertThrows(UnsupportedOperationException.class,
                    () -> result.add("cherry"));
        }

        @Test
        void emptyFirstList() {
            List<String> result = StreamChallenges2.challenge7(
                    List.of(),
                    List.of("apple","banana"));

            assertEquals(List.of("apple","banana"), result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge7(null, List.of("a")));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — groupingBy(length) + mapping + toSet
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            Map<Integer, Set<String>> result = StreamChallenges2.challenge8(
                    List.of("apple","fig","grape","bee","mango","ant","cherry"));

            assertEquals(Set.of("fig","bee","ant"),         result.get(3));
            assertEquals(Set.of("apple","grape","mango"),   result.get(5));
            assertEquals(Set.of("cherry"),                  result.get(6));
        }

        @Test
        void allSameLength() {
            Map<Integer, Set<String>> result = StreamChallenges2.challenge8(
                    List.of("cat","dog","ant","bat"));

            assertEquals(1, result.size());
            assertEquals(Set.of("cat","dog","ant","bat"), result.get(3));
        }

        @Test
        void singleWord() {
            Map<Integer, Set<String>> result = StreamChallenges2.challenge8(
                    List.of("hello"));

            assertEquals(Set.of("hello"), result.get(5));
        }

        @Test
        void duplicatesInSet() {
            // Sets remove duplicates automatically
            Map<Integer, Set<String>> result = StreamChallenges2.challenge8(
                    List.of("cat","cat","dog"));

            assertEquals(Set.of("cat","dog"), result.get(3));
        }

        @Test
        void emptyList() {
            assertTrue(StreamChallenges2.challenge8(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Top N most frequent words across sentences
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            List<String> result = StreamChallenges2.challenge9(List.of(
                    "the cat sat on the mat",
                    "the cat in the hat",
                    "the cat sat"), 3);

            // the=5, cat=3, sat=2, on=1, mat=1, in=1, hat=1
            assertEquals("the", result.get(0));
            assertEquals("cat", result.get(1));
            assertEquals("sat", result.get(2));
        }

        @Test
        void tieBreakByAlpha() {
            List<String> result = StreamChallenges2.challenge9(List.of(
                    "b b a a c c"), 3);

            // a=2, b=2, c=2 → same freq → alpha ASC: a,b,c
            assertEquals("a", result.get(0));
            assertEquals("b", result.get(1));
            assertEquals("c", result.get(2));
        }

        @Test
        void topNGreaterThanWords() {
            List<String> result = StreamChallenges2.challenge9(
                    List.of("hello world"), 10);

            assertEquals(2, result.size());
        }

        @Test
        void singleSentence() {
            List<String> result = StreamChallenges2.challenge9(
                    List.of("java java streams"), 2);

            assertEquals("java",    result.get(0)); // freq=2
            assertEquals("streams", result.get(1)); // freq=1
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge9(null, 3));
        }

        @Test
        void invalidTopN() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge9(List.of("hello"), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Collatz sequence using Stream.iterate()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            // 6→3→10→5→16→8→4→2→1
            assertEquals(List.of(6L,3L,10L,5L,16L,8L,4L,2L,1L),
                    StreamChallenges2.challenge10(6));
        }

        @Test
        void startingAtOne() {
            assertEquals(List.of(1L), StreamChallenges2.challenge10(1));
        }

        @Test
        void startingAtTwo() {
            // 2→1
            assertEquals(List.of(2L,1L), StreamChallenges2.challenge10(2));
        }

        @Test
        void startingAtTwelve() {
            // 12→6→3→10→5→16→8→4→2→1
            List<Long> result = StreamChallenges2.challenge10(12);
            assertEquals(10, result.size());
            assertEquals(12L, result.get(0));
            assertEquals(1L,  result.get(result.size() - 1)); // always ends with 1
        }

        @Test
        void alwaysEndsWithOne() {
            for (long n : new long[]{1,2,3,4,5,6,7,8,9,10,27}) {
                List<Long> result = StreamChallenges2.challenge10(n);
                assertFalse(result.isEmpty());
                assertEquals(1L, result.get(result.size() - 1),
                        "Sequence for n=" + n + " should end with 1");
            }
        }

        @Test
        void firstElementIsN() {
            assertEquals(27L, StreamChallenges2.challenge10(27).get(0));
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge10(0));
        }

        @Test
        void negativeInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges2.challenge10(-5));
        }
    }
}