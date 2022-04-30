package com.example.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.Entity;

@EnableAspectJAutoProxy
@ComponentScan({"com.example.calculator.*","com.example.commonlogic.*"})
@EntityScan("com.example.commonlogic.*")
@SpringBootApplication
@ConfigurationPropertiesScan({"com.example.calculator.util","com.example.commonlogic"})
public class CalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }

}
