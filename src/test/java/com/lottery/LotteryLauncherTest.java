package com.lottery;

import com.lottery.parsers.EndDateParser;
import com.lottery.parsers.NumberParser;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class LotteryLauncherTest {

    public static final String USAGE_MESSAGE = "Usage: Lottery endDate-ddMMyyyy number number number number number number\n";
    public static final String DATE_ARG = "0";
    public static final String NUMBER_1 = "1";
    public static final String NUMBER_2 = "2";
    public static final String NUMBER_3 = "3";
    public static final String NUMBER_4 = "4";
    public static final String NUMBER_5 = "5";
    public static final String NUMBER_6 = "6";
    public static final String[] CORRECT_NUMBER_OF_ARGS = new String[]{DATE_ARG, NUMBER_1, NUMBER_2, NUMBER_3, NUMBER_4, NUMBER_5, NUMBER_6};
    public static final String[] TOO_FEW_ARGS = new String[]{DATE_ARG, NUMBER_1, NUMBER_2, NUMBER_3, NUMBER_4, NUMBER_5};
    public static final String[] TOO_MANY_ARGS = new String[]{DATE_ARG, NUMBER_1, NUMBER_2, NUMBER_3, NUMBER_4, NUMBER_5, NUMBER_6, "7"};
    private Game game;
    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private EndDateParser endDateParser;
    private LotteryLauncher lotteryLauncher;
    private NumberParser numberParser;

    @Before
    public void setUp() throws Exception {
        endDateParser = mock(EndDateParser.class);
        game = mock(Game.class);
        outputStream = new ByteArrayOutputStream();
        out = new PrintStream(outputStream);
        numberParser = mock(NumberParser.class);
        lotteryLauncher = new LotteryLauncher(game, endDateParser, numberParser, out);
    }

    @Test
    public void shouldParseDateAsFirstArgument() {
        when(numberParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);
        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);
        verify(endDateParser).parseDate(eq(DATE_ARG));
    }

    @Test
    public void shouldRejectInvalidDateArguments() {

        when(numberParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(false);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);

        assertThat(outputStream.toString(), equalTo(USAGE_MESSAGE));
    }

    @Test
    public void shouldRejectInvalidNumberOfArguments() {

        when(numberParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);

        lotteryLauncher.launch(TOO_FEW_ARGS);

        assertThat(outputStream.toString(), equalTo(USAGE_MESSAGE));
        outputStream.reset();

        lotteryLauncher.launch(TOO_MANY_ARGS);

        assertThat(outputStream.toString(), equalTo(USAGE_MESSAGE));
        outputStream.reset();
    }

    @Test
    public void shouldValidateAllNumberArguments() {
        when(numberParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);

        verify(numberParser).isValid(eq(NUMBER_1));
        verify(numberParser).isValid(eq(NUMBER_2));
        verify(numberParser).isValid(eq(NUMBER_3));
        verify(numberParser).isValid(eq(NUMBER_4));
        verify(numberParser).isValid(eq(NUMBER_5));
        verify(numberParser).isValid(eq(NUMBER_6));
    }

    @Test
    public void shouldReportUsageMessageForInvalidNumberArguments() {
        when(numberParser.isValid(any(String.class))).thenReturn(false);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);

        assertThat(outputStream.toString(), equalTo(USAGE_MESSAGE));
        outputStream.reset();
    }

    @Test
    public void shouldInitiateGameWithValidArguments() {
        when(numberParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);

        int expected_1 = 10;
        int expected_2 = 20;
        int expected_3 = 30;
        int expected_4 = 40;
        int expected_5 = 50;
        int expected_6 = 60;
        int[] expected_numbers = {expected_1, expected_2, expected_3, expected_4,
                expected_5, expected_6};
        when(numberParser.parse(eq(NUMBER_1))).thenReturn(expected_1);
        when(numberParser.parse(eq(NUMBER_2))).thenReturn(expected_2);
        when(numberParser.parse(eq(NUMBER_3))).thenReturn(expected_3);
        when(numberParser.parse(eq(NUMBER_4))).thenReturn(expected_4);
        when(numberParser.parse(eq(NUMBER_5))).thenReturn(expected_5);
        when(numberParser.parse(eq(NUMBER_6))).thenReturn(expected_6);

        LocalDate expectedDate = new LocalDate();
        when(endDateParser.parseDate(eq(DATE_ARG))).thenReturn(expectedDate);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);
        verify(game).signUp(expectedDate, expected_numbers);
    }

}
