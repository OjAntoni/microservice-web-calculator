package com.example.addmicroserivce.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.springframework.stereotype.Service;

@Service
public class AdditionService {
    public Operation calculate(Operation operation){
        return Operation.builder()
                .argOne(operation.getArgOne())
                .argTwo(operation.getArgTwo())
                .operationType(OperationType.ADD)
                .result(operation.getArgOne()+operation.getArgTwo())
                .build();
    }
}
