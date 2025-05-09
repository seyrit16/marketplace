package com.example.order_service.repository;

import com.example.order_service.model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, UUID> {
}
