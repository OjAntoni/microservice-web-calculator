package com.example.divmicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import com.example.divmicroservice.util.properties.DivisionProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DivisionService {
    private final DivisionProperties prop;

    public DivisionService(DivisionProperties prop) {
        this.prop = prop;
    }

    public Operation calc(Operation operation){
        double res = BigDecimal.valueOf(operation.getArgTwo()!=0 ? operation.getArgOne()/operation.getArgTwo() : Double.MAX_VALUE).
                setScale(prop.getPrecision(), RoundingMode.HALF_UP).doubleValue();
        return Operation.builder()
                .argTwo(operation.getArgTwo())
                .argOne(operation.getArgOne())
                .result(res)
                .operationType(OperationType.DIVIDE)
                .build();
    }

}
