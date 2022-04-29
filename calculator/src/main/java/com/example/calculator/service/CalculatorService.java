package com.example.calculator.service;

import com.example.calculator.aop.annotation.Persisting;
import com.example.calculator.util.MicroservicesConnectionProperties;
import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CalculatorService {
    private final OperationMapper mapper;
    private final MicroservicesConnectionProperties microservicesConnectionProperties;

    public CalculatorService(OperationMapper mapper, MicroservicesConnectionProperties microservicesConnectionProperties) {
        this.mapper = mapper;
        this.microservicesConnectionProperties = microservicesConnectionProperties;
    }

    @Persisting
    public Operation requestAndGetOperation(Operation op, OperationType type){
        String path = "http://";
        String uri = "";
        switch (type.toString()) {
            case "add":
                path += microservicesConnectionProperties.getAdd().getHost() + ":";
                path += microservicesConnectionProperties.getAdd().getPort();
                uri = "/add";
                break;
            case "sub":
                path += microservicesConnectionProperties.getSub().getHost() + ":";
                path += microservicesConnectionProperties.getSub().getPort();
                uri = "/sub";
                break;
            case "mult":
                path += microservicesConnectionProperties.getMult().getHost() + ":";
                path += microservicesConnectionProperties.getMult().getPort();
                uri = "/mult";
                break;
            case "div":
                path += microservicesConnectionProperties.getDiv().getHost() + ":";
                path += microservicesConnectionProperties.getDiv().getPort();
                uri = "/div";
                break;
        }
        WebClient webClient = WebClient.create(path);
        return webClient.post().uri(uri).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.mapOperationToOperationRequestDto(op))
                .retrieve()
                .bodyToMono(Operation.class)
                .block();
    }
}
