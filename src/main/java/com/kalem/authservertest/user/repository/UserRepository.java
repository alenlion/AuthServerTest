package com.kalem.authservertest.user.repository;

import com.kalem.authservertest.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rayan Aksu
 * @since 7/27/2023
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findFirstByUsername( String username );
}
