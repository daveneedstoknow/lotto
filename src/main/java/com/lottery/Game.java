package com.lottery;

import org.joda.time.LocalDate;

/**
 * Job: Runs the lottery game for the dates and numbers specified
 */
public class Game {

    private final RoundOfGame roundOfGame;
    private final ResultsPublisher resultsPublisher;

    public Game(final RoundOfGame roundOfGame, final ResultsPublisher resultsPublisher) {
        this.roundOfGame = roundOfGame;
        this.resultsPublisher = resultsPublisher;
    }

    public void run(LocalDate firstDraw, LocalDate endDate, NumberSet numbers) {

        LocalDate drawDate = firstDraw;

        while (!drawDate.isAfter(endDate)) {
            roundOfGame.draw(drawDate, resultsPublisher, numbers);
            drawDate = drawDate.plusWeeks(1);
        }
    }

}
