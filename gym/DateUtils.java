package gym;

import gym.management.Sessions.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDateTime timeToString (String date){
        return LocalDateTime.parse(date, formatter);
    }
    public static LocalDate stringToDate (String date ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate realDate;
        try {
            realDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date string. Expected format: dd-MM-yyyy");
        }
        return realDate;
    }


    public static boolean isInTheFuture (LocalDateTime dateTime){
        return dateTime.isAfter(LocalDateTime.now());

    }

}
