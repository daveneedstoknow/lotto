package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Job: Verify DrawGenerator works correctly
 */
public class DrawGeneratorTest {

    public static final LocalDate DRAW_DATE_1 = new LocalDate(2010, 2, 1);
    public static final LocalDate DRAW_DATE_2 = new LocalDate(2010, 2, 3);
    public static final NumberSet NUMBER_SET_1 = new NumberSet(new int[]{1, 2, 3, 4, 5, 6});
    public static final Draw DRAW_1 = new Draw(DRAW_DATE_1, NUMBER_SET_1);
    public static final NumberSet NUMBER_SET_2 = new NumberSet(new int[]{2, 3, 4, 5, 6, 7});
    public static final Draw DRAW_2 = new Draw(DRAW_DATE_2, NUMBER_SET_2);
    private DrawGenerator drawGenerator;
    public static final List<LocalDate> DRAW_DATES = Arrays.asList(DRAW_DATE_1, DRAW_DATE_2);
    private DrawDateSelector drawDateSelector;
    private LotteryMachine lotteryMachine;
    public static final LocalDate END_DATE = new LocalDate();

    @Before
    public void setUp() throws Exception {
        drawDateSelector = mock(DrawDateSelector.class);
        lotteryMachine = mock(LotteryMachine.class);
        drawGenerator = new DrawGenerator(drawDateSelector, lotteryMachine);
    }

    @Test
    public void shouldSelectNumbersForEveryDraw() {

        when(drawDateSelector.drawDates(END_DATE)).thenReturn(DRAW_DATES);
        when(lotteryMachine.draw()).thenReturn(NUMBER_SET_1, NUMBER_SET_2);

        List<Draw> draws = drawGenerator.draw(END_DATE);

        List<Draw> expectedDraws = Arrays.asList(DRAW_1, DRAW_2);

        assertThat(draws, equalTo(expectedDraws));

    }
}
