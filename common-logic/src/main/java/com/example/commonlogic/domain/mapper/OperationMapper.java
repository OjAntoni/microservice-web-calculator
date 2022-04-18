package com.example.commonlogic.domain.mapper;

import com.example.commonlogic.domain.operation.Operation;
import com.example.commonlogic.web.dto.OperationRequestDto;
import com.example.commonlogic.web.dto.OperationResponseDto;
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
            @Mapping(target = "uuid", source = "id")
    })
    OperationResponseDto mapOperationToOperationResponseDto(Operation op);
}
