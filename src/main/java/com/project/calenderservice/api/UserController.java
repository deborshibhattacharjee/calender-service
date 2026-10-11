package com.project.calenderservice.api;

import com.project.calenderservice.dtos.request.UserRequest;
import com.project.calenderservice.dtos.response.UserResponse;
import com.project.calenderservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        UserResponse userResponse = userService.createUser(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userResponse.id()).toUri();

        return ResponseEntity.created(location).body(userResponse);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserById(){
        return ResponseEntity.ok(userService.getUserById());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
