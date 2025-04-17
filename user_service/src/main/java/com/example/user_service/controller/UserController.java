package com.example.user_service.controller;

import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.dto.response.Response;
import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import com.example.user_service.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getUserData(){
        User user = userService.getUserById(userService.getUserFromAuthentication().getId());
        UserResponse userResponse = userMapper.toUserResponse(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }

    @PutMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest requestData){

        UserResponse userResponse = userService.update(requestData);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(){
        userService.delete();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
