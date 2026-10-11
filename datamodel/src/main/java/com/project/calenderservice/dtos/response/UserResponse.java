package com.project.calenderservice.dtos.response;


import java.time.Instant;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String emailId,
        Instant createdTime,
        Instant modifiedTime
) {}
