package com.lottery;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Job: Generates a set of dated draws for a game
 */
public class DrawGenerator {
    private final DrawDateSelector drawDateSelector;
    private final LotteryMachine lotteryMachine;

    public DrawGenerator(DrawDateSelector drawDateSelector, LotteryMachine lotteryMachine) {

        this.drawDateSelector = drawDateSelector;
        this.lotteryMachine = lotteryMachine;
    }

    public List<Draw> draw(LocalDate startDate, LocalDate endDate) {
        List<Draw> draws = new ArrayList<>();
        for (LocalDate drawDate : drawDateSelector.drawDates(startDate, endDate)) {
            draws.add(new Draw(drawDate, lotteryMachine.draw()));
        }

        return draws;
    }
}
