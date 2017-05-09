package pl.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.proj.dao.RecordDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by msz on 08.05.17.
 */
@RestController
public class RecordController {

    @Autowired
    RecordDao recordDao;

    @RequestMapping("getTest")
    public List<String> getTest() {
        List<String> lista = new ArrayList<>();
        lista.add("aaaa");

        return lista;
    }

    @RequestMapping("putRecord/{distance}")
    public String putRecord(@PathVariable String distance) {
        recordDao.save(new Date(), distance);
        return "OK";
    }
}
