package com.ping23.util;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestWeekdayUtilities
{

    @Test
    public void testGetMondayCeiling()
    {
        // testing set - known values
        // 6/12/97 should return 6/16/97
        // 6/23/97 should return 6/23/97
        // 7/29/09 should return 8/3/09
        // 12/30/09 should return 1/4/10
        // 10/10/12 should return 10/15/12
        // 10/13/16 should return 10/17/16
        // 11/14/16 should return 11/14/16
        // 12/20/16 should return 12/26/16
        // 1/7/17 should return 1/9/17
        // 12/6/17 should return 12/11/17
        // 9/3/19 should return 9/9/19

        int[][] dates =
            new int[][] { { 6, 12, 1997, 6, 16, 1997 },
                { 6, 23, 1997, 6, 23, 1997 }, { 7, 29, 2009, 8, 3, 2009 },
                { 12, 30, 2009, 1, 4, 2010 }, { 10, 10, 2012, 10, 15, 2012 },
                { 10, 13, 2016, 10, 17, 2016 }, { 11, 14, 2016, 11, 14, 2016 },
                { 12, 20, 2016, 12, 26, 2016 }, { 1, 7, 2017, 1, 9, 2017 },
                { 12, 6, 2017, 12, 11, 2017 }, { 9, 3, 2019, 9, 9, 2019 } };

        for (int i = 0; i < dates.length; i++)
        {
            Calendar cal = Calendar.getInstance();
            int referenceMonth = dates[i][0] - 1; // month is zero-based
            int referenceDay = dates[i][1];
            int referenceYear = dates[i][2];
            cal.set(referenceYear, referenceMonth, referenceDay);
            Date referenceDate = cal.getTime();

            int correctMonth = dates[i][3] - 1; // month is zero-based
            int correctDay = dates[i][4];
            int correctYear = dates[i][5];
            cal.set(correctYear, correctMonth, correctDay);
            Date correctDate = cal.getTime();

            // get the computed value
            Date computedDate =
                WeekdayUtilities.getMondayCeiling(referenceDate);

            //System.out.println("referenceDate = " + referenceDate);
            //System.out.println("trueMondayCeiling = " + trueMondayCeiling);
            //System.out.println("computedMondayCeiling = "
            //    + computedMondayCeiling);

            assertEquals(correctDate, computedDate);

        }
    } // testGetMondayCeiling()

    @Test
    public void testAddDays()
    {
        int[][] dates =
            new int[][] {
                { 11,27,2017,12,23,2017,26 },
                { 12,2,2017,12,9,2017,7 },
                { 12,27,2017,1,3,2018,7 },
                { 11,26,2017,1,5,2018,40 }
            };

        for (int i = 0; i < dates.length; i++)
        {
            Calendar cal = Calendar.getInstance();
            int referenceMonth = dates[i][0] - 1; // month is zero-based
            int referenceDay = dates[i][1];
            int referenceYear = dates[i][2];
            cal.set(referenceYear, referenceMonth, referenceDay);
            Date referenceDate = cal.getTime();

            int correctMonth = dates[i][3] - 1; // month is zero-based
            int correctDay = dates[i][4];
            int correctYear = dates[i][5];
            cal.set(correctYear, correctMonth, correctDay);
            Date correctDate = cal.getTime();
            
            int numDays = dates[i][6];

            // get the computed value
            Date computedDate =
                WeekdayUtilities.addDays(referenceDate, numDays);

            //System.out.println("referenceDate = " + referenceDate);
            //System.out.println("correctDate = " + correctDate);
            //System.out.println("computedDate = "
            //    + computedDate);

            assertEquals(correctDate, computedDate);

        }
        
    } // testAddDays()
    
    @Test
    public void testAddWeekdays()
    {
        int[][] dates =
            new int[][] {
                { 11,27,2017,12,28,2017,23 },
                { 12,2,2017,12,13,2017,7 },
                { 12,27,2017,1,5,2018,7 },
                { 11,26,2017,1,8,2018,30 }
            };

        for (int i = 0; i < dates.length; i++)
        {
            Calendar cal = Calendar.getInstance();
            int referenceMonth = dates[i][0] - 1; // month is zero-based
            int referenceDay = dates[i][1];
            int referenceYear = dates[i][2];
            cal.set(referenceYear, referenceMonth, referenceDay);
            Date referenceDate = cal.getTime();

            int correctMonth = dates[i][3] - 1; // month is zero-based
            int correctDay = dates[i][4];
            int correctYear = dates[i][5];
            cal.set(correctYear, correctMonth, correctDay);
            Date correctDate = cal.getTime();
            
            int numDays = dates[i][6];

            // get the computed value
            Date computedDate =
                WeekdayUtilities.addWeekdays(referenceDate, numDays);

            System.out.println("referenceDate = " + referenceDate);
            System.out.println("correctDate = " + correctDate);
            System.out.println("computedDate = "
                + computedDate);

            assertEquals(correctDate, computedDate);

        }
        
    } // testAddWeekdays()
    
    @Test
    public void testGetMondayFloor()
    {
        // testing set - known values
        int[][] dates =
            new int[][] {
                {6,11,2009,6,8,2009},
                {9,19,2009,9,14,2009},
                {10,12,2009,10,12,2009},
                {12,4,2009,11,30,2009},
                {1,2,2013,12,31,2012},
                {3,1,2012,2,27,2012},
                {8,24,2004,8,23,2004},
                {1,4,2002,12,31,2001},
                {3,1,1997,2,24,1997},
                {3,1,1996,2,26,1996}
        };

        for (int i = 0; i < dates.length; i++)
        {
            Calendar cal = Calendar.getInstance();
            int referenceMonth = dates[i][0] - 1; // month is zero-based
            int referenceDay = dates[i][1];
            int referenceYear = dates[i][2];
            cal.set(referenceYear, referenceMonth, referenceDay);
            Date referenceDate = cal.getTime();

            int correctMonth = dates[i][3] - 1; // month is zero-based
            int correctDay = dates[i][4];
            int correctYear = dates[i][5];
            cal.set(correctYear, correctMonth, correctDay);
            Date correctDate = cal.getTime();

            // get the computed value
            Date computedDate =
                WeekdayUtilities.getMondayFloor(referenceDate);

            //System.out.println("referenceDate = " + referenceDate);
            //System.out.println("correctDate = " + correctDate);
            //System.out.println("computedDate = "
            //    + computedDate);

            assertEquals(correctDate, computedDate);

        }
    } // testGetMondayFloor()

    @Test
    public void testGetWeekNumber()
    {
        Calendar cal = Calendar.getInstance();
        int startYear = 2004;
        int startMonth = 9;
        int startDay = 13;
        cal.set(startYear, startMonth, startDay);

        // normalize to Monday of the following week
        Date startDate = WeekdayUtilities.getMondayCeiling(cal.getTime()); // Monday
                                                                           // of
                                                                           // first
                                                                           // full
                                                                           // week
                                                                           // following
        cal.setTime(startDate);
        startMonth = cal.get(Calendar.MONTH);
        startDay = cal.get(Calendar.DAY_OF_MONTH);
        // note the initial values depend on the above
        int assertWeekNumber = 1;
        int weekDayCounter = 1; // init to Sunday; will increment weeks by
                                // counting days

        for (int year = startYear; year <= startYear + 20; ++year)
        {
            for (int month = startMonth; month < 12; ++month)
            {
                // reset startMonth for following iterations
                startMonth = 0;

                // how many days in this month?
                Calendar tempCal = Calendar.getInstance();
                tempCal.set(year, month, 1);
                int daysInMonth =
                    tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int day = startDay; day <= daysInMonth; ++day)
                {
                    // reset startDay for following iterations
                    startDay = 1;

                    // increment day
                    ++weekDayCounter;

                    // if we've counted to day 8 then increment week number and
                    // start over
                    if (weekDayCounter == 8)
                    {
                        ++assertWeekNumber;
                        weekDayCounter = 1;
                    }

                    cal.set(year, month, day);
                    // System.out.println("getWeekNumber for date: " +
                    // cal.getTime() + " yields: "
                    // + WeekdayUtilities.getWeekNumber(startDate,
                    // cal.getTime()));
                    // System.out.println("weekDayCounter for same date equals: "
                    // + weekDayCounter);
                    // System.out.println("assertWeekNumber for same date yields: "
                    // + assertWeekNumber);
                    assertEquals(
                        assertWeekNumber,
                        WeekdayUtilities.getWeekNumber(startDate, cal.getTime()));
                }
            }
        }

    }

    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestWeekdayUtilities.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }

    }

}
