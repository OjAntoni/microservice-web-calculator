package com.example.submicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionServiceTest {
    private final SubtractionService service = new SubtractionService();

    @Test
    void calculate() {
        Operation op = Operation.builder().argOne(5).argTwo(5).operationType(OperationType.ADD).build();
        op = service.calc(op);
        Assertions.assertEquals(0, op.getResult());
    }
}