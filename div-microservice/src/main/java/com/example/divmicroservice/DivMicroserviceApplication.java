package com.example.divmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.commonlogic", "com.example.divmicroservice"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DivMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DivMicroserviceApplication.class, args);
    }

}
