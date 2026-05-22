package dev.perfectbogus.functional.mini.challenges;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChallengesTest {
    // Challenge 1
    @Test
    void testFilterAndCount() {
        assertEquals(3, Challenges.filterAndCount(
                List.of(3, 15, 7, 22, 1, 18, 9), 10));
    }

    @Test
    void testFilterAndCountNoneMatch() {
        assertEquals(0, Challenges.filterAndCount(
                List.of(1, 2, 3), 10));
    }

    // Challenge 2
    @Test
    void testTransformAList() {
        assertEquals(List.of("ALICE", "BOB", "CHARLIE"),
                Challenges.transformAList(List.of("charlie", "alice", "bob")));
    }

    // Challenge 3
    @Test
    void testSumWithReduce() {
        assertEquals(12L, Challenges.sumWithReduce(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void testSumWithReduceNoEvens() {
        assertEquals(0L, Challenges.sumWithReduce(List.of(1, 3, 5)));
    }

    // Challenge 4
    @Test
    void testFlatMap() {
        List<String> result = Challenges.flatMap(
                List.of("hello world", "world of java"));
        assertEquals(4, result.size());
        assertTrue(result.containsAll(List.of("hello", "world", "of", "java")));
    }

    // Challenge 5
    @Test
    void testGroupingByFirstLetter() {
        Map<Character, List<String>> result = Challenges.groupingByFirstLetter(
                List.of("apple", "banana", "avocado", "blueberry", "cherry"));
        assertEquals(List.of("apple", "avocado"), result.get('a'));
        assertEquals(List.of("banana", "blueberry"), result.get('b'));
        assertEquals(List.of("cherry"), result.get('c'));
    }

    // Challenge 6
    @Test
    void testStudentsWhoPassed() {
        Map<String, Integer> scores = Map.of(
                "Alice", 85,
                "Bob",   45,
                "Carol", 72,
                "David", 55,
                "Eve",   90
        );
        Map<String, Character> result = Challenges.studentsWhoPassed(scores);
        assertEquals(3, result.size());
        assertEquals('B', result.get("Alice"));
        assertEquals('C', result.get("Carol"));
        assertEquals('A', result.get("Eve"));
        assertFalse(result.containsKey("Bob"));
        assertFalse(result.containsKey("David"));
    }

    @Test
    void testStudentsExactlyAt60() {
        Map<String, Character> result = Challenges.studentsWhoPassed(
                Map.of("Frank", 60));
        assertTrue(result.containsKey("Frank")); // 60 should pass!
        assertEquals('D', result.get("Frank"));
    }

    // Challenge 7
    @Test
    void testPartitioning() {
        Map<Boolean, List<Integer>> result = Challenges.partitioning(
                List.of(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(List.of(2, 4, 6, 8), result.get(true));
        assertEquals(List.of(1, 3, 5, 7), result.get(false));
    }

    // Challenge 8
    @Test
    void testJoiningNames() {
        assertEquals("Members: [charlie, alice, bob]",
                Challenges.joiningNames(List.of("charlie", "alice", "bob")));
    }

    // Challenge 9
    @Test
    void testChainFound() {
        assertEquals("WORLD!",
                Challenges.chain(List.of("hi", "hello", "world!", "java")));
    }

    @Test
    void testChainNotFound() {
        assertEquals("NONE",
                Challenges.chain(List.of("hi", "hey", "java")));
    }

    // Challenge 10
    @Test
    void testFrequencyMap() {
        Map<String, Long> result = Challenges.frequencyMap(
                List.of("apple", "banana", "apple", "cherry", "banana", "apple"));
        assertEquals(3L, result.get("apple"));
        assertEquals(2L, result.get("banana"));
        assertEquals(1L, result.get("cherry"));
        // Verify order — apple first
        assertEquals("apple", result.keySet().iterator().next());
    }
}