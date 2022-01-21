package com.ping23.scratch.util;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * <p> Title: com.managementworlds.common.util.WeekdayUtilities </p> <p>
 * Description: </p> <p> Copyright: Copyright (c) 2003 </p> <p> Company:
 * Management Worlds, Inc. </p>
 * 
 * @author David C. Browne
 * @author James L. Stevenson
 */

public class WeekdayUtilities
{

    /**
     * the number of milliseconds in a day
     */
    static public final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

    /**
     * number of regular days in a week
     */
    static public final int DAYS_PER_WEEK = 7;

    /**
     * number of weekdays in a week
     */
    static public final int WEEKDAYS_PER_WEEK = 5;

    /**
     * number of weekend days in a week
     */
    static public final int WEEKEND_DAYS_PER_WEEK = 2;

    /**
     * A constant for the number of weeks in a month (abstract).
     */
    static public final int WEEKS_PER_MONTH = 4;

    /**
     * fixed integral number of months in a year
     */
    public static final int MONTHS_PER_YEAR = 12;

    /**
     * number of weeks in a year, based on fixed weeks per month and fixed months
     * per year
     */
    public static final int WEEKS_PER_YEAR = WEEKS_PER_MONTH * MONTHS_PER_YEAR;

    /**
     * A constant for the last workday of the week.
     */
    static public final int LAST_WORKDAY_OF_WEEK = Calendar.FRIDAY;

    /**
     * the hour of noon
     */
    static private final int NOON = 12;

    /**
     * start of day is 8:00am
     */
    public static final int DAY_START_HOUR = 8;

    /**
     * end of day is 5:00pm
     */
    public static final int DAY_END_HOUR = 17;

    /**
     * lunch starts at 12:00pm
     */
    public static final int LUNCH_START_HOUR = 12;

    /**
     * lunch lasts an hour
     */
    public static final int LUNCH_DURATION = 1;

    /**
     * the number of days to go from saturday to monday
     */
    static private final int SATURDAY_TO_MONDAY = 2;

    /**
     * the number of days to go from sunday to monday
     */
    static private final int SUNDAY_TO_MONDAY = 1;

    /**
     * the number of days to go from tuesday to monday
     */
    static private final int TUESDAY_TO_MONDAY = 6;

    /**
     * the number of days to go from wednesday to monday
     */
    static private final int WEDNESDAY_TO_MONDAY = 5;

    /**
     * the number of days to go from thursday to monday
     */
    static private final int THURSDAY_TO_MONDAY = 4;

    /**
     * the number of days to go from friday to monday
     */
    static private final int FRIDAY_TO_MONDAY = 3;

    /**
     * the number of days to go from friday to saturday
     */
    static private final int FRIDAY_TO_SATURDAY = 1;

    /**
     * the number of days to go from friday to sunday
     */
    static private final int FRIDAY_TO_SUNDAY = 2;

    /**
     * a timezone with the current raw offset and no daylight savings time
     */
    static private final SimpleTimeZone NO_DST_TZ = new SimpleTimeZone(TimeZone.getDefault().getRawOffset(),
            "NO_DST_TZ");


    /**
     * set the time of day to be noon
     * 
     * @param cal is the date to clear out the time for
     */
    static private void setTimeToNoon(Calendar cal)
    {
        // check the arg
        if (cal == null)
            throw new IllegalArgumentException("calendar argument is null");

        cal.set(Calendar.HOUR_OF_DAY, NOON);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    } // setTimeToNoon()


    /**
     * Get int Day of Week for a given Date
     * 
     * @param date is the Date for which to return the Day of Week
     * @return int Day of Week
     */
    static public int getDayOfWeek(Date date)
    {
        return getCalendar(date).get(Calendar.DAY_OF_WEEK);
    }


    /**
     * Return a Calendar object equivalent to the given Date object
     * 
     * @param date
     * @return the Calendar object
     */
    static public Calendar getCalendar(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    /**
     * set the time of day to be noon with the timezone having no DST, leaving
     * only the day, month, and year intact
     * 
     * @param date is the date to clear out the time for
     * @return the Calendar representing the cleared date
     */
    static public Calendar XgetClearCalendar(Date date)
    {
        // check the arg
        if (date == null)
            throw new IllegalArgumentException("date argument is null");

        // we want all the dates that we use to have the same time of day and
        // no time zone
        Calendar cal = Calendar.getInstance(NO_DST_TZ);
        cal.setTime(date);
        setTimeToNoon(cal);

        return cal;
    } // getClearCalendar()


    /**
     * set the time of day to be noon with the timezone having no DST, leaving
     * only the day, month, and year intact -- based on current day
     * 
     * @return the Calendar representing the cleared date
     */
    static public Calendar XgetClearCalendar()
    {
        // we want all the dates that we use to have the same time of day and
        // no time zone
        Calendar cal = Calendar.getInstance(NO_DST_TZ);
        setTimeToNoon(cal);

        return cal;
    } // getClearCalendar()


    /**
     * get the next possible weekday if the date is on the weekend
     * 
     * @param date is the date we are interested in
     * @return the next possible weekday if the date is on the weekend
     */
    static public Calendar ceiling(Date date)
    {
        // check the arg
        if (date == null)
            throw new IllegalArgumentException("date argument is null");

        // get calendar version of date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        // adjust if calendar on weekend
        switch (dayOfWeek)
        {
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, SATURDAY_TO_MONDAY);
            break;
        case Calendar.SUNDAY:
            cal.add(Calendar.DATE, SUNDAY_TO_MONDAY);
            break;
        default:
            // do nothing for the weekdays
        }

        return cal;
    } // ceiling()


    /**
     * get the previous possible weekday if the date is on the weekend
     * 
     * @param date is the date we are interested in
     * @return the previous possible weekday if the date is on the weekend
     */
    static public Calendar floor(Date date)
    {
        // check the arg
        if (date == null)
            throw new IllegalArgumentException("date argument is null");

        // get calendar version of date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        // adjust if calendar on weekend
        switch (dayOfWeek)
        {
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, -FRIDAY_TO_SATURDAY);
            break;
        case Calendar.SUNDAY:
            cal.add(Calendar.DATE, -FRIDAY_TO_SUNDAY);
            break;
        default:
            // do nothing for the weekdays
        }

        return cal;
    } // floor()


    /**
     * get the number of weekdays *between* the dates
     * 
     * @param startDate is the first day of the interval
     * @param endDate is the last day of the interval
     * @return the number of weekdays *between* the dates
     */
    static public int interval(Date startDate, Date endDate)
    {
        // make sure that start and end date are on weekdays
        Calendar start = ceiling(startDate);
        Calendar end = floor(endDate);

        // sanity check
        if (end.before(start))
        {
            System.out.println("End date " + end.getTime() + " is before start date " + start.getTime());
            return 0;
            // throw new IllegalArgumentException("End date " + end.getTime()
            // + " is before start date " + start.getTime());
        }

        long milliDifference = end.getTimeInMillis() - start.getTimeInMillis();
        int dayDifference = (int) (milliDifference / MILLIS_PER_DAY);

        // number of weeks in the interval
        int weekDifference = dayDifference / DAYS_PER_WEEK;

        // this is the number of days left after removing complete weeks from the
        // interval -- must be less than number of days in a week
        int daysOfWeekRemainder = dayDifference % DAYS_PER_WEEK;

        // get the number of weekdays in the non-full week interval -- we know
        // that the variables 'start' and 'end' are weekdays -- we assume that
        // the weekdays are contiguous integers in the Calendar class
        // (Calendar.MONDAY + 4 == Calendar.FRIDAY, etc) -- so if the start date
        // is later in the week then the end date, there is a weekend between them
        // that we need to discount -- if they are the same day of the week, then
        // the remainder would be zero and we don't have to do anything here
        if (start.get(Calendar.DAY_OF_WEEK) > end.get(Calendar.DAY_OF_WEEK))
            daysOfWeekRemainder -= WEEKEND_DAYS_PER_WEEK;

        return weekDifference * WEEKDAYS_PER_WEEK + daysOfWeekRemainder;
    } // interval()


    /**
     * get the number of weekdays that span the dates, inclusive
     * 
     * @param startDate is the first day of the span
     * @param endDate is the last day of the span
     * @return the number of weekdays span the dates, inclusive
     */
    static public int duration(Date startDate, Date endDate)
    {
        return interval(startDate, endDate) + 1;
    } // duration()


    /**
     * Find the weekday that is numWeekdays ahead of date -- if date falls on the
     * weekend, push it up to the next weekday
     * 
     * @param date is the date to add weekdays to
     * @param numDays is the number of days to add to the date
     * @return the day date that is numDays ahead of date
     */
    static public Date addDays(Date date, int numDays)
    {
        // check the args
        if (date == null)
        {
            throw new IllegalArgumentException("date argument is null");
        }
        else if (numDays < 0)
        {
            // return subtractWeekdays(date, -numWeekdays);
        }
        else if (numDays == 0)
        {
            return date;
        }

        // make sure date is a weekday, moving it forward if necessary
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // add days
        cal.add(Calendar.DATE, numDays);

        return cal.getTime();

    } // addDays()


    /**
     * Find the weekday that is numWeekdays ahead of date -- if date falls on the
     * weekend, push it up to the next weekday
     * 
     * @param date is the date to add weekdays to
     * @param numWeekdays is the number of weekdays to add to the date
     * @return the weekday that is numWeekdays ahead of date
     */
    static public Date addWeekdays(Date date, int numWeekdays)
    {
        // check the args
        if (date == null)
        {
            throw new IllegalArgumentException("date argument is null");
        }
        else if (numWeekdays < 0)
        {
            return subtractWeekdays(date, -numWeekdays);
        }
        else if (numWeekdays == 0)
        {
            return date;
        }

        // make sure date is a weekday, moving it forward if necessary
        Calendar cal = ceiling(date);
        int weeks = numWeekdays / WEEKDAYS_PER_WEEK;
        int weekdayRemainder = numWeekdays % WEEKDAYS_PER_WEEK;

        // if adding the remainder of days to the calendar's day of the week
        // puts us beyond Friday, then we need to add the weekend to make sure
        // that we end up on a weekday -- we assume that the weekdays are
        // contiguous integers in the Calendar class (Calendar.MONDAY + 4 ==
        // Calendar.FRIDAY, etc)
        if (cal.get(Calendar.DAY_OF_WEEK) + weekdayRemainder > Calendar.FRIDAY)
            weekdayRemainder += WEEKEND_DAYS_PER_WEEK;

        // calc the new calendar date
        cal.add(Calendar.DATE, weeks * DAYS_PER_WEEK + weekdayRemainder);

        return cal.getTime();
    } // addWeekdays()


    /**
     * Lightweight method to return the next day's Date for any given Date.
     * 
     * @param date is the base Date for which we want the next day's Date
     * @return the next day's Date object
     */
    static public Date getNextDate(Date date)
    {
        // check the args
        if (date == null)
            throw new IllegalArgumentException("date argument is null");

        // get calendar version of date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DATE, 1);

        // make sure date is a weekday, moving it forward if necessary
        Calendar newCal = ceiling(cal.getTime());

        return newCal.getTime();

    } // getNextDate()


    /**
     * find the weekday that is numWeekdays behind date -- if date falls on the
     * weekend, push it back to the previous weekday
     * 
     * @param date is the date to subtract weekdays from
     * @param numWeekdays is the number of weekdays to subtract from the date
     * @return the weekday that is numWeekdays behind date
     */
    static public Date subtractWeekdays(Date date, int numWeekdays)
    {
        // check the args
        if (date == null)
            throw new IllegalArgumentException("date argument is null");
        else if (numWeekdays < 0)
            throw new IllegalArgumentException("numWeekdays argument is less than 0: " + String.valueOf(numWeekdays));

        // make sure date is a weekday, moving it backward if necessary
        Calendar cal = floor(date);
        int weeks = numWeekdays / WEEKDAYS_PER_WEEK;
        int weekdayRemainder = numWeekdays % WEEKDAYS_PER_WEEK;

        // if subtracting the remainder of days to the calendar's day of the
        // week puts us before Monday, then we need to add the weekend to make
        // sure that we end up on a weekday -- we assume that the weekdays are
        // contiguous integers in the Calendar class (Calendar.MONDAY + 4 ==
        // Calendar.FRIDAY, etc)
        if (cal.get(Calendar.DAY_OF_WEEK) - weekdayRemainder < Calendar.MONDAY)
            weekdayRemainder += WEEKEND_DAYS_PER_WEEK;

        // calc the new calendar date
        cal.add(Calendar.DATE, -(weeks * DAYS_PER_WEEK + weekdayRemainder));

        return cal.getTime();
    } // subtractWeekdays()


    /**
     * get the first Monday date of the corresponding work week
     * 
     * @param date is what we want the prev Monday for
     * @return the prev Monday given a date (or given date if it is already
     *         Monday)
     */
    static public Date getMondayFloor(Date date)
    {
        // get calendar version of date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        switch (cal.get(Calendar.DAY_OF_WEEK))
        {
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, SATURDAY_TO_MONDAY - DAYS_PER_WEEK);
            break;
        case Calendar.SUNDAY:
            cal.add(Calendar.DATE, SUNDAY_TO_MONDAY - DAYS_PER_WEEK);
            break;
        case Calendar.TUESDAY:
            cal.add(Calendar.DATE, TUESDAY_TO_MONDAY - DAYS_PER_WEEK);
            break;
        case Calendar.WEDNESDAY:
            cal.add(Calendar.DATE, WEDNESDAY_TO_MONDAY - DAYS_PER_WEEK);
            break;
        case Calendar.THURSDAY:
            cal.add(Calendar.DATE, THURSDAY_TO_MONDAY - DAYS_PER_WEEK);
            break;
        case Calendar.FRIDAY:
            cal.add(Calendar.DATE, FRIDAY_TO_MONDAY - DAYS_PER_WEEK);
            break;
        case Calendar.MONDAY:
        default:
            // do nothing
            // there are no other days of the week
        }

        return cal.getTime();
    } // getMondayFloor()


    /**
     * get the first Monday date of the following full work week
     * 
     * @param date is what we want the next Monday for
     * @return the next Monday given a date (or given date if it is already
     *         Monday)
     */
    static public Date getMondayCeiling(Date date)
    {
        // get calendar version of date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        switch (cal.get(Calendar.DAY_OF_WEEK))
        {
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, SATURDAY_TO_MONDAY);
            break;
        case Calendar.SUNDAY:
            cal.add(Calendar.DATE, SUNDAY_TO_MONDAY);
            break;
        case Calendar.TUESDAY:
            cal.add(Calendar.DATE, TUESDAY_TO_MONDAY);
            break;
        case Calendar.WEDNESDAY:
            cal.add(Calendar.DATE, WEDNESDAY_TO_MONDAY);
            break;
        case Calendar.THURSDAY:
            cal.add(Calendar.DATE, THURSDAY_TO_MONDAY);
            break;
        case Calendar.FRIDAY:
            cal.add(Calendar.DATE, FRIDAY_TO_MONDAY);
            break;
        case Calendar.MONDAY:
        default:
            // do nothing
            // there are no other days of the week
        }

        return cal.getTime();
    } // getMondayCeiling()


    /**
     * get the previous Friday relative to the given date
     * 
     * @param date is what we want the prev Friday for
     * @return the prev Friday given a date (or return the given date if it is
     *         already Friday)
     */
    public static Date getPreviousFriday(Date date)
    {
        // sanity check
        if (date == null)
        {
            throw new IllegalArgumentException("date must not be null");
        }

        // get calendar version of date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Date previousFriday = date;

        // sun-thu
        if (cal.get(Calendar.DAY_OF_WEEK) < Calendar.FRIDAY)
        {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); // advances to FRIDAY
            // of the same week
            cal.add(Calendar.WEEK_OF_YEAR, -1); // retreat to previous FRIDAY
            previousFriday = cal.getTime();
        }
        // sat
        else if (cal.get(Calendar.DAY_OF_WEEK) > Calendar.FRIDAY)
        {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); // retreats to FRIDAY
            // of the same week
            previousFriday = cal.getTime();
        }

        return previousFriday;
    } // getPreviousFriday()


    /**
     * get the week number for the given currentDate (startingDate week is week
     * 1, any date before startingDate will return week 0).
     * 
     * @param startingDate is the starting date of the interval
     * @param currentDate is the date to find the week number for
     * @return the week number for the given currentDate
     */
    public static int oldGetWeekNumber(Date startingDate, Date currentDate)
    {
        // sanity check
        if (startingDate == null)
        {
            throw new IllegalArgumentException("startingDate must not be null");
        }
        if (currentDate == null)
        {
            throw new IllegalArgumentException("currentDate must not be null");
        }

        int returnValue = 0;
        Date adjustedCurrentDate = WeekdayUtilities.getMondayFloor(currentDate);

        // if adjustedCurrentDate is before start date, we will return 0, else
        // calculate the week number of adjustedCurrentDate, numbering starts at 1
        if (!adjustedCurrentDate.before(startingDate))
        {
            // get number of weekdays between startingDate and adjustedCurrentDate
            int weekDays = WeekdayUtilities.interval(startingDate, adjustedCurrentDate);

            // days is even multiple of weeks, since start and end are both Monday
            returnValue = weekDays / WeekdayUtilities.WEEKDAYS_PER_WEEK + 1;
        }

        return returnValue;
    } // getWeekNumber()


    /**
     * get the weeknumber of the offset date w.r.t. the base date, where
     * the base date is in week 1 -- returns 0 if offsetDate is in a week
     * before baseDate
     * 
     * @param baseDate is the base date
     * @param offsetDate is the offset date
     * @return the week number of the offset date, where baseDate is in week
     *         1
     */
    public static int getWeekNumber(Date baseDate, Date offsetDate)
    {
        // sanity check
        if (baseDate == null)
        {
            throw new IllegalArgumentException("basedate must not be null");
        }
        if (offsetDate == null)
        {
            throw new IllegalArgumentException("offsetDate must not be null");
        }

        int weekNumber = getWeekOffset(baseDate, offsetDate) + 1;

        // System.out.println("WeekdayUtilities.getWeekNumber()");
        // System.out.println("weekNumber = " + weekNumber);

        return Math.max(weekNumber, 0);
    } // getWeekNumber()


    /**
     * get the number of weeks between two dates
     * DOES NOT include the week in which baseDate occurs
     * DOES include the week in which offsetDate occurs
     * 
     * @param baseDate first date
     * @param offsetDate second date
     * @return number of weeks between two dates, not inclusive
     */
    private static int getWeekOffset(Date baseDate, Date offsetDate)
    {
        // sanity check
        if (baseDate == null)
        {
            throw new IllegalArgumentException("basedate must not be null");
        }
        if (offsetDate == null)
        {
            throw new IllegalArgumentException("offsetDate must not be null");
        }

        // Period period =
        // new Period(WeekdayUtilities.getMondayFloor(baseDate).toDateMidnight(),
        // WeekdayUtilities.getMondayFloor(offsetDate).toDateMidnight(),
        // PeriodType.weeks());

        // return period.getWeeks();

        Calendar baseCal = getCalendar(baseDate);
        Calendar offsetCal = getCalendar(offsetDate);
        int baseYear = baseCal.get(Calendar.YEAR);
        int offsetYear = offsetCal.get(Calendar.YEAR);
        int baseWeek = baseCal.get(Calendar.WEEK_OF_YEAR);
        int offsetWeek = offsetCal.get(Calendar.WEEK_OF_YEAR);

        // if it's the last partial week of the year java.util.Calendar will return 1.
        // we want this to return the next sequential week number, so fix this;
        // So... if it's December and offsetWeek is 1 then set to next sequential week number
        if (offsetCal.get(Calendar.MONTH) == 11 && offsetWeek == 1)
        {
            int maxWeekNumber = offsetCal.getActualMaximum(Calendar.WEEK_OF_YEAR);
            offsetWeek = maxWeekNumber + 1;
        }

        // System.out.println("***** WeekdayUtilities.getWeekOffset(): "
        // + "baseYear.baseWeek.baseDay = " + baseYear + "." + baseWeek + "." + baseDay
        // + "offsetYear.offsetWeek.offsetDay = " + offsetYear + "." + offsetWeek + "." + offsetDay
        // );

        // the return value
        int weekOffset;

        // if the years are different then normalize
        if (baseYear == offsetYear)
        {
            // calculate the value
            weekOffset = offsetWeek - baseWeek;
        }
        else
        {
            // how many weeks in the first year
            int firstYearWeeks = baseCal.getActualMaximum(Calendar.WEEK_OF_YEAR) - baseWeek;

            // how many weeks in the intervening years
            int interveningYearWeeks = 0;
            if (offsetYear - baseYear > 1)
            {
                Calendar tmpCal = Calendar.getInstance();
                for (int interveningYear = baseYear + 1; interveningYear < offsetYear; ++interveningYear)
                {
                    tmpCal.set(interveningYear, 0, 1);
                    interveningYearWeeks += tmpCal.getActualMaximum(Calendar.WEEK_OF_YEAR);
                }
            }

            // add 'em up
            weekOffset = firstYearWeeks + interveningYearWeeks + offsetWeek;

        }

        // System.out.println("***** WeekdayUtilities.getWeekOffset(): "
        // + "\n modified baseYear.baseWeek = " + baseYear + "." + baseWeek
        // + "\n modified offsetYear.offsetWeek = " + offsetYear + "." + offsetWeek
        // + "\n weekOffset = " + weekOffset
        // );

        return weekOffset;
    } // getWeekOffset()


    // assert: startDate must be before endDate
    @SuppressWarnings("unused")
    private static long daysBetween(Date startDate, Date endDate)
    {

        Calendar date = getCalendar(startDate);
        Calendar end = getCalendar(endDate);
        long daysBetween = 0;
        while (date.before(end))
        {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }


    /**
     * Get the Monday Date given a weekNumber. Date returned is for the Monday of
     * the given week. If weekNumber is 0 or less, returns startDate.
     * 
     * @param weekNumber is the week number to find the date for
     * @param startDate is the starting date of the period
     * @return the date given a week number
     */
    public static Date getMondayDateForWeekNumber(Date startDate, int weekNumber)
    {
        Date returnValue = null;

        if (weekNumber > 0)
        {
            // startDate is rigged to be a Monday
            // if we add full weeks then we will again be on a Monday
            int daysToAdd = (weekNumber - 1) * WEEKDAYS_PER_WEEK;
            returnValue = addWeekdays(startDate, daysToAdd);
        }
        else
        {
            returnValue = startDate;
        }

        return returnValue;
    } // getMondayDateForWeekNumber()


    /**
     * Get the Friday Date given a weekNumber. Date returned is for the Friday of
     * the given week. If weekNumber is 0 or less, returns startDate.
     * 
     * @param weekNumber is the week number to find the date for
     * @param startDate is the starting date of the period
     * @return the date given a week number
     */
    public static Date getFridayDateForWeekNumber(Date startDate, int weekNumber)
    {
        Date returnValue = null;

        if (weekNumber > 0)
        {
            // startDate is rigged to be a Monday
            // if we add full weeks then we will again be on a Monday
            int daysToAdd = (weekNumber - 1) * WEEKDAYS_PER_WEEK;
            // add 4 to get to Friday
            daysToAdd += 4;
            returnValue = addWeekdays(startDate, daysToAdd);
        }
        else
        {
            returnValue = startDate;
        }

        return returnValue;
    } // getFridayDateForWeekNumber()


    /**
     * get the beginning week number of the 4-week month which includes the given
     * week number.
     * 
     * @param thisWeekNumber is the week number for which we want the beginning
     *            week number of the 4-week month
     * @return the beginning week number of the 4-week month which includes the
     *         given week number.
     */
    public static int getFirstWeekNumberOfMonth(int thisWeekNumber)
    {
        // how many full months have passed?
        int fullMonthsPassed = thisWeekNumber / 4;
        // if it's the last week of the month then we need to back up 1
        if (thisWeekNumber % 4 == 0)
            --fullMonthsPassed;
        return (fullMonthsPassed * 4) + 1;
    } // getThisMonthStartWeek()


    /**
     * Get the day name for the given day number
     * 
     * @param date is the date for which we want the day name
     * @return the day name
     */
    public static String getDayNameForDate(Date date)
    {
        String[] weekdayNames = new DateFormatSymbols().getWeekdays();

        int dayNumber = getDayOfWeek(date);

        return weekdayNames[dayNumber];
    } // getDayNameForDayNumber()


    /**
     * Get the month number of a reference date. Assumes the baseDate is in month
     * 1 (simulationStartDate) Returns 0 if offsetDate is in a month before
     * baseDate
     * 
     * @param baseDate
     * @param offsetDate is the date in question
     * @return the month number of the reference date.
     */
    public static int getMonthNumber(Date baseDate, Date offsetDate)
    {
        Calendar baseCal = Calendar.getInstance();
        baseCal.setTime(baseDate);
        Calendar offsetCal = Calendar.getInstance();
        offsetCal.setTime(offsetDate);

        int yearDifference = offsetCal.get(Calendar.YEAR) - baseCal.get(Calendar.YEAR);
        int monthDifference = offsetCal.get(Calendar.MONTH) - baseCal.get(Calendar.MONTH);

        int monthNumber = (yearDifference * 12) + monthDifference + 1;

        return Math.max(monthNumber, 0);
    }


    /**
     * get the date that represents the start of a given workday
     * 
     * @param referenceDate is the Date for which we want the corresponding value
     * @return the date that represents the start of a given workday
     */
    public static Date getDayStart(Date referenceDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDate);

        cal.set(Calendar.HOUR_OF_DAY, DAY_START_HOUR);

        return cal.getTime();
    } // getDayStart()


    /**
     * get the date that represents the end of a given workday
     * 
     * @param referenceDate is the Date for which we want the corresponding value
     * @return the date that represents the end of a given workday
     */
    public static Date getDayEnd(Date referenceDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDate);

        cal.set(Calendar.HOUR_OF_DAY, DAY_END_HOUR);

        return cal.getTime();
    } // getDayEnd()


    /**
     * if it is Friday, get the following Sunday, else just return the date
     * @param referenceDate is the date to check
     * @return the possibly modified date
     */
    public static Date addWeekendIfFriday(final Date referenceDate)
    {

        Date returnDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDate);

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
        {
            // returnDate = referenceDate.plus(Period.days(2));
            cal.add(Calendar.DAY_OF_WEEK, 2);
            returnDate = cal.getTime();
        }
        else
        {
            returnDate = referenceDate;
        }

        // System.out.println();
        // System.out.println(returnDate);

        return returnDate;

    } // addWeekendIfFriday()


    /**
     * main function for testing
     * 
     * @param args String[]
     */
    @SuppressWarnings("unused")
    static public void main(String[] args)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 28);

        MWDateFormatter defFmt = MWDateFormatter.getInstance(MWDateFormatter.DEFAULT_PATTERN);
        System.out.println("cal.set(2011, 10, 28) yields: " + defFmt.format(cal.getTime()));

        System.out.println("day of week = " + cal.get(Calendar.DAY_OF_WEEK));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println("Setting DAY_OF_WEEK to SUNDAY yields: " + defFmt.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("Setting DAY_OF_WEEK to MONDAY yields: " + defFmt.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        System.out.println("Setting DAY_OF_WEEK to FRIDAY yields: " + defFmt.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        System.out.println("Setting DAY_OF_WEEK to SATURDAY yields: " + defFmt.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        System.out.println("Setting DAY_OF_WEEK to FRIDAY yields: " + defFmt.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("Setting DAY_OF_WEEK to MONDAY yields: " + defFmt.format(cal.getTime()));

        // do week number calculations
        cal.set(2011, 0, 3);
        Date date1 = cal.getTime();
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(2011, 4, 19);
        Date date2 = cal.getTime();
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        System.out.println("Date 1: " + defFmt.format(date1));
        System.out.println("Day of year for Date 1: " + day1);
        System.out.println("Date 2: " + defFmt.format(date2));
        System.out.println("Day of year for Date 2: " + day2);
        System.out.println("WeekOffset for Date2 relative to Date1: " + getWeekOffset(date1, date2));
        System.out.println("WeekNumber for Date2 relative to Date1: " + getWeekNumber(date1, date2));

        // test some methods
        Date startDate;

        // test getWeekOfYear()
        if (false)
        {
            System.out.println("\n\n");
            cal.set(2000, 0, 3); // Monday of first week of year
            startDate = cal.getTime();
            for (int year = 2000; year <= 2015; ++year)
            {
                for (int month = 0; month < 12; ++month)
                {
                    Calendar tempCal = Calendar.getInstance();
                    tempCal.set(year, month, 1);
                    int daysInMonth = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    for (int day = 1; day <= daysInMonth; ++day)
                    {
                        cal.set(year, month, day);
                        System.out.println("getWeekOfYear for date: " + cal.getTime() + " yields: "
                                + cal.get(Calendar.WEEK_OF_YEAR));
                    }
                }
            }
        }

        if (false)
        {
            // test getWeekOffset()
            int startYear = 2004;
            int startMonth = 9;
            int startDay = 13;
            cal.set(startYear, startMonth, startDay);

            // normalize to Monday of the following week
            startDate = WeekdayUtilities.getMondayCeiling(cal.getTime()); // Monday of first full week following
            cal.setTime(startDate);
            startMonth = cal.get(Calendar.MONTH);
            startDay = cal.get(Calendar.DAY_OF_MONTH);
            // note the initial values depend on the above
            int assertWeekNumber = 1;
            int weekDayCounter = 1; // init to Sunday; will increment weeks by counting days

            for (int year = startYear; year <= startYear + 20; ++year)
            {
                for (int month = startMonth; month < 12; ++month)
                {
                    // reset startMonth for following iterations
                    startMonth = 0;
                    
                    // how many days in this month?
                    Calendar tempCal = Calendar.getInstance();
                    tempCal.set(year, month, 1);
                    int daysInMonth = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    for (int day = startDay; day <= daysInMonth; ++day)
                    {
                        // reset startDay for following iterations
                        startDay = 1;
                        
                        cal.set(year, month, day);
                        System.out.println("getWeekOffset for date: " + cal.getTime() + " yields: "
                                + getWeekOffset(startDate, cal.getTime()));
                    }
                }
            }
        }

        // test getWeekNumber()
        if (true)
        {
            System.out.println("\n\n");
            int startYear = 2004;
            int startMonth = 9;
            int startDay = 13;
            cal.set(startYear, startMonth, startDay);
            
            // normalize to Monday of the following week
            startDate = WeekdayUtilities.getMondayCeiling(cal.getTime()); // Monday of first full week following
            cal.setTime(startDate);
            startMonth = cal.get(Calendar.MONTH);
            startDay = cal.get(Calendar.DAY_OF_MONTH);
            // note the initial values depend on the above
            int assertWeekNumber = 1;
            int weekDayCounter = 1; // init to Sunday; will increment weeks by counting days

            for (int year = startYear; year <= startYear + 20; ++year)
            {
                for (int month = startMonth; month < 12; ++month)
                {
                    // reset startMonth for following iterations
                    startMonth = 0;
                    
                    // how many days in this month?
                    Calendar tempCal = Calendar.getInstance();
                    tempCal.set(year, month, 1);
                    int daysInMonth = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    for (int day = startDay; day <= daysInMonth; ++day)
                    {
                        // reset startDay for following iterations
                        startDay = 1;
                        
                        cal.set(year, month, day);
                        System.out.println("getWeekNumber for date: " + cal.getTime() + " yields: "
                                + getWeekNumber(startDate, cal.getTime()));
                    }
                }
            }
        }

    }

} // class WeekdayUtilities
