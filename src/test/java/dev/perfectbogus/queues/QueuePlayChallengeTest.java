package dev.perfectbogus.queues;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueuePlayChallengeTest {

    private static String[] names(int n) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = "n" + i;
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 1: serveInOrder
    // ==========================================================
    @Nested
    class ServeInOrderTests {

        @Test
        void testExample() {
            String[] commands = {"arrive ana", "arrive bob", "serve", "arrive cy", "serve", "serve", "serve"};
            assertEquals(List.of("ana", "bob", "cy"), QueuePlayChallenge.serveInOrder(commands));
        }

        @Test
        void testServeOnEmptyLineIsIgnored() {
            assertEquals(List.of("x"), QueuePlayChallenge.serveInOrder(new String[] {"serve", "arrive x", "serve"}));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), QueuePlayChallenge.serveInOrder(new String[] {}));
        }

        @Test
        void testNobodyIsServed() {
            assertEquals(List.of(), QueuePlayChallenge.serveInOrder(new String[] {"arrive a", "arrive b", "arrive c"}));
        }

        @Test
        void testOnlyServeCommands() {
            assertEquals(List.of(), QueuePlayChallenge.serveInOrder(new String[] {"serve", "serve"}));
        }

        @Test
        void testFirstInFirstOut() {
            assertEquals(List.of("a", "b"), QueuePlayChallenge.serveInOrder(
                    new String[] {"arrive a", "arrive b", "serve", "serve", "serve"}));
        }

        @Test
        void testServeBetweenArrivals() {
            assertEquals(List.of("a", "b"), QueuePlayChallenge.serveInOrder(
                    new String[] {"arrive a", "serve", "arrive b", "serve"}));
        }

        @Test
        void testSomeCustomersStayInTheLine() {
            assertEquals(List.of("a"), QueuePlayChallenge.serveInOrder(
                    new String[] {"arrive a", "arrive b", "arrive c", "serve"}));
        }

        @Test
        void testSameNameTwice() {
            assertEquals(List.of("a", "a"), QueuePlayChallenge.serveInOrder(
                    new String[] {"arrive a", "arrive a", "serve", "serve"}));
        }

        @Test
        void testNamesAreNotSorted() {
            assertEquals(List.of("zoe", "al", "mia"), QueuePlayChallenge.serveInOrder(
                    new String[] {"arrive zoe", "arrive al", "arrive mia", "serve", "serve", "serve"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] commands = {"arrive a", "serve"};
            QueuePlayChallenge.serveInOrder(commands);
            assertArrayEquals(new String[] {"arrive a", "serve"}, commands);
        }

        @Test
        void testBigInput() {
            String[] commands = new String[200_000];
            for (int i = 0; i < 100_000; i++) {
                commands[i] = "arrive c" + i;
                commands[i + 100_000] = "serve";
            }
            List<String> result = QueuePlayChallenge.serveInOrder(commands);
            assertEquals(100_000, result.size());
            assertEquals("c0", result.get(0));
            assertEquals("c99999", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 2: reverseWords
    // ==========================================================
    @Nested
    class ReverseWordsTests {

        @Test
        void testThreeWords() {
            assertEquals("c b a", QueuePlayChallenge.reverseWords("a b c"));
        }

        @Test
        void testTwoWords() {
            assertEquals("world hello", QueuePlayChallenge.reverseWords("hello world"));
        }

        @Test
        void testOneWord() {
            assertEquals("one", QueuePlayChallenge.reverseWords("one"));
        }

        @Test
        void testEmpty() {
            assertEquals("", QueuePlayChallenge.reverseWords(""));
        }

        @Test
        void testRepeatedWords() {
            assertEquals("b a a", QueuePlayChallenge.reverseWords("a a b"));
        }

        @Test
        void testNumbers() {
            assertEquals("333 22 1", QueuePlayChallenge.reverseWords("1 22 333"));
        }

        @Test
        void testTheCaseIsKept() {
            assertEquals("b A", QueuePlayChallenge.reverseWords("A b"));
        }

        @Test
        void testTheLettersOfEachWordKeepTheirOrder() {
            assertEquals("lazy dog the", QueuePlayChallenge.reverseWords("the dog lazy"));
        }

        @Test
        void testOneLetterWords() {
            assertEquals("e d c b a", QueuePlayChallenge.reverseWords("a b c d e"));
        }

        @Test
        void testLongSentence() {
            assertEquals("six five four three two one", QueuePlayChallenge.reverseWords("one two three four five six"));
        }

        @Test
        void testReversingTwiceGivesTheOriginal() {
            String sentence = "to be or not to be";
            assertEquals(sentence, QueuePlayChallenge.reverseWords(QueuePlayChallenge.reverseWords(sentence)));
        }

        @Test
        void testBigSentence() {
            StringBuilder original = new StringBuilder();
            StringBuilder expected = new StringBuilder();
            for (int i = 0; i < 100_000; i++) {
                original.append(i == 0 ? "" : " ").append("w").append(i);
                expected.append(i == 0 ? "" : " ").append("w").append(99_999 - i);
            }
            assertEquals(expected.toString(), QueuePlayChallenge.reverseWords(original.toString()));
        }
    }

    // ==========================================================
    // CHALLENGE 3: areBracketsBalanced
    // ==========================================================
    @Nested
    class AreBracketsBalancedTests {

        @Test
        void testAllKinds() {
            assertTrue(QueuePlayChallenge.areBracketsBalanced("([]{})"));
        }

        @Test
        void testWrongKind() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("(]"));
        }

        @Test
        void testNotClosed() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("(("));
        }

        @Test
        void testClosedBeforeOpened() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced(")("));
        }

        @Test
        void testOtherCharactersAreIgnored() {
            assertTrue(QueuePlayChallenge.areBracketsBalanced("a(b)c"));
            assertTrue(QueuePlayChallenge.areBracketsBalanced("(a[b]{c})"));
        }

        @Test
        void testEmpty() {
            assertTrue(QueuePlayChallenge.areBracketsBalanced(""));
        }

        @Test
        void testNoBrackets() {
            assertTrue(QueuePlayChallenge.areBracketsBalanced("hello world"));
        }

        @Test
        void testWrongOrder() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("([)]"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("{[}"));
        }

        @Test
        void testEveryClosingBracketMustMatch() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("(}"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("[)"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("{]"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("[}"));
        }

        @Test
        void testOnlyClosing() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("]"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("}"));
        }

        @Test
        void testOnlyOpening() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("("));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("{"));
        }

        @Test
        void testOneBracketTooMuch() {
            assertFalse(QueuePlayChallenge.areBracketsBalanced("(()"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("())"));
        }

        @Test
        void testSeveralGroups() {
            assertTrue(QueuePlayChallenge.areBracketsBalanced("()[]{}"));
            assertTrue(QueuePlayChallenge.areBracketsBalanced("((()))"));
            assertTrue(QueuePlayChallenge.areBracketsBalanced("[{()}]"));
        }

        @Test
        void testBigTexts() {
            String balanced = "(".repeat(100_000) + ")".repeat(100_000);
            assertTrue(QueuePlayChallenge.areBracketsBalanced(balanced));
            assertFalse(QueuePlayChallenge.areBracketsBalanced(balanced + ")"));
            assertFalse(QueuePlayChallenge.areBracketsBalanced("(" + balanced));
        }
    }

    // ==========================================================
    // CHALLENGE 4: kSmallest
    // ==========================================================
    @Nested
    class KSmallestTests {

        @Test
        void testExample() {
            assertArrayEquals(new int[] {1, 2}, QueuePlayChallenge.kSmallest(new int[] {5, 1, 4, 2, 3}, 2));
        }

        @Test
        void testRepeatedValues() {
            assertArrayEquals(new int[] {1, 3}, QueuePlayChallenge.kSmallest(new int[] {3, 3, 1}, 2));
            assertArrayEquals(new int[] {2, 2, 2}, QueuePlayChallenge.kSmallest(new int[] {2, 2, 2, 2}, 3));
        }

        @Test
        void testOneValue() {
            assertArrayEquals(new int[] {9}, QueuePlayChallenge.kSmallest(new int[] {9}, 1));
        }

        @Test
        void testKIsTheLength() {
            assertArrayEquals(new int[] {1, 2, 3, 4}, QueuePlayChallenge.kSmallest(new int[] {4, 3, 2, 1}, 4));
        }

        @Test
        void testKOne() {
            assertArrayEquals(new int[] {7}, QueuePlayChallenge.kSmallest(new int[] {10, 9, 8, 7}, 1));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[] {-5, -1}, QueuePlayChallenge.kSmallest(new int[] {-1, -5, 3, 0}, 2));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[] {Integer.MIN_VALUE, 0}, QueuePlayChallenge.kSmallest(
                    new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, 2));
            assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE}, QueuePlayChallenge.kSmallest(
                    new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE}, 2));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(new int[] {1, 2, 3}, QueuePlayChallenge.kSmallest(new int[] {1, 2, 3, 4, 5}, 3));
        }

        @Test
        void testSortedFromBiggestToSmallest() {
            assertArrayEquals(new int[] {1, 2}, QueuePlayChallenge.kSmallest(new int[] {5, 4, 3, 2, 1}, 2));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertArrayEquals(new int[] {1000, 1000, 5000}, QueuePlayChallenge.kSmallest(
                    new int[] {5000, 1000, 100_000, 1000}, 3));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {5, 1, 4, 2, 3};
            QueuePlayChallenge.kSmallest(nums, 2);
            assertArrayEquals(new int[] {5, 1, 4, 2, 3}, nums);
        }

        @Test
        void testBigArray() {
            int[] nums = new int[200_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums.length - 1 - i;
            }
            assertArrayEquals(new int[] {0, 1, 2}, QueuePlayChallenge.kSmallest(nums, 3));
            int[] all = QueuePlayChallenge.kSmallest(nums, 200_000);
            assertEquals(200_000, all.length);
            assertEquals(199_999, all[199_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 5: kthLargest
    // ==========================================================
    @Nested
    class KthLargestTests {

        @Test
        void testExampleOne() {
            assertEquals(5, QueuePlayChallenge.kthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
        }

        @Test
        void testExampleWithRepeatedValues() {
            assertEquals(4, QueuePlayChallenge.kthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        }

        @Test
        void testOneValue() {
            assertEquals(7, QueuePlayChallenge.kthLargest(new int[] {7}, 1));
        }

        @Test
        void testTheLargest() {
            assertEquals(6, QueuePlayChallenge.kthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 1));
        }

        @Test
        void testTheSmallest() {
            assertEquals(1, QueuePlayChallenge.kthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 9));
            assertEquals(1, QueuePlayChallenge.kthLargest(new int[] {3, 1, 2}, 3));
        }

        @Test
        void testInTheMiddle() {
            assertEquals(3, QueuePlayChallenge.kthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 5));
        }

        @Test
        void testRepeatedValuesCountEachTime() {
            assertEquals(5, QueuePlayChallenge.kthLargest(new int[] {5, 5, 5}, 2));
            assertEquals(1, QueuePlayChallenge.kthLargest(new int[] {1, 1, 2}, 2));
            assertEquals(1, QueuePlayChallenge.kthLargest(new int[] {1, 2}, 2));
        }

        @Test
        void testNegativeValues() {
            assertEquals(-1, QueuePlayChallenge.kthLargest(new int[] {-1, -2, -3}, 1));
            assertEquals(-3, QueuePlayChallenge.kthLargest(new int[] {-1, -2, -3}, 3));
        }

        @Test
        void testExtremeValues() {
            assertEquals(Integer.MIN_VALUE, QueuePlayChallenge.kthLargest(
                    new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, 3));
            assertEquals(Integer.MAX_VALUE, QueuePlayChallenge.kthLargest(
                    new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE}, 1));
        }

        @Test
        void testValuesBiggerThanTheCacheOfIntegers() {
            assertEquals(1000, QueuePlayChallenge.kthLargest(new int[] {1000, 1000, 5000}, 2));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {3, 2, 1, 5, 6, 4};
            QueuePlayChallenge.kthLargest(nums, 2);
            assertArrayEquals(new int[] {3, 2, 1, 5, 6, 4}, nums);
        }

        @Test
        void testBigArray() {
            int[] nums = new int[200_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) ((long) i * 37 % 200_000);
            }
            assertEquals(199_999, QueuePlayChallenge.kthLargest(nums, 1));
            assertEquals(100_000, QueuePlayChallenge.kthLargest(nums, 100_000));
            assertEquals(0, QueuePlayChallenge.kthLargest(nums, 200_000));
        }
    }

    // ==========================================================
    // CHALLENGE 6: mergeSortedArrays
    // ==========================================================
    @Nested
    class MergeSortedArraysTests {

        @Test
        void testThreeArrays() {
            assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, QueuePlayChallenge.mergeSortedArrays(
                    new int[][] {{1, 4, 7}, {2, 5}, {3, 6, 8}}));
        }

        @Test
        void testEmptyArraysInside() {
            assertArrayEquals(new int[] {1}, QueuePlayChallenge.mergeSortedArrays(new int[][] {{}, {1}, {}}));
        }

        @Test
        void testNoArrays() {
            assertArrayEquals(new int[] {}, QueuePlayChallenge.mergeSortedArrays(new int[][] {}));
        }

        @Test
        void testOnlyEmptyArrays() {
            assertArrayEquals(new int[] {}, QueuePlayChallenge.mergeSortedArrays(new int[][] {{}, {}}));
        }

        @Test
        void testOneArray() {
            assertArrayEquals(new int[] {1, 2, 3}, QueuePlayChallenge.mergeSortedArrays(new int[][] {{1, 2, 3}}));
        }

        @Test
        void testRepeatedValues() {
            assertArrayEquals(new int[] {1, 1, 1}, QueuePlayChallenge.mergeSortedArrays(new int[][] {{1, 1}, {1}}));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[] {-5, -3, 0}, QueuePlayChallenge.mergeSortedArrays(new int[][] {{-5, 0}, {-3}}));
        }

        @Test
        void testArraysThatDoNotOverlap() {
            assertArrayEquals(new int[] {1, 2, 5, 6},
                    QueuePlayChallenge.mergeSortedArrays(new int[][] {{5, 6}, {1, 2}}));
        }

        @Test
        void testManyArraysWithOneValue() {
            assertArrayEquals(new int[] {1, 2, 3}, QueuePlayChallenge.mergeSortedArrays(new int[][] {{3}, {1}, {2}}));
        }

        @Test
        void testExtremeValues() {
            assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE}, QueuePlayChallenge.mergeSortedArrays(
                    new int[][] {{Integer.MAX_VALUE}, {Integer.MIN_VALUE}}));
        }

        @Test
        void testOneArrayMuchLongerThanTheOthers() {
            assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, QueuePlayChallenge.mergeSortedArrays(
                    new int[][] {{2}, {1, 3, 4, 5, 6, 7, 8}, {9}}));
        }

        @Test
        void testOriginalsAreNotModified() {
            int[][] arrays = {{1, 4}, {2, 3}};
            QueuePlayChallenge.mergeSortedArrays(arrays);
            assertArrayEquals(new int[] {1, 4}, arrays[0]);
            assertArrayEquals(new int[] {2, 3}, arrays[1]);
        }

        @Test
        void testBigArrays() {
            int[][] arrays = new int[3][100_000];
            for (int a = 0; a < 3; a++) {
                for (int i = 0; i < 100_000; i++) {
                    arrays[a][i] = i * 3 + a;
                }
            }
            int[] result = QueuePlayChallenge.mergeSortedArrays(arrays);
            assertEquals(300_000, result.length);
            for (int i = 0; i < result.length; i++) {
                assertEquals(i, result[i]);
            }
        }
    }

    // ==========================================================
    // CHALLENGE 7: isPalindrome
    // ==========================================================
    @Nested
    class IsPalindromeTests {

        @Test
        void testPalindrome() {
            assertTrue(QueuePlayChallenge.isPalindrome("racecar"));
        }

        @Test
        void testNotPalindrome() {
            assertFalse(QueuePlayChallenge.isPalindrome("abca"));
        }

        @Test
        void testTheCaseMatters() {
            assertFalse(QueuePlayChallenge.isPalindrome("Aa"));
            assertTrue(QueuePlayChallenge.isPalindrome("AbA"));
        }

        @Test
        void testEmpty() {
            assertTrue(QueuePlayChallenge.isPalindrome(""));
        }

        @Test
        void testOneCharacter() {
            assertTrue(QueuePlayChallenge.isPalindrome("a"));
        }

        @Test
        void testEvenLength() {
            assertTrue(QueuePlayChallenge.isPalindrome("abba"));
            assertFalse(QueuePlayChallenge.isPalindrome("ab"));
        }

        @Test
        void testSpacesCount() {
            assertTrue(QueuePlayChallenge.isPalindrome("a b a"));
            assertFalse(QueuePlayChallenge.isPalindrome("A man"));
            assertFalse(QueuePlayChallenge.isPalindrome("ab ca"));
            assertFalse(QueuePlayChallenge.isPalindrome("ab a"));
        }

        @Test
        void testOnlyTheMiddleIsDifferent() {
            assertTrue(QueuePlayChallenge.isPalindrome("abcdcba"));
        }

        @Test
        void testOnlyTheEndsAreDifferent() {
            assertFalse(QueuePlayChallenge.isPalindrome("abccbx"));
            assertFalse(QueuePlayChallenge.isPalindrome("xbccba"));
        }

        @Test
        void testCharactersBiggerThanTheCacheOfCharacters() {
            assertTrue(QueuePlayChallenge.isPalindrome("éxé"));
            assertFalse(QueuePlayChallenge.isPalindrome("éxñ"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertTrue(QueuePlayChallenge.isPalindrome("12!21"));
            assertFalse(QueuePlayChallenge.isPalindrome("12!12"));
        }

        @Test
        void testBigTexts() {
            String half = "a".repeat(200_000);
            assertTrue(QueuePlayChallenge.isPalindrome(half + "b" + half));
            assertFalse(QueuePlayChallenge.isPalindrome(half + "bc" + half));
            assertFalse(QueuePlayChallenge.isPalindrome("c" + half + "b" + half));
        }
    }

    // ==========================================================
    // CHALLENGE 8: lastSurvivor
    // ==========================================================
    @Nested
    class LastSurvivorTests {

        @Test
        void testEveryTwo() {
            assertEquals("n2", QueuePlayChallenge.lastSurvivor(names(5), 2));
        }

        @Test
        void testEveryOne() {
            assertEquals("n4", QueuePlayChallenge.lastSurvivor(names(5), 1));
        }

        @Test
        void testOnePerson() {
            assertEquals("n0", QueuePlayChallenge.lastSurvivor(names(1), 5));
        }

        @Test
        void testSevenAndThree() {
            assertEquals("n3", QueuePlayChallenge.lastSurvivor(names(7), 3));
        }

        @Test
        void testTenAndTwo() {
            assertEquals("n4", QueuePlayChallenge.lastSurvivor(names(10), 2));
        }

        @Test
        void testTwoPeople() {
            assertEquals("n1", QueuePlayChallenge.lastSurvivor(names(2), 1));
            assertEquals("n1", QueuePlayChallenge.lastSurvivor(names(2), 5));
        }

        @Test
        void testKEqualsTheNumberOfPeople() {
            assertEquals("n1", QueuePlayChallenge.lastSurvivor(names(3), 3));
            assertEquals("n3", QueuePlayChallenge.lastSurvivor(names(6), 6));
        }

        @Test
        void testKBiggerThanTheNumberOfPeople() {
            assertEquals("n3", QueuePlayChallenge.lastSurvivor(names(4), 10));
        }

        @Test
        void testPowerOfTwoWithTwo() {
            assertEquals("n0", QueuePlayChallenge.lastSurvivor(names(8), 2));
        }

        @Test
        void testRealNames() {
            assertEquals("cy", QueuePlayChallenge.lastSurvivor(new String[] {"ana", "bob", "cy"}, 2));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] people = names(5);
            QueuePlayChallenge.lastSurvivor(people, 2);
            assertArrayEquals(names(5), people);
        }

        @Test
        void testBigCircle() {
            assertEquals("n608", QueuePlayChallenge.lastSurvivor(names(1000), 1000));
            assertEquals("n999", QueuePlayChallenge.lastSurvivor(names(1000), 1));
            assertEquals("n976", QueuePlayChallenge.lastSurvivor(names(1000), 2));
        }
    }

    // ==========================================================
    // CHALLENGE 9: runTasks
    // ==========================================================
    @Nested
    class RunTasksTests {

        @Test
        void testDifferentPriorities() {
            assertEquals(List.of("b", "c", "a"), QueuePlayChallenge.runTasks(new String[] {"a:1", "b:5", "c:3"}));
        }

        @Test
        void testSamePriority() {
            assertEquals(List.of("a", "b", "c"), QueuePlayChallenge.runTasks(new String[] {"a:2", "b:2", "c:2"}));
        }

        @Test
        void testTiesInTwoGroups() {
            assertEquals(List.of("b", "d", "a", "c"), QueuePlayChallenge.runTasks(
                    new String[] {"a:1", "b:2", "c:1", "d:2"}));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), QueuePlayChallenge.runTasks(new String[] {}));
        }

        @Test
        void testOneTask() {
            assertEquals(List.of("a"), QueuePlayChallenge.runTasks(new String[] {"a:0"}));
        }

        @Test
        void testPriorityIsComparedAsANumber() {
            assertEquals(List.of("b", "a"), QueuePlayChallenge.runTasks(new String[] {"a:9", "b:10"}));
        }

        @Test
        void testBigPriorities() {
            assertEquals(List.of("a", "b"), QueuePlayChallenge.runTasks(new String[] {"a:1000000", "b:999999"}));
            assertEquals(List.of("a", "b"), QueuePlayChallenge.runTasks(new String[] {"b:999999", "a:1000000"}));
        }

        @Test
        void testTiesWithBigPriorities() {
            assertEquals(List.of("x", "y", "z"), QueuePlayChallenge.runTasks(new String[] {"x:500", "y:500", "z:500"}));
            assertEquals(List.of("p", "q"), QueuePlayChallenge.runTasks(new String[] {"p:1000000", "q:1000000"}));
        }

        @Test
        void testMixedTies() {
            assertEquals(List.of("c", "d", "a", "b", "e"), QueuePlayChallenge.runTasks(
                    new String[] {"a:5", "b:5", "c:7", "d:7", "e:5"}));
        }

        @Test
        void testZeroPriorityRunsLast() {
            assertEquals(List.of("b", "a"), QueuePlayChallenge.runTasks(new String[] {"a:0", "b:1"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] tasks = {"a:1", "b:5", "c:3"};
            QueuePlayChallenge.runTasks(tasks);
            assertArrayEquals(new String[] {"a:1", "b:5", "c:3"}, tasks);
        }

        @Test
        void testBigInput() {
            String[] tasks = new String[100_000];
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = "t" + i + ":" + i % 3;
            }
            List<String> result = QueuePlayChallenge.runTasks(tasks);
            assertEquals(100_000, result.size());
            assertEquals("t2", result.get(0));
            assertEquals("t5", result.get(1));
            assertEquals("t1", result.get(33_333));
            assertEquals("t99999", result.get(99_999));
        }
    }

    // ==========================================================
    // CHALLENGE 10: movingSum
    // ==========================================================
    @Nested
    class MovingSumTests {

        @Test
        void testWindowOfThree() {
            assertArrayEquals(new int[] {1, 3, 6, 9, 12}, QueuePlayChallenge.movingSum(new int[] {1, 2, 3, 4, 5}, 3));
        }

        @Test
        void testWindowOfOne() {
            assertArrayEquals(new int[] {5, -1, 2}, QueuePlayChallenge.movingSum(new int[] {5, -1, 2}, 1));
        }

        @Test
        void testWindowBiggerThanTheArray() {
            assertArrayEquals(new int[] {1, 2, 3}, QueuePlayChallenge.movingSum(new int[] {1, 1, 1}, 10));
        }

        @Test
        void testWindowEqualToTheArray() {
            assertArrayEquals(new int[] {1, 3, 6, 10, 15}, QueuePlayChallenge.movingSum(new int[] {1, 2, 3, 4, 5}, 5));
        }

        @Test
        void testWindowOfTwo() {
            assertArrayEquals(new int[] {1, 3, 5, 7, 9}, QueuePlayChallenge.movingSum(new int[] {1, 2, 3, 4, 5}, 2));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[] {-1, -3, -5}, QueuePlayChallenge.movingSum(new int[] {-1, -2, -3}, 2));
        }

        @Test
        void testZeros() {
            assertArrayEquals(new int[] {0, 0, 0}, QueuePlayChallenge.movingSum(new int[] {0, 0, 0}, 2));
        }

        @Test
        void testSumsGoBackDown() {
            assertArrayEquals(new int[] {10, 30, 60, 100}, QueuePlayChallenge.movingSum(new int[] {10, 20, 30, 40}, 4));
            assertArrayEquals(new int[] {1, 0, 0, 0}, QueuePlayChallenge.movingSum(new int[] {1, -1, 1, -1}, 2));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new int[] {}, QueuePlayChallenge.movingSum(new int[] {}, 3));
        }

        @Test
        void testOneValue() {
            assertArrayEquals(new int[] {7}, QueuePlayChallenge.movingSum(new int[] {7}, 1));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] nums = {1, 2, 3, 4, 5};
            QueuePlayChallenge.movingSum(nums, 3);
            assertArrayEquals(new int[] {1, 2, 3, 4, 5}, nums);
        }

        @Test
        void testBigArray() {
            int[] nums = new int[300_000];
            java.util.Arrays.fill(nums, 1);
            int[] result = QueuePlayChallenge.movingSum(nums, 100_000);
            assertEquals(6, result[5]);
            assertEquals(100_000, result[99_999]);
            assertEquals(100_000, result[299_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 11: minCostToConnect
    // ==========================================================
    @Nested
    class MinCostToConnectTests {

        @Test
        void testExample() {
            assertEquals(29, QueuePlayChallenge.minCostToConnect(new int[] {4, 3, 2, 6}));
        }

        @Test
        void testOneRope() {
            assertEquals(0, QueuePlayChallenge.minCostToConnect(new int[] {5}));
        }

        @Test
        void testNoRopes() {
            assertEquals(0, QueuePlayChallenge.minCostToConnect(new int[] {}));
        }

        @Test
        void testTwoRopes() {
            assertEquals(2, QueuePlayChallenge.minCostToConnect(new int[] {1, 1}));
            assertEquals(30, QueuePlayChallenge.minCostToConnect(new int[] {10, 20}));
        }

        @Test
        void testThreeRopes() {
            assertEquals(9, QueuePlayChallenge.minCostToConnect(new int[] {1, 2, 3}));
            assertEquals(15, QueuePlayChallenge.minCostToConnect(new int[] {3, 3, 3}));
        }

        @Test
        void testFourRopesTheSame() {
            assertEquals(8, QueuePlayChallenge.minCostToConnect(new int[] {1, 1, 1, 1}));
        }

        @Test
        void testFiveRopes() {
            assertEquals(33, QueuePlayChallenge.minCostToConnect(new int[] {1, 2, 3, 4, 5}));
        }

        @Test
        void testNewRopeCanBeTheSmallestAgain() {
            assertEquals(20, QueuePlayChallenge.minCostToConnect(new int[] {2, 2, 3, 3}));
        }

        @Test
        void testTheOrderDoesNotMatter() {
            assertEquals(29, QueuePlayChallenge.minCostToConnect(new int[] {6, 2, 4, 3}));
            assertEquals(29, QueuePlayChallenge.minCostToConnect(new int[] {2, 3, 4, 6}));
        }

        @Test
        void testOriginalIsNotModified() {
            int[] ropes = {4, 3, 2, 6};
            QueuePlayChallenge.minCostToConnect(ropes);
            assertArrayEquals(new int[] {4, 3, 2, 6}, ropes);
        }

        @Test
        void testBigNumberOfRopes() {
            int[] ropes = new int[65_536];
            java.util.Arrays.fill(ropes, 1);
            assertEquals(1_048_576L, QueuePlayChallenge.minCostToConnect(ropes));
        }

        @Test
        void testTheTotalIsBiggerThanAnInt() {
            int[] ropes = new int[65_536];
            java.util.Arrays.fill(ropes, 1000);
            assertEquals(1_048_576_000L, QueuePlayChallenge.minCostToConnect(ropes));
            int[] more = new int[131_072];
            java.util.Arrays.fill(more, 1000);
            assertEquals(2_228_224_000L, QueuePlayChallenge.minCostToConnect(more));
        }
    }

    // ==========================================================
    // CHALLENGE 12: binaryNumbers
    // ==========================================================
    @Nested
    class BinaryNumbersTests {

        @Test
        void testFive() {
            assertEquals(List.of("1", "10", "11", "100", "101"), QueuePlayChallenge.binaryNumbers(5));
        }

        @Test
        void testOne() {
            assertEquals(List.of("1"), QueuePlayChallenge.binaryNumbers(1));
        }

        @Test
        void testZero() {
            assertEquals(List.of(), QueuePlayChallenge.binaryNumbers(0));
        }

        @Test
        void testTwo() {
            assertEquals(List.of("1", "10"), QueuePlayChallenge.binaryNumbers(2));
        }

        @Test
        void testThree() {
            assertEquals(List.of("1", "10", "11"), QueuePlayChallenge.binaryNumbers(3));
        }

        @Test
        void testEight() {
            assertEquals(List.of("1", "10", "11", "100", "101", "110", "111", "1000"),
                    QueuePlayChallenge.binaryNumbers(8));
        }

        @Test
        void testSixteenEndsWithAPowerOfTwo() {
            List<String> result = QueuePlayChallenge.binaryNumbers(16);
            assertEquals(16, result.size());
            assertEquals("10000", result.get(15));
        }

        @Test
        void testSizeOfTheResult() {
            assertEquals(37, QueuePlayChallenge.binaryNumbers(37).size());
        }

        @Test
        void testNoNumberStartsWithZero() {
            for (String s : QueuePlayChallenge.binaryNumbers(100)) {
                assertEquals('1', s.charAt(0));
            }
        }

        @Test
        void testTheTextsAreDifferent() {
            List<String> result = QueuePlayChallenge.binaryNumbers(200);
            assertEquals(200, new java.util.HashSet<>(result).size());
        }

        @Test
        void testEveryNumberIsTheNextOne() {
            List<String> result = QueuePlayChallenge.binaryNumbers(1000);
            for (int i = 0; i < result.size(); i++) {
                assertEquals(Integer.toBinaryString(i + 1), result.get(i));
            }
        }

        @Test
        void testBigN() {
            List<String> result = QueuePlayChallenge.binaryNumbers(100_000);
            assertEquals(100_000, result.size());
            assertEquals("11000011010100000", result.get(99_999));
            assertEquals(Integer.toBinaryString(65_536), result.get(65_535));
        }
    }

    // ==========================================================
    // CHALLENGE 13: rotateRight
    // ==========================================================
    @Nested
    class RotateRightTests {

        @Test
        void testRotateByTwo() {
            assertArrayEquals(new int[] {4, 5, 1, 2, 3}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3, 4, 5}, 2));
        }

        @Test
        void testRotateByTheLength() {
            assertArrayEquals(new int[] {1, 2, 3}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3}, 3));
        }

        @Test
        void testRotateByOneMoreThanTheLength() {
            assertArrayEquals(new int[] {3, 1, 2}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3}, 4));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new int[] {}, QueuePlayChallenge.rotateRight(new int[] {}, 5));
        }

        @Test
        void testZero() {
            assertArrayEquals(new int[] {1, 2, 3}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3}, 0));
        }

        @Test
        void testRotateByOne() {
            assertArrayEquals(new int[] {5, 1, 2, 3, 4}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3, 4, 5}, 1));
        }

        @Test
        void testOneValue() {
            assertArrayEquals(new int[] {7}, QueuePlayChallenge.rotateRight(new int[] {7}, 100));
        }

        @Test
        void testTwoValues() {
            assertArrayEquals(new int[] {2, 1}, QueuePlayChallenge.rotateRight(new int[] {1, 2}, 3));
        }

        @Test
        void testBiggerThanTheLength() {
            assertArrayEquals(new int[] {3, 4, 1, 2}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3, 4}, 6));
        }

        @Test
        void testNegativeValues() {
            assertArrayEquals(new int[] {-3, -1, -2}, QueuePlayChallenge.rotateRight(new int[] {-1, -2, -3}, 1));
        }

        @Test
        void testResultIsANewArray() {
            int[] nums = {1, 2, 3, 4, 5};
            int[] result = QueuePlayChallenge.rotateRight(nums, 2);
            assertArrayEquals(new int[] {1, 2, 3, 4, 5}, nums);
            assertTrue(result != nums);
        }

        @Test
        void testHugeK() {
            assertArrayEquals(new int[] {3, 1, 2}, QueuePlayChallenge.rotateRight(new int[] {1, 2, 3}, 1_000_000_000));
            int[] nums = new int[100_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            int[] result = QueuePlayChallenge.rotateRight(nums, 999_999_999);
            assertEquals(1, result[0]);
            assertEquals(0, result[99_999]);
            assertEquals(99_999, result[99_998]);
        }
    }

    // ==========================================================
    // CHALLENGE 14: topKFrequent
    // ==========================================================
    @Nested
    class TopKFrequentTests {

        @Test
        void testExample() {
            assertArrayEquals(new String[] {"a", "b"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"a", "b", "a", "c", "b", "a"}, 2));
        }

        @Test
        void testTieIsAlphabetical() {
            assertArrayEquals(new String[] {"a", "b"},
                    QueuePlayChallenge.topKFrequent(new String[] {"b", "a", "c"}, 2));
        }

        @Test
        void testOneWinner() {
            assertArrayEquals(new String[] {"y"}, QueuePlayChallenge.topKFrequent(new String[] {"z", "y", "y"}, 1));
        }

        @Test
        void testAllTheWords() {
            assertArrayEquals(new String[] {"a", "b", "c"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"c", "b", "a"}, 3));
            assertArrayEquals(new String[] {"a", "b", "c"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"a", "b", "a", "c", "b", "a"}, 3));
        }

        @Test
        void testOneWord() {
            assertArrayEquals(new String[] {"x"}, QueuePlayChallenge.topKFrequent(new String[] {"x"}, 1));
        }

        @Test
        void testTwoTiedGroups() {
            assertArrayEquals(new String[] {"a", "b"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"b", "b", "a", "a", "c"}, 2));
        }

        @Test
        void testTheCaseMatters() {
            assertArrayEquals(new String[] {"B"},
                    QueuePlayChallenge.topKFrequent(new String[] {"B", "b", "b", "B"}, 1));
            assertArrayEquals(new String[] {"b", "B"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"B", "b", "b"}, 2));
        }

        @Test
        void testMostFrequentFirst() {
            assertArrayEquals(new String[] {"c", "b"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"a", "b", "b", "c", "c", "c"}, 2));
        }

        @Test
        void testShorterWordFirstWhenTied() {
            assertArrayEquals(new String[] {"a", "ab"}, QueuePlayChallenge.topKFrequent(new String[] {"ab", "a"}, 2));
        }

        @Test
        void testSeveralCounts() {
            assertArrayEquals(new String[] {"d", "c", "b"}, QueuePlayChallenge.topKFrequent(
                    new String[] {"a", "b", "b", "c", "c", "c", "d", "d", "d", "d"}, 3));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"b", "a", "b"};
            QueuePlayChallenge.topKFrequent(words, 1);
            assertArrayEquals(new String[] {"b", "a", "b"}, words);
        }

        @Test
        void testBigArrayWithManyTies() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "w" + i % 1000;
            }
            assertArrayEquals(new String[] {"w0", "w1", "w10"}, QueuePlayChallenge.topKFrequent(words, 3));
        }

        @Test
        void testBigArrayWithAWinner() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = i < 50_000 ? "z" : "u" + i;
            }
            assertArrayEquals(new String[] {"z", "u50000"}, QueuePlayChallenge.topKFrequent(words, 2));
        }
    }

    // ==========================================================
    // CHALLENGE 15: applyEditor
    // ==========================================================
    @Nested
    class ApplyEditorTests {

        @Test
        void testExample() {
            assertEquals("abe", QueuePlayChallenge.applyEditor(new String[] {"type ab", "type cd", "undo", "type e"}));
        }

        @Test
        void testUndoWithNothingToUndo() {
            assertEquals("x", QueuePlayChallenge.applyEditor(new String[] {"undo", "type x"}));
        }

        @Test
        void testUndoEverything() {
            assertEquals("", QueuePlayChallenge.applyEditor(new String[] {"type a", "undo", "undo"}));
        }

        @Test
        void testEmpty() {
            assertEquals("", QueuePlayChallenge.applyEditor(new String[] {}));
        }

        @Test
        void testOnlyTyping() {
            assertEquals("abc", QueuePlayChallenge.applyEditor(new String[] {"type a", "type b", "type c"}));
        }

        @Test
        void testTwoUndos() {
            assertEquals("a",
                    QueuePlayChallenge.applyEditor(new String[] {"type a", "type b", "type c", "undo", "undo"}));
        }

        @Test
        void testSameTextTwice() {
            assertEquals("ab", QueuePlayChallenge.applyEditor(new String[] {"type ab", "type ab", "undo"}));
        }

        @Test
        void testOnlyUndo() {
            assertEquals("", QueuePlayChallenge.applyEditor(new String[] {"undo", "undo"}));
        }

        @Test
        void testTypeAfterUndo() {
            assertEquals("ac", QueuePlayChallenge.applyEditor(new String[] {"type a", "type b", "undo", "type c"}));
        }

        @Test
        void testUndoRemovesWholeText() {
            assertEquals("hi", QueuePlayChallenge.applyEditor(new String[] {"type hi", "type there", "undo"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] ops = {"type a", "undo"};
            QueuePlayChallenge.applyEditor(ops);
            assertArrayEquals(new String[] {"type a", "undo"}, ops);
        }

        @Test
        void testTheOrderOfTheTextIsKept() {
            assertEquals("onetwothree", QueuePlayChallenge.applyEditor(
                    new String[] {"type one", "type two", "type three"}));
        }

        @Test
        void testBigInput() {
            String[] ops = new String[70_000];
            for (int i = 0; i < 50_000; i++) {
                ops[i] = "type ab";
            }
            for (int i = 50_000; i < 70_000; i++) {
                ops[i] = "undo";
            }
            assertEquals("ab".repeat(30_000), QueuePlayChallenge.applyEditor(ops));
        }
    }
}