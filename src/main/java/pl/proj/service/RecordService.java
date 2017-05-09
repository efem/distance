package pl.proj.service;

import pl.proj.domain.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by msz on 09.05.17.
 */
public interface RecordService {
    List<Record> findAll();

    Record save (Record record);

    Record save (Date date, String distance);

    Record getRecordById (Long id);

    List <Record> getRecordsForDistance (double from, double to);

}
