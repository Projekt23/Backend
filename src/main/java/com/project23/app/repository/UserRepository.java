package com.project23.app.repository;

import com.project23.app.Entity.User;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}