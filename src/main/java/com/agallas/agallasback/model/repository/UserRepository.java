package com.agallas.agallasback.model.repository;

import com.agallas.agallasback.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserAndPass(String user, String pass);
    Optional<UserEntity> findByUser(String user);
    Boolean existsByUser(String user);

    @Query("SELECT u FROM UserEntity u WHERE u.deletedAt is null ")
    Page<UserEntity> findAllActive(Pageable pageable);
}
