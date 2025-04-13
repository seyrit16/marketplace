package com.example.user_service.service;

import com.example.user_service.config.security.components.JwtData;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.response.UserResponse;
import com.example.user_service.dto.request.user.UserUpdatePasswordRequest;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.model.User;

public interface UserService {
    User save(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    void verifyUserExistByEmail(String email);

    User register(SignUpRequest dto);

    UserResponse update(JwtData jwtData, UserUpdateRequest dto);

    User updatePassword(JwtData jwtData, UserUpdatePasswordRequest dto);

    void delete(JwtData jwtData);
}
