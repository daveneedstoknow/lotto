package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Job: Ensure Draw works correctly
 */
public class DrawTest {

    public static final LocalDate DRAW_DATE_1 = new LocalDate(2010, 1, 1);
    public static final LocalDate DRAW_DATE_2 = new LocalDate(2011, 1, 1);

    @Test
    public void shouldImplementEqualsAndHashCode() {
        Draw firstDraw = new Draw(DRAW_DATE_1, new NumberSet(1, 2, 3, 4, 5, 6));
        Draw secondDraw = new Draw(DRAW_DATE_1, new NumberSet(1, 2, 3, 4, 5, 6));
        Draw thirdDraw = new Draw(DRAW_DATE_2, new NumberSet(1, 2, 3, 4, 5, 6));

        assertThat(firstDraw, equalTo(secondDraw));
        assertThat(firstDraw, not(equalTo(thirdDraw)));
        assertThat(firstDraw.hashCode(), equalTo(secondDraw.hashCode()));


    }
}
