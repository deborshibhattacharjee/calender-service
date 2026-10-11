package com.project.calenderservice.config;

import com.project.calenderservice.dtos.UserContext;
import com.project.calenderservice.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserContextInterceptor implements HandlerInterceptor {

    private final UserContext userContext;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler){
        String userId = request.getHeader("X-User-Id");
        if(userId==null || userId.isBlank()){
            throw new BadRequestException("X-User-Id is required");
        }
        try {
            userContext.setUserId(UUID.fromString(userId));
        } catch (IllegalArgumentException exception){
            throw new BadRequestException("Invalid X-User-Id header");
        }

        return true;
    }

}
