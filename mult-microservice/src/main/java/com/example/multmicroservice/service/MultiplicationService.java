package com.example.multmicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.springframework.stereotype.Service;

@Service
public class MultiplicationService {

    public Operation calc(Operation op){
        return Operation.builder()
                .argOne(op.getArgOne())
                .argTwo(op.getArgOne())
                .operationType(OperationType.MULTIPLY)
                .result(op.getArgOne()*op.getArgTwo())
                .build();
    }

}
