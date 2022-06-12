package com.example.saveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.saveservice","com.example.commonlogic.*"})
@SpringBootApplication
@ConfigurationPropertiesScan("com.example.commonlogic")
@EntityScan("com.example.commonlogic.*")
public class SaveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveServiceApplication.class, args);
    }

}
