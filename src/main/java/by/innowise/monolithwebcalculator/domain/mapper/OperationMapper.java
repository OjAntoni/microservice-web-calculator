package by.innowise.monolithwebcalculator.domain.mapper;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.web.dto.OperationDto;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {
    public Operation mapOperationDtoToOperation(OperationDto dto) {
        Operation op = new Operation();
        op.setArgOne(dto.getArgOne());
        op.setArgTwo(dto.getArgTwo());
        op.setResult(dto.getResult());
        return op;
    }

    public OperationDto mapOperationToOperationDto(Operation op) {
        return new OperationDto(op.getArgOne(), op.getArgTwo(), op.getResult());
    }
}
