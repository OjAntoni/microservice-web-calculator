package by.innowise.monolithwebcalculator.domain.mapper;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.web.dto.OperationRequestDto;
import by.innowise.monolithwebcalculator.web.dto.OperationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {
    public Operation mapOperationRequestDtoToOperation(OperationRequestDto dto) {
        Operation op = new Operation();
        op.setArgOne(dto.getArgOne());
        op.setArgTwo(dto.getArgTwo());
        return op;
    }

    public OperationResponseDto mapOperationToOperationResponseDto(Operation op) {
        return new OperationResponseDto(op.getArgOne(), op.getArgTwo(), op.getResult(), op.getOperationType());
    }
}
