package com.lottery;

/**
 * Job: Publish a draw result
 */
public class ResultsPublisher {
    private final WinningsCalculator winningsCalculator;
    private final ConsoleResultWriter consoleResultWriter;

    public ResultsPublisher(WinningsCalculator winningsCalculator, ConsoleResultWriter consoleResultWriter) {

        this.winningsCalculator = winningsCalculator;
        this.consoleResultWriter = consoleResultWriter;
    }

    public void publish(Draw draw, NumberSet numbers) {
        long winnings = winningsCalculator.calculateWinnings(draw, numbers);
        consoleResultWriter.write(draw, winnings);
    }
}
