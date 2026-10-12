package dev.perfectbogus.streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamPracticeChallengeTest {

    // ==========================================================
    // CHALLENGE 1: squaresOfOdds
    // ==========================================================
    @Nested
    class SquaresOfOddsTests {

        @Test
        void testSquaresOnlyTheOddNumbers() {
            assertEquals(List.of(1, 9, 25), StreamPracticeChallenge.squaresOfOdds(List.of(1, 2, 3, 4, 5)));
        }

        @Test
        void testKeepsOriginalOrder() {
            assertEquals(List.of(25, 1, 9), StreamPracticeChallenge.squaresOfOdds(List.of(5, 4, 1, 3)));
        }

        @Test
        void testNegativeOddNumbersAreOddToo() {
            assertEquals(List.of(9, 1), StreamPracticeChallenge.squaresOfOdds(List.of(-3, -2, -1, 0)));
        }

        @Test
        void testNoOddNumbersGivesEmptyList() {
            assertEquals(List.of(), StreamPracticeChallenge.squaresOfOdds(List.of(2, 4, 6)));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.squaresOfOdds(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 2: firstThreeLongWords
    // ==========================================================
    @Nested
    class FirstThreeLongWordsTests {

        @Test
        void testTakesTheFirstThreeThatQualify() {
            List<String> words = List.of("a", "bbb", "cc", "dddd", "eeeee", "ffffff", "g");
            assertEquals(List.of("bbb", "dddd", "eeeee"), StreamPracticeChallenge.firstThreeLongWords(words, 3));
        }

        @Test
        void testFewerThanThreeQualify() {
            assertEquals(List.of("bbbb"),
                    StreamPracticeChallenge.firstThreeLongWords(List.of("aa", "bbbb", "c"), 3));
        }

        @Test
        void testMinLengthIsInclusive() {
            assertEquals(List.of("abc"), StreamPracticeChallenge.firstThreeLongWords(List.of("abc", "ab"), 3));
        }

        @Test
        void testNothingQualifies() {
            assertEquals(List.of(), StreamPracticeChallenge.firstThreeLongWords(List.of("a", "bb"), 5));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.firstThreeLongWords(List.of(), 1));
        }
    }

    // ==========================================================
    // CHALLENGE 3: pageOf
    // ==========================================================
    @Nested
    class PageOfTests {

        private final List<String> items = List.of("a", "b", "c", "d", "e", "f", "g");

        @Test
        void testFirstPage() {
            assertEquals(List.of("a", "b", "c"), StreamPracticeChallenge.pageOf(items, 1, 3));
        }

        @Test
        void testMiddlePage() {
            assertEquals(List.of("d", "e", "f"), StreamPracticeChallenge.pageOf(items, 2, 3));
        }

        @Test
        void testLastPageMayBeShort() {
            assertEquals(List.of("g"), StreamPracticeChallenge.pageOf(items, 3, 3));
        }

        @Test
        void testPageAfterTheEndIsEmpty() {
            assertEquals(List.of(), StreamPracticeChallenge.pageOf(items, 4, 3));
        }

        @Test
        void testPageSizeLargerThanTheList() {
            assertEquals(items, StreamPracticeChallenge.pageOf(items, 1, 10));
            assertEquals(List.of(), StreamPracticeChallenge.pageOf(items, 2, 10));
        }

        @Test
        void testVeryLargePageNumberDoesNotOverflow() {
            assertEquals(List.of(), StreamPracticeChallenge.pageOf(items, 2_000_000, 2_000));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.pageOf(List.of(), 1, 5));
        }
    }

    // ==========================================================
    // CHALLENGE 4: allWithinRange
    // ==========================================================
    @Nested
    class AllWithinRangeTests {

        @Test
        void testAllInsideIncludingTheBoundaries() {
            assertTrue(StreamPracticeChallenge.allWithinRange(List.of(1, 5, 10), 1, 10));
        }

        @Test
        void testOneAboveTheMax() {
            assertFalse(StreamPracticeChallenge.allWithinRange(List.of(1, 5, 11), 1, 10));
        }

        @Test
        void testOneBelowTheMin() {
            assertFalse(StreamPracticeChallenge.allWithinRange(List.of(0, 5, 10), 1, 10));
        }

        @Test
        void testEmptyInputIsTrue() {
            assertTrue(StreamPracticeChallenge.allWithinRange(List.of(), 1, 10));
        }
    }

    // ==========================================================
    // CHALLENGE 5: firstWithPrefix
    // ==========================================================
    @Nested
    class FirstWithPrefixTests {

        @Test
        void testReturnsTheFirstMatch() {
            assertEquals(Optional.of("apple"),
                    StreamPracticeChallenge.firstWithPrefix(List.of("apple", "banana", "avocado"), "a"));
        }

        @Test
        void testSkipsNonMatchingWords() {
            assertEquals(Optional.of("banana"),
                    StreamPracticeChallenge.firstWithPrefix(List.of("apple", "banana", "avocado"), "b"));
        }

        @Test
        void testIsCaseSensitive() {
            assertEquals(Optional.of("apricot"),
                    StreamPracticeChallenge.firstWithPrefix(List.of("Apple", "apricot"), "a"));
        }

        @Test
        void testNoMatchGivesEmptyOptional() {
            assertTrue(StreamPracticeChallenge.firstWithPrefix(List.of("apple"), "z").isEmpty());
        }

        @Test
        void testEmptyInput() {
            assertTrue(StreamPracticeChallenge.firstWithPrefix(List.of(), "a").isEmpty());
        }
    }

    // ==========================================================
    // CHALLENGE 6: sumOfMultiples
    // ==========================================================
    @Nested
    class SumOfMultiplesTests {

        @Test
        void testBelowTen() {
            assertEquals(23L, StreamPracticeChallenge.sumOfMultiples(10));
        }

        @Test
        void testBelowOneThousand() {
            assertEquals(233168L, StreamPracticeChallenge.sumOfMultiples(1000));
        }

        @Test
        void testLimitIsExcluded() {
            assertEquals(45L, StreamPracticeChallenge.sumOfMultiples(15));
            assertEquals(60L, StreamPracticeChallenge.sumOfMultiples(16));
        }

        @Test
        void testSmallLimits() {
            assertEquals(0L, StreamPracticeChallenge.sumOfMultiples(3));
            assertEquals(3L, StreamPracticeChallenge.sumOfMultiples(4));
        }

        @Test
        void testZeroAndNegativeLimits() {
            assertEquals(0L, StreamPracticeChallenge.sumOfMultiples(0));
            assertEquals(0L, StreamPracticeChallenge.sumOfMultiples(-5));
        }

        @Test
        void testResultBiggerThanAnIntDoesNotOverflow() {
            assertEquals(2333316668L, StreamPracticeChallenge.sumOfMultiples(100_000));
            assertEquals(233333166668L, StreamPracticeChallenge.sumOfMultiples(1_000_000));
        }
    }

    // ==========================================================
    // CHALLENGE 7: mergeDistinctSorted
    // ==========================================================
    @Nested
    class MergeDistinctSortedTests {

        @Test
        void testMergesSortsAndRemovesRepeats() {
            assertEquals(List.of(1, 2, 3, 5),
                    StreamPracticeChallenge.mergeDistinctSorted(List.of(3, 1, 2), List.of(2, 5, 3)));
        }

        @Test
        void testRepeatsInsideOneList() {
            assertEquals(List.of(1), StreamPracticeChallenge.mergeDistinctSorted(List.of(1, 1), List.of(1)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(-5, -1, 0, 4),
                    StreamPracticeChallenge.mergeDistinctSorted(List.of(4, -1), List.of(0, -5)));
        }

        @Test
        void testOneListEmpty() {
            assertEquals(List.of(2, 3), StreamPracticeChallenge.mergeDistinctSorted(List.of(), List.of(3, 2)));
            assertEquals(List.of(2, 3), StreamPracticeChallenge.mergeDistinctSorted(List.of(3, 2), List.of()));
        }

        @Test
        void testBothEmpty() {
            assertEquals(List.of(), StreamPracticeChallenge.mergeDistinctSorted(List.of(), List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortByLengthThenAlphabetical
    // ==========================================================
    @Nested
    class SortByLengthThenAlphabeticalTests {

        @Test
        void testShortestFirstThenAlphabetical() {
            assertEquals(List.of("fig", "date", "kiwi", "pear", "apple"),
                    StreamPracticeChallenge.sortByLengthThenAlphabetical(
                            List.of("pear", "fig", "apple", "kiwi", "date")));
        }

        @Test
        void testAlphabeticalIsNaturalOrderNotIgnoringCase() {
            assertEquals(List.of("B", "a", "b"),
                    StreamPracticeChallenge.sortByLengthThenAlphabetical(List.of("b", "B", "a")));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals(List.of("a", "a", "bb"),
                    StreamPracticeChallenge.sortByLengthThenAlphabetical(List.of("bb", "a", "a")));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.sortByLengthThenAlphabetical(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 9: distinctLetterCount
    // ==========================================================
    @Nested
    class DistinctLetterCountTests {

        @Test
        void testIgnoresPunctuationAndSpaces() {
            assertEquals(7L, StreamPracticeChallenge.distinctLetterCount("Hello, World!"));
        }

        @Test
        void testUpperAndLowerCaseAreTheSameLetter() {
            assertEquals(2L, StreamPracticeChallenge.distinctLetterCount("AaBb"));
        }

        @Test
        void testPangram() {
            assertEquals(26L, StreamPracticeChallenge.distinctLetterCount(
                    "The quick brown fox jumps over the lazy dog"));
        }

        @Test
        void testRepeatedLetter() {
            assertEquals(1L, StreamPracticeChallenge.distinctLetterCount("aaa"));
        }

        @Test
        void testNoLetters() {
            assertEquals(0L, StreamPracticeChallenge.distinctLetterCount("123 !?"));
            assertEquals(0L, StreamPracticeChallenge.distinctLetterCount(""));
        }
    }

    // ==========================================================
    // CHALLENGE 10: firstMultiples
    // ==========================================================
    @Nested
    class FirstMultiplesTests {

        @Test
        void testFirstFourMultiplesOfThree() {
            assertEquals(List.of(3, 6, 9, 12), StreamPracticeChallenge.firstMultiples(3, 4));
        }

        @Test
        void testSingleMultiple() {
            assertEquals(List.of(7), StreamPracticeChallenge.firstMultiples(7, 1));
        }

        @Test
        void testCountZeroGivesEmptyList() {
            assertEquals(List.of(), StreamPracticeChallenge.firstMultiples(5, 0));
        }

        @Test
        void testNegativeBase() {
            assertEquals(List.of(-2, -4, -6), StreamPracticeChallenge.firstMultiples(-2, 3));
        }

        @Test
        void testZeroBase() {
            assertEquals(List.of(0, 0, 0), StreamPracticeChallenge.firstMultiples(0, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 11: runningTotals
    // ==========================================================
    @Nested
    class RunningTotalsTests {

        @Test
        void testPrefixSums() {
            assertEquals(List.of(1, 3, 6, 10), StreamPracticeChallenge.runningTotals(List.of(1, 2, 3, 4)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(3, 2, 0, 4), StreamPracticeChallenge.runningTotals(List.of(3, -1, -2, 4)));
        }

        @Test
        void testSingleElement() {
            assertEquals(List.of(5), StreamPracticeChallenge.runningTotals(List.of(5)));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.runningTotals(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 12: uniqueWordsSorted
    // ==========================================================
    @Nested
    class UniqueWordsSortedTests {

        @Test
        void testLowerCasedDistinctAndSorted() {
            assertEquals(List.of("brown", "dog", "fox", "lazy", "quick", "the"),
                    StreamPracticeChallenge.uniqueWordsSorted(List.of("The quick brown fox", "the Lazy dog")));
        }

        @Test
        void testExtraWhitespaceAndBlankSentencesProduceNoEmptyWords() {
            assertEquals(List.of("hello", "world"),
                    StreamPracticeChallenge.uniqueWordsSorted(List.of("  hello   world  ", "")));
        }

        @Test
        void testOnlyBlankSentences() {
            assertEquals(List.of(), StreamPracticeChallenge.uniqueWordsSorted(List.of("   ", "")));
        }

        @Test
        void testPunctuationStaysInTheWord() {
            assertEquals(List.of("hi", "hi,"), StreamPracticeChallenge.uniqueWordsSorted(List.of("Hi, hi")));
        }

        @Test
        void testTabsAndNewlinesSeparateWords() {
            assertEquals(List.of("a", "b", "c"), StreamPracticeChallenge.uniqueWordsSorted(List.of("a\tb\nc")));
        }

        @Test
        void testSameWordInDifferentCases() {
            assertEquals(List.of("a"), StreamPracticeChallenge.uniqueWordsSorted(List.of("A a A")));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.uniqueWordsSorted(List.of()));
        }
    }

    // ==========================================================
    // CHALLENGE 13: pairsWithSum
    // ==========================================================
    @Nested
    class PairsWithSumTests {

        @Test
        void testFindsAllPairs() {
            assertEquals(List.of(List.of(1, 5), List.of(2, 4)),
                    StreamPracticeChallenge.pairsWithSum(List.of(1, 2, 3, 4, 5), 6));
        }

        @Test
        void testPairsKeepPositionOrderAndOrderByFirstThenSecondPosition() {
            assertEquals(List.of(List.of(4, 1), List.of(3, 2)),
                    StreamPracticeChallenge.pairsWithSum(List.of(4, 1, 3, 2, 5), 5));
        }

        @Test
        void testEqualValuesAtDifferentPositionsFormSeparatePairs() {
            assertEquals(List.of(List.of(2, 2), List.of(2, 2), List.of(2, 2)),
                    StreamPracticeChallenge.pairsWithSum(List.of(2, 2, 2), 4));
        }

        @Test
        void testMixedCase() {
            assertEquals(List.of(List.of(3, 3), List.of(2, 4)),
                    StreamPracticeChallenge.pairsWithSum(List.of(3, 3, 2, 4, 1), 6));
        }

        @Test
        void testNegativeNumbersAndZeros() {
            assertEquals(List.of(List.of(-1, 1), List.of(0, 0)),
                    StreamPracticeChallenge.pairsWithSum(List.of(-1, 1, 0, 0), 0));
        }

        @Test
        void testLargeValues() {
            assertEquals(List.of(List.of(1000, 3000)),
                    StreamPracticeChallenge.pairsWithSum(List.of(1000, 2000, 3000), 4000));
        }

        @Test
        void testAnElementIsNotPairedWithItself() {
            assertEquals(List.of(), StreamPracticeChallenge.pairsWithSum(List.of(5), 10));
        }

        @Test
        void testNoPairsAndEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.pairsWithSum(List.of(1, 2, 3), 10));
            assertEquals(List.of(), StreamPracticeChallenge.pairsWithSum(List.of(), 5));
        }
    }

    // ==========================================================
    // CHALLENGE 14: windowAverages
    // ==========================================================
    @Nested
    class WindowAveragesTests {

        @Test
        void testWindowOfThree() {
            assertEquals(List.of(2.0, 3.0, 4.0),
                    StreamPracticeChallenge.windowAverages(List.of(1, 2, 3, 4, 5), 3));
        }

        @Test
        void testFractionalAverages() {
            assertEquals(List.of(1.5, 3.0, 6.0), StreamPracticeChallenge.windowAverages(List.of(1, 2, 4, 8), 2));
        }

        @Test
        void testWindowAsLargeAsTheList() {
            assertEquals(List.of(1.5), StreamPracticeChallenge.windowAverages(List.of(1, 2), 2));
        }

        @Test
        void testWindowOfOne() {
            assertEquals(List.of(4.0, 6.0), StreamPracticeChallenge.windowAverages(List.of(4, 6), 1));
        }

        @Test
        void testWindowLargerThanTheListGivesEmptyList() {
            assertEquals(List.of(), StreamPracticeChallenge.windowAverages(List.of(1, 2), 3));
        }

        @Test
        void testEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.windowAverages(List.of(), 1));
        }
    }

    // ==========================================================
    // CHALLENGE 15: topKFrequentWords
    // ==========================================================
    @Nested
    class TopKFrequentWordsTests {

        @Test
        void testMostFrequentFirst() {
            assertEquals(List.of("a", "b"),
                    StreamPracticeChallenge.topKFrequentWords(List.of("a", "b", "a", "c", "b", "a"), 2));
        }

        @Test
        void testTiesAreOrderedAlphabetically() {
            assertEquals(List.of("apple", "pear"),
                    StreamPracticeChallenge.topKFrequentWords(List.of("pear", "apple", "pear", "apple", "fig"), 2));
        }

        @Test
        void testTieBreakAlsoDecidesWhoIsCutOff() {
            assertEquals(List.of("a"), StreamPracticeChallenge.topKFrequentWords(List.of("b", "a"), 1));
        }

        @Test
        void testAlphabeticalTieBreakIsNaturalOrder() {
            assertEquals(List.of("B", "b"), StreamPracticeChallenge.topKFrequentWords(List.of("b", "B"), 2));
        }

        @Test
        void testFewerDistinctWordsThanK() {
            assertEquals(List.of("x", "y"), StreamPracticeChallenge.topKFrequentWords(List.of("x", "y", "x"), 5));
        }

        @Test
        void testKZeroAndEmptyInput() {
            assertEquals(List.of(), StreamPracticeChallenge.topKFrequentWords(List.of("a"), 0));
            assertEquals(List.of(), StreamPracticeChallenge.topKFrequentWords(List.of(), 3));
        }
    }
}