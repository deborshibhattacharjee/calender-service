package com.project.calenderservice.repository;

import com.project.calenderservice.entities.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CalenderRepository extends JpaRepository<Calender, UUID> {

    List<Calender> findByOwnerId(UUID ownerId);

    Optional<Calender> findByIdAndOwnerId(UUID id, UUID ownerId);

    boolean existsByOwnerIdAndName(UUID ownerId, String name);
}
