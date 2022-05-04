package com.example.calculator.service;

import com.example.calculator.aop.annotation.Persisting;
import com.example.calculator.util.properties.MicroserviceNetworkProperties;
import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CalculatorService {
    private final OperationMapper mapper;
    private final MicroserviceNetworkProperties prop;

    public CalculatorService(OperationMapper mapper, MicroserviceNetworkProperties prop) {
        this.mapper = mapper;
        this.prop = prop;
    }

    @Persisting
    public Operation requestAndGetOperation(Operation op, String type){
        String path = "";
        String uri = "";
        switch (type) {
            case "add":
                path += prop.getADD_SERVICE_URI();
                System.out.println(path);
                uri = "/add";
                break;
            case "sub":
                path += prop.getSUB_SERVICE_URI();
                uri = "/sub";
                break;
            case "mult":
                path += prop.getMULT_SERVICE_URI();
                uri = "/mult";
                break;
            case "div":
                path += prop.getDIV_SERVICE_URI();
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
