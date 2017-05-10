package pl.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.proj.dao.RecordDao;
import pl.proj.domain.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
