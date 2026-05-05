package dev.perfectbogus.deques;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    @Test
    void testPalindrome() {
        assertTrue(Palindrome.isPalindrome("aa"));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(Palindrome.isPalindrome("racecar"));
    }

    @Test
    void testNotPalindrome() {
        assertFalse(Palindrome.isPalindrome("notAPalindrome"));
    }

    @Test
    void testIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> Palindrome.isPalindrome(null));
    }

    @Test
    void testEmptyString() {
        assertTrue(Palindrome.isPalindrome(""));
    }

    @Test
    void testSingleChar() {
        assertTrue(Palindrome.isPalindrome("a"));
    }

    @Test
    void testCaseSensitive() {
        assertFalse(Palindrome.isPalindrome("Racecar"));
    }

    @Test
    void testWithSpaces() {
        assertFalse(Palindrome.isPalindrome("race car"));
    }

}