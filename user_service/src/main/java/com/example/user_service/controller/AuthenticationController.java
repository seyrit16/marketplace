package com.example.user_service.controller;

import com.example.user_service.dto.auth.request.SignInWithCodeRequest;
import com.example.user_service.dto.auth.request.SignInWithPasswordRequest;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.auth.response.JwtTokenResponse;
import com.example.user_service.dto.response.Response;
import com.example.user_service.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/send_code")
    public ResponseEntity<Void> sendCode(@RequestParam(name = "email") String email) {
        authenticationService.sendVerificationCode(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign_up/user")
    public ResponseEntity<Void> signUpUser(@RequestBody SignUpRequest requestData) {

        authenticationService.signUpUser(requestData);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign_in/with_code/user")
    public ResponseEntity<JwtTokenResponse> signInWithCodeUser(@RequestBody SignInWithCodeRequest requestData) {

        String token = authenticationService.signInWithCode(requestData.getEmail(), requestData.getCode());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }

    @PostMapping("/sign_in/with_password/user")
    public ResponseEntity<JwtTokenResponse> signInWithPasswordUser(@RequestBody SignInWithPasswordRequest requestData) {

        String token = authenticationService.signInWithPassword(requestData.getEmail(), requestData.getPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }
}
