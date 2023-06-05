package com.agallas.agallasback.service;

import com.agallas.agallasback.model.entity.AnimalEntity;
import com.agallas.agallasback.model.entity.AnimalStateEntity;
import com.agallas.agallasback.model.repository.AnimalRepository;
import com.agallas.agallasback.model.repository.AnimalStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalStateService {

    private final AnimalStateRepository animalStateRepository;
    private final AnimalRepository animalRepository;
    private final StateTypeService stateTypeService;

    public void saveState(Integer stateType, Date stateDate, AnimalEntity animal){
        AnimalStateEntity state = new AnimalStateEntity();
        state.setAnimal(this.animalRepository.getOne(animal.getId()));
        state.setStateType(this.stateTypeService.get(stateType));
        state.setStartDate(stateDate);
        state.setCreatedAt(new Date());
        AnimalStateEntity stateSaved = this.animalStateRepository.save(state);
    }

    public Optional<AnimalStateEntity> getByAnimalId(Long animalId) {
        return animalStateRepository.getByAnimalId(animalId);
    }
}
