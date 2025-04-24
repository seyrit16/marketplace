package com.example.user_service.service.mapper;

import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateUserFromUserUpdateDto(UserUpdateRequest dto, @MappingTarget User user);
}
