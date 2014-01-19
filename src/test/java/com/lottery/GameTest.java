package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Job: Verify Game is run correctly
 */
public class GameTest {

    private static final LocalDate END_DATE = new LocalDate(2014, 6, 23);
    private static final LocalDate DAY_AFTER_END_DATE = new LocalDate(2014, 6, 24);
    private static final LocalDate A_SUNDAY = new LocalDate(2014, 1, 19);
    private static final LocalDate A_MONDAY = new LocalDate(2014, 1, 20);
    private static final LocalDate WEEK_ON_MONDAY = new LocalDate(2014, 1, 27);
    private static final LocalDate TWO_WEEKS_ON_MONDAY = new LocalDate(2014, 2, 3);

    public static final NumberSet NUMBERS = new NumberSet(1, 2, 3, 4, 5, 6);
    public static final List<Draw> DRAWS = Arrays.asList(mock(Draw.class));
    private Game game;
    private RoundOfGame roundOfGame;
    private ResultsPublisher resultsPublisher;

    @Before
    public void setUp() throws Exception {
        roundOfGame = mock(RoundOfGame.class);
        resultsPublisher = mock(ResultsPublisher.class);
        game = new Game(roundOfGame, resultsPublisher);
    }

    @Test
    public void shouldDrawEveryWeekInclusiveOfStartAndEndDate() {
        game.run(A_MONDAY, A_MONDAY.plusWeeks(2), NUMBERS);

        InOrder inOrder = inOrder(roundOfGame);
        inOrder.verify(roundOfGame).draw(A_MONDAY, resultsPublisher, NUMBERS);
        inOrder.verify(roundOfGame).draw(WEEK_ON_MONDAY, resultsPublisher, NUMBERS);
        inOrder.verify(roundOfGame).draw(TWO_WEEKS_ON_MONDAY, resultsPublisher, NUMBERS);

        verify(roundOfGame, times(3)).draw(any(LocalDate.class), any(ResultsPublisher.class), any(NumberSet.class));
    }


}
