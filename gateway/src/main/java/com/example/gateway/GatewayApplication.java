package com.example.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    private final DiscoveryClient discoveryClient;

    public GatewayApplication(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder rlb){
        return rlb.routes()
                .route(p -> p.path("/*")
                        .filters(rw -> rw.rewritePath("/(?<segment>.*)", "/calc/${segment}"))
                        .uri(serviceUrl("calculator")))
                .build();
    }

    private String serviceUrl(String serviceId) {
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        if (list != null && !list.isEmpty()) {
            return list.get(0).getUri().toString();
        }
        return null;
    }
}
