package examples.json.util;

import scala.Predef;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Created by wilson on 3/1/17.
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time
        System.out.println(timePoint);
        System.out.println(LocalDate.of(2012, Month.DECEMBER, 12)); // from values
        System.out.println(LocalDate.ofEpochDay(150));  // middle of 1970
        System.out.println(LocalTime.of(17, 18)); // the train I took home today
        System.out.println(LocalTime.parse("10:15:30")); // From a String

        System.out.println("------------Listing 1 --------------");
        LocalDate theDate = timePoint.toLocalDate();
        Month month = timePoint.getMonth();
        int day = timePoint.getDayOfMonth();
        System.out.println(theDate);
        System.out.println(month);
        System.out.println(day);
        System.out.println(timePoint.getSecond());

        System.out.println("------------Listing 2 --------------");
        // Set the value, returning a new object
        LocalDateTime thePast = timePoint.withDayOfMonth(
                10).withYear(2010);

        /* You can use direct manipulation methods, or pass a value and field pair */
        LocalDateTime yetAnother = thePast.plusWeeks(
                3).plus(3, ChronoUnit.WEEKS);
        System.out.println(thePast);
        System.out.println(yetAnother);

        System.out.println("------------Listing 3 --------------");
        System.out.println(timePoint.with(lastDayOfMonth()));
//        System.out.println(timePoint.with(previousOrSame(ChronoUnit.WEDNESDAY)));

        // Using value classes as adjusters
        timePoint.with(LocalTime.now());


        ZoneId id = ZoneId.of("Europe/Paris");
        ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
        System.out.println(id + " ----- " + zoned);

        System.out.println(OffsetTime.now().getOffset());


        System.out.println("-------------------DEMO-------------------");
        System.out.println(startDate("2017-13-03", "+j8:00"));

//        List<String> zoneList = new ArrayList<>(ZoneId.getAvailableZoneIds());
//
//        System.out.println(zoneList);

        System.out.println("=============================================");
        System.out.println();

    }

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String DEFAULT_OFFSET = "+08:00";

    public static String startDate(String date, String offset) {
        String startDayTime = "00:00:00.000";
        String finalOffset= getOffsetOrDefault(offset, DEFAULT_OFFSET);
        String finalDate= getDateOrDefault(date, LocalDate.now().toString());
        return String.format("%s %s%s", finalDate, startDayTime, finalOffset);
    }

    public static String endDate(String date, String offset) {
        String startDayTime = "23:59:59.999";
        String finalOffset= getOffsetOrDefault(offset, DEFAULT_OFFSET);
        String finalDate= getDateOrDefault(date, LocalDate.now().toString());
        return String.format("%s %s%s", finalDate, startDayTime, finalOffset);
    }

    private static String getDateOrDefault(String date, String defaultValue) {
        try {
            LocalDate.parse(date, formatter);
            return date;
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    private static String getOffsetOrDefault(String offset, String defaultValue) {
        try {
            ZoneOffset.of(offset);
            return offset;
        } catch (Exception ex) {
            return  defaultValue;
        }
    }
}
