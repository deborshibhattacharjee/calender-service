package com.project.calenderservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    @Setter
    private String name;
    @Setter
    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;
    @Column(nullable = false, updatable = false)
    private Instant createdTime;
    @Column(nullable=false)
    private Instant modifiedTime;

    @PrePersist
    protected void onCreate(){
        createdTime = Instant.now();
        modifiedTime = Instant.now();
    }

    @PreUpdate
    protected void onUpdate(){
        modifiedTime = Instant.now();
    }
}
