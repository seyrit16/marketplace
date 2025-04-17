package com.example.user_service.service;

import com.example.user_service.config.security.components.CustomUserDetails;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.dto.request.user.UserUpdatePasswordRequest;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.model.User;

import java.util.UUID;

public interface UserService {
    User save(User user);

    User getUserById(UUID id);

    User getUserByEmail(String email);

    void verifyUserExistByEmail(String email);

    User register(SignUpRequest dto);

    UserResponse update(UserUpdateRequest dto);

    User updatePassword(UserUpdatePasswordRequest dto);

    void delete();
    CustomUserDetails getUserFromAuthentication();
}
