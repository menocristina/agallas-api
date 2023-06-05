package com.agallas.agallasback.model.repository;

import com.agallas.agallasback.model.entity.RoleEntity;
import com.agallas.agallasback.security.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleType(RoleType roleType);
}
