package com.example.divmicroservice.service;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.domain.operation.OperationType;
import com.example.divmicroservice.util.properties.DivisionProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DivisionServiceTest {
    DivisionProperties properties;
    DivisionService service;

    @BeforeAll
    void setUp(){
        properties = new DivisionProperties();
        properties.setPrecision(2);
        service = new DivisionService(properties);
    }


    @Test
    void calculate() {
        Operation op = Operation.builder().argOne(2).argTwo(2).operationType(OperationType.ADD).build();
        op = service.calc(op);
        Assertions.assertEquals(1, op.getResult());
    }
}