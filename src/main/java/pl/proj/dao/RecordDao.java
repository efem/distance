package pl.proj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.proj.domain.Record;

import java.util.Date;
import java.util.List;


@Component
public interface RecordDao extends JpaRepository<Record, Long> {
    List<Record> findByDistanceBetween(double from, double to);

    List<Record> findByDateBetween(Date from, Date to);

}
