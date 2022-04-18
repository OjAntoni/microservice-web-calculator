package com.example.divmicroservice.web.controller;

import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.web.dto.OperationRequestDto;
import com.example.divmicroservice.service.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/div")
public class DivisionController {
    private final OperationMapper mapper;
    private final DivisionService service;

    public DivisionController(OperationMapper mapper, DivisionService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Operation> calculate(@RequestBody OperationRequestDto operationRequestDto){
        Operation operation = mapper.mapOperationRequestDtoToOperation(operationRequestDto);
        return new ResponseEntity<>(service.calc(operation), HttpStatus.OK);
    }
}
