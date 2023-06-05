package com.agallas.agallasback.model.mappers;

import com.agallas.agallasback.model.dto.StateTypeDTO;
import com.agallas.agallasback.model.entity.StateTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateTypeMapper {

    StateTypeDTO toDTO(StateTypeEntity entity);
    List<StateTypeDTO> toDTO(List<StateTypeEntity> entityList);
}
