package com.example.user_service.service;

import com.example.user_service.dto.auth.request.SignUpPickupPointRequest;
import com.example.user_service.model.User;

public interface PickupPointService {
    User register(SignUpPickupPointRequest data);
}
