package com.project.calenderservice.service;

import com.project.calenderservice.dtos.request.CalenderRequest;
import com.project.calenderservice.dtos.request.CalenderUpdaterRequest;
import com.project.calenderservice.dtos.response.CalenderResponse;

import java.util.List;
import java.util.UUID;

public interface CalenderService {

    CalenderResponse createCalender(CalenderRequest request);

    CalenderResponse getCalender(UUID id);

    List<CalenderResponse> getCalenderByOwnerId(UUID ownerId);

    CalenderResponse updateCalender(UUID id, CalenderUpdaterRequest request);

    void deleteCalender(UUID id);
}
