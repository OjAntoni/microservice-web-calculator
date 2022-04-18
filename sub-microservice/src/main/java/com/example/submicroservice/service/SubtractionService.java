package com.example.submicroservice.service;
import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.springframework.stereotype.Service;

@Service
public class SubtractionService {

    public Operation calc(Operation op){
        return Operation.builder()
                .argOne(op.getArgOne())
                .argTwo(op.getArgOne())
                .operationType(OperationType.SUBTRACT)
                .result(op.getArgOne()-op.getArgTwo())
                .build();
    }

}
