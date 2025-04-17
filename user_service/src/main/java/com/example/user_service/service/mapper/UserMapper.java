package com.example.user_service.service.mapper;

import com.example.user_service.dto.response.user.UserResponse;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.model.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponse toUserResponse(User user);

    @Mapping(target = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "surname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "patronymic", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "phoneNumber", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUserUpdateDto(UserUpdateRequest dto, @MappingTarget User user);
}
