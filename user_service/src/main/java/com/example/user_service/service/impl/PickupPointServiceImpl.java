package com.example.user_service.service.impl;

import com.example.user_service.client.OrderClient;
import com.example.user_service.dto.auth.request.SignUpPickupPointRequest;
import com.example.user_service.exception.OrderClientException;
import com.example.user_service.exception.PasswordIsMissingException;
import com.example.user_service.invariant.Role;
import com.example.user_service.model.User;
import com.example.user_service.service.PickupPointService;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PickupPointServiceImpl implements PickupPointService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderClient orderClient;

    @Autowired
    public PickupPointServiceImpl(UserService userService, PasswordEncoder passwordEncoder, OrderClient orderClient) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderClient = orderClient;
    }

    @Override
    @Transactional
    public User register(SignUpPickupPointRequest data) {

        userService.verifyUserExistByEmail(data.getEmail());
        User user = new User();
        user.setEmail(data.getEmail());
        Optional.ofNullable(data.getPassword())
                .filter(password -> !password.trim().isEmpty())
                .map(passwordEncoder::encode)
                .ifPresentOrElse(user::setPassword, () -> {
                    throw new PasswordIsMissingException("Поле пароль является обязательным");
                });
        user.setRole(Role.PICKUP_POINT);
        user.setIsActive(true);
        user.setIsLocked(false);
        User savedUser = userService.save(user);

        if(user.getRole().equals(Role.PICKUP_POINT)){
            try{
                data.getPickupPointCreateRequest().setId(savedUser.getId());
                orderClient.createPickupPoint(
                        data.getPickupPointCreateRequest()
                );
            }catch (Exception exception){
                exception.printStackTrace();
                throw new OrderClientException("Ошибка при создании пвз.",exception.getCause());
            }
        }

        return savedUser;
    }
}
