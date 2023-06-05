package com.agallas.agallasback.service;

import com.agallas.agallasback.model.dto.AnimalDTO;
import com.agallas.agallasback.model.entity.AnimalEntity;
import com.agallas.agallasback.model.entity.AnimalStateEntity;
import com.agallas.agallasback.model.mappers.AnimalMapper;
import com.agallas.agallasback.model.mappers.StateTypeMapper;
import com.agallas.agallasback.model.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    private final AnimalMapper animalMapper;

    private final StateTypeMapper stateTypeMapper;

    private final AnimalStateService animalStateService;

    @Transactional
    public void save(AnimalDTO animal) {
        AnimalEntity animalSaved = this.animalRepository.save(this.animalMapper.toEntity(animal));
        this.animalStateService.saveState(animal.getActualStateType(), animal.getActualStateDate(), animalSaved);
    }

    public Page<AnimalDTO> getAll(Pageable pageable){
        return this.animalMapper.toPageDTO(this.animalRepository.findAllActive(pageable));
    }

    public void delete(Long id) {
        AnimalEntity animalEntity = this.animalRepository.getOne(id);
        animalEntity.setDeletedAt(new Date());
        this.animalRepository.save(animalEntity);
    }

    public AnimalDTO get(Long id) {
        AnimalDTO animalDTO = this.animalMapper.toDTO(this.animalRepository.getOne(id));
        return setLastState(animalDTO);
    }

    private AnimalDTO setLastState(AnimalDTO animalDTO){
        AnimalStateEntity lastAnimalState = animalStateService.getByAnimalId(animalDTO.getId()).orElse(null);
        if (lastAnimalState != null){
            animalDTO.setActualStateType(lastAnimalState.getStateType().getId());
            animalDTO.setActualStateDate(lastAnimalState.getStartDate());
        }
        return animalDTO;
    }
}
