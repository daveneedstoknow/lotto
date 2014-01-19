package com.lottery;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Job: Verify Results Publish works correctly
 */
public class ResultsPublisherTest {

    public static final Draw DRAW = mock(Draw.class);
    public static final NumberSet NUMBERS = mock(NumberSet.class);
    public static final long WINNINGS = 25;
    private WinningsCalculator winningsCalculator;
    private ConsoleResultWriter consoleResultWriter;

    @Before
    public void setUp() throws Exception {
        winningsCalculator = mock(WinningsCalculator.class);
        consoleResultWriter = mock(ConsoleResultWriter.class);
    }

    @Test
    public void shouldCheckResultAndPublish() {
        ResultsPublisher resultsPublisher = new ResultsPublisher(winningsCalculator, consoleResultWriter);

        when(winningsCalculator.calculateWinnings(DRAW, NUMBERS)).thenReturn(WINNINGS);

        resultsPublisher.publish(DRAW, NUMBERS);

        verify(consoleResultWriter).write(DRAW, WINNINGS);
    }
}
