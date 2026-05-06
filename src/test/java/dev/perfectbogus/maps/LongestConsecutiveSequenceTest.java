package dev.perfectbogus.maps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestConsecutiveSequenceTest {

    @Test
    void testBasicCase() {
        int[] data = {100, 4, 200, 1, 3, 2};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(4, result);
    }

    @Test
    void testSingleElement() {
        int[] data = {1};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(1, result);
    }

    @Test
    void testAllConsecutive() {
        int[] data = {1, 2, 3, 4 ,5};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(5, result);
    }

    @Test
    void testNoConsecutiveNumbers() {
        int[] data = {10, 20, 30};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(1, result);
    }

    @Test
    void testDuplicateNumbers() {
        int[] data = {1, 2, 2, 3 , 4};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(4, result);
    }

    @Test
    void testNegativeNumbers() {
        int[] data = {-3, -2, -1, 0, 1};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(5, result);
    }

    @Test
    void testEmptyArray() {
        int[] data = {};
        int result = LongestConsecutiveSequence.count(data);
        assertEquals(0, result);
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> LongestConsecutiveSequence.count(null));
    }

}