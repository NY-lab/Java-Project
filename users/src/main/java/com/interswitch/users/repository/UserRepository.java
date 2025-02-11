package com.interswitch.users.repository;

import com.interswitch.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // Correct method for finding by username
    Optional<User> findByEmail(String email);        // Add other methods if necessary
    boolean existsByUsername(String username);       // For checking existence of username
    boolean existsByEmail(String email);             // For checking existence of email
}
