package by.innowise.monolithwebcalculator.web.controller;

import by.innowise.monolithwebcalculator.domain.mapper.OperationMapper;
import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.repository.OperationRepository;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import by.innowise.monolithwebcalculator.web.dto.OperationResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CalculatorController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CalculatorService calculatorService;
    @MockBean
    OperationRepository operationRepository;
    @MockBean
    OperationMapper operationMapper;

    @BeforeAll
    void setUp(){
        Mockito.when(operationRepository.save(any())).thenReturn(null);
    }

    @Test
    void add() throws Exception {
        Mockito.when(calculatorService.sum(Mockito.any())).thenReturn(new Operation(6, 6, 12, OperationType.ADD));
        Mockito.when(operationMapper.mapOperationToOperationResponseDto(any())).thenReturn(new OperationResponseDto(6, 6, 12, OperationType.ADD));

        testApiCall("add", 12, OperationType.ADD);
    }

    @Test
    void multiply() throws Exception {
        Mockito.when(calculatorService.mul(Mockito.any())).thenReturn(new Operation(6, 6, 36, OperationType.MULTIPLY));
        Mockito.when(operationMapper.mapOperationToOperationResponseDto(any())).thenReturn(new OperationResponseDto(6, 6, 36, OperationType.MULTIPLY));

        testApiCall("mul", 36, OperationType.MULTIPLY);
    }

    @Test
    void subtract() throws Exception {
        Mockito.when(calculatorService.sub(Mockito.any())).thenReturn(new Operation(6, 6, 0, OperationType.SUBTRACT));
        Mockito.when(operationMapper.mapOperationToOperationResponseDto(any())).thenReturn(new OperationResponseDto(6, 6, 0, OperationType.SUBTRACT));

        testApiCall("sub", 0, OperationType.SUBTRACT);
    }

    @Test
    void divide() throws Exception {
        Mockito.when(calculatorService.div(Mockito.any())).thenReturn(new Operation(6, 6, 1, OperationType.DIVIDE));
        Mockito.when(operationMapper.mapOperationToOperationResponseDto(any())).thenReturn(new OperationResponseDto(6, 6, 1, OperationType.DIVIDE));

        testApiCall("div", 1, OperationType.DIVIDE);
    }

    private void testApiCall(String endpoint, double expectedValue, OperationType type) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/" + endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OperationResponseDto(6, 6, 0, type))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.argOne").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.argTwo").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(expectedValue));
    }
}