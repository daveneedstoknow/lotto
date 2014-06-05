package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Job: Ensure Draw works correctly
 */
public class DrawTest {

    public static final LocalDate NORMAL_DRAW_DATE = new LocalDate(2010, 1, 1);
    public static final LocalDate DRAW_DATE_2 = new LocalDate(2011, 1, 1);


    @Test
    public void shouldImplementEqualsAndHashCode() {
        fail();
        Draw firstDraw = new Draw(new NumberSet(7, 2, 3, 4, 5, 6), new NumberSet(1, 2, 3, 4, 5, 6));
        Draw secondDraw = new Draw(new NumberSet(7, 2, 3, 4, 5, 6), new NumberSet(1, 2, 3, 4, 5, 6));
        Draw thirdDraw = new Draw(new NumberSet(8, 2, 3, 4, 5, 6), new NumberSet(1, 2, 3, 4, 5, 6));

        assertThat(firstDraw, equalTo(secondDraw));
        assertThat(firstDraw, not(equalTo(thirdDraw)));
        assertThat(firstDraw.hashCode(), equalTo(secondDraw.hashCode()));
    }

    @Test
    public void shouldCalculateWinningsForNoMatchedNumbers() {
        NumberSet drawnNumbers = new NumberSet(7, 8, 9, 10, 11, 12);
        NumberSet playersNumbers = new NumberSet(1, 2, 3, 4, 5, 6);
        Draw firstDraw = new Draw(drawnNumbers, playersNumbers);
        assertEquals(drawnNumbers.sum(), firstDraw.baseWinnings());
    }

    @Test
    public void shouldCalculateWinningsForThreeToSixNumbers() {

        NumberSet drawnNumbers = new NumberSet(1, 2, 3, 10, 11, 12);
        NumberSet playersNumbers3Matching = new NumberSet(1, 2, 3, 4, 5, 6);
        NumberSet playersNumbers5Matching = new NumberSet(1, 2, 3, 10, 11, 6);

        long oneThousandFor3GuessedNumber = 3000;
        long product3MissedNumbers = drawnNumbers.productExcluding(playersNumbers3Matching);
        long expectedWinningsDraw1 = oneThousandFor3GuessedNumber + product3MissedNumbers;

        Draw draw1 = new Draw(drawnNumbers, playersNumbers3Matching);

        assertEquals(expectedWinningsDraw1, draw1.baseWinnings());

        long oneThousandFor5GuessedNumber = 5000;
        long product1MissedNumbers = drawnNumbers.productExcluding(playersNumbers5Matching);
        long expectedWinningsDraw2 = oneThousandFor5GuessedNumber + product1MissedNumbers;

        Draw draw2 = new Draw(drawnNumbers, playersNumbers5Matching);

        assertEquals(expectedWinningsDraw2, draw2.baseWinnings());
    }

    @Test
    public void shouldCalculateWinningsForSixNumbers() {

        NumberSet drawnNumbers = new NumberSet(1, 2, 3, 10, 11, 12);
        NumberSet playersNumbers6Matching = new NumberSet(1, 2, 3, 10, 11, 12);

        long expectedWinnings = drawnNumbers.sum() * 10000;

        Draw draw = new Draw(drawnNumbers, playersNumbers6Matching);

        assertEquals(expectedWinnings, draw.baseWinnings());
    }
}
