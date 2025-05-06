package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.dto.request.ProductUpdateRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.service.ProductService;
import com.example.product_service.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public ResponseEntity<ProductResponse> getProductById(@RequestParam(name = "id") UUID id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productMapper
                        .toProductResponse(productService.getProductById(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(@RequestPart("data") ProductCreateRequest data,
                                              @RequestPart("files") List<MultipartFile> files){

        productService.create(data, files);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@RequestParam(name = "id") UUID id){

        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
