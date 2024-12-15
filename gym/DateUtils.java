package gym;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime timeToString (String date){
        return LocalDateTime.parse(date, formatter);
    }
    public static boolean inTheFuture (LocalDateTime dateTime){
        return dateTime.isAfter(LocalDateTime.now());

    }

}
