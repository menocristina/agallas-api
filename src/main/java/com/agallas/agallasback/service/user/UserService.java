package com.agallas.agallasback.service.user;

import com.agallas.agallasback.exception.UserAlreadyExistsException;
import com.agallas.agallasback.model.dto.UserDto;
import com.agallas.agallasback.model.entity.RoleEntity;
import com.agallas.agallasback.model.entity.UserEntity;
import com.agallas.agallasback.model.mappers.UserMapper;
import com.agallas.agallasback.model.repository.UserRepository;
import com.agallas.agallasback.security.enums.RoleType;
import com.agallas.agallasback.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Boolean login(UserDto userDto) {
        return userRepository.findByUserAndPass(userDto.getUser(), userDto.getPass()).orElse(null) != null;
    }

    public Boolean existsByUsername(String username){
        return userRepository.existsByUser(username);
    }

    @Transactional
    public void newUser(UserDto userDto) throws UserAlreadyExistsException{
        if (!userRepository.existsByUser(userDto.getUser())){
            UserEntity userEntity = userMapper.toEntity(userDto);
            userEntity.setPass(passwordEncoder.encode(userDto.getPass()));
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(roleService.getByRoleType(RoleType.ROLE_USER).get());
            userEntity.setRoles(roles);
            userEntity.setCreatedAt(new Date());
            userRepository.save(userEntity);
        } else {
            throw new UserAlreadyExistsException("Ya existe un usuario con ese nombre de usuario");
        }

    }

    @Transactional(readOnly = true)
    public Page<UserDto> getAll(Pageable pageable){
        return this.userMapper.toPageDTO(this.userRepository.findAllActive(pageable));
    }

    @Transactional(readOnly = true)
    public UserDto get(Long userId){
        return mapToView(userRepository.findById(userId).get());
    }

    private UserDto mapToView(UserEntity user){
        UserDto dto = null;
        if (user != null){
            dto = new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setUser(user.getUser());
            dto.setIsAdmin(user.getRoles() != null && user.getRoles().stream().filter( role -> role.getRoleType().equals(RoleType.ROLE_ADMIN)).count() > 0);
        }
        return dto;
    }
}
