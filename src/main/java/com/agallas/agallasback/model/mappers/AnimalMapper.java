package com.agallas.agallasback.model.mappers;

import com.agallas.agallasback.model.dto.AnimalDTO;
import com.agallas.agallasback.model.entity.AnimalEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDTO toDTO(AnimalEntity entity);
    List<AnimalDTO> toDTO(List<AnimalEntity> entity);
    AnimalEntity toEntity(AnimalDTO dto);
    default Page<AnimalDTO> toPageDTO(Page<AnimalEntity> animales){
        return animales.map(this::toDTO);
    }
}
