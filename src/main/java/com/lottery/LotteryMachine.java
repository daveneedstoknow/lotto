package com.lottery;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Job: Provides pseudo-random Draw results
 */
public class LotteryMachine {
    public static final int MAX_LOTTERY_NUMBER = 60;
    private final Random random;

    public LotteryMachine(Random random) {

        this.random = random;
    }

    public NumberSet draw() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < NumberSet.DRAW_SIZE) {

            int random = Math.abs(this.random.nextInt()) % MAX_LOTTERY_NUMBER;
            if (random != 0) numbers.add(random);
        }
        return new NumberSet(numbers);
    }
}
