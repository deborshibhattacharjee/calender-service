package com.project.calenderservice.repository;


import com.project.calenderservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailId(String emailId);

    boolean existsByEmailId(String emailId);
}
