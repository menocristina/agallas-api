package com.agallas.agallasback.service.user;

import com.agallas.agallasback.model.dto.UserDto;
import com.agallas.agallasback.model.entity.RoleEntity;
import com.agallas.agallasback.model.entity.UserEntity;
import com.agallas.agallasback.model.mappers.UserMapper;
import com.agallas.agallasback.model.repository.UserRepository;
import com.agallas.agallasback.security.enums.RoleType;
import com.agallas.agallasback.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserEditService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void editUser(UserDto userDto){
        UserEntity userEntity = map(userDto);
        Set<RoleEntity> roles = new HashSet<>();
        if (userDto.getIsAdmin()){
            roles.add(roleService.getByRoleType(RoleType.ROLE_ADMIN).get());
        } else{
            roles.add(roleService.getByRoleType(RoleType.ROLE_USER).get());
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }

    private UserEntity map(UserDto userDto){
        UserEntity userEntity =  userMapper.toEntity(userDto);
        UserEntity userBeforeEdit = userRepository.getOne(userDto.getId());
        if (userDto.getNewPass().isEmpty()){
            userEntity.setPass(userBeforeEdit.getPass());
        } else {
            userEntity.setPass(passwordEncoder.encode(userDto.getNewPass()));
        }
        userEntity.setCreatedAt(userBeforeEdit.getCreatedAt());
        return userEntity;
    }
}
