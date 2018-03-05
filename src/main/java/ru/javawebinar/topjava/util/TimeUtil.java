package ru.javawebinar.topjava.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy в HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
