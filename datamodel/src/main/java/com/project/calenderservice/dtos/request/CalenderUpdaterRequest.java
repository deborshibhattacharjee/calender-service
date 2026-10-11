package com.project.calenderservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public record CalenderUpdaterRequest (

    @NotBlank
    String name,
    String description,
    @NotBlank
    String timeZone
){}
