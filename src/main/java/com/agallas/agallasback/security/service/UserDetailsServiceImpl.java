package com.agallas.agallasback.security.service;

import com.agallas.agallasback.model.entity.UserEntity;
import com.agallas.agallasback.security.details.UserCustomDetails;
import com.agallas.agallasback.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserAuthService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username){
        UserEntity userEntity = userService.getByUser(username).orElse(null);
        if (userEntity != null){
            return UserCustomDetails.build(userEntity);
        }
        else {
            return null;
        }
    }
}
