package com.example.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Value("${uri}")
    private String CALC_URI;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder rlb){
        return rlb.routes()
                .route(p -> p.path("/*")
                        .filters(rw -> rw.rewritePath("/(?<segment>.*)", "/calc/${segment}")).uri(CALC_URI))
                .build();
    }
}
