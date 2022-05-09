package com.example.addmicroserivce.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdditionServiceTest {
    private final AdditionService service = new AdditionService();

    @Test
    void calculate() {
        Operation op = Operation.builder().argOne(2).argTwo(2).operationType(OperationType.ADD).build();
        op = service.calculate(op);
        Assertions.assertEquals(4, op.getResult());
    }
}