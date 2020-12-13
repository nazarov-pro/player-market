package com.shahinnazarov.player.utils;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class Constants {
    public static final BigDecimal MONTHLY_EXPERIENCE_AMOUNT =
            BigDecimal.valueOf(100_000);
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final String APP_EXCEPTION_PREFIX = "PLYR";

}
