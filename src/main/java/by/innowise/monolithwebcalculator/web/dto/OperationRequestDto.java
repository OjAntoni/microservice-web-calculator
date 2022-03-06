package by.innowise.monolithwebcalculator.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequestDto {
    private double argOne;
    private double argTwo;
}
