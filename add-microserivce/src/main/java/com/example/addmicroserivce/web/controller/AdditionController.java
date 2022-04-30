package com.example.addmicroserivce.web.controller;

import com.example.addmicroserivce.service.AdditionService;
import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.web.dto.OperationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class AdditionController {
    private final AdditionService service;
    private final OperationMapper mapper;

    public AdditionController(AdditionService service, OperationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Operation> calculate(@RequestBody OperationRequestDto operationRequestDto){
        Operation op = service.calculate(mapper.mapOperationRequestDtoToOperation(operationRequestDto));
        return new ResponseEntity<Operation>(op, HttpStatus.OK);
    }

}
