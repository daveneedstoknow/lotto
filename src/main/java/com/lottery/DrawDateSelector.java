package com.lottery;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Job: Provides an iterator for every draw date for the game period
 */
public class DrawDateSelector {

    public List<LocalDate> drawDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> drawDates = new ArrayList<>();
        int dayOfWeek = startDate.getDayOfWeek();


        if (dayOfWeek == DateTimeConstants.MONDAY) drawDates.add(startDate);

        LocalDate nextDraw = startDate.plusDays(8 - dayOfWeek);
        while (!nextDraw.isAfter(endDate)) {
            drawDates.add(nextDraw);
            nextDraw = nextDraw.plusWeeks(1);
        }
        return drawDates;
    }
}
