package com.example.calculator.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@Getter @Setter
public class MicroserviceNetworkProperties {
    @Value("${ADD_SERVICE_URI}")
    private String ADD_SERVICE_URI;
    @Value("${SUB_SERVICE_URI}")
    private String SUB_SERVICE_URI;
    @Value("${DIV_SERVICE_URI}")
    private String DIV_SERVICE_URI;
    @Value("${MULT_SERVICE_URI}")
    private String MULT_SERVICE_URI;
}
