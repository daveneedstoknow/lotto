package com.lottery;

import org.joda.time.LocalDate;

/**
 * Job: Run a single round of the game
 */
public class RoundOfGame {
    private final LotteryMachine lotteryMachine;

    public RoundOfGame(LotteryMachine lotteryMachine) {

        this.lotteryMachine = lotteryMachine;
    }

    public void draw(LocalDate drawDate, ResultsPublisher resultsPublisher, NumberSet numbers) {
        Draw draw = new Draw(drawDate, lotteryMachine.draw());
        resultsPublisher.publish(draw, numbers);
    }
}
