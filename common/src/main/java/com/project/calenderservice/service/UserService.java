package com.project.calenderservice.service;

import com.project.calenderservice.dtos.request.UserRequest;
import com.project.calenderservice.dtos.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(UUID id, UserRequest request);

    UserResponse getUserById(UUID id);

    UserResponse getUserByEmail(String email);
}
