package com.lottery.parsers;

/**
 * Job: knows how to parse and validate a lottery number
 */
public class NumberParser {
    public boolean isValid(String... numbers) {
        for (String numberString : numbers) {
            try {
                final int number = Integer.parseInt(numberString);
                if (!(number >= 1 && number <= 60)) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public int[] parse(String... numbersString) {
        int[] numbers = new int[numbersString.length];
        for (int i = 0; i < numbersString.length; i++) {
            String numberString = numbersString[i];
            numbers[i] = Integer.parseInt(numberString);
        }
        return numbers;
    }
}
