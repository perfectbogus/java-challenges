package dev.perfectbogus.sets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class SetsPracticeChallengeTest {

    private static <T> List<T> orderOf(Set<T> set) {
        return new ArrayList<>(set);
    }

    private static Set<Integer> setOf(Integer... values) {
        return new HashSet<>(List.of(values));
    }

    private static <T> LinkedHashSet<T> linked(List<T> values) {
        return new LinkedHashSet<>(values);
    }

    private static TreeSet<Integer> treeOf(Integer... values) {
        return new TreeSet<>(List.of(values));
    }

    // ==========================================================
    // CHALLENGE 1: uniqueWords
    // ==========================================================
    @Nested
    class UniqueWordsTests {

        @Test
        void testRepeatedWord() {
            assertEquals(Set.of("a", "b"), SetsPracticeChallenge.uniqueWords(List.of("a", "b", "a")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(Set.of("a", "A"), SetsPracticeChallenge.uniqueWords(List.of("a", "A")));
        }

        @Test
        void testAllEqual() {
            assertEquals(Set.of("x"), SetsPracticeChallenge.uniqueWords(List.of("x", "x", "x")));
        }

        @Test
        void testEmpty() {
            assertEquals(Set.of(), SetsPracticeChallenge.uniqueWords(List.of()));
        }

        @Test
        void testAllDifferent() {
            assertEquals(Set.of("a", "b", "c"), SetsPracticeChallenge.uniqueWords(List.of("a", "b", "c")));
        }

        @Test
        void testSizeIsTheNumberOfDifferentWords() {
            assertEquals(3, SetsPracticeChallenge.uniqueWords(List.of("a", "b", "a", "c", "b")).size());
        }

        @Test
        void testEmptyWord() {
            assertEquals(Set.of("", "a"), SetsPracticeChallenge.uniqueWords(List.of("", "a", "")));
        }

        @Test
        void testSpacesMakeWordsDifferent() {
            assertEquals(Set.of("a", "a ", " a"), SetsPracticeChallenge.uniqueWords(List.of("a", "a ", " a", "a")));
        }

        @Test
        void testMoreThanFiveWords() {
            assertEquals(Set.of("a", "b", "c", "d", "e", "f", "g", "h"),
                    SetsPracticeChallenge.uniqueWords(List.of("a", "b", "c", "d", "e", "f", "g", "h", "a")));
        }

        @Test
        void testResultCanBeChanged() {
            HashSet<String> r = SetsPracticeChallenge.uniqueWords(List.of("a"));
            r.add("b");
            assertEquals(2, r.size());
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "a", "b"));
            SetsPracticeChallenge.uniqueWords(words);
            assertEquals(List.of("a", "a", "b"), words);
        }

        @Test
        void testManyWords() {
            assertEquals(Set.of("to", "be", "or", "not"),
                    SetsPracticeChallenge.uniqueWords(List.of("to", "be", "or", "not", "to", "be")));
        }
    }

    // ==========================================================
    // CHALLENGE 2: union
    // ==========================================================
    @Nested
    class UnionTests {

        @Test
        void testOverlapping() {
            assertEquals(setOf(1, 2, 3, 4), SetsPracticeChallenge.union(setOf(1, 2, 3), setOf(3, 4)));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(setOf(1), SetsPracticeChallenge.union(setOf(1), setOf()));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(setOf(1), SetsPracticeChallenge.union(setOf(), setOf(1)));
        }

        @Test
        void testBothEmpty() {
            assertEquals(setOf(), SetsPracticeChallenge.union(setOf(), setOf()));
        }

        @Test
        void testDisjoint() {
            assertEquals(setOf(1, 2, 3, 4), SetsPracticeChallenge.union(setOf(1, 2), setOf(3, 4)));
        }

        @Test
        void testEqualSets() {
            assertEquals(setOf(1, 2), SetsPracticeChallenge.union(setOf(1, 2), setOf(1, 2)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(setOf(-1, 0, 1), SetsPracticeChallenge.union(setOf(-1, 0), setOf(0, 1)));
        }

        @Test
        void testNegativeNumbersAndZeroOnlyInTheSecondSet() {
            assertEquals(setOf(1, -5, 0), SetsPracticeChallenge.union(setOf(1), setOf(-5, 0)));
        }

        @Test
        void testNeitherSetIsModified() {
            Set<Integer> first = new HashSet<>(setOf(1, 2));
            Set<Integer> second = new HashSet<>(setOf(2, 3));
            SetsPracticeChallenge.union(first, second);
            assertEquals(setOf(1, 2), first);
            assertEquals(setOf(2, 3), second);
        }

        @Test
        void testSize() {
            assertEquals(5, SetsPracticeChallenge.union(setOf(1, 2, 3), setOf(3, 4, 5)).size());
        }

        @Test
        void testOneContainsTheOther() {
            assertEquals(setOf(1, 2, 3), SetsPracticeChallenge.union(setOf(1, 2, 3), setOf(2)));
            assertEquals(setOf(1, 2, 3), SetsPracticeChallenge.union(setOf(2), setOf(1, 2, 3)));
        }

        @Test
        void testWorksWithImmutableSets() {
            assertEquals(setOf(1, 2), SetsPracticeChallenge.union(Set.of(1), Set.of(2)));
        }

        @Test
        void testResultCanBeChanged() {
            HashSet<Integer> r = SetsPracticeChallenge.union(Set.of(1), Set.of(2));
            r.add(3);
            assertEquals(3, r.size());
        }
    }

    // ==========================================================
    // CHALLENGE 3: intersection
    // ==========================================================
    @Nested
    class IntersectionTests {

        @Test
        void testOverlapping() {
            assertEquals(setOf(2, 3), SetsPracticeChallenge.intersection(setOf(1, 2, 3), setOf(2, 3, 4)));
        }

        @Test
        void testDisjoint() {
            assertEquals(setOf(), SetsPracticeChallenge.intersection(setOf(1, 2), setOf(3, 4)));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(setOf(), SetsPracticeChallenge.intersection(setOf(1, 2), setOf()));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(setOf(), SetsPracticeChallenge.intersection(setOf(), setOf(1, 2)));
        }

        @Test
        void testEqualSets() {
            assertEquals(setOf(1, 2), SetsPracticeChallenge.intersection(setOf(1, 2), setOf(1, 2)));
        }

        @Test
        void testOneElementInCommon() {
            assertEquals(setOf(3), SetsPracticeChallenge.intersection(setOf(1, 2, 3), setOf(3, 4, 5)));
        }

        @Test
        void testOneContainsTheOther() {
            assertEquals(setOf(2), SetsPracticeChallenge.intersection(setOf(1, 2, 3), setOf(2)));
            assertEquals(setOf(2), SetsPracticeChallenge.intersection(setOf(2), setOf(1, 2, 3)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(setOf(-1), SetsPracticeChallenge.intersection(setOf(-1, 0), setOf(-1, 5)));
        }

        @Test
        void testManyElementsInCommon() {
            assertEquals(setOf(1, 2, 3, 4, 5),
                    SetsPracticeChallenge.intersection(setOf(1, 2, 3, 4, 5, 6), setOf(0, 1, 2, 3, 4, 5)));
        }

        @Test
        void testZeroInCommon() {
            assertEquals(setOf(0), SetsPracticeChallenge.intersection(setOf(0, 1), setOf(0, 2)));
        }

        @Test
        void testNeitherSetIsModified() {
            Set<Integer> first = new HashSet<>(setOf(1, 2, 3));
            Set<Integer> second = new HashSet<>(setOf(2, 3, 4));
            SetsPracticeChallenge.intersection(first, second);
            assertEquals(setOf(1, 2, 3), first);
            assertEquals(setOf(2, 3, 4), second);
        }

        @Test
        void testWorksWithImmutableSets() {
            assertEquals(setOf(2), SetsPracticeChallenge.intersection(Set.of(1, 2), Set.of(2, 3)));
        }

        @Test
        void testSize() {
            assertEquals(3, SetsPracticeChallenge.intersection(setOf(1, 2, 3, 4), setOf(2, 3, 4, 5)).size());
        }
    }

    // ==========================================================
    // CHALLENGE 4: difference
    // ==========================================================
    @Nested
    class DifferenceTests {

        @Test
        void testOverlapping() {
            assertEquals(setOf(1, 3), SetsPracticeChallenge.difference(setOf(1, 2, 3), setOf(2, 5)));
        }

        @Test
        void testSecondContainsFirst() {
            assertEquals(setOf(), SetsPracticeChallenge.difference(setOf(1, 2), setOf(1, 2, 3)));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(setOf(1, 2), SetsPracticeChallenge.difference(setOf(1, 2), setOf()));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(setOf(), SetsPracticeChallenge.difference(setOf(), setOf(1, 2)));
        }

        @Test
        void testDisjoint() {
            assertEquals(setOf(1, 2), SetsPracticeChallenge.difference(setOf(1, 2), setOf(3, 4)));
        }

        @Test
        void testEqualSets() {
            assertEquals(setOf(), SetsPracticeChallenge.difference(setOf(1, 2), setOf(1, 2)));
        }

        @Test
        void testOrderMatters() {
            assertEquals(setOf(1), SetsPracticeChallenge.difference(setOf(1, 2), setOf(2, 3)));
            assertEquals(setOf(3), SetsPracticeChallenge.difference(setOf(2, 3), setOf(1, 2)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(setOf(-2), SetsPracticeChallenge.difference(setOf(-2, 0), setOf(0, 7)));
        }

        @Test
        void testNeitherSetIsModified() {
            Set<Integer> first = new HashSet<>(setOf(1, 2, 3));
            Set<Integer> second = new HashSet<>(setOf(2));
            SetsPracticeChallenge.difference(first, second);
            assertEquals(setOf(1, 2, 3), first);
            assertEquals(setOf(2), second);
        }

        @Test
        void testWorksWithImmutableSets() {
            assertEquals(setOf(1), SetsPracticeChallenge.difference(Set.of(1, 2), Set.of(2)));
        }

        @Test
        void testSize() {
            assertEquals(2, SetsPracticeChallenge.difference(setOf(1, 2, 3, 4), setOf(3, 4, 5)).size());
        }
    }

    // ==========================================================
    // CHALLENGE 5: firstRepeatedWord
    // ==========================================================
    @Nested
    class FirstRepeatedWordTests {

        @Test
        void testSecondAppearanceComesFirst() {
            assertEquals("b", SetsPracticeChallenge.firstRepeatedWord(List.of("a", "b", "c", "b", "a")));
        }

        @Test
        void testNoRepeats() {
            assertNull(SetsPracticeChallenge.firstRepeatedWord(List.of("a", "b", "c")));
        }

        @Test
        void testConsecutiveRepeats() {
            assertEquals("x", SetsPracticeChallenge.firstRepeatedWord(List.of("x", "x", "y", "y")));
        }

        @Test
        void testCaseMatters() {
            assertNull(SetsPracticeChallenge.firstRepeatedWord(List.of("a", "A")));
        }

        @Test
        void testEmpty() {
            assertNull(SetsPracticeChallenge.firstRepeatedWord(List.of()));
        }

        @Test
        void testSingleWord() {
            assertNull(SetsPracticeChallenge.firstRepeatedWord(List.of("a")));
        }

        @Test
        void testRepeatAtTheEnd() {
            assertEquals("a", SetsPracticeChallenge.firstRepeatedWord(List.of("a", "b", "c", "a")));
        }

        @Test
        void testFirstWordIsNotAlwaysTheAnswer() {
            assertEquals("c", SetsPracticeChallenge.firstRepeatedWord(List.of("a", "b", "c", "c", "a")));
        }

        @Test
        void testThreeAppearances() {
            assertEquals("a", SetsPracticeChallenge.firstRepeatedWord(List.of("a", "a", "a")));
        }

        @Test
        void testEmptyWordCanRepeat() {
            assertEquals("", SetsPracticeChallenge.firstRepeatedWord(List.of("", "x", "")));
        }

        @Test
        void testSpacesMakeWordsDifferent() {
            assertNull(SetsPracticeChallenge.firstRepeatedWord(List.of("a", "a ", " a")));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "b", "a"));
            SetsPracticeChallenge.firstRepeatedWord(words);
            assertEquals(List.of("a", "b", "a"), words);
        }

        @Test
        void testLongList() {
            assertEquals("k", SetsPracticeChallenge.firstRepeatedWord(
                    List.of("a", "b", "c", "d", "e", "f", "g", "k", "h", "k", "a")));
        }
    }

    // ==========================================================
    // CHALLENGE 6: distinctInOrder
    // ==========================================================
    @Nested
    class DistinctInOrderTests {

        @Test
        void testMixed() {
            LinkedHashSet<String> r = SetsPracticeChallenge.distinctInOrder(List.of("b", "a", "b", "c", "a"));
            assertEquals(List.of("b", "a", "c"), orderOf(r));
        }

        @Test
        void testAllEqual() {
            assertEquals(List.of("x"), orderOf(SetsPracticeChallenge.distinctInOrder(List.of("x", "x"))));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.distinctInOrder(List.of())));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            assertEquals(List.of("z", "m", "a"),
                    orderOf(SetsPracticeChallenge.distinctInOrder(List.of("z", "m", "a"))));
        }

        @Test
        void testFirstAppearanceIsKept() {
            assertEquals(List.of("a", "b", "c"),
                    orderOf(SetsPracticeChallenge.distinctInOrder(List.of("a", "b", "c", "b", "a"))));
        }

        @Test
        void testCaseMatters() {
            assertEquals(List.of("a", "A"), orderOf(SetsPracticeChallenge.distinctInOrder(List.of("a", "A", "a"))));
        }

        @Test
        void testNoRepeats() {
            assertEquals(List.of("q", "w", "e"),
                    orderOf(SetsPracticeChallenge.distinctInOrder(List.of("q", "w", "e"))));
        }

        @Test
        void testSingleWord() {
            assertEquals(List.of("hello"), orderOf(SetsPracticeChallenge.distinctInOrder(List.of("hello"))));
        }

        @Test
        void testSize() {
            assertEquals(2, SetsPracticeChallenge.distinctInOrder(List.of("a", "b", "a", "b", "a")).size());
        }

        @Test
        void testRepeatedLaterDoesNotMoveTheWord() {
            assertEquals(List.of("a", "b"),
                    orderOf(SetsPracticeChallenge.distinctInOrder(List.of("a", "b", "a", "a"))));
        }

        @Test
        void testSpacesMakeWordsDifferent() {
            assertEquals(List.of("a", "a "), orderOf(SetsPracticeChallenge.distinctInOrder(List.of("a", "a ", "a"))));
        }
    }

    // ==========================================================
    // CHALLENGE 7: commonInOrder
    // ==========================================================
    @Nested
    class CommonInOrderTests {

        @Test
        void testOrderOfTheFirstList() {
            assertEquals(List.of(3, 2),
                    orderOf(SetsPracticeChallenge.commonInOrder(List.of(3, 1, 2, 3), List.of(2, 3))));
        }

        @Test
        void testNothingInCommon() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.commonInOrder(List.of(5, 6), List.of(7))));
        }

        @Test
        void testSecondListOrderDoesNotMatter() {
            assertEquals(List.of(1, 2, 3),
                    orderOf(SetsPracticeChallenge.commonInOrder(List.of(1, 2, 3), List.of(3, 2, 1))));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.commonInOrder(List.of(), List.of(1))));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.commonInOrder(List.of(1), List.of())));
        }

        @Test
        void testRepeatsAreRemoved() {
            assertEquals(List.of(1), orderOf(SetsPracticeChallenge.commonInOrder(List.of(1, 1, 1), List.of(1, 1))));
        }

        @Test
        void testNotAlphabeticalOrNumericalOrder() {
            assertEquals(List.of(9, 4, 7),
                    orderOf(SetsPracticeChallenge.commonInOrder(List.of(9, 4, 7, 1), List.of(7, 4, 9, 0))));
        }

        @Test
        void testOnlyCommonOnesAreKept() {
            assertEquals(List.of(2, 4), orderOf(SetsPracticeChallenge.commonInOrder(
                    List.of(1, 2, 3, 4, 5), List.of(4, 2, 8))));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(-1, 0),
                    orderOf(SetsPracticeChallenge.commonInOrder(List.of(-1, 0, 5), List.of(0, -1))));
        }

        @Test
        void testNeitherListIsModified() {
            List<Integer> first = new ArrayList<>(List.of(3, 1, 3));
            List<Integer> second = new ArrayList<>(List.of(3, 3));
            SetsPracticeChallenge.commonInOrder(first, second);
            assertEquals(List.of(3, 1, 3), first);
            assertEquals(List.of(3, 3), second);
        }

        @Test
        void testFirstAppearanceInTheFirstListDecides() {
            assertEquals(List.of(2, 1), orderOf(SetsPracticeChallenge.commonInOrder(List.of(2, 1, 2), List.of(1, 2))));
        }
    }

    // ==========================================================
    // CHALLENGE 8: uniqueChars
    // ==========================================================
    @Nested
    class UniqueCharsTests {

        @Test
        void testBanana() {
            assertEquals(List.of('b', 'a', 'n'), orderOf(SetsPracticeChallenge.uniqueChars("banana")));
        }

        @Test
        void testCaseMatters() {
            assertEquals(List.of('a', 'A'), orderOf(SetsPracticeChallenge.uniqueChars("aAa")));
        }

        @Test
        void testSpacesCount() {
            assertEquals(List.of('a', ' ', 'b'), orderOf(SetsPracticeChallenge.uniqueChars("a b a")));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.uniqueChars("")));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            assertEquals(List.of('z', 'y', 'x'), orderOf(SetsPracticeChallenge.uniqueChars("zyx")));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(List.of('x'), orderOf(SetsPracticeChallenge.uniqueChars("x")));
        }

        @Test
        void testAllEqual() {
            assertEquals(List.of('a'), orderOf(SetsPracticeChallenge.uniqueChars("aaaa")));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals(List.of('1', '-', '2'), orderOf(SetsPracticeChallenge.uniqueChars("1-1-2")));
        }

        @Test
        void testSentence() {
            assertEquals(List.of('h', 'e', 'l', 'o', ' ', 'w', 'r', 'd'),
                    orderOf(SetsPracticeChallenge.uniqueChars("hello world")));
        }

        @Test
        void testRepeatedLaterDoesNotMoveTheCharacter() {
            assertEquals(List.of('a', 'b'), orderOf(SetsPracticeChallenge.uniqueChars("abbbaaab")));
        }

        @Test
        void testSize() {
            assertEquals(4, SetsPracticeChallenge.uniqueChars("mississippi").size());
        }
    }

    // ==========================================================
    // CHALLENGE 9: appearOnce
    // ==========================================================
    @Nested
    class AppearOnceTests {

        @Test
        void testMixed() {
            assertEquals(List.of("b", "c"), orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "b", "a", "c"))));
        }

        @Test
        void testAllRepeated() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.appearOnce(List.of("x", "x", "x"))));
        }

        @Test
        void testAllDifferent() {
            assertEquals(List.of("a", "b", "c"), orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "b", "c"))));
        }

        @Test
        void testThreeAppearancesAreNotOne() {
            assertEquals(List.of("b", "c"),
                    orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "a", "b", "a", "c"))));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.appearOnce(List.of())));
        }

        @Test
        void testSingleWord() {
            assertEquals(List.of("a"), orderOf(SetsPracticeChallenge.appearOnce(List.of("a"))));
        }

        @Test
        void testOrderIsTheOrderOfTheList() {
            assertEquals(List.of("z", "m"), orderOf(SetsPracticeChallenge.appearOnce(List.of("z", "a", "m", "a"))));
        }

        @Test
        void testCaseMatters() {
            assertEquals(List.of("a", "A"), orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "A"))));
        }

        @Test
        void testRepeatedWordsFarApart() {
            assertEquals(List.of("b", "c"),
                    orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "b", "c", "d", "a", "d"))));
        }

        @Test
        void testFourAppearances() {
            assertEquals(List.of("b"), orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "a", "a", "a", "b"))));
        }

        @Test
        void testRepeatedPairs() {
            assertEquals(List.of("e"), orderOf(SetsPracticeChallenge.appearOnce(List.of("a", "b", "a", "b", "e"))));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("a", "b", "a"));
            SetsPracticeChallenge.appearOnce(words);
            assertEquals(List.of("a", "b", "a"), words);
        }
    }

    // ==========================================================
    // CHALLENGE 10: mergeKeepingOrder
    // ==========================================================
    @Nested
    class MergeKeepingOrderTests {

        @Test
        void testMixed() {
            LinkedHashSet<String> r = SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("c", "a")), linked(List.of("b", "a", "d")));
            assertEquals(List.of("c", "a", "b", "d"), orderOf(r));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(List.of("x"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("x")), linked(List.of()))));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(List.of("y", "x"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of()), linked(List.of("y", "x")))));
        }

        @Test
        void testBothEmpty() {
            assertEquals(List.of(),
                    orderOf(SetsPracticeChallenge.mergeKeepingOrder(linked(List.of()), linked(List.of()))));
        }

        @Test
        void testFirstGroupGoesFirst() {
            assertEquals(List.of("z", "y", "a", "b"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("z", "y")), linked(List.of("a", "b")))));
        }

        @Test
        void testEqualSets() {
            assertEquals(List.of("b", "a"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("b", "a")), linked(List.of("b", "a")))));
        }

        @Test
        void testSecondInDifferentOrder() {
            assertEquals(List.of("a", "b", "c"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("a", "b")), linked(List.of("c", "b", "a")))));
        }

        @Test
        void testNeitherSetIsModified() {
            LinkedHashSet<String> first = linked(List.of("a", "b"));
            LinkedHashSet<String> second = linked(List.of("c", "a"));
            SetsPracticeChallenge.mergeKeepingOrder(first, second);
            assertEquals(List.of("a", "b"), orderOf(first));
            assertEquals(List.of("c", "a"), orderOf(second));
        }

        @Test
        void testSize() {
            assertEquals(4, SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("a", "b", "c")), linked(List.of("c", "d"))).size());
        }

        @Test
        void testCaseMatters() {
            assertEquals(List.of("a", "A"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("a")), linked(List.of("A", "a")))));
        }

        @Test
        void testWorksWithImmutableSets() {
            assertEquals(Set.of("a", "b"), SetsPracticeChallenge.mergeKeepingOrder(Set.of("a"), Set.of("b")));
        }

        @Test
        void testSecondGroupKeepsItsOwnOrder() {
            assertEquals(List.of("a", "z", "y", "x"), orderOf(SetsPracticeChallenge.mergeKeepingOrder(
                    linked(List.of("a")), linked(List.of("z", "y", "x")))));
        }
    }

    // ==========================================================
    // CHALLENGE 11: sortedDistinct
    // ==========================================================
    @Nested
    class SortedDistinctTests {

        @Test
        void testMixed() {
            assertEquals(List.of(1, 2, 3), orderOf(SetsPracticeChallenge.sortedDistinct(List.of(3, 1, 3, 2))));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(List.of(-1, 0), orderOf(SetsPracticeChallenge.sortedDistinct(List.of(-1, 0, -1))));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.sortedDistinct(List.of())));
        }

        @Test
        void testAlreadySorted() {
            assertEquals(List.of(1, 2, 3), orderOf(SetsPracticeChallenge.sortedDistinct(List.of(1, 2, 3))));
        }

        @Test
        void testReverseOrder() {
            assertEquals(List.of(1, 2, 3), orderOf(SetsPracticeChallenge.sortedDistinct(List.of(3, 2, 1))));
        }

        @Test
        void testAllEqual() {
            assertEquals(List.of(7), orderOf(SetsPracticeChallenge.sortedDistinct(List.of(7, 7, 7))));
        }

        @Test
        void testNumericalOrderNotTextOrder() {
            assertEquals(List.of(2, 10, 100), orderOf(SetsPracticeChallenge.sortedDistinct(List.of(100, 2, 10))));
        }

        @Test
        void testFirstAndLast() {
            TreeSet<Integer> r = SetsPracticeChallenge.sortedDistinct(List.of(5, 9, 1, 7));
            assertEquals(1, r.first());
            assertEquals(9, r.last());
        }

        @Test
        void testExtremeValues() {
            assertEquals(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE),
                    orderOf(SetsPracticeChallenge.sortedDistinct(List.of(Integer.MAX_VALUE, Integer.MIN_VALUE, 0))));
        }

        @Test
        void testListIsNotModified() {
            List<Integer> numbers = new ArrayList<>(List.of(3, 1, 3));
            SetsPracticeChallenge.sortedDistinct(numbers);
            assertEquals(List.of(3, 1, 3), numbers);
        }

        @Test
        void testSize() {
            assertEquals(3, SetsPracticeChallenge.sortedDistinct(List.of(1, 2, 3, 1, 2, 3)).size());
        }
    }

    // ==========================================================
    // CHALLENGE 12: nextBigger
    // ==========================================================
    @Nested
    class NextBiggerTests {

        @Test
        void testBetweenElements() {
            assertEquals(5, SetsPracticeChallenge.nextBigger(treeOf(2, 5, 9), 4));
        }

        @Test
        void testStrictlyGreater() {
            assertEquals(9, SetsPracticeChallenge.nextBigger(treeOf(2, 5, 9), 5));
        }

        @Test
        void testNoneBigger() {
            assertEquals(-1, SetsPracticeChallenge.nextBigger(treeOf(2, 5, 9), 9));
        }

        @Test
        void testNegativeN() {
            assertEquals(2, SetsPracticeChallenge.nextBigger(treeOf(2, 5, 9), -3));
        }

        @Test
        void testGreaterThanAll() {
            assertEquals(-1, SetsPracticeChallenge.nextBigger(treeOf(2, 5, 9), 100));
        }

        @Test
        void testEmptySet() {
            assertEquals(-1, SetsPracticeChallenge.nextBigger(new TreeSet<>(), 3));
        }

        @Test
        void testSingleElement() {
            assertEquals(4, SetsPracticeChallenge.nextBigger(treeOf(4), 3));
            assertEquals(-1, SetsPracticeChallenge.nextBigger(treeOf(4), 4));
        }

        @Test
        void testZeroIsAnElement() {
            assertEquals(0, SetsPracticeChallenge.nextBigger(treeOf(0, 3), -1));
            assertEquals(3, SetsPracticeChallenge.nextBigger(treeOf(0, 3), 0));
        }

        @Test
        void testJustBelowAnElement() {
            assertEquals(9, SetsPracticeChallenge.nextBigger(treeOf(2, 5, 9), 8));
        }

        @Test
        void testExtremeN() {
            assertEquals(2, SetsPracticeChallenge.nextBigger(treeOf(2, 5), Integer.MIN_VALUE));
            assertEquals(-1, SetsPracticeChallenge.nextBigger(treeOf(2, 5), Integer.MAX_VALUE));
        }

        @Test
        void testSetIsNotModified() {
            TreeSet<Integer> set = treeOf(2, 5, 9);
            SetsPracticeChallenge.nextBigger(set, 4);
            assertEquals(List.of(2, 5, 9), orderOf(set));
        }

        @Test
        void testNeighbour() {
            assertEquals(3, SetsPracticeChallenge.nextBigger(treeOf(1, 2, 3, 4), 2));
        }
    }

    // ==========================================================
    // CHALLENGE 13: countBetween
    // ==========================================================
    @Nested
    class CountBetweenTests {

        @Test
        void testBothEndsIncluded() {
            assertEquals(3, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 5, 12));
        }

        @Test
        void testNothingInRange() {
            assertEquals(0, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 2, 4));
        }

        @Test
        void testSingleElementRange() {
            assertEquals(1, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 9, 9));
        }

        @Test
        void testFromGreaterThanTo() {
            assertEquals(0, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 12, 5));
        }

        @Test
        void testWholeSet() {
            assertEquals(4, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 1, 12));
            assertEquals(4, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), -100, 100));
        }

        @Test
        void testOnlyTheFromElementIsIncluded() {
            assertEquals(1, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 12, 20));
        }

        @Test
        void testOnlyTheToElementIsIncluded() {
            assertEquals(1, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), -5, 1));
        }

        @Test
        void testEmptySet() {
            assertEquals(0, SetsPracticeChallenge.countBetween(new TreeSet<>(), 0, 10));
        }

        @Test
        void testEndsWithoutElements() {
            assertEquals(2, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 4, 10));
        }

        @Test
        void testFromEqualsToWithoutElement() {
            assertEquals(0, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 6, 6));
        }

        @Test
        void testFromGreaterThanToInsideTheElements() {
            assertEquals(0, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 9, 5));
        }

        @Test
        void testSetIsNotModified() {
            TreeSet<Integer> set = treeOf(1, 5, 9, 12);
            SetsPracticeChallenge.countBetween(set, 5, 9);
            assertEquals(4, set.size());
        }

        @Test
        void testExcludingTheEnds() {
            assertEquals(1, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 6, 11));
            assertEquals(2, SetsPracticeChallenge.countBetween(treeOf(1, 5, 9, 12), 5, 9));
        }
    }

    // ==========================================================
    // CHALLENGE 14: byLengthThenAlphabetical
    // ==========================================================
    @Nested
    class ByLengthThenAlphabeticalTests {

        @Test
        void testMixed() {
            assertEquals(List.of("a", "aa", "bb", "ccc"),
                    orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("bb", "a", "ccc", "aa", "a"))));
        }

        @Test
        void testUppercaseFirst() {
            assertEquals(List.of("A", "b"), orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("b", "A"))));
        }

        @Test
        void testEmpty() {
            assertEquals(List.of(), orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of())));
        }

        @Test
        void testLengthMattersMoreThanLetters() {
            assertEquals(List.of("z", "aa"),
                    orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("aa", "z"))));
        }

        @Test
        void testSameLengthAlphabetical() {
            assertEquals(List.of("ab", "ba", "cd"),
                    orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("cd", "ba", "ab"))));
        }

        @Test
        void testDifferentWordsWithSameLengthAreKept() {
            assertEquals(3, SetsPracticeChallenge.byLengthThenAlphabetical(List.of("aa", "bb", "cc")).size());
        }

        @Test
        void testRepeatedWordsAreRemoved() {
            assertEquals(List.of("a"), orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("a", "a", "a"))));
        }

        @Test
        void testCaseMakesWordsDifferent() {
            assertEquals(List.of("A", "a"), orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("a", "A"))));
        }

        @Test
        void testEmptyWordIsFirst() {
            assertEquals(List.of("", "a"), orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("a", ""))));
        }

        @Test
        void testFirstAndLast() {
            TreeSet<String> r = SetsPracticeChallenge.byLengthThenAlphabetical(List.of("hello", "hi", "hey", "a"));
            assertEquals("a", r.first());
            assertEquals("hello", r.last());
        }

        @Test
        void testSpacesMakeWordsDifferent() {
            assertEquals(List.of("a", " a", "a "),
                    orderOf(SetsPracticeChallenge.byLengthThenAlphabetical(List.of("a ", "a", " a"))));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("bb", "a"));
            SetsPracticeChallenge.byLengthThenAlphabetical(words);
            assertEquals(List.of("bb", "a"), words);
        }

        @Test
        void testManyWords() {
            assertEquals(List.of("a", "be", "to", "not", "the"), orderOf(
                    SetsPracticeChallenge.byLengthThenAlphabetical(List.of("the", "not", "to", "be", "a", "to"))));
        }
    }

    // ==========================================================
    // CHALLENGE 15: removeLargest
    // ==========================================================
    @Nested
    class RemoveLargestTests {

        @Test
        void testTwoRemoved() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            assertEquals(List.of(3, 2), SetsPracticeChallenge.removeLargest(set, 2));
            assertEquals(List.of(1), orderOf(set));
        }

        @Test
        void testZeroChangesNothing() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            assertEquals(List.of(), SetsPracticeChallenge.removeLargest(set, 0));
            assertEquals(List.of(1, 2, 3), orderOf(set));
        }

        @Test
        void testMoreThanTheSize() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            assertEquals(List.of(3, 2, 1), SetsPracticeChallenge.removeLargest(set, 5));
            assertTrue(set.isEmpty());
        }

        @Test
        void testExactlyTheSize() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            assertEquals(List.of(3, 2, 1), SetsPracticeChallenge.removeLargest(set, 3));
            assertTrue(set.isEmpty());
        }

        @Test
        void testOne() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            assertEquals(List.of(3), SetsPracticeChallenge.removeLargest(set, 1));
            assertEquals(List.of(1, 2), orderOf(set));
        }

        @Test
        void testEmptySet() {
            TreeSet<Integer> set = new TreeSet<>();
            assertEquals(List.of(), SetsPracticeChallenge.removeLargest(set, 3));
            assertTrue(set.isEmpty());
        }

        @Test
        void testDescendingOrder() {
            TreeSet<Integer> set = treeOf(10, 30, 20, 40);
            assertEquals(List.of(40, 30, 20), SetsPracticeChallenge.removeLargest(set, 3));
            assertEquals(List.of(10), orderOf(set));
        }

        @Test
        void testNegativeNumbers() {
            TreeSet<Integer> set = treeOf(-5, 0, 5);
            assertEquals(List.of(5), SetsPracticeChallenge.removeLargest(set, 1));
            assertEquals(List.of(-5, 0), orderOf(set));
        }

        @Test
        void testRemovedOnesAreGoneAndOthersStay() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            SetsPracticeChallenge.removeLargest(set, 2);
            assertFalse(set.contains(3));
            assertFalse(set.contains(2));
            assertTrue(set.contains(1));
        }

        @Test
        void testCalledTwice() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            assertEquals(List.of(3), SetsPracticeChallenge.removeLargest(set, 1));
            assertEquals(List.of(2), SetsPracticeChallenge.removeLargest(set, 1));
            assertEquals(List.of(1), SetsPracticeChallenge.removeLargest(set, 1));
            assertEquals(List.of(), SetsPracticeChallenge.removeLargest(set, 1));
        }

        @Test
        void testSizeAfterwards() {
            TreeSet<Integer> set = treeOf(3, 1, 2);
            SetsPracticeChallenge.removeLargest(set, 2);
            assertEquals(1, set.size());
        }

        @Test
        void testLargerSet() {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 1; i <= 10; i++) {
                set.add(i);
            }
            assertEquals(List.of(10, 9, 8, 7), SetsPracticeChallenge.removeLargest(set, 4));
            assertEquals(6, set.size());
            assertEquals(6, set.last());
        }
    }
}