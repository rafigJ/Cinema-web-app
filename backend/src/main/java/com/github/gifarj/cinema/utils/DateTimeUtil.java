package com.github.gifarj.cinema.utils;

import java.time.LocalDate;

@Deprecated
public class DateTimeUtil {

    /**
     * Checks if a given date falls within a specified date range, including the start and end dates.
     *
     * @param date  The date to check.
     * @param start The start date of the range.
     * @param end   The end date of the range.
     * @return {@code true} if the date is equal to the start or end dates, or if it falls between
     * the start and end dates (inclusive); {@code false} otherwise.
     */
    public static boolean isDateBetweenInclusive(LocalDate date, LocalDate start, LocalDate end) {
        return date.equals(start) || date.equals(end) || (date.isAfter(start) && date.isBefore(end));
    }
}
