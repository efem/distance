package pl.proj.controller;

import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.proj.domain.Record;
import pl.proj.service.RecordService;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@Slf4j
public class RecordController {

    @Autowired
    RecordService recordService;

    @GetMapping("/record/{id}")
    public ResponseEntity<?> getRecord(@PathVariable String id) {
        log.info("GET RECORD (with ID): {}", id);

        return recordService.findById(Long.parseLong(id))
                .map(record -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Long.toString(record.getId()))
                                .location(new URI("/record/" + record.getId()))
                                .body(record);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllRecords")
    Iterable<Record> getAllRecords() {
        log.info("GET ALL RECORDS");
        return recordService.findAll();
    }

    @GetMapping("/getRecordsByDistance/{from:.+|,+}/{to:.+|,+}")
    List<Record> getRecordsBetweenDistance(
            @PathVariable String from,
            @PathVariable String to) {

        log.info("GET RECORDS BETWEEN DISTANCE (by PV): " + from + ", TO: " + to);

        return recordService.findByDistanceBetween(
                Double.parseDouble(from.replaceAll(",", ".")),
                Double.parseDouble(to.replaceAll(",", ".")));
    }

    @GetMapping("/getRecordsByPage")
    Iterable<Record> getLastThree(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        log.info("GET LAST RECORDS (by PV): " + ((pageNumber - 1) * pageSize + 1) + " - " + pageNumber * pageSize);

        Page<Record> page = recordService.getPageOfRecord(pageNumber, pageSize);
        return page.getContent();
    }


    @GetMapping("/showRecordsByDistance")
    Iterable<Record> showRecordsBetweenDistance(
            @RequestParam String from,
            @RequestParam String to) {

        log.info("SHOW RECORDS BETWEEN DISTANCE (by RP): " + from + ", TO: " + to);

        return recordService.findByDistanceBetween(
                Double.parseDouble(from.replaceAll(",", ".")),
                Double.parseDouble(to.replaceAll(",", ".")));
    }


    @GetMapping("/showRecordsByDate")
    Iterable<Record> showRecordsBetweenDate(
            @RequestParam String from,
            @RequestParam String to) throws ParseException {

        log.info("SHOW RECORDS BETWEEN DATES (by RP): " + from + ", TO: " + to);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date dateFrom = format.parse(from);
        Date dateTo = format.parse(to);
        return recordService.findByDateBetween(dateFrom, dateTo);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleAppException(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PutMapping("/addrecord/{distance:.+|,+}")
    public ResponseEntity<Record> putRecord(@PathVariable String distance) {
        double parsedDistance = Double.parseDouble(distance.replaceAll(",", "."));
        log.info("PUT RECORD (by PV): " + new Date() + ", " + distance + ", " + parsedDistance);

        Record newRecord = recordService.save(new Record(parsedDistance));
        try {
            return ResponseEntity
                    .created(new URI("/record/" + String.valueOf(newRecord.getId())))
                    .eTag(String.valueOf(newRecord.getId()))
                    .body(newRecord);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
