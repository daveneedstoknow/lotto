package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Job: Verify RoundOfGame works correctly
 */
public class RoundOfGameTest {

    public static final LocalDate DRAW_DATE_1 = new LocalDate(2010, 2, 1);
    public static final LocalDate DRAW_DATE_2 = new LocalDate(2010, 2, 3);
    public static final NumberSet CHOSEN_NUMBERS = new NumberSet(new int[]{51, 2, 3, 4, 5, 6});
    public static final NumberSet NUMBER_SET_1 = new NumberSet(new int[]{1, 2, 3, 4, 5, 6});
    public static final Draw DRAW_1 = new Draw(NUMBER_SET_1, CHOSEN_NUMBERS);
    public static final NumberSet NUMBER_SET_2 = new NumberSet(new int[]{2, 3, 4, 5, 6, 7});
    public static final Draw DRAW_2 = new Draw(NUMBER_SET_2, CHOSEN_NUMBERS);
    public static final long WINNINGS_1 = 4719147l;
    public static final long WINNINGS_2 = 4347829147l;
    private RoundOfGame roundOfGame;
    private LotteryMachine lotteryMachine;
    private ResultsPublisher resultsPublisher;
    private SpecialDateMultiplier specialDateMultiplier;

    @Before
    public void setUp() throws Exception {
        lotteryMachine = mock(LotteryMachine.class);
        resultsPublisher = mock(ResultsPublisher.class);
        specialDateMultiplier = mock(SpecialDateMultiplier.class);
        roundOfGame = new RoundOfGame(lotteryMachine, resultsPublisher, specialDateMultiplier);
    }

    @Test
    public void shouldPublishRoundWinnings() {

        when(lotteryMachine.draw()).thenReturn(NUMBER_SET_1, NUMBER_SET_2);

        when(specialDateMultiplier.applyMultiplier(DRAW_DATE_1, DRAW_1)).thenReturn(WINNINGS_1);
        when(specialDateMultiplier.applyMultiplier(DRAW_DATE_2, DRAW_2)).thenReturn(WINNINGS_2);

        roundOfGame.draw(DRAW_DATE_1, CHOSEN_NUMBERS);
        roundOfGame.draw(DRAW_DATE_2, CHOSEN_NUMBERS);

        verify(resultsPublisher).publish(eq(DRAW_DATE_1), eq(NUMBER_SET_1), eq(WINNINGS_1));
        verify(resultsPublisher).publish(eq(DRAW_DATE_2), eq(NUMBER_SET_2), eq(WINNINGS_2));

    }
}
