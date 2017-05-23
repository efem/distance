package pl.proj.helper;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proj.controller.RecordController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by msz on 23.05.17.
 */
public abstract class DateTimeHelper {
    public static Date shiftDate() {

        /*Shifting date by 2h - dirty way*/

        Date newDate = DateUtils.addHours(new Date(), 2);
        return  newDate;
    }


}
