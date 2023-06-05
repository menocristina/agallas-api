package com.agallas.agallasback.service;

import com.agallas.agallasback.model.entity.UserEntity;
import com.agallas.agallasback.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;

    public Optional<UserEntity> getByUser(String user){
        return userRepository.findByUser(user);
    }
}
