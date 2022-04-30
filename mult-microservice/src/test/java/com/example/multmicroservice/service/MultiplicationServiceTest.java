package com.example.multmicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationServiceTest {
    private final MultiplicationService service = new MultiplicationService();

    @Test
    void calculate() {
        Operation op = Operation.builder().argOne(2).argTwo(2).operationType(OperationType.ADD).build();
        op = service.calc(op);
        Assertions.assertEquals(4, op.getResult());
    }
}