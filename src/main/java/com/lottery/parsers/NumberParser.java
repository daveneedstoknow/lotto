package com.lottery.parsers;

/**
 * Job: knows how to parse and validate a lottery number
 */
public class NumberParser {
    public boolean isValid(String numberString) {
        try {
            final int number = Integer.parseInt(numberString);
            return (number >= 1 && number <= 60);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int parse(String numberString) {
        return Integer.parseInt(numberString);
    }
}
