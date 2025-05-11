package com.example.user_service.controller;

import com.example.user_service.dto.auth.request.*;
import com.example.user_service.dto.auth.response.JwtTokenResponse;
import com.example.user_service.dto.response.Response;
import com.example.user_service.dto.rest.request.pickup_point.PickupPointCreateRequest;
import com.example.user_service.invariant.Role;
import com.example.user_service.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/send_code")
    public ResponseEntity<Void> sendCode(@NotBlank(message = "Электронная почта не должна быть пустой")
                                         @Email(message = "Электронная почта должна быть валидной")
                                         @RequestParam(name = "email") String email) {
        authenticationService.sendVerificationCode(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign_up/user")
    public ResponseEntity<Void> signUpUser(@Valid @RequestBody SignUpRequest requestData) {
        authenticationService.signUpUser(requestData);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign_up/seller")
    public ResponseEntity<Void> signUpSeller(@Valid @RequestBody SignUpSellerRequest requestData) {
        authenticationService.signUpSeller(requestData);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign_up/pickup_point")
    public ResponseEntity<Void> signUpPickupPoint(@Valid @RequestBody SignUpPickupPointRequest requestData) {
        authenticationService.signUpPickupPoint(requestData);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign_in/with_code/user")
    public ResponseEntity<JwtTokenResponse> signInWithCodeUser(@Valid @RequestBody SignInWithCodeRequest requestData) {
        String token = authenticationService.signInWithCode(requestData.getEmail(), requestData.getCode(), Role.USER);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }

    @PostMapping("/sign_in/with_password/user")
    public ResponseEntity<JwtTokenResponse> signInWithPasswordUser(@Valid @RequestBody SignInWithPasswordRequest requestData) {
        String token = authenticationService.signInWithPassword(requestData.getEmail(), requestData.getPassword(), Role.USER);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }

    @PostMapping("/sign_in/with_code/seller")
    public ResponseEntity<JwtTokenResponse> signInWithCodeSeller(@Valid @RequestBody SignInWithCodeRequest requestData) {
        String token = authenticationService.signInWithCode(requestData.getEmail(), requestData.getCode(), Role.SELLER);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }

    @PostMapping("/sign_in/with_password/seller")
    public ResponseEntity<JwtTokenResponse> signInWithPasswordSeller(@Valid @RequestBody SignInWithPasswordRequest requestData) {
        String token = authenticationService.signInWithPassword(requestData.getEmail(), requestData.getPassword(), Role.SELLER);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }

    @PostMapping("/sign_in/with_code/pickup_point")
    public ResponseEntity<JwtTokenResponse> signInWithCodePickupPoint(@Valid @RequestBody SignInWithCodeRequest requestData) {
        String token = authenticationService.signInWithCode(requestData.getEmail(), requestData.getCode(), Role.PICKUP_POINT);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }

    @PostMapping("/sign_in/with_password/pickup_point")
    public ResponseEntity<JwtTokenResponse> signInWithPasswordPickupPoint(@Valid @RequestBody SignInWithPasswordRequest requestData) {
        String token = authenticationService.signInWithPassword(requestData.getEmail(), requestData.getPassword(), Role.PICKUP_POINT);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtTokenResponse(token));
    }
}