package pl.proj.helper;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public abstract class DateTimeHelper {
    public static Date shiftDate() {

        /*Shifting date by 2h - dirty way*/
        Date newDate = DateUtils.addHours(new Date(), 2);
        return  newDate;
    }


}
