package com.shahinnazarov.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeUtils {
    long epoch();

    long epochMillis();

    LocalDateTime nowDateTimeAsUTC();

    LocalDate nowDateAsUTC();
}
