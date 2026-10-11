package com.project.calenderservice.validator;

import com.project.calenderservice.exception.BadRequestException;

import java.time.DateTimeException;
import java.time.ZoneId;

public interface Validator {
    void validate();

    default void validateTimeZone(String timeZone){
        try {
            ZoneId.of(timeZone);
        } catch (DateTimeException exception){
            throw new BadRequestException("Invalid time zone: " + timeZone);
        }
    }
}
