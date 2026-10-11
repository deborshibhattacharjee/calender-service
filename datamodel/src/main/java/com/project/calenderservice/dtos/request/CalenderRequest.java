package com.project.calenderservice.dtos.request;

import java.util.UUID;

public record CalenderRequest(
        UUID ownerId,
        String name,
        String description,
        String timeZone
) {}
