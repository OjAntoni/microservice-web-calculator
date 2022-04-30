package com.example.divmicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {

    public Operation calc(Operation operation){
        return Operation.builder()
                .argTwo(operation.getArgTwo())
                .argOne(operation.getArgOne())
                .result(operation.getArgTwo()!=0 ? operation.getArgOne()/operation.getArgTwo() : Double.MAX_VALUE)
                .operationType(OperationType.DIVIDE)
                .build();
    }

}
