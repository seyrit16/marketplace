package com.example.order_service.repository;

import com.example.order_service.model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupRepository extends JpaRepository<PickupPoint,Long> {
}
