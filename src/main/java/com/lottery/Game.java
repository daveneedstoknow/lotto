package com.lottery;

import org.joda.time.LocalDate;

/**
 * Job: Runs the lottery game for the dates and numbers specified
 */
public class Game {

    private final RoundOfGame roundOfGame;

    public Game(final RoundOfGame roundOfGame) {
        this.roundOfGame = roundOfGame;
    }

    public void run(LocalDate firstDraw, LocalDate endDate, NumberSet playersNumbers) {

        LocalDate drawDate = firstDraw;

        while (!drawDate.isAfter(endDate)) {
            roundOfGame.draw(drawDate, playersNumbers);
            drawDate = drawDate.plusWeeks(1);
        }
    }

}
