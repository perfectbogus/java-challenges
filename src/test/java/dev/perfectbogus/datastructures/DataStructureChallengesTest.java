package dev.perfectbogus.datastructures;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DataStructureChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Valid Parentheses using Deque as stack
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void validMixed() {
            assertTrue(DataStructureChallenges.challenge1("([]{})"));
        }

        @Test
        void invalidWrongOrder() {
            assertFalse(DataStructureChallenges.challenge1("([)]"));
        }

        @Test
        void validNested() {
            assertTrue(DataStructureChallenges.challenge1("{[]}"));
        }

        @Test
        void invalidUnclosed() {
            assertFalse(DataStructureChallenges.challenge1("((("));
        }

        @Test
        void emptyStringValid() {
            assertTrue(DataStructureChallenges.challenge1(""));
        }

        @Test
        void singlePair() {
            assertTrue(DataStructureChallenges.challenge1("()"));
        }

        @Test
        void onlyOpenBrackets() {
            assertFalse(DataStructureChallenges.challenge1("{["));
        }

        @Test
        void onlyCloseBrackets() {
            assertFalse(DataStructureChallenges.challenge1("})"));
        }

        @Test
        void validComplex() {
            assertTrue(DataStructureChallenges.challenge1("({[()]})"));
        }

        @Test
        void mismatchedBrackets() {
            assertFalse(DataStructureChallenges.challenge1("(}"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Running Average using ArrayDeque sliding window
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            List<Double> result = DataStructureChallenges.challenge2(
                    List.of(1, 2, 3, 4, 5), 3);

            assertEquals(5, result.size());
            assertEquals(1.0,  result.get(0), 0.001); // [1]
            assertEquals(1.5,  result.get(1), 0.001); // [1,2]
            assertEquals(2.0,  result.get(2), 0.001); // [1,2,3]
            assertEquals(3.0,  result.get(3), 0.001); // [2,3,4]
            assertEquals(4.0,  result.get(4), 0.001); // [3,4,5]
        }

        @Test
        void windowSizeOne() {
            List<Double> result = DataStructureChallenges.challenge2(
                    List.of(5, 10, 15), 1);

            assertEquals(5.0,  result.get(0), 0.001);
            assertEquals(10.0, result.get(1), 0.001);
            assertEquals(15.0, result.get(2), 0.001);
        }

        @Test
        void windowSizeEqualsListSize() {
            List<Double> result = DataStructureChallenges.challenge2(
                    List.of(1, 2, 3, 4), 4);

            assertEquals(1.0,   result.get(0), 0.001); // [1]
            assertEquals(1.5,   result.get(1), 0.001); // [1,2]
            assertEquals(2.0,   result.get(2), 0.001); // [1,2,3]
            assertEquals(2.5,   result.get(3), 0.001); // [1,2,3,4]
        }

        @Test
        void withNegativeNumbers() {
            List<Double> result = DataStructureChallenges.challenge2(
                    List.of(-1, -2, -3), 2);

            assertEquals(-1.0,  result.get(0), 0.001); // [-1]
            assertEquals(-1.5,  result.get(1), 0.001); // [-1,-2]
            assertEquals(-2.5,  result.get(2), 0.001); // [-2,-3]
        }

        @Test
        void singleElement() {
            List<Double> result = DataStructureChallenges.challenge2(List.of(42), 1);
            assertEquals(1, result.size());
            assertEquals(42.0, result.get(0), 0.001);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge2(null, 3));
        }

        @Test
        void invalidWindowSize() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge2(List.of(1, 2, 3), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — K Largest Elements using PriorityQueue min heap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            List<Integer> result = DataStructureChallenges.challenge3(
                    new int[]{3, 1, 5, 12, 2, 11, 7}, 3);

            assertEquals(List.of(12, 11, 7), result);
        }

        @Test
        void kEqualsOne() {
            List<Integer> result = DataStructureChallenges.challenge3(
                    new int[]{3, 1, 5, 12, 2}, 1);

            assertEquals(List.of(12), result);
        }

        @Test
        void kEqualsLength() {
            List<Integer> result = DataStructureChallenges.challenge3(
                    new int[]{3, 1, 5}, 3);

            assertEquals(List.of(5, 3, 1), result);
        }

        @Test
        void withDuplicates() {
            List<Integer> result = DataStructureChallenges.challenge3(
                    new int[]{5, 5, 5, 5}, 2);

            assertEquals(List.of(5, 5), result);
        }

        @Test
        void withNegatives() {
            List<Integer> result = DataStructureChallenges.challenge3(
                    new int[]{-5, -1, -3, -2}, 2);

            assertEquals(List.of(-1, -2), result);
        }

        @Test
        void singleElement() {
            List<Integer> result = DataStructureChallenges.challenge3(
                    new int[]{42}, 1);

            assertEquals(List.of(42), result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge3(null, 2));
        }

        @Test
        void invalidK() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge3(new int[]{1, 2, 3}, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — First Non-Repeating Character using LinkedHashMap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            assertEquals('l', DataStructureChallenges.challenge4("leetcode"));
        }

        @Test
        void noUniqueChar() {
            assertEquals('-', DataStructureChallenges.challenge4("aabb"));
        }

        @Test
        void lastCharUnique() {
            assertEquals('c', DataStructureChallenges.challenge4("aabbc"));
        }

        @Test
        void singleChar() {
            assertEquals('z', DataStructureChallenges.challenge4("z"));
        }

        @Test
        void allSameChar() {
            assertEquals('-', DataStructureChallenges.challenge4("aaaa"));
        }

        @Test
        void firstCharUnique() {
            assertEquals('a', DataStructureChallenges.challenge4("abcbc"));
        }

        @Test
        void emptyString() {
            assertEquals('-', DataStructureChallenges.challenge4(""));
        }

        @Test
        void longerString() {
            // a=2,b=2,c=1,d=1 → first unique is 'c' at index 4
            assertEquals('c', DataStructureChallenges.challenge4("aabbcde"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — TreeMap range count, floor and ceiling
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            DataStructureChallenges.RangeResult result = DataStructureChallenges.challenge5(
                    List.of(1, 3, 5, 7, 9, 11, 13), 4, 10, 6);

            assertEquals(3,  result.count());    // 5,7,9 in [4,10]
            assertEquals(5,  result.floor());    // largest key <= 6
            assertEquals(7,  result.ceiling());  // smallest key >= 6
        }

        @Test
        void queryExistsInMap() {
            DataStructureChallenges.RangeResult result = DataStructureChallenges.challenge5(
                    List.of(1, 3, 5, 7, 9), 3, 7, 5);

            assertEquals(3, result.count());     // 3,5,7 in [3,7]
            assertEquals(5, result.floor());     // 5 itself
            assertEquals(5, result.ceiling());   // 5 itself
        }

        @Test
        void floorDoesNotExist() {
            DataStructureChallenges.RangeResult result = DataStructureChallenges.challenge5(
                    List.of(5, 7, 9), 1, 10, 3);

            assertEquals(3, result.count());
            assertNull(result.floor());          // no key <= 3
            assertEquals(5, result.ceiling());   // smallest >= 3
        }

        @Test
        void ceilingDoesNotExist() {
            DataStructureChallenges.RangeResult result = DataStructureChallenges.challenge5(
                    List.of(1, 3, 5), 1, 10, 7);

            assertEquals(3, result.count());
            assertEquals(5, result.floor());     // largest <= 7
            assertNull(result.ceiling());        // no key >= 7
        }

        @Test
        void noneInRange() {
            DataStructureChallenges.RangeResult result = DataStructureChallenges.challenge5(
                    List.of(1, 2, 3, 10, 11, 12), 4, 9, 6);

            assertEquals(0, result.count());    // nothing in [4,9]
            assertEquals(3, result.floor());
            assertEquals(10, result.ceiling());
        }

        @Test
        void allInRange() {
            DataStructureChallenges.RangeResult result = DataStructureChallenges.challenge5(
                    List.of(2, 4, 6, 8), 1, 10, 5);

            assertEquals(4, result.count());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge5(null, 1, 10, 5));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Max Stack using two ArrayDeques
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            List<Integer> result = DataStructureChallenges.challenge6(
                    List.of("push:5","push:3","push:7","getMax","pop","getMax","peek"));

            assertEquals(3, result.size());
            assertEquals(7, result.get(0)); // getMax after [5,3,7]
            assertEquals(5, result.get(1)); // getMax after [5,3] (7 popped)
            assertEquals(3, result.get(2)); // peek at top of [5,3]
        }

        @Test
        void maxAfterPoppingMax() {
            List<Integer> result = DataStructureChallenges.challenge6(
                    List.of("push:10","push:5","push:3","getMax","pop","getMax","pop","getMax"));

            assertEquals(10, result.get(0)); // getMax = 10
            assertEquals(10, result.get(1)); // getMax still 10 (5 popped, 10 remains)
            assertEquals(10, result.get(2)); // getMax still 10 (3 popped)
        }

        @Test
        void ascendingPushes() {
            List<Integer> result = DataStructureChallenges.challenge6(
                    List.of("push:1","push:2","push:3","getMax"));

            assertEquals(1, result.size());
            assertEquals(3, result.get(0));
        }

        @Test
        void descendingPushes() {
            List<Integer> result = DataStructureChallenges.challenge6(
                    List.of("push:3","push:2","push:1","getMax"));

            assertEquals(3, result.get(0)); // max always 3
        }

        @Test
        void peekDoesNotRemove() {
            List<Integer> result = DataStructureChallenges.challenge6(
                    List.of("push:5","push:3","peek","peek","getMax"));

            assertEquals(3, result.get(0)); // peek = 3
            assertEquals(3, result.get(1)); // peek still 3
            assertEquals(5, result.get(2)); // max still 5
        }

        @Test
        void emptyOperations() {
            assertTrue(DataStructureChallenges.challenge6(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Task Scheduler using PriorityQueue
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            List<DataStructureChallenges.Task> tasks = new ArrayList<>(List.of(
                    new DataStructureChallenges.Task("Deploy",  3, 5),
                    new DataStructureChallenges.Task("Test",    1, 2),
                    new DataStructureChallenges.Task("Review",  3, 3),
                    new DataStructureChallenges.Task("Meeting", 2, 1),
                    new DataStructureChallenges.Task("Fix Bug", 3, 4)
            ));
            List<String> result = DataStructureChallenges.challenge7(tasks);

            assertEquals(List.of("Deploy(5)","Fix Bug(4)","Review(3)","Meeting(1)","Test(2)"), result);
        }

        @Test
        void singleTask() {
            List<DataStructureChallenges.Task> tasks = new ArrayList<>(List.of(
                    new DataStructureChallenges.Task("Deploy", 3, 5)));

            assertEquals(List.of("Deploy(5)"), DataStructureChallenges.challenge7(tasks));
        }

        @Test
        void allSamePriority() {
            List<DataStructureChallenges.Task> tasks = new ArrayList<>(List.of(
                    new DataStructureChallenges.Task("Zebra", 1, 3),
                    new DataStructureChallenges.Task("Alpha", 1, 1),
                    new DataStructureChallenges.Task("Mango", 1, 2)
            ));
            List<String> result = DataStructureChallenges.challenge7(tasks);

            // Same priority → alpha ASC
            assertEquals("Alpha(1)", result.get(0));
            assertEquals("Mango(2)", result.get(1));
            assertEquals("Zebra(3)", result.get(2));
        }

        @Test
        void allSamePriority7_2() {
            List<DataStructureChallenges.Task> tasks = new ArrayList<>(List.of(
                    new DataStructureChallenges.Task("Zebra", 1, 3),
                    new DataStructureChallenges.Task("Alpha", 1, 1),
                    new DataStructureChallenges.Task("Mango", 1, 2)
            ));
            List<String> result = DataStructureChallenges.challenge7_2(tasks);

            // Same priority → alpha ASC
            assertEquals("Alpha(1)", result.get(0));
            assertEquals("Mango(2)", result.get(1));
            assertEquals("Zebra(3)", result.get(2));
        }

        @Test
        void highPriorityProcessedFirst() {
            List<DataStructureChallenges.Task> tasks = new ArrayList<>(List.of(
                    new DataStructureChallenges.Task("Low",  1, 10),
                    new DataStructureChallenges.Task("High", 9,  1)
            ));
            List<String> result = DataStructureChallenges.challenge7(tasks);

            assertEquals("High(1)", result.get(0));
            assertEquals("Low(10)", result.get(1));
        }

        @Test
        void emptyList() {
            assertTrue(DataStructureChallenges.challenge7(new ArrayList<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Top K Frequent Words using HashMap + PriorityQueue
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            List<String> result = DataStructureChallenges.challenge8(
                    List.of("apple","banana","apple","cherry","banana","apple","date","cherry"), 3);

            assertEquals(List.of("apple","banana","cherry"), result);
        }

        @Test
        void tieBreakByAlpha() {
            List<String> result = DataStructureChallenges.challenge8(
                    List.of("the","day","is","sunny","the","the","sunny","is","is"), 4);

            // the=3, is=3, sunny=2, day=1 → is before the (alpha)
            assertEquals(List.of("is","the","sunny","day"), result);
        }

        @Test
        void kEqualsOne() {
            List<String> result = DataStructureChallenges.challenge8(
                    List.of("apple","apple","banana","banana","banana"), 1);

            assertEquals(List.of("banana"), result);
        }

        @Test
        void allSameFrequency() {
            List<String> result = DataStructureChallenges.challenge8(
                    List.of("c","a","b"), 2);

            // All freq=1 → alpha ASC: a,b
            assertEquals(List.of("a","b"), result);
        }

        @Test
        void singleWord() {
            List<String> result = DataStructureChallenges.challenge8(List.of("hello"), 1);
            assertEquals(List.of("hello"), result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge8(null, 3));
        }

        @Test
        void invalidK() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge8(List.of("a","b"), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Evaluate Reverse Polish Notation using Deque as stack
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void addAndMultiply() {
            // (2+1)*3 = 9
            assertEquals(9, DataStructureChallenges.challenge9(
                    new String[]{"2","1","+","3","*"}));
        }

        @Test
        void divideAndAdd() {
            // 4 + (13/5) = 4 + 2 = 6
            assertEquals(6, DataStructureChallenges.challenge9(
                    new String[]{"4","13","5","/","+"}));
        }

        @Test
        void complexExpression() {
            // = 22
            assertEquals(22, DataStructureChallenges.challenge9(
                    new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
        }

        @Test
        void singleNumber() {
            assertEquals(42, DataStructureChallenges.challenge9(new String[]{"42"}));
        }

        @Test
        void subtraction() {
            // 5-3 = 2
            assertEquals(2, DataStructureChallenges.challenge9(
                    new String[]{"5","3","-"}));
        }

        @Test
        void divisionTruncatesZero() {
            // 7/2 = 3 (truncates, not 3.5)
            assertEquals(3, DataStructureChallenges.challenge9(
                    new String[]{"7","2","/"}));
        }

        @Test
        void negativeNumbers() {
            // -2 * -3 = 6
            assertEquals(6, DataStructureChallenges.challenge9(
                    new String[]{"-2","-3","*"}));
        }

        @Test
        void multipleOperations() {
            // ((3+4)*2)/7 = 14/7 = 2
            assertEquals(2, DataStructureChallenges.challenge9(
                    new String[]{"3","4","+","2","*","7","/"}));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge9(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Sliding Window Maximum using monotonic Deque
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            assertArrayEquals(
                    new int[]{3,3,5,5,6,7},
                    DataStructureChallenges.challenge10(
                            new int[]{1,3,-1,-3,5,3,6,7}, 3));
        }

        @Test
        void windowSizeOne() {
            // Window=1 → max of each single element = itself
            assertArrayEquals(
                    new int[]{1,3,-1,-3,5},
                    DataStructureChallenges.challenge10(
                            new int[]{1,3,-1,-3,5}, 1));
        }

        @Test
        void windowSizeEqualsArray() {
            // Window = entire array → single result
            assertArrayEquals(
                    new int[]{5},
                    DataStructureChallenges.challenge10(
                            new int[]{1,3,2,5,4}, 5));
        }

        @Test
        void allSameValues() {
            assertArrayEquals(
                    new int[]{3,3,3},
                    DataStructureChallenges.challenge10(
                            new int[]{3,3,3,3,3}, 3));
        }

        @Test
        void descendingArray() {
            // Descending → max always first element of window
            assertArrayEquals(
                    new int[]{5,4,3},
                    DataStructureChallenges.challenge10(
                            new int[]{5,4,3,2,1}, 3));
        }

        @Test
        void ascendingArray() {
            // Ascending → max always last element of window
            assertArrayEquals(
                    new int[]{3,4,5},
                    DataStructureChallenges.challenge10(
                            new int[]{1,2,3,4,5}, 3));
        }

        @Test
        void withNegatives() {
            assertArrayEquals(
                    new int[]{-1,-1,-2},
                    DataStructureChallenges.challenge10(
                            new int[]{-5,-3,-1,-2,-4}, 3));
        }

        @Test
        void singleElement() {
            assertArrayEquals(
                    new int[]{7},
                    DataStructureChallenges.challenge10(new int[]{7}, 1));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge10(null, 3));
        }

        @Test
        void invalidK() {
            assertThrows(IllegalArgumentException.class,
                    () -> DataStructureChallenges.challenge10(new int[]{1,2,3}, 0));
        }
    }
}