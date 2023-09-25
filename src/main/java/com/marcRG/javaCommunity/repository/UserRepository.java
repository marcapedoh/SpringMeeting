package com.marcRG.javaCommunity.repository;

import com.marcRG.javaCommunity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
