package com.example.addmicroserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.addmicroserivce", "com.example.commonlogic"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class AddMicroserivceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddMicroserivceApplication.class, args);
    }

}
