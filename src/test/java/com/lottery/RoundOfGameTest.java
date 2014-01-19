package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Job: Verify RoundOfGame works correctly
 */
public class RoundOfGameTest {

    public static final LocalDate DRAW_DATE_1 = new LocalDate(2010, 2, 1);
    public static final NumberSet CHOSEN_NUMBERS = new NumberSet(new int[]{51, 2, 3, 4, 5, 6});
    public static final NumberSet NUMBER_SET_1 = new NumberSet(new int[]{1, 2, 3, 4, 5, 6});
    public static final Draw DRAW_1 = new Draw(DRAW_DATE_1, NUMBER_SET_1);
    public static final NumberSet NUMBER_SET_2 = new NumberSet(new int[]{2, 3, 4, 5, 6, 7});
    private RoundOfGame roundOfGame;
    private LotteryMachine lotteryMachine;

    @Before
    public void setUp() throws Exception {
        lotteryMachine = mock(LotteryMachine.class);
        roundOfGame = new RoundOfGame(lotteryMachine);
    }

    @Test
    public void shouldPublishRoundResults() {

        when(lotteryMachine.draw()).thenReturn(NUMBER_SET_1, NUMBER_SET_2);

        ResultsPublisher resultsPublisher = mock(ResultsPublisher.class);

        roundOfGame.draw(DRAW_DATE_1, resultsPublisher, CHOSEN_NUMBERS);

        verify(resultsPublisher).publish(DRAW_1, CHOSEN_NUMBERS);

    }
}
