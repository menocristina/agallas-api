package com.agallas.agallasback.model.repository;

import com.agallas.agallasback.model.entity.AnimalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    @Query("SELECT a FROM AnimalEntity a WHERE a.deletedAt is NULL")
    Page<AnimalEntity> findAllActive(Pageable pageable);
}
