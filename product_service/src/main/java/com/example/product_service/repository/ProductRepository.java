package com.example.product_service.repository;

import co.elastic.clients.elasticsearch.nodes.OperatingSystem;
import com.example.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findAllBySellerId(UUID id);
}
