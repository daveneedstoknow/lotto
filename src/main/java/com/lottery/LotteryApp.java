package com.lottery;

import com.lottery.parsers.EndDateParser;
import com.lottery.parsers.NumberParser;
import org.joda.time.LocalDate;

import java.util.Random;

public class LotteryApp {

    // Use manual Dependency Injection - using a DI container seems heavyweight for this application
    public static final LocalDate TODAYS_DATE = new LocalDate();
    public static final EndDateParser END_DATE_PARSER = new EndDateParser(TODAYS_DATE);
    public static final LotteryMachine LOTTERY_MACHINE = new LotteryMachine(new Random());
    public static final RoundOfGame DRAW_GENERATOR = new RoundOfGame(LOTTERY_MACHINE);
    public static final ResultsPublisher RESULTS_PUBLISHER = new ResultsPublisher(new WinningsCalculator(), new ConsoleResultWriter());
    public static final Game GAME = new Game(DRAW_GENERATOR, RESULTS_PUBLISHER);
    public static final NumberParser NUMBER_PARSER = new NumberParser();
    private static LotteryLauncher launcher = new LotteryLauncher(GAME, END_DATE_PARSER, NUMBER_PARSER, System.out);

    public static void main(String[] args) {
        launcher.launch(args);
    }

    public static void setLauncher(LotteryLauncher launcher) {
        LotteryApp.launcher = launcher;
    }
}
