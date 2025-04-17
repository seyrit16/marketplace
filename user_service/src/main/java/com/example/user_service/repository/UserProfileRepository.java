package com.example.user_service.repository;

import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

}
