package by.innowise.monolithwebcalculator.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OperationDto {
    private double argOne;
    private double argTwo;
    private double result;
}
