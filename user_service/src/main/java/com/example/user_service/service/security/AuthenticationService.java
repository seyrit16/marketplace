package com.example.user_service.service.security;

import com.example.user_service.dto.auth.request.SignUpPickupPointRequest;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.auth.request.SignUpSellerRequest;
import com.example.user_service.dto.rest.request.seller.SellerCreateRequest;
import com.example.user_service.invariant.Role;
import com.example.user_service.model.User;

public interface AuthenticationService {
    User signUpUser(SignUpRequest dto);
    User signUpSeller(SignUpSellerRequest dto);
    User signUpPickupPoint(SignUpPickupPointRequest data);
    Boolean checkIsValidCodeByEmail(String email, String verifyCode);

    String signInWithCode(String email, String verifyCode, Role role);

    String signInWithPassword(String email, String password, Role role);

    void sendVerificationCode(String email);
}
