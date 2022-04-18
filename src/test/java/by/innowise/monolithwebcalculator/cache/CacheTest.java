package by.innowise.monolithwebcalculator.cache;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.repository.OperationRepository;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CacheTest {
    @MockBean
    private OperationRepository operationRepository;
    @Autowired
    private CalculatorService calculatorService;
    private Operation sampleOperation;
    @Autowired
    CacheManager cacheManager;

    @BeforeAll
    void setUp(){
        sampleOperation = new Operation(2, 2, 4, OperationType.ADD);
        sampleOperation.setId(UUID.randomUUID());
        Mockito.when(operationRepository.save(sampleOperation)).thenReturn(sampleOperation);
        Mockito.when(operationRepository.getOperationById(ArgumentMatchers.any())).thenReturn(sampleOperation);
    }

    @Test
    @Order(1)
    void testIfObjectWillBeInCache(){
        Operation returnedOp = calculatorService.sum(sampleOperation);
        Mockito.verify(operationRepository, Mockito.times(1)).save(ArgumentMatchers.any());
        Assertions.assertNotNull(cacheManager.getCache("operations").get(sampleOperation.getId()));
    }

    @Test
    @Order(2)
    void testIfRepositoryMethodNotInvoked(){
        Operation returnedOp = calculatorService.getById(sampleOperation.getId());
        Assertions.assertNotNull(returnedOp);
        Mockito.verify(operationRepository, Mockito.times(0))
                .getById(sampleOperation.getId());
    }
}
