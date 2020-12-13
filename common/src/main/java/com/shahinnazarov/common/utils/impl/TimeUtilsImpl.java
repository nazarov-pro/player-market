package com.shahinnazarov.common.utils.impl;

import com.shahinnazarov.common.utils.TimeUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TimeUtilsImpl implements {@link TimeUtils}
 * Time related functions
 * <a href="https://www.epochconverter.com/">Epoch Converter</a>
 */
public class TimeUtilsImpl implements TimeUtils {
    /**
     * Get epoch(with seconds) version of current time
     *
     * @return long epoch seconds
     */
    @Override
    public long epoch() {
        return Instant.now(Clock.systemUTC()).getEpochSecond();
    }

    /**
     * Get epoch(with milliseconds) version of current time
     *
     * @return long epoch millis
     */
    @Override
    public long epochMillis() {
        return Instant.now(Clock.systemUTC()).toEpochMilli();
    }

    /**
     * Datetime now as UTC
     *
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime nowDateTimeAsUTC() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    /**
     * Date now as UTC
     *
     * @return LocalDate
     */
    @Override
    public LocalDate nowDateAsUTC() {
        return LocalDate.now(Clock.systemUTC());
    }
}
