package com.example.product_service.service.impl;

import com.example.product_service.dto.request.ProductUpdateRequest;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductSearchService;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductSearchService productSearchService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductSearchService productSearchService, ProductRepository productRepository) {
        this.productSearchService = productSearchService;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Product update(ProductUpdateRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getAllProductsBySellerId(UUID id, Pageable pageable) {
        return null;
    }
}
