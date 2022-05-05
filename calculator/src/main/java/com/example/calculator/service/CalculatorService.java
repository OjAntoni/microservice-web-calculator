package com.example.calculator.service;

import com.example.calculator.aop.annotation.Persisting;
import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CalculatorService {
    private final OperationMapper mapper;
    private final DiscoveryClient discoveryClient;

    public CalculatorService(OperationMapper mapper, DiscoveryClient discoveryClient) {
        this.mapper = mapper;
        this.discoveryClient = discoveryClient;
    }

    @Persisting
    public Operation requestAndGetOperation(Operation op, String type){
        String path = "";
        String method = "";
        switch (type) {
            case "add":
                path += serviceUrl("add-service");
                method = "/add";
                break;
            case "sub":
                path += serviceUrl("sub-service");
                method = "/sub";
                break;
            case "mult":
                path += serviceUrl("mult-service");
                method = "/mult";
                break;
            case "div":
                path += serviceUrl("div-service");
                method = "/div";
                break;
        }
        WebClient webClient = WebClient.create(path);
        return webClient.post().uri(method).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.mapOperationToOperationRequestDto(op))
                .retrieve()
                .bodyToMono(Operation.class)
                .block();
    }

    private String serviceUrl(String serviceId) {
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().toString();
        }
        return null;
    }
}
