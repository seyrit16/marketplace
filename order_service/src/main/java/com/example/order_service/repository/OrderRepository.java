package com.example.order_service.repository;

import com.example.order_service.model.Order;
import com.example.order_service.model.invariant.OrderItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("""
        select distinct o
        from Order o
        join o.items oi
        where o.userId = :userId and oi.itemStatus in :statuses
""")
    Optional<List<Order>> findOrdersByUserIdAndItemStatuses(
            UUID userId, Collection<OrderItemStatus> statuses
    );
}
