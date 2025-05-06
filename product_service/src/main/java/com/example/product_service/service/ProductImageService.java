package com.example.product_service.service;

import com.example.product_service.dto.request.ProductImageCreateRequest;
import com.example.product_service.dto.request.ProductImageUpdateRequest;
import com.example.product_service.model.ProductImage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    ProductImage save(ProductImage productImage);

    ProductImage create(ProductImageCreateRequest data, MultipartFile file);

    void update(List<ProductImageUpdateRequest> productImages);

    void delete(ProductImage productImage);

    void deleteById(Long id);
}
