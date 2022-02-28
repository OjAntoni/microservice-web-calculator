package by.innowise.monolithwebcalculator.web.dto;

import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OperationResponseDto {
    private double argOne;
    private double argTwo;
    private double result;
    private OperationType type;
}
