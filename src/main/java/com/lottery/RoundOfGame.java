package com.lottery;

import org.joda.time.LocalDate;

/**
 * Job: Run a single round of the game, drawing numbers, calculate winnings and publish results
 */
public class RoundOfGame {
    private final LotteryMachine lotteryMachine;
    private final ResultsPublisher resultsPublisher;
    private final WinningsCalculator winningsCalculator;

    public RoundOfGame(LotteryMachine lotteryMachine, ResultsPublisher resultsPublisher, WinningsCalculator winningsCalculator) {

        this.lotteryMachine = lotteryMachine;
        this.resultsPublisher = resultsPublisher;
        this.winningsCalculator = winningsCalculator;
    }

    public void draw(LocalDate drawDate, NumberSet numbers) {
        NumberSet drawnNumbers = lotteryMachine.draw();
        Draw draw = new Draw(drawnNumbers, numbers);
        long winnings = winningsCalculator.applyMultiplier(drawDate, draw);
        resultsPublisher.publish(drawDate, drawnNumbers, winnings);
    }
}
