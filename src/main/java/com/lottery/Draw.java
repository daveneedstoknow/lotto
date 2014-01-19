package com.lottery;

/**
 * Job: Understands how many numbers have matched and what that means for base winnings
 */
public class Draw {
    private final NumberSet drawnNumbers;
    private final NumberSet playedNumbers;

    public Draw(NumberSet drawnNumbers, NumberSet playersNumbers) {
        this.drawnNumbers = drawnNumbers;
        this.playedNumbers = playersNumbers;
    }

    public long baseWinnings() {
        int matchedNumberCount = drawnNumbers.countMatching(playedNumbers);
        if (matchedNumberCount < 3) return drawnNumbers.sum();
        if (matchedNumberCount < 6) return (matchedNumberCount * 1000) + drawnNumbers.productExcluding(playedNumbers);
        return drawnNumbers.sum() * 10000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Draw draw = (Draw) o;

        if (!drawnNumbers.equals(draw.drawnNumbers)) return false;
        if (!playedNumbers.equals(draw.playedNumbers)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = drawnNumbers.hashCode();
        result = 31 * result + playedNumbers.hashCode();
        return result;
    }
}
