package pl.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.proj.dao.RecordDao;
import pl.proj.domain.Record;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public Optional<Record> findById(Long id) {
        return recordDao.findById(id);
    }

    @Override
    public List<Record> findAll() {
        return recordDao.findAll();
    }

    @Override
    public Page<Record> getPageOfRecord(int pageNumber, int pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "id");
        return recordDao.findAll(request);
    }

    @Override
    public Record save(Record record) {
        return recordDao.save(record);
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
