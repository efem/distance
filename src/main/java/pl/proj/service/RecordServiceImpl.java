package pl.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.proj.dao.RecordDao;
import pl.proj.domain.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by Marek on 09.05.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public List<Record> findAll() {
        return recordDao.findAll();
    }

    @Override
    public Record save(Record record) {
        return recordDao.save(record);
    }

    @Override
    public Record getRecordById(Long id) {
        return recordDao.findOne(id);
    }

    @Override
    public List<Record> findByDistanceBetween(double from, double to) {
        return recordDao.findByDistanceBetween(from, to);
    }

    @Override
    public List<Record> findByDateBetween(Date from, Date to) {
        return recordDao.findByDateBetween(from, to);
    }
}
