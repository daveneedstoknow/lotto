package com.lottery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Job: Verifies that NumberSet works correctly
 */
public class NumberSetTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireNotLessThanSixNumbers() {
        new NumberSet(new int[]{1, 2, 3, 4, 5});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireNotMoreThanSixNumbers() {
        new NumberSet(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireSixUniqueNumbers() {
        new NumberSet(new int[]{1, 2, 3, 4, 5, 4});
    }


    @Test
    public void shouldUnderstandEqualNumberSets() {
        NumberSet firstNumberSet = new NumberSet(new int[]{1, 2, 3, 4, 5, 6});
        NumberSet secondNumberSet = new NumberSet(new int[]{1, 2, 3, 4, 5, 6});
        NumberSet thirdNumberSet = new NumberSet(new int[]{1, 2, 4, 7, 5, 6});

        assertEquals(firstNumberSet, secondNumberSet);

        assertNotEquals(firstNumberSet, thirdNumberSet);
    }


    @Test
    public void shouldCalculateSumOfNumbers() {
        NumberSet numberSet = new NumberSet(60, 59, 58, 57, 56, 55);

        assertEquals(60 + 59 + 58 + 57 + 56 + 55, numberSet.sum());
    }

    @Test
    public void shouldCalculateProductOfMissedNumbers() {
        NumberSet numberSet = new NumberSet(60, 59, 58, 57, 56, 55);

        long product = 60l * 59l * 58l * 57l * 56l * 55l;
        NumberSet excludedNumbers = new NumberSet(1, 2, 3, 4, 5, 6);
        assertEquals(product, numberSet.productExcluding(excludedNumbers));

        long partialProduct = 60l * 59l * 56l * 55l;
        NumberSet excludedNumbers1 = new NumberSet(58, 57, 3, 4, 5, 6);
        assertEquals(partialProduct, numberSet.productExcluding(excludedNumbers1));
    }

    @Test
    public void shouldCountNumberOfMatchedNumbers() {
        NumberSet numberSet = new NumberSet(60, 59, 58, 57, 56, 55);
        NumberSet noMatching = new NumberSet(1, 2, 3, 4, 5, 6);
        NumberSet oneMatching = new NumberSet(1, 2, 3, 60, 5, 6);
        NumberSet allMatching = new NumberSet(60, 59, 58, 57, 56, 55);

        assertEquals(0, numberSet.countMatching(noMatching));
        assertEquals(1, numberSet.countMatching(oneMatching));
        assertEquals(6, numberSet.countMatching(allMatching));

    }
}
