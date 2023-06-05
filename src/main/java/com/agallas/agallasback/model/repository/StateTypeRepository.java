package com.agallas.agallasback.model.repository;

import com.agallas.agallasback.model.entity.StateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StateTypeRepository extends JpaRepository<StateTypeEntity, Integer> {
}
