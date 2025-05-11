package com.example.order_service.repository;

import com.example.order_service.model.OrderItem;
import com.example.order_service.model.invariant.OrderItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    Optional<List<OrderItem>> findAllByOrder_UserIdAndItemStatusIn(
            UUID id, Collection<OrderItemStatus> statuses
    );
}
