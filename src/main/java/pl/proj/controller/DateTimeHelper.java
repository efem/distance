package pl.proj.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by msz on 23.05.17.
 */
public abstract class DateTimeHelper {
    public static Date convertDateToUTC(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateOutcome= format.parse(dateString);
        return  dateOutcome;
    }


}
