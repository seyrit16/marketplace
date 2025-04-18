package com.example.user_service.service;

import com.example.user_service.client.SellerClient;
import com.example.user_service.dto.auth.request.SignUpSellerRequest;
import com.example.user_service.exception.PasswordIsMissingException;
import com.example.user_service.exception.SellerClientException;
import com.example.user_service.invariant.Role;
import com.example.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SellerClient sellerClient;

    @Autowired
    public SellerServiceImpl(UserService userService, PasswordEncoder passwordEncoder, SellerClient sellerClient) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.sellerClient = sellerClient;
    }

    @Override
    @Transactional
    public User register(SignUpSellerRequest dto) {
        userService.verifyUserExistByEmail(dto.getEmail());

        User user = new User();
        user.setEmail(dto.getEmail());
        Optional.ofNullable(dto.getPassword())
                .filter(password -> !password.trim().isEmpty())
                .map(passwordEncoder::encode)
                .ifPresentOrElse(user::setPassword, () -> {
                    throw new PasswordIsMissingException("Поле пароль является обязательным");
                });
        user.setRole(Role.SELLER);
        user.setIsActive(true);
        user.setIsLocked(false);
        User savedUser = userService.save(user);

        if(user.getRole().equals(Role.SELLER)){
            try{
                dto.getSellerCreateRequest().setId(savedUser.getId());
                sellerClient.createSeller(
                        dto.getSellerCreateRequest()
                );
            }catch (Exception exception){
                exception.printStackTrace();
                throw new SellerClientException("Ошибка при создании покупателя.",exception.getCause());
            }
        }

        return savedUser;
    }
}
