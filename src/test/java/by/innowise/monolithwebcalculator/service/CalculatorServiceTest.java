package by.innowise.monolithwebcalculator.service;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    static CalculatorService calculatorService;
    Random r;

    @BeforeAll
    static void setUp() {
        calculatorService = new CalculatorService();
    }

    @BeforeEach
    void beforeEach() {
        r = new Random();
    }

    @Test
    void sumTest() {
        for (int i = 0; i < 10; i++) {
            double argOne = r.nextDouble() * i * 10;
            double argTwo = r.nextDouble() * i * 10;
            double res = argOne + argTwo;
            Assertions.assertEquals(res, calculatorService.sum(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
        }
    }

    @Test
    void subTest() {
        for (int i = 0; i < 10; i++) {
            double argOne = r.nextDouble() * i * 10;
            double argTwo = r.nextDouble() * i * 10;
            double res = argOne - argTwo;
            Assertions.assertEquals(res, calculatorService.sub(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
        }
    }

    @Test
    void mulTest() {
        for (int i = 0; i < 10; i++) {
            double argOne = r.nextDouble() * i * 10;
            double argTwo = r.nextDouble() * i * 10;
            double res = argOne * argTwo;
            Assertions.assertEquals(res, calculatorService.mul(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
        }
    }

    @Test
    void divTest() {
        for (int i = 0; i < 10; i++) {
            double argOne = r.nextDouble() * i * 10;
            double argTwo = r.nextDouble() * i * 10;
            if (argTwo == 0) {
                assertThrows(IllegalArgumentException.class, () -> calculatorService.div(Operation.builder().argOne(argOne).argTwo(argTwo).build()));
            } else {
                double res = argOne / argTwo;
                Assertions.assertEquals(res, calculatorService.div(Operation.builder().argOne(argOne).argTwo(argTwo).build()).getResult());
            }

        }
    }
}