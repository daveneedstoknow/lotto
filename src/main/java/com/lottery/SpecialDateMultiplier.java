package com.lottery;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 * Job: AppliesMultiplier to score for special dates
 */
public class SpecialDateMultiplier {
    public long applyMultiplier(LocalDate drawDate, Draw draw) {
        if (isFebruaryInLeapYear(drawDate))
            return drawDate.getDayOfMonth() == 29 ? draw.baseWinnings() * 3 : draw.baseWinnings() * 2;

        return draw.baseWinnings();
    }

    private boolean isFebruaryInLeapYear(LocalDate drawDate) {
        return drawDate.year().isLeap() && drawDate.getMonthOfYear() == DateTimeConstants.FEBRUARY;
    }
}
