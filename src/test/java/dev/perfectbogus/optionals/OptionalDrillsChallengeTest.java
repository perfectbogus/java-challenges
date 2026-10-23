package dev.perfectbogus.optionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class OptionalDrillsChallengeTest {

    // ==========================================================
    // CHALLENGE 1: nonBlank
    // ==========================================================
    @Nested
    class NonBlankTests {

        @Test
        void testNormalText() {
            assertEquals(Optional.of("hello"), OptionalDrillsChallenge.nonBlank("hello"));
        }

        @Test
        void testTextWithSpacesIsKeptAsItIs() {
            assertEquals(Optional.of("  hi "), OptionalDrillsChallenge.nonBlank("  hi "));
        }

        @Test
        void testEmptyText() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.nonBlank(""));
        }

        @Test
        void testEmptyTextImproved() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.nonBlankImproved(""));
        }

        @Test
        void testOnlySpaces() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.nonBlank("   "));
        }

        @Test
        void testOneSpace() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.nonBlank(" "));
        }

        @Test
        void testNull() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.nonBlank(null));
        }

        @Test
        void testSingleCharacter() {
            assertEquals(Optional.of("x"), OptionalDrillsChallenge.nonBlank("x"));
        }

        @Test
        void testOnlyTabsAndNewLines() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.nonBlank("\t\n "));
        }
    }

    // ==========================================================
    // CHALLENGE 2: priceOf
    // ==========================================================
    @Nested
    class PriceOfTests {

        private Map<String, Integer> prices() {
            Map<String, Integer> prices = new HashMap<>();
            prices.put("pen", 2);
            prices.put("desk", 150);
            prices.put("free", 0);
            prices.put("ghost", null);
            return prices;
        }

        @Test
        void testExistingItem() {
            assertEquals(Optional.of(2), OptionalDrillsChallenge.priceOf(prices(), "pen"));
        }

        @Test
        void testAnotherItem() {
            assertEquals(Optional.of(150), OptionalDrillsChallenge.priceOf(prices(), "desk"));
        }

        @Test
        void testMissingItem() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.priceOf(prices(), "chair"));
        }

        @Test
        void testItemWithNullPrice() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.priceOf(prices(), "ghost"));
        }

        @Test
        void testZeroIsAPrice() {
            assertEquals(Optional.of(0), OptionalDrillsChallenge.priceOf(prices(), "free"));
        }

        @Test
        void testEmptyMap() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.priceOf(new HashMap<>(), "pen"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.priceOf(prices(), "PEN"));
        }

        @Test
        void testTheMapIsNotModified() {
            Map<String, Integer> prices = prices();
            OptionalDrillsChallenge.priceOf(prices, "chair");
            assertEquals(4, prices.size());
        }
    }

    // ==========================================================
    // CHALLENGE 3: valueOrLoad
    // ==========================================================
    @Nested
    class ValueOrLoadTests {

        @Test
        void testCachedValueIsReturned() {
            assertEquals("old", OptionalDrillsChallenge.valueOrLoad(Optional.of("old"), () -> "new"));
        }

        @Test
        void testLoaderIsNotCalledWhenThereIsAValue() {
            int[] calls = {0};
            OptionalDrillsChallenge.valueOrLoad(Optional.of("old"), () -> {
                calls[0]++;
                return "new";
            });
            assertEquals(0, calls[0]);
        }

        @Test
        void testLoaderIsUsedWhenEmpty() {
            assertEquals("new", OptionalDrillsChallenge.valueOrLoad(Optional.empty(), () -> "new"));
        }

        @Test
        void testLoaderIsCalledExactlyOnceWhenEmpty() {
            int[] calls = {0};
            String result = OptionalDrillsChallenge.valueOrLoad(Optional.empty(), () -> {
                calls[0]++;
                return "loaded";
            });
            assertEquals("loaded", result);
            assertEquals(1, calls[0]);
        }

        @Test
        void testEmptyTextInCacheIsAValue() {
            int[] calls = {0};
            String result = OptionalDrillsChallenge.valueOrLoad(Optional.of(""), () -> {
                calls[0]++;
                return "new";
            });
            assertEquals("", result);
            assertEquals(0, calls[0]);
        }

        @Test
        void testLoaderCanReturnAnEmptyText() {
            assertEquals("", OptionalDrillsChallenge.valueOrLoad(Optional.empty(), () -> ""));
        }

        @Test
        void testSeveralCalls() {
            int[] calls = {0};
            OptionalDrillsChallenge.valueOrLoad(Optional.empty(), () -> {
                calls[0]++;
                return "a";
            });
            OptionalDrillsChallenge.valueOrLoad(Optional.of("b"), () -> {
                calls[0]++;
                return "c";
            });
            OptionalDrillsChallenge.valueOrLoad(Optional.empty(), () -> {
                calls[0]++;
                return "d";
            });
            assertEquals(2, calls[0]);
        }
    }

    // ==========================================================
    // CHALLENGE 4: allPresent
    // ==========================================================
    @Nested
    class AllPresentTests {

        @Test
        void testAllWithValue() {
            assertTrue(OptionalDrillsChallenge.allPresent(List.of(Optional.of("a"), Optional.of("b"))));
        }

        @Test
        void testOneEmpty() {
            assertFalse(OptionalDrillsChallenge.allPresent(List.of(Optional.of("a"), Optional.empty())));
        }

        @Test
        void testEmptyList() {
            assertTrue(OptionalDrillsChallenge.allPresent(List.of()));
        }

        @Test
        void testOnlyEmpty() {
            assertFalse(OptionalDrillsChallenge.allPresent(List.of(Optional.empty())));
        }

        @Test
        void testEmptyAtTheStart() {
            List<Optional<String>> values = List.of(Optional.empty(), Optional.of("a"), Optional.of("b"));
            assertFalse(OptionalDrillsChallenge.allPresent(values));
        }

        @Test
        void testEmptyAtTheEnd() {
            List<Optional<String>> values = List.of(Optional.of("a"), Optional.of("b"), Optional.empty());
            assertFalse(OptionalDrillsChallenge.allPresent(values));
        }

        @Test
        void testEmptyTextIsAValue() {
            assertTrue(OptionalDrillsChallenge.allPresent(List.of(Optional.of(""), Optional.of(""))));
        }

        @Test
        void testSingleValue() {
            assertTrue(OptionalDrillsChallenge.allPresent(List.of(Optional.of("x"))));
        }
    }

    // ==========================================================
    // CHALLENGE 5: longestWord
    // ==========================================================
    @Nested
    class LongestWordTests {

        @Test
        void testLongestWord() {
            assertEquals(Optional.of("horse"),
                    OptionalDrillsChallenge.longestWord(List.of("cat", "horse", "dog", "mouse")));
        }

        @Test
        void testTieTheFirstOneWins() {
            assertEquals(Optional.of("horse"),
                    OptionalDrillsChallenge.longestWord(List.of("cat", "horse", "mouse")));
        }

        @Test
        void testTieOfThreeTheFirstOneWins() {
            assertEquals(Optional.of("bb"), OptionalDrillsChallenge.longestWord(List.of("a", "bb", "cc", "dd")));
        }

        @Test
        void testSingleWord() {
            assertEquals(Optional.of("a"), OptionalDrillsChallenge.longestWord(List.of("a")));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.longestWord(List.of()));
        }

        @Test
        void testLongestAtTheStart() {
            assertEquals(Optional.of("elephant"),
                    OptionalDrillsChallenge.longestWord(List.of("elephant", "ant", "bee")));
        }

        @Test
        void testLongestAtTheEnd() {
            assertEquals(Optional.of("elephant"),
                    OptionalDrillsChallenge.longestWord(List.of("ant", "bee", "elephant")));
        }

        @Test
        void testAllEmptyTexts() {
            assertEquals(Optional.of(""), OptionalDrillsChallenge.longestWord(List.of("", "")));
        }

        @Test
        void testListIsNotModified() {
            List<String> words = new ArrayList<>(List.of("b", "aaa", "cc"));
            OptionalDrillsChallenge.longestWord(words);
            assertEquals(List.of("b", "aaa", "cc"), words);
        }
    }

    // ==========================================================
    // CHALLENGE 6: greeting
    // ==========================================================
    @Nested
    class GreetingTests {

        @Test
        void testWithName() {
            assertEquals("Hello, Ana!", OptionalDrillsChallenge.greeting(Optional.of("Ana")));
        }

        @Test
        void testWithoutName() {
            assertEquals("Hello, stranger!", OptionalDrillsChallenge.greeting(Optional.empty()));
        }

        @Test
        void testEmptyTextIsAName() {
            assertEquals("Hello, !", OptionalDrillsChallenge.greeting(Optional.of("")));
        }

        @Test
        void testNameWithSpaces() {
            assertEquals("Hello, Ana Maria!", OptionalDrillsChallenge.greeting(Optional.of("Ana Maria")));
        }

        @Test
        void testTheCaseIsKept() {
            assertEquals("Hello, bOB!", OptionalDrillsChallenge.greeting(Optional.of("bOB")));
        }

        @Test
        void testTheNameStrangerIsNotChanged() {
            assertEquals("Hello, stranger!", OptionalDrillsChallenge.greeting(Optional.of("stranger")));
        }

        @Test
        void testSpacesAreKept() {
            assertEquals("Hello,  x !", OptionalDrillsChallenge.greeting(Optional.of(" x ")));
        }
    }

    // ==========================================================
    // CHALLENGE 7: addOptionals
    // ==========================================================
    @Nested
    class AddOptionalsTests {

        @Test
        void testBothPresent() {
            assertEquals(Optional.of(5), OptionalDrillsChallenge.addOptionals(Optional.of(2), Optional.of(3)));
        }

        @Test
        void testSecondEmpty() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.addOptionals(Optional.of(2), Optional.empty()));
        }

        @Test
        void testFirstEmpty() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.addOptionals(Optional.empty(), Optional.of(3)));
        }

        @Test
        void testBothEmpty() {
            assertEquals(Optional.empty(),
                    OptionalDrillsChallenge.addOptionals(Optional.empty(), Optional.empty()));
        }

        @Test
        void testZeroIsANumber() {
            assertEquals(Optional.of(0), OptionalDrillsChallenge.addOptionals(Optional.of(0), Optional.of(0)));
            assertEquals(Optional.of(7), OptionalDrillsChallenge.addOptionals(Optional.of(0), Optional.of(7)));
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(Optional.of(-1), OptionalDrillsChallenge.addOptionals(Optional.of(-4), Optional.of(3)));
            assertEquals(Optional.of(0), OptionalDrillsChallenge.addOptionals(Optional.of(-4), Optional.of(4)));
        }

        @Test
        void testBigNumbers() {
            assertEquals(Optional.of(2_000_000_000),
                    OptionalDrillsChallenge.addOptionals(Optional.of(1_000_000_000), Optional.of(1_000_000_000)));
        }

        @Test
        void testTheOrderDoesNotChangeTheSum() {
            assertEquals(OptionalDrillsChallenge.addOptionals(Optional.of(8), Optional.of(9)),
                    OptionalDrillsChallenge.addOptionals(Optional.of(9), Optional.of(8)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: firstNonNull
    // ==========================================================
    @Nested
    class FirstNonNullTests {

        @Test
        void testSkipsTheNulls() {
            assertEquals(Optional.of("b"), OptionalDrillsChallenge.firstNonNull(Arrays.asList(null, null, "b", "c")));
        }

        @Test
        void testFirstElementIsNotNull() {
            assertEquals(Optional.of("a"), OptionalDrillsChallenge.firstNonNull(Arrays.asList("a", null)));
        }

        @Test
        void testEmptyTextIsNotNull() {
            assertEquals(Optional.of(""), OptionalDrillsChallenge.firstNonNull(Arrays.asList(null, "")));
        }

        @Test
        void testAllNull() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.firstNonNull(Arrays.asList(null, null)));
        }

        @Test
        void testEmptyList() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.firstNonNull(new ArrayList<>()));
        }

        @Test
        void testNoNulls() {
            assertEquals(Optional.of("x"), OptionalDrillsChallenge.firstNonNull(List.of("x", "y", "z")));
        }

        @Test
        void testNullInTheMiddle() {
            assertEquals(Optional.of("a"), OptionalDrillsChallenge.firstNonNull(Arrays.asList("a", null, "c")));
        }

        @Test
        void testTheFirstNonNullIsNotTheSmallestOne() {
            assertEquals(Optional.of("z"), OptionalDrillsChallenge.firstNonNull(Arrays.asList("z", "a")));
            assertEquals(Optional.of("m"), OptionalDrillsChallenge.firstNonNull(Arrays.asList(null, "m", "b")));
        }

        @Test
        void testSingleNull() {
            assertEquals(Optional.empty(), OptionalDrillsChallenge.firstNonNull(Arrays.asList((String) null)));
        }

        @Test
        void testListIsNotModified() {
            List<String> values = new ArrayList<>(Arrays.asList(null, "a"));
            OptionalDrillsChallenge.firstNonNull(values);
            assertEquals(2, values.size());
            assertNull(values.get(0));
        }
    }

    // ==========================================================
    // CHALLENGE 9: sameValue
    // ==========================================================
    @Nested
    class SameValueTests {

        @Test
        void testEqualValues() {
            assertTrue(OptionalDrillsChallenge.sameValue(Optional.of("x"), Optional.of("x")));
        }

        @Test
        void testEqualValuesImproved() {
            assertTrue(OptionalDrillsChallenge.sameValueImproved(Optional.of("x"), Optional.of("x")));
        }

        @Test
        void testBothEmpty() {
            assertTrue(OptionalDrillsChallenge.sameValue(Optional.empty(), Optional.empty()));
        }

        @Test
        void testDifferentCase() {
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.of("x"), Optional.of("X")));
        }

        @Test
        void testFirstHasValueSecondEmpty() {
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.of("x"), Optional.empty()));
        }

        @Test
        void testFirstEmptySecondHasValue() {
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.empty(), Optional.of("x")));
        }

        @Test
        void testDifferentValues() {
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.of("a"), Optional.of("b")));
        }

        @Test
        void testEmptyTextIsNotEmptyOptional() {
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.of(""), Optional.empty()));
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.empty(), Optional.of("")));
        }

        @Test
        void testEqualTextsThatAreDifferentObjects() {
            String first = new String("same");
            String second = new String("same");
            assertTrue(OptionalDrillsChallenge.sameValue(Optional.of(first), Optional.of(second)));
        }

        @Test
        void testTwoEmptyTextsAreTheSame() {
            assertTrue(OptionalDrillsChallenge.sameValue(Optional.of(""), Optional.of("")));
        }

        @Test
        void testSpacesMatter() {
            assertFalse(OptionalDrillsChallenge.sameValue(Optional.of("x"), Optional.of("x ")));
        }
    }

    // ==========================================================
    // CHALLENGE 10: smallestEven
    // ==========================================================
    @Nested
    class SmallestEvenTests {

        @Test
        void testSmallestEven() {
            OptionalInt result = OptionalDrillsChallenge.smallestEven(new int[]{7, 4, 9, 2, 6});
            assertTrue(result.isPresent());
            assertEquals(2, result.getAsInt());
        }

        @Test
        void testNegativeEven() {
            OptionalInt result = OptionalDrillsChallenge.smallestEven(new int[]{-3, -4, 5});
            assertTrue(result.isPresent());
            assertEquals(-4, result.getAsInt());
        }

        @Test
        void testNoEvenNumber() {
            assertFalse(OptionalDrillsChallenge.smallestEven(new int[]{1, 3, 5}).isPresent());
        }

        @Test
        void testEmptyArray() {
            assertFalse(OptionalDrillsChallenge.smallestEven(new int[]{}).isPresent());
        }

        @Test
        void testZeroIsEven() {
            OptionalInt result = OptionalDrillsChallenge.smallestEven(new int[]{5, 0, 3});
            assertTrue(result.isPresent());
            assertEquals(0, result.getAsInt());
        }

        @Test
        void testSmallerOddNumbersAreIgnored() {
            OptionalInt result = OptionalDrillsChallenge.smallestEven(new int[]{-7, -5, 8, 10});
            assertTrue(result.isPresent());
            assertEquals(8, result.getAsInt());
        }

        @Test
        void testSingleEvenNumber() {
            assertEquals(OptionalInt.of(6), OptionalDrillsChallenge.smallestEven(new int[]{6}));
        }

        @Test
        void testSingleOddNumber() {
            assertEquals(OptionalInt.empty(), OptionalDrillsChallenge.smallestEven(new int[]{7}));
        }

        @Test
        void testRepeatedEvenNumbers() {
            assertEquals(OptionalInt.of(4), OptionalDrillsChallenge.smallestEven(new int[]{4, 4, 8}));
        }

        @Test
        void testArrayIsNotModified() {
            int[] numbers = {9, 4, 2};
            OptionalDrillsChallenge.smallestEven(numbers);
            assertArrayEquals(new int[]{9, 4, 2}, numbers);
        }
    }
}