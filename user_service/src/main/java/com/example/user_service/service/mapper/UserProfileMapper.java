package com.example.user_service.service.mapper;

import com.example.user_service.dto.request.user.UserProfileUpdateRequest;
import com.example.user_service.dto.request.user.UserUpdateRequest;
import com.example.user_service.dto.response.user.UserProfileResponse;
import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {

    @Named("toUserProfileDto")
    UserProfileResponse  toUserProfileDto(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateUserProfileFromUpdateDto(UserProfileUpdateRequest dto, @MappingTarget UserProfile userProfile);
}
