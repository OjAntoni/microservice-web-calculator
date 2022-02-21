package by.innowise.monolithwebcalculator.web.controller;

import by.innowise.monolithwebcalculator.domain.mapper.OperationMapper;
import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import by.innowise.monolithwebcalculator.web.dto.OperationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calc")
public class CalculatorController {

    private CalculatorService calculatorService;
    private OperationMapper operationMapper;

    public CalculatorController(CalculatorService calculatorService, OperationMapper operationMapper) {
        this.calculatorService = calculatorService;
        this.operationMapper = operationMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<OperationDto> add(@RequestBody OperationDto operationDto) {
        Operation op = calculatorService.sum(operationMapper.mapOperationDtoToOperation(operationDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationDto(op), HttpStatus.OK);
    }

    @PostMapping("/mul")
    public ResponseEntity<OperationDto> multiply(@RequestBody OperationDto operationDto) {
        Operation op = calculatorService.mul(operationMapper.mapOperationDtoToOperation(operationDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationDto(op), HttpStatus.OK);
    }

    @PostMapping("/sub")
    public ResponseEntity<OperationDto> subtract(@RequestBody OperationDto operationDto) {
        Operation op = calculatorService.sub(operationMapper.mapOperationDtoToOperation(operationDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationDto(op), HttpStatus.OK);
    }

    @PostMapping("/div")
    public ResponseEntity<OperationDto> divide(@RequestBody OperationDto operationDto) {
        Operation op = calculatorService.div(operationMapper.mapOperationDtoToOperation(operationDto));
        return new ResponseEntity<>(operationMapper.mapOperationToOperationDto(op), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
