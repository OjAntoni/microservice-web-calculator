package com.example.calculator.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "microservice")
@Getter @Setter
public class MicroservicesConnectionProperties {
    private MicroservicesProperties add;
    private MicroservicesProperties sub;
    private MicroservicesProperties mult;
    private MicroservicesProperties div;
}
