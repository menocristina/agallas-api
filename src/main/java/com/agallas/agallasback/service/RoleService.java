package com.agallas.agallasback.service;

import com.agallas.agallasback.model.entity.RoleEntity;
import com.agallas.agallasback.model.repository.RoleRepository;
import com.agallas.agallasback.security.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<RoleEntity> getByRoleType(RoleType roleType){
        return roleRepository.findByRoleType(roleType);
    }
}
