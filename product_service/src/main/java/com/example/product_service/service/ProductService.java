package com.example.product_service.service;

import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.dto.request.ProductUpdateRequest;
import com.example.product_service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product save(Product product);

    Product create(ProductCreateRequest data, List<MultipartFile> files);

    List<Product> getProductsById(List<UUID> ids);

    Product getProductById(UUID id);

    Product update(ProductUpdateRequest request);

    void delete(Product product);

    Page<Product> getAllProductsBySellerId(UUID id, Pageable pageable);
}
