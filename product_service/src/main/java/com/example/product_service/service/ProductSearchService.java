package com.example.product_service.service;

import com.example.product_service.dto.request.ProductSearchRequest;
import com.example.product_service.model.document.ProductDocument;
import org.springframework.data.domain.Page;

public interface ProductSearchService {

    Page<ProductDocument> searchAllFields(ProductSearchRequest request);
}
