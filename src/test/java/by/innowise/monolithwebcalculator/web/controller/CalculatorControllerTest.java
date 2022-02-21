package by.innowise.monolithwebcalculator.web.controller;

import by.innowise.monolithwebcalculator.domain.mapper.OperationMapper;
import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import by.innowise.monolithwebcalculator.service.CalculatorService;
import by.innowise.monolithwebcalculator.web.dto.OperationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.ServletContext;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CalculatorController.class)
@ExtendWith(MockitoExtension.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CalculatorService calculatorService;
    @MockBean
    OperationMapper operationMapper;

    @Test
    void add() throws Exception {
        Mockito.when(calculatorService.sum(Mockito.any())).thenReturn(new Operation(6, 6, 12, OperationType.ADD));
        Mockito.when(operationMapper.mapOperationToOperationDto(any())).thenReturn(new OperationDto(6, 6, 12));

        testApiCall("add", 12);
    }

    @Test
    void multiply() throws Exception {
        Mockito.when(calculatorService.mul(Mockito.any())).thenReturn(new Operation(6, 6, 36, OperationType.MULTIPLY));
        Mockito.when(operationMapper.mapOperationToOperationDto(any())).thenReturn(new OperationDto(6, 6, 36));

        testApiCall("mul", 36);
    }

    @Test
    void subtract() throws Exception {
        Mockito.when(calculatorService.sub(Mockito.any())).thenReturn(new Operation(6, 6, 0, OperationType.SUBTRACT));
        Mockito.when(operationMapper.mapOperationToOperationDto(any())).thenReturn(new OperationDto(6, 6, 0));

        testApiCall("sub", 0);
    }

    @Test
    void divide() throws Exception {
        Mockito.when(calculatorService.div(Mockito.any())).thenReturn(new Operation(6, 6, 1, OperationType.DIVIDE));
        Mockito.when(operationMapper.mapOperationToOperationDto(any())).thenReturn(new OperationDto(6, 6, 1));

        testApiCall("div", 1);
    }

    private void testApiCall(String endpoint, double expectedValue) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/" + endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OperationDto(6, 6, 0))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.argOne").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.argTwo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(expectedValue));
    }
}