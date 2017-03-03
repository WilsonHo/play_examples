package examples.json.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wilson on 2/28/17.
 */
public class TimeDemo {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z");
        System.out.println("Format 1:   " + dateFormatter.format(now));

        dateFormatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("Format 2:   " + dateFormatter.format(now));

        dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        System.out.println("Format 3:   " + dateFormatter.format(now));

        System.out.println(Calendar.getInstance());

        System.out.println("---------------------------------------");

        // Use Date.toString()
        System.out.println(now);

        // Use DateFormat
        DateFormat formatter = DateFormat.getInstance(); // Date and time
        String dateStr = formatter.format(now);
        System.out.println(dateStr);
        formatter = DateFormat.getTimeInstance();        // time only
        System.out.println(formatter.format(now));

        // Use locale
        formatter = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.ENGLISH);
        System.out.println(formatter.format(now));

        // Use SimpleDateFormat
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println(simpleFormatter.format(now));
    }
}
