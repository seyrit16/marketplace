package com.example.user_service.controller;

import com.example.user_service.dto.request.user.UserProfileUpdateRequest;
import com.example.user_service.dto.request.user.UserUpdateEmailRequest;
import com.example.user_service.dto.request.user.UserUpdatePasswordRequest;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.dto.response.user.UserProfileResponse;
import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import com.example.user_service.service.UserProfileService;
import com.example.user_service.service.UserService;
import com.example.user_service.service.mapper.UserMapper;
import com.example.user_service.service.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserController(UserService userService, UserProfileService userProfileService, UserMapper userMapper, UserProfileMapper userProfileMapper) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.userMapper = userMapper;
        this.userProfileMapper = userProfileMapper;
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getUserData() {
        User user = userService.getUserById(userService.getUserFromAuthentication().getId());
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }

    @PutMapping("/update/password")
    public ResponseEntity<UserResponse> updateUserPassword(@Valid @RequestBody UserUpdatePasswordRequest dto) {
        User user = userService.updatePassword(dto);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }

    @PutMapping("/update/email")
    public ResponseEntity<UserResponse> updateUserEmail(@Valid @RequestBody UserUpdateEmailRequest dto) {
        UserResponse userResponse = userService.updateEmail(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }

    @PutMapping("/update/user_profile")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@Valid @RequestBody UserProfileUpdateRequest dto) {
        UserProfile userProfile = userProfileService.updateUserProfile(dto);
        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileDto(userProfile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userProfileResponse);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser() {
        userService.delete();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
