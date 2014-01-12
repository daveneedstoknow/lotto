package com.lottery;

import com.lottery.parsers.EndDateParser;
import com.lottery.parsers.NumberParser;
import org.joda.time.LocalDate;

public class LotteryApp {

    public static final EndDateParser END_DATE_PARSER = new EndDateParser(new LocalDate());

    public static final Game GAME = new Game();
    // Use manual Dependency Injection - using a DI container seems heavyweight for this application
    private static LotteryLauncher launcher = new LotteryLauncher(GAME, END_DATE_PARSER,
            new NumberParser(), System.out);

    public static void main(String[] args) {
        launcher.launch(args);
    }

    public static void setLauncher(LotteryLauncher launcher) {
        LotteryApp.launcher = launcher;
    }
}
