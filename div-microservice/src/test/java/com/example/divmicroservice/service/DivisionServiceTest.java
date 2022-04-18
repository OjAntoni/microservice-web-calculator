package com.example.divmicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionServiceTest {
    private final DivisionService service = new DivisionService();

    @Test
    void calculate() {
        Operation op = Operation.builder().argOne(2).argTwo(2).operationType(OperationType.ADD).build();
        op = service.calc(op);
        Assertions.assertEquals(1, op.getResult());
    }
}