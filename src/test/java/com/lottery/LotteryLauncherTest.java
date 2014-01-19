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

    public static final String USAGE_MESSAGE = "Usage: Lottery dd/MM/yyyy number number number number number number\n";
    public static final String DATE_ARG = "0";
    public static final String NUMBER_ARG_1 = "1";
    public static final String NUMBER_ARG_2 = "2";
    public static final String NUMBER_ARG_3 = "3";
    public static final String NUMBER_ARG_4 = "4";
    public static final String NUMBER_ARG_5 = "5";
    public static final String NUMBER_ARG_6 = "6";
    public static final String[] NUMBER_ARGS = new String[]{NUMBER_ARG_1, NUMBER_ARG_2, NUMBER_ARG_3, NUMBER_ARG_4, NUMBER_ARG_5, NUMBER_ARG_6};
    public static final String[] CORRECT_NUMBER_OF_ARGS = new String[]{DATE_ARG, NUMBER_ARG_1, NUMBER_ARG_2, NUMBER_ARG_3, NUMBER_ARG_4, NUMBER_ARG_5, NUMBER_ARG_6};
    public static final String[] TOO_FEW_ARGS = new String[]{DATE_ARG, NUMBER_ARG_1, NUMBER_ARG_2, NUMBER_ARG_3, NUMBER_ARG_4, NUMBER_ARG_5};
    public static final String[] TOO_MANY_ARGS = new String[]{DATE_ARG, NUMBER_ARG_1, NUMBER_ARG_2, NUMBER_ARG_3, NUMBER_ARG_4, NUMBER_ARG_5, NUMBER_ARG_6, "7"};
    public static final LocalDate END_DATE = new LocalDate(2014, 8, 1);
    public static final LocalDate FIRST_DRAW_DATE = new LocalDate(2014, 3, 30);
    private Game game;
    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private EndDateParser endDateParser;
    private LotteryLauncher lotteryLauncher;
    private NumberParser numberParser;
    public static final int NUMBER_1 = 10;
    public static final int NUMBER_2 = 20;
    public static final int NUMBER_3 = 30;
    public static final int NUMBER_4 = 40;
    public static final int NUMBER_5 = 50;
    public static final int NUMBER_6 = 60;
    public static final int[] PARSED_NUMBERS = new int[]{NUMBER_1, NUMBER_2, NUMBER_3, NUMBER_4,
            NUMBER_5, NUMBER_6};
    public static final NumberSet NUMBER_SET = new NumberSet(PARSED_NUMBERS);

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
        when(numberParser.isValid((String[]) anyVararg())).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.endDate(any(String.class))).thenReturn(END_DATE);
        when(numberParser.parse((String[]) anyVararg())).thenReturn(PARSED_NUMBERS);
        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);
        verify(endDateParser).endDate(eq(DATE_ARG));
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
        when(numberParser.isValid((String[]) anyVararg())).thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);
        when(endDateParser.endDate(any(String.class))).thenReturn(END_DATE);
        when(numberParser.parse((String[]) anyVararg())).thenReturn(PARSED_NUMBERS);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);

        verify(numberParser).parse(eq(NUMBER_ARG_1), eq(NUMBER_ARG_2), eq(NUMBER_ARG_3), eq(NUMBER_ARG_4),
                eq(NUMBER_ARG_5), eq(NUMBER_ARG_6));
    }

    @Test
    public void shouldReportUsageMessageForInvalidNumberArguments() {
        when(numberParser.isValid(any(String[].class))).thenReturn(false);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);

        assertThat(outputStream.toString(), equalTo(USAGE_MESSAGE));
        outputStream.reset();
    }

    @Test
    public void shouldInitiateGameWithValidArguments() {
        when(numberParser.isValid(eq(NUMBER_ARG_1), eq(NUMBER_ARG_2), eq(NUMBER_ARG_3), eq(NUMBER_ARG_4),
                eq(NUMBER_ARG_5), eq(NUMBER_ARG_6)))
                .thenReturn(true);
        when(endDateParser.isValid(any(String.class))).thenReturn(true);

        when(numberParser.parse(eq(NUMBER_ARG_1), eq(NUMBER_ARG_2), eq(NUMBER_ARG_3), eq(NUMBER_ARG_4),
                eq(NUMBER_ARG_5), eq(NUMBER_ARG_6)))
                .thenReturn(PARSED_NUMBERS);

        when(endDateParser.endDate(eq(DATE_ARG))).thenReturn(END_DATE);
        when(endDateParser.firstDrawDate(eq(DATE_ARG))).thenReturn(FIRST_DRAW_DATE);

        lotteryLauncher.launch(CORRECT_NUMBER_OF_ARGS);


        verify(game).run(eq(FIRST_DRAW_DATE), eq(END_DATE), eq(NUMBER_SET));
    }

}
