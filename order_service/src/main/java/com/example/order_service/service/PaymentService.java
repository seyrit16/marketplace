package com.example.order_service.service;

import com.example.order_service.dto.request.PaymentRequest;

public interface PaymentService {
    void pay(PaymentRequest data);
}
