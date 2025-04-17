package com.example.user_service.service.security.impl;

import com.example.user_service.config.security.components.CustomUserDetails;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.exception.AuthenticationFailedException;
import com.example.user_service.exception.InvalidVerificationCodeException;
import com.example.user_service.model.User;
import com.example.user_service.service.EmailService;
import com.example.user_service.service.UserService;
import com.example.user_service.service.VerificationService;
import com.example.user_service.service.security.AuthenticationService;
import com.example.user_service.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService  {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final JwtService jwtService;
    private final VerificationService verificationService;
    private final EmailService emailService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, @Qualifier("customUserDetailsService") CustomUserDetailsService customUserDetailsService, UserService userService, JwtService jwtService, VerificationService verificationService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.verificationService = verificationService;
        this.emailService = emailService;
    }

    @Override
    public User signUpUser(SignUpRequest dto) {

        checkIsValidCodeByEmail(dto.getEmail(), dto.getVerifyCode());
        verificationService.deleteCode(dto.getEmail());
        return userService.register(dto);
    }

    @Override
    public Boolean checkIsValidCodeByEmail(String email, String verifyCode) {
        if(!verificationService.verifyCode(email,verifyCode)){
            throw new InvalidVerificationCodeException("Неверный код авторизации.");
        }
        return true;
    }

    @Override
    public String signInWithCode(String email, String verifyCode) {

        checkIsValidCodeByEmail(email, verifyCode);

        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
        verificationService.deleteCode(email);

        return jwtService.generateToken(customUserDetails);
    }

    @Override
    public String signInWithPassword(String email, String password) {
        CustomUserDetails customUserDetails;
        try{
            String effectivePassword = Optional.ofNullable(password).orElse("");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, effectivePassword)
            );
            customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        }catch (AuthenticationException exception){
            throw new AuthenticationFailedException("Неверный адрес почты или пароль.");
        }

        return jwtService.generateToken(customUserDetails);
    }

    @Override
    public void sendVerificationCode(String email) {
        String code = String.format("%06d", (int)(Math.random() * 900000) + 100000);
        verificationService.saveVerificationCode(email,code);
        emailService.sendSimpleEmail(email, "Marketplace", "Ваш код верификации: " + code);
    }
}
