package com.lottery;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Job: Generates a set of dated draws for a game
 */
public class DrawGenerator {
    private final DrawDateFactory drawDateFactory;
    private final LotteryMachine lotteryMachine;

    public DrawGenerator(DrawDateFactory drawDateFactory, LotteryMachine lotteryMachine) {

        this.drawDateFactory = drawDateFactory;
        this.lotteryMachine = lotteryMachine;
    }

    public List<Draw> draw(LocalDate endDate) {
        List<Draw> draws = new ArrayList<>();
        for (LocalDate drawDate : drawDateFactory.drawDates(endDate)) {
            draws.add(new Draw(drawDate, lotteryMachine.draw()));
        }

        return draws;
    }
}
