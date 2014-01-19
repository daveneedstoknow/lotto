package com.lottery;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Job: Represents a particular set of lottery numbers - whether as a selection of played numbers
 * or as the numbers in a particular draw
 */
public class NumberSet {

    public static final int DRAW_SIZE = 6;
    private final Set<Integer> numbers;

    public NumberSet(int... numbers) {
        this.numbers = new HashSet<>();
        for (int number : numbers) {
            this.numbers.add(number);
        }
        validateSize();
    }

    public NumberSet(Set<Integer> numbers) {
        this.numbers = new TreeSet<>(numbers);

        validateSize();
    }

    private void validateSize() {
        if (this.numbers.size() != DRAW_SIZE) {
            throw new IllegalArgumentException("Require 6 unique numbers");
        }
    }

    public long sum() {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    public long productExcluding(NumberSet excludedNumbers) {
        long product = 1;
        for (Integer number : numbers) {
            if (!excludedNumbers.numbers.contains(number)) product = product * number;
        }
        return product;
    }

    public int countMatching(NumberSet other) {
        Set<Integer> matching = new HashSet<>(numbers);
        matching.retainAll(other.numbers);
        return matching.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext())
                stringBuilder.append(',');
        }
        return stringBuilder.toString();
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
