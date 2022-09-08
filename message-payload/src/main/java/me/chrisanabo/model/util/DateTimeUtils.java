package me.chrisanabo.model.util;


import me.chrisanabo.model.constants.Constants;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTimeUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(Constants.DATETIME_FORMAT)
            .withZone(ZoneId.from(ZoneOffset.UTC))
            .withResolverStyle(ResolverStyle.STRICT);;

    public static String getCurrentDateTime() {
        return formatter.format(Clock.systemUTC().instant());
    }

    public static boolean isValidDateTime(String datetime) {
        if (datetime == null) {
            return false;
        }
        try {
            formatter.parse(datetime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
