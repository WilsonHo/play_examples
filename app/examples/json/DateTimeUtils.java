package examples.json;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Created by wilson on 3/18/17.
 */
public class DateTimeUtils {
    public static void main(String[] args) {
        executeLocalDateTime();
        System.out.println("executeLocalDateTime##########################################");
        executeZonedDateTime();
        System.out.println("executeZonedDateTime##########################################");
        executeChronoUnitsEnum();
        System.out.println("executeChronoUnitsEnum##########################################");
        executePeriod();
        System.out.println("executePeriod##########################################");
        executeDuration();
        System.out.println("executeDuration##########################################");
        executeTemporalAdjuster();
        System.out.println("executeTemporalAdjuster##########################################");
        executeBackwardCompatability();
        System.out.println("executeBackwardCompatability##########################################");
        getFirstDayOfYear();
        System.out.println("getFirstDayOfYear##########################################");
        getFirstDayOfMonth();
        System.out.println("getFirstDayOfMonth##########################################");
        getFirstDayOfWeek();
        System.out.println("getFirstDayOfWeek##########################################");
        executeInstant();
        System.out.println("executeInstant##########################################");
        getFirstHourOfDay();
        System.out.println("getFirstHourOfDay##########################################");
        getFirstMinuteOfDay();
        System.out.println("getFirstMinuteOfDay##########################################");
        getFirstSecondOfDay();
        System.out.println("getFirstSecondOfDay##########################################");
        formatLocalDateTime();
    }

    private static void executeLocalDateTime() {
        /**
         * LocalDateTime will use timezone from local, so we will can not use
         * this class to get timezone
         */

        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentDateTime);

        LocalDate currentDate = currentDateTime.toLocalDate();
        System.out.println("Current Date: " + currentDate);

        LocalTime currentTime = currentDateTime.toLocalTime();
        System.out.println("Current Time: " + currentTime);

        int yearFromDateTime = currentDateTime.getYear();
        System.out.println("Get year from current DateTime: " + yearFromDateTime);

        Month monthFromDateTime = currentDateTime.getMonth();
        System.out.println("Get month from current DateTime: " + monthFromDateTime);

        int dayOfMonthFromDateTime = currentDateTime.getDayOfMonth();
        System.out.println("Get day from current DateTime: " + dayOfMonthFromDateTime);

        int hourFromDateTime = currentDateTime.getHour();
        System.out.println("Get hour from current DateTime: " + hourFromDateTime);

        int minuteFromDateTime = currentDateTime.getMinute();
        System.out.println("Get minute from current DateTime: " + minuteFromDateTime);

        int secondFromDateTime = currentDateTime.getSecond();
        System.out.println("Get second from current DateTime: " + secondFromDateTime);

        int nanoFromDateTime = currentDateTime.getNano();
        System.out.println("Get nano from current DateTime: " + nanoFromDateTime);

        LocalDateTime customLocalDateTime = currentDateTime.withYear(2012).withDayOfMonth(10);
        System.out.println("Use current DateTime and set month=10 year=2012: " + customLocalDateTime);

        //12 december 2014
        LocalDate createLocalDate = LocalDate.of(2014, Month.JANUARY, 26);
        System.out.println("Create new LocalDate with year=2014 month=01 day=26: " + createLocalDate);

        //22 hour 15 minutes
        LocalTime createLocalTime = LocalTime.of(22, 15);
        System.out.println("Create new LocalTime with hour=22 minute=15: " + createLocalTime);

        //parse a string
        LocalTime parseLocalTimeFromString = LocalTime.parse("20:15:30");
        System.out.println("Parse LocalTime from string `20:15:30` : " + parseLocalTimeFromString);

        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365th day from base date= " + dateFromBase);

        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
        System.out.println("100th day of 2014=" + hundredDay2014);

        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(1000);
        System.out.println("10000th second time= " + specificSecondTime);

    }


    private static void executeZonedDateTime() {
        // Get the current date and time
        ZonedDateTime getZonedFromDateTimeString = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
        System.out.println("Get ZonedDateTime from string: " + getZonedFromDateTimeString);

        ZoneId zoneId = ZoneId.of("Europe/Paris");
        System.out.println("Get ZoneId from string: " + zoneId);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone of system: " + currentZone);

        LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Create current LocalDateTime with timezone: " + todayKolkata);
    }

    public static void executeChronoUnitsEnum() {
        //Get the current date
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current LocalDate: " + currentDate);

        //add 1 week to the current date
        LocalDate nextWeek = currentDate.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);

        //add 1 month to the current date
        LocalDate nextMonth = currentDate.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);

        //add 1 year to the current date
        LocalDate nextYear = currentDate.plus(1, ChronoUnit.YEARS);
        System.out.println("Next year: " + nextYear);

        //add 10 years to the current date
        LocalDate nextDecade = currentDate.plus(1, ChronoUnit.DECADES);
        System.out.println("Date after ten year: " + nextDecade);
    }

    private static void executePeriod() {
        //Get the current date
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current date: " + currentDate);

        //add 1 month to the current date
        LocalDate nextMonth = currentDate.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);

        Period periodNextMonthAndCurrentDate = Period.between(nextMonth, currentDate);
        System.out.println("Period between nextMonth and currentDate: " + periodNextMonthAndCurrentDate);

        Period periodCurrentDateAndNextMonth = Period.between(currentDate, nextMonth);
        System.out.println("Period between currentDate and nextMonth: " + periodCurrentDateAndNextMonth);
    }

    private static void executeDuration() {
        LocalTime currentTime = LocalTime.now();
        Duration twoHours = Duration.ofHours(2);

        LocalTime next2Hours = currentTime.plus(twoHours);

        Duration durationCurrentTimeAndNext2Hours = Duration.between(currentTime, next2Hours);
        System.out.println("Period between next2Hours and currentTime: " + durationCurrentTimeAndNext2Hours);
        Duration durationNext2HoursAndCurrentTime = Duration.between(next2Hours, currentTime);
        System.out.println("Period between currentTime and next2Hours: " + durationNext2HoursAndCurrentTime);
    }

    private static void executeTemporalAdjuster() {
        //Get the current date
        LocalDate currentDate = LocalDate.now();

        //get the next tuesday
        LocalDate nextTuesday = currentDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println("Next Tuesday on : " + nextTuesday);

        //get the second saturday of next month
        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        System.out.println("First Day of Month on: " + firstDayOfMonth);
        LocalDate secondSaturday = firstDayOfMonth
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        System.out.println("Second Saturday on : " + secondSaturday);
    }

    private static void executeBackwardCompatability() {
        Date currentDate = new Date();
        System.out.println("Current date: " + currentDate);

        //Get the instant of current date in terms of milliseconds
        Instant now = currentDate.toInstant();
        ZoneId currentZone = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
        System.out.println("Local date: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
        System.out.println("Zoned date: " + zonedDateTime);
    }

    private static void getFirstDayOfYear() {
        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), Month.JANUARY, 1);
        System.out.println("First Day of year on: " + firstDayOfMonth);
    }

    private static void getFirstDayOfMonth() {
        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        System.out.println("First Day of Month on: " + firstDayOfMonth);
    }

    private static void getFirstDayOfWeek() {
        LocalDate currentDate = LocalDate.now();

        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        System.out.println("First Day of Week on Locale.FRANCE: " + currentDate.with(fieldISO, 1));

        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        System.out.println("First Day of Week on Locale.US: " + currentDate.with(fieldUS, 1));
    }

    private static void getFirstHourOfDay(){
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = " + timestamp);

        LocalDateTime currentDateTimeZone = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(timestamp, ZoneId.of("UTC"));
        System.out.println("Current DateTime from Instant and current timezone: "+ currentDateTimeZone);
        System.out.println("Current DateTime from Instant and time timezone Z: "+ currentDateTime);

        LocalDateTime firstHourOfDay = currentDateTime
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        System.out.println("firstHourOfDay timezone Z: "+ firstHourOfDay);

    }

    private static void getFirstMinuteOfDay(){
        Instant timestamp = Instant.now();

        LocalDateTime currentDateTime = LocalDateTime.ofInstant(timestamp, ZoneId.of("UTC"));

        LocalDateTime firstHourOfDay = currentDateTime
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        System.out.println("getFirstMinuteOfDay timezone Z: "+ firstHourOfDay);
    }

    private static void getFirstSecondOfDay(){
        Instant timestamp = Instant.now();

        LocalDateTime currentDateTime = LocalDateTime.ofInstant(timestamp, ZoneId.of("UTC"));

        LocalDateTime firstHourOfDay = currentDateTime
                .withSecond(0)
                .withNano(0);
        System.out.println("getFirstSecondOfDay timezone Z: "+ firstHourOfDay);
    }

    private static void executeInstant() {
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = " + timestamp);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time toEpochMilli = " + timestamp.toEpochMilli());
        System.out.println("Specific Time = " + specificTime);

        //Duration example
        Duration thirtyDay = Duration.ofDays(30);
        System.out.println(thirtyDay);
    }

    public static void executeConversion() {
        // LocalDate/LocalTime <-> LocalDateTime
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);
        LocalDate dateFromDateTime = LocalDateTime.now().toLocalDate();
        LocalTime timeFromDateTime = LocalDateTime.now().toLocalTime();

        // Instant <-> LocalDateTime
        Instant instant = Instant.now();
        LocalDateTime dateTimeFromInstant = LocalDateTime.ofInstant(instant, ZoneId.of("America/Los_Angeles"));
        Instant instantFromDateTime = LocalDateTime.now().toInstant(ZoneOffset.ofHours(-2));         // convert old date/calendar/timezone classes
        Instant instantFromDate = new Date().toInstant();
        Instant instantFromCalendar = Calendar.getInstance().toInstant();
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        ZonedDateTime zonedDateTimeFromGregorianCalendar = new GregorianCalendar().toZonedDateTime();

        // convert to old classes
        Date dateFromInstant = Date.from(Instant.now());
        TimeZone timeZone = TimeZone.getTimeZone(ZoneId.of("America/Los_Angeles"));
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.now());
    }

    public static void formatLocalDateTime() {
        Instant currentInstant = Instant.now();

        ZoneId defaultZoneId = ZoneId.of("UTC");

        LocalDateTime currentLocalDateTime = LocalDateTime.ofInstant(currentInstant, defaultZoneId);
        System.out.println(currentLocalDateTime);

        Instant currentInstantGetBack = currentLocalDateTime.toInstant(ZoneOffset.UTC);     // convert old date/calendar/timezone classes

        DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date currentDate = Date.from(currentInstantGetBack);
        System.out.println(isoFormat.format(currentDate));
    }
}
