package com.example.seller_service.repository;

import com.example.seller_service.model.SellerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<SellerProfile, UUID> {
}
