package com.example.calculator.web.controller;

import com.example.calculator.service.CalculatorService;
import com.example.calculator.util.MicroservicesConnectionProperties;
import com.example.commonlogic.domain.mapper.OperationMapper;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import com.example.commonlogic.web.dto.OperationRequestDto;
import com.example.commonlogic.web.dto.OperationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calc")
public class CalculatorController {
    private final CalculatorService service;
    private final OperationMapper mapper;

    public CalculatorController(CalculatorService service, OperationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/{type}")
    public ResponseEntity<OperationResponseDto> calc(@RequestBody OperationRequestDto operationRequestDto, @PathVariable String type) {
        Operation response = service.requestAndGetOperation(mapper.mapOperationRequestDtoToOperation(operationRequestDto), OperationType.valueOf(type));
        return new ResponseEntity<>(mapper.mapOperationToOperationResponseDto(response), HttpStatus.OK);
    }

}
