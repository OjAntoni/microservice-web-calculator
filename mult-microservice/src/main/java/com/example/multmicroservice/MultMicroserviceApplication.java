package com.example.multmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.commonlogic", "com.example.multmicroservice"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MultMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultMicroserviceApplication.class, args);
    }

}
