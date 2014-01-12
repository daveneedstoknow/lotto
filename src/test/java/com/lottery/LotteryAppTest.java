package com.lottery;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LotteryAppTest {

    @Test
    public void shouldUseLotteryLauncherInMain() {
        LotteryLauncher lotteryLauncher = mock(LotteryLauncher.class);
        LotteryApp.setLauncher(lotteryLauncher);

        String[] args = {};
        LotteryApp.main(args);
        verify(lotteryLauncher).launch(args);
    }
}
