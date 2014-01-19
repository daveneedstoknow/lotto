package com.lottery;

import com.lottery.parsers.EndDateParser;
import com.lottery.parsers.NumberParser;
import org.joda.time.LocalDate;

import java.io.PrintStream;

/**
 * Job: Understands how to parse the input arguments and launch the game
 */
public class LotteryLauncher {

    public static final String USAGE_MESSAGE = "Usage: Lottery endDate-ddMMyyyy number number number number number number";
    public static final int END_DATE_POSITION = 0;
    public static final int FIRST_NUMBER_POSITION = 1;
    public static final int LAST_NUMBER_POSITION = FIRST_NUMBER_POSITION + NumberSet.DRAW_SIZE;
    private final Game game;
    private final EndDateParser endDateParser;
    private final NumberParser numberParser;
    private final PrintStream out;

    public LotteryLauncher(Game game, EndDateParser endDateParser, NumberParser numberParser,
                           PrintStream out) {

        this.game = game;
        this.endDateParser = endDateParser;
        this.numberParser = numberParser;
        this.out = out;
    }

    public void launch(String... args) {
        if (argumentsAreInvalid(args)) {
            printUsageMessage();
            return;
        }

        LocalDate endDate = endDateParser.parseDate(args[END_DATE_POSITION]);

        int[] parsedNumbers = numberParser.parse(getNumberArguments(args));
        game.run(endDate, new NumberSet(parsedNumbers));
    }

    private boolean argumentsAreInvalid(String[] args) {
        return !hasCorrectNumberOfArguments(args)
                || !endDateParser.isValid(args[END_DATE_POSITION])
                || !numberParser.isValid(getNumberArguments(args));
    }

    private String[] getNumberArguments(String[] args) {
        String[] numberArgs = new String[NumberSet.DRAW_SIZE];
        for (int i = FIRST_NUMBER_POSITION; i < LAST_NUMBER_POSITION; i++) {
            numberArgs[i - 1] = args[i];
        }
        return numberArgs;
    }

    private boolean hasCorrectNumberOfArguments(String[] args) {
        return args.length == LAST_NUMBER_POSITION;
    }

    private void printUsageMessage() {
        out.println(USAGE_MESSAGE);
    }
}
