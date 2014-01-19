package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Job: Calculates winnings from Draw and Date
 */
public class WinningsCalculatorTest {

    public static final LocalDate FEB_29 = new LocalDate(2004, 2, 29);
    public static final LocalDate FEB_28 = new LocalDate(2004, 2, 28);
    public static final LocalDate NORMAL_DATE = new LocalDate(2005, 2, 2);
    public static final LocalDate LEAP_YEAR_NOT_FEB = new LocalDate(2004, 1, 2);
    public static final long BASE_WINNINGS = 4l;
    private Draw draw;
    private WinningsCalculator winningsCalculator;

    @Before
    public void setUp() throws Exception {
        draw = mock(Draw.class);
        when(draw.baseWinnings()).thenReturn(BASE_WINNINGS);
        winningsCalculator = new WinningsCalculator();
    }

    @Test
    public void shouldReturnBaseWinningsForNonFebInLeapYear() {
        assertEquals(BASE_WINNINGS, winningsCalculator.applyMultiplier(NORMAL_DATE, draw));
        assertEquals(BASE_WINNINGS, winningsCalculator.applyMultiplier(LEAP_YEAR_NOT_FEB, draw));
    }

    @Test
    public void shouldTripleForFebruary29th() {
        assertEquals(BASE_WINNINGS * 3, winningsCalculator.applyMultiplier(FEB_29, draw));
    }

    @Test
    public void shouldDoubleForFebruaryInLeapYear() {
        assertEquals(BASE_WINNINGS * 2, winningsCalculator.applyMultiplier(FEB_28, draw));
    }
}
