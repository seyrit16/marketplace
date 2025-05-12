package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductSearchRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.service.ProductSearchService;
import com.example.product_service.service.ProductService;
import com.example.product_service.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/search")
@Validated
public class SearchController {

    private final ProductSearchService productSearchService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public SearchController(ProductSearchService productSearchService, ProductService productService, ProductMapper productMapper) {
        this.productSearchService = productSearchService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/query")
    public ResponseEntity<List<ProductResponse>> searchByQuery(@Valid @RequestBody ProductSearchRequest data,
                                                               Pageable pageable) throws IOException {
        data.setPageable(pageable);
        List<UUID> productIds = productSearchService.searchIdsByQuery(data);

        List<Product> products = productService.getProductsById(productIds);
        List<ProductResponse> productResponses = products.stream()
                .map(productMapper::toProductResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(productResponses);
    }
}