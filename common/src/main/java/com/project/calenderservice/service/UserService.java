package com.project.calenderservice.service;

import com.project.calenderservice.dtos.request.UserRequest;
import com.project.calenderservice.dtos.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(UserRequest request);

    UserResponse getUserById();

    UserResponse getUserByEmail(String email);
}
