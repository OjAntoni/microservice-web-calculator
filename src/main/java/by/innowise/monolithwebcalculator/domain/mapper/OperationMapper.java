package by.innowise.monolithwebcalculator.domain.mapper;

import by.innowise.monolithwebcalculator.domain.operation.Operation;
import by.innowise.monolithwebcalculator.web.dto.OperationRequestDto;
import by.innowise.monolithwebcalculator.web.dto.OperationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    @Mappings({
            @Mapping(target = "argOne", source = "argOne"),
            @Mapping(target = "argTwo", source = "argTwo"),
    })
    Operation mapOperationRequestDtoToOperation(OperationRequestDto dto);
    @Mappings({
            @Mapping(target = "argOne", source = "argOne"),
            @Mapping(target = "argTwo", source = "argTwo"),
            @Mapping(target = "result", source = "result"),
            @Mapping(target = "type", source = "operationType"),
    })
    OperationResponseDto mapOperationToOperationResponseDto(Operation op);
}
