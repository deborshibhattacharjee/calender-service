package com.project.calenderservice.service.impl;

import com.project.calenderservice.dtos.UserContext;
import com.project.calenderservice.dtos.request.CalenderRequest;
import com.project.calenderservice.dtos.request.CalenderUpdaterRequest;
import com.project.calenderservice.dtos.response.CalenderResponse;
import com.project.calenderservice.entities.Calender;
import com.project.calenderservice.exception.ResourceNotFoundException;
import com.project.calenderservice.repository.CalenderRepository;
import com.project.calenderservice.repository.UserRepository;
import com.project.calenderservice.service.CalenderService;
import com.project.calenderservice.validator.CalenderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalenderServiceImpl implements CalenderService {

    private final CalenderRepository calenderRepository;
    private final UserRepository userRepository;
    private final CalenderValidator calenderValidator;
    private final UserContext userContext;


    @Override
    @Transactional
    public CalenderResponse createCalender(CalenderRequest request) {
        if(!userRepository.existsById(request.ownerId())){
            throw new ResourceNotFoundException("User does not exist: " + request.ownerId());
        }
        calenderValidator.validateTimeZone(request.timeZone());
        Calender calender = Calender.builder().ownerId(request.ownerId()).name(request.name())
                .description(request.description()).timeZone(request.timeZone()).build();
        return mapToResponse(calenderRepository.save(calender));
    }

    @Override
    @Transactional(readOnly = true)
    public CalenderResponse getCalender(UUID id) {
        UUID ownerId = userContext.getUserId();
        Calender calender = calenderRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(()->new ResourceNotFoundException("Calender not found"));
        return mapToResponse(calender);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CalenderResponse> getCalenderByOwnerId() {
        UUID ownerId = userContext.getUserId();
        if(!userRepository.existsById(ownerId)){
            throw new ResourceNotFoundException("User does not exist");
        }
        List<Calender> calenders = calenderRepository.findByOwnerId(ownerId);
        return calenders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CalenderResponse updateCalender(UUID id, CalenderUpdaterRequest request) {
        UUID ownerId = userContext.getUserId();
        Calender calender = calenderRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(()->new ResourceNotFoundException("Calender not found"));

        calenderValidator.validateTimeZone(request.timeZone());
        calender.setName(request.name());
        calender.setDescription(request.description());
        calender.setTimeZone(request.timeZone());

        return mapToResponse(calenderRepository.save(calender));
    }

    @Override
    @Transactional
    public void deleteCalender(UUID id) {
        UUID ownerId = userContext.getUserId();
        Calender calender = calenderRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(()->new ResourceNotFoundException("Calender not found"));
        calenderRepository.delete(calender);
    }

    private CalenderResponse mapToResponse(Calender calender){
        return new CalenderResponse(
                calender.getId(),
                calender.getOwnerId(),
                calender.getName(),
                calender.getDescription(),
                calender.getTimeZone(),
                calender.getCreatedTime(),
                calender.getModifiedTime()
        );
    }
}
