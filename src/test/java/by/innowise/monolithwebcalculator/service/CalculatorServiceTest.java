package by.innowise.monolithwebcalculator.service;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorServiceTest {

    private CalculatorService calculatorService;
    private Random r;
    private double argOne;
    private double argTwo;

    @BeforeAll
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @BeforeEach
    void beforeEach() {
        r = new Random();
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