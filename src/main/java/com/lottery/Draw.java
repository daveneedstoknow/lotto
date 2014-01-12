package com.lottery;

import org.joda.time.LocalDate;

/**
 * Job: Represents a specific draw result
 */
public class Draw {
    private final LocalDate drawDate;
    private final NumberSet numberSet;

    public Draw(LocalDate drawDate, NumberSet numberSet) {
        this.drawDate = drawDate;
        this.numberSet = numberSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Draw draw = (Draw) o;

        if (!drawDate.equals(draw.drawDate)) return false;
        if (!numberSet.equals(draw.numberSet)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = drawDate.hashCode();
        result = 31 * result + numberSet.hashCode();
        return result;
    }
}
