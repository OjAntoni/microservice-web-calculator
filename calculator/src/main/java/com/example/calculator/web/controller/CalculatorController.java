package com.example.calculator.web.controller;

import com.example.calculator.service.OperationService;
import com.example.calculator.util.MicroservicesConnectionProperties;
import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.web.dto.OperationRequestDto;
import com.example.commonlogic.web.dto.OperationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/calc")
public class CalculatorController {
    private final OperationService service;
    private final OperationMapper mapper;
    private final MicroservicesConnectionProperties microservicesConnectionProperties;

    public CalculatorController(OperationService service, OperationMapper mapper, MicroservicesConnectionProperties microservicesConnectionProperties) {
        this.service = service;
        this.mapper = mapper;
        this.microservicesConnectionProperties = microservicesConnectionProperties;
    }

    @PostMapping("/{type}")
    public ResponseEntity<OperationResponseDto> calc(@RequestBody OperationRequestDto operationRequestDto, @PathVariable String type) {
        String path = "http://";
        String uri = "";
        switch (type) {
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
        Operation response = webClient.post().uri(uri).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(operationRequestDto)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();
        service.doSmth(response);
        return new ResponseEntity<>(mapper.mapOperationToOperationResponseDto(response), HttpStatus.OK);

    }

}
