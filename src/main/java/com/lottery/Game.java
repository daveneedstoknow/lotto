package com.lottery;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Job: Runs the lottery game for the dates and numbers specified
 */
public class Game {

    private final DrawGenerator drawGenerator;
    private final ResultsPublisher resultsPublisher;

    public Game(final DrawGenerator drawGenerator, final ResultsPublisher resultsPublisher) {
        this.drawGenerator = drawGenerator;
        this.resultsPublisher = resultsPublisher;
    }

    public void run(LocalDate endDate, NumberSet numbers) {
        LocalDate startDate = endDate.minusMonths(6);
        List<Draw> draws = drawGenerator.draw(startDate, endDate);
        resultsPublisher.publish(draws, numbers);
    }
}
