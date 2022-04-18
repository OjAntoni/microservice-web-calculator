package com.example.submicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.commonlogic", "com.example.submicroservice"})
@SpringBootApplication
public class SubMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubMicroserviceApplication.class, args);
    }

}
