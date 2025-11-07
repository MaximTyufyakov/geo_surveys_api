package com.geo_surveys.geo_surveys_api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GeoSurveysApiApplication {
    public static void main(String[] args) {
      SpringApplication.run(GeoSurveysApiApplication.class, args);
    }
    // @Value("${spring.datasource.url}")
    // private String dbPassword;

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
}