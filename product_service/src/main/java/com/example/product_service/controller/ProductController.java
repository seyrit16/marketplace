package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductCreateRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.service.ProductService;
import com.example.product_service.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@Validated
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public ResponseEntity<ProductResponse> getProductById(@NotNull(message = "Идентификатор продукта не должен быть пустым") @RequestParam(name = "id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productMapper
                        .toProductResponse(productService.getProductById(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(@Valid @RequestPart("data") ProductCreateRequest data,
                                              @NotNull(message = "Файлы не должны быть пустыми")
                                              @Size(min = 1, max = 10, message = "Количество файлов должно быть от 1 до 10")
                                              @RequestPart("files") List<MultipartFile> files) {
        productService.create(data, files);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@NotNull(message = "Идентификатор продукта не должен быть пустым") @RequestParam(name = "id") UUID id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}