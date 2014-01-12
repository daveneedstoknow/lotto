package com.lottery;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Job: Understands the run rules for the lottery game
 */
public class DrawGenerator {
    private final DrawDateFactory drawDateFactory;
    private final LotteryMachine lotteryMachine;

    public DrawGenerator(DrawDateFactory drawDateFactory, LotteryMachine lotteryMachine) {

        this.drawDateFactory = drawDateFactory;
        this.lotteryMachine = lotteryMachine;
    }

    public List<Draw> draw(LocalDate endDate) {
        for (LocalDate drawDate : drawDateFactory.drawDates(endDate)) {
            lotteryMachine.draw();
        }

        return null;
    }
}
