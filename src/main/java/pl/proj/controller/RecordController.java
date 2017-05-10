package pl.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.proj.dao.RecordDao;
import pl.proj.domain.Record;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by msz on 08.05.17.
 */
@RestController
public class RecordController {

    static final Logger LOG = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    RecordDao recordDao;

    @RequestMapping("putRecord/{distance:.+|,+}")
    public String putRecord(@PathVariable String distance) {
        recordDao.save(new Record(new Date(), Double.parseDouble(distance.replaceAll(",","."))));
        LOG.info("READ: " + distance + ", " + Double.parseDouble(distance.replaceAll(",",".")));
        return "OK";
    }

    @RequestMapping("getRecords/{from:.+|,+}/{to:.+|,+}")
    public List<Record> getRecordsBetweenDistance(
            @PathVariable String from,
            @PathVariable String to) {

        LOG.info("RECORDS FROM DISTANCE: " + from + ", TO: " + to);

        return recordDao.findByDistanceBetween(
                Double.parseDouble(from.replaceAll(",",".")),
                Double.parseDouble(to.replaceAll(",",".")));
    }

    @RequestMapping("showRecords")
    public List<Record> showRecordsBetweenDistance(
            @RequestParam String from,
            @RequestParam String to) {

        LOG.info("RECORDS FROM DISTANCE: " + from + ", TO: " + to);

        return recordDao.findByDistanceBetween(
                Double.parseDouble(from.replaceAll(",",".")),
                Double.parseDouble(to.replaceAll(",",".")));
    }

    @RequestMapping("showRecordsByDate")
    public List<Record> showRecordsBetweenDate(
            @RequestParam String from,
            @RequestParam String to) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        LOG.info("RECORDS FROM DATE: " + from + ", TO: " + to);
        Date dateFrom = format.parse(from);
        Date dateTo = format.parse(to);
        return recordDao.findByDateBetween(dateFrom, dateTo);
    }
}
