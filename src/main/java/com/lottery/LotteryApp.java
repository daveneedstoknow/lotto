package com.lottery;

import com.lottery.parsers.EndDateParser;
import com.lottery.parsers.NumberParser;
import org.joda.time.LocalDate;

import java.util.Random;

/*
* Job: The main entry point, assembles the dependencies and launches the application
 */
public class LotteryApp {

    // Use manual Dependency Injection - using a DI container seemed heavyweight for this application
    public static final LocalDate TODAYS_DATE = new LocalDate();
    public static final EndDateParser END_DATE_PARSER = new EndDateParser(TODAYS_DATE);
    public static final LotteryMachine LOTTERY_MACHINE = new LotteryMachine(new Random());
    public static final ResultsPublisher RESULTS_PUBLISHER = new ResultsPublisher(System.out);
    public static final WinningsCalculator WINNINGS_CALCULATOR = new WinningsCalculator();

    public static final RoundOfGame DRAW_GENERATOR = new RoundOfGame(LOTTERY_MACHINE, RESULTS_PUBLISHER, WINNINGS_CALCULATOR);
    public static final Game GAME = new Game(DRAW_GENERATOR);
    public static final NumberParser NUMBER_PARSER = new NumberParser();
    private static LotteryLauncher launcher = new LotteryLauncher(GAME, END_DATE_PARSER, NUMBER_PARSER, System.out);

    public static void main(String[] args) {
        launcher.launch(args);
    }

    public static void setLauncher(LotteryLauncher launcher) {
        LotteryApp.launcher = launcher;
    }
}
