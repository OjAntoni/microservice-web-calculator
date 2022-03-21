package by.innowise.monolithwebcalculator.service;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculatorService {

    private final OperationRepository operationRepository;

    public CalculatorService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Transactional
    public Operation sum(Operation operation){
        double res = operation.getArgOne() + operation.getArgTwo();
        Operation op = new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.ADD);
        operationRepository.save(op);
        return op;
    }

    @Transactional
    public Operation sub(Operation operation){
        double res = operation.getArgOne() - operation.getArgTwo();
        Operation op = new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.SUBTRACT);
        operationRepository.save(op);
        return op;
    }

    @Transactional
    public Operation mul(Operation operation){
        double res = operation.getArgOne() * operation.getArgTwo();
        Operation op = new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.MULTIPLY);
        operationRepository.save(op);
        return op;
    }

    @Transactional
    public Operation div(Operation operation){
        if(operation.getArgTwo() == 0){
            throw new IllegalArgumentException("Number can't be divided by zero!");
        }
        double res = operation.getArgOne() / operation.getArgTwo();
        Operation op = new Operation(operation.getArgOne(), operation.getArgTwo(), res, OperationType.DIVIDE);
        operationRepository.save(op);
        return op;
    }

    @Transactional
    public OperationType findMostPopularOperation(){
        return operationRepository.findMostPopular();
    }
}
