package by.innowise.monolithwebcalculator.web.controller;

import by.innowise.monolithwebcalculator.domain.mapper.OperationMapper;
import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import by.innowise.monolithwebcalculator.web.dto.OperationRequestDto;
import by.innowise.monolithwebcalculator.web.dto.OperationResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calc")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final OperationMapper operationMapper;

    public CalculatorController(CalculatorService calculatorService, OperationMapper operationMapper) {
        this.calculatorService = calculatorService;
        this.operationMapper = operationMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<OperationResponseDto> add(@RequestBody OperationRequestDto operationRequestDto) {
        Operation op = calculatorService.sum(operationMapper.mapOperationRequestDtoToOperation(operationRequestDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationResponseDto(op), HttpStatus.OK);
    }

    @PostMapping("/mul")
    public ResponseEntity<OperationResponseDto> multiply(@RequestBody OperationRequestDto operationRequestDto) {
        Operation op = calculatorService.mul(operationMapper.mapOperationRequestDtoToOperation(operationRequestDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationResponseDto(op), HttpStatus.OK);
    }

    @PostMapping("/sub")
    public ResponseEntity<OperationResponseDto> subtract(@RequestBody OperationRequestDto operationRequestDto) {
        Operation op = calculatorService.sub(operationMapper.mapOperationRequestDtoToOperation(operationRequestDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationResponseDto(op), HttpStatus.OK);
    }

    @PostMapping("/div")
    public ResponseEntity<OperationResponseDto> divide(@RequestBody OperationRequestDto operationRequestDto) {
        Operation op = calculatorService.div(operationMapper.mapOperationRequestDtoToOperation(operationRequestDto));
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(operationMapper.mapOperationToOperationResponseDto(op), headers, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
