package pl.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.TimeZone;


@SpringBootApplication
@ComponentScan
public class DistanceApplication {

/*    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DistanceApplication.class);
    }*/

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(DistanceApplication.class, args);


    }
}
