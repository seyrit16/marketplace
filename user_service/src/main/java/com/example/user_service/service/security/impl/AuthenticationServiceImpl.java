package com.example.user_service.service.security.impl;

import com.example.user_service.config.security.components.CustomUserDetails;
import com.example.user_service.dto.auth.request.SignUpPickupPointRequest;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.auth.request.SignUpSellerRequest;
import com.example.user_service.exception.AuthenticationFailedException;
import com.example.user_service.exception.InvalidVerificationCodeException;
import com.example.user_service.invariant.Role;
import com.example.user_service.model.User;
import com.example.user_service.service.*;
import com.example.user_service.service.security.AuthenticationService;
import com.example.user_service.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final JwtService jwtService;
    private final VerificationService verificationService;
    private final EmailService emailService;
    private final SellerService sellerService;
    private final PickupPointService pickupPointService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, @Qualifier("customUserDetailsService") CustomUserDetailsService customUserDetailsService, UserService userService, JwtService jwtService, VerificationService verificationService, EmailService emailService, SellerService sellerService, PickupPointService pickupPointService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.sellerService = sellerService;
        this.pickupPointService = pickupPointService;
    }

    @Override
    @Transactional
    public User signUpUser(SignUpRequest dto) {

        checkIsValidCodeByEmail(dto.getEmail(), dto.getVerifyCode());
        verificationService.deleteCode(dto.getEmail());
        return userService.register(dto);
    }

    @Override
    @Transactional
    public User signUpSeller(SignUpSellerRequest dto) {
        checkIsValidCodeByEmail(dto.getEmail(), dto.getCode());
        verificationService.deleteCode(dto.getEmail());
        return sellerService.register(dto);
    }

    @Override
    @Transactional
    public User signUpPickupPoint(SignUpPickupPointRequest data) {

        checkIsValidCodeByEmail(data.getEmail(), data.getCode());
        verificationService.deleteCode(data.getEmail());
        return pickupPointService.register(data);
    }


    @Override
    public Boolean checkIsValidCodeByEmail(String email, String verifyCode) {
        if (!verificationService.verifyCode(email, verifyCode)) {
            throw new InvalidVerificationCodeException("Неверный код авторизации.");
        }
        return true;
    }

    @Override
    public String signInWithCode(String email, String verifyCode, Role role) {

        checkIsValidCodeByEmail(email, verifyCode);

        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
        verificationService.deleteCode(email);
        if (!customUserDetails.getRole().equals(role)) {
            throw new AuthenticationFailedException("Доступ запрещён: используйте соответствующий адрес для входа в систему.");
        }

        return jwtService.generateToken(customUserDetails);
    }

    @Override
    public String signInWithPassword(String email, String password, Role role) {
        CustomUserDetails customUserDetails;
        try {
            String effectivePassword = Optional.ofNullable(password).orElse("");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, effectivePassword)
            );
            customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        } catch (AuthenticationException exception) {
            throw new AuthenticationFailedException("Неверный адрес почты или пароль.");
        }
        if (!customUserDetails.getRole().equals(role)) {
            throw new AuthenticationFailedException("Доступ запрещён: используйте соответствующий адрес для входа в систему.");
        }

        return jwtService.generateToken(customUserDetails);
    }

    @Override
    public void sendVerificationCode(String email) {
        String code = String.format("%06d", (int) (Math.random() * 900000) + 100000);
        verificationService.saveVerificationCode(email, code);
        emailService.sendSimpleEmail(email, "Marketplace", "Ваш код верификации: " + code);
    }
}
