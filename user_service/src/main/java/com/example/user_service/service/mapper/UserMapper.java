package com.example.user_service.service.mapper;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.dto.response.UserResponse;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "verifyCode", ignore = true)
    @Mapping(target = "token", ignore = true)
    UserDTO toUserDTO(User user);

    UserResponse toUserResponse(User user);

    User fromUserDTO(UserDTO userDTO);

    void updateUserFromUserUpdateDto(UserUpdateRequest dto, @MappingTarget User user);
}
