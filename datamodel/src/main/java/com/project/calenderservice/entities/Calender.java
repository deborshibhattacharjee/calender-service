package com.project.calenderservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "calenders")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Calender {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "timezone", nullable = false)
    private String timeZone;
    @Column(name = "created_time", updatable = false)
    private Instant createdTime;
    @Column(name = "modified_time")
    private Instant modifiedTime;

    @PrePersist
    public void onCreate(){
        createdTime = Instant.now();
        modifiedTime = Instant.now();
    }

    @PreUpdate
    public void onUpdate(){
        modifiedTime = Instant.now();
    }
}
