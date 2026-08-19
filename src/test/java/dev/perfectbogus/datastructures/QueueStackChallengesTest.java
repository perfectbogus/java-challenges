package dev.perfectbogus.datastructures;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QueueStackChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Reverse words using Deque as stack
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            assertEquals("fun is Java World Hello",
                    QueueStackChallenges.challenge1("Hello World Java is fun"));
        }

        @Test
        void singleWord() {
            assertEquals("one", QueueStackChallenges.challenge1("one"));
        }

        @Test
        void twoWords() {
            assertEquals("World Hello", QueueStackChallenges.challenge1("Hello World"));
        }

        @Test
        void singleChars() {
            assertEquals("d c b a", QueueStackChallenges.challenge1("a b c d"));
        }

        @Test
        void alreadyReversed() {
            assertEquals("Hello", QueueStackChallenges.challenge1("Hello"));
        }

        @Test
        void emptyString() {
            assertEquals("", QueueStackChallenges.challenge1(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Valid Stack Sequence
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void validSequence() {
            assertTrue(QueueStackChallenges.challenge2(
                    new int[]{1,2,3,4,5},
                    new int[]{4,5,3,2,1}));
        }

        @Test
        void invalidSequence() {
            assertFalse(QueueStackChallenges.challenge2(
                    new int[]{1,2,3,4,5},
                    new int[]{4,3,5,1,2}));
        }

        @Test
        void pushAllThenPopAll() {
            assertTrue(QueueStackChallenges.challenge2(
                    new int[]{1,2,3},
                    new int[]{3,2,1}));
        }

        @Test
        void pushPopAlternating() {
            assertTrue(QueueStackChallenges.challenge2(
                    new int[]{1,2,3},
                    new int[]{1,2,3}));
        }

        @Test
        void singleElement() {
            assertTrue(QueueStackChallenges.challenge2(
                    new int[]{1}, new int[]{1}));
        }

        @Test
        void wrongLastElement() {
            assertFalse(QueueStackChallenges.challenge2(
                    new int[]{1,2,3},
                    new int[]{3,1,2}));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge2(null, new int[]{1}));
        }

        @Test
        void differentLengths() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge2(new int[]{1,2}, new int[]{1}));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Palindrome check using Deque
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void simplePalindrome() {
            assertTrue(QueueStackChallenges.challenge3("racecar"));
        }

        @Test
        void notPalindrome() {
            assertFalse(QueueStackChallenges.challenge3("hello"));
        }

        @Test
        void palindromeWithSpacesAndCase() {
            assertTrue(QueueStackChallenges.challenge3("A man a plan a canal Panama"));
        }

        @Test
        void palindromeWithSpaces() {
            assertTrue(QueueStackChallenges.challenge3("Was it a car or a cat I saw"));
        }

        @Test
        void emptyString() {
            assertTrue(QueueStackChallenges.challenge3(""));
        }

        @Test
        void singleChar() {
            assertTrue(QueueStackChallenges.challenge3("a"));
        }

        @Test
        void twoSameChars() {
            assertTrue(QueueStackChallenges.challenge3("aa"));
        }

        @Test
        void twoDifferentChars() {
            assertFalse(QueueStackChallenges.challenge3("ab"));
        }

        @Test
        void numbersAndLetters() {
            assertTrue(QueueStackChallenges.challenge3("A1b2b1A"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Ticket Counter Simulation (VIP queue jumping)
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            List<String> result = QueueStackChallenges.challenge4(
                    List.of("REG:Alice","REG:Bob","VIP:Carol","REG:Diana","VIP:Eve"));

            // VIPs added to front in arrival order → Eve(last VIP) at very front
            assertEquals("Eve",  result.get(0));
            assertEquals("Carol",result.get(1));
            assertEquals("Alice",result.get(2));
            assertEquals("Bob",  result.get(3));
            assertEquals("Diana",result.get(4));
        }

        @Test
        void allRegular() {
            List<String> result = QueueStackChallenges.challenge4(
                    List.of("REG:Alice","REG:Bob","REG:Carol"));

            assertEquals(List.of("Alice","Bob","Carol"), result);
        }

        @Test
        void allVip() {
            List<String> result = QueueStackChallenges.challenge4(
                    List.of("VIP:Alice","VIP:Bob","VIP:Carol"));

            // VIPs jump to front in arrival order → Carol at front
            assertEquals("Carol", result.get(0));
            assertEquals("Bob",   result.get(1));
            assertEquals("Alice", result.get(2));
        }

        @Test
        void singleVip() {
            List<String> result = QueueStackChallenges.challenge4(
                    List.of("REG:Alice","VIP:Bob","REG:Carol"));

            assertEquals("Bob",   result.get(0)); // VIP first
            assertEquals("Alice", result.get(1));
            assertEquals("Carol", result.get(2));
        }

        @Test
        void singleCustomer() {
            List<String> result = QueueStackChallenges.challenge4(List.of("REG:Alice"));
            assertEquals(List.of("Alice"), result);
        }

        @Test
        void emptyList() {
            assertTrue(QueueStackChallenges.challenge4(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Sort a Stack using one additional Stack
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<Integer> result = QueueStackChallenges.challenge5(
                    List.of(3, 1, 4));

            // smallest at TOP (last element in list)
            assertEquals(4, result.get(0)); // bottom = largest
            assertEquals(3, result.get(1));
            assertEquals(1, result.get(2)); // top = smallest
        }

        @Test
        void alreadySorted() {
            List<Integer> result = QueueStackChallenges.challenge5(
                    List.of(5, 3, 1)); // 1 is top (smallest already)

            assertEquals(5, result.get(0));
            assertEquals(3, result.get(1));
            assertEquals(1, result.get(2));
        }

        @Test
        void reverseSorted() {
            List<Integer> result = QueueStackChallenges.challenge5(
                    List.of(1, 3, 5)); // 5 is top

            assertEquals(5, result.get(0));
            assertEquals(3, result.get(1));
            assertEquals(1, result.get(2));
        }

        @Test
        void withDuplicates() {
            List<Integer> result = QueueStackChallenges.challenge5(
                    List.of(3, 1, 3, 1));

            assertEquals(3, result.get(0));
            assertEquals(3, result.get(1));
            assertEquals(1, result.get(2));
            assertEquals(1, result.get(3)); // top = smallest
        }

        @Test
        void singleElement() {
            assertEquals(List.of(42), QueueStackChallenges.challenge5(List.of(42)));
        }

        @Test
        void twoElements() {
            List<Integer> result = QueueStackChallenges.challenge5(List.of(5, 2));
            assertEquals(5, result.get(0)); // bottom = largest
            assertEquals(2, result.get(1)); // top = smallest
        }

        @Test
        void emptyStack() {
            assertTrue(QueueStackChallenges.challenge5(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Score of Balanced Brackets using Stack
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void singlePair() {
            assertEquals(1, QueueStackChallenges.challenge6("()"));
        }

        @Test
        void nestedPair() {
            assertEquals(2, QueueStackChallenges.challenge6("(())"));
        }

        @Test
        void consecutivePairs() {
            assertEquals(2, QueueStackChallenges.challenge6("()()"));
        }

        @Test
        void complexCase() {
            // (()(()))
            // inner: () = 1, (()) = 2 → together = 3 → outer: 2*3 = 6
            assertEquals(6, QueueStackChallenges.challenge6("(()(()))"));
        }

        @Test
        void tripleNested() {
            // ((())) = 2*(2*(1)) = 4
            assertEquals(4, QueueStackChallenges.challenge6("(***)"));
        }

        @Test
        void tripleNestedCorrect() {
            // ((()))
            // innermost () = 1 → (()) = 2 → ((())) = 4
            assertEquals(4, QueueStackChallenges.challenge6("((()))"));
        }

        @Test
        void mixedConsecutiveAndNested() {
            // ()(()) = 1 + 2 = 3
            assertEquals(3, QueueStackChallenges.challenge6("()(())"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Browser History using two Stacks
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicNavigation() {
            List<String> result = QueueStackChallenges.challenge7(List.of(
                    "visit:google", "visit:facebook", "back", "current",
                    "forward", "current", "visit:twitter", "forward", "current"
            ));

            assertEquals(List.of("google", "facebook", "twitter"), result);
        }

        @Test
        void backAtHome() {
            // back when on home → stay on home
            List<String> result = QueueStackChallenges.challenge7(List.of(
                    "back", "current"
            ));
            assertEquals(List.of("home"), result);
        }

        @Test
        void forwardWithNoHistory() {
            // forward when no forward history → stay
            List<String> result = QueueStackChallenges.challenge7(List.of(
                    "visit:google", "forward", "current"
            ));
            assertEquals(List.of("google"), result);
        }

        @Test
        void visitClearsForward() {
            // back then visit should clear forward stack
            List<String> result = QueueStackChallenges.challenge7(List.of(
                    "visit:google", "visit:facebook", "back",
                    "visit:twitter",
                    "forward",   // forward cleared by visit! → stays at twitter
                    "current"
            ));
            assertEquals(List.of("twitter"), result);
        }

        @Test
        void multipleBack() {
            List<String> result = QueueStackChallenges.challenge7(List.of(
                    "visit:a", "visit:b", "visit:c",
                    "back", "current",
                    "back", "current"
            ));
            assertEquals(List.of("b", "a"), result);
        }

        @Test
        void currentAtStart() {
            List<String> result = QueueStackChallenges.challenge7(List.of("current"));
            assertEquals(List.of("home"), result);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Decode String using two Stacks
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void simpleRepeat() {
            assertEquals("aaa", QueueStackChallenges.challenge8("3[a]"));
        }

        @Test
        void nestedRepeat() {
            assertEquals("abbabbabb", QueueStackChallenges.challenge8("3[a2[b]]"));
        }

        @Test
        void consecutiveGroups() {
            assertEquals("abcabccdcdcdef", QueueStackChallenges.challenge8("2[abc]3[cd]ef"));
        }

        @Test
        void nestedInNested() {
            // 2[3[a]b] → inner: 3[a]=aaa → outer: 2[aaab]=aaabaaab
            assertEquals("aaabaaab", QueueStackChallenges.challenge8("2[3[a]b]"));
        }

        @Test
        void noRepeat() {
            assertEquals("abc", QueueStackChallenges.challenge8("abc"));
        }

        @Test
        void singleChar() {
            assertEquals("z", QueueStackChallenges.challenge8("z"));
        }

        @Test
        void repeatOnce() {
            assertEquals("ab", QueueStackChallenges.challenge8("1[ab]"));
        }

        @Test
        void deeplyNested() {
            // 2[2[a]] → inner: 2[a]=aa → outer: 2[aa]=aaaa
            assertEquals("aaaa", QueueStackChallenges.challenge8("2[2[a]]"));
        }

        @Test
        void emptyString() {
            assertEquals("", QueueStackChallenges.challenge8(""));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Sliding Window MINIMUM using monotonic Deque
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            assertArrayEquals(
                    new int[]{1,1,0,0,0,2},
                    QueueStackChallenges.challenge9(
                            new int[]{3,1,2,4,0,5,3,2}, 3));
        }

        @Test
        void windowSizeOne() {
            assertArrayEquals(
                    new int[]{3,1,2,4,0},
                    QueueStackChallenges.challenge9(
                            new int[]{3,1,2,4,0}, 1));
        }

        @Test
        void windowSizeEqualsArray() {
            assertArrayEquals(
                    new int[]{0},
                    QueueStackChallenges.challenge9(
                            new int[]{3,1,2,4,0}, 5));
        }

        @Test
        void ascendingArray() {
            // ascending → min always first element of window
            assertArrayEquals(
                    new int[]{1,2,3},
                    QueueStackChallenges.challenge9(
                            new int[]{1,2,3,4,5}, 3));
        }

        @Test
        void descendingArray() {
            // descending → min always last element of window
            assertArrayEquals(
                    new int[]{3,2,1},
                    QueueStackChallenges.challenge9(
                            new int[]{5,4,3,2,1}, 3));
        }

        @Test
        void allSameValues() {
            assertArrayEquals(
                    new int[]{3,3,3},
                    QueueStackChallenges.challenge9(
                            new int[]{3,3,3,3,3}, 3));
        }

        @Test
        void withNegatives() {
            assertArrayEquals(
                    new int[]{-5,-5,-3},
                    QueueStackChallenges.challenge9(
                            new int[]{-1,-3,-5,-3,-2}, 3));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge9(null, 3));
        }

        @Test
        void invalidK() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge9(new int[]{1,2,3}, 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Hot Potato Game using Deque
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            QueueStackChallenges.HotPotatoResult result =
                    QueueStackChallenges.challenge10(
                            List.of("Alice","Bob","Carol","Diana","Eve"), 3);

            assertEquals(List.of("Carol","Alice","Eve","Bob"), result.eliminated());
            assertEquals("Diana", result.winner());
        }

        @Test
        void twoPlayers() {
            QueueStackChallenges.HotPotatoResult result =
                    QueueStackChallenges.challenge10(List.of("Alice","Bob"), 2);

            assertEquals(1, result.eliminated().size());
            // k=2: rotate 1 → Alice goes to back → Bob is first → Bob eliminated
            assertEquals("Bob", result.eliminated().get(0));
            assertEquals("Alice", result.winner());
        }

        @Test
        void singlePlayer() {
            QueueStackChallenges.HotPotatoResult result =
                    QueueStackChallenges.challenge10(List.of("Alice"), 3);

            assertTrue(result.eliminated().isEmpty());
            assertEquals("Alice", result.winner());
        }

        @Test
        void kEqualsOne() {
            // k=1: first player is always eliminated immediately
            QueueStackChallenges.HotPotatoResult result =
                    QueueStackChallenges.challenge10(
                            List.of("Alice","Bob","Carol"), 1);

            assertEquals("Alice", result.eliminated().get(0));
            assertEquals("Bob",   result.eliminated().get(1));
            assertEquals("Carol", result.winner());
        }

        @Test
        void eliminatedCountEqualsPlayersMinusOne() {
            QueueStackChallenges.HotPotatoResult result =
                    QueueStackChallenges.challenge10(
                            List.of("A","B","C","D","E"), 3);

            assertEquals(4, result.eliminated().size());
            assertNotNull(result.winner());
        }

        @Test
        void winnerNotInEliminated() {
            QueueStackChallenges.HotPotatoResult result =
                    QueueStackChallenges.challenge10(
                            List.of("Alice","Bob","Carol","Diana","Eve"), 3);

            assertFalse(result.eliminated().contains(result.winner()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge10(null, 3));
        }

        @Test
        void invalidK() {
            assertThrows(IllegalArgumentException.class,
                    () -> QueueStackChallenges.challenge10(List.of("Alice"), 0));
        }
    }
}