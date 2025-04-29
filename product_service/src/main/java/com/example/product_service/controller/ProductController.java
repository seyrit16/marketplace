package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(@RequestPart("data") ProductCreateRequest data,
                                              @RequestPart("files") List<MultipartFile> files){

        productService.create(data, files);
        return ResponseEntity.ok().build();
    }
}
