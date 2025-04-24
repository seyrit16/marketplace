package com.example.user_service.service;

import com.example.user_service.dto.request.user.UserProfileUpdateRequest;
import com.example.user_service.model.UserProfile;

import java.util.UUID;

public interface UserProfileService {
    UserProfile save(UserProfile userProfile);
    UserProfile getById(UUID id);
    UserProfile updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest);
}
