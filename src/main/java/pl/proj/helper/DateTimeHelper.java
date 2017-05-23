package pl.proj.helper;

import org.apache.commons.lang3.time.DateUtils;

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
        format.setTimeZone(TimeZone.getTimeZone("CEST"));
        Date dateOutcome= format.parse(dateString);
        return  dateOutcome;
    }

    public static Date getDateInUTC() throws ParseException {
        DateFormat formatCEST = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        formatCEST.setTimeZone(TimeZone.getTimeZone("UTC"));

        DateFormat formatUTC= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        Date dateOutcome= formatUTC.parse(formatCEST.format(new Date()));

        Date newDate = DateUtils.addHours(new Date(), 2);
        return  newDate;
    }


}
