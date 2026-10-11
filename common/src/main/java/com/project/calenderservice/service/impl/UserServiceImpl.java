package com.project.calenderservice.service.impl;

import com.project.calenderservice.dtos.UserContext;
import com.project.calenderservice.dtos.request.UserRequest;
import com.project.calenderservice.dtos.response.UserResponse;
import com.project.calenderservice.entities.User;
import com.project.calenderservice.exception.UserAlreadyExistsException;
import com.project.calenderservice.exception.ResourceNotFoundException;
import com.project.calenderservice.repository.UserRepository;
import com.project.calenderservice.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserContext userContext;


    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmailId(request.emailId())) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.emailId());
        }

        User user = User.builder().name(request.name()).emailId(request.emailId()).build();
        return mapToResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserRequest request) {
        UUID userId = userContext.getUserId();
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User with id "+ userId + " not found"));

        user.setName(request.name());
        user.setEmailId(request.emailId());

        return mapToResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById() {
        UUID userId = userContext.getUserId();
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User with id "+ userId + " not found"));
        return mapToResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmailId(email).orElseThrow(()->
                new ResourceNotFoundException("User with email " + email +" not found"));
        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmailId(),
                user.getCreatedTime(),
                user.getModifiedTime()
        );
    }
}

