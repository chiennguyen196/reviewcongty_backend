package com.reviewcongty.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ReviewcongtyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewcongtyBackendApplication.class, args);
    }

}
