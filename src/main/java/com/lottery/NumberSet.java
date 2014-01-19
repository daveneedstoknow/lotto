package com.lottery;

import java.util.HashSet;
import java.util.Set;

/**
* Job: Represents a particular set of lottery numbers - whether as a selection of played numbers
 *     or as the numbers in a particular draw
 */
public class NumberSet {

    public static final int DRAW_SIZE = 6;
    private final Set<Integer> numbers;

    public NumberSet(int...numbers) {
        this.numbers = new HashSet<>();
        for (int number : numbers) {
            this.numbers.add(number);
        }
        validateSize();
    }

    public NumberSet(Set<Integer> numbers) {
        this.numbers = numbers;

        validateSize();
    }

    private void validateSize() {
        if (this.numbers.size() != DRAW_SIZE) {
            throw new IllegalArgumentException("Require 6 unique numbers");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberSet numberSet = (NumberSet) o;

        if (!numbers.equals(numberSet.numbers)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }
}
