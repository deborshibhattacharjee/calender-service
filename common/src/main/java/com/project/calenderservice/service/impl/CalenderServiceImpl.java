package com.project.calenderservice.service.impl;

import com.project.calenderservice.dtos.request.CalenderRequest;
import com.project.calenderservice.dtos.request.CalenderUpdaterRequest;
import com.project.calenderservice.dtos.response.CalenderResponse;
import com.project.calenderservice.entities.Calender;
import com.project.calenderservice.exception.ResourceNotFoundException;
import com.project.calenderservice.repository.CalenderRepository;
import com.project.calenderservice.repository.UserRepository;
import com.project.calenderservice.service.CalenderService;
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


    @Override
    @Transactional
    public CalenderResponse createCalender(CalenderRequest request) {
        if(!userRepository.existsById(request.ownerId())){
            throw new ResourceNotFoundException("User does not exist: " + request.ownerId());
        }

        Calender calender = Calender.builder().ownerId(request.ownerId()).name(request.name())
                .description(request.description()).timeZone(request.timeZone()).build();
        return mapToResponse(calenderRepository.save(calender));
    }

    @Override
    @Transactional(readOnly = true)
    public CalenderResponse getCalender(UUID id) {
        Calender calender = calenderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Calender not found"));
        return mapToResponse(calender);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CalenderResponse> getCalenderByOwnerId(UUID ownerId) {
        if(!userRepository.existsById(ownerId)){
            throw new ResourceNotFoundException("User does not exist");
        }

        List<Calender> calenders = calenderRepository.findByOwnerId(ownerId);
        return calenders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public CalenderResponse updateCalender(UUID id, CalenderUpdaterRequest request) {
        Calender calender = calenderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Calender does not exist"));

        calender.setName(request.name());
        calender.setDescription(request.description());
        calender.setTimeZone(request.timeZone());

        return mapToResponse(calenderRepository.save(calender));
    }

    @Override
    public void deleteCalender(UUID id) {
        Calender calender = calenderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Calender does not exist"));
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
