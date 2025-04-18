package com.example.user_service.service;

import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.auth.request.SignUpSellerRequest;
import com.example.user_service.model.User;

public interface SellerService {
    User register(SignUpSellerRequest dto);
}
