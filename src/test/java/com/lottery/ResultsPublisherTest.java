package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Job: Verify Results Publisher works correctly
 */
public class ResultsPublisherTest {

    @Test
    public void shouldPublishResult() {
        PrintStream printStream = mock(PrintStream.class);
        ResultsPublisher resultsPublisher = new ResultsPublisher(printStream);
        resultsPublisher.publish(new LocalDate(2014, 01, 01), new NumberSet(1, 2, 3, 4, 5, 6), 21439);
        verify(printStream).println("2014-01-01; 1,2,3,4,5,6; £21439");
    }
}
