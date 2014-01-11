package com.lottery;

public class LotteryApp {

    private static LotteryLauncher launcher = new LotteryLauncher();

    public static void main(String [] args) {
        launcher.launch(args);
    }

    public static void setLauncher(LotteryLauncher launcher) {
        LotteryApp.launcher = launcher;
    }
}
