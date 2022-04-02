package by.innowise.monolithwebcalculator.service;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.DefaultOperationConstants;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.repository.OperationRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "operations")
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

    @Transactional
    @Cacheable(value = "operations")
    public Operation getById(UUID uuid){
        return Optional.ofNullable(operationRepository.getOperationById(uuid)).orElseGet(this::getDefaultOperation);
    }

    public Operation getDefaultOperation(){
        return Operation.builder()
                .argOne(DefaultOperationConstants.ARG_ONE)
                .argTwo(DefaultOperationConstants.ARG_TWO)
                .result(DefaultOperationConstants.RESULT)
                .operationType(DefaultOperationConstants.OPERATION_TYPE)
                .build();
    }
}
