package com.lottery;

import com.lottery.parsers.NumberParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Job: Verifies that NumberParser works correctly
 */
public class NumberParserTest {

    private NumberParser numberParser;

    @Before
    public void setUp() throws Exception {
        numberParser = new NumberParser();
    }

    @Test
    public void shouldRejectInvalidNumbers() {
        assertFalse(numberParser.isValid("fsdfs"));
        assertFalse(numberParser.isValid("1a1"));
        assertFalse(numberParser.isValid("1.1"));
        assertFalse(numberParser.isValid("-1"));
        assertFalse(numberParser.isValid("0"));
        assertFalse(numberParser.isValid("61"));
        assertFalse(numberParser.isValid("fsdfs", "61"));
    }

    @Test
    public void shouldAcceptValidNumbers() {
        assertTrue(numberParser.isValid("1"));
        assertTrue(numberParser.isValid("60"));
        assertTrue(numberParser.isValid("1", "60"));
    }

    @Test
    public void shouldParseNumber() {
        assertArrayEquals(new int[]{3}, numberParser.parse("3"));
        assertArrayEquals(new int[]{60}, numberParser.parse("60"));
        assertArrayEquals(new int[]{1}, numberParser.parse("1"));
        assertArrayEquals(new int[]{1, 3, 60}, numberParser.parse("1", "3", "60"));
    }
}
