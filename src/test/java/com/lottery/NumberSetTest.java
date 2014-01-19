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
}
