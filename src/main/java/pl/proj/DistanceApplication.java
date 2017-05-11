package pl.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.proj.domain.Record;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DistanceApplication extends SpringBootServletInitializer {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DistanceApplication.class);
	}*/



	public static void main(String[] args) {
		SpringApplication.run(DistanceApplication.class, args);

        final String url = "http://www.31337.ovh:8080/distance/getRecords/5/7";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        List<Record> recordList = restTemplate.getForObject(url, List.class);
       String xx = restTemplate.getForObject(url, String.class);

        System.out.println(xx);


	}
}
