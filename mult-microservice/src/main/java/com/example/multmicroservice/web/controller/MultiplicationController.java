package com.example.multmicroservice.web.controller;

import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.web.dto.OperationRequestDto;
import com.example.multmicroservice.service.MultiplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mult")
public class MultiplicationController {
    private final OperationMapper mapper;
    private final MultiplicationService service;

    public MultiplicationController(OperationMapper mapper, MultiplicationService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Operation> calculate(@RequestBody OperationRequestDto operationRequestDto){
        Operation operation = mapper.mapOperationRequestDtoToOperation(operationRequestDto);
        return new ResponseEntity<>(service.calc(operation), HttpStatus.OK);
    }
}
