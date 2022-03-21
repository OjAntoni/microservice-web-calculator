package by.innowise.monolithwebcalculator.web.controller;

import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stat")
public class StatisticsController {

    private final CalculatorService calculatorService;

    public StatisticsController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/popular") 
    public ResponseEntity<String> getMostPopular(){
        OperationType popular = calculatorService.findMostPopularOperation();
        return new ResponseEntity<>(popular == null ? "no data" : popular.name(), HttpStatus.OK);
    }
}
