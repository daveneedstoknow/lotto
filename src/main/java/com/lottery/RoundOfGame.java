package com.lottery;

import org.joda.time.LocalDate;

/**
 * Job: Run a single round of the game
 */
public class RoundOfGame {
    private final LotteryMachine lotteryMachine;
    private ResultsPublisher resultsPublisher;
    private final SpecialDateMultiplier specialDateMultiplier;

    public RoundOfGame(LotteryMachine lotteryMachine, ResultsPublisher resultsPublisher, SpecialDateMultiplier specialDateMultiplier) {

        this.lotteryMachine = lotteryMachine;
        this.resultsPublisher = resultsPublisher;
        this.specialDateMultiplier = specialDateMultiplier;
    }

    public void draw(LocalDate drawDate, NumberSet numbers) {
        NumberSet drawnNumbers = lotteryMachine.draw();
        Draw draw = new Draw(drawnNumbers, numbers);
        long winnings = specialDateMultiplier.applyMultiplier(drawDate, draw);
        resultsPublisher.publish(drawDate, drawnNumbers, winnings);
    }
}
