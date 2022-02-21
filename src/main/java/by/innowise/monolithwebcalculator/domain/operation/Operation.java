package by.innowise.monolithwebcalculator.domain.operation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Operation {
    private double argOne;
    private double argTwo;
    private double result;
    private OperationType operationType;
}
