package by.innowise.monolithwebcalculator.domain.mapper;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.web.dto.OperationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class OperationMapperTest {

    static OperationMapper mapper;
    static List<OperationType> operationTypes;
    Random r;

    @BeforeAll
    static void setUp() {
        mapper = new OperationMapper();
        operationTypes = Arrays.asList(OperationType.values());
    }

    @BeforeEach
    void beforeEach() {
        r = new Random();
    }

    @Test
    void mapOperationDtoToOperationTest() {
        for (int i = 0; i < 20; i++) {

            double argOne = r.nextDouble();
            double argTwo = r.nextDouble();
            double res = r.nextDouble();

            OperationDto dto = new OperationDto();
            dto.setArgOne(argOne);
            dto.setArgTwo(argTwo);
            dto.setResult(res);

            Operation mappedOp = mapper.mapOperationDtoToOperation(dto);
            Assertions.assertEquals(argOne, mappedOp.getArgOne());
            Assertions.assertEquals(argTwo, mappedOp.getArgTwo());
            Assertions.assertEquals(res, mappedOp.getResult());
        }
    }

    @Test
    void mapOperationToOperationDtoTest() {
        for (int i = 0; i < 20; i++) {

            double argOne = r.nextDouble();
            double argTwo = r.nextDouble();
            double res = r.nextDouble();
            OperationType type = operationTypes.get(r.nextInt(operationTypes.size()));
            Operation op = new Operation(argOne, argTwo, res, type);

            OperationDto mappedOpDto = mapper.mapOperationToOperationDto(op);
            Assertions.assertEquals(argOne, mappedOpDto.getArgOne());
            Assertions.assertEquals(argTwo, mappedOpDto.getArgTwo());
            Assertions.assertEquals(res, mappedOpDto.getResult());
        }
    }
}