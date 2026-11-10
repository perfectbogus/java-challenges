package dev.perfectbogus.optionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class OptionalFunctionalChallengeTest {

    // ==========================================================
    // CHALLENGE 1: firstLongWord
    // ==========================================================
    @Nested
    class FirstLongWordTests {

        @Test
        void testFindsTheFirstMatch() {
            assertEquals(Optional.of("hello"),
                    OptionalFunctionalChallenge.firstLongWord(List.of("hi", "hello", "world"), 4));
        }

        @Test
        void testFirstWordMatches() {
            assertEquals(Optional.of("hi"), OptionalFunctionalChallenge.firstLongWord(List.of("hi", "hello"), 2));
        }

        @Test
        void testNoWordIsLongEnough() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.firstLongWord(List.of("hi", "yes"), 5));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.firstLongWord(List.of(), 1));
        }

        @Test
        void testExactLengthCounts() {
            assertEquals(Optional.of("abc"),
                    OptionalFunctionalChallenge.firstLongWord(List.of("ab", "abc", "abcd"), 3));
        }

        @Test
        void testOneBelowDoesNotCount() {
            assertEquals(Optional.of("abcd"),
                    OptionalFunctionalChallenge.firstLongWord(List.of("ab", "abc", "abcd"), 4));
        }

        @Test
        void testMinLengthZeroGivesTheFirstWord() {
            assertEquals(Optional.of(""), OptionalFunctionalChallenge.firstLongWord(List.of("", "a"), 0));
        }

        @Test
        void testNegativeMinLength() {
            assertEquals(Optional.of("x"), OptionalFunctionalChallenge.firstLongWord(List.of("x", "yy"), -3));
        }

        @Test
        void testOnlyTheLastWordMatches() {
            assertEquals(Optional.of("longest"),
                    OptionalFunctionalChallenge.firstLongWord(List.of("a", "bb", "ccc", "longest"), 7));
        }

        @Test
        void testSeveralMatchesGiveTheFirst() {
            assertEquals(Optional.of("bbb"),
                    OptionalFunctionalChallenge.firstLongWord(List.of("a", "bbb", "cccc", "ddddd"), 3));
        }

        @Test
        void testEmptyWordsAreSkipped() {
            assertEquals(Optional.of("a"), OptionalFunctionalChallenge.firstLongWord(List.of("", "", "a"), 1));
        }

        @Test
        void testTheCaseDoesNotMatter() {
            assertEquals(Optional.of("ABC"), OptionalFunctionalChallenge.firstLongWord(List.of("ab", "ABC"), 3));
        }

        @Test
        void testResultIsNeverNull() {
            assertNotNull(OptionalFunctionalChallenge.firstLongWord(List.of("a"), 10));
        }
    }

    // ==========================================================
    // CHALLENGE 2: parseInteger
    // ==========================================================
    @Nested
    class ParseIntegerTests {

        @Test
        void testPositive() {
            assertEquals(Optional.of(42), OptionalFunctionalChallenge.parseInteger("42"));
        }

        @Test
        void testNegative() {
            assertEquals(Optional.of(-7), OptionalFunctionalChallenge.parseInteger("-7"));
        }

        @Test
        void testZero() {
            assertEquals(Optional.of(0), OptionalFunctionalChallenge.parseInteger("0"));
        }

        @Test
        void testLetters() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("abc"));
        }

        @Test
        void testEmptyText() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger(""));
        }

        @Test
        void testTooBig() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("2147483648"));
        }

        @Test
        void testBiggestInt() {
            assertEquals(Optional.of(2147483647), OptionalFunctionalChallenge.parseInteger("2147483647"));
        }

        @Test
        void testSmallestInt() {
            assertEquals(Optional.of(-2147483648), OptionalFunctionalChallenge.parseInteger("-2147483648"));
        }

        @Test
        void testTooSmall() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("-2147483649"));
        }

        @Test
        void testDecimalNumber() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("5.0"));
        }

        @Test
        void testSpacesAroundTheNumber() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger(" 5"));
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("5 "));
        }

        @Test
        void testOnlyAMinusSign() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("-"));
        }

        @Test
        void testTwoMinusSigns() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("--5"));
        }

        @Test
        void testPlusSignIsAccepted() {
            assertEquals(Optional.of(5), OptionalFunctionalChallenge.parseInteger("+5"));
        }

        @Test
        void testLeadingZeros() {
            assertEquals(Optional.of(7), OptionalFunctionalChallenge.parseInteger("007"));
        }

        @Test
        void testNumberWithLetterInside() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("12a3"));
        }

        @Test
        void testNumberWithSeparator() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.parseInteger("1,000"));
        }
    }

    // ==========================================================
    // CHALLENGE 3: greeting
    // ==========================================================
    @Nested
    class GreetingTests {

        @Test
        void testWithName() {
            assertEquals("Hello, ana!", OptionalFunctionalChallenge.greeting(Optional.of("ana")));
        }

        @Test
        void testWithoutName() {
            assertEquals("Hello, stranger!", OptionalFunctionalChallenge.greeting(Optional.empty()));
        }

        @Test
        void testNameWithSpace() {
            assertEquals("Hello, Mr Smith!", OptionalFunctionalChallenge.greeting(Optional.of("Mr Smith")));
        }

        @Test
        void testEmptyNameIsUsedAsItIs() {
            assertEquals("Hello, !", OptionalFunctionalChallenge.greeting(Optional.of("")));
        }

        @Test
        void testAnotherName() {
            assertEquals("Hello, Bob!", OptionalFunctionalChallenge.greeting(Optional.of("Bob")));
        }

        @Test
        void testNameStrangerIsUsedAsItIs() {
            assertEquals("Hello, stranger!", OptionalFunctionalChallenge.greeting(Optional.of("stranger")));
        }

        @Test
        void testNameKeepsItsCase() {
            assertEquals("Hello, ANA!", OptionalFunctionalChallenge.greeting(Optional.of("ANA")));
        }

        @Test
        void testNameWithSpacesAroundIsNotTrimmed() {
            assertEquals("Hello,  ana !", OptionalFunctionalChallenge.greeting(Optional.of(" ana ")));
        }

        @Test
        void testNameWithExclamationMark() {
            assertEquals("Hello, wow!!", OptionalFunctionalChallenge.greeting(Optional.of("wow!")));
        }
    }

    // ==========================================================
    // CHALLENGE 4: firstPresent
    // ==========================================================
    @Nested
    class FirstPresentTests {

        @Test
        void testBothPresent() {
            assertEquals(Optional.of("a"),
                    OptionalFunctionalChallenge.firstPresent(Optional.of("a"), Optional.of("b")));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(Optional.of("b"),
                    OptionalFunctionalChallenge.firstPresent(Optional.empty(), Optional.of("b")));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(Optional.of("a"),
                    OptionalFunctionalChallenge.firstPresent(Optional.of("a"), Optional.empty()));
        }

        @Test
        void testBothEmpty() {
            assertEquals(Optional.empty(),
                    OptionalFunctionalChallenge.firstPresent(Optional.empty(), Optional.empty()));
        }

        @Test
        void testSameValues() {
            assertEquals(Optional.of("x"),
                    OptionalFunctionalChallenge.firstPresent(Optional.of("x"), Optional.of("x")));
        }

        @Test
        void testEmptyTextIsAValue() {
            assertEquals(Optional.of(""),
                    OptionalFunctionalChallenge.firstPresent(Optional.of(""), Optional.of("b")));
        }

        @Test
        void testSecondValueIsTheOneKept() {
            assertEquals(Optional.of("second"),
                    OptionalFunctionalChallenge.firstPresent(Optional.empty(), Optional.of("second")));
        }

        @Test
        void testResultIsNeverNull() {
            assertNotNull(OptionalFunctionalChallenge.firstPresent(Optional.empty(), Optional.empty()));
        }
    }

    // ==========================================================
    // CHALLENGE 5: lengthIfLongEnough
    // ==========================================================
    @Nested
    class LengthIfLongEnoughTests {

        @Test
        void testLongerThanTheMinimum() {
            assertEquals(Optional.of(5), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("hello"), 3));
        }

        @Test
        void testExactlyTheMinimum() {
            assertEquals(Optional.of(5), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("hello"), 5));
        }

        @Test
        void testOneBelowTheMinimum() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("hello"), 6));
        }

        @Test
        void testTooShort() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("hi"), 3));
        }

        @Test
        void testEmptyOptional() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.empty(), 0));
        }

        @Test
        void testEmptyTextWithMinimumZero() {
            assertEquals(Optional.of(0), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of(""), 0));
        }

        @Test
        void testEmptyTextWithMinimumOne() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of(""), 1));
        }

        @Test
        void testNegativeMinimum() {
            assertEquals(Optional.of(2), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("hi"), -4));
        }

        @Test
        void testTheLengthIsReturnedNotTheMinimum() {
            assertEquals(Optional.of(10), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("abcdefghij"), 2));
        }

        @Test
        void testEmptyOptionalWithNegativeMinimum() {
            assertEquals(Optional.empty(), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.empty(), -1));
        }

        @Test
        void testTextWithSpaces() {
            assertEquals(Optional.of(3), OptionalFunctionalChallenge.lengthIfLongEnough(Optional.of("a b"), 3));
        }
    }

    // ==========================================================
    // CHALLENGE 6: sumPresent
    // ==========================================================
    @Nested
    class SumPresentTests {

        @Test
        void testMixed() {
            assertEquals(3, OptionalFunctionalChallenge.sumPresent(
                    List.of(Optional.of(1), Optional.empty(), Optional.of(2))));
        }

        @Test
        void testAllEmpty() {
            assertEquals(0, OptionalFunctionalChallenge.sumPresent(List.of(Optional.empty(), Optional.empty())));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(7, OptionalFunctionalChallenge.sumPresent(
                    List.of(Optional.of(-5), Optional.of(5), Optional.of(7))));
        }

        @Test
        void testEmptyList() {
            assertEquals(0, OptionalFunctionalChallenge.sumPresent(List.of()));
        }

        @Test
        void testOneNumber() {
            assertEquals(9, OptionalFunctionalChallenge.sumPresent(List.of(Optional.of(9))));
        }

        @Test
        void testEmptyAtTheStartAndAtTheEnd() {
            assertEquals(30, OptionalFunctionalChallenge.sumPresent(
                    List.of(Optional.empty(), Optional.of(10), Optional.of(20), Optional.empty())));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(12, OptionalFunctionalChallenge.sumPresent(
                    List.of(Optional.of(4), Optional.of(4), Optional.of(4))));
        }

        @Test
        void testZeros() {
            assertEquals(0, OptionalFunctionalChallenge.sumPresent(List.of(Optional.of(0), Optional.of(0))));
        }

        @Test
        void testOnlyNegatives() {
            assertEquals(-6, OptionalFunctionalChallenge.sumPresent(
                    List.of(Optional.of(-1), Optional.empty(), Optional.of(-5))));
        }

        @Test
        void testManyEmptyOnes() {
            List<Optional<Integer>> values = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                values.add(i % 10 == 0 ? Optional.of(i) : Optional.empty());
            }
            assertEquals(450, OptionalFunctionalChallenge.sumPresent(values));
        }

        @Test
        void testBigNumbers() {
            assertEquals(2_000_000_000, OptionalFunctionalChallenge.sumPresent(
                    List.of(Optional.of(1_000_000_000), Optional.of(1_000_000_000))));
        }
    }

    // ==========================================================
    // CHALLENGE 7: longerThan
    // ==========================================================
    @Nested
    class LongerThanTests {

        @Test
        void testLongerText() {
            assertTrue(OptionalFunctionalChallenge.longerThan(3).test("abcd"));
        }

        @Test
        void testEqualLengthIsFalse() {
            assertFalse(OptionalFunctionalChallenge.longerThan(3).test("abc"));
        }

        @Test
        void testShorterText() {
            assertFalse(OptionalFunctionalChallenge.longerThan(3).test("ab"));
        }

        @Test
        void testEmptyTextWithZero() {
            assertFalse(OptionalFunctionalChallenge.longerThan(0).test(""));
        }

        @Test
        void testEmptyTextWithNegative() {
            assertTrue(OptionalFunctionalChallenge.longerThan(-1).test(""));
        }

        @Test
        void testOneCharacterWithZero() {
            assertTrue(OptionalFunctionalChallenge.longerThan(0).test("a"));
        }

        @Test
        void testTheResultIsNeverNull() {
            assertNotNull(OptionalFunctionalChallenge.longerThan(1));
        }

        @Test
        void testTwoPredicatesAreIndependent() {
            Predicate<String> longerThanOne = OptionalFunctionalChallenge.longerThan(1);
            Predicate<String> longerThanFive = OptionalFunctionalChallenge.longerThan(5);
            assertTrue(longerThanOne.test("abc"));
            assertFalse(longerThanFive.test("abc"));
            assertTrue(longerThanFive.test("abcdef"));
        }

        @Test
        void testSamePredicateManyTimes() {
            Predicate<String> predicate = OptionalFunctionalChallenge.longerThan(2);
            assertTrue(predicate.test("abc"));
            assertFalse(predicate.test("ab"));
            assertTrue(predicate.test("abcd"));
            assertFalse(predicate.test(""));
        }

        @Test
        void testSpacesCount() {
            assertTrue(OptionalFunctionalChallenge.longerThan(2).test("a b"));
            assertFalse(OptionalFunctionalChallenge.longerThan(2).test("a "));
        }

        @Test
        void testSpacesAtTheEndsCount() {
            assertTrue(OptionalFunctionalChallenge.longerThan(2).test("a  "));
            assertTrue(OptionalFunctionalChallenge.longerThan(2).test("  a"));
            assertTrue(OptionalFunctionalChallenge.longerThan(0).test("   "));
        }

        @Test
        void testLongText() {
            assertTrue(OptionalFunctionalChallenge.longerThan(999).test("x".repeat(1000)));
            assertFalse(OptionalFunctionalChallenge.longerThan(1000).test("x".repeat(1000)));
        }

        @Test
        void testCanBeNegated() {
            assertFalse(OptionalFunctionalChallenge.longerThan(3).negate().test("abcd"));
            assertTrue(OptionalFunctionalChallenge.longerThan(3).negate().test("abc"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: isValidUsername
    // ==========================================================
    @Nested
    class IsValidUsernameTests {

        @Test
        void testValidName() {
            assertTrue(OptionalFunctionalChallenge.isValidUsername().test("ana"));
        }

        @Test
        void testTooShort() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("an"));
        }

        @Test
        void testContainsASpace() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("ana smith"));
        }

        @Test
        void testTooLong() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("abcdefghijk"));
        }

        @Test
        void testExactlyTenCharacters() {
            assertTrue(OptionalFunctionalChallenge.isValidUsername().test("abcdefghij"));
        }

        @Test
        void testExactlyThreeCharacters() {
            assertTrue(OptionalFunctionalChallenge.isValidUsername().test("abc"));
        }

        @Test
        void testEmptyText() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test(""));
        }

        @Test
        void testOneCharacter() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("a"));
        }

        @Test
        void testSpaceAtTheStart() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test(" ana"));
        }

        @Test
        void testSpaceAtTheEnd() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("ana "));
        }

        @Test
        void testOnlySpaces() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("    "));
        }

        @Test
        void testDigitsAndSymbolsAreAllowed() {
            assertTrue(OptionalFunctionalChallenge.isValidUsername().test("a_b-1"));
        }

        @Test
        void testTabIsNotASpace() {
            assertTrue(OptionalFunctionalChallenge.isValidUsername().test("a\tb"));
        }

        @Test
        void testUppercaseIsAllowed() {
            assertTrue(OptionalFunctionalChallenge.isValidUsername().test("ANA"));
        }

        @Test
        void testTwoCharactersPlusSpaceHasRightLengthButSpace() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("a b"));
        }

        @Test
        void testLongNameWithSpace() {
            assertFalse(OptionalFunctionalChallenge.isValidUsername().test("abcde fghijklm"));
        }

        @Test
        void testSamePredicateManyTimes() {
            Predicate<String> predicate = OptionalFunctionalChallenge.isValidUsername();
            assertTrue(predicate.test("bob"));
            assertFalse(predicate.test("bo"));
            assertTrue(predicate.test("alice"));
            assertFalse(predicate.test("a lice"));
        }
    }

    // ==========================================================
    // CHALLENGE 9: applyAll
    // ==========================================================
    @Nested
    class ApplyAllTests {

        @Test
        void testAddThenDouble() {
            List<Function<Integer, Integer>> steps = List.of(x -> x + 1, x -> x * 2);
            assertEquals(8, OptionalFunctionalChallenge.applyAll(steps, 3));
        }

        @Test
        void testDoubleThenAdd() {
            List<Function<Integer, Integer>> steps = List.of(x -> x * 2, x -> x + 1);
            assertEquals(7, OptionalFunctionalChallenge.applyAll(steps, 3));
        }

        @Test
        void testNoFunctions() {
            assertEquals(5, OptionalFunctionalChallenge.applyAll(List.of(), 5));
        }

        @Test
        void testOneFunction() {
            List<Function<Integer, Integer>> steps = List.of(x -> x - 4);
            assertEquals(6, OptionalFunctionalChallenge.applyAll(steps, 10));
        }

        @Test
        void testThreeFunctions() {
            List<Function<Integer, Integer>> steps = List.of(x -> x + 2, x -> x * x, x -> x - 1);
            assertEquals(24, OptionalFunctionalChallenge.applyAll(steps, 3));
        }

        @Test
        void testEachFunctionIsUsedOnce() {
            int[] calls = {0};
            Function<Integer, Integer> counting = x -> {
                calls[0]++;
                return x;
            };
            OptionalFunctionalChallenge.applyAll(List.of(counting, counting, counting), 1);
            assertEquals(3, calls[0]);
        }

        @Test
        void testOrderOfCalls() {
            List<String> order = new ArrayList<>();
            List<Function<Integer, Integer>> steps = List.of(x -> {
                order.add("first");
                return x;
            }, x -> {
                order.add("second");
                return x;
            }, x -> {
                order.add("third");
                return x;
            });
            OptionalFunctionalChallenge.applyAll(steps, 0);
            assertEquals(List.of("first", "second", "third"), order);
        }

        @Test
        void testTheFirstFunctionReceivesTheStartNumber() {
            List<Function<Integer, Integer>> steps = List.of(x -> x == 42 ? 1 : 0);
            assertEquals(1, OptionalFunctionalChallenge.applyAll(steps, 42));
        }

        @Test
        void testEachFunctionReceivesTheResultOfThePreviousOne() {
            List<Function<Integer, Integer>> steps = List.of(x -> 10, x -> x == 10 ? 20 : -1);
            assertEquals(20, OptionalFunctionalChallenge.applyAll(steps, 0));
        }

        @Test
        void testNegativeStart() {
            List<Function<Integer, Integer>> steps = List.of(x -> x * 3, x -> x + 1);
            assertEquals(-8, OptionalFunctionalChallenge.applyAll(steps, -3));
        }

        @Test
        void testIdentityFunctions() {
            List<Function<Integer, Integer>> steps = List.of(Function.identity(), Function.identity());
            assertEquals(9, OptionalFunctionalChallenge.applyAll(steps, 9));
        }

        @Test
        void testManyFunctions() {
            List<Function<Integer, Integer>> steps = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                steps.add(x -> x + 1);
            }
            assertEquals(100, OptionalFunctionalChallenge.applyAll(steps, 0));
        }

        @Test
        void testListIsNotModified() {
            List<Function<Integer, Integer>> steps = new ArrayList<>();
            steps.add(x -> x + 1);
            OptionalFunctionalChallenge.applyAll(steps, 0);
            assertEquals(1, steps.size());
        }
    }

    // ==========================================================
    // CHALLENGE 10: counterFrom
    // ==========================================================
    @Nested
    class CounterFromTests {

        @Test
        void testSuccessiveCalls() {
            Supplier<Integer> counter = OptionalFunctionalChallenge.counterFrom(5);
            assertEquals(5, counter.get());
            assertEquals(6, counter.get());
            assertEquals(7, counter.get());
        }

        @Test
        void testStartingAtZero() {
            Supplier<Integer> counter = OptionalFunctionalChallenge.counterFrom(0);
            assertEquals(0, counter.get());
            assertEquals(1, counter.get());
        }

        @Test
        void testNegativeStart() {
            Supplier<Integer> counter = OptionalFunctionalChallenge.counterFrom(-2);
            assertEquals(-2, counter.get());
            assertEquals(-1, counter.get());
            assertEquals(0, counter.get());
            assertEquals(1, counter.get());
        }

        @Test
        void testTheFirstCallGivesTheStartNumber() {
            assertEquals(100, OptionalFunctionalChallenge.counterFrom(100).get());
        }

        @Test
        void testCountersAreIndependent() {
            Supplier<Integer> a = OptionalFunctionalChallenge.counterFrom(0);
            Supplier<Integer> b = OptionalFunctionalChallenge.counterFrom(0);
            assertEquals(0, a.get());
            assertEquals(1, a.get());
            assertEquals(0, b.get());
        }

        @Test
        void testCountersWithDifferentStarts() {
            Supplier<Integer> a = OptionalFunctionalChallenge.counterFrom(10);
            Supplier<Integer> b = OptionalFunctionalChallenge.counterFrom(20);
            assertEquals(10, a.get());
            assertEquals(20, b.get());
            assertEquals(11, a.get());
            assertEquals(21, b.get());
        }

        @Test
        void testManyCalls() {
            Supplier<Integer> counter = OptionalFunctionalChallenge.counterFrom(1);
            int last = 0;
            for (int i = 0; i < 1000; i++) {
                last = counter.get();
            }
            assertEquals(1000, last);
        }

        @Test
        void testTwoCountersWithTheSameStartAfterManyCalls() {
            Supplier<Integer> a = OptionalFunctionalChallenge.counterFrom(5);
            for (int i = 0; i < 10; i++) {
                a.get();
            }
            assertEquals(5, OptionalFunctionalChallenge.counterFrom(5).get());
            assertEquals(15, a.get());
        }

        @Test
        void testTheResultIsNeverNull() {
            assertNotNull(OptionalFunctionalChallenge.counterFrom(0));
        }

        @Test
        void testEachNumberIsOneMoreThanThePrevious() {
            Supplier<Integer> counter = OptionalFunctionalChallenge.counterFrom(7);
            int previous = counter.get();
            for (int i = 0; i < 20; i++) {
                int next = counter.get();
                assertEquals(previous + 1, next);
                previous = next;
            }
        }
    }

    // ==========================================================
    // CHALLENGE 11: forEachLongWord
    // ==========================================================
    @Nested
    class ForEachLongWordTests {

        @Test
        void testCallsTheActionInOrder() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("hi", "hello", "a", "world"), 3, received::add);
            assertEquals(List.of("hello", "world"), received);
        }

        @Test
        void testRepeatedWordsAreUsedEachTime() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("hi", "hi"), 2, received::add);
            assertEquals(List.of("hi", "hi"), received);
        }

        @Test
        void testNoWordIsLongEnough() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("a", "b"), 5, received::add);
            assertEquals(List.of(), received);
        }

        @Test
        void testEmptyList() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of(), 0, received::add);
            assertEquals(List.of(), received);
        }

        @Test
        void testExactLengthCounts() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("ab", "abc", "abcd"), 3, received::add);
            assertEquals(List.of("abc", "abcd"), received);
        }

        @Test
        void testMinimumZeroUsesEveryWord() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("", "a", "bb"), 0, received::add);
            assertEquals(List.of("", "a", "bb"), received);
        }

        @Test
        void testNegativeMinimumUsesEveryWord() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("a", "bb"), -1, received::add);
            assertEquals(List.of("a", "bb"), received);
        }

        @Test
        void testTheActionIsNotCalledWhenNothingMatches() {
            int[] calls = {0};
            OptionalFunctionalChallenge.forEachLongWord(List.of("a", "b"), 5, w -> calls[0]++);
            assertEquals(0, calls[0]);
        }

        @Test
        void testNumberOfCalls() {
            int[] calls = {0};
            OptionalFunctionalChallenge.forEachLongWord(List.of("aaa", "b", "ccc", "dddd"), 3, w -> calls[0]++);
            assertEquals(3, calls[0]);
        }

        @Test
        void testOrderIsTheOrderOfTheListNotAlphabetical() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("zzz", "aaa", "mmm"), 3, received::add);
            assertEquals(List.of("zzz", "aaa", "mmm"), received);
        }

        @Test
        void testTheWordIsPassedUnchanged() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("HeLLo"), 1, received::add);
            assertEquals(List.of("HeLLo"), received);
        }

        @Test
        void testOnlyTheLastWordMatches() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("a", "bb", "cccc"), 4, received::add);
            assertEquals(List.of("cccc"), received);
        }

        @Test
        void testOnlyTheFirstWordMatches() {
            List<String> received = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("cccc", "bb", "a"), 4, received::add);
            assertEquals(List.of("cccc"), received);
        }

        @Test
        void testTheActionCanBeCalledWithADifferentConsumerEachTime() {
            List<String> first = new ArrayList<>();
            List<String> second = new ArrayList<>();
            OptionalFunctionalChallenge.forEachLongWord(List.of("abc", "de"), 2, first::add);
            OptionalFunctionalChallenge.forEachLongWord(List.of("abc", "de"), 3, second::add);
            assertEquals(List.of("abc", "de"), first);
            assertEquals(List.of("abc"), second);
        }
    }
}