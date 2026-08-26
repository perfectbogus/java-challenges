package dev.perfectbogus.streams;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — flatMap sentences → words, filter length, group by first char
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            Map<Character, Long> result = StreamChallenges.challenge1(
                    List.of("apple avocado banana",
                            "cherry apricot fig",
                            "blueberry grape date"),
                    4);

            // words > 4 chars: apple,avocado,banana,cherry,apricot,blueberry,grape
            assertEquals(3L, result.get('a')); // apple,avocado,apricot
            assertEquals(2L, result.get('b')); // banana,blueberry
            assertEquals(1L, result.get('c')); // cherry
            assertEquals(1L, result.get('g')); // grape
            assertNull(result.get('f'));        // fig has length=3, excluded!
            assertNull(result.get('d'));        // date has length=4, NOT > 4!
            assertEquals(4, result.size());
        }

        @Test
        void minLengthZeroKeepsAll() {
            Map<Character, Long> result = StreamChallenges.challenge1(
                    List.of("hi cat"), 0);

            // all words kept: hi(2>0), cat(3>0)
            assertEquals(1L, result.get('h'));
            assertEquals(1L, result.get('c'));
        }

        @Test
        void allWordsTooShort() {
            Map<Character, Long> result = StreamChallenges.challenge1(
                    List.of("hi it is"), 5);

            // all words length <= 5 → none qualify
            assertTrue(result.isEmpty());
        }

        @Test
        void singleSentence() {
            Map<Character, Long> result = StreamChallenges.challenge1(
                    List.of("stream filter collect"), 5);

            // stream(6)>5, filter(6)>5, collect(7)>5 → all qualify
            assertEquals(1L, result.get('s')); // stream
            assertEquals(1L, result.get('f')); // filter
            assertEquals(1L, result.get('c')); // collect
        }

        @Test
        void multipleWordsPerFirstChar() {
            Map<Character, Long> result = StreamChallenges.challenge1(
                    List.of("alpha arctic animal about"), 4);

            // all > 4 chars, all start with 'a'
            assertEquals(4L, result.get('a'));
        }

        @Test
        void emptyList() {
            assertTrue(StreamChallenges.challenge1(List.of(), 3).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges.challenge1(null, 3));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Max profit from one buy-sell using reduce()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            // buy@1, sell@6 → profit=5
            assertEquals(5, StreamChallenges.challenge2(List.of(7,1,5,3,6,4)));
        }

        @Test
        void decreasingPrices() {
            // prices only go down → no profit possible
            assertEquals(0, StreamChallenges.challenge2(List.of(7,6,4,3,1)));
        }

        @Test
        void increasingPrices() {
            // buy first, sell last → profit=4
            assertEquals(4, StreamChallenges.challenge2(List.of(1,2,3,4,5)));
        }

        @Test
        void singlePrice() {
            // can't buy and sell → profit=0
            assertEquals(0, StreamChallenges.challenge2(List.of(5)));
        }

        @Test
        void twoPrices() {
            assertEquals(3, StreamChallenges.challenge2(List.of(1,4)));
        }

        @Test
        void twoDecreasingPrices() {
            assertEquals(0, StreamChallenges.challenge2(List.of(4,1)));
        }

        @Test
        void allSamePrice() {
            assertEquals(0, StreamChallenges.challenge2(List.of(5,5,5,5)));
        }

        @Test
        void profitAtStart() {
            // best buy at index 0, best sell at index 1
            assertEquals(10, StreamChallenges.challenge2(List.of(1,11,5,3,2)));
        }

        @Test
        void largeSpread() {
            assertEquals(999, StreamChallenges.challenge2(List.of(1,1000,500,200)));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — takeWhile + dropWhile to split at threshold
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(1,2,3,5,5,6,7,8,9), 5);

            assertEquals(List.of(1,2,3), result.below()); // < 5
            assertEquals(List.of(6,7,8,9), result.above()); // > 5 (5s excluded!)
        }

        @Test
        void thresholdNotInList() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(2,4,6,8,10), 5);

            assertEquals(List.of(2,4), result.below());    // < 5
            assertEquals(List.of(6,8,10), result.above()); // > 5
        }

        @Test
        void allBelowThreshold() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(1,2,3,4), 10);

            assertEquals(List.of(1,2,3,4), result.below());
            assertTrue(result.above().isEmpty());
        }

        @Test
        void allAboveThreshold() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(6,7,8,9), 5);

            assertTrue(result.below().isEmpty());
            assertEquals(List.of(6,7,8,9), result.above());
        }

        @Test
        void thresholdAtStart() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(1,2,3,4,5), 1);

            assertTrue(result.below().isEmpty()); // nothing < 1
            assertEquals(List.of(2,3,4,5), result.above()); // > 1
        }

        @Test
        void thresholdAtEnd() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(1,2,3,4,5), 5);

            assertEquals(List.of(1,2,3,4), result.below());
            assertTrue(result.above().isEmpty()); // nothing > 5
        }

        @Test
        void emptyList() {
            StreamChallenges.SplitResult result = StreamChallenges.challenge3(
                    List.of(), 5);

            assertTrue(result.below().isEmpty());
            assertTrue(result.above().isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges.challenge3(null, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Fibonacci using Stream.iterate()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void firstEight() {
            assertEquals(List.of(0L,1L,1L,2L,3L,5L,8L,13L),
                    StreamChallenges.challenge4(8));
        }

        @Test
        void firstOne() {
            assertEquals(List.of(0L), StreamChallenges.challenge4(1));
        }

        @Test
        void firstTwo() {
            assertEquals(List.of(0L,1L), StreamChallenges.challenge4(2));
        }

        @Test
        void firstFive() {
            assertEquals(List.of(0L,1L,1L,2L,3L), StreamChallenges.challenge4(5));
        }

        @Test
        void first15() {
            List<Long> result = StreamChallenges.challenge4(15);
            assertEquals(15, result.size());
            assertEquals(0L,   result.get(0));
            assertEquals(1L,   result.get(1));
            assertEquals(377L, result.get(14)); // F(14)=377
        }

        @Test
        void correctSequenceLength() {
            assertEquals(10, StreamChallenges.challenge4(10).size());
        }

        @Test
        void eachTermIsSumOfPrevTwo() {
            List<Long> result = StreamChallenges.challenge4(10);
            for (int i = 2; i < result.size(); i++) {
                assertEquals(result.get(i-1) + result.get(i-2), result.get(i),
                        "F(" + i + ") should equal F(" + (i-1) + ") + F(" + (i-2) + ")");
            }
        }

        @Test
        void invalidInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges.challenge4(0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Nested groupingBy → find dept+tier with highest avg salary
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<StreamChallenges.Employee> employees = List.of(
                    new StreamChallenges.Employee("Alice", "Engineering", 95000, 8),
                    new StreamChallenges.Employee("Bob",   "Marketing",   60000, 2),
                    new StreamChallenges.Employee("Carol", "Engineering", 85000, 3),
                    new StreamChallenges.Employee("Diana", "Marketing",   70000, 7),
                    new StreamChallenges.Employee("Eve",   "Engineering", 90000, 6)
            );
            String result = StreamChallenges.challenge5(employees);

            // Engineering SENIOR: (95000+90000)/2=92500 ← highest!
            assertEquals("Engineering-SENIOR=92500.00", result);
        }

        @Test
        void singleEmployee() {
            List<StreamChallenges.Employee> single = List.of(
                    new StreamChallenges.Employee("Alice", "Engineering", 95000, 8)
            );
            String result = StreamChallenges.challenge5(single);
            assertEquals("Engineering-SENIOR=95000.00", result);
        }

        @Test
        void allJunior() {
            List<StreamChallenges.Employee> juniors = List.of(
                    new StreamChallenges.Employee("Alice", "Engineering", 80000, 2),
                    new StreamChallenges.Employee("Bob",   "Marketing",   70000, 1),
                    new StreamChallenges.Employee("Carol", "Engineering", 90000, 3)
            );
            String result = StreamChallenges.challenge5(juniors);

            // Engineering JUNIOR avg = (80000+90000)/2 = 85000
            // Marketing JUNIOR avg = 70000
            // Highest = Engineering JUNIOR 85000
            assertEquals("Engineering-JUNIOR=85000.00", result);
        }

        @Test
        void exactlyFiveYearsIsSenior() {
            List<StreamChallenges.Employee> employees = List.of(
                    new StreamChallenges.Employee("Alice", "HR", 80000, 5), // SENIOR (>= 5)
                    new StreamChallenges.Employee("Bob",   "HR", 60000, 4)  // JUNIOR (< 5)
            );
            String result = StreamChallenges.challenge5(employees);

            // HR SENIOR avg=80000 > HR JUNIOR avg=60000
            assertEquals("HR-SENIOR=80000.00", result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges.challenge5(null));
        }

        @Test
        void emptyInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> StreamChallenges.challenge5(List.of()));
        }
    }
}