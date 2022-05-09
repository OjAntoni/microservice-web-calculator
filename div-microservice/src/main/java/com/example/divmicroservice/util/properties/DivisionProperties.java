package com.example.divmicroservice.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Getter @Setter
public class DivisionProperties {
    @Value("${precision}")
    private int precision;
}
