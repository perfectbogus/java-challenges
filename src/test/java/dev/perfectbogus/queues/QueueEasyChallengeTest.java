package dev.perfectbogus.queues;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class QueueEasyChallengeTest {

    private static Queue<String> line(String... names) {
        return new LinkedList<>(Arrays.asList(names));
    }

    private static Queue<String> dequeLine(String... names) {
        return new ArrayDeque<>(Arrays.asList(names));
    }

    private static Queue<Integer> numbers(Integer... values) {
        return new LinkedList<>(Arrays.asList(values));
    }

    private static Queue<Integer> dequeNumbers(Integer... values) {
        return new ArrayDeque<>(Arrays.asList(values));
    }

    private static <T> List<T> contents(Queue<T> queue) {
        return new ArrayList<>(queue);
    }

    // ==========================================================
    // CHALLENGE 1: serveNext
    // ==========================================================
    @Nested
    class ServeNextTests {

        @Test
        void testReturnsTheFirstPersonAndRemovesThem() {
            Queue<String> line = line("Ana", "Bob", "Cy");

            assertEquals("Ana", QueueEasyChallenge.serveNext(line));

            assertEquals(List.of("Bob", "Cy"), contents(line));
        }

        @Test
        void testRemovesOnlyOnePerCall() {
            Queue<String> line = line("Ana", "Bob", "Cy");

            assertEquals("Ana", QueueEasyChallenge.serveNext(line));
            assertEquals("Bob", QueueEasyChallenge.serveNext(line));

            assertEquals(List.of("Cy"), contents(line));
        }

        @Test
        void testEmptyLineReturnsNobody() {
            Queue<String> line = line();

            assertEquals("nobody", QueueEasyChallenge.serveNext(line));

            assertEquals(0, line.size());
        }

        @Test
        void testEmptyLineStaysEmptyAfterSeveralCalls() {
            Queue<String> line = line("Ana");

            assertEquals("Ana", QueueEasyChallenge.serveNext(line));
            assertEquals("nobody", QueueEasyChallenge.serveNext(line));
            assertEquals("nobody", QueueEasyChallenge.serveNext(line));

            assertEquals(0, line.size());
        }

        @Test
        void testWorksWithAnArrayDeque() {
            Queue<String> line = dequeLine("Ana", "Bob");

            assertEquals("Ana", QueueEasyChallenge.serveNext(line));
            assertEquals(List.of("Bob"), contents(line));
            assertEquals("nobody", QueueEasyChallenge.serveNext(dequeLine()));
        }
    }

    // ==========================================================
    // CHALLENGE 2: moveFrontToBack
    // ==========================================================
    @Nested
    class MoveFrontToBackTests {

        @Test
        void testTheFirstPersonGoesToTheBack() {
            Queue<String> line = line("Ana", "Bob", "Cy");

            QueueEasyChallenge.moveFrontToBack(line);

            assertEquals(List.of("Bob", "Cy", "Ana"), contents(line));
        }

        @Test
        void testTwoPeopleSwapPlaces() {
            Queue<String> line = line("Ana", "Bob");

            QueueEasyChallenge.moveFrontToBack(line);

            assertEquals(List.of("Bob", "Ana"), contents(line));
        }

        @Test
        void testOnePersonStaysTheSame() {
            Queue<String> line = line("Ana");

            QueueEasyChallenge.moveFrontToBack(line);

            assertEquals(List.of("Ana"), contents(line));
        }

        @Test
        void testEmptyLineStaysEmpty() {
            Queue<String> linked = line();
            Queue<String> deque = dequeLine();

            QueueEasyChallenge.moveFrontToBack(linked);
            QueueEasyChallenge.moveFrontToBack(deque);

            assertEquals(0, linked.size());
            assertEquals(0, deque.size());
        }

        @Test
        void testRepeatingItAsManyTimesAsPeopleGivesTheOriginalLine() {
            Queue<String> line = line("Ana", "Bob", "Cy");

            QueueEasyChallenge.moveFrontToBack(line);
            QueueEasyChallenge.moveFrontToBack(line);
            assertEquals(List.of("Cy", "Ana", "Bob"), contents(line));
            QueueEasyChallenge.moveFrontToBack(line);

            assertEquals(List.of("Ana", "Bob", "Cy"), contents(line));
        }

        @Test
        void testWorksWithAnArrayDeque() {
            Queue<String> line = dequeLine("Ana", "Bob", "Cy");

            QueueEasyChallenge.moveFrontToBack(line);

            assertEquals(List.of("Bob", "Cy", "Ana"), contents(line));
        }
    }

    // ==========================================================
    // CHALLENGE 3: largestWaiting
    // ==========================================================
    @Nested
    class LargestWaitingTests {

        @Test
        void testReturnsTheGreatestNumber() {
            assertEquals(9, QueueEasyChallenge.largestWaiting(numbers(4, 9, 2)));
        }

        @Test
        void testTheQueueIsNotChanged() {
            Queue<Integer> queue = numbers(4, 9, 2);

            QueueEasyChallenge.largestWaiting(queue);

            assertEquals(List.of(4, 9, 2), contents(queue));
        }

        @Test
        void testTheQueueIsNotChangedWithAnArrayDeque() {
            Queue<Integer> queue = dequeNumbers(1, 5, 3);

            assertEquals(5, QueueEasyChallenge.largestWaiting(queue));

            assertEquals(List.of(1, 5, 3), contents(queue));
        }

        @Test
        void testAllNegativeNumbers() {
            assertEquals(-2, QueueEasyChallenge.largestWaiting(numbers(-5, -2, -9)));
        }

        @Test
        void testGreatestAtTheStartAndAtTheEnd() {
            assertEquals(8, QueueEasyChallenge.largestWaiting(numbers(8, 1, 2)));
            assertEquals(8, QueueEasyChallenge.largestWaiting(numbers(1, 2, 8)));
        }

        @Test
        void testRepeatedAndExtremeNumbers() {
            assertEquals(3, QueueEasyChallenge.largestWaiting(numbers(3, 3, 3)));
            assertEquals(Integer.MIN_VALUE, QueueEasyChallenge.largestWaiting(numbers(Integer.MIN_VALUE)));
            assertEquals(Integer.MAX_VALUE,
                    QueueEasyChallenge.largestWaiting(numbers(0, Integer.MAX_VALUE, Integer.MIN_VALUE)));
        }

        @Test
        void testEmptyQueueGivesNull() {
            assertNull(QueueEasyChallenge.largestWaiting(numbers()));
        }
    }

    // ==========================================================
    // CHALLENGE 4: removeValue
    // ==========================================================
    @Nested
    class RemoveValueTests {

        @Test
        void testRemovesEveryOccurrence() {
            Queue<Integer> queue = numbers(3, 7, 3, 5, 3);

            assertEquals(3, QueueEasyChallenge.removeValue(queue, 3));

            assertEquals(List.of(7, 5), contents(queue));
        }

        @Test
        void testValueThatIsNotThere() {
            Queue<Integer> queue = numbers(1, 2);

            assertEquals(0, QueueEasyChallenge.removeValue(queue, 9));

            assertEquals(List.of(1, 2), contents(queue));
        }

        @Test
        void testAllNumbersAreRemoved() {
            Queue<Integer> queue = numbers(4, 4, 4);

            assertEquals(3, QueueEasyChallenge.removeValue(queue, 4));

            assertEquals(0, queue.size());
        }

        @Test
        void testNumbersNextToEachOther() {
            Queue<Integer> queue = numbers(5, 5, 1, 5, 5, 2, 5);

            assertEquals(5, QueueEasyChallenge.removeValue(queue, 5));

            assertEquals(List.of(1, 2), contents(queue));
        }

        @Test
        void testTheOtherNumbersKeepTheirOrder() {
            Queue<Integer> queue = dequeNumbers(5, 1, 9, 5, 2, 8, 5);

            assertEquals(3, QueueEasyChallenge.removeValue(queue, 5));

            assertEquals(List.of(1, 9, 2, 8), contents(queue));
        }

        @Test
        void testNegativeAndBigNumbers() {
            Queue<Integer> queue = numbers(-1, 1000, -1, 1000, 7);

            assertEquals(2, QueueEasyChallenge.removeValue(queue, 1000));
            assertEquals(2, QueueEasyChallenge.removeValue(queue, -1));

            assertEquals(List.of(7), contents(queue));
        }

        @Test
        void testEmptyQueue() {
            Queue<Integer> queue = numbers();

            assertEquals(0, QueueEasyChallenge.removeValue(queue, 4));

            assertEquals(0, queue.size());
        }
    }

    // ==========================================================
    // CHALLENGE 5: reversed
    // ==========================================================
    @Nested
    class ReversedTests {

        @Test
        void testTheLastNumberComesOutFirst() {
            Queue<Integer> result = QueueEasyChallenge.reversed(numbers(1, 2, 3));

            assertEquals(3, result.poll());
            assertEquals(2, result.poll());
            assertEquals(1, result.poll());
            assertNull(result.poll());
        }

        @Test
        void testTheOriginalQueueIsNotModified() {
            Queue<Integer> queue = numbers(1, 2, 3);

            QueueEasyChallenge.reversed(queue);

            assertEquals(List.of(1, 2, 3), contents(queue));
        }

        @Test
        void testTheResultIsANewQueue() {
            Queue<Integer> queue = numbers(1, 2, 3);

            Queue<Integer> result = QueueEasyChallenge.reversed(queue);

            assertNotSame(queue, result);
        }

        @Test
        void testTheResultCanBeChangedWithoutChangingTheOriginal() {
            Queue<Integer> queue = numbers(1, 2, 3);

            Queue<Integer> result = QueueEasyChallenge.reversed(queue);
            result.offer(9);
            result.poll();

            assertEquals(List.of(1, 2, 3), contents(queue));
            assertEquals(List.of(2, 1, 9), contents(result));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(List.of(3, 2, 2, 1), contents(QueueEasyChallenge.reversed(numbers(1, 2, 2, 3))));
        }

        @Test
        void testOneNumberAndEmpty() {
            assertEquals(List.of(7), contents(QueueEasyChallenge.reversed(numbers(7))));
            assertEquals(0, QueueEasyChallenge.reversed(numbers()).size());
        }

        @Test
        void testWorksWithAnArrayDeque() {
            Queue<Integer> queue = dequeNumbers(4, 5, 6);

            assertEquals(List.of(6, 5, 4), contents(QueueEasyChallenge.reversed(queue)));
            assertEquals(List.of(4, 5, 6), contents(queue));
        }
    }

    // ==========================================================
    // CHALLENGE 6: rotate
    // ==========================================================
    @Nested
    class RotateTests {

        @Test
        void testMovesTheFirstNumbersToTheBack() {
            Queue<Integer> queue = numbers(1, 2, 3, 4, 5);

            QueueEasyChallenge.rotate(queue, 2);

            assertEquals(List.of(3, 4, 5, 1, 2), contents(queue));
        }

        @Test
        void testZeroChangesNothing() {
            Queue<Integer> queue = numbers(1, 2, 3);

            QueueEasyChallenge.rotate(queue, 0);

            assertEquals(List.of(1, 2, 3), contents(queue));
        }

        @Test
        void testOneAndAllButOne() {
            Queue<Integer> one = numbers(1, 2, 3, 4);
            Queue<Integer> almostAll = numbers(1, 2, 3, 4);

            QueueEasyChallenge.rotate(one, 1);
            QueueEasyChallenge.rotate(almostAll, 3);

            assertEquals(List.of(2, 3, 4, 1), contents(one));
            assertEquals(List.of(4, 1, 2, 3), contents(almostAll));
        }

        @Test
        void testAsManyAsTheSizeGivesTheSameQueue() {
            Queue<Integer> queue = numbers(1, 2, 3);

            QueueEasyChallenge.rotate(queue, 3);

            assertEquals(List.of(1, 2, 3), contents(queue));
        }

        @Test
        void testMoreThanTheSize() {
            Queue<Integer> seven = numbers(1, 2, 3, 4, 5);
            Queue<Integer> twelve = numbers(1, 2, 3, 4, 5);
            Queue<Integer> ten = numbers(1, 2, 3, 4, 5);

            QueueEasyChallenge.rotate(seven, 7);
            QueueEasyChallenge.rotate(twelve, 12);
            QueueEasyChallenge.rotate(ten, 10);

            assertEquals(List.of(3, 4, 5, 1, 2), contents(seven));
            assertEquals(List.of(3, 4, 5, 1, 2), contents(twelve));
            assertEquals(List.of(1, 2, 3, 4, 5), contents(ten));
        }

        @Test
        void testEmptyQueueStaysEmpty() {
            Queue<Integer> linked = numbers();
            Queue<Integer> deque = dequeNumbers();

            QueueEasyChallenge.rotate(linked, 3);
            QueueEasyChallenge.rotate(deque, 0);

            assertEquals(0, linked.size());
            assertEquals(0, deque.size());
        }

        @Test
        void testOneNumberWithAHugeK() {
            Queue<Integer> queue = numbers(8);

            QueueEasyChallenge.rotate(queue, 1000000);

            assertEquals(List.of(8), contents(queue));
        }

        @Test
        void testWorksWithAnArrayDeque() {
            Queue<Integer> queue = dequeNumbers(1, 2, 3, 4, 5);

            QueueEasyChallenge.rotate(queue, 2);

            assertEquals(List.of(3, 4, 5, 1, 2), contents(queue));
        }
    }

    // ==========================================================
    // CHALLENGE 7: lastN
    // ==========================================================
    @Nested
    class LastNTests {

        @Test
        void testKeepsTheLastNumbers() {
            assertEquals(List.of(4, 5, 6), contents(QueueEasyChallenge.lastN(new int[]{1, 2, 3, 4, 5, 6}, 3)));
        }

        @Test
        void testFewerNumbersThanNKeepsAll() {
            assertEquals(List.of(8, 9), contents(QueueEasyChallenge.lastN(new int[]{8, 9}, 5)));
        }

        @Test
        void testNZeroGivesAnEmptyQueue() {
            assertEquals(0, QueueEasyChallenge.lastN(new int[]{8, 9}, 0).size());
        }

        @Test
        void testNEqualToTheAmountOfNumbers() {
            assertEquals(List.of(1, 2, 3), contents(QueueEasyChallenge.lastN(new int[]{1, 2, 3}, 3)));
        }

        @Test
        void testNOne() {
            assertEquals(List.of(6), contents(QueueEasyChallenge.lastN(new int[]{1, 2, 6}, 1)));
        }

        @Test
        void testRepeatedNumbers() {
            assertEquals(List.of(5, 1), contents(QueueEasyChallenge.lastN(new int[]{5, 5, 5, 1}, 2)));
        }

        @Test
        void testEmptyArray() {
            assertEquals(0, QueueEasyChallenge.lastN(new int[]{}, 3).size());
        }

        @Test
        void testTheOldestNumberComesOutFirst() {
            Queue<Integer> result = QueueEasyChallenge.lastN(new int[]{10, 20, 30, 40}, 2);

            assertEquals(30, result.poll());
            assertEquals(40, result.poll());
            assertNull(result.poll());
        }

        @Test
        void testTheArrayIsNotModified() {
            int[] input = {1, 2, 3, 4, 5};

            QueueEasyChallenge.lastN(input, 2);

            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, input);
        }
    }

    // ==========================================================
    // CHALLENGE 8: interleave
    // ==========================================================
    @Nested
    class InterleaveTests {

        @Test
        void testAlternatesStartingWithTheFirstQueue() {
            Queue<Integer> result = QueueEasyChallenge.interleave(numbers(1, 2, 3), numbers(10, 20));

            assertEquals(List.of(1, 10, 2, 20, 3), contents(result));
        }

        @Test
        void testSecondQueueIsLonger() {
            Queue<Integer> result = QueueEasyChallenge.interleave(numbers(1), numbers(10, 20, 30));

            assertEquals(List.of(1, 10, 20, 30), contents(result));
        }

        @Test
        void testSameLength() {
            Queue<Integer> result = QueueEasyChallenge.interleave(numbers(1, 2), numbers(3, 4));

            assertEquals(List.of(1, 3, 2, 4), contents(result));
        }

        @Test
        void testOneOfThemIsEmpty() {
            assertEquals(List.of(5, 6), contents(QueueEasyChallenge.interleave(numbers(), numbers(5, 6))));
            assertEquals(List.of(5, 6), contents(QueueEasyChallenge.interleave(numbers(5, 6), numbers())));
        }

        @Test
        void testBothEmpty() {
            assertEquals(0, QueueEasyChallenge.interleave(numbers(), numbers()).size());
        }

        @Test
        void testTheOriginalQueuesAreNotModified() {
            Queue<Integer> first = numbers(1, 2, 3);
            Queue<Integer> second = numbers(10, 20);

            QueueEasyChallenge.interleave(first, second);

            assertEquals(List.of(1, 2, 3), contents(first));
            assertEquals(List.of(10, 20), contents(second));
        }

        @Test
        void testTheResultIsANewQueue() {
            Queue<Integer> first = numbers(1, 2);
            Queue<Integer> second = numbers();

            Queue<Integer> result = QueueEasyChallenge.interleave(first, second);

            assertNotSame(first, result);
            assertNotSame(second, result);
        }

        @Test
        void testWorksWithArrayDeques() {
            Queue<Integer> result = QueueEasyChallenge.interleave(dequeNumbers(1, 2, 3), dequeNumbers(7, 8, 9));

            assertEquals(List.of(1, 7, 2, 8, 3, 9), contents(result));
        }
    }

    // ==========================================================
    // CHALLENGE 9: totalWaitingTime
    // ==========================================================
    @Nested
    class TotalWaitingTimeTests {

        @Test
        void testAddsWhatEachCustomerWaits() {
            assertEquals(8, QueueEasyChallenge.totalWaitingTime(new int[]{3, 2, 4}));
        }

        @Test
        void testTheOrderMatters() {
            assertEquals(10, QueueEasyChallenge.totalWaitingTime(new int[]{4, 2, 3}));
            assertEquals(7, QueueEasyChallenge.totalWaitingTime(new int[]{2, 3, 4}));
        }

        @Test
        void testOneCustomerWaitsNothing() {
            assertEquals(0, QueueEasyChallenge.totalWaitingTime(new int[]{5}));
        }

        @Test
        void testNoCustomers() {
            assertEquals(0, QueueEasyChallenge.totalWaitingTime(new int[]{}));
        }

        @Test
        void testTwoCustomers() {
            assertEquals(7, QueueEasyChallenge.totalWaitingTime(new int[]{7, 9}));
        }

        @Test
        void testAllTakeOneMinute() {
            assertEquals(6, QueueEasyChallenge.totalWaitingTime(new int[]{1, 1, 1, 1}));
        }

        @Test
        void testBiggerTimes() {
            assertEquals(100, QueueEasyChallenge.totalWaitingTime(new int[]{10, 20, 30, 40}));
        }
    }

    // ==========================================================
    // CHALLENGE 10: binaryNumbers
    // ==========================================================
    @Nested
    class BinaryNumbersTests {

        @Test
        void testFirstFiveNumbers() {
            assertEquals(List.of("1", "10", "11", "100", "101"), QueueEasyChallenge.binaryNumbers(5));
        }

        @Test
        void testOneNumber() {
            assertEquals(List.of("1"), QueueEasyChallenge.binaryNumbers(1));
        }

        @Test
        void testZeroNumbers() {
            assertEquals(0, QueueEasyChallenge.binaryNumbers(0).size());
        }

        @Test
        void testFirstTenNumbers() {
            assertEquals(List.of("1", "10", "11", "100", "101", "110", "111", "1000", "1001", "1010"),
                    QueueEasyChallenge.binaryNumbers(10));
        }

        @Test
        void testPowersOfTwo() {
            List<String> result = QueueEasyChallenge.binaryNumbers(16);

            assertEquals(16, result.size());
            assertEquals("1000", result.get(7));
            assertEquals("10000", result.get(15));
        }

        @Test
        void testEveryNumberOfALongerList() {
            List<String> result = QueueEasyChallenge.binaryNumbers(50);

            assertEquals(50, result.size());
            for (int i = 1; i <= 50; i++) {
                assertEquals(Integer.toBinaryString(i), result.get(i - 1));
            }
        }
    }

    // ==========================================================
    // CHALLENGE 11: lastPlayer
    // ==========================================================
    @Nested
    class LastPlayerTests {

        private final List<String> five = List.of("Ana", "Bob", "Cy", "Di", "Eve");

        @Test
        void testOnePassPerRound() {
            assertEquals("Cy", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob", "Cy"), 1));
        }

        @Test
        void testFivePlayersTwoPasses() {
            assertEquals("Di", QueueEasyChallenge.lastPlayer(five, 2));
        }

        @Test
        void testTwoPlayers() {
            assertEquals("Bob", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob"), 2));
            assertEquals("Ana", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob"), 1));
        }

        @Test
        void testOnePlayerAlwaysWins() {
            assertEquals("Ana", QueueEasyChallenge.lastPlayer(List.of("Ana"), 1));
            assertEquals("Ana", QueueEasyChallenge.lastPlayer(List.of("Ana"), 7));
        }

        @Test
        void testMorePassesThanPlayers() {
            assertEquals("Ana", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob", "Cy"), 5));
            assertEquals("Cy", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob", "Cy", "Di"), 7));
            assertEquals("Ana", QueueEasyChallenge.lastPlayer(five, 7));
        }

        @Test
        void testOtherCombinations() {
            assertEquals("Bob", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob", "Cy", "Di"), 3));
            assertEquals("Bob", QueueEasyChallenge.lastPlayer(List.of("Ana", "Bob", "Cy", "Di"), 4));
            assertEquals("Di", QueueEasyChallenge.lastPlayer(five, 5));
            assertEquals("Bob", QueueEasyChallenge.lastPlayer(five, 4));
        }

        @Test
        void testTheListIsNotModified() {
            List<String> players = new ArrayList<>(List.of("Ana", "Bob", "Cy", "Di"));

            QueueEasyChallenge.lastPlayer(players, 3);

            assertEquals(List.of("Ana", "Bob", "Cy", "Di"), players);
        }
    }

    // ==========================================================
    // CHALLENGE 12: finishingOrder
    // ==========================================================
    @Nested
    class FinishingOrderTests {

        @Test
        void testOneUnitPerTurn() {
            assertEquals(List.of("Bob", "Cy", "Ana"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy"}, new int[]{3, 1, 2}, 1));
        }

        @Test
        void testTheOneWithLessWorkDoesNotAlwaysLeaveFirst() {
            assertEquals(List.of("Ana", "Bob"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob"}, new int[]{6, 5}, 3));
            assertEquals(List.of("Ana", "Bob"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob"}, new int[]{4, 3}, 2));
        }

        @Test
        void testSeveralTurnsPerCustomer() {
            assertEquals(List.of("Bob", "Cy", "Ana"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy"}, new int[]{5, 3, 4}, 2));
            assertEquals(List.of("Bob", "Di", "Cy", "Ana"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy", "Di"},
                            new int[]{7, 2, 6, 3}, 3));
        }

        @Test
        void testAQuantumBiggerThanTheWorkOfEveryoneKeepsTheOriginalOrder() {
            assertEquals(List.of("Ana", "Bob", "Cy"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy"}, new int[]{3, 1, 2}, 10));
        }

        @Test
        void testSameWorkKeepsTheOriginalOrder() {
            assertEquals(List.of("Ana", "Bob", "Cy"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy"}, new int[]{3, 3, 3}, 2));
            assertEquals(List.of("Ana", "Bob", "Cy"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy"}, new int[]{1, 1, 1}, 1));
        }

        @Test
        void testFourCustomersWithOneUnitPerTurn() {
            assertEquals(List.of("Ana", "Cy", "Bob", "Di"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana", "Bob", "Cy", "Di"},
                            new int[]{1, 3, 2, 3}, 1));
        }

        @Test
        void testOneCustomerAndNoCustomers() {
            assertEquals(List.of("Ana"),
                    QueueEasyChallenge.finishingOrder(new String[]{"Ana"}, new int[]{4}, 3));
            assertEquals(0, QueueEasyChallenge.finishingOrder(new String[]{}, new int[]{}, 2).size());
        }

        @Test
        void testTheArraysAreNotModified() {
            String[] names = {"Ana", "Bob", "Cy"};
            int[] units = {3, 1, 2};

            QueueEasyChallenge.finishingOrder(names, units, 1);

            assertArrayEquals(new String[]{"Ana", "Bob", "Cy"}, names);
            assertArrayEquals(new int[]{3, 1, 2}, units);
        }
    }
}