package by.innowise.monolithwebcalculator.service;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public Operation sum(Operation operation){
        double res = operation.getArgOne() + operation.getArgTwo();
        return new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.ADD);
    }

    public Operation sub(Operation operation){
        double res = operation.getArgOne() - operation.getArgTwo();
        return new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.SUBTRACT);
    }

    public Operation mul(Operation operation){
        double res = operation.getArgOne() * operation.getArgTwo();
        return new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.MULTIPLY);
    }

    public Operation div(Operation operation){
        if(operation.getArgTwo() == 0){
            throw new IllegalArgumentException("Number can't be divided by zero!");
        }
        double res = operation.getArgOne() / operation.getArgTwo();
        return new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.DIVIDE);
    }
}
