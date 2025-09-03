package generics.and.collections;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        printDate(cal);

        cal.add(Calendar.DAY_OF_MONTH, 5);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.YEAR, 1);

        printDate(cal);

        cal.add(Calendar.DAY_OF_MONTH, -20);
        printDate(cal);

        cal.set(2035, Calendar.SEPTEMBER, 9);
        printDate(cal);

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM, yyyy");
        System.out.println(sdf.format(date));

    }

    public static void printDate(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        System.out.println("Current Date & Time: " + day + "/" + month + "/" + year + " " + hour + ":" + minute);
    }
}
