package dev.perfectbogus.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesTest {

    @Test
    void SimpleTest() {
        String s = "()()";
        assertTrue(ValidParentheses.solve(s));
    }

    @Test
    void CombinationTest() {
        String s = "([{}])";
        assertTrue(ValidParentheses.solve(s));
    }

    @Test
    void SimpleInvalidTest() {
        String s = "(]";
        assertFalse(ValidParentheses.solve(s));
    }

    @Test
    void CombinationInvalidTest() {
        String s = "([)]";
        assertFalse(ValidParentheses.solve(s));
    }

    @Test
    void FailTest() {
        String s = "(}}}";
        assertFalse(ValidParentheses.solve(s));
    }
}