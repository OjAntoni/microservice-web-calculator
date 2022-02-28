package by.innowise.monolithwebcalculator.domain.mapper;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.web.dto.OperationRequestDto;
import by.innowise.monolithwebcalculator.web.dto.OperationResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    void mapOperationRequestDtoToOperationTest() {
        for (int i = 0; i < 20; i++) {

            double argOne = r.nextDouble();
            double argTwo = r.nextDouble();
            double res = r.nextDouble();

            OperationRequestDto dto = new OperationRequestDto();
            dto.setArgOne(argOne);
            dto.setArgTwo(argTwo);

            Operation mappedOp = mapper.mapOperationRequestDtoToOperation(dto);
            Assertions.assertEquals(argOne, mappedOp.getArgOne());
            Assertions.assertEquals(argTwo, mappedOp.getArgTwo());
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

            OperationResponseDto mappedOpDto = mapper.mapOperationToOperationResponseDto(op);
            Assertions.assertEquals(argOne, mappedOpDto.getArgOne());
            Assertions.assertEquals(argTwo, mappedOpDto.getArgTwo());
            Assertions.assertEquals(res, mappedOpDto.getResult());
        }
    }
}