package com.geo_surveys.geo_surveys_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point.
 */
@SpringBootApplication
@RestController
public class GeoSurveysApiApplication {

    static void main(String[] args) {
        SpringApplication.run(GeoSurveysApiApplication.class, args);
    }
}
