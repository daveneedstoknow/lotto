package com.lottery;

import com.lottery.parsers.EndDateParser;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Job: Verify EndDateParser works correctly
 */
public class EndDateParserTest {
    private LocalDate todaysDate = new LocalDate(2014, 1, 1);
    private EndDateParser endDateParser = new EndDateParser(todaysDate);


    @Test
    public void shouldAcceptValidDate() {
        assertTrue(endDateParser.isValid("01/01/2015"));
        assertTrue(endDateParser.isValid("01/07/2015"));
        assertTrue(endDateParser.isValid("31/12/2015"));
    }

    @Test
    public void shouldRejectBadlyFormattedDates() {
        assertFalse(endDateParser.isValid("01/31/2015"));
        assertFalse(endDateParser.isValid("01/07/15"));
    }

    @Test
    public void shouldEnsureDateIsLessThanSixMonthsAway() {
        assertFalse(endDateParser.isValid("31/12/2013"));
        assertTrue(endDateParser.isValid("01/07/2014"));
        assertFalse(endDateParser.isValid("30/06/2014"));
    }

    @Test
    public void shouldParseDate() {
        assertEquals(todaysDate, endDateParser.endDate("01/01/2014"));
    }
}
