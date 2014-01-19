package com.lottery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Job: Provides pseudo-random Draw results
 */
public class LotteryMachine {
    private final Random random;

    public LotteryMachine(Random random) {

        this.random = random;
    }

    public NumberSet draw() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < NumberSet.DRAW_SIZE)
        {
            numbers.add(random.nextInt());
        }
        return new NumberSet(numbers);
    }
}
