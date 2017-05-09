package pl.proj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msz on 08.05.17.
 */
@RestController
public class RecordController {

    @RequestMapping("getTest")
    public List<String> getTest() {
        List<String> lista = new ArrayList<>();
        lista.add("aaaa");

        return lista;
    }
}
