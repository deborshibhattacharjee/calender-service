package com.project.calenderservice.api;

import com.project.calenderservice.dtos.request.CalenderRequest;
import com.project.calenderservice.dtos.request.CalenderUpdaterRequest;
import com.project.calenderservice.dtos.response.CalenderResponse;
import com.project.calenderservice.service.CalenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/calender/v1")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    @PostMapping
    public ResponseEntity<CalenderResponse> createCalender(@Valid @RequestBody CalenderRequest request){
        CalenderResponse response = calenderService.createCalender(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                .buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalenderResponse> getCalender(@PathVariable UUID id){
        return ResponseEntity.ok(calenderService.getCalender(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CalenderResponse>> getCalendersByOwnerId(){
        return ResponseEntity.ok(calenderService.getCalenderByOwnerId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalenderResponse> updateCalender(@PathVariable UUID id,
                                                           @Valid @RequestBody CalenderUpdaterRequest request){
        return ResponseEntity.ok(calenderService.updateCalender(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCalender(@PathVariable UUID id){
        calenderService.deleteCalender(id);
        return ResponseEntity.ok(Map.of("status", "deleted"));
    }
}
