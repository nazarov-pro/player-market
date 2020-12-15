package com.shahinnazarov.common.utils;

import com.shahinnazarov.common.utils.impl.TimeUtilsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class TimeUtilsTest {
    private TimeUtils timeUtils;

    @BeforeEach
    public void setUp() {
        timeUtils = new TimeUtilsImpl();
    }

    @Test
    void testEpoch() {
        long epoch = timeUtils.epoch();
        Assertions.assertTrue(epoch > 0);
    }

    @Test
    void testEpochMillis() {
        long epochMillis = timeUtils.epochMillis();
        Assertions.assertTrue(epochMillis > 0);
    }

    @Test
    void testNowDateAsUTC() {
        LocalDate date = timeUtils.nowDateAsUTC();
        Assertions.assertNotNull(date);
    }

    @Test
    void testNowDateTimeAsUTC() {
        LocalDateTime dateTime = timeUtils.nowDateTimeAsUTC();
        Assertions.assertNotNull(dateTime);
    }

}
