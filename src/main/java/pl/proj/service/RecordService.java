package pl.proj.service;

import org.springframework.data.domain.Page;
import pl.proj.domain.Record;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RecordService {
    Optional<Record> findById(Long id);

    List<Record> findAll();

    Page<Record> getPageOfRecord(int pageNumber, int pageSize);

    Record save (Record record);

    List <Record> findByDistanceBetween (double from, double to);

    List <Record> findByDateBetween (Date from, Date to);

}
