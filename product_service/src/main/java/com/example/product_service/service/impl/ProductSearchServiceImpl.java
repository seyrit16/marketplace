package com.example.product_service.service.impl;

import com.example.product_service.dto.request.ProductSearchRequest;
import com.example.product_service.model.document.ProductDocument;
import com.example.product_service.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ProductSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Page<ProductDocument> searchAllFields(ProductSearchRequest request) {
        return null;
    }
}
