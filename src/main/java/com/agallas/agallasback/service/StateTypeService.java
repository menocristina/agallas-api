package com.agallas.agallasback.service;

import com.agallas.agallasback.model.dto.StateTypeDTO;
import com.agallas.agallasback.model.entity.StateTypeEntity;
import com.agallas.agallasback.model.mappers.StateTypeMapper;
import com.agallas.agallasback.model.repository.StateTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateTypeService {

    private final StateTypeRepository stateTypeRepository;
    private final StateTypeMapper stateTypeMapper;

    public List<StateTypeDTO> getAll(){
        return this.stateTypeMapper.toDTO(this.stateTypeRepository.findAll());
    }

    public StateTypeEntity get(Integer id) {
        return this.stateTypeRepository.getOne(id);
    }
}
