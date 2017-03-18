package examples.json.util.java8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by wilson on 3/2/17.
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now); // 2017-03-02T15:10:11.997Z

        System.out.println(now.getEpochSecond());

        Instant tomorrow = now.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow); // 2017-03-02T15:10:11.997Z
        System.out.println(now.plus(1, ChronoUnit.NANOS));
        System.out.println(now.plus(1, ChronoUnit.MICROS));
        System.out.println(now.plus(1, ChronoUnit.MILLIS));
        System.out.println(now.plus(1, ChronoUnit.SECONDS));
        System.out.println(now.plus(1, ChronoUnit.MINUTES));
        System.out.println(now.plus(1, ChronoUnit.HOURS));
        System.out.println(now.plus(1, ChronoUnit.HALF_DAYS));
        System.out.println(now.plus(1, ChronoUnit.DAYS));
//        System.out.println(now.plus(1, ChronoUnit.WEEKS));
//        System.out.println(now.plus(1, ChronoUnit.MONTHS));
//        System.out.println(now.plus(1, ChronoUnit.YEARS));
//        System.out.println(now.plus(1, ChronoUnit.CENTURIES));
//        System.out.println(now.plus(1, ChronoUnit.DECADES));
//        System.out.println(now.plus(1, ChronoUnit.DECADES));
//        System.out.println(now.plus(1, ChronoUnit.ERAS));
//        System.out.println(now.plus(1, ChronoUnit.FOREVER));

        Instant yesterday = now.minus(1,ChronoUnit.HALF_DAYS);
        System.out.println(yesterday);

        System.out.println(now.compareTo(tomorrow));
        System.out.println(now.isAfter(yesterday));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("--------------------------------------");
        System.out.println(localDateTime);
        System.out.println(localDateTime.atZone(ZoneId.of("America/New_York")));

        System.out.println(DayOfWeek.from(localDateTime));
        System.out.println(localDateTime.get(ChronoField.DAY_OF_WEEK));
//        System.out.println(localDateTime.get(ChronoField.OFFSET_SECONDS));

        System.out.println(localDateTime.get(ChronoField.DAY_OF_YEAR));

        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());

        System.out.println(LocalDateTime.of(2014, 10, 1, 10, 0));

        LocalDateTime parsedLocalDateTime = LocalDateTime.parse("2014-01-01T11:00");
        System.out.println(parsedLocalDateTime);
        System.out.println("------------1--------------");
        System.out.println(LocalDateTime.now(ZoneId.of("UTC")));

        System.out.println(LocalDateTime.ofInstant(now, ZoneId.of("UTC")));

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println( );

        System.out.println(zonedDateTime.until(ZonedDateTime.parse("2017-03-02T15:29:27.460+07:00"), ChronoUnit.HOURS));

        System.out.println(zonedDateTime.getOffset());

        System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("'The' dd 'day of' MMM 'in year' YYYY 'and zone is' z")));

        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.toInstant());
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("America/Chicago")));
        System.out.println(zonedDateTime.withZoneSameLocal(ZoneId.of("America/Chicago")));


        LocalDateTime ldt = LocalDateTime.ofInstant(now, ZoneOffset.UTC);

        DateFormat FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
            Date dateKey = Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());

        System.out.println("LocalDateTime ---> Date ::::: " + FORMAT_YYYYMMDD.format(dateKey));
    }
}
