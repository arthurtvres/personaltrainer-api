package com.personal.personalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.personal.personalapi.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
