package com.example.user_service.service.impl;

import com.example.user_service.config.security.components.CustomUserDetails;
import com.example.user_service.dto.request.user.UserProfileUpdateRequest;
import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import com.example.user_service.repository.UserProfileRepository;
import com.example.user_service.service.UserProfileService;
import com.example.user_service.service.UserService;
import com.example.user_service.service.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserService userService;
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserService userService, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    @Transactional
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile getById(UUID id) {
        return userProfileRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("Такой пользователь не найден."));
    }

    @Override
    @Transactional
    public UserProfile updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest) {

        UserProfile userProfile = getById(userService.getUserFromAuthentication().getId());
        userProfileMapper.updateUserProfileFromUpdateDto(userProfileUpdateRequest,userProfile);

        return save(userProfile);
    }
}
