package com.lottery;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Job: Verify that DrawDateSelector selects draws correctly
 */
public class DrawDateSelectorTest {

    private static final LocalDate END_DATE = new LocalDate(2014, 6, 23);
    private static final LocalDate DAY_AFTER_END_DATE = new LocalDate(2014, 6, 24);
    private static final LocalDate A_SUNDAY = new LocalDate(2014, 1, 19);
    private static final LocalDate A_MONDAY = new LocalDate(2014, 1, 20);
    private static final LocalDate WEEK_ON_MONDAY = new LocalDate(2014, 1, 27);
    private static final LocalDate TWO_WEEKS_ON_MONDAY = new LocalDate(2014, 2, 3);
    private DrawDateSelector drawDateSelector;

    @Before
    public void setUp() throws Exception {
        drawDateSelector = new DrawDateSelector();
    }

    @Test
    public void shouldGetStartDateIfItsAMonday() {
        DrawDateSelector drawDateSelector = new DrawDateSelector();

        List<LocalDate> localDates = drawDateSelector.drawDates(A_MONDAY, END_DATE);
        assertThat(localDates.get(0), equalTo(A_MONDAY));
    }

    @Test
    public void shouldNotGetStartDateIfItsANotMonday() {

        List<LocalDate> localDates = drawDateSelector.drawDates(A_SUNDAY, END_DATE);
        assertThat(localDates.get(0), not(equalTo(A_SUNDAY)));
    }

    @Test
    public void shouldGetAllMondaysUpToEndDate() {
        List<LocalDate> localDates = drawDateSelector.drawDates(A_SUNDAY, A_SUNDAY.plusWeeks(3));
        assertThat(localDates.size(), equalTo(3));
        assertThat(localDates.get(0), equalTo(A_MONDAY));
        assertThat(localDates.get(1), equalTo(WEEK_ON_MONDAY));
        assertThat(localDates.get(2), equalTo(TWO_WEEKS_ON_MONDAY));
    }

    @Test
    public void shouldIncludeMondayEndDate() {
        List<LocalDate> localDates = drawDateSelector.drawDates(A_MONDAY, A_MONDAY.plusWeeks(1));
        assertThat(localDates.size(), equalTo(2));

        assertThat(localDates.get(0), equalTo(A_MONDAY));
        assertThat(localDates.get(1), equalTo(WEEK_ON_MONDAY));
    }


}