package by.innowise.monolithwebcalculator.web.dto;

import by.innowise.monolithwebcalculator.domain.operation.OperationType;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OperationResponseDto {
    private UUID uuid;
    private double argOne;
    private double argTwo;
    private double result;
    private OperationType type;
}
