package com.example.user_service.service.security.impl;

import com.example.user_service.config.security.components.CustomUserDetails;
import com.example.user_service.dto.UserDTO;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService{

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        return getUser(user).map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("Такой адрес электронной почты не найден."));
    }

    private Optional<UserDTO> getUser(User user){
        if(user == null) {
            return Optional.empty();
        }

        UserDTO userDTO =UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .isLocked(user.getIsLocked())
                .build();
        return Optional.of(userDTO);
    }
}
