package dev.perfectbogus.functional.interfaces;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalInterfacesChallengeTest {

    // ==========================================================
    // CHALLENGE 1: addTo
    // ==========================================================
    @Nested
    class AddToTests {

        @Test
        void testAddsTexts() {
            List<String> list = new ArrayList<>();
            Consumer<String> c = FunctionalInterfacesChallenge.addTo(list);
            c.accept("a");
            c.accept("b");
            assertEquals(List.of("a", "b"), list);
        }

        @Test
        void testNothingIsAddedWhenCreated() {
            List<String> list = new ArrayList<>();
            FunctionalInterfacesChallenge.addTo(list);
            assertEquals(0, list.size());
        }

        @Test
        void testRepeatedTextsAreAdded() {
            List<String> list = new ArrayList<>();
            Consumer<String> c = FunctionalInterfacesChallenge.addTo(list);
            c.accept("a");
            c.accept("b");
            c.accept("a");
            assertEquals(List.of("a", "b", "a"), list);
        }

        @Test
        void testExistingElementsAreKept() {
            List<String> list = new ArrayList<>(List.of("x"));
            FunctionalInterfacesChallenge.addTo(list).accept("y");
            assertEquals(List.of("x", "y"), list);
        }

        @Test
        void testTextIsNotChanged() {
            List<String> list = new ArrayList<>();
            FunctionalInterfacesChallenge.addTo(list).accept("MiXed Case ");
            assertEquals(List.of("MiXed Case "), list);
        }

        @Test
        void testTwoConsumersForTwoLists() {
            List<String> first = new ArrayList<>();
            List<String> second = new ArrayList<>();
            Consumer<String> c1 = FunctionalInterfacesChallenge.addTo(first);
            Consumer<String> c2 = FunctionalInterfacesChallenge.addTo(second);
            c1.accept("a");
            c2.accept("b");
            c1.accept("c");
            assertEquals(List.of("a", "c"), first);
            assertEquals(List.of("b"), second);
        }

        @Test
        void testEmptyTextIsAdded() {
            List<String> list = new ArrayList<>();
            FunctionalInterfacesChallenge.addTo(list).accept("");
            assertEquals(List.of(""), list);
        }

        @Test
        void testManyTexts() {
            List<String> list = new ArrayList<>();
            Consumer<String> c = FunctionalInterfacesChallenge.addTo(list);
            for (int i = 0; i < 100; i++) {
                c.accept("t" + i);
            }
            assertEquals(100, list.size());
            assertEquals("t99", list.get(99));
        }
    }

    // ==========================================================
    // CHALLENGE 2: both
    // ==========================================================
    @Nested
    class BothTests {

        @Test
        void testOrder() {
            List<String> log = new ArrayList<>();
            Consumer<String> first = t -> log.add("1:" + t);
            Consumer<String> second = t -> log.add("2:" + t);
            FunctionalInterfacesChallenge.both(first, second).accept("x");
            assertEquals(List.of("1:x", "2:x"), log);
        }

        @Test
        void testOrderIsNotSwapped() {
            List<String> log = new ArrayList<>();
            Consumer<String> a = t -> log.add("A");
            Consumer<String> b = t -> log.add("B");
            FunctionalInterfacesChallenge.both(b, a).accept("x");
            assertEquals(List.of("B", "A"), log);
        }

        @Test
        void testNothingIsCalledWhenCreated() {
            int[] calls = {0};
            Consumer<String> c = t -> calls[0]++;
            FunctionalInterfacesChallenge.both(c, c);
            assertEquals(0, calls[0]);
        }

        @Test
        void testEachConsumerIsCalledOncePerText() {
            int[] firstCalls = {0};
            int[] secondCalls = {0};
            Consumer<String> both = FunctionalInterfacesChallenge.both(t -> firstCalls[0]++, t -> secondCalls[0]++);
            both.accept("a");
            both.accept("b");
            both.accept("c");
            assertEquals(3, firstCalls[0]);
            assertEquals(3, secondCalls[0]);
        }

        @Test
        void testBothReceiveTheSameText() {
            List<String> seen = new ArrayList<>();
            FunctionalInterfacesChallenge.both(seen::add, seen::add).accept("same");
            assertEquals(List.of("same", "same"), seen);
        }

        @Test
        void testSeveralTexts() {
            List<String> log = new ArrayList<>();
            Consumer<String> both = FunctionalInterfacesChallenge.both(t -> log.add("1:" + t), t -> log.add("2:" + t));
            both.accept("a");
            both.accept("b");
            assertEquals(List.of("1:a", "2:a", "1:b", "2:b"), log);
        }

        @Test
        void testSecondSeesTheEffectOfTheFirst() {
            List<String> list = new ArrayList<>();
            int[] sizeSeenBySecond = {-1};
            FunctionalInterfacesChallenge.both(list::add, t -> sizeSeenBySecond[0] = list.size()).accept("x");
            assertEquals(1, sizeSeenBySecond[0]);
        }

        @Test
        void testSameConsumerTwice() {
            int[] calls = {0};
            Consumer<String> c = t -> calls[0]++;
            FunctionalInterfacesChallenge.both(c, c).accept("x");
            assertEquals(2, calls[0]);
        }
    }

    // ==========================================================
    // CHALLENGE 3: repeat
    // ==========================================================
    @Nested
    class RepeatTests {

        @Test
        void testThreeTimes() {
            assertEquals("ababab", FunctionalInterfacesChallenge.repeat(3).apply("ab"));
        }

        @Test
        void testOnce() {
            assertEquals("ab", FunctionalInterfacesChallenge.repeat(1).apply("ab"));
        }

        @Test
        void testZeroTimes() {
            assertEquals("", FunctionalInterfacesChallenge.repeat(0).apply("ab"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", FunctionalInterfacesChallenge.repeat(5).apply(""));
        }

        @Test
        void testSingleCharacter() {
            assertEquals("zzzzz", FunctionalInterfacesChallenge.repeat(5).apply("z"));
        }

        @Test
        void testNoSeparatorIsAdded() {
            assertEquals("a b a b ", FunctionalInterfacesChallenge.repeat(2).apply("a b "));
        }

        @Test
        void testTheSameOperatorCanBeUsedManyTimes() {
            UnaryOperator<String> twice = FunctionalInterfacesChallenge.repeat(2);
            assertEquals("xx", twice.apply("x"));
            assertEquals("yzyz", twice.apply("yz"));
            assertEquals("xx", twice.apply("x"));
        }

        @Test
        void testLengthOfTheResult() {
            assertEquals(60, FunctionalInterfacesChallenge.repeat(20).apply("abc").length());
        }

        @Test
        void testTwoOperatorsAreIndependent() {
            UnaryOperator<String> two = FunctionalInterfacesChallenge.repeat(2);
            UnaryOperator<String> three = FunctionalInterfacesChallenge.repeat(3);
            assertEquals("aa", two.apply("a"));
            assertEquals("aaa", three.apply("a"));
        }
    }

    // ==========================================================
    // CHALLENGE 4: longer
    // ==========================================================
    @Nested
    class LongerTests {

        @Test
        void testSecondIsLonger() {
            assertEquals("bb", FunctionalInterfacesChallenge.longer().apply("a", "bb"));
        }

        @Test
        void testFirstIsLonger() {
            assertEquals("bb", FunctionalInterfacesChallenge.longer().apply("bb", "a"));
        }

        @Test
        void testTieGivesTheFirst() {
            assertEquals("ab", FunctionalInterfacesChallenge.longer().apply("ab", "cd"));
            assertEquals("cd", FunctionalInterfacesChallenge.longer().apply("cd", "ab"));
        }

        @Test
        void testEmptyTexts() {
            assertEquals("x", FunctionalInterfacesChallenge.longer().apply("", "x"));
            assertEquals("x", FunctionalInterfacesChallenge.longer().apply("x", ""));
        }

        @Test
        void testBothEmpty() {
            assertEquals("", FunctionalInterfacesChallenge.longer().apply("", ""));
        }

        @Test
        void testTheSameObjectIsReturned() {
            String first = new String("aa");
            String second = new String("aa");
            assertSame(first, FunctionalInterfacesChallenge.longer().apply(first, second));
            assertSame(second, FunctionalInterfacesChallenge.longer().apply(second, first));
        }

        @Test
        void testAlphabeticalOrderDoesNotMatter() {
            assertEquals("zz", FunctionalInterfacesChallenge.longer().apply("zz", "aa"));
            assertEquals("aaa", FunctionalInterfacesChallenge.longer().apply("zz", "aaa"));
        }

        @Test
        void testWorksInAReduction() {
            BinaryOperator<String> op = FunctionalInterfacesChallenge.longer();
            String result = List.of("a", "bbb", "cc", "ddd").stream().reduce("", op);
            assertEquals("bbb", result);
        }

        @Test
        void testSpacesCount() {
            assertEquals("a  ", FunctionalInterfacesChallenge.longer().apply("ab", "a  "));
        }
    }

    // ==========================================================
    // CHALLENGE 5: lengthAbove
    // ==========================================================
    @Nested
    class LengthAboveTests {

        @Test
        void testLonger() {
            assertTrue(FunctionalInterfacesChallenge.lengthAbove().test("hello", 3));
        }

        @Test
        void testSameLength() {
            assertFalse(FunctionalInterfacesChallenge.lengthAbove().test("hello", 5));
        }

        @Test
        void testShorter() {
            assertFalse(FunctionalInterfacesChallenge.lengthAbove().test("hello", 9));
        }

        @Test
        void testEmptyTextAndNegativeNumber() {
            assertTrue(FunctionalInterfacesChallenge.lengthAbove().test("", -1));
        }

        @Test
        void testEmptyTextAndZero() {
            assertFalse(FunctionalInterfacesChallenge.lengthAbove().test("", 0));
        }

        @Test
        void testOneAbove() {
            assertTrue(FunctionalInterfacesChallenge.lengthAbove().test("hello", 4));
        }

        @Test
        void testSpacesCount() {
            assertTrue(FunctionalInterfacesChallenge.lengthAbove().test("   ", 2));
        }

        @Test
        void testTheArgumentsAreNotSwapped() {
            BiPredicate<String, Integer> p = FunctionalInterfacesChallenge.lengthAbove();
            assertTrue(p.test("abcdef", 2));
            assertFalse(p.test("ab", 6));
        }

        @Test
        void testCanBeNegated() {
            BiPredicate<String, Integer> p = FunctionalInterfacesChallenge.lengthAbove();
            assertTrue(p.negate().test("ab", 2));
        }
    }

    // ==========================================================
    // CHALLENGE 6: divisibleBy
    // ==========================================================
    @Nested
    class DivisibleByTests {

        @Test
        void testDivisible() {
            assertTrue(FunctionalInterfacesChallenge.divisibleBy(3).test(9));
        }

        @Test
        void testNotDivisible() {
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(3).test(10));
        }

        @Test
        void testNegativeNumber() {
            assertTrue(FunctionalInterfacesChallenge.divisibleBy(3).test(-6));
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(3).test(-7));
        }

        @Test
        void testZero() {
            assertTrue(FunctionalInterfacesChallenge.divisibleBy(3).test(0));
        }

        @Test
        void testNegativeDivisor() {
            assertTrue(FunctionalInterfacesChallenge.divisibleBy(-3).test(6));
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(-3).test(7));
        }

        @Test
        void testDivisorOne() {
            IntPredicate p = FunctionalInterfacesChallenge.divisibleBy(1);
            assertTrue(p.test(0));
            assertTrue(p.test(17));
            assertTrue(p.test(-17));
        }

        @Test
        void testEven() {
            IntPredicate even = FunctionalInterfacesChallenge.divisibleBy(2);
            assertTrue(even.test(10));
            assertFalse(even.test(11));
            assertFalse(even.test(-11));
            assertTrue(even.test(-10));
        }

        @Test
        void testNumberSmallerThanDivisor() {
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(10).test(3));
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(10).test(-3));
        }

        @Test
        void testDivisorItself() {
            assertTrue(FunctionalInterfacesChallenge.divisibleBy(7).test(7));
            assertTrue(FunctionalInterfacesChallenge.divisibleBy(7).test(-7));
        }

        @Test
        void testRemainderOne() {
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(5).test(6));
            assertFalse(FunctionalInterfacesChallenge.divisibleBy(5).test(4));
        }

        @Test
        void testUsedWithAStream() {
            assertEquals(3, java.util.stream.IntStream.rangeClosed(1, 10)
                    .filter(FunctionalInterfacesChallenge.divisibleBy(3)).count());
        }
    }

    // ==========================================================
    // CHALLENGE 7: vowelCount
    // ==========================================================
    @Nested
    class VowelCountTests {

        @Test
        void testBanana() {
            assertEquals(3, FunctionalInterfacesChallenge.vowelCount().applyAsInt("banana"));
        }

        @Test
        void testUppercaseVowels() {
            assertEquals(5, FunctionalInterfacesChallenge.vowelCount().applyAsInt("AEIOU"));
        }

        @Test
        void testNoVowels() {
            assertEquals(0, FunctionalInterfacesChallenge.vowelCount().applyAsInt("rhythm"));
        }

        @Test
        void testEmptyText() {
            assertEquals(0, FunctionalInterfacesChallenge.vowelCount().applyAsInt(""));
        }

        @Test
        void testAllVowelsInBothCases() {
            assertEquals(10, FunctionalInterfacesChallenge.vowelCount().applyAsInt("aeiouAEIOU"));
        }

        @Test
        void testRepeatedVowelsAreCountedEveryTime() {
            assertEquals(6, FunctionalInterfacesChallenge.vowelCount().applyAsInt("aaaeee"));
        }

        @Test
        void testYIsNotAVowel() {
            assertEquals(0, FunctionalInterfacesChallenge.vowelCount().applyAsInt("yY"));
        }

        @Test
        void testOtherCharacters() {
            assertEquals(2, FunctionalInterfacesChallenge.vowelCount().applyAsInt("1a 2e! x?"));
        }

        @Test
        void testSentence() {
            assertEquals(5, FunctionalInterfacesChallenge.vowelCount().applyAsInt("The quick brown fox"));
        }

        @Test
        void testEachVowelIndividually() {
            ToIntFunction<String> f = FunctionalInterfacesChallenge.vowelCount();
            assertEquals(1, f.applyAsInt("a"));
            assertEquals(1, f.applyAsInt("e"));
            assertEquals(1, f.applyAsInt("i"));
            assertEquals(1, f.applyAsInt("o"));
            assertEquals(1, f.applyAsInt("u"));
            assertEquals(1, f.applyAsInt("A"));
            assertEquals(1, f.applyAsInt("E"));
            assertEquals(1, f.applyAsInt("I"));
            assertEquals(1, f.applyAsInt("O"));
            assertEquals(1, f.applyAsInt("U"));
        }

        @Test
        void testConsonantsOnly() {
            assertEquals(0, FunctionalInterfacesChallenge.vowelCount().applyAsInt("bcdfghjklmnpqrstvwxzBCDFGH"));
        }
    }

    // ==========================================================
    // CHALLENGE 8: safeDivide
    // ==========================================================
    @Nested
    class SafeDivideTests {

        @Test
        void testNormalDivision() {
            assertEquals(3, FunctionalInterfacesChallenge.safeDivide().applyAsInt(7, 2));
        }

        @Test
        void testNegativeFirst() {
            assertEquals(-3, FunctionalInterfacesChallenge.safeDivide().applyAsInt(-7, 2));
        }

        @Test
        void testNegativeSecond() {
            assertEquals(-3, FunctionalInterfacesChallenge.safeDivide().applyAsInt(7, -2));
        }

        @Test
        void testBothNegative() {
            assertEquals(3, FunctionalInterfacesChallenge.safeDivide().applyAsInt(-7, -2));
        }

        @Test
        void testDivisionByZero() {
            assertEquals(0, FunctionalInterfacesChallenge.safeDivide().applyAsInt(5, 0));
        }

        @Test
        void testZeroDividedByZero() {
            assertEquals(0, FunctionalInterfacesChallenge.safeDivide().applyAsInt(0, 0));
        }

        @Test
        void testZeroDividedByNumber() {
            assertEquals(0, FunctionalInterfacesChallenge.safeDivide().applyAsInt(0, 5));
        }

        @Test
        void testExactDivision() {
            assertEquals(5, FunctionalInterfacesChallenge.safeDivide().applyAsInt(20, 4));
        }

        @Test
        void testTheOrderOfTheArgumentsMatters() {
            IntBinaryOperator op = FunctionalInterfacesChallenge.safeDivide();
            assertEquals(5, op.applyAsInt(10, 2));
            assertEquals(0, op.applyAsInt(2, 10));
        }

        @Test
        void testNegativeNumberDividedByZero() {
            assertEquals(0, FunctionalInterfacesChallenge.safeDivide().applyAsInt(-9, 0));
        }

        @Test
        void testSmallerThanDivisor() {
            assertEquals(0, FunctionalInterfacesChallenge.safeDivide().applyAsInt(1, 3));
            assertEquals(0, FunctionalInterfacesChallenge.safeDivide().applyAsInt(-1, 3));
        }

        @Test
        void testBigNumbers() {
            assertEquals(1_000_000_000, FunctionalInterfacesChallenge.safeDivide().applyAsInt(2_000_000_000, 2));
        }
    }

    // ==========================================================
    // CHALLENGE 9: clamp
    // ==========================================================
    @Nested
    class ClampTests {

        @Test
        void testInside() {
            assertEquals(5, FunctionalInterfacesChallenge.clamp().apply(5, 1, 10));
        }

        @Test
        void testBelow() {
            assertEquals(1, FunctionalInterfacesChallenge.clamp().apply(-3, 1, 10));
        }

        @Test
        void testAbove() {
            assertEquals(10, FunctionalInterfacesChallenge.clamp().apply(15, 1, 10));
        }

        @Test
        void testEqualToTheMaximum() {
            assertEquals(10, FunctionalInterfacesChallenge.clamp().apply(10, 1, 10));
        }

        @Test
        void testEqualToTheMinimum() {
            assertEquals(1, FunctionalInterfacesChallenge.clamp().apply(1, 1, 10));
        }

        @Test
        void testMinimumEqualsMaximum() {
            assertEquals(4, FunctionalInterfacesChallenge.clamp().apply(100, 4, 4));
            assertEquals(4, FunctionalInterfacesChallenge.clamp().apply(-100, 4, 4));
            assertEquals(4, FunctionalInterfacesChallenge.clamp().apply(4, 4, 4));
        }

        @Test
        void testNegativeRange() {
            assertEquals(-5, FunctionalInterfacesChallenge.clamp().apply(-9, -5, -1));
            assertEquals(-1, FunctionalInterfacesChallenge.clamp().apply(0, -5, -1));
            assertEquals(-3, FunctionalInterfacesChallenge.clamp().apply(-3, -5, -1));
        }

        @Test
        void testJustOutside() {
            assertEquals(1, FunctionalInterfacesChallenge.clamp().apply(0, 1, 10));
            assertEquals(10, FunctionalInterfacesChallenge.clamp().apply(11, 1, 10));
        }

        @Test
        void testTheArgumentsAreNotMixedUp() {
            FunctionalInterfacesChallenge.TriFunction<Integer, Integer, Integer, Integer> f =
                    FunctionalInterfacesChallenge.clamp();
            assertEquals(20, f.apply(50, 10, 20));
            assertEquals(10, f.apply(5, 10, 20));
            assertEquals(15, f.apply(15, 10, 20));
        }

        @Test
        void testBigValues() {
            assertEquals(Integer.MAX_VALUE, FunctionalInterfacesChallenge.clamp()
                    .apply(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE));
            assertEquals(0, FunctionalInterfacesChallenge.clamp().apply(Integer.MIN_VALUE, 0, Integer.MAX_VALUE));
        }
    }

    // ==========================================================
    // CHALLENGE 10: Transformer.then
    // ==========================================================
    @Nested
    class TransformerThenTests {

        @Test
        void testAddThenDouble() {
            FunctionalInterfacesChallenge.Transformer<Integer> addOne = x -> x + 1;
            FunctionalInterfacesChallenge.Transformer<Integer> twice = x -> x * 2;
            assertEquals(8, addOne.then(twice).transform(3));
        }

        @Test
        void testDoubleThenAdd() {
            FunctionalInterfacesChallenge.Transformer<Integer> addOne = x -> x + 1;
            FunctionalInterfacesChallenge.Transformer<Integer> twice = x -> x * 2;
            assertEquals(7, twice.then(addOne).transform(3));
        }

        @Test
        void testThreeTransformers() {
            FunctionalInterfacesChallenge.Transformer<Integer> minusOne = x -> x - 1;
            FunctionalInterfacesChallenge.Transformer<Integer> times5 = x -> x * 5;
            FunctionalInterfacesChallenge.Transformer<Integer> minusThree = x -> x - 3;
            assertEquals(17, minusOne.then(times5).then(minusThree).transform(5));
        }

        @Test
        void testWithTexts() {
            FunctionalInterfacesChallenge.Transformer<String> upper = String::toUpperCase;
            FunctionalInterfacesChallenge.Transformer<String> exclaim = s -> s + "!";
            assertEquals("HI!", upper.then(exclaim).transform("hi"));
            assertEquals("HI!", exclaim.then(upper).transform("hi"));
            FunctionalInterfacesChallenge.Transformer<String> firstLetterOnly = s -> s.substring(0, 1);
            assertEquals("H", upper.then(firstLetterOnly).transform("hi"));
            assertEquals("h", firstLetterOnly.then(exclaim).then(s -> s.substring(0, 1)).transform("hi"));
        }

        @Test
        void testNothingIsCalledWhenCreated() {
            int[] calls = {0};
            FunctionalInterfacesChallenge.Transformer<Integer> counting = x -> {
                calls[0]++;
                return x;
            };
            counting.then(counting);
            assertEquals(0, calls[0]);
        }

        @Test
        void testEachTransformerIsCalledOncePerTransform() {
            List<String> log = new ArrayList<>();
            FunctionalInterfacesChallenge.Transformer<Integer> first = x -> {
                log.add("first");
                return x + 1;
            };
            FunctionalInterfacesChallenge.Transformer<Integer> second = x -> {
                log.add("second");
                return x + 1;
            };
            assertEquals(12, first.then(second).transform(10));
            assertEquals(List.of("first", "second"), log);
        }

        @Test
        void testSecondReceivesTheResultOfTheFirst() {
            List<Integer> seen = new ArrayList<>();
            FunctionalInterfacesChallenge.Transformer<Integer> first = x -> x * 10;
            FunctionalInterfacesChallenge.Transformer<Integer> second = x -> {
                seen.add(x);
                return x;
            };
            first.then(second).transform(4);
            assertEquals(List.of(40), seen);
        }

        @Test
        void testOriginalTransformersDoNotChange() {
            FunctionalInterfacesChallenge.Transformer<Integer> addOne = x -> x + 1;
            FunctionalInterfacesChallenge.Transformer<Integer> twice = x -> x * 2;
            addOne.then(twice);
            assertEquals(4, addOne.transform(3));
            assertEquals(6, twice.transform(3));
        }

        @Test
        void testTheCombinedTransformerCanBeUsedManyTimes() {
            FunctionalInterfacesChallenge.Transformer<Integer> addOne = x -> x + 1;
            FunctionalInterfacesChallenge.Transformer<Integer> twice = x -> x * 2;
            FunctionalInterfacesChallenge.Transformer<Integer> both = addOne.then(twice);
            assertEquals(8, both.transform(3));
            assertEquals(0, both.transform(-1));
            assertEquals(8, both.transform(3));
        }

        @Test
        void testThenCanBeChainedWithItself() {
            FunctionalInterfacesChallenge.Transformer<Integer> addOne = x -> x + 1;
            assertEquals(13, addOne.then(addOne).then(addOne).transform(10));
        }

        @Test
        void testSubtractionShowsTheOrder() {
            FunctionalInterfacesChallenge.Transformer<Integer> fromTen = x -> 10 - x;
            FunctionalInterfacesChallenge.Transformer<Integer> square = x -> x * x;
            assertEquals(49, fromTen.then(square).transform(3));
            assertEquals(1, square.then(fromTen).transform(3));
        }
    }
}