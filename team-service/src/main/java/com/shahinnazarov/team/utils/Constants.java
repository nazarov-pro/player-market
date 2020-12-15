package com.shahinnazarov.team.utils;

import java.time.format.DateTimeFormatter;

/**
 * Contains constant/static variables
 */
public class Constants {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final String APP_EXCEPTION_PREFIX = "TEAM";
    public static final String PATH_TEAM_MOCK_CSV_FILE = "teams.csv";

}
