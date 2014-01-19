package com.lottery;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Job: Verify LotteryMachine is working
 */
public class LotteryMachineTest {

    public static final Random RANDOM = mock(Random.class);

    @Test
    public void shouldGenerateValidNumberSet() {
        LotteryMachine lotteryMachine = new LotteryMachine(RANDOM);
        when(RANDOM.nextInt()).thenReturn(1, 2, 3, 4, 5, 6,
                1, 1, 2, 3, 4, 5, 8);

        assertThat(lotteryMachine.draw(), equalTo(new NumberSet(1, 2, 3, 4, 5, 6)));
        assertThat(lotteryMachine.draw(), equalTo(new NumberSet(1, 2, 3, 4, 5, 8)));

    }
}
