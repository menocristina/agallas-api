package com.agallas.agallasback.model.mappers;

import com.agallas.agallasback.model.dto.UserDto;
import com.agallas.agallasback.model.entity.UserEntity;
import com.agallas.agallasback.security.enums.RoleType;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.Objects;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDto userDto);

    default UserDto toDTO(UserEntity entity){
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setUser( entity.getUser() );
        userDto.setPass( entity.getPass() );
        userDto.setName( entity.getName() );
        userDto.setSurname( entity.getSurname() );
        userDto.setIsAdmin(false);
        if (Objects.nonNull(entity.getRoles())){
            userDto.setIsAdmin(
                    entity.getRoles().stream()
                            .anyMatch(role -> role.getRoleType().equals(RoleType.ROLE_ADMIN))
            );
        }

        return userDto;
    };

    default Page<UserDto> toPageDTO(Page<UserEntity> users){
        return users.map(this::toDTO);
    }
}
