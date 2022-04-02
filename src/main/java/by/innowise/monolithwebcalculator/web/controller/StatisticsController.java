package by.innowise.monolithwebcalculator.web.controller;

import by.innowise.monolithwebcalculator.domain.mapper.OperationMapper;
import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import by.innowise.monolithwebcalculator.web.dto.OperationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/stat")
public class StatisticsController {

    private final CalculatorService calculatorService;
    private final OperationMapper mapper;

    public StatisticsController(CalculatorService calculatorService, OperationMapper mapper) {
        this.calculatorService = calculatorService;
        this.mapper = mapper;
    }

    @GetMapping("/popular")
    public ResponseEntity<String> getMostPopular(){
        OperationType popular = calculatorService.findMostPopularOperation();
        return new ResponseEntity<>(popular == null ? "no data" : popular.name(), HttpStatus.OK);
    }

    @GetMapping("/find/{uuid}")
    public ResponseEntity<OperationResponseDto> getByUUID(@PathVariable UUID uuid){
        Operation operation = calculatorService.getById(uuid);
        return new ResponseEntity<>(mapper.mapOperationToOperationResponseDto(operation), HttpStatus.OK);
    }
}
