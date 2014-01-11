package com.lottery.parsers;

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
    private final LocalDate todaysDate;

    public EndDateParser(LocalDate todaysDate) {

        this.todaysDate = todaysDate;
    }

    public LocalDate parseDate(String validEndDate) {
        return DATE_TIME_FORMATTER.parseLocalDate(validEndDate);
    }

    public boolean isValid(String arg) {
        try {
            LocalDate endDate = DATE_TIME_FORMATTER.parseLocalDate(arg);
            return todaysDate.plusMonths(6).compareTo(endDate) <= 0;
        } catch (IllegalArgumentException invalidDate) {
            return false;
        }

    }
}
