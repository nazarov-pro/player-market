package com.shahinnazarov.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Time utility functions (wrapper)
 */
public interface TimeUtils {
    long epoch();

    long epochMillis();

    LocalDateTime nowDateTimeAsUTC();

    LocalDate nowDateAsUTC();
}
