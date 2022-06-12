package com.example.commonlogic.web.dto;

import com.example.commonlogic.domain.operation.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OperationResponseDto {
    private double argOne;
    private double argTwo;
    private double result;
    private OperationType type;
}
