package com.example.addmicroserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.addmicroserivce","com.example.commonlogic"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AddMicroserivceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddMicroserivceApplication.class, args);
    }

}
