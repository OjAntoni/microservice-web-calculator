package by.innowise.monolithwebcalculator.service;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.repository.OperationRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.invocation.MatchersBinder;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    private CalculatorService calculatorService;
    private double argOne;
    private double argTwo;
    @Mock
    private OperationRepository operationRepository;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
        calculatorService = new CalculatorService(operationRepository);
        Mockito.when(operationRepository.save(any())).thenReturn(null);
    }

    @BeforeEach
    void beforeEach() {
        Random r = new Random();
        argOne = r.nextDouble() * 100;
        double tmp = r.nextDouble() * 100;
        argTwo = tmp == 0 ? 1 : tmp;
    }

    @RepeatedTest(10)
    void sumTest() {
        double res = argOne + argTwo;
        Assertions.assertEquals(res, calculatorService.sum(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
    }

    @RepeatedTest(10)
    void subTest() {
        double res = argOne - argTwo;
        Assertions.assertEquals(res, calculatorService.sub(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
    }

    @RepeatedTest(10)
    void mulTest() {
        double res = argOne * argTwo;
        Assertions.assertEquals(res, calculatorService.mul(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
    }

    @RepeatedTest(10)
    void divTest() {
        double res = argOne / argTwo;
        Assertions.assertEquals(res, calculatorService.div(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
    }

    @Test
    void expectExceptionWhileDividingByZeroTest() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.div(Operation.builder().argOne(10).argTwo(0).build()));
    }
}