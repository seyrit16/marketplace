package com.example.user_service.service.impl;

import com.example.user_service.config.security.components.CustomUserDetails;
import com.example.user_service.dto.auth.request.SignUpRequest;
import com.example.user_service.dto.request.user.UserUpdateEmailRequest;
import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.dto.request.user.UserUpdatePasswordRequest;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.exception.PasswordIsMissingException;
import com.example.user_service.exception.UserAlreadyExistException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.invariant.Role;
import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import com.example.user_service.repository.UserProfileRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import com.example.user_service.service.mapper.UserMapper;
import com.example.user_service.service.security.JwtService;
import com.example.user_service.service.security.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder,
                           @Lazy CustomUserDetailsService customUserDetailsService, JwtService jwtService,
                           UserMapper userMapper) {

        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Такой пользователь не найден.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile getUserProfileById(UUID id) {
        return userProfileRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Такой пользователь не найден.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("Пользователь с таким адресом электронной почты не найден.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void verifyUserExistByEmail(String email) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с таким адресом электронной почты уже существует");
        }
    }

    @Override
    @Transactional
    public User register(SignUpRequest dto) {

        verifyUserExistByEmail(dto.getEmail());

        User user = new User();
        user.setEmail(dto.getEmail());
        Optional.ofNullable(dto.getPassword())
                .filter(password -> !password.trim().isEmpty())
                .map(passwordEncoder::encode)
                .ifPresentOrElse(user::setPassword, () -> {
                    throw new PasswordIsMissingException("Поле пароль является обязательным");
                });
        user.setRole(Role.USER);
        user.setIsActive(true);
        user.setIsLocked(false);
        User savedUser = null;

        if(user.getRole().equals(Role.USER)){

            UserProfile userProfile = UserProfile.builder()
                    .id(user.getId())
                    .user(user)
                    .surname(dto.getUserProfile().getSurname())
                    .name(dto.getUserProfile().getName())
                    .patronymic(dto.getUserProfile().getPatronymic())
                    .cards(new ArrayList<>())
                    .defaultPickupPointId(null)
                    .build();
            savedUser = saveUserProfile(userProfile).getUser();
        }

        return savedUser;
    }

    @Override
    @Transactional
    public UserResponse update(UserUpdateRequest dto) {

        User user = getUserById(getUserFromAuthentication().getId());
        boolean emailChanged = dto.getEmail() != null && !dto.getEmail().equals(user.getEmail());
        if (emailChanged) {
            verifyUserExistByEmail(dto.getEmail());
        }
        userMapper.updateUserFromUserUpdateDto(dto, user);
        User newUser = save(user);
        UserResponse userResponse = userMapper.toUserResponse(user);

        String newToken;
        if (emailChanged) {
            CustomUserDetails customUserDetails =
                    (CustomUserDetails) customUserDetailsService.loadUserByUsername(newUser.getEmail());
            newToken = jwtService.refreshToken(customUserDetails);
            userResponse.setToken(newToken);
        }

        return userResponse;
    }

    @Override
    @Transactional
    public User updatePassword(UserUpdatePasswordRequest dto) {

        User user = getUserById(getUserFromAuthentication().getId());
        Optional.ofNullable(dto.getPassword())
                .filter(password -> !password.trim().isEmpty())
                .map(passwordEncoder::encode)
                .ifPresentOrElse(user::setPassword, () -> {
                    throw new PasswordIsMissingException("Поле пароль является обязательным");
                });
        return save(user);
    }

    @Override
    @Transactional
    public UserResponse updateEmail(UserUpdateEmailRequest dto) {

        User user = getUserById(getUserFromAuthentication().getId());
        boolean emailChanged = dto.getEmail() != null && !dto.getEmail().equals(user.getEmail());
        if (emailChanged) {
            verifyUserExistByEmail(dto.getEmail());
        }
        user.setEmail(dto.getEmail());
        user = save(user);
        UserResponse userResponse = userMapper.toUserResponse(user);

        String newToken;
        if (emailChanged) {
            CustomUserDetails customUserDetails =
                    (CustomUserDetails) customUserDetailsService.loadUserByUsername(user.getEmail());
            newToken = jwtService.refreshToken(customUserDetails);
            userResponse.setToken(newToken);
        }

        return userResponse;
    }

    @Override
    @Transactional
    public void delete() {

        User user = getUserById(getUserFromAuthentication().getId());
        user.setIsActive(false);
        save(user);
    }

    @Override
    public CustomUserDetails getUserFromAuthentication() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable((CustomUserDetails) auth.getPrincipal())
                .orElseThrow(()-> new UserNotFoundException("Пользователь не найден."));
    }
}
