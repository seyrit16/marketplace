package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductImageCreateRequest;
import com.example.product_service.dto.request.ProductImageUpdateRequest;
import com.example.product_service.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/product_image")
@Validated
public class ProductImageController {

    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProductImage(@Valid @RequestPart("data") ProductImageCreateRequest data,
                                                   @NotNull(message = "Файл не должен быть пустым")
                                                   @Size(max = 5242880, message = "Размер файла не должен превышать 5MB")
                                                   @RequestPart("file") MultipartFile file) {
        productImageService.create(data, file);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update_product_imgs")
    public ResponseEntity<Void> updateProductImages(@Valid @RequestBody List<ProductImageUpdateRequest> data) {
        productImageService.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProductImage(@Positive(message = "Идентификатор изображения должен быть положительным числом") @RequestParam(name = "id") Long id) {
        productImageService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}