package com.ats.rtem.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static LocalDateTime parseStartDateToLocalDateTime(String dateStr) {
        if (dateStr == null) return null;
        try {
            // Try parsing as ISO date-time
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            try {
                // If it's just a date (e.g., "2025-04-27"), parse and set time to midnight
                return LocalDate.parse(dateStr).atStartOfDay();
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format. Supported formats: yyyy-MM-dd or yyyy-MM-dd'T'HH:mm:ss");
            }
        }
    }

    public static LocalDateTime parseEndDateToLocalDateTime(String dateStr) {
        if (dateStr == null) return null;
        try {
            // Try parsing as ISO date-time
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            try {
                // If it's just a date, set the time to the end of the day (23:59:59.999999)
                return LocalDate.parse(dateStr).atTime(LocalTime.MAX);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format. Supported formats: yyyy-MM-dd or yyyy-MM-dd'T'HH:mm:ss");
            }
        }
    }
}

