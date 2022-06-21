package cs.tntrung.cg.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantUtils {
    private static final String DAYTIME_FORMAT = "HH:mm dd-MM-yyyy";
    private static final String DATE_FORMAT = "HUE,HH:mm ngay dd thang MM nam yyyy";
    public static String instantToStringDayTime(Instant instant) {
        return instantToStringDayTime ( instant, null );
    }
    public static String instantToStringDayTime(Instant instant, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : DAYTIME_FORMAT).withZone( ZoneId.systemDefault());
        return formatter.format(instant);
    }
    public static String instantToStringDate(Instant instant) {
        return instantToStringDate ( instant, null );
    }
    public static String instantToStringDate(Instant instant, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : DATE_FORMAT).withZone( ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
