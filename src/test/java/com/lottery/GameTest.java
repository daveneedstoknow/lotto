package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Job: Verify Game is run correctly
 */
public class GameTest {

    private static final LocalDate END_DATE = new LocalDate(2014, 6, 1);
    private static final LocalDate START_DATE = new LocalDate(2013, 12, 1);
    public static final NumberSet NUMBERS = new NumberSet(1, 2, 3, 4, 5, 6);
    public static final List<Draw> DRAWS = Arrays.asList(mock(Draw.class));

    @Test
    public void shouldDrawNumbersAndPublishResults() {

        DrawGenerator drawGenerator = mock(DrawGenerator.class);
        when(drawGenerator.draw(eq(START_DATE), eq(END_DATE))).thenReturn(DRAWS);

        ResultsPublisher resultsPublisher = mock(ResultsPublisher.class);
        Game game = new Game(drawGenerator, resultsPublisher);

        game.run(END_DATE, NUMBERS);

        verify(resultsPublisher).publish(DRAWS, NUMBERS);
    }


}
