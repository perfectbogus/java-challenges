package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringSortMixChallengeTest {

    // ==========================================================
    // CHALLENGE 1: sortWordsOfSentence
    // ==========================================================
    @Nested
    class SortWordsOfSentenceTests {

        @Test
        void testFourWords() {
            assertEquals("fox the brown quick", StringSortMixChallenge.sortWordsOfSentence("the quick brown fox"));
        }

        @Test
        void testTwoWords() {
            assertEquals("a b", StringSortMixChallenge.sortWordsOfSentence("b a"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals("B a b", StringSortMixChallenge.sortWordsOfSentence("b B a"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringSortMixChallenge.sortWordsOfSentence(""));
        }

        @Test
        void testOneWord() {
            assertEquals("hello", StringSortMixChallenge.sortWordsOfSentence("hello"));
        }

        @Test
        void testLengthIsTheMainCriterion() {
            assertEquals("b aaa", StringSortMixChallenge.sortWordsOfSentence("aaa b"));
            assertEquals("a bb ccc", StringSortMixChallenge.sortWordsOfSentence("ccc bb a"));
        }

        @Test
        void testAlreadySorted() {
            assertEquals("a bb ccc", StringSortMixChallenge.sortWordsOfSentence("a bb ccc"));
        }

        @Test
        void testRepeatedWordsAreKept() {
            assertEquals("a go go", StringSortMixChallenge.sortWordsOfSentence("go go a"));
        }

        @Test
        void testSeveralWordsOfTheSameLength() {
            assertEquals("be be or to to not", StringSortMixChallenge.sortWordsOfSentence("to be or not to be"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals("a1 b2", StringSortMixChallenge.sortWordsOfSentence("b2 a1"));
            assertEquals("! a", StringSortMixChallenge.sortWordsOfSentence("a !"));
        }

        @Test
        void testUppercaseWordsBeforeLowercaseOnes() {
            assertEquals("Zed apple", StringSortMixChallenge.sortWordsOfSentence("Zed apple"));
            assertEquals("Zz aa", StringSortMixChallenge.sortWordsOfSentence("aa Zz"));
        }

        @Test
        void testNumbersAreNotCompared() {
            assertEquals("9 10", StringSortMixChallenge.sortWordsOfSentence("10 9"));
        }

        @Test
        void testLongWordsGoLast() {
            assertEquals("i am the best word", StringSortMixChallenge.sortWordsOfSentence("word best the am i"));
        }

        @Test
        void testBigSentence() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "x" + (i % 10);
            }
            String result = StringSortMixChallenge.sortWordsOfSentence(String.join(" ", words));
            assertTrue(result.startsWith("x0 x0 x0"));
            assertTrue(result.endsWith("x9 x9 x9"));
            assertEquals(299_999, result.length());
        }
    }

    // ==========================================================
    // CHALLENGE 2: sortedByVowelCount
    // ==========================================================
    @Nested
    class SortedByVowelCountTests {

        @Test
        void testFourWords() {
            assertArrayEquals(new String[] {"audio", "banana", "tree", "sky"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"sky", "banana", "tree", "audio"}));
        }

        @Test
        void testFourWordsExtended() {
            assertArrayEquals(new String[] {"audio", "banana", "tree", "sky"},
                    StringSortMixChallenge.sortedByVowelsCountExtended(new String[] {"sky", "banana", "tree", "audio"}));
        }

        @Test
        void testFourWordsPerformance() {
            assertArrayEquals(new String[] {"audio", "banana", "tree", "sky"},
                    StringSortMixChallenge.sortedByVowelsCountPerformance(new String[] {"sky", "banana", "tree", "audio"}));
        }

        @Test
        void testTiesAreAlphabetical() {
            assertArrayEquals(new String[] {"ab", "ba"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"ba", "ab"}));
            assertArrayEquals(new String[] {"ab", "ba"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"ab", "ba"}));
        }

        @Test
        void testNumberOfVowelsNotTheirRatio() {
            assertArrayEquals(new String[] {"aab", "ea"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"ea", "aab"}));
        }

        @Test
        void testNoWords() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.sortedByVowelCount(new String[] {}));
        }

        @Test
        void testEveryVowelCounts() {
            assertArrayEquals(new String[] {"a", "b"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"b", "a"}));
            assertArrayEquals(new String[] {"e", "b"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"b", "e"}));
            assertArrayEquals(new String[] {"i", "b"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"b", "i"}));
            assertArrayEquals(new String[] {"o", "b"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"b", "o"}));
            assertArrayEquals(new String[] {"u", "b"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"b", "u"}));
        }

        @Test
        void testYIsNotAVowel() {
            assertArrayEquals(new String[] {"a", "yy"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"yy", "a"}));
        }

        @Test
        void testRepeatedVowelsCountEachTime() {
            assertArrayEquals(new String[] {"aa", "bab"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"bab", "aa"}));
        }

        @Test
        void testEmptyWord() {
            assertArrayEquals(new String[] {"a", ""},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"", "a"}));
        }

        @Test
        void testRepeatedWords() {
            assertArrayEquals(new String[] {"a", "a"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"a", "a"}));
        }

        @Test
        void testOneWord() {
            assertArrayEquals(new String[] {"hello"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"hello"}));
        }

        @Test
        void testWordsWithoutVowelsAreAlphabetical() {
            assertArrayEquals(new String[] {"brr", "sky", "zzz"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"zzz", "sky", "brr"}));
        }

        @Test
        void testThreeLevels() {
            assertArrayEquals(new String[] {"aei", "ab", "ba", "b"},
                    StringSortMixChallenge.sortedByVowelCount(new String[] {"b", "ba", "aei", "ab"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"sky", "banana", "tree", "audio"};
            StringSortMixChallenge.sortedByVowelCount(words);
            assertArrayEquals(new String[] {"sky", "banana", "tree", "audio"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = "a".repeat(i % 4) + "z";
            }
            String[] result = StringSortMixChallenge.sortedByVowelCount(words);
            assertEquals("aaaz", result[0]);
            assertEquals("aaaz", result[24_999]);
            assertEquals("aaz", result[25_000]);
            assertEquals("z", result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 3: sortCharactersByFrequency
    // ==========================================================
    @Nested
    class SortCharactersByFrequencyTests {

        @Test
        void testTree() {
            assertEquals("eert", StringSortMixChallenge.sortCharactersByFrequency("tree"));
        }

        @Test
        void testTreeStream() {
            assertEquals("eert", StringSortMixChallenge.sortCharactersByFrequencyStream("tree"));
        }

        @Test
        void testBanana() {
            assertEquals("aaannb", StringSortMixChallenge.sortCharactersByFrequency("banana"));
        }

        @Test
        void testTieIsAlphabetical() {
            assertEquals("aaazzz", StringSortMixChallenge.sortCharactersByFrequency("zzzaaa"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringSortMixChallenge.sortCharactersByFrequency(""));
        }

        @Test
        void testAllDifferent() {
            assertEquals("abc", StringSortMixChallenge.sortCharactersByFrequency("cba"));
        }

        @Test
        void testAllTheSame() {
            assertEquals("aaaa", StringSortMixChallenge.sortCharactersByFrequency("aaaa"));
        }

        @Test
        void testOneCharacter() {
            assertEquals("x", StringSortMixChallenge.sortCharactersByFrequency("x"));
        }

        @Test
        void testThreeLevels() {
            assertEquals("bbbaac", StringSortMixChallenge.sortCharactersByFrequency("aabbbc"));
        }

        @Test
        void testMississippi() {
            assertEquals("iiiissssppm", StringSortMixChallenge.sortCharactersByFrequency("mississippi"));
        }

        @Test
        void testThreeWayTie() {
            assertEquals("aabbcc", StringSortMixChallenge.sortCharactersByFrequency("cabbca"));
        }

        @Test
        void testCopiesAreTogether() {
            assertEquals("aabb", StringSortMixChallenge.sortCharactersByFrequency("abab"));
            assertEquals("yyzzx", StringSortMixChallenge.sortCharactersByFrequency("xyzzy"));
        }

        @Test
        void testFrequencyBeatsAlphabeticalOrder() {
            assertEquals("zzay", StringSortMixChallenge.sortCharactersByFrequency("yazz"));
        }

        @Test
        void testFirstAndLastLetters() {
            assertEquals("aazzy", StringSortMixChallenge.sortCharactersByFrequency("yzaza"));
        }

        @Test
        void testBigText() {
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 300_000; i++) {
                text.append(i % 10 < 5 ? 'a' : i % 10 < 8 ? 'b' : 'c');
            }
            assertEquals("a".repeat(150_000) + "b".repeat(90_000) + "c".repeat(60_000),
                    StringSortMixChallenge.sortCharactersByFrequency(text.toString()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: sortByCustomOrder
    // ==========================================================
    @Nested
    class SortByCustomOrderTests {

        @Test
        void testHello() {
            assertEquals("llohe", StringSortMixChallenge.sortByCustomOrder("hello", "lohe"));
        }

        @Test
        void testHelloSimplified() {
            assertEquals("llohe", StringSortMixChallenge.sortByCustomOrderSimplified("hello", "lohe"));
        }

        @Test
        void testReversedAlphabet() {
            assertEquals("cba", StringSortMixChallenge.sortByCustomOrder("abc", "cba"));
        }

        @Test
        void testRepeatedLetters() {
            assertEquals("baa", StringSortMixChallenge.sortByCustomOrder("aab", "ba"));
            assertEquals("bbaa", StringSortMixChallenge.sortByCustomOrder("abab", "ba"));
        }

        @Test
        void testEmptyText() {
            assertEquals("", StringSortMixChallenge.sortByCustomOrder("", "x"));
        }

        @Test
        void testNormalAlphabet() {
            assertEquals("abc", StringSortMixChallenge.sortByCustomOrder("abc", "abc"));
            assertEquals("xyz", StringSortMixChallenge.sortByCustomOrder("zyx", "xyz"));
        }

        @Test
        void testOrderLongerThanTheText() {
            assertEquals("ac", StringSortMixChallenge.sortByCustomOrder("ca", "zyxabc"));
        }

        @Test
        void testTheCaseMatters() {
            assertEquals("Aa", StringSortMixChallenge.sortByCustomOrder("aA", "Aa"));
            assertEquals("aA", StringSortMixChallenge.sortByCustomOrder("Aa", "aA"));
        }

        @Test
        void testAllTheSame() {
            assertEquals("zzz", StringSortMixChallenge.sortByCustomOrder("zzz", "z"));
        }

        @Test
        void testOneCharacter() {
            assertEquals("q", StringSortMixChallenge.sortByCustomOrder("q", "pq"));
        }

        @Test
        void testOrderIsNotAlphabetical() {
            assertEquals("zay", StringSortMixChallenge.sortByCustomOrder("yaz", "zay"));
        }

        @Test
        void testOrderIsNotTheFrequency() {
            assertEquals("bcaaa", StringSortMixChallenge.sortByCustomOrder("aaabc", "bca"));
        }

        @Test
        void testDigitsAndSymbols() {
            assertEquals("9!1", StringSortMixChallenge.sortByCustomOrder("1!9", "9!1"));
        }

        @Test
        void testSpaces() {
            assertEquals("  ab", StringSortMixChallenge.sortByCustomOrder("a b ", " ab"));
        }

        @Test
        void testBigText() {
            String text = "abcd".repeat(75_000);
            assertEquals("d".repeat(75_000) + "c".repeat(75_000) + "b".repeat(75_000) + "a".repeat(75_000),
                    StringSortMixChallenge.sortByCustomOrder(text, "dcba"));
        }
    }

    // ==========================================================
    // CHALLENGE 5: topLongestWords
    // ==========================================================
    @Nested
    class TopLongestWordsTests {

        @Test
        void testTwoOfFour() {
            assertArrayEquals(new String[] {"aaa", "ccc"},
                    StringSortMixChallenge.topLongestWords(new String[] {"a", "ccc", "bb", "aaa"}, 2));
        }

        @Test
        void testTwoOfFourSimplified() {
            assertArrayEquals(new String[] {"aaa", "ccc"},
                    StringSortMixChallenge.topLongestWordsSimplified(new String[] {"a", "ccc", "bb", "aaa"}, 2));
        }

        @Test
        void testAllTheWords() {
            assertArrayEquals(new String[] {"bb", "a"},
                    StringSortMixChallenge.topLongestWords(new String[] {"a", "bb"}, 2));
        }

        @Test
        void testTiesAreCutAlphabetically() {
            assertArrayEquals(new String[] {"aaa", "bbb"},
                    StringSortMixChallenge.topLongestWords(new String[] {"ccc", "aaa", "bbb"}, 2));
        }

        @Test
        void testNone() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.topLongestWords(new String[] {"a", "bb"}, 0));
        }

        @Test
        void testNoWords() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.topLongestWords(new String[] {}, 0));
        }

        @Test
        void testOneWord() {
            assertArrayEquals(new String[] {"x"}, StringSortMixChallenge.topLongestWords(new String[] {"x"}, 1));
        }

        @Test
        void testLengthIsTheMainCriterion() {
            assertArrayEquals(new String[] {"zzzz"},
                    StringSortMixChallenge.topLongestWords(new String[] {"zzzz", "a"}, 1));
        }

        @Test
        void testTheCaseMatters() {
            assertArrayEquals(new String[] {"B", "b"},
                    StringSortMixChallenge.topLongestWords(new String[] {"b", "B"}, 2));
        }

        @Test
        void testRepeatedWords() {
            assertArrayEquals(new String[] {"aa", "aa"},
                    StringSortMixChallenge.topLongestWords(new String[] {"aa", "aa", "b"}, 2));
        }

        @Test
        void testEmptyWord() {
            assertArrayEquals(new String[] {"a", ""},
                    StringSortMixChallenge.topLongestWords(new String[] {"", "a"}, 2));
        }

        @Test
        void testOnlyTheLongestOne() {
            assertArrayEquals(new String[] {"longest"},
                    StringSortMixChallenge.topLongestWords(new String[] {"a", "bb", "longest", "ccc"}, 1));
        }

        @Test
        void testThreeLevels() {
            assertArrayEquals(new String[] {"ccc", "bb", "a"},
                    StringSortMixChallenge.topLongestWords(new String[] {"a", "bb", "ccc"}, 3));
        }

        @Test
        void testResultSizeIsN() {
            assertEquals(3, StringSortMixChallenge.topLongestWords(new String[] {"a", "b", "c", "d", "e"}, 3).length);
        }

        @Test
        void testOriginalIsNotModified() {
            String[] words = {"a", "ccc", "bb", "aaa"};
            StringSortMixChallenge.topLongestWords(words, 2);
            assertArrayEquals(new String[] {"a", "ccc", "bb", "aaa"}, words);
        }

        @Test
        void testBigArray() {
            String[] words = new String[100_000];
            for (int i = 0; i < words.length; i++) {
                words[i] = String.valueOf(i);
            }
            assertArrayEquals(new String[] {"10000", "10001", "10002"},
                    StringSortMixChallenge.topLongestWords(words, 3));
        }
    }

    // ==========================================================
    // CHALLENGE 6: sortFilesByExtension
    // ==========================================================
    @Nested
    class SortFilesByExtensionTests {

        @Test
        void testFourFiles() {
            assertArrayEquals(new String[] {"c.doc", "a.txt", "b.txt", "a.zip"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"b.txt", "a.zip", "c.doc", "a.txt"}));
        }

        @Test
        void testNameIsComparedWithoutTheExtension() {
            assertArrayEquals(new String[] {"a.x", "a-b.x"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"a-b.x", "a.x"}));
        }

        @Test
        void testTheCaseMatters() {
            assertArrayEquals(new String[] {"a.TXT", "a.txt"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"a.txt", "a.TXT"}));
        }

        @Test
        void testNoFiles() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.sortFilesByExtension(new String[] {}));
        }

        @Test
        void testOneFile() {
            assertArrayEquals(new String[] {"a.b"}, StringSortMixChallenge.sortFilesByExtension(new String[] {"a.b"}));
        }

        @Test
        void testExtensionIsTheMainCriterion() {
            assertArrayEquals(new String[] {"z.a", "a.b"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"a.b", "z.a"}));
        }

        @Test
        void testSameExtensionOrderedByName() {
            assertArrayEquals(new String[] {"a.x", "b.x"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"b.x", "a.x"}));
        }

        @Test
        void testRepeatedFiles() {
            assertArrayEquals(new String[] {"a.x", "a.x"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"a.x", "a.x"}));
        }

        @Test
        void testShorterExtensionGoesFirst() {
            assertArrayEquals(new String[] {"a.a", "a.ab"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"a.ab", "a.a"}));
        }

        @Test
        void testShorterNameGoesFirst() {
            assertArrayEquals(new String[] {"a.x", "ab.x"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"ab.x", "a.x"}));
        }

        @Test
        void testExtensionWithDigits() {
            assertArrayEquals(new String[] {"b.mp3", "a.mp4"},
                    StringSortMixChallenge.sortFilesByExtension(new String[] {"a.mp4", "b.mp3"}));
        }

        @Test
        void testManyExtensions() {
            assertArrayEquals(new String[] {"x.doc", "y.doc", "a.png", "b.png", "c.txt"},
                    StringSortMixChallenge.sortFilesByExtension(
                            new String[] {"c.txt", "b.png", "y.doc", "a.png", "x.doc"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] files = {"b.txt", "a.zip", "c.doc", "a.txt"};
            StringSortMixChallenge.sortFilesByExtension(files);
            assertArrayEquals(new String[] {"b.txt", "a.zip", "c.doc", "a.txt"}, files);
        }

        @Test
        void testBigArray() {
            String[] files = new String[100_000];
            for (int i = 0; i < files.length; i++) {
                files[i] = "f" + i + ".e" + (i % 10);
            }
            String[] result = StringSortMixChallenge.sortFilesByExtension(files);
            assertEquals("f0.e0", result[0]);
            assertEquals("f10.e0", result[1]);
            assertEquals("f99999.e9", result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 7: sortVersions
    // ==========================================================
    @Nested
    class SortVersionsTests {

        @Test
        void testThreeVersions() {
            assertArrayEquals(new String[] {"0.9.9", "1.2.0", "1.10.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"1.10.0", "1.2.0", "0.9.9"}));
        }

        @Test
        void testThirdNumberIsNumeric() {
            assertArrayEquals(new String[] {"1.0.9", "1.0.10"},
                    StringSortMixChallenge.sortVersions(new String[] {"1.0.10", "1.0.9"}));
        }

        @Test
        void testFirstNumberIsNumeric() {
            assertArrayEquals(new String[] {"9.0.0", "10.0.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"10.0.0", "9.0.0"}));
        }

        @Test
        void testNoVersions() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.sortVersions(new String[] {}));
        }

        @Test
        void testOneVersion() {
            assertArrayEquals(new String[] {"1.2.3"}, StringSortMixChallenge.sortVersions(new String[] {"1.2.3"}));
        }

        @Test
        void testRepeatedVersions() {
            assertArrayEquals(new String[] {"1.0.0", "1.0.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"1.0.0", "1.0.0"}));
        }

        @Test
        void testZeros() {
            assertArrayEquals(new String[] {"0.0.0", "0.0.1"},
                    StringSortMixChallenge.sortVersions(new String[] {"0.0.1", "0.0.0"}));
        }

        @Test
        void testSecondNumberDecidesBeforeTheThird() {
            assertArrayEquals(new String[] {"1.2.9", "1.3.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"1.3.0", "1.2.9"}));
        }

        @Test
        void testFirstNumberDecidesBeforeTheSecond() {
            assertArrayEquals(new String[] {"1.99.99", "2.0.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"2.0.0", "1.99.99"}));
        }

        @Test
        void testBiggestNumbers() {
            assertArrayEquals(new String[] {"999998.999999.999999", "999999.0.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"999999.0.0", "999998.999999.999999"}));
        }

        @Test
        void testSecondNumberIsNumeric() {
            assertArrayEquals(new String[] {"1.9.0", "1.10.0", "1.100.0"},
                    StringSortMixChallenge.sortVersions(new String[] {"1.100.0", "1.9.0", "1.10.0"}));
        }

        @Test
        void testManyVersions() {
            assertArrayEquals(new String[] {"0.1.0", "0.1.1", "0.2.0", "1.0.0", "1.0.1"},
                    StringSortMixChallenge.sortVersions(new String[] {"1.0.1", "0.2.0", "1.0.0", "0.1.1", "0.1.0"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] versions = {"1.10.0", "1.2.0", "0.9.9"};
            StringSortMixChallenge.sortVersions(versions);
            assertArrayEquals(new String[] {"1.10.0", "1.2.0", "0.9.9"}, versions);
        }

        @Test
        void testBigArray() {
            String[] versions = new String[100_000];
            for (int i = 0; i < versions.length; i++) {
                versions[i] = (99_999 - i) / 1000 + "." + (99_999 - i) % 1000 + ".0";
            }
            String[] result = StringSortMixChallenge.sortVersions(versions);
            assertEquals("0.0.0", result[0]);
            assertEquals("0.1.0", result[1]);
            assertEquals("99.999.0", result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 8: sortByLastWord
    // ==========================================================
    @Nested
    class SortByLastWordTests {

        @Test
        void testThreeSentences() {
            assertArrayEquals(new String[] {"say Apple", "go home", "a b Zoo"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"go home", "say Apple", "a b Zoo"}));
        }

        @Test
        void testTiesKeepTheOrder() {
            assertArrayEquals(new String[] {"x b", "y B"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"x b", "y B"}));
            assertArrayEquals(new String[] {"y B", "x b"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"y B", "x b"}));
        }

        @Test
        void testShorterLastWordGoesFirst() {
            assertArrayEquals(new String[] {"y a", "x ab"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"x ab", "y a"}));
        }

        @Test
        void testNoSentences() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.sortByLastWord(new String[] {}));
        }

        @Test
        void testSentencesWithOneWord() {
            assertArrayEquals(new String[] {"a", "b"}, StringSortMixChallenge.sortByLastWord(new String[] {"b", "a"}));
            assertArrayEquals(new String[] {"single"}, StringSortMixChallenge.sortByLastWord(new String[] {"single"}));
        }

        @Test
        void testTheLastWordNotTheFirstOne() {
            assertArrayEquals(new String[] {"z a", "a z"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"a z", "z a"}));
        }

        @Test
        void testTheCaseIsIgnored() {
            assertArrayEquals(new String[] {"a apple", "a Zoo"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"a Zoo", "a apple"}));
            assertArrayEquals(new String[] {"a Apple", "a zoo"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"a zoo", "a Apple"}));
        }

        @Test
        void testMiddleWordsDoNotMatter() {
            assertArrayEquals(new String[] {"a b c", "z a c"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"a b c", "z a c"}));
            assertArrayEquals(new String[] {"z a c", "a b c"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"z a c", "a b c"}));
        }

        @Test
        void testWholeSentenceIsReturned() {
            assertArrayEquals(new String[] {"four five six", "one two three"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"one two three", "four five six"}));
        }

        @Test
        void testSeveralTies() {
            assertArrayEquals(new String[] {"b a", "c a", "d A", "a B"},
                    StringSortMixChallenge.sortByLastWord(new String[] {"a B", "b a", "c a", "d A"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] sentences = {"go home", "say Apple", "a b Zoo"};
            StringSortMixChallenge.sortByLastWord(sentences);
            assertArrayEquals(new String[] {"go home", "say Apple", "a b Zoo"}, sentences);
        }

        @Test
        void testBigArray() {
            String[] sentences = new String[100_000];
            for (int i = 0; i < sentences.length; i++) {
                sentences[i] = "s" + i + " w" + String.format("%05d", 99_999 - i);
            }
            String[] result = StringSortMixChallenge.sortByLastWord(sentences);
            assertEquals("s99999 w00000", result[0]);
            assertEquals("s0 w99999", result[99_999]);
        }

        @Test
        void testBigArrayWithTies() {
            String[] sentences = new String[100_000];
            for (int i = 0; i < sentences.length; i++) {
                sentences[i] = "s" + i + (i % 2 == 0 ? " b" : " a");
            }
            String[] result = StringSortMixChallenge.sortByLastWord(sentences);
            assertEquals("s1 a", result[0]);
            assertEquals("s3 a", result[1]);
            assertEquals("s0 b", result[50_000]);
            assertEquals("s99998 b", result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 9: sortNumbersAsText
    // ==========================================================
    @Nested
    class SortNumbersAsTextTests {

        @Test
        void testFourNumbers() {
            assertArrayEquals(new String[] {"1", "9", "10", "100"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"10", "9", "100", "1"}));
        }

        @Test
        void testSameLength() {
            assertArrayEquals(new String[] {"12", "21", "30"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"30", "12", "21"}));
        }

        @Test
        void testZero() {
            assertArrayEquals(new String[] {"0", "5", "10"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"10", "0", "5"}));
        }

        @Test
        void testNoNumbers() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.sortNumbersAsText(new String[] {}));
        }

        @Test
        void testOneNumber() {
            assertArrayEquals(new String[] {"5"}, StringSortMixChallenge.sortNumbersAsText(new String[] {"5"}));
        }

        @Test
        void testNotAlphabetical() {
            assertArrayEquals(new String[] {"9", "10"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"10", "9"}));
            assertArrayEquals(new String[] {"2", "11", "100"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"100", "11", "2"}));
        }

        @Test
        void testRepeatedNumbers() {
            assertArrayEquals(new String[] {"7", "7", "70"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"70", "7", "7"}));
        }

        @Test
        void testBigNumbers() {
            String small = "9".repeat(49);
            String big = "1" + "0".repeat(49);
            assertArrayEquals(new String[] {small, big}, StringSortMixChallenge.sortNumbersAsText(
                    new String[] {big, small}));
        }

        @Test
        void testBigNumbersWithTheSameLength() {
            String a = "123456789012345678901234567890123456789012345678" + "90";
            String b = "123456789012345678901234567890123456789012345678" + "91";
            assertArrayEquals(new String[] {a, b}, StringSortMixChallenge.sortNumbersAsText(new String[] {b, a}));
        }

        @Test
        void testNumbersTooBigForALong() {
            String[] numbers = {"99999999999999999999", "9999999999999999999", "100000000000000000000"};
            assertArrayEquals(new String[] {"9999999999999999999", "99999999999999999999", "100000000000000000000"},
                    StringSortMixChallenge.sortNumbersAsText(numbers));
        }

        @Test
        void testFirstDigitDecidesWhenTheLengthIsEqual() {
            assertArrayEquals(new String[] {"199", "299", "999"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"999", "199", "299"}));
        }

        @Test
        void testLastDigitDecidesWhenTheLengthIsEqual() {
            assertArrayEquals(new String[] {"101", "102", "109"},
                    StringSortMixChallenge.sortNumbersAsText(new String[] {"109", "101", "102"}));
        }

        @Test
        void testOriginalIsNotModified() {
            String[] numbers = {"10", "9", "100", "1"};
            StringSortMixChallenge.sortNumbersAsText(numbers);
            assertArrayEquals(new String[] {"10", "9", "100", "1"}, numbers);
        }

        @Test
        void testBigArray() {
            String[] numbers = new String[100_000];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = String.valueOf(99_999 - i);
            }
            String[] result = StringSortMixChallenge.sortNumbersAsText(numbers);
            assertEquals("0", result[0]);
            assertEquals("1", result[1]);
            assertEquals("10", result[10]);
            assertEquals("99999", result[99_999]);
        }
    }

    // ==========================================================
    // CHALLENGE 10: rankByScore
    // ==========================================================
    @Nested
    class RankByScoreTests {

        @Test
        void testThreeEntries() {
            assertArrayEquals(new String[] {"bob", "ana", "cy"},
                    StringSortMixChallenge.rankByScore(new String[] {"ana:90", "bob:100", "cy:90"}));
        }

        @Test
        void testScoresAreNumbers() {
            assertArrayEquals(new String[] {"b", "a"},
                    StringSortMixChallenge.rankByScore(new String[] {"a:9", "b:10"}));
        }

        @Test
        void testTiesAreAlphabeticalAndTheCaseMatters() {
            assertArrayEquals(new String[] {"B", "b"}, StringSortMixChallenge.rankByScore(new String[] {"b:1", "B:1"}));
        }

        @Test
        void testNoEntries() {
            assertArrayEquals(new String[] {}, StringSortMixChallenge.rankByScore(new String[] {}));
        }

        @Test
        void testOneEntry() {
            assertArrayEquals(new String[] {"x"}, StringSortMixChallenge.rankByScore(new String[] {"x:5"}));
        }

        @Test
        void testTiesAreOrderedByNameNotByPosition() {
            assertArrayEquals(new String[] {"a", "z"}, StringSortMixChallenge.rankByScore(new String[] {"z:1", "a:1"}));
            assertArrayEquals(new String[] {"a", "z"}, StringSortMixChallenge.rankByScore(new String[] {"a:1", "z:1"}));
        }

        @Test
        void testHighestScoreFirst() {
            assertArrayEquals(new String[] {"c", "b", "a"},
                    StringSortMixChallenge.rankByScore(new String[] {"a:1", "b:2", "c:3"}));
        }

        @Test
        void testZeroAndMaximumScores() {
            assertArrayEquals(new String[] {"top", "mid", "low"},
                    StringSortMixChallenge.rankByScore(new String[] {"low:0", "top:1000", "mid:999"}));
        }

        @Test
        void testHundredBeatsNinetyNine() {
            assertArrayEquals(new String[] {"a", "b"},
                    StringSortMixChallenge.rankByScore(new String[] {"b:99", "a:100"}));
        }

        @Test
        void testNamesWithDigitsAndSpaces() {
            assertArrayEquals(new String[] {"ana maria", "a1"},
                    StringSortMixChallenge.rankByScore(new String[] {"a1:5", "ana maria:7"}));
        }

        @Test
        void testTiesCompareOnlyTheName() {
            assertArrayEquals(new String[] {"a", "a1"},
                    StringSortMixChallenge.rankByScore(new String[] {"a1:5", "a:5"}));
        }

        @Test
        void testRepeatedNames() {
            assertArrayEquals(new String[] {"ana", "ana", "bob"},
                    StringSortMixChallenge.rankByScore(new String[] {"ana:5", "bob:1", "ana:9"}));
        }

        @Test
        void testSeveralTiesAndScores() {
            assertArrayEquals(new String[] {"d", "a", "c", "b"},
                    StringSortMixChallenge.rankByScore(new String[] {"b:1", "a:5", "c:5", "d:9"}));
        }

        @Test
        void testOnlyNamesAreReturned() {
            String[] result = StringSortMixChallenge.rankByScore(new String[] {"ana:90"});
            assertEquals(1, result.length);
            assertEquals("ana", result[0]);
        }

        @Test
        void testOriginalIsNotModified() {
            String[] entries = {"ana:90", "bob:100", "cy:90"};
            StringSortMixChallenge.rankByScore(entries);
            assertArrayEquals(new String[] {"ana:90", "bob:100", "cy:90"}, entries);
        }

        @Test
        void testBigArray() {
            String[] entries = new String[100_000];
            for (int i = 0; i < entries.length; i++) {
                entries[i] = "n" + String.format("%05d", i) + ":" + (i % 100);
            }
            String[] result = StringSortMixChallenge.rankByScore(entries);
            assertEquals("n00099", result[0]);
            assertEquals("n00199", result[1]);
            assertEquals("n99900", result[99_999]);
        }
    }
}