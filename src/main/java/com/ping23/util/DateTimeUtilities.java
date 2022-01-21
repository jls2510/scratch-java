package com.ping23.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeUtilities {

    public static Date getDate(LocalDate date, LocalTime time, ZoneId timeZone) {
        return Date.from(ZonedDateTime.of(date, time, timeZone).toInstant());
    }

    public static Date toStartOfDay(LocalDate date, ZoneId timeZone) {
        final ZoneId zone = timeZone == null ? ZoneId.systemDefault() : timeZone;
        return Date.from(date.atStartOfDay().atZone(zone).toInstant());
    }

    public static Date toEndOfDay(LocalDate date, ZoneId timeZone) {
        final ZoneId zone = timeZone == null ? ZoneId.systemDefault() : timeZone;
        return Date.from(date.atTime(23, 59, 59).atZone(zone).toInstant());
    }

}
