package com.lottery;

import java.util.HashSet;
import java.util.Set;

/**
* Job: Represents a particular set of lottery numbers - whether as a selection of played numbers
 *     or as the numbers in a particular draw
 */
public class NumberSet {

    private final Set<Integer> numbers = new HashSet<>();

    public NumberSet(int[] numbers) {
        for (int number : numbers) {
            this.numbers.add(number);
        }
        if (this.numbers.size() != 6) {
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
