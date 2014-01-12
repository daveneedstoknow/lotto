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
 * Job: Verify Game works correctly
 */
public class DrawGeneratorTest {

    public static final LocalDate DRAW_DATE_1 = new LocalDate();
    public static final NumberSet NUMBER_SET = new NumberSet(new int[]{1, 2, 3, 4, 5, 6});
    private DrawGenerator drawGenerator;
    public static final List<LocalDate> DRAW_DATES = Arrays.asList(DRAW_DATE_1);
    private DrawDateFactory drawDateFactory;
    private LotteryMachine lotteryMachine;
    public static final LocalDate END_DATE = new LocalDate();

    @Before
    public void setUp() throws Exception {
        drawDateFactory = mock(DrawDateFactory.class);
        lotteryMachine = mock(LotteryMachine.class);
        drawGenerator = new DrawGenerator(drawDateFactory, lotteryMachine);
    }

    @Test
    public void shouldSelectNumbersForEveryDraw() {

        when(drawDateFactory.drawDates(END_DATE)).thenReturn(DRAW_DATES);
        when(lotteryMachine.draw()).thenReturn(NUMBER_SET);

        List<Draw> draws = drawGenerator.draw(END_DATE);

        List<Draw> expectedDraws = Arrays.asList(new Draw(DRAW_DATE_1, NUMBER_SET));

        assertThat(draws, equalTo(expectedDraws));

    }
}
