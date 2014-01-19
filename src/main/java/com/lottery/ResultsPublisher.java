package com.lottery;

import org.joda.time.LocalDate;

import java.io.PrintStream;

/**
 * Job: Publish a draw result
 */
public class ResultsPublisher {

    private final PrintStream printStream;

    public ResultsPublisher(PrintStream printStream) {

        this.printStream = printStream;
    }

    public void publish(LocalDate drawDate, NumberSet drawnNumbers, long winnings) {
        printStream.println(drawDate + "; " + drawnNumbers + "; £" + winnings );
    }
}
