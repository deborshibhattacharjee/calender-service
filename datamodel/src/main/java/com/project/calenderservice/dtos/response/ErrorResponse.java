package com.project.calenderservice.dtos.response;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Instant timeStamp
) {}
