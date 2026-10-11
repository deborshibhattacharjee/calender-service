package com.project.calenderservice.dtos.response;

import java.time.Instant;
import java.util.UUID;

public record CalenderResponse(
        UUID id,
        UUID ownerId,
        String name,
        String description,
        String timeZone,
        Instant createdTime,
        Instant modifiedTime
) {}
