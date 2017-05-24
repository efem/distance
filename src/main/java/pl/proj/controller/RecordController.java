package pl.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.proj.domain.Record;
import pl.proj.helper.DateTimeHelper;
import pl.proj.service.RecordService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by msz on 08.05.17.
 */
@RestController
public class RecordController {

    static final Logger LOG = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    RecordService recordService;

    @RequestMapping("/")
    public String start() {
        return "<html><b>getAllRecords</b> - getting all records " +
                "<br /><b>getRecordsByDistance/from/to</b> - getting records between from/to distance" +
                "<br /><b>showRecordsByDistance?from=X&to=Y</b> - getting records between X and Y distance" +
                "<br /><b>getRecordsByPage?pageNumber=X&pageSize=Y</b> - getting records with page number = X with page size = Y" +
                "<br /><b>showRecordsByDate?from=X&to=Y</b> - getting records between X and Y dates (format: yyyy-MM-dd_HH-mm-ss)" +
                "<br /><b>putRecord/X</b> - add a record with X distance</html>" ;
    }

    @RequestMapping("getAllRecords")
    public List<Record> getAllRecords() {

        LOG.info("GET ALL RECORDS");

        return recordService.findAll();
    }

    @RequestMapping("getRecordsByDistance/{from:.+|,+}/{to:.+|,+}")
    public List<Record> getRecordsBetweenDistance(
            @PathVariable String from,
            @PathVariable String to) {

        LOG.info("GET RECORDS BETWEEN DISTANCE (by PV): " + from + ", TO: " + to);

        return recordService.findByDistanceBetween(
                Double.parseDouble(from.replaceAll(",", ".")),
                Double.parseDouble(to.replaceAll(",", ".")));
    }

    @RequestMapping("getRecordsByPage")
    public List<Record> getLastThree(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        LOG.info("GET LAST RECORDS (by PV): " + ((pageNumber - 1) * pageSize + 1) + " - " + pageNumber * pageSize);

        Page<Record> page = recordService.getPageOfRecord(pageNumber, pageSize);
        return page.getContent();
    }


    @RequestMapping("showRecordsByDistance")
    public List<Record> showRecordsBetweenDistance(
            @RequestParam String from,
            @RequestParam String to) {

        LOG.info("SHOW RECORDS BETWEEN DISTANCE (by RP): " + from + ", TO: " + to);

        return recordService.findByDistanceBetween(
                Double.parseDouble(from.replaceAll(",", ".")),
                Double.parseDouble(to.replaceAll(",", ".")));
    }


    @RequestMapping("showRecordsByDate")
    public List<Record> showRecordsBetweenDate(
            @RequestParam String from,
            @RequestParam String to) throws ParseException {

        LOG.info("SHOW RECORDS BETWEEN DATES (by RP): " + from + ", TO: " + to);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date dateFrom = format.parse(from);
        Date dateTo = format.parse(to);
        return recordService.findByDateBetween(dateFrom, dateTo);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @RequestMapping("putRecord/{distance:.+|,+}")
    public String putRecord(@PathVariable String distance) throws ParseException {

        try {
            LOG.info("PUT RECORD (by PV): " + new Date() + ", " + distance + ", " +
                    Double.parseDouble(distance.replaceAll(",", ".")));
            /*recordService.save(
                    new Record(DateTimeHelper.shiftDate(),
                    Double.parseDouble(distance.replaceAll(",", "."))));*/
            recordService.save(
                    new Record(new Date(),
                            Double.parseDouble(distance.replaceAll(",", "."))));
            return "OK - " + distance;
        } catch (NumberFormatException nef) {
            LOG.error("ERROR PARSING: " + nef);
            return "ERR - BAD VALUE: " + nef.getMessage();
        }

    }
}
