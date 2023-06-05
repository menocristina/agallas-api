package com.agallas.agallasback.model.repository;

import com.agallas.agallasback.model.entity.AnimalStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalStateRepository extends JpaRepository<AnimalStateEntity, Long> {

    @Query("SELECT a FROM AnimalStateEntity a WHERE a.animal.id = :animalId AND a.startDate IN (SELECT MAX(s.startDate) FROM AnimalStateEntity s ) AND a.endDate is null AND a.deletedAt is null")
    Optional<AnimalStateEntity> getByAnimalId(Long animalId);
}
