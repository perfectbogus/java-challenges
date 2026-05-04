package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesisSeniorTest {

    @Test
    void testSinglePair() {
        assertTrue(ValidParenthesisSenior.solve("()"));
    }

    @Test
    void testMultiplePairs() {
        assertTrue(ValidParenthesisSenior.solve("()[]{}"));
    }

    @Test
    void testNested() {
        assertTrue(ValidParenthesisSenior.solve("{[]}"));
    }

    @Test
    void testMismatchedPairs() {
        assertFalse(ValidParenthesisSenior.solve("(]"));
    }

    @Test
    void testWrongOrder() {
        assertFalse(ValidParenthesisSenior.solve("([)]"));
    }

    @Test
    void testOnlyClosingBracket() {
        assertFalse(ValidParenthesisSenior.solve("]"));
    }

    @Test
    void testEmptyString() {
        assertTrue(ValidParenthesisSenior.solve(""));
    }

}