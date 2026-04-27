package com.csuf.expensesplittingapi.repository;

import com.csuf.expensesplittingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used for logging in
    Optional<User> findByEmail(String email);

    // Used to prevent duplicate registrations (HTTP 409 Conflict requirement)
    boolean existsByEmail(String email);
}