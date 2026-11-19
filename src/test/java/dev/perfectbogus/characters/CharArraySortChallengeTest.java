package dev.perfectbogus.characters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class CharArraySortChallengeTest {

    private static char[] chars(String text) {
        return text.toCharArray();
    }

    private static char[] cycle(int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = (char) ('a' + i % 26);
        }
        return result;
    }

    // ==========================================================
    // CHALLENGE 1: sortChars
    // ==========================================================
    @Nested
    class SortCharsTests {

        @Test
        void testMixedCase() {
            assertArrayEquals(chars("Aab"), CharArraySortChallenge.sortChars(chars("bAa")));
        }

        @Test
        void testTwoChars() {
            assertArrayEquals(chars("yz"), CharArraySortChallenge.sortChars(chars("zy")));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(chars(""), CharArraySortChallenge.sortChars(chars("")));
        }

        @Test
        void testOneChar() {
            assertArrayEquals(chars("q"), CharArraySortChallenge.sortChars(chars("q")));
        }

        @Test
        void testAlreadySorted() {
            assertArrayEquals(chars("abcd"), CharArraySortChallenge.sortChars(chars("abcd")));
        }

        @Test
        void testReversed() {
            assertArrayEquals(chars("abcde"), CharArraySortChallenge.sortChars(chars("edcba")));
        }

        @Test
        void testUppercaseBeforeLowercase() {
            assertArrayEquals(chars("YZab"), CharArraySortChallenge.sortChars(chars("aZbY")));
        }

        @Test
        void testDuplicates() {
            assertArrayEquals(chars("aabbb"), CharArraySortChallenge.sortChars(chars("babab")));
        }

        @Test
        void testDigitsAndSymbols() {
            assertArrayEquals(chars("!1a"), CharArraySortChallenge.sortChars(chars("a1!")));
            assertArrayEquals(chars("019A"), CharArraySortChallenge.sortChars(chars("9A01")));
        }

        @Test
        void testAllTheSame() {
            assertArrayEquals(chars("zzzz"), CharArraySortChallenge.sortChars(chars("zzzz")));
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("bAa");
            CharArraySortChallenge.sortChars(original);
            assertArrayEquals(chars("bAa"), original);
        }

        @Test
        void testResultIsANewArray() {
            char[] original = chars("abc");
            assertTrue(CharArraySortChallenge.sortChars(original) != original);
        }

        @Test
        void testBigArray() {
            char[] result = CharArraySortChallenge.sortChars(cycle(260_000));
            assertEquals(260_000, result.length);
            assertEquals("a".repeat(10_000), new String(result, 0, 10_000));
            assertEquals("z".repeat(10_000), new String(result, 250_000, 10_000));
            assertEquals("m", new String(result, 120_000, 1));
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortIgnoringCase
    // ==========================================================
    @Nested
    class SortIgnoringCaseTests {

        @Test
        void testMixed() {
            assertArrayEquals(chars("AabB"), CharArraySortChallenge.sortIgnoringCase(chars("bAaB")));
        }

        @Test
        void testSameLetterKeepsTheOrder() {
            assertArrayEquals(chars("aA"), CharArraySortChallenge.sortIgnoringCase(chars("aA")));
            assertArrayEquals(chars("Aa"), CharArraySortChallenge.sortIgnoringCase(chars("Aa")));
        }

        @Test
        void testCaseDoesNotMatter() {
            assertArrayEquals(chars("aBc"), CharArraySortChallenge.sortIgnoringCase(chars("cBa")));
        }

        @Test
        void testUppercaseLetterNotAlwaysFirst() {
            assertArrayEquals(chars("aZ"), CharArraySortChallenge.sortIgnoringCase(chars("Za")));
            assertArrayEquals(chars("aZ"), CharArraySortChallenge.sortIgnoringCase(chars("aZ")));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(chars(""), CharArraySortChallenge.sortIgnoringCase(chars("")));
        }

        @Test
        void testOneLetter() {
            assertArrayEquals(chars("Q"), CharArraySortChallenge.sortIgnoringCase(chars("Q")));
        }

        @Test
        void testSeveralRepeatedLetters() {
            assertArrayEquals(chars("aAaAbB"), CharArraySortChallenge.sortIgnoringCase(chars("bBaAaA")));
            assertArrayEquals(chars("AaAaBb"), CharArraySortChallenge.sortIgnoringCase(chars("BAaAab")));
        }

        @Test
        void testAllUppercase() {
            assertArrayEquals(chars("ABC"), CharArraySortChallenge.sortIgnoringCase(chars("CAB")));
        }

        @Test
        void testAllLowercase() {
            assertArrayEquals(chars("abc"), CharArraySortChallenge.sortIgnoringCase(chars("cab")));
        }

        @Test
        void testFirstAndLastLetters() {
            assertArrayEquals(chars("AaZz"), CharArraySortChallenge.sortIgnoringCase(chars("ZzAa")));
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("bAaB");
            CharArraySortChallenge.sortIgnoringCase(original);
            assertArrayEquals(chars("bAaB"), original);
        }

        @Test
        void testBigArray() {
            char[] letters = new char[300_000];
            for (int i = 0; i < letters.length; i++) {
                letters[i] = i % 3 == 0 ? 'b' : i % 3 == 1 ? 'A' : 'a';
            }
            String expected = "Aa".repeat(100_000) + "b".repeat(100_000);
            assertEquals(expected, new String(CharArraySortChallenge.sortIgnoringCase(letters)));
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortDescending
    // ==========================================================
    @Nested
    class SortDescendingTests {

        @Test
        void testThreeLetters() {
            assertArrayEquals(chars("cba"), CharArraySortChallenge.sortDescending(chars("acb")));
        }

        @Test
        void testThreeLettersSimplified() {
            assertArrayEquals(chars("cba"), CharArraySortChallenge.sortDescendingSimplified(chars("acb")));
        }

        @Test
        void testLowercaseBeforeUppercase() {
            assertArrayEquals(chars("aA"), CharArraySortChallenge.sortDescending(chars("Aa")));
            assertArrayEquals(chars("aA"), CharArraySortChallenge.sortDescending(chars("aA")));
        }

        @Test
        void testRepeated() {
            assertArrayEquals(chars("xx"), CharArraySortChallenge.sortDescending(chars("xx")));
            assertArrayEquals(chars("bbaaa"), CharArraySortChallenge.sortDescending(chars("ababa")));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(chars(""), CharArraySortChallenge.sortDescending(chars("")));
        }

        @Test
        void testOneChar() {
            assertArrayEquals(chars("k"), CharArraySortChallenge.sortDescending(chars("k")));
        }

        @Test
        void testAlreadyDescending() {
            assertArrayEquals(chars("dcba"), CharArraySortChallenge.sortDescending(chars("dcba")));
        }

        @Test
        void testAscendingInput() {
            assertArrayEquals(chars("edcba"), CharArraySortChallenge.sortDescending(chars("abcde")));
        }

        @Test
        void testDigitsAndLetters() {
            assertArrayEquals(chars("ba91"), CharArraySortChallenge.sortDescending(chars("1a9b")));
        }

        @Test
        void testSymbols() {
            assertArrayEquals(chars("~z!"), CharArraySortChallenge.sortDescending(chars("!z~")));
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("acb");
            CharArraySortChallenge.sortDescending(original);
            assertArrayEquals(chars("acb"), original);
        }

        @Test
        void testBigArray() {
            char[] result = CharArraySortChallenge.sortDescending(cycle(260_000));
            assertEquals(260_000, result.length);
            assertEquals("z".repeat(10_000), new String(result, 0, 10_000));
            assertEquals("a".repeat(10_000), new String(result, 250_000, 10_000));
        }
    }

    // ==========================================================
    // CHALLENGE 4: vowelsFirst
    // ==========================================================
    @Nested
    class VowelsFirstTests {

        @Test
        void testFourLetters() {
            assertArrayEquals(chars("aebc"), CharArraySortChallenge.vowelsFirst(chars("bace")));
        }

        @Test
        void testThreeLetters() {
            assertArrayEquals(chars("auz"), CharArraySortChallenge.vowelsFirst(chars("zua")));
        }

        @Test
        void testOnlyConsonants() {
            assertArrayEquals(chars("cd"), CharArraySortChallenge.vowelsFirst(chars("dc")));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(chars(""), CharArraySortChallenge.vowelsFirst(chars("")));
        }

        @Test
        void testOnlyVowels() {
            assertArrayEquals(chars("aeiou"), CharArraySortChallenge.vowelsFirst(chars("uoiea")));
        }

        @Test
        void testEveryVowelGoesBeforeAConsonant() {
            assertArrayEquals(chars("eb"), CharArraySortChallenge.vowelsFirst(chars("be")));
            assertArrayEquals(chars("ih"), CharArraySortChallenge.vowelsFirst(chars("hi")));
            assertArrayEquals(chars("on"), CharArraySortChallenge.vowelsFirst(chars("no")));
            assertArrayEquals(chars("ut"), CharArraySortChallenge.vowelsFirst(chars("tu")));
        }

        @Test
        void testTheLetterAIsAVowel() {
            assertArrayEquals(chars("aeb"), CharArraySortChallenge.vowelsFirst(chars("bea")));
        }

        @Test
        void testYIsAConsonant() {
            assertArrayEquals(chars("ay"), CharArraySortChallenge.vowelsFirst(chars("ya")));
            assertArrayEquals(chars("by"), CharArraySortChallenge.vowelsFirst(chars("yb")));
        }

        @Test
        void testRepeatedLetters() {
            assertArrayEquals(chars("aaebb"), CharArraySortChallenge.vowelsFirst(chars("babea")));
        }

        @Test
        void testOneLetter() {
            assertArrayEquals(chars("x"), CharArraySortChallenge.vowelsFirst(chars("x")));
        }

        @Test
        void testWholeAlphabet() {
            assertArrayEquals(chars("aeioubcdfghjklmnpqrstvwxyz"),
                    CharArraySortChallenge.vowelsFirst(chars("zyxwvutsrqponmlkjihgfedcba")));
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("bace");
            CharArraySortChallenge.vowelsFirst(original);
            assertArrayEquals(chars("bace"), original);
        }

        @Test
        void testBigArray() {
            String expected = "a".repeat(10_000) + "e".repeat(10_000) + "i".repeat(10_000) + "o".repeat(10_000)
                    + "u".repeat(10_000);
            String consonants = "bcdfghjklmnpqrstvwxyz";
            StringBuilder all = new StringBuilder(expected);
            for (int i = 0; i < consonants.length(); i++) {
                all.append(String.valueOf(consonants.charAt(i)).repeat(10_000));
            }
            assertEquals(all.toString(), new String(CharArraySortChallenge.vowelsFirst(cycle(260_000))));
        }
    }

    // ==========================================================
    // CHALLENGE 5: sortByDistance
    // ==========================================================
    @Nested
    class SortByDistanceTests {

        @Test
        void testThreeLetters() {
            assertArrayEquals(chars("bda"), CharArraySortChallenge.sortByDistance(chars("adb"), 'c'));
        }

        @Test
        void testTieSmallerFirst() {
            assertArrayEquals(chars("xz"), CharArraySortChallenge.sortByDistance(chars("zx"), 'y'));
            assertArrayEquals(chars("xz"), CharArraySortChallenge.sortByDistance(chars("xz"), 'y'));
        }

        @Test
        void testTargetInTheArray() {
            assertArrayEquals(chars("ma"), CharArraySortChallenge.sortByDistance(chars("ma"), 'm'));
            assertArrayEquals(chars("ma"), CharArraySortChallenge.sortByDistance(chars("am"), 'm'));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(chars(""), CharArraySortChallenge.sortByDistance(chars(""), 'k'));
        }

        @Test
        void testOneLetter() {
            assertArrayEquals(chars("q"), CharArraySortChallenge.sortByDistance(chars("q"), 'a'));
        }

        @Test
        void testTargetSmallerThanEveryLetter() {
            assertArrayEquals(chars("bcd"), CharArraySortChallenge.sortByDistance(chars("dcb"), 'a'));
        }

        @Test
        void testTargetBiggerThanEveryLetter() {
            assertArrayEquals(chars("cba"), CharArraySortChallenge.sortByDistance(chars("abc"), 'z'));
        }

        @Test
        void testRepeatedLetters() {
            assertArrayEquals(chars("ccbbd"), CharArraySortChallenge.sortByDistance(chars("bdbcc"), 'c'));
            assertArrayEquals(chars("bbdd"), CharArraySortChallenge.sortByDistance(chars("dbdb"), 'c'));
        }

        @Test
        void testSeveralTies() {
            assertArrayEquals(chars("mlnkojp"), CharArraySortChallenge.sortByDistance(chars("pjokmnl"), 'm'));
        }

        @Test
        void testUppercaseAndLowercase() {
            assertArrayEquals(chars("zA"), CharArraySortChallenge.sortByDistance(chars("Az"), 'a'));
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("adb");
            CharArraySortChallenge.sortByDistance(original, 'c');
            assertArrayEquals(chars("adb"), original);
        }

        @Test
        void testBigArray() {
            String order = "mlnkojpiqhrgsfteudvcwbxayz";
            StringBuilder expected = new StringBuilder();
            for (int i = 0; i < order.length(); i++) {
                expected.append(String.valueOf(order.charAt(i)).repeat(10_000));
            }
            assertEquals(expected.toString(), new String(CharArraySortChallenge.sortByDistance(cycle(260_000), 'm')));
        }
    }

    // ==========================================================
    // CHALLENGE 6: lowerBeforeUpper
    // ==========================================================
    @Nested
    class LowerBeforeUpperTests {

        @Test
        void testMixed() {
            assertArrayEquals(chars("abAB"), CharArraySortChallenge.lowerBeforeUpper(chars("bAaB")));
        }

        @Test
        void testTwoLetters() {
            assertArrayEquals(chars("yZ"), CharArraySortChallenge.lowerBeforeUpper(chars("Zy")));
        }

        @Test
        void testOnlyUppercase() {
            assertArrayEquals(chars("BC"), CharArraySortChallenge.lowerBeforeUpper(chars("CB")));
        }

        @Test
        void testOnlyLowercase() {
            assertArrayEquals(chars("abc"), CharArraySortChallenge.lowerBeforeUpper(chars("cab")));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(chars(""), CharArraySortChallenge.lowerBeforeUpper(chars("")));
        }

        @Test
        void testOneLetter() {
            assertArrayEquals(chars("G"), CharArraySortChallenge.lowerBeforeUpper(chars("G")));
        }

        @Test
        void testSmallUppercaseAfterBigLowercase() {
            assertArrayEquals(chars("zA"), CharArraySortChallenge.lowerBeforeUpper(chars("Az")));
            assertArrayEquals(chars("zA"), CharArraySortChallenge.lowerBeforeUpper(chars("zA")));
        }

        @Test
        void testRepeatedLetters() {
            assertArrayEquals(chars("aabAAB"), CharArraySortChallenge.lowerBeforeUpper(chars("AbAaBa")));
        }

        @Test
        void testEachGroupIsAlphabetical() {
            assertArrayEquals(chars("cdeAB"), CharArraySortChallenge.lowerBeforeUpper(chars("dBecA")));
            assertArrayEquals(chars("bcdXYZ"), CharArraySortChallenge.lowerBeforeUpper(chars("ZcYbXd")));
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("bAaB");
            CharArraySortChallenge.lowerBeforeUpper(original);
            assertArrayEquals(chars("bAaB"), original);
        }

        @Test
        void testBigArray() {
            char[] letters = new char[300_000];
            for (int i = 0; i < letters.length; i++) {
                letters[i] = i % 3 == 0 ? 'B' : i % 3 == 1 ? 'a' : 'c';
            }
            String expected = "a".repeat(100_000) + "c".repeat(100_000) + "B".repeat(100_000);
            assertEquals(expected, new String(CharArraySortChallenge.lowerBeforeUpper(letters)));
        }
    }

    // ==========================================================
    // CHALLENGE 7: topLargest
    // ==========================================================
    @Nested
    class TopLargestTests {

        @Test
        void testTwoOfThree() {
            assertArrayEquals(chars("cb"), CharArraySortChallenge.topLargest(chars("acb"), 2));
        }

        @Test
        void testRepeatedCharsCountEachTime() {
            assertArrayEquals(chars("ba"), CharArraySortChallenge.topLargest(chars("aab"), 2));
            assertArrayEquals(chars("bb"), CharArraySortChallenge.topLargest(chars("bab"), 2));
        }

        @Test
        void testNone() {
            assertArrayEquals(chars(""), CharArraySortChallenge.topLargest(chars("ab"), 0));
        }

        @Test
        void testOne() {
            assertArrayEquals(chars("q"), CharArraySortChallenge.topLargest(chars("q"), 1));
            assertArrayEquals(chars("z"), CharArraySortChallenge.topLargest(chars("azb"), 1));
        }

        @Test
        void testAll() {
            assertArrayEquals(chars("cba"), CharArraySortChallenge.topLargest(chars("bca"), 3));
        }

        @Test
        void testEmptyArray() {
            assertArrayEquals(chars(""), CharArraySortChallenge.topLargest(chars(""), 0));
        }

        @Test
        void testLowercaseBeatsUppercase() {
            assertArrayEquals(chars("aZ"), CharArraySortChallenge.topLargest(chars("ZYa"), 2));
        }

        @Test
        void testTheLargestAreNotTheLastOnes() {
            assertArrayEquals(chars("zy"), CharArraySortChallenge.topLargest(chars("zyabc"), 2));
        }

        @Test
        void testTheLargestAreNotTheFirstOnes() {
            assertArrayEquals(chars("zy"), CharArraySortChallenge.topLargest(chars("abczy"), 2));
        }

        @Test
        void testResultSize() {
            assertEquals(3, CharArraySortChallenge.topLargest(chars("abcde"), 3).length);
            assertEquals(0, CharArraySortChallenge.topLargest(chars("abcde"), 0).length);
        }

        @Test
        void testOriginalIsNotModified() {
            char[] original = chars("acb");
            CharArraySortChallenge.topLargest(original, 2);
            assertArrayEquals(chars("acb"), original);
        }

        @Test
        void testBigArray() {
            assertArrayEquals(chars("zzz"), CharArraySortChallenge.topLargest(cycle(260_000), 3));
            assertEquals("z".repeat(10_000) + "y",
                    new String(CharArraySortChallenge.topLargest(cycle(260_000), 10_001)));
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortWordsByLastChar
    // ==========================================================
    @Nested
    class SortWordsByLastCharTests {

        @Test
        void testThreeWords() {
            assertArrayEquals(new String[] {"ca", "ab", "bb"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "ca", "bb"}));
        }

        @Test
        void testTieIsAlphabetical() {
            assertArrayEquals(new String[] {"ab", "bb"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"bb", "ab"}));
            assertArrayEquals(new String[] {"ab", "bb"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "bb"}));
        }

        @Test
        void testTheCaseMatters() {
            assertArrayEquals(new String[] {"aa", "Zb"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"Zb", "aa"}));
            assertArrayEquals(new String[] {"Ab", "ab"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "Ab"}));
            assertArrayEquals(new String[] {"aB", "ab"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "aB"}));
        }

        @Test
        void testLastCharIsComparedWithItsCase() {
            assertArrayEquals(new String[] {"zB", "ab"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "zB"}));
            assertArrayEquals(new String[] {"aC", "ab"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "aC"}));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new String[] {}, CharArraySortChallenge.sortWordsByLastChar(new String[] {}));
        }

        @Test
        void testOneWord() {
            assertArrayEquals(new String[] {"hello"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"hello"}));
        }

        @Test
        void testLastCharNotFirstChar() {
            assertArrayEquals(new String[] {"za", "ab"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "za"}));
        }

        @Test
        void testWordsWithOneChar() {
            assertArrayEquals(new String[] {"a", "b", "c"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"c", "a", "b"}));
        }

        @Test
        void testDifferentLengthsSameLastChar() {
            assertArrayEquals(new String[] {"ab", "bab", "zb"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"zb", "bab", "ab"}));
        }

        @Test
        void testRepeatedWords() {
            assertArrayEquals(new String[] {"ca", "ca", "ab"},
                    CharArraySortChallenge.sortWordsByLastChar(new String[] {"ab", "ca", "ca"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"ab", "ca", "bb"};
            CharArraySortChallenge.sortWordsByLastChar(words);
            assertArrayEquals(new String[] {"ab", "ca", "bb"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = String.valueOf(i);
            }
            String[] result = CharArraySortChallenge.sortWordsByLastChar(words);
            assertEquals("0", result[0]);
            assertEquals("10", result[1]);
            assertEquals("100", result[2]);
            assertEquals("1000", result[3]);
            assertEquals("10000", result[4]);
            assertEquals("99999", result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortByMiddleChar
    // ==========================================================
    @Nested
    class SortByMiddleCharTests {

        private String word(int i) {
            return (char) ('0' + i % 10) + "" + (char) ('0' + i / 10 % 10) + (char) ('a' + i % 26)
                    + (char) ('0' + i / 100 % 10) + (char) ('0' + i / 1000 % 10);
        }

        @Test
        void testThreeWords() {
            assertArrayEquals(new String[] {"xay", "abc", "zzb"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"abc", "xay", "zzb"}));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertArrayEquals(new String[] {"xbx", "aba"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"xbx", "aba"}));
            assertArrayEquals(new String[] {"aba", "xbx"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"aba", "xbx"}));
        }

        @Test
        void testWordsWithOneChar() {
            assertArrayEquals(new String[] {"a", "z"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"z", "a"}));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new String[] {}, CharArraySortChallenge.sortByMiddleChar(new String[] {}));
        }

        @Test
        void testMiddleOfFiveCharacters() {
            assertArrayEquals(new String[] {"zzazz", "aabaa", "yycyy"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"yycyy", "zzazz", "aabaa"}));
        }

        @Test
        void testFirstAndLastCharsDoNotMatter() {
            assertArrayEquals(new String[] {"zaz", "abb"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"abb", "zaz"}));
        }

        @Test
        void testTheCaseMatters() {
            assertArrayEquals(new String[] {"xBx", "xax"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"xax", "xBx"}));
        }

        @Test
        void testSeveralTies() {
            assertArrayEquals(new String[] {"1a1", "2a2", "3a3", "0b0"},
                    CharArraySortChallenge.sortByMiddleChar(new String[] {"0b0", "1a1", "2a2", "3a3"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"abc", "xay", "zzb"};
            CharArraySortChallenge.sortByMiddleChar(words);
            assertArrayEquals(new String[] {"abc", "xay", "zzb"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = word(i);
            }
            String[] result = CharArraySortChallenge.sortByMiddleChar(words);
            assertEquals(word(0), result[0]);
            assertEquals(word(26), result[1]);
            assertEquals(word(52), result[2]);
            assertEquals(word(99_995), result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 10: sortRows
    // ==========================================================
    @Nested
    class SortRowsTests {

        @Test
        void testThreeRows() {
            char[][] rows = {chars("b"), chars("az"), chars("a")};
            assertArrayEquals(new char[][] {chars("a"), chars("az"), chars("b")},
                    CharArraySortChallenge.sortRows(rows));
        }

        @Test
        void testEqualFirstCharAndLengthKeepTheOrder() {
            char[][] rows = {chars("ay"), chars("ax")};
            assertArrayEquals(new char[][] {chars("ay"), chars("ax")}, CharArraySortChallenge.sortRows(rows));
            char[][] other = {chars("ax"), chars("ay")};
            assertArrayEquals(new char[][] {chars("ax"), chars("ay")}, CharArraySortChallenge.sortRows(other));
        }

        @Test
        void testOnlyTheFirstCharIsUsedBeforeTheLength() {
            char[][] rows = {chars("caa"), chars("b")};
            assertArrayEquals(new char[][] {chars("b"), chars("caa")}, CharArraySortChallenge.sortRows(rows));
        }

        @Test
        void testEmpty() {
            assertArrayEquals(new char[][] {}, CharArraySortChallenge.sortRows(new char[][] {}));
        }

        @Test
        void testOneRow() {
            assertArrayEquals(new char[][] {chars("xyz")},
                    CharArraySortChallenge.sortRows(new char[][] {chars("xyz")}));
        }

        @Test
        void testShorterRowFirstWhenTheFirstCharIsTheSame() {
            char[][] rows = {chars("abc"), chars("a"), chars("ab")};
            assertArrayEquals(new char[][] {chars("a"), chars("ab"), chars("abc")},
                    CharArraySortChallenge.sortRows(rows));
        }

        @Test
        void testLaterCharsDoNotMatter() {
            char[][] rows = {chars("ab"), chars("aa")};
            assertArrayEquals(new char[][] {chars("ab"), chars("aa")}, CharArraySortChallenge.sortRows(rows));
        }

        @Test
        void testUppercaseFirstChar() {
            char[][] rows = {chars("a"), chars("B")};
            assertArrayEquals(new char[][] {chars("B"), chars("a")}, CharArraySortChallenge.sortRows(rows));
        }

        @Test
        void testFirstCharBeatsLength() {
            char[][] rows = {chars("bbbb"), chars("c"), chars("aaaaa")};
            assertArrayEquals(new char[][] {chars("aaaaa"), chars("bbbb"), chars("c")},
                    CharArraySortChallenge.sortRows(rows));
        }

        @Test
        void testResultKeepsTheSameRows() {
            char[] first = chars("ay");
            char[] second = chars("ax");
            char[][] result = CharArraySortChallenge.sortRows(new char[][] {first, second});
            assertTrue(result[0] == first);
            assertTrue(result[1] == second);
        }

        @Test
        void testOriginalIsNotModified() {
            char[][] rows = {chars("b"), chars("az"), chars("a")};
            CharArraySortChallenge.sortRows(rows);
            assertArrayEquals(new char[][] {chars("b"), chars("az"), chars("a")}, rows);
        }

        @Test
        void testRowsAreNotModified() {
            char[][] rows = {chars("cba"), chars("zyx")};
            CharArraySortChallenge.sortRows(rows);
            assertArrayEquals(chars("cba"), rows[0]);
            assertArrayEquals(chars("zyx"), rows[1]);
        }

        @Test
        void testBigArray() {
            char[][] rows = new char[100_000][];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = i / 26 % 2 == 0 ? new char[] {(char) ('a' + i % 26)}
                        : new char[] {(char) ('a' + i % 26), 'q'};
            }
            char[][] result = CharArraySortChallenge.sortRows(rows);
            assertArrayEquals(chars("a"), result[0]);
            assertArrayEquals(chars("zq"), result[99_999]);
            for (int i = 1; i < result.length; i++) {
                boolean ordered = result[i - 1][0] < result[i][0]
                        || result[i - 1][0] == result[i][0] && result[i - 1].length <= result[i].length;
                assertTrue(ordered);
            }
        }
    }
}