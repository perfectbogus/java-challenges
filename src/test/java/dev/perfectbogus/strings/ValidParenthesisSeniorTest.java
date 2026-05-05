package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesisSeniorTest {

    @Test
    void testSinglePair() {
        assertTrue(ValidParenthesisSenior.isValid("()"));
    }

    @Test
    void testMultiplePairs() {
        assertTrue(ValidParenthesisSenior.isValid("()[]{}"));
    }

    @Test
    void testNested() {
        assertTrue(ValidParenthesisSenior.isValid("{[]}"));
    }

    @Test
    void testMismatchedPairs() {
        assertFalse(ValidParenthesisSenior.isValid("(]"));
    }

    @Test
    void testWrongOrder() {
        assertFalse(ValidParenthesisSenior.isValid("([)]"));
    }

    @Test
    void testOnlyClosingBracket() {
        assertFalse(ValidParenthesisSenior.isValid("]"));
    }

    @Test
    void testEmptyString() {
        assertTrue(ValidParenthesisSenior.isValid(""));
    }

}