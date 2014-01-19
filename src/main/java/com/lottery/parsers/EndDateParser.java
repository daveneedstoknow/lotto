package com.lottery.parsers;

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Understands how to parse an end date
 */
public class EndDateParser {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2)
            .appendLiteral('/')
            .appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2)
            .appendLiteral('/')
            .appendFixedDecimal(DateTimeFieldType.year(), 4)
            .toFormatter();
    public static final int GAME_LENGTH_IN_MONTHS = 6;
    private final LocalDate todaysDate;

    public EndDateParser(LocalDate todaysDate) {

        this.todaysDate = todaysDate;
    }

    public LocalDate endDate(String validEndDate) {
        return DATE_TIME_FORMATTER.parseLocalDate(validEndDate);
    }

    public LocalDate firstDrawDate(String validEndDate) {
        LocalDate startDate = endDate(validEndDate).minusMonths(GAME_LENGTH_IN_MONTHS);
        int dayOfWeek = startDate.getDayOfWeek();
        return (dayOfWeek == DateTimeConstants.MONDAY) ? startDate : startDate.plusDays(8 - dayOfWeek);
    }

    public boolean isValid(String arg) {
        try {
            LocalDate endDate = DATE_TIME_FORMATTER.parseLocalDate(arg);
            return todaysDate.plusMonths(GAME_LENGTH_IN_MONTHS).compareTo(endDate) <= 0;
        } catch (IllegalArgumentException invalidDate) {
            return false;
        }

    }
}
