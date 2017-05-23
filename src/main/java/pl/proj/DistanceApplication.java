package pl.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.TimeZone;


@SpringBootApplication
public class DistanceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DistanceApplication.class);
    }

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(DistanceApplication.class, args);


    }
}
